package com.abc.controller.cszj;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.FileOperateUtil;
import com.abc.controller.BaseController;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.Event.*;
import com.abc.soa.request.cszj.MsgautoRq;
import com.abc.soa.response.cszj.MsgautoRs;
import com.abc.soa.response.cszj.bo.MsgautoBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/6/29.
 */
@Controller
@RequestMapping(value = "/cszjs/msgauto")
public class MsgAutoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MsgAutoController.class);

    /**
     * 跳转至关键词自动回复页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/msgauto_gjc.php")
    public String wxuserList(MsgautoRq msgautoRq, HttpServletRequest request, Model model) {
        Map<String, String> map = new HashMap<String, String>();
        if (!"".equals(msgautoRq.getKeyString()) && (msgautoRq.getKeyString()) != null)
            map.put("keyString", msgautoRq.getKeyString());
        if (!"".equals(msgautoRq.getSearchTp()) && (msgautoRq.getSearchTp()) != null)
            map.put("searchTp", msgautoRq.getSearchTp());

        map.put("page", msgautoRq.getPage() + "");
        map.put("size", msgautoRq.getSize() + "");
        MsgautoRs msgautoRs = SoaConnectionFactory.get(request, ConstantsUri.MSGAUTO_GJC_LIST, map, MsgautoRs.class);
        model.addAttribute("gjcList", msgautoRs.getDataList());
        msgautoRq.setTotalItems(msgautoRs.getTotal());
        msgautoRq.setTotalPage((int) Math.ceil((double) msgautoRq.getTotalItems() / (double) msgautoRq.getSize()));
        model.addAttribute("pagination", msgautoRq);

        return "cszj/msgauto/msgauto_gjc";
    }


    /**
     * 跳转至被添加自动回复页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/msgauto_btj.php", method = RequestMethod.GET)
    public String msgautobtj(HttpServletRequest request, Model model, @RequestParam(value = "setting", required = false) String setting) {
        MsgautoRq msgautoRq = new MsgautoRq();
        msgautoRq.setSetting("0");
        if(setting == null || setting.equals("0")){
            //这里就显示出来
            model.addAttribute("xianshi", "1");
        }
        MsgautoRs msgautoRs = SoaConnectionFactory.get(request, ConstantsUri.MSGAUTO_MSG_QRY, msgautoRq, MsgautoRs.class);
        model.addAttribute("msgautoDto", msgautoRs.getData());
        return "cszj/msgauto/msgauto_btj";
    }


    /**
     * 跳转至消息自动回复页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/msgauto_msg.php", method = RequestMethod.GET)
    public String msgauto(HttpServletRequest request, Model model, @RequestParam(value = "setting", required = false) String setting) {
        MsgautoRq msgautoRq = new MsgautoRq();
        msgautoRq.setSetting(setting);
        ;
        MsgautoRs msgautoRs = SoaConnectionFactory.get(request, ConstantsUri.MSGAUTO_MSG_QRY, msgautoRq, MsgautoRs.class);
        model.addAttribute("msgautoDto", msgautoRs.getData());
        return "cszj/msgauto/msgauto_msg";
    }

    /**
     * 删除
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/delete.php")
    public
    @ResponseBody
    BaseResponse del(String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.MSGAUTO_MSG_DEL, null, BaseResponse.class, id);
    }

    /**
     * 关键词新增页面
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/addPage.php")
    public String addPage(HttpServletRequest request, Model model, @RequestParam(value = "msgId", required = false) String msgId) {
        LOGGER.info("addPage  msgId:{}", msgId);
        if (!StringUtils.isEmpty(msgId)) {
            String id = msgId;
            //     String setting="2";
            MsgautoRs msgautoRs = SoaConnectionFactory.getRestful(request, ConstantsUri.MSGAUTO_MSG_QRYID, null, MsgautoRs.class, id);
            model.addAttribute("msgautoDto", msgautoRs.getData());
        }
        return "cszj/msgauto/gjc_edit";
    }

    /**
     * 保存或新增  自动回复
     *
     * @param
     * @param request
     * @return
     */
    @PostMapping("/addPagepost.php")
    public     @ResponseBody     BaseResponse  addPagepost(@RequestBody MsgautoBO msgautoBO, HttpServletRequest request) {

        LOGGER.info("addPagepost  msgautoBO:{}", msgautoBO.toString());
        String id = msgautoBO.getId();

        msgautoBO.setSetting("2");
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.MSGAUTO_MSG_creat, msgautoBO, MsgautoRs.class);
        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.MSGAUTO_MSG_update, msgautoBO, MsgautoRs.class, id);
        }
        return returnObj;
    }
    /**
     * 消息自动回复修改
     */
    @PostMapping("/update_msg.php")
    public @ResponseBody  BaseResponse  updateMsg(@RequestBody MsgautoBO msgautoBO, HttpServletRequest request) {
        LOGGER.info("updateMsg  {}", msgautoBO.toString());
        String id = msgautoBO.getId();

        msgautoBO.setSetting("1");
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            LOGGER.error("updateMsg id错误");
        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.MSGAUTO_MSG_update, msgautoBO, MsgautoRs.class, id);
        }
        return returnObj;
    }
    /**
     * 添加自动回复修改
     */
    @PostMapping("/update_btj.php")
    public @ResponseBody  BaseResponse  updateBtj(@RequestBody MsgautoBO msgautoBO, HttpServletRequest request) {
        LOGGER.info("updateBtj  {}", msgautoBO.toString());
        String id = msgautoBO.getId();

        msgautoBO.setSetting("0");
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            LOGGER.error("updateBtj id错误");
        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.MSGAUTO_MSG_update, msgautoBO, MsgautoRs.class, id);
        }
        return returnObj;
    }
    /**
     * 活动管理保存操作
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/save.php", method = RequestMethod.POST)
    public ModelAndView add(@RequestBody EventSaveUpdBo eventSaveUpdBo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        EventSponsorDataBo result = null;
        EventSaveBo results = null;
        List<EventModelItemBo> eventModelItemBo = eventSaveUpdBo.getEventModelItemBo(eventSaveUpdBo);
        EventBo eventBo = eventSaveUpdBo.getEventBo(eventSaveUpdBo);
        List<EventSponsorBo> eventSponsorBo = eventSaveUpdBo.getEventSponsorBo(eventSaveUpdBo);
        EventSponsorBo zbf = null;
        if (CommonUtils.nullOrBlank(eventSaveUpdBo.getEventId().trim())) {
            for (int i = 0; i < eventSponsorBo.size(); i++) {
                EventSponsorBo ev = eventSponsorBo.get(i);
                if (ev.getSponsorId() != null && !"".equals(ev.getSponsorId().trim())) {
                    result = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENTSPONSOR_UPD, ev, EventSponsorDataBo.class, ev.getSponsorId().trim());
                } else {
                    result = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENTSPONSOR_SAVE, ev, EventSponsorDataBo.class);
                }
                if (zbf == null) {
                    if (eventSaveUpdBo.getZbfindex() == i) {
                        eventBo.setSponsorId(result.getData().getSponsorId().trim());
                    }
                }
            }
            EventSaveBo esb = new EventSaveBo();
            eventBo.setStatus(eventSaveUpdBo.getStatus());
            esb.setEvent(eventBo);
            esb.setModelItemList(eventModelItemBo);
            results = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENT_SAVE, esb, EventSaveBo.class);
        } else {
            for (int i = 0; i < eventSponsorBo.size(); i++) {
                EventSponsorBo ev = eventSponsorBo.get(i);
                if (ev.getSponsorId() != null && !"".equals(ev.getSponsorId().trim())) {
                    result = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENTSPONSOR_UPD, ev, EventSponsorDataBo.class, ev.getSponsorId());
                } else {
                    result = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENTSPONSOR_SAVE, ev, EventSponsorDataBo.class);
                }
                if (zbf == null) {
                    if (eventSaveUpdBo.getZbfindex() == i) {
                        eventBo.setSponsorId(result.getData().getSponsorId().trim());
                    }
                }
            }
            EventSaveBo esb = new EventSaveBo();
            eventBo.setStatus(eventSaveUpdBo.getStatus());
            esb.setEvent(eventBo);
            esb.setModelItemList(eventModelItemBo);
            results = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENT_UPD, esb, EventSaveBo.class, eventSaveUpdBo.getEventId());
        }
        mav.addObject("result", results);
        return mav;
    }


    /**
     * 活动管理上传图片操作
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/upload.php", method = RequestMethod.POST)
    public
    @ResponseBody
    BaseResponse add(MultipartHttpServletRequest multipartRequest, String path, MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session, String fileTypeTag) {
        MultipartFile file = multipartRequest.getFile("filep");
        CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
        getPicFileType();
        FjDto fjDto = new FjDto();
        fjDto.setFileName(file.getOriginalFilename());
        try {
            byte[] img = file.getBytes();
            if (file.getSize() > 1024 * 200) {
                return new BaseResponse("300", "文件大小不能大于200k");
            }
            if (getFileTypeByStream(img) == null) {
                return new BaseResponse("300", "上传文件类型错误!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
            fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
            cmsFileUploadDto.setDirectory(path.replaceAll("!", "/"));
            cmsFileUploadDto.getFjBo().add(fjDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
        if (fileListDto.getDataList().size() > 0) {
            return new BaseResponse("200", SpringCtxHolder.getProperty("abc.soa-url") + "/images" + fileListDto.getDataList().get(0).getFilePath());
        } else {
            return new BaseResponse("300", "上传失败");
        }
    }

    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    public String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
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

    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp", "424D");
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


    /**
     * 活动统计页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/tj/{id}", method = RequestMethod.GET)
    public ModelAndView eventtjym(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_tj");
        mav.addObject("id", id);
        return mav;
    }

    /**
     * 活动推广页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/tg/{id}", method = RequestMethod.GET)
    public ModelAndView eventtgym(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_tg");
        EventDataBo eventSaveBo = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_EVENT_UPD, null, EventDataBo.class, id);
        StringBuffer sb = new StringBuffer();
        sb.append("http://www.baidu.com?eventid=").append(id);
        StringBuffer sbbm = new StringBuffer();
        sbbm.append("http://www.baidu.com?eventid=").append(id);
        StringBuffer sban = new StringBuffer();
        sban.append("<a href=\"\" style=\"display:inline-block; padding:0px 5px; background: #009900; color: #fff;\">立即报名</a>");
        List<EventModelItemBo> modelItemList = eventSaveBo.getData().getModelItemList();
        StringBuffer bmform = new StringBuffer();
        if (modelItemList != null && modelItemList.size() > 0) {
            bmform.append("<form id='bmform'>");
            for (EventModelItemBo eventModelBo : modelItemList) {
                bmform.append(eventModelBo.getItemLabel());
                bmform.append("<input name='").append(eventModelBo.getField()).append("' id='").append(eventModelBo.getField()).append("'>");
            }
            bmform.append("</form>");
        }
        mav.addObject("url", sb.toString());
        mav.addObject("bmurl", sbbm.toString());
        mav.addObject("bman", sban.toString());
        mav.addObject("bmform", bmform.toString());
        mav.addObject("id", id);
        return mav;
    }
}
