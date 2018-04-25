package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.CommentPaginationCriteria;
import com.abc.soa.request.system.CommentCriteria;
import com.abc.soa.response.system.bo.IdsBo;
import com.abc.soa.response.system.bo.commentBo.*;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wuhao on 2017-06-13.
 * 评论管理，评论统计
 */
@Controller
@RequestMapping(value = "/cms/comment")
public class CommentController extends BaseController {

    /**
     * 评论管理查询列表页面
     *
     * @param pagerSpec
     * @param request
     * @return
     */
    @RequestMapping(value = "/list.php", method = RequestMethod.GET)
    public ModelAndView list(PagerSpec pagerSpec, CommentPaginationCriteria commentPaginationCriteria, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/comment/list");
        CommentCriteria criteria = new CommentCriteria();
        if (!CommonUtils.nullOrBlank(commentPaginationCriteria.getContentId())) {
            criteria.setContentId(commentPaginationCriteria.getContentId());
        } else {
            criteria.setContentId("");
        }
        if (!CommonUtils.nullOrBlank(commentPaginationCriteria.getIsChecked())) {
            criteria.setIsChecked(commentPaginationCriteria.getIsChecked());
        } else {
            criteria.setIsChecked("");
        }
        if (!CommonUtils.nullOrBlank(commentPaginationCriteria.getIsRecommend())) {
            criteria.setIsRecommend(commentPaginationCriteria.getIsRecommend());
        } else {
            criteria.setIsRecommend("");
        }
        criteria.setPage(commentPaginationCriteria.getPage());
        criteria.setSize(commentPaginationCriteria.getSize());
        System.out.println(JSONArray.fromObject(criteria));
        CommentListBo comments = SoaConnectionFactory.get(request, ConstantsUri.COMMENT, criteria, CommentListBo.class);
        commentPaginationCriteria.setTotalItems((long)comments.getTotal());
        commentPaginationCriteria.setTotalPage((int) Math.ceil((double) commentPaginationCriteria.getTotalItems() / (double) commentPaginationCriteria.getSize()));
        mav.addObject("comments", comments);
        mav.addObject("pagination", commentPaginationCriteria);
        mav.addObject("contentId", criteria.getContentId());
        mav.addObject("isChecked", criteria.getIsChecked());
        mav.addObject("isRecommend", criteria.getIsRecommend());
        return mav;
    }

    /**
     * 评论管理修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/comment/form_edit");
        CommentDetailBo result = SoaConnectionFactory.getRestful(request, ConstantsUri.COMMENT_ONE, null, CommentDetailBo.class, id);
        result.getData().getComment().setCommentId(id);
        mav.addObject("comment", result.getData().getComment());
        mav.addObject("commentExt", result.getData().getCommentExt());
        return mav;
    }

    /**
     * 评论管理保存操作
     *
     * @param request
     * @param commentBo
     * @return
     */
    @RequestMapping(value = "/save.php", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, @RequestBody CommentBo commentBo) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        CommentItemBo result = null;
        CommentSaveBo commentSaveBo = new CommentSaveBo();
        CommentExtBo commentExtBo = new CommentExtBo();
       // commentBo.setIsChecked(0);
        commentSaveBo.setComment(commentBo);
        commentExtBo.setIp(commentBo.getIp());
        commentExtBo.setCommentId(commentBo.getCommentId());
        commentExtBo.setReply(commentBo.getReply());
        commentExtBo.setText(commentBo.getText());
        commentSaveBo.setCommentExt(commentExtBo);
        commentBo.setReplyCount(commentBo.getReplyCount()+1);
        result = SoaConnectionFactory.put(request, ConstantsUri.COMMENT_ONE, commentSaveBo, CommentItemBo.class, commentBo.getCommentId());
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 评论管理删除操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView del(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        result=SoaConnectionFactory.delete(request, ConstantsUri.COMMENT_ONE, null, BaseResponse.class, id);
        //return "redirect:/cms/comment/list.php";
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 评论管理批量删除操作
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchDel.php", method = RequestMethod.POST)
    public ModelAndView batchDel(@RequestParam(value = "ids") String ids, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            IdsBo idsbo=new IdsBo();
            idsbo.setIds(id);
            result=SoaConnectionFactory.post(request,ConstantsUri.COMMENT_DELETELIST,idsbo,BaseResponse.class);
            //COMMENT_DELETELIST
//            if(id.length>0){
//                for(String idcode : id){
//                    if(idcode!=null&&!"".equals(idcode))
//                        result=SoaConnectionFactory.delete(request, ConstantsUri.COMMENT_ONE, null, BaseResponse.class, idcode);
//                }
//            }
        }
        mav.addObject("result", result);
        return mav;
    }


