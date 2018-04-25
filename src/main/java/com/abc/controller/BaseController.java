package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CodeUtil;
import com.abc.common.util.RSAUtils;
import com.abc.dto.CallbackConstants;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.financed.bo.SfExportBO;
import com.abc.soa.response.soa.PublicKey;
import com.abc.soa.response.soa.SmrzCrypt;
import com.abc.soa.response.system.Area;
import com.abc.soa.response.system.City;
import com.abc.soa.response.system.Province;
import com.abc.soa.response.system.bo.*;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月14日 下午7:42:07
 * @description 
 */
public class BaseController {

    protected Logger _log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected SoaConnectionFactory soaConnectionFactory;
    @Autowired
    private RestTemplate restTemplate;

    // App缓存信息
    private static Map<String, String> appCache = new ConcurrentHashMap<>();
    
	public BaseController() {
        super();
    }
    
    protected ModelAndView checkMap(ModelAndView mav, Map<String, String> map){
        if (map.isEmpty()) {
            mav.addObject("rescode", CallbackConstants.PARAMETER_EMPTY.code);
            mav.addObject("message", CallbackConstants.PARAMETER_EMPTY.msg);
            return mav;
        }
        Iterator<?> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if(null == entry.getValue() || "".equals(entry.getValue().toString())){
                mav.addObject("rescode", CallbackConstants.PARAMETER_EMPTY.code);
                mav.addObject("message", CallbackConstants.PARAMETER_EMPTY.msg);
            }
        }
        return mav;
    }
    
    public ModelAndView callback(Object obj) {
    	ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView(), null);
    	modelAndView.addObject("result", obj);
    	return modelAndView;
    }
    
    public ModelAndView callbackMsg(ModelAndView mav, CallbackConstants constants) {
    	BaseResponse response = new BaseResponse(constants.code, constants.msg);
    	mav.addObject("result", response);
        return mav;
    }
    
    public ModelAndView callbackMsg(ModelAndView mav, BaseResponse response) {
        mav.addObject("result", response);
        return mav;
    }
    

    public void nativeResponseWithBytes(HttpServletResponse response, Map resultMap){
            ObjectMapper mapper = new ObjectMapper();
            String resultStr = null;
            PrintWriter out=null;
        try{
                resultStr = mapper.writeValueAsString(resultMap);
                response.setCharacterEncoding("UTF-8"); //设置编码格式
                response.setContentType("text/html");   //设置数据格式
                out = response.getWriter(); //获取写入对象
                out.print(resultStr); //将json数据写入流中
            }catch(Exception e){
                _log.error(e.getMessage());
                e.printStackTrace();
            }finally{
                try {
                    if(out!=null){ out.flush(); out.close();}
                } catch (Exception e) {
                    _log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
    }

    public void nativeResponseWithJsonBytes(HttpServletResponse response, Map resultMap){
        ObjectMapper mapper = new ObjectMapper();
        String resultStr = null;
        PrintWriter out=null;
        try{
            resultStr = mapper.writeValueAsString(resultMap);
            response.setCharacterEncoding("UTF-8"); //设置编码格式
            response.setContentType("application/json");   //设置数据格式
            out = response.getWriter(); //获取写入对象
            out.print(resultStr); //将json数据写入流中
        }catch(Exception e){
            _log.error(e.getMessage());
            e.printStackTrace();
        }finally{
            try {
                if(out!=null){ out.flush(); out.close();}
            } catch (Exception e) {
                _log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 按字典id获取数据字典编码
     */
    public List<DictBO> getDictBOList(HttpServletRequest request,String id){
		DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, id);
		return dicts.getDataList();
	}
    
    
    /**
     * 导出Excel
     * @param dataList
     * @param fileName
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void exportExecl(HttpServletResponse response,List dataList,String fileName,String excelTemp){
    	Workbook workBook = null;
        try {
            Map map = new HashMap();
            //File file = ResourceUtils.getFile(excelTemp);
            map.put("table", dataList);
            XLSTransformer transformer = new XLSTransformer();
            //InputStream input = new FileInputStream(file);
            InputStream input= new ClassPathResource(excelTemp).getInputStream();
            workBook = transformer.transformXLS(input, map);
            fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
            response.setHeader("content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/msexcel");
            OutputStream stream = response.getOutputStream();
            workBook.write(stream);
            stream.flush();
            stream.close();
        } catch (Exception e) {
        	 _log.error("导出Excel异常：",e);
        }
    }
    /**
     * 导出Excel
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void exportExeclfp(HttpServletResponse response,List dataList,String fileName,File file ,String excelTemp) {
        Workbook workBook = null;
        BufferedInputStream in = null;
        List<SfExportBO> sfExportBO = dataList;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            if (excelTemp.endsWith("xlsx")) {
                workBook = new XSSFWorkbook(in);
            } else {
                //POIFSFileSystem fs = new POIFSFileSystem(in);
                workBook = new HSSFWorkbook(in);
            }
            // 遍历每一个sheet
            Sheet sheet = workBook.getSheetAt(0);
            for (int j = 0; j < sfExportBO.size(); j++) {
                // 取得某一行
                Row row = sheet.getRow(j + 3);
                Cell cell = row.getCell(0);
                cell.setCellValue(sfExportBO.get(j).getOrderNo());
                Cell fphm = row.getCell(1);
                fphm.setCellValue(sfExportBO.get(j).getContent());
                Cell cell1 = row.getCell(2);
                cell1.setCellValue(sfExportBO.get(j).getCompany());
                Cell cell2 = row.getCell(3);
                cell2.setCellValue(sfExportBO.get(j).getName());
                Cell cell3 = row.getCell(4);
                cell3.setCellValue(sfExportBO.get(j).getTel());
                Cell cell4 = row.getCell(5);
                cell4.setCellValue(sfExportBO.get(j).getPhone());
                Cell cell5 = row.getCell(6);
                cell5.setCellValue(sfExportBO.get(j).getAddress());
                Cell cell6 = row.getCell(7);
                cell6.setCellValue("寄付月结");
                Cell cell9 = row.getCell(10);
                cell9.setCellValue(sfExportBO.get(j).getCargoContent());
                Cell cell11 = row.getCell(12);
                cell11.setCellValue(sfExportBO.get(j).getNum());
                Cell cell15 = row.getCell(16);
                cell15.setCellValue("云仓专配次日");
            }
            fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
            response.setHeader("content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/msexcel");
            OutputStream stream = response.getOutputStream();
            workBook.write(stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            _log.error("导出Excel异常：", e);
        }
    }
    public String[] getAddres(HttpServletRequest request,String[] addressId){
    	String[] addres=new String[3];
    	if(addressId.length==3){
    		ProvinceListRs list = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
    		List<Province> Pprovinces=list.getDataList();
    		for(Province province:Pprovinces){
    			if(province.getProvinceId().equals(addressId[0])){
    				addres[0]=province.getProvince();
    				break;
    			}
    		}
    		
    		if(!StringUtils.isEmpty(addressId[2])){
    			CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, addressId[0]);
        		List<City> citys=cityListRs.getDataList();
        		for(City city:citys){
        			if(city.getCityId().equals(addressId[1])){
        				addres[1]=city.getCity();
        				break;
        			}
        		}
    		}
    		
    		if(!StringUtils.isEmpty(addressId[1])){
	    		AreaListRs areaListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.AREA, null, AreaListRs.class, addressId[1]);
	    		List<Area> areas=areaListRs.getDataList();
	    		for(Area area:areas){
	    			if(area.getAreaId().equals(addressId[2])){
	    				addres[2]=area.getArea();
	    				break;
	    			}
	    		}
    		}
    	}
    	
    	return addres;
    }

    /**
     * 按字典id获取数据字典编码
     */
    public RSAPublicKey getpublickey(HttpServletRequest request){
        PublicKey key = SoaConnectionFactory.get(request, ConstantsUri.PUBLICKEY_GET, null, PublicKey.class);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(key.getPk()))) {
            return (RSAPublicKey) ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 加密json字符串
     */
    public String rsaEncrypt(String json,HttpServletRequest request) {
        try {
            return new String(RSAUtils.encrypt(getpublickey(request), json.getBytes()), "iso-8859-1");
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Map getEncryptBody(String json,HttpServletRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("p", rsaEncrypt(json,request));
        return body;
    }

    /**
     * 实名认证的加密方法
     * @param json
     * @return
     */
    public String rsaSmrz(String json){
        try {
            byte[] nsrsbhbyte = json.getBytes("utf-8");
            SmrzCrypt crypt = new SmrzCrypt();
            byte[] des = crypt.encryptContent(nsrsbhbyte, "abc4000!");
            String code = CodeUtil.encodeContent("base64", des);
            return code;
        } catch (Exception e) {
            _log.error("加密错误", e.getMessage());
        }
        return null;
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ModelAndView authorizationException(Exception e,HttpServletRequest request) {
        //访问地址
        String url = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
        ModelAndView modelAndView = new ModelAndView("/403");
        modelAndView.addObject("url", url);
        modelAndView.addObject("reason", e.toString());
        if(e.getStackTrace()!=null && e.getStackTrace().length > 0){
            StackTraceElement element = e.getStackTrace()[0];
            modelAndView.addObject("stackTrace", element);
        }

        _log.error("系统捕获到异常：", e);

        return modelAndView;
    }

}
