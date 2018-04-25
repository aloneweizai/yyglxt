package com.abc.common.soa;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaInterfaceException;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.HttpClientUtils;
import com.abc.common.util.NetWorkUtil;
import com.abc.soa.ConstantsUri;
import com.abc.common.util.ReflectUtils;
import com.abc.soa.response.system.bo.AdminLogBO;
import com.abc.soa.response.system.bo.UserBO;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月10日 下午3:54:30
 * @description
 */
public class SoaConnection<T> extends Connection<T> {

	public final static ObjectMapper mapper = new ObjectMapper();
	private static RestTemplate restTemplate;

//	static {
//		int readTimeout =30000;
//		int connectTimeout=10000;
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		requestFactory.setReadTimeout(readTimeout);
//		requestFactory.setConnectTimeout(connectTimeout);
//		restTemplate = new RestTemplate();
//		restTemplate.setRequestFactory(requestFactory);
//	}

	static {
		int readTimeout =30000;
		int connectTimeout=10000;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		clientHttpRequestFactory.setConnectTimeout(connectTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		restTemplate = new RestTemplate(clientHttpRequestFactory);
	}

	protected Class<T> _class;
	private Object parameters;
	private boolean isDebugMod;
	private HttpMethod httpMethod;
	private HttpServletRequest request;
	private boolean isRestful = true;
	private Object[] objects;

	SoaConnection(HttpServletRequest request, ConstantsUri requestUri, Object parameters,
			Class<T> t, HttpMethod httpMethod, Object... objects) {
		super(requestUri, httpMethod.name());
		this.request = request;
		this._class = t;
		this.parameters=parameters;
		this.httpMethod = httpMethod;
		this.objects = objects;
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

	@Override
	T parseObject() {
		try {
			T res = mapper.readValue(this.getReturnJson(), _class);
			return res;
		} catch (Exception e) {
			_log.error("SoaConnection.parseObject()  soa返回json格式异常", e);
			e.printStackTrace();
			return (T) new BaseResponse("-999", "SOA返回数据格式异常，请联系管理员");
		}
	}

	@Override
	List<T> parseList() {
		try {
            JavaType javaType = getCollectionType(ArrayList.class, _class);
            List<T> res =  (List<T>)mapper.readValue(this.getReturnJson(), javaType);
            return res;
        } catch (Exception e) {
			String msg = "SoaConnection.parseList() soa返回json格式异常";
			_log.error(msg, e);
            e.printStackTrace();
        }
		return null;
	}

	@Override
	void connect() throws SoaInterfaceException {
		long time = System.currentTimeMillis();
		String userToken = null;
		try {
			if (this.isDebugMod) {
				throw new SoaInterfaceException("debug mod");
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Version", SpringCtxHolder.getProperty("abc.version"));

			if (this.request != null) {
				if(this.request.getSession().getAttribute("userToken") != null){
					userToken = (String)this.request.getSession().getAttribute("userToken");
					headers.add("admin-token", userToken);
				}
				ServletContext application = this.request.getSession().getServletContext();
				if (application.getAttribute("accessToken") != null) {
					String accessToken = (String) application.getAttribute("accessToken");
					headers.add("Access-Token", accessToken);
				}
				headers.add("Client-Ip",NetWorkUtil.getClientIp(this.request));
				headers.add("Client-User-Agent", this.request.getHeader("User-Agent"));
			}

			_log.info("请求Uri:" + this.getRemoteUrl());
            _log.info("请求参数:" + mapper.writeValueAsString(this.parameters));
			HttpEntity<String> json =restTemplate.exchange(this.getRemoteUrl(), this.httpMethod, new HttpEntity<Object>(this.parameters, headers), String.class, objects);

			time = System.currentTimeMillis() - time;
			_log.info(this.getConstantsUri().describe + " <" + this.getRemoteUrl() + ">连接时间: " + time + " ms");
			ResponseEntity<String> rs = (ResponseEntity<String>)json;

			_log.info(this.getConstantsUri().describe + "返回StatusCode:"+ rs.getStatusCodeValue() +",json body内容：" + json.getBody());
			this.setReturnJson(json.getBody());

			try {
				restTemplate.exchange(this.getHost() + ConstantsUri.ADMINLOG_LIST.uri, HttpMethod.POST, new HttpEntity<Object>(adminLog(), headers), String.class);
			}catch (Exception e){
				_log.error(ConstantsUri.ADMINLOG_LIST.describe +"异常：", e);
			}
		} catch(Exception e) {
			String erroInfo = "调用" + this.getConstantsUri().describe  + "异常 <url=" + this.getRemoteUrl() + ">";
			_log.error(erroInfo, e);
		}
	}

	private AdminLogBO adminLog(){
		/* 记录日志 */
		AdminLogBO log = new AdminLogBO();
		log.setBusinessUri(this.getRemoteUrl());
		log.setBusinessName(this.getConstantsUri().describe);
		log.setMethod(this.httpMethod.name());
		if(this.request.getSession().getAttribute("currentUser") != null){
			UserBO userBO = (UserBO)this.request.getSession().getAttribute("currentUser");
			log.setAdminId(userBO.getId());
		}
		StringBuilder reqStr = new StringBuilder();
		if(this.parameters != null){
			reqStr.append("请求body:");
			try {
				reqStr.append(mapper.writeValueAsString(this.parameters));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(this.objects != null && this.objects.length>0){
			reqStr.append("请求URL参数:");
			for (Object obj: this.objects){
				reqStr.append(obj).append(";");
			}
		}
		log.setBusinessData(reqStr.toString());
		return log;
	}

	@Override
	void createUrl() {

		this.setHost(SpringCtxHolder.getProperty("abc.soa-url"));

		String uri = this.getHost() + this.getConstantsUri().uri;
		if (!this.isRestful && this.parameters != null) {
			StringBuffer url = new StringBuffer();
			url.append(!this.isRestful ? uri + "?" : uri);

			Map<String, String> map = null;
			try {
				if (!(this.parameters instanceof Map)) {
					map = ReflectUtils.getObjFieldValue(this.parameters);
				} else {
					map = (Map<String, String>) this.parameters;
				}
				if(map != null) {
					Set<String> keys = map.keySet();
					if(keys != null){
						for (String key : keys) {
							String val = (String) map.get(key);
                            url.append("&" + key + "=" + val);
						}
					}
				}
				uri = url.toString();
				this.parameters = null;
			} catch (Exception e){
				String msg = "组装" + this.getConstantsUri().describe + "请求参数异常!";
				_log.error(msg, e);
				throw new SoaInterfaceException(msg);
			}
		}
		this.setRemoteUrl(uri);
	}

	public boolean isDebugMod() {
		return isDebugMod;
	}

	public void setDebugMod(boolean isDebugMod) {
		this.isDebugMod = isDebugMod;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isRestful() {
		return isRestful;
	}

	public void setRestful(boolean isRestful) {
		this.isRestful = isRestful;
	}



}
