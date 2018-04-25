package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.DateUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.bangbang.QuestionClassifyListRs;
import com.abc.soa.response.bangbang.QuestionClassifyStatisticsListRs;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/11/7 11:47
 * 话题统计
 */
@Controller
public class TopicStatisticsController {

    /*话题查询*/
    @GetMapping("/bangbang/topicStatistics/list.php")
    public String list(HttpServletRequest request, Model model){
        Map map = new HashMap<>();
        map.put("parentCode","0");
        QuestionClassifyListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFY_LIST, map, QuestionClassifyListRs.class);
        model.addAttribute("classifyList", rs.getDataList());
        QuestionClassifyStatisticsListRs statisticsListRs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTIONCLASSIFY_STATISTICS, map, QuestionClassifyStatisticsListRs.class);
        model.addAttribute("statisticsList", statisticsListRs.getDataList());
        return "bangbang/topicStatistics/list";
    }

    @GetMapping("/bangbang/topicStatistics/ajaxList.php")
    public @ResponseBody BaseResponse ajaxList(HttpServletRequest request,
                                               @RequestParam(value = "parentCode") String parentCode,
                                               @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
                                               @RequestParam(value = "endTime",defaultValue = "") String endTime) throws Exception {
        Map map = new HashMap<>();
        map.put("parentCode", parentCode);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        QuestionClassifyStatisticsListRs statisticsListRs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTIONCLASSIFY_STATISTICS, map, QuestionClassifyStatisticsListRs.class);
        return statisticsListRs;
    }


}
