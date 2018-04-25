package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.dto.cms.questionnaire.QuestionStatus;
import com.abc.dto.cms.questionnaire.SubjectType;
import com.abc.service.ExportUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.questionnaire.AnswerCriteria;
import com.abc.soa.response.cms.questionnaire.*;
import com.abc.soa.response.consumer.ConsumerInfoRs;
import com.abc.soa.response.system.bo.UserBO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author liuqi
 * @Date 2017/6/19 17:58
 * 问卷调查
 */
@Controller
public class QuestionnaireController {
    /*问卷列表 GET*/
    @GetMapping("/cms/questionnaire/list.php")
    public String list(PagerSpec pagerSpec, HttpServletRequest request, Model model){
        HashMap<String,String> map = new HashMap<>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        QuestionnaireBOListRs list = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION, map, QuestionnaireBOListRs.class);
        pagerSpec.setTotalItems(list.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec));
        model.addAttribute("questions", list.getDataList());
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        return "cms/questionnaire/list";
    }
    /*问卷新增 GET*/
    @GetMapping("/cms/questionnaire/add.php")
    public String add(HttpServletRequest request, Model model){
        model.addAttribute("currLink",request.getContextPath()+"/cms/questionnaire/list.php");
        model.addAttribute("subjectTypes", SubjectType.values());
        //通用皮肤图片
        ImageListRs themes = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_THEMES, null, ImageListRs.class);
        model.addAttribute("themeImgs", themes.getDataList());
        return "cms/questionnaire/form";
    }
    /*问卷编辑 GET*/
    @GetMapping("/cms/questionnaire/edit/{id}.php")
    public String edit(HttpServletRequest request,@RequestParam(value = "currLink", defaultValue = "")String currLink,@PathVariable String id, Model model){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/cms/questionnaire/list.php" : currLink;
        model.addAttribute("currLink",currLink);

        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, id);
        QuestionnaireBO question = questionRs.getData();
        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, question.getId());
        List<SubjectsBO> subjects = subjectListRs.getDataList();
        LinkedHashMap<String,List<SubjectsBO>> linkedHashMap = getPageSubjectsMap(subjects);
        model.addAttribute("question", question);
        model.addAttribute("subjectMap", linkedHashMap);
        model.addAttribute("subjectTypes", SubjectType.values());
        //通用皮肤图片
        ImageListRs themes = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_THEMES, null, ImageListRs.class);
        model.addAttribute("themeImgs", themes.getDataList());

        HashMap map = new HashMap();
        map.put("questionId",id);
        AccessLogtjListBoRs answerLogtjListBoRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_STATISTICS_LIST, map, AccessLogtjListBoRs.class);
        if(answerLogtjListBoRs.getData()!=null && answerLogtjListBoRs.getData().getLlcnts() >0){
            model.addAttribute("isSubmitPapers", true);
        }else{
            model.addAttribute("isSubmitPapers", false);
        }
        return "cms/questionnaire/form";
    }
    /*问卷新增 POST*/
    @PostMapping("/cms/questionnaire/edit.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody QuestionnaireBO questionnaireBO, UserBO user){
        questionnaireBO.setUpdateUser(user.getUsername());
        if(CommonUtils.nullOrBlank(questionnaireBO.getId())){
            if(questionnaireBO.getQuestionnaireParam() == null){
                QuestionnaireParam param = new QuestionnaireParam(1, 0, 0, 0, 0, 0, 0, 0, 0);
                questionnaireBO.setQuestionnaireParam(param);
            }
            questionnaireBO.setCreateUser(user.getUsername());
            return SoaConnectionFactory.post(request, ConstantsUri.CMS_QUESTION, questionnaireBO, QuestionnaireBORs.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.CMS_QUESTION_ID, questionnaireBO, QuestionnaireBORs.class, questionnaireBO.getId());
        }
    }

    /* 问卷皮肤修改 */
    @PostMapping("/cms/questionnaire/ajaxSkin/{questionId}.php")
    public @ResponseBody BaseResponse ajaxSkin(HttpServletRequest request, @PathVariable("questionId") String questionId, @RequestParam("imageUrl") String imageUrl){
        return SoaConnectionFactory.put(request, ConstantsUri.CMS_QUESTION_SKIN, imageUrl, BaseResponse.class, questionId);
    }

    /* 问卷回收状态修改 */
    @PostMapping("/cms/questionnaire/recycle/{questionId}.php")
    public @ResponseBody BaseResponse recycle(HttpServletRequest request, @PathVariable("questionId") String questionId, @RequestParam("status") String status){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO questionnaire = questionRs.getData();
        if(questionnaire == null){
            return new BaseResponse(false,"系统异常");
        }else{
            QuestionnaireParam qParam = questionnaire.getQuestionnaireParam();
            if(qParam != null && qParam.getRecoveryEndTime() !=null && new Date().after(qParam.getRecoveryEndTime())){
                return new BaseResponse(false,"设置结束时间小于当前时间的问卷不能发布");
            }
        }
        if(QuestionStatus.PUBLISH.getCode().equals(status)){
            SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, questionId);
            List<SubjectsBO> subjects = subjectListRs.getDataList();
            if(subjects == null || subjects.isEmpty()){
                return new BaseResponse(false,"没有题目的问卷不能发布");
            }
        }
        return SoaConnectionFactory.put(request, ConstantsUri.CMS_QUESTION_STATUS, status, QuestionnaireBORs.class, questionId);
    }
    /*问卷 删去 */
    @DeleteMapping("/cms/questionnaire/del/{questionId}.php")
    public @ResponseBody BaseResponse del(HttpServletRequest request, @PathVariable("questionId") String questionId, Model model){
        HashMap map = new HashMap();
        map.put("questionId",questionId);
        AccessLogtjListBoRs answerLogtjListBoRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_STATISTICS_LIST, map, AccessLogtjListBoRs.class);
        if(answerLogtjListBoRs.getData()!=null && answerLogtjListBoRs.getData().getLlcnts() >0){
            return new BaseResponse(false, "有回答记录的问卷不能删除");
        }else{
            return SoaConnectionFactory.delete(request, ConstantsUri.CMS_QUESTION_ID, null, BaseResponse.class, questionId);
        }
    }

    /*问卷 复制*/
    @PostMapping("/cms/questionnaire/copy/{questionId}.php")
    public @ResponseBody BaseResponse copyQuestionnaire(HttpServletRequest request, UserBO user, @PathVariable("questionId") String questionId){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO oldQuestion = questionRs.getData();
        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, oldQuestion.getId());
        oldQuestion.setId(StringUtils.EMPTY);
        oldQuestion.setTitle(oldQuestion.getTitle().concat("_副本"));
        oldQuestion.setUpdateUser(user.getUsername());
        List<SubjectsBO> oldSubjets = subjectListRs.getDataList();
        for (SubjectsBO s: oldSubjets){
            s.setId(StringUtils.EMPTY);s.setQuestionId(StringUtils.EMPTY);
        }
        oldQuestion.setSubjectsBOList(oldSubjets);
        return SoaConnectionFactory.post(request, ConstantsUri.CMS_QUESTION_COPY, oldQuestion, QuestionnaireBORs.class);
    }

    /*问卷 预览 */
    @GetMapping("/cms/questionnaire/preview/{questionId}.php")
    public String preview(@PathVariable("questionId") String questionId, Model model){
        model.addAttribute("questionId", questionId);
        model.addAttribute("SNSURL", BaseObject.getConfig("snsdomain"));
        model.addAttribute("WEIXIN_URL", BaseObject.getConfig("WEIXIN_URL"));
        return "cms/questionnaire/preview";
    }

    /*问卷 预览 */
    @GetMapping("/cms/questionnaire/preview/pc/{questionId}.php")
    public String previewPc(HttpServletRequest request, Model model, @PathVariable("questionId") String questionId){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO question = questionRs.getData();
        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, question.getId());
        List<SubjectsBO> subjects = subjectListRs.getDataList();
        LinkedHashMap<String,List<SubjectsBO>> linkedHashMap = getPageSubjectsMap(subjects);
        model.addAttribute("question", question);
        model.addAttribute("pageSubjectMap", linkedHashMap);
        model.addAttribute("startTime", new Date());
        model.addAttribute("SNSURL", BaseObject.getConfig("snsdomain"));
        return "cms/questionnaire/preview_pc";
    }

    /*问卷 提交*/
    @PostMapping("/cms/questionnaire/preview/submit.php")
    public @ResponseBody AnswerLogBORs submitAnswer(HttpServletRequest request,@RequestBody AnswerLogBO answerLogBO){
        answerLogBO.setIpAddress(CommonUtils.getRequestIpAddrs(request));
        AnswerLogBORs rs = SoaConnectionFactory.put(request, ConstantsUri.CMS_QUESTION_ANSWER_SUBMIT, answerLogBO, AnswerLogBORs.class);
        return rs;
    }


    /*删去page下所有题目 */
    @DeleteMapping("/cms/questionnaire/subjects/{pages}/{questionId}.php")
    public @ResponseBody BaseResponse delPage(HttpServletRequest request, @PathVariable("pages") String pages, @PathVariable("questionId") String questionId){
        return SoaConnectionFactory.delete(request, ConstantsUri.CMS_SUBJECT_DEL_PAGE, null, BaseResponse.class, pages, questionId);
    }

    /*新增题目 POST*/
    @PostMapping("/cms/questionnaire/subjects/edit.php")
    public @ResponseBody BaseResponse submitSubjects(HttpServletRequest request, @RequestBody SubjectsBO subjectsBO){
        if(CommonUtils.nullOrBlank(subjectsBO.getId())){
            return SoaConnectionFactory.post(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, subjectsBO, SubjectsBORs.class, subjectsBO.getQuestionId());
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.CMS_SUBJECT_ID_QUESTIONID, subjectsBO, SubjectsBORs.class, subjectsBO.getId(), subjectsBO.getQuestionId());
        }
    }

    /* 复制题目 */
    @PostMapping("/cms/questionnaire/subject/copy/{subjectId}.php")
    public @ResponseBody BaseResponse copySubject(HttpServletRequest request,@PathVariable("subjectId") String subjectId, @RequestBody List<SubjectsBO> updateSubjects) {
        return SoaConnectionFactory.post(request, ConstantsUri.CMS_SUBJECT_COPY, updateSubjects, SubjectsBORs.class, subjectId);
    }

    /* 删去题目 */
    @DeleteMapping("/cms/questionnaire/subjects/del/{id}/{questionId}.php")
    public @ResponseBody BaseResponse submitSubjects(HttpServletRequest request,@PathVariable("questionId") String questionId, @PathVariable("id") String id){
        return SoaConnectionFactory.delete(request, ConstantsUri.CMS_SUBJECT_ID_QUESTIONID, null, BaseResponse.class, id, questionId);
    }

    /* 设置保存 */
    @PostMapping("/cms/questionnaire/setup/{questionId}.php")
    public @ResponseBody BaseResponse setup(HttpServletRequest request, @PathVariable("questionId") String questionId, @RequestBody QuestionnaireParam questionnaireParam){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO question = questionRs.getData();
        questionnaireParam.setQuestionId(questionId);
        question.setQuestionnaireParam(questionnaireParam);
        return SoaConnectionFactory.put(request, ConstantsUri.CMS_QUESTION_ID, question, QuestionnaireBORs.class, questionId);
    }

    /* 统计分析 */
    @GetMapping("/cms/questionnaire/statistics/{questionId}.php")
    public String statistics(HttpServletRequest request, @PathVariable("questionId") String questionId,
                             Model model,PagerSpec pagerSpec){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        model.addAttribute("question", questionRs.getData());
        //1:查询回收率，4：问卷回收量（日期-数量统计图），5：地域分布，
        QuestionnaireBORs recoveryRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_RECOVERY_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO questionnaireBO = recoveryRs.getData();
        model.addAttribute("recovery",(questionnaireBO==null || questionnaireBO.getAccessNum()==null) ? 0: questionnaireBO.getAccessNum());
        //2：平均完成时间，
        AnswerLogBORs answerLogBORs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_AVG_ANSWER_TIME, null, AnswerLogBORs.class, questionId);
        AnswerLogBO answerLogBO = answerLogBORs.getData();
        long avgTime = (answerLogBO == null ? 0: answerLogBORs.getData().getAvgTimeLong());
        if(avgTime<0){
            avgTime = 0;
        }
        long avgSecond = avgTime;
        model.addAttribute("avgMinute", avgSecond/60);
        model.addAttribute("avgSecond", (avgSecond)%60);
        HashMap map = new HashMap();
        map.put("questionId",questionId);
        //3：问卷流量量（日期-数量统计图），
        AccessLogtjListBoRs accessLogtjListBoRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ACCESS_STATISTICS_LIST, map, AccessLogtjListBoRs.class);
        model.addAttribute("accessStatistics", accessLogtjListBoRs.getData());
        //4：问卷回收量（日期-数量统计图）
        AccessLogtjListBoRs answerLogtjListBoRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_STATISTICS_LIST, map, AccessLogtjListBoRs.class);
        model.addAttribute("answerStatistics", answerLogtjListBoRs.getData());

        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, questionId);
        List<SubjectsBO> subjects = subjectListRs.getDataList();
        sortSubject(subjects);
        model.addAttribute("subjects", subjects);
        //6，初始化问卷回答记录
        Map map1 = new HashMap<String,String>();
        map1.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map1.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        AnswerLogBOListRs answerLogBOListRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_LIST, map1, AnswerLogBOListRs.class, questionId);
        List<AnswerLogBO> answerRecords =  answerLogBOListRs.getDataList();
        model.addAttribute("answerRecords", answerRecords);
        String link = new StringBuilder(request.getContextPath()).append("/cms/questionnaire/statistics/answerList/").append(questionId).append(".php?page=[:page]").toString();
        pagerSpec.setTotalItems(answerLogBOListRs.getTotal());
        pagerSpec.setLink(link);
        model.addAttribute("pageHtml", PagerUtil.pager(PagerUtil.calculatePagerSpec(pagerSpec), true));

        LinkedHashMap<String,List<SubjectsBO>> linkedHashMap = getPageSubjectsMap(subjects);
        model.addAttribute("pageSubjectMap", linkedHashMap);

        HashMap subjectsdtxxtjReq = new HashMap();
        subjectsdtxxtjReq.put("questionId", questionId);
        SubjectsdtxxtjBoListRs subjectsdtxxtjBoListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_QUESTION_STATISTICS_LIST, subjectsdtxxtjReq, SubjectsdtxxtjBoListRs.class);
        model.addAttribute("subjectsdtxxtjBos", subjectsdtxxtjBoListRs.getDataList());
        return "cms/questionnaire/statistics";
    }

    /* ajax问卷浏览量统计分析 */
    @GetMapping("/cms/questionnaire/ajaxAccessStatistics/{questionId}.php")
    public @ResponseBody BaseResponse ajaxAccessStatistics(HttpServletRequest request,
            @PathVariable("questionId") String questionId,
            @RequestParam(value = "startTime", defaultValue = "") String startTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime){
        HashMap map = new HashMap();
        map.put("questionId",questionId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        AccessLogtjListBoRs accessLogtjListBoRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ACCESS_STATISTICS_LIST, map, AccessLogtjListBoRs.class);
        return accessLogtjListBoRs;
    }
    /* ajax问卷浏览量统计分析 */
    @GetMapping("/cms/questionnaire/ajaxAnswerStatistics/{questionId}.php")
    public @ResponseBody BaseResponse ajaxAnswerStatistics(HttpServletRequest request, @PathVariable("questionId") String questionId,
                                             @RequestParam(value = "startTime", defaultValue = "") String startTime, @RequestParam(value = "endTime", defaultValue = "") String endTime){
        HashMap map = new HashMap();
        map.put("questionId",questionId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        AccessLogtjListBoRs accessLogtjListBoRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_STATISTICS_LIST, map, AccessLogtjListBoRs.class);
        return accessLogtjListBoRs;
    }

    /*统计分析 回答记录*/
    @GetMapping("/cms/questionnaire/statistics/answerList/{questionId}.php")
    public @ResponseBody HashMap<String, Object> answerList(HttpServletRequest request,
        @PathVariable("questionId") String questionId,
        @RequestParam(value = "startTime", defaultValue = "") String startTime,
        @RequestParam(value = "endTime", defaultValue = "") String endTime,
        PagerSpec pagerSpec) throws ParseException {
        AnswerCriteria criteria = new AnswerCriteria().withPagerSpec(pagerSpec);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!StringUtils.isEmpty(startTime)){
            criteria.withStartDate(startTime);
        }
        if(!StringUtils.isEmpty(endTime)){
            criteria.withEndDate(endTime);
        }
        HashMap rs = new HashMap();

        AnswerLogBOListRs answerLogBOListRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_LIST, criteria, AnswerLogBOListRs.class, questionId);
        pagerSpec.setTotalItems(answerLogBOListRs.getTotal());
        rs.put("pageHtml",PagerUtil.pager(PagerUtil.calculatePagerSpec(pagerSpec), true));

        rs.put("success",answerLogBOListRs.isSuccess());
        rs.put("message",answerLogBOListRs.getMessage());
        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, questionId);
        List<SubjectsBO> subjects = subjectListRs.getDataList();
        sortSubject(subjects);

        List<AnswerLogBO> answerLogs = answerLogBOListRs.getDataList();
        if(answerLogs!=null && !answerLogs.isEmpty()){
            int index = pagerSpec.getOffset();
            StringBuilder sb = new StringBuilder();
            for (AnswerLogBO answerLog: answerLogs){
                sb.append("<tr>")
                        .append("<td><a href=\"").append(request.getContextPath())
                        .append("/cms/questionnaire/statistics/answer/").append(questionId).append("/").append(answerLog.getId()).append(".php")
                        .append("\" target=\"_blank\"><span class=\"recycle_eye\"></span></a></td>")
                .append("<td>").append(++index).append("</td><td>");
                if(answerLog.getStartTime() !=null){
                    sb.append(fmt.format(answerLog.getStartTime()));
                }
                sb.append("</td><td>");
                if(answerLog.getEndTime() !=null){
                    sb.append(fmt.format(answerLog.getEndTime()));
                }
                sb.append("</td>");
                List<Answer> answers = answerLog.getAnswerList();
                for (SubjectsBO subject: subjects){
                    sb.append("<td>");
                    if(answers!=null && !answers.isEmpty()){
                        for (Answer answer: answers){
                            if(answer.getSubjectsId().equals(subject.getId())){
                                String content = answer.getContent()==null ? StringUtils.EMPTY : answer.getContent();
                                sb.append(content).append(" ");
                            }
                        }
                    }
                    sb.append("</td>");
                }
                sb.append("</tr>");
            }
            rs.put("bodyHtml",sb.toString());
        }

        return rs;
    }

    /* 统计分析 回答记录 批量删除 */
    @PostMapping(value = "/cms/questionnaire/statistics/delAnswer/{questionId}.php")
    public @ResponseBody BaseResponse itemDelList(@PathVariable("questionId") String questionId, @RequestParam("ids") String ids, HttpServletRequest request) throws IOException {
        AnswerLogBO answerLogBO = new AnswerLogBO();
        answerLogBO.setId(ids);
        return SoaConnectionFactory.delete(request, ConstantsUri.CMS_QUESTION_ANSWER_DEL, answerLogBO, BaseResponse.class, questionId);
    }

    /*回答记录 单个查询  */
    @GetMapping(value = "/cms/questionnaire/statistics/answer/{questionId}/{answerId}.php")
    public String viewAnswer(HttpServletRequest request, @PathVariable("answerId") String answerId,@PathVariable("questionId") String questionId, Model model){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO question = questionRs.getData();
        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, question.getId());
        List<SubjectsBO> subjects = subjectListRs.getDataList();
        LinkedHashMap<String,List<SubjectsBO>> linkedHashMap = getPageSubjectsMap(subjects);
        model.addAttribute("question", question);
        model.addAttribute("pageSubjectMap", linkedHashMap);
        AnswerLogBORs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ANSWER, null, AnswerLogBORs.class, answerId);
        AnswerLogBO answerLogBO = rs.getData();
        model.addAttribute("answer",answerLogBO);
        if(!StringUtils.isEmpty(answerLogBO.getUserId())){
            ConsumerInfoRs res = SoaConnectionFactory.getRestful(request, ConstantsUri.CONSUMER_INFO, null , ConsumerInfoRs.class, answerLogBO.getUserId());
            model.addAttribute("user",res.getUser());
        }
        return "cms/questionnaire/answer_view";
    }

    /* 试卷回答记录导出 */
    @GetMapping(value = "/cms/questionnaire/statistics/answer/export/{questionId}.php")
    public void exportAnswerByQuestionId(@PathVariable("questionId") String questionId,
         @RequestParam(value = "startTime", defaultValue = "") String startTime,
         @RequestParam(value = "endTime", defaultValue = "") String endTime,
        HttpServletRequest request, HttpServletResponse response){
        QuestionnaireBORs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_QUESTION_ID, null, QuestionnaireBORs.class, questionId);
        QuestionnaireBO question = questionRs.getData();

        SubjectsBOListRs subjectListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SUBJECT_QUESTIONID, null, SubjectsBOListRs.class, questionId);
        List<SubjectsBO> subjects = subjectListRs.getDataList();
        sortSubject(subjects);
        Map<String, Object> cvsmap = null;
        StringBuilder titleSb = new StringBuilder("用户微信ID,开始答题时间,结束答题时间");
        StringBuilder mapKey = new StringBuilder("weixinId,st,et");
        int subjectIndex = 1;
        for (SubjectsBO subject: subjects){
            titleSb.append(",").append(subjectIndex++).append(".").append(subject.getTitle().replaceAll("<([^>]*)>",""));
            mapKey.append(",").append(subject.getId());
        }
        AnswerCriteria criteria = new AnswerCriteria();
        PagerSpec pagerSpec = new PagerSpec();
        pagerSpec.setCurrentPage(0);pagerSpec.setPerPageNum(0);
        criteria.withPagerSpec(pagerSpec);
        if(!StringUtils.isEmpty(startTime)){
            criteria.withStartDate(startTime);
        }
        if(!StringUtils.isEmpty(endTime)){
            criteria.withEndDate(endTime);
        }
        AnswerLogBOListRs answerLogBOListRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_ANSWER_LIST, criteria, AnswerLogBOListRs.class, questionId);
        List<AnswerLogBO> answerLogBOs = answerLogBOListRs.getDataList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> dataMap = null;
        List<Map<String, String>> dataList = new ArrayList<>();
        for (AnswerLogBO answerLogBO: answerLogBOs){
            dataMap = new HashMap<String, String>();
            dataMap.put("weixinId",answerLogBO.getWeixinId());
            dataMap.put("st",sdf.format(answerLogBO.getStartTime()));
            dataMap.put("et",sdf.format(answerLogBO.getEndTime()));
            List<Answer> answers = answerLogBO.getAnswerList();//回答的记录
            for (Answer answer: answers){
                if(dataMap.get(answer.getSubjectsId()) == null){
                    dataMap.put(answer.getSubjectsId(), CommonUtils.nullToString(answer.getContent()));
                }else{
                    dataMap.put(answer.getSubjectsId(), (String)dataMap.get(answer.getSubjectsId())+"，"+CommonUtils.nullToString(answer.getContent()));
                }
            }
            dataList.add(dataMap);
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(question.getTitle()+"_答题记录_", response);
            ExportUtil.doExport(dataList, titleSb.toString(), mapKey.toString(), os);
        } catch (Exception e) {
        }
    }

    /* 题目回答记录导出 */
    @GetMapping(value = "/cms/questionnaire/statistics/subjectAnswer/export/{subjectId}.php")
    public void exportAnswerBySubjectId(@PathVariable("subjectId") String subjectId, HttpServletRequest request, HttpServletResponse response){
        SubjectsBORs subjectsBORs = SoaConnectionFactory.get(request, ConstantsUri.CMS_SUBJECT_ID, null, SubjectsBORs.class, subjectId);
        SubjectsBO subject = subjectsBORs.getData();
        AnswertjBOListRs answertjBOListRs = SoaConnectionFactory.get(request, ConstantsUri.CMS_QUESTION_SUBJECT_ANSWER, null, AnswertjBOListRs.class, subjectId);
        Map<String, Object> cvsmap = null;
        StringBuilder titleSb = new StringBuilder("开始答题时间,结束答题时间,").append(subject.getTitle().replaceAll("<([^>]*)>",""));
        StringBuilder mapKey = new StringBuilder("st,et,").append(subject.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> dataMap = null;
        List<Map<String, String>> dataList = new ArrayList<>();
        List<AnswertjBO> answertjBOs = answertjBOListRs.getDataList();
        if(answertjBOs != null){
            for (AnswertjBO answertjBO: answertjBOs){
                if(answertjBO.getStartTime() != null && answertjBO.getEndTime() != null){
                    dataMap = new HashMap<String, String>();
                    dataMap.put("st",sdf.format(answertjBO.getStartTime()));
                    dataMap.put("et",sdf.format(answertjBO.getEndTime()));
                    dataMap.put(subject.getId(),answertjBO.getContent());
                    dataList.add(dataMap);
                }
            }
        }

        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties("答题记录_", response);
            ExportUtil.doExport(dataList, titleSb.toString(), mapKey.toString(), os);
        } catch (Exception e) {
        }
    }

    private void sortSubject(List<SubjectsBO> list){
        Collections.sort(list, new Comparator<SubjectsBO>() {
            public int compare(SubjectsBO o1, SubjectsBO o2) {
                if(o1.getPages() > o2.getPages()){
                    return 1;
                }else if(o1.getPages() == o2.getPages()){
                    if (o1.getNumber() > o2.getNumber()) {
                        return 1;
                    }else if(o1.getNumber() == o2.getNumber()) {
                        return 0;
                    }else{
                        return -1;
                    }
                }else{
                    return -1;
                }
            }
        });
        for (SubjectsBO subjectsBO: list){
            if("1".equals(subjectsBO.getOptionType()) || "2".equals(subjectsBO.getOptionType())){
                sortOption(subjectsBO.getOptionList());
            }
        }
    }
    private void sortOption(List<Option> list){
        Collections.sort(list, new Comparator<Option>() {
            public int compare(Option o1, Option o2) {
                if(o1.getSequence() > o2.getSequence()){
                    return 1;
                }else if(o1.getSequence() == o2.getSequence()){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
    }



    private LinkedHashMap<String,List<SubjectsBO>> getPageSubjectsMap(List<SubjectsBO> subjects){
        if(subjects == null){
            return null;
        }
        Map<String, List<SubjectsBO>> map = new TreeMap<String, List<SubjectsBO>>(
                new Comparator<String>() {
                    public int compare(String k1, String k2) {
                        return Integer.valueOf(k1).compareTo(Integer.valueOf(k2));
                    }
                });
        //把题目通过page分开
        for (SubjectsBO subject: subjects){
            if(map.containsKey(subject.getPages().toString())){
                List<SubjectsBO> val = (List<SubjectsBO>) map.get(subject.getPages().toString());
                val.add(subject);
            }else{
                List<SubjectsBO> val = new ArrayList<SubjectsBO>();
                val.add(subject);
                map.put(subject.getPages().toString(), val);
            }
        }
        for (String k : map.keySet()) {
            sortSubject(map.get(k));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap<String,List<SubjectsBO>>();
        linkedHashMap.putAll(map);
        return linkedHashMap;
    }
}
