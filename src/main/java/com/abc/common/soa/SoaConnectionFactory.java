package com.abc.common.soa;

import com.abc.common.exception.SoaInterfaceException;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.soa.Result;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZuoWei
 * @createTime 2015.11.10 14:54:30
 * @description
 */
@Service
@Scope("prototype")
public class SoaConnectionFactory {

	private final static boolean isDebugMod = false;
	private final static String testReturnJson = "{\"msg\": \"connect error\", \"code\": \"01\"}";
	protected static Logger _log = LoggerFactory.getLogger(SoaConnectionFactory.class);

	public static <T extends BaseResponse> T postWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.POST, objects);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T post(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		saveAccessTokenInSession(request);
		return postWithoutToken(request,uri,obj,_class,objects);
	}

	public static <T extends BaseResponse> T getWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.GET, objects);
		res.setRestful(false);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> T get(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class) {
		saveAccessTokenInSession(request);
		return getWithoutToken(request, uri, obj, _class);
	}

	public static <T extends BaseResponse> T get(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		saveAccessTokenInSession(request);
		return getWithoutToken(request, uri, obj, _class,objects);
	}


	public static <T extends BaseResponse> T getRestfulWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects){
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.GET, objects);
        try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T exchange(RestTemplate restTemplate,String url,
						  HttpMethod method,
						  HttpEntity<?> requestEntity,
						  Class<T> responseType) {
		//LOGGER.info("Request: {}, {}", url, requestEntity);

		ResponseEntity<T> responseEntity = null;
		try {

			responseEntity = restTemplate.exchange(url, method, requestEntity, responseType);
		} catch (RestClientException e) {
			//LOGGER.error("RestClient调用服务出现异常: " + e.getMessage(), e);
		}
		//OGGER.info("Response: {}, {}", url, responseEntity);
		return responseEntity != null ? responseEntity.getBody() : null;
	}

	/**
	 * 对请求参数为rest型变量的exchange方法的封装，可以参考对应的方法RestTemplate.exchange
	 *
	 * @param url           相对url地址
	 * @param method        HttpMethod方法类型
	 * @param requestEntity 包含请求头(HttpHeaders)或(和)请求体(Object)的实体
	 * @param uriVariables  uri中的参数占位符
	 * @return String
	 */
	public static String exchange(RestTemplate restTemplate,String url,
						   HttpMethod method,
						   HttpEntity<?> requestEntity,
						   Object... uriVariables) {
		ResponseEntity<String> responseEntity = null;
		Result result = null;
		try {
			responseEntity = restTemplate.exchange(url, method, requestEntity, String.class, uriVariables);
		} catch (RestClientException e) {
			result = new Result.Builder().code("5003").message(e.getMessage()).build();
		}
		return responseEntity != null ? responseEntity.getBody() : result != null ? JSON.toJSONString(result) : null;
	}

	public static <T extends BaseResponse> T getRestfuloutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		ServletContext application = request.getSession().getServletContext();
		if(StringUtils.isEmpty((String) application.getAttribute("expiresTime")) || StringUtils.isEmpty((String) application.getAttribute("accessToken"))){
			SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
		}else{
			String expiresTime = (String)application.getAttribute("expiresTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isEmpty(expiresTime)){
				SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
			}else{
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(sdf.parse((String) application.getAttribute("expiresTime")));
				}catch (Exception e){
					_log.error(e.getMessage());
					cal.setTime(new Date());
					e.printStackTrace();
				}
				Calendar currDateTime = Calendar.getInstance();
				currDateTime.setTime(new Date());
				if(currDateTime.after(cal)){
					SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
				}
			}
		}
		return getRestfulWithoutToken(request,uri,obj,_class,objects);
	}

	public static <T extends BaseResponse> T getRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		saveAccessTokenInSession(request);
		return getRestfulWithoutToken(request,uri,obj,_class,objects);
	}

	public static <T extends BaseResponse> T putRestfulWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.PUT, objects);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T putRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		saveAccessTokenInSession(request);
		return putRestfulWithoutToken(request,uri,obj,_class,objects);
	}
	
	public static <T extends BaseResponse> T put(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.PUT, objects);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> T delete(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.DELETE, objects);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T deleteRestfulWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.DELETE, objects);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T deleteRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		saveAccessTokenInSession(request);
		return deleteRestfulWithoutToken(request, uri, obj, _class, objects);
	}
	
	public static <T extends BaseResponse> T deleteUnRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> _class, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, _class, HttpMethod.DELETE, objects);
		res.setRestful(false);
		try {
			if (isDebugMod) {
				res.setDebugMod(isDebugMod);
				res.setReturnJson(testReturnJson);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> List<T> queryList(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> t) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, t, HttpMethod.GET);
		try {
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseList();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> List<T> queryList(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> t, Object... objects) {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, t, HttpMethod.GET, objects);
		try {
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseList();
		} catch (SoaInterfaceException e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	

	static void checkCode(String str) throws UnsupportedEncodingException {
		System.out.println(str);
		 
        System.out.println(str.getBytes());
 
        System.out.println(str.getBytes("GB2312"));
 
        System.out.println(str.getBytes("ISO8859_1"));
 
        System.out.println(new String(str.getBytes()));
        
        System.out.println(new String(str.getBytes(), "GBK"));
        
        System.out.println(new String(str.getBytes(), "utf-8"));
 
        System.out.println(new String(str.getBytes(), "GB2312"));
 
        System.out.println(new String(str.getBytes(), "ISO8859_1"));
 
        System.out.println(new String(str.getBytes("GB2312")));
 
        System.out.println(new String(str.getBytes("GB2312"), "GB2312"));
 
        System.out.println(new String(str.getBytes("GB2312"), "ISO8859_1"));
 
        System.out.println(new String(str.getBytes("ISO8859_1")));
 
        System.out.println(new String(str.getBytes("ISO8859_1"), "GB2312"));
 
        System.out.println(new String(str.getBytes("ISO8859_1"), "ISO8859_1"));
	}

	/**
	 * 设置AccessToken及expiresTime到session中，便于访问soa时设置head相关参数
	 * @param request
	 */
	public static void saveAccessTokenInSession(HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		if(StringUtils.isEmpty((String) application.getAttribute("expiresTime")) || StringUtils.isEmpty((String) application.getAttribute("accessToken"))){
			SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
		}else{
			String expiresTime = (String)application.getAttribute("expiresTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isEmpty(expiresTime)){
				SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
			}else{
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(sdf.parse((String) application.getAttribute("expiresTime")));
				}catch (Exception e){
					_log.error(e.getMessage());
					cal.setTime(new Date());
					e.printStackTrace();
				}
				Calendar currDateTime = Calendar.getInstance();
				currDateTime.setTime(new Date());
				if(currDateTime.after(cal)){
					SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
				}
			}
		}
	}

	private static boolean continuedAccessToken(HttpServletRequest request,String returnconde){
		boolean flage=false;
		if("4025".equals(returnconde)||"4035".equals(returnconde)){
			SoaAuth.saveAccessTokenInSession(request, ConstantsUri.GET_SOA_ACCESS_TOKEN);
			flage= true;
		}
		return flage;
	}

}
