package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.FileOperateUtil;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.consumer.LotteryActivityRs;
import com.abc.soa.response.system.Friendlink;
import com.abc.soa.response.system.bo.DictListBO;
import com.abc.soa.response.system.bo.UserBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.soa.request.consumer.LotteryRq;
import com.abc.soa.response.consumer.LotteryRs;
import com.abc.soa.response.consumer.bo.LotteryBO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/19.
 * 奖品管理
 */
@Controller
@RequestMapping(value = "/consumerManager/lottery")
public class LotteryController {

    protected static Logger log = LoggerFactory.getLogger(LotteryController.class);

    /**
     * 奖品列表
     * @param lotteryRq
     * @param request
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/lottery.php")
    public String lotteryList(LotteryRq lotteryRq,HttpServletRequest request, Model model,HttpSession session) {
        LotteryRq newRq = lotteryRq;
        if(newRq.getName()== null){
            LotteryRq oldRq =(LotteryRq)session.getAttribute(newRq.getClass().getSimpleName());
            if(oldRq != null){
                newRq.setName(oldRq.getName());
            }
        }
        session.setAttribute(newRq.getClass().getSimpleName(),newRq);
        lotteryRq.setSize(0);
        LotteryRs lotteryRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERY_LIST, lotteryRq, LotteryRs.class);
        String imgPath = SpringCtxHolder.getProperty("abc.soa-upload-url");

        for (int i = 0; i < lotteryRs.getDataList().size(); i++) {
            String url = lotteryRs.getDataList().get(i).getImage();
            if (url != null && !"".equals(url)) {
                lotteryRs.getDataList().get(i).setImage(imgPath + url);
            } else {
                lotteryRs.getDataList().get(i).setImage("");
            }
        }

        model.addAttribute("listRs", lotteryRs.getDataList());
        lotteryRq.setTotalItems(lotteryRs.getTotal());
        lotteryRq.calculate();
        model.addAttribute("pagination", lotteryRq);
        return "consumer/lottery/Lottery_list";
    }

    /**
     * 修改奖品
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/lotteryEdit.php")
    public String lotteryEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            LotteryRs lotteryRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERY_INFO, null, LotteryRs.class, id);
            String url = lotteryRs.getData().getImage();
            String imgPath = SpringCtxHolder.getProperty("abc.soa-upload-url");
            if (url != null && !"".equals(url)) {
                lotteryRs.getData().setImage(imgPath + url);
            } else {
                lotteryRs.getData().setImage("");
            }
            model.addAttribute("obj", lotteryRs.getData());
        }

        LotteryActivityRs lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_LIST, null, LotteryActivityRs.class);


        model.addAttribute("lotteryActivityRs", lotteryActivityRs.getDataList());

        //去数据字典获取
        DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "lotteryType");
        if(dicts!= null && dicts.getDataList()!=null && dicts.getDataList().size()>0){
            model.addAttribute("lotteryTypes",dicts.getDataList());
        }

        return "consumer/lottery/lottery_edit";
    }
    private Integer toInt(String str){
        if(str== null || str.isEmpty())return 0;

        return Integer.parseInt(str);
    }
    private Double toDouble(String str){
        if(str== null || str.isEmpty())return 0.0;

        return Double.parseDouble(str);
    }

    /**
     * 添加修改奖品保存
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/lotterySave.php", method = RequestMethod.POST)
    public ModelAndView add(MultipartHttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        LotteryBO lotteryBO = new LotteryBO();
        if (request.getParameter("id") != null) {
            lotteryBO.setId(request.getParameter("id"));
        }
         lotteryBO.setActivityId(request.getParameter("activityId"));
        lotteryBO.setLevel(toInt(request.getParameter("level")));
        lotteryBO.setStock(toInt(request.getParameter("stock")));
        lotteryBO.setCount(toInt(request.getParameter("count")));

        lotteryBO.setTimeStock(toInt(request.getParameter("timeStock")));
        lotteryBO.setTimeCount(toInt(request.getParameter("timeCount")));

        lotteryBO.setLimits(toInt(request.getParameter("limits")));
        lotteryBO.setName(request.getParameter("name"));
        lotteryBO.setDescription(request.getParameter("description"));
        lotteryBO.setCost(toDouble(request.getParameter("cost")));

        lotteryBO.setTypes(request.getParameter("types"));
       // String tmpTime = request.getParameter("startTime");
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");


        lotteryBO.setNoluck( "true".equals(request.getParameter("noluck")));
        lotteryBO.setSend(   "true".equals(request.getParameter("send")));



        BaseResponse rs = null;
        //图片上传开始
        MultipartFile file = request.getFile("FILE01");
        Friendlink result = null;
        if (file != null && file.getSize() > 0) {
            FjDto fjDto = new FjDto();
            fjDto.setFileName(file.getOriginalFilename());
            CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
            try {
                byte[] img = file.getBytes();
                if (file.getSize() > 1024 * 200) {
                    BaseResponse br = new BaseResponse("300", "文件大小不能大于200k");
                    mav.addObject("result", br);
                    return mav;
                }
                if (getFileTypeByStream(img) == null) {
                    BaseResponse br = new BaseResponse("300", "上传文件类型错误!");
                    mav.addObject("result", br);
                    return mav;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
//                fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
                fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
                UserBO userBO = (UserBO) request.getSession().getAttribute("currentUser");
                cmsFileUploadDto.setDirectory(userBO.getId());
                cmsFileUploadDto.getFjBo().add(fjDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileListDto fileListDto = SoaConnectionFactory.post(request, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
            if (fileListDto != null) {
                if (fileListDto.getDataList().size() > 0) {
                    lotteryBO.setImage("/images"+fileListDto.getDataList().get(0).getFilePath());
                }
            }
        }
        if (CommonUtils.nullOrBlank(lotteryBO.getId())) {
            rs = SoaConnectionFactory.post(request, ConstantsUri.LOTTERY_LIST, lotteryBO, LotteryRs.class );

        } else {
            rs = SoaConnectionFactory.put(request, ConstantsUri.LOTTERY_INFO, lotteryBO, LotteryRs.class, lotteryBO.getId());
        }
        mav.addObject("result", rs);
        return mav;
    }

    /**
     * 删除奖品
     */
    @RequestMapping("/lotteryDel.php")
    public
    @ResponseBody
    BaseResponse lotteryDel(@RequestParam(required = true) String id, HttpServletRequest request) {

        return SoaConnectionFactory.delete(request, ConstantsUri.LOTTERY_INFO, null, BaseResponse.class, id);
    }


    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    public String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        getPicFileType();
        System.out.println(filetypeHex);
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP
                .entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue().toUpperCase();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp", "424D");
    }
}