    /**
     * 评论管理批量审核操作
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchReview.php", method = RequestMethod.POST)
    public ModelAndView batchReview(@RequestParam(value = "ids") String ids, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            IdsBo idsBo=new IdsBo();
            idsBo.setIds(id);
            result=SoaConnectionFactory.put(request,ConstantsUri.COMMENT_SPLIST,idsBo,BaseResponse.class);
        }
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 跳转至评论统计页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/toTjPage.php", method = RequestMethod.GET)
    public ModelAndView toTjPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/commentTj/tj");
        return mav;
    }

    /**
     * 评论统计页面获取统计数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTjData.php", method = RequestMethod.GET)
    public ModelAndView getTjData(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        CommentTjDetailBo list = SoaConnectionFactory.get(request, ConstantsUri.COMMENT_TJ, null, CommentTjDetailBo.class);
        String[] tjday = new String[24];
        for (int i = 0; i < 24; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjday().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjday().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjday().get(j).getCnt();
                    tjday[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjday[i] = Arrays.toString(arr);
            }
        }
        String[] tjday0 = new String[24];
        for (int i = 0; i < 24; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjday0().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjday0().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjday0().get(j).getCnt();
                    tjday0[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjday0[i] = Arrays.toString(arr);
            }
        }
        String[] tjday1 = new String[24];
        for (int i = 0; i < 24; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjday1().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjday1().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjday1().get(j).getCnt();
                    tjday1[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjday1[i] = Arrays.toString(arr);
            }
        }
        String[] tjmonth = new String[31];
        for (int i = 0; i < 31; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjmonth().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjmonth().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjmonth().get(j).getCnt();
                    tjmonth[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjmonth[i] = Arrays.toString(arr);
            }
        }


        String[] tjmonth0 = new String[31];
        for (int i = 0; i < 31; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjmonth0().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjmonth0().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjmonth0().get(j).getCnt();
                    tjmonth0[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjmonth0[i] = Arrays.toString(arr);
            }
        }
        String[] tjmonth1 = new String[31];
        for (int i = 0; i < 31; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjmonth1().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjmonth1().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjmonth1().get(j).getCnt();
                    tjmonth1[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjmonth1[i] = Arrays.toString(arr);
            }
        }
        String[] tjyear = new String[12];
        for (int i = 0; i < 12; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjyear().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjyear().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjyear().get(j).getCnt();
                    tjyear[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjyear[i] = Arrays.toString(arr);
            }
        }
        String[] tjyear0 = new String[12];
        for (int i = 0; i < 12; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjyear0().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjyear0().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjyear0().get(j).getCnt();
                    tjyear0[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjyear0[i] = Arrays.toString(arr);
            }
        }
        String[] tjyear1 = new String[12];
        for (int i = 0; i < 12; i++) {
            boolean isContain = false;
            for (int j = 0; j < list.getData().getTjyear1().size(); j++) {
                if (String.valueOf(i).equals(list.getData().getTjyear1().get(j).getTj())) {
                    String[] arr = new String[2];
                    arr[0] = String.valueOf(i);
                    arr[1] = list.getData().getTjyear1().get(j).getCnt();
                    tjyear1[i] = Arrays.toString(arr);
                    isContain = true;
                }
            }
            if (!isContain) {
                String[] arr = new String[2];
                arr[0] = String.valueOf(i);
                arr[1] = "0";
                tjyear1[i] = Arrays.toString(arr);
            }
        }
        mav.addObject("tjday", Arrays.toString(tjday));
        mav.addObject("tjday0", Arrays.toString(tjday0));
        mav.addObject("tjday1", Arrays.toString(tjday1));
        mav.addObject("tjmonth", Arrays.toString(tjmonth));
        mav.addObject("tjmonth0", Arrays.toString(tjmonth0));
        mav.addObject("tjmonth1", Arrays.toString(tjmonth1));
        mav.addObject("tjyear", Arrays.toString(tjyear));
        mav.addObject("tjyear0", Arrays.toString(tjyear0));
        mav.addObject("tjyear1", Arrays.toString(tjyear1));
        mav.addObject("days", list.getData().getDays());
        mav.addObject("weeks", list.getData().getWeeks());
        mav.addObject("months", list.getData().getMonths());
        mav.addObject("years", list.getData().getYears());
        mav.addObject("cnts", list.getData().getCnts());
        return mav;
    }

}
