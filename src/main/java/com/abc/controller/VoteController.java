package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.FileOperateUtil;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.vote.*;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
import com.abc.soa.response.system.bo.DictBO;
import com.abc.soa.response.system.bo.DictListBO;
import freemarker.template.SimpleDate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
public class VoteController {

    protected static Logger _log = LoggerFactory.getLogger(VoteController.class);

    @GetMapping("/cms/vote/list.php")
    public String listPage(HttpServletRequest request, Model model,BasePaginationCriteria basePaginationCriteria){

        VoteListDto voteList = SoaConnectionFactory.get(request, ConstantsUri.CMS_VOTE, basePaginationCriteria, VoteListDto.class);

        basePaginationCriteria.setTotalItems(voteList.getTotal());
        basePaginationCriteria.setTotalPage((int) Math.ceil((double) basePaginationCriteria.getTotalItems() / (double) basePaginationCriteria.getSize()));

        model.addAttribute("voteList",voteList.getDataList());
        model.addAttribute("pagination",basePaginationCriteria);
        return "cms/vote/list";
    }

    @GetMapping("/cms/vote/add.php")
    public String addPage(HttpServletRequest request,Model model,String voteId){

        VoteDto voteDto = null;
        List<VoteSubjectItemDto> subjectItemList = null;
        List<VoteCollectionDto> collectionList = new ArrayList<>();
        String subjectId = null;
        String voteType = "normal";
        if(!StringUtils.isEmpty(voteId)){
            VoteQueryDto voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,voteId);

            if(voteQueryDto.getData().getSubjectList()!=null && voteQueryDto.getData().getSubjectList().size()>0 && voteQueryDto.getData().getSubjectList().get(0).getItemList()!=null){
                if(voteQueryDto.isSuccess() && voteQueryDto.getData()!=null){
                    voteDto = voteQueryDto.getData();
                    subjectId = voteDto.getSubjectList().get(0).getId();
                    subjectItemList = voteDto.getSubjectList().get(0).getItemList();
                }else{
                    voteDto = new VoteDto();
                }
            }else{
                voteDto = new VoteDto();
            }
        }else{
            voteDto = new VoteDto();
        }
        if(subjectItemList==null){
            subjectItemList = new ArrayList<>();
        }

        for(VoteSubjectItemDto subjectItem : subjectItemList){
            if("degree".equals(subjectItem.getType())){
                voteType = "degree";
                break;
            }
        }

        DictListBO dictList= SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_vote_collect");
        if(voteDto!=null && voteDto.getAdditionList()!=null) {
            for (VoteCollectionDto tmpCollection : voteDto.getAdditionList()) {
                for (DictBO dictBO : dictList.getDataList()) {
                    if (dictBO.getFieldValue().equals(tmpCollection.getDictId())) {
                        tmpCollection.setContent(dictBO.getFieldKey());
                        collectionList.add(tmpCollection);
                    }
                }
            }
        }

        model.addAttribute("hostUrl", SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context"));
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("subjectItemList",subjectItemList);
        model.addAttribute("collectionList",collectionList);
        model.addAttribute("voteDto",voteDto);
        model.addAttribute("voteType",voteType);
        model.addAttribute("cmsVoteCollectionList",dictList.getDataList());
        return "cms/vote/add";
    }

    @GetMapping("/cms/vote/publish.php")
    public String publishPage(HttpServletRequest request,Model model,String voteId){
        if(StringUtils.isEmpty(voteId)){
            return "404";
        }

        VoteQueryDto voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,voteId);

        if(!voteQueryDto.isSuccess() || voteQueryDto.getData()==null){
            return "404";
        }

        model.addAttribute("hostUrl", SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context"));
        model.addAttribute("voteId",voteId);
        model.addAttribute("voteDto",voteQueryDto.getData());
        return "cms/vote/publish";
    }

    @GetMapping("/cms/vote/generate.php")
    public String generate(HttpServletRequest request,Model model,String voteId){
        if(StringUtils.isEmpty(voteId)){
            return "404";
        }

        VoteQueryDto voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,voteId);

        if(!voteQueryDto.isSuccess() || voteQueryDto.getData()==null){
            return "404";
        }

        VoteDto voteDto = voteQueryDto.getData();

        String snsUrl = SpringCtxHolder.getProperty("snsdomain");

