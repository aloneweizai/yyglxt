package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.NoticeRq;
import com.abc.soa.response.cszj.NoticeRs;
import com.abc.soa.response.cszj.bo.NoticeBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/notice")
public class NoticeController {

    protected static Logger _log = LoggerFactory.getLogger(NoticeController.class);
    /**
     * 通知公告列表接口
     *
     * @param noticeRq
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/list.php")
    public String ShippingList(NoticeRq noticeRq, HttpServletRequest request, Model model,HttpSession session) {
        NoticeRs noticeRs = SoaConnectionFactory.get(request, ConstantsUri.NOTICE_LIST, noticeRq, NoticeRs.class);
        model.addAttribute("noticeList", noticeRs.getDataList());
        noticeRq.setTotalItems(noticeRs.getTotal());
        noticeRq.setTotalPage((int) Math.ceil((double) noticeRq.getTotalItems() / (double) noticeRq.getSize()));
        model.addAttribute("pagination", noticeRq);
        model.addAttribute("BaseRq",noticeRq);
        model.addAttribute("referer", request.getHeader("Referer"));
        session.setAttribute("NoticeRq", noticeRq);
        return "cszj/notice/list";
    }

    /**
     * 跳转到通知公告预览页面
     *
     * @param noticeId
     * @param request
     * @param
     * @return
     */
    @GetMapping("/addlook.php")
    public String addlook(HttpServletRequest request, @RequestParam String noticeId){
    	 NoticeRs noticeRs = SoaConnectionFactory.getRestful(request, ConstantsUri.NOTICE_INFO, null, NoticeRs.class, noticeId);
    	 request.setAttribute("noticeDto", noticeRs.getData());
         return "cszj/notice/addlook";
    }
    
    /**
     * 跳转到通知公告新增，修改页面
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/addPage.php")
    public String addPage(HttpServletRequest request, Model model, @RequestParam(value = "noticeId", required = false) String noticeId) {
        if (!StringUtils.isEmpty(noticeId)) {
            NoticeRs noticeRs = SoaConnectionFactory.getRestful(request, ConstantsUri.NOTICE_INFO, null, NoticeRs.class, noticeId);
            model.addAttribute("noticeDto", noticeRs.getData());
        }
        NoticeRq rq = (NoticeRq) request.getSession().getAttribute("NoticeRq");
        model.addAttribute("NoticeRq", rq);
        model.addAttribute("referer", request.getHeader("Referer"));
        return "cszj/notice/form_edit";
    }

    /**
     * 通知公告新增，修改保存
     *
     * @param request
     * @return
     */
    @PostMapping("/save.php")
    public
    @ResponseBody
    BaseResponse save(@RequestBody NoticeBO notice, HttpServletRequest request) {
        BaseResponse rs = null;
        if (CommonUtils.nullOrBlank(notice.getId())) {
            rs = SoaConnectionFactory.post(request, ConstantsUri.NOTICE_ADD, notice, NoticeRs.class);
        } else {
            rs = SoaConnectionFactory.put(request, ConstantsUri.NOTICE_EDIT, notice, NoticeRs.class, notice.getId());
        }
        return rs;
    }


    @PostMapping("/noticeSave.php")
    @ResponseBody
    public ModelAndView insertOrUpdate(HttpServletRequest request,NoticeBO notice){
        BaseResponse rs = null;
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        if (CommonUtils.nullOrBlank(notice.getId())) {
            rs = SoaConnectionFactory.post(request, ConstantsUri.NOTICE_ADD, notice, NoticeRs.class);
        } else {
            rs = SoaConnectionFactory.put(request, ConstantsUri.NOTICE_EDIT, notice, NoticeRs.class, notice.getId());
        }
        mav.addObject("result", rs);
        return mav;
    }
    /**
     * 通知公告删除
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/delete.php")
    public
    @ResponseBody
    BaseResponse delete(String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.NOTICE_DEL, null, BaseResponse.class, id);
    }

    /**
     * 通知公告撤销
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/noticecx.php")
    public
    @ResponseBody
    BaseResponse noticecx(NoticeBO notice, HttpServletRequest request, Model model) {
        notice.setStatus("0");
        return SoaConnectionFactory.put(request, ConstantsUri.NOTICE_EDIT, notice, BaseResponse.class, notice.getId());
    }

}