        model.addAttribute("voteId",voteId);
        model.addAttribute("url",snsUrl + "/pub/vote/" + voteDto.getId() + ".php");
        return "cms/vote/generate";
    }

    /**
     * 新增、更新基本设置
     * @param request
     * @param model
     * @param baseVoteInfo
     * @param voteCollectStrList
     * @param voteItemStrList
     * @return
     */
    @PostMapping("/cms/vote/inserOrUpdate.php")
    @ResponseBody
    public VoteQueryDto inserOrUpdate(HttpServletRequest request, Model model, VoteDto baseVoteInfo,
                                @RequestParam(value = "voteCollectListStr") String voteCollectStrList,
                                @RequestParam(value = "voteItemListStr") String voteItemStrList) {

        VoteQueryDto result = null;
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        VoteCollectionDto tmpVoteCollect = null;
        VoteSubjectItemDto tmpVoteSubjectItem = null;
        List<VoteCollectionDto> voteCollectList = new ArrayList<>();
        List<VoteSubjectItemDto> voteSubjectItemList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        jsonArray = new JSONArray(voteCollectStrList);
        for(int i=0;i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            tmpVoteCollect = new VoteCollectionDto();
            tmpVoteCollect.setId(jsonObject.getString("id"));
            tmpVoteCollect.setVoteId(jsonObject.getString("voteId"));
            tmpVoteCollect.setContent(jsonObject.getString("content"));
            tmpVoteCollect.setDictId(jsonObject.getString("dictId"));
            tmpVoteCollect.setRequired(jsonObject.getBoolean("required"));
            voteCollectList.add(tmpVoteCollect);
        }

        jsonArray = new JSONArray(voteItemStrList);
        for(int i=0;i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            tmpVoteSubjectItem = new VoteSubjectItemDto();
            tmpVoteSubjectItem.setId(jsonObject.getString("id"));
            tmpVoteSubjectItem.setSubjectId(jsonObject.getString("subjectId"));
            tmpVoteSubjectItem.setDetail(jsonObject.getString("detail"));
            tmpVoteSubjectItem.setImage(jsonObject.getString("image"));
            tmpVoteSubjectItem.setItem(jsonObject.getString("item"));
            tmpVoteSubjectItem.setType(jsonObject.getString("type"));
            tmpVoteSubjectItem.setStatus(jsonObject.getString("status"));
            tmpVoteSubjectItem.setSort(i);
            voteSubjectItemList.add(tmpVoteSubjectItem);
        }

        baseVoteInfo.setAdditionList(voteCollectList);
        List<VoteSubjectDto> voteSubjectList = new ArrayList<>();
        VoteSubjectDto voteSubjectDto = new VoteSubjectDto();
        voteSubjectDto.setCreateTime(sdf.format(new Date()));
        voteSubjectDto.setItemList(voteSubjectItemList);
        voteSubjectList.add(voteSubjectDto);
        baseVoteInfo.setSubjectList(voteSubjectList);

        baseVoteInfo.getSubjectList().get(0).setSubject(baseVoteInfo.getName());
        baseVoteInfo.setStartTime(sdf.format(new Date()));


        VoteQueryDto voteQueryDto = null;
        VoteDto queryDto = null;
        if(!StringUtils.isEmpty(baseVoteInfo.getId())){
            voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,baseVoteInfo.getId());
            queryDto = voteQueryDto.getData();
            queryDto.setName(baseVoteInfo.getName());
            queryDto.setChannel(baseVoteInfo.getChannel());
            queryDto.setQuickVote(baseVoteInfo.getQuickVote());
            queryDto.setPrivacyVote(baseVoteInfo.getPrivacyVote());
            queryDto.setShowResult(baseVoteInfo.getShowResult());
            queryDto.setEndTime(baseVoteInfo.getEndTime());
            queryDto.setStartIntro(baseVoteInfo.getStartIntro());
            queryDto.setEndIntro(baseVoteInfo.getEndIntro());
            queryDto.setAdditionList(baseVoteInfo.getAdditionList());
            queryDto.getSubjectList().get(0).setSubject(baseVoteInfo.getName());
            queryDto.getSubjectList().get(0).setItemList(voteSubjectItemList);

            result = SoaConnectionFactory.put(request,ConstantsUri.CMS_VOTE_RESTFUL,queryDto,VoteQueryDto.class,baseVoteInfo.getId());
        }else{
            result = SoaConnectionFactory.post(request,ConstantsUri.CMS_VOTE,baseVoteInfo,VoteQueryDto.class);
        }

        return result;
    }


    /**
     * 更新发布设置
     * @param request
     * @param model
     * @param voteDto
     * @return
     */
    @PostMapping("/cms/vote/update.php")
    @ResponseBody
    public VoteQueryDto update(HttpServletRequest request, Model model,@RequestBody VoteDto voteDto){

        VoteQueryDto result = null;

        String voteId = voteDto.getId();

        if(StringUtils.isEmpty(voteId)){
            result = new VoteQueryDto();
            result.setCode("-1");
            result.setMessage("查询失败");
            return result;
        }

        VoteQueryDto voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,voteId);
        if(!voteQueryDto.isSuccess() || voteQueryDto.getData()==null){
            result = new VoteQueryDto();
            result.setCode("-1");
            result.setMessage("查询失败");
            return result;
        }

        VoteDto targetVote = voteQueryDto.getData();

        targetVote.setStatus(voteDto.getStatus());
        targetVote.setHeader(voteDto.getHeader());
        targetVote.setFooter(voteDto.getFooter());
        targetVote.setValidateCode(voteDto.getValidateCode());
        targetVote.setHiddenResult(voteDto.getHiddenResult());

        result = SoaConnectionFactory.putRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,targetVote,VoteQueryDto.class,voteId);

        if(!result.isSuccess()){
            result.setCode("-1");
            result.setMessage("发布设置失败");
            return result;
        }

        return result;

    }

    @PostMapping("/cms/vote/toZero.php")
    @ResponseBody
    public BaseResponse toZero(HttpServletRequest request,String voteId){
        BaseResponse result = null;

        if(StringUtils.isEmpty(voteId)){
            result = new BaseResponse("-1","清零失败");
            return result;
        }

        BaseResponse resp = SoaConnectionFactory.delete(request,ConstantsUri.CMS_VOTE_LOG,null,BaseResponse.class,voteId);
        /*VoteQueryDto voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,voteId);
        voteQueryDto.getData().setNop(0);

        if(voteQueryDto.getData().getSubjectList()!=null && voteQueryDto.getData().getSubjectList().size()>0 && voteQueryDto.getData().getSubjectList().get(0).getItemList()!=null){
            for (int i = 0; i < voteQueryDto.getData().getSubjectList().get(0).getItemList().size(); i++) {
                voteQueryDto.getData().getSubjectList().get(0).getItemList().get(i).setNop(0);
            }
        }*/

        result = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,BaseResponse.class,voteId);

        return result;
    }

    @PostMapping("/cms/vote/delete.php")
    @ResponseBody
    public BaseResponse delete(HttpServletRequest request,String voteId){
        BaseResponse result = null;

        if(StringUtils.isEmpty(voteId)){
            result = new BaseResponse("-1","删除失败");
            return result;
        }

        result = SoaConnectionFactory.delete(request,ConstantsUri.CMS_VOTE_RESTFUL,null,BaseResponse.class,voteId);

        return result;
    }


    @GetMapping("/cms/vote/statistics.php")
    public String statisticsPage(HttpServletRequest request,Model model,String voteId){
        if(StringUtils.isEmpty(voteId)){
            return "404";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastWeek = Calendar.getInstance();
        lastWeek.setTime(new Date());
        lastWeek.add(Calendar.MONTH,-1);

        model.addAttribute("startDate",sdf.format(lastWeek.getTime()));
        model.addAttribute("endDate",sdf.format(new Date()));
        model.addAttribute("voteId",voteId);
        return "cms/vote/statistics";
    }

    private List<String[]> packStatisticsData(SimpleDateFormat tarSdf,List<VoteRollTJDto> srcList) throws Exception{
        List<String[]> result = new ArrayList<>();
        VoteRollTJDto tmpRollTj = null;
        String[] tmp=null;
        SimpleDateFormat reqSdf = new SimpleDateFormat("yyyyMMdd");
        if(srcList.size()>0) {
            for (int i = 0; i < srcList.size(); i++) {
                tmpRollTj = srcList.get(i);
                if(result.size()>0) {
                    for (int j = 0; j < result.size(); j++) {
                        if (result.get(j)[0].equalsIgnoreCase(tarSdf.format(reqSdf.parse(tmpRollTj.getTj())))) {
                            result.get(j)[1] = new Integer(result.get(j)[1]) + tmpRollTj.getCnt() + "";
                            break;
                        }
                        if(j==result.size()-1){
                            tmp = new String[2];
                            tmp[0] = tarSdf.format(reqSdf.parse(tmpRollTj.getTj()));
                            tmp[1] = Integer.toString(tmpRollTj.getCnt());
                            result.add(tmp);
                        }
                    }
                }else{
                    tmp = new String[2];
                    tmp[0] = tarSdf.format(reqSdf.parse(tmpRollTj.getTj()));
                    tmp[1] = Integer.toString(tmpRollTj.getCnt());
                    result.add(tmp);
                }
            }
        }
        return result;
    }

    private int dayDiff(Calendar c1, Calendar c2, String type){
        if("day".equalsIgnoreCase(type)){
            if((c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)) &&
                    (c1.get(Calendar.DAY_OF_YEAR)==c2.get(Calendar.DAY_OF_YEAR)))
                return 1;
            else
                return (int)
                        Math.ceil((Math.abs(c1.getTimeInMillis()-c2.getTimeInMillis()))/(1000*24*3600.0));
        }else if("month".equalsIgnoreCase(type)){
            if((c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)) && (c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)))
                return 1;
            else{
                return (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR))*12 + (12-c2.get(Calendar.MONTH));
            }
        }else{
            return ( c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR) + 1 );
        }
    }

    @PostMapping("/cms/vote/summaryVote.php")
    @ResponseBody
    public Map<String,Object> summaryVote(HttpServletRequest request,String voteId,String startDate,String endDate,String statisticsScope){
        Map<String,Object> result = new HashMap<>();
        if(StringUtils.isEmpty(voteId)){
            result.put("code","-1");
            result.put("message","缺少必要参数");
            return result;
        }

        Map<String,Object> param = new HashMap<>();
        param.put("voteId",voteId);
        param.put("startTime",startDate);
        param.put("endTime",endDate);
        VoteStatisticsListQueryDto queryDto = SoaConnectionFactory.get(request,ConstantsUri.CMS_VOTE_STATISTICS,param,VoteStatisticsListQueryDto.class);

        SimpleDateFormat reqSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tarSdf = null;

        VoteStatisticsDto voteStatis = queryDto.getData();

        //综合统计表
        List<Object> sumVoteStatis = new ArrayList<>();
        List<String[]> mobileLine = new ArrayList<>();
        List<String[]> pcLine = new ArrayList<>();
        List<String[]> sumVoteLine = new ArrayList<>();
        String[] tmp = null;
        VoteRollTJDto tmpRollTj = null;
        int k = 0;
        try {

            Calendar startCal = Calendar.getInstance();
            startCal.setTime(reqSdf.parse(startDate));
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(reqSdf.parse(endDate));

            if("day".equalsIgnoreCase(statisticsScope)){
                tarSdf = new SimpleDateFormat("yyyy-MM-dd");
            }else if("month".equalsIgnoreCase(statisticsScope)){
                tarSdf = new SimpleDateFormat("yyyy-MM");
            }else{
                tarSdf = new SimpleDateFormat("yyyy");
            }

            mobileLine = packStatisticsData(tarSdf,voteStatis.getMobileWeblist());
            pcLine = packStatisticsData(tarSdf,voteStatis.getPclist());
            sumVoteLine = packStatisticsData(tarSdf,voteStatis.getTptj());

            int num = dayDiff(endCal,startCal,statisticsScope);

            do{
                tmp = new String[2];
                tmp[0] = tarSdf.format(startCal.getTime());
                tmp[1] = "0";

                if(voteStatis.getMobileWeblist().size()<=0)
                    mobileLine.add(tmp);
                if(voteStatis.getPclist().size()<=0)
                    pcLine.add(tmp);
                if(voteStatis.getTptj().size()<=0)
                    sumVoteLine.add(tmp);

                if("day".equalsIgnoreCase(statisticsScope)){
                    startCal.add(Calendar.DAY_OF_MONTH,1);
                }else if("month".equalsIgnoreCase(statisticsScope)){
                    startCal.add(Calendar.MONTH,1);
                }else{
                    startCal.add(Calendar.YEAR,1);
                }
                k++;
            }while (k<num);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code","-1");
            result.put("message","统计失败");
            return result;
        }
        sumVoteStatis.add(mobileLine);
        sumVoteStatis.add(pcLine);
        sumVoteStatis.add(sumVoteLine);

        List<Map<String,Object>> voteItemList = new ArrayList<>();
        Map<String,Object> voteItem = null;
        BigDecimal a = null;
        BigDecimal b = null;

        for(int i=0;i<voteStatis.getTpxxtj().size();i++){
            voteItem = new HashMap<>();
            voteItem.put("id",voteStatis.getTpxxtj().get(i).getId());
            voteItem.put("item",voteStatis.getTpxxtj().get(i).getTj());
            a = new BigDecimal(voteStatis.getTpxxtj().get(i).getCnt());
            b = new BigDecimal(voteStatis.getTpcnt());
            voteItem.put("percent", a.divide(b, 4, RoundingMode.HALF_DOWN).multiply(new BigDecimal(100)));
            voteItem.put("bz",voteStatis.getTpxxtj().get(i).getBz());
            voteItemList.add(voteItem);
        }

        result.put("totalVote",voteStatis.getTpcnt());
        result.put("totalView",voteStatis.getLlcnts());
        result.put("viewByTime",voteStatis.getLlcnt());
        //投票综合统计表
        result.put("mobileLine",mobileLine);
        result.put("pcLine",pcLine);
        result.put("sumVoteLine",sumVoteLine);
        //投票详细信息表
        result.put("voteDetail",voteStatis.getTpxxtj());
        //投票项投票量
        result.put("voteItemList",voteItemList);

        result.put("code","2000");
        return result;
    }

    @PostMapping("/cms/vote/frozenOrReleaseVote.php")
    @ResponseBody
    public BaseResponse frozenVote(HttpServletRequest request,@RequestParam("id") String voteSubItemId,@RequestParam("curBz") String curBz){
        BaseResponse result = null;

        if(StringUtils.isEmpty(voteSubItemId)){
            result = new BaseResponse("-1","请选择投票项");
            return result;
        }

        if(StringUtils.isEmpty(curBz)){
            result = new BaseResponse("-1","操作失败");
            return result;
        }

        Map<String,Object> param = new HashMap<>();
        param.put("id",voteSubItemId);
        if("0".equals(curBz)){
            param.put("bz","1");
        }else{
            param.put("bz","0");
        }

        result = SoaConnectionFactory.put(request,ConstantsUri.CMS_VOTE_UPDATE_SUBITEM,param,BaseResponse.class);

        return result;
    }

    @GetMapping("/cms/vote/fileUpload.php")
    @ResponseBody
    public Map<String, Object> initUeditorFileUpload(HttpServletRequest request,String action) {
        Map<String, Object> result = new HashMap<>();
        result.put("imageUrl", "/cms/vote/fileUpload.php");
        result.put("imagePathFormat", "/images/{filename}");
        result.put("imageFieldName", "upfile");
        result.put("imageMaxSize", "10240");
        result.put("imageActionName","uploadimage");
        result.put("imageUrlPrefix",BaseObject.getConfig("picdomain"));
        return result;
    }

    @PostMapping("/cms/vote/fileUpload.php")
    @ResponseBody
    public Map<String, Object> fileUpload(MultipartHttpServletRequest multipartRequest){

        Map<String, Object> result = new HashMap<>();

        FileListDto fileResult = null;
        try {
            fileResult = FileOperateUtil.voteImgUpload(multipartRequest);
        }catch (Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
            result.put("state", "上传文件失败");
            result.put("url", "");
            result.put("title", "");
            return result;
        }
        if (fileResult!=null && fileResult.getDataList().size() > 0) {
            result.put("state", "SUCCESS");
            result.put("url", SpringCtxHolder.getProperty("abc.soa-upload-context")+fileResult.getDataList().get(0).getFilePath());
            result.put("title", "");
        } else {
            result.put("state", "上传文件失败");
            result.put("url", "");
            result.put("title", "");
        }
        return result;
    }

    @GetMapping("/cms/vote/activeOrInactiveVote.php")
    @ResponseBody
    public BaseResponse activeOrInactiveVote(HttpServletRequest request,String voteId){
        BaseResponse result = null;
        if(StringUtils.isEmpty(voteId)){
            result = new BaseResponse("-1","请选择启用/停用项目");
            return result;
        }
        VoteQueryDto voteQueryDto = SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_VOTE_RESTFUL,null,VoteQueryDto.class,voteId);
        voteQueryDto.getData().setStatus(voteQueryDto.getData().getStatus()==0?1:0);
        result = SoaConnectionFactory.put(request,ConstantsUri.CMS_VOTE_RESTFUL,voteQueryDto.getData(),BaseResponse.class,voteId);
        return result;
    }

    @GetMapping("/cms/vote/view.php")
    public String view(HttpServletRequest request,Model model,String voteId){

        String snsUrl = SpringCtxHolder.getProperty("snsdomain");

        model.addAttribute("voteId",voteId);
        model.addAttribute("url",snsUrl + "/pub/vote/" + voteId + ".php");

        return "cms/vote/view";
    }
}
