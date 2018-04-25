<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bangbang/help.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <style>
        a{
            color: #337ab7;
            text-decoration: none;
            cursor: pointer;
        }
        a:hover,a:focus {
            color: #23527c;
        }
        .js-page-tr input{
            text-decoration: none;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        var ctx = "${ctx}";
        var imgurl="${imgPth!}";
    </script>
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<input type="hidden" name="sortFieldName" value="">
<input type="hidden" name="sortName" value="">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <form class="layui-form" id="form1">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${RequestParameters["keywords"]!}" name="keywords" placeholder="标题" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="isRecommend">
                            <option value="">推荐状态</option>
                            <option value="true" <#if RequestParameters["isRecommend"]?? && RequestParameters["isRecommend"] =='true'>selected</#if>>已推荐</option>
                            <option value="false" <#if RequestParameters["isRecommend"]?? && RequestParameters["isRecommend"] =='false'>selected</#if>>未推荐</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">内容类型</label>
                    <div class="layui-input-inline">
                        <select name="type">
                            <option value="">请选择内容类型</option>
                            <option value="question" <#if RequestParameters["type"]?? && RequestParameters["type"] =='question'>selected</#if>>话题</option>
                            <option value="cheats"  <#if RequestParameters["type"]?? && RequestParameters["type"] =='cheats'>selected</#if>>秘籍</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button class="layui-btn ">查找</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <form>
        <div class="layui-form">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="5%">序号</th>
                    <th width="8%" style="text-align: center">内容类型</th>
                    <th style="text-align: center">标题</th>
                    <th style="text-align: center">
                        <span class="js_sort" data-name="answerNum" style="cursor: pointer;">回答数</span>
                        <span class="layui-table-sort layui-inline"
                            <#if RequestParameters["sortFieldName"]?? && RequestParameters["sortName"]??
                            && RequestParameters["sortFieldName"]=='answerNum'>lay-sort="${RequestParameters["sortName"]!}"</#if>>
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                        </span>
                    </th>
                    <th style="text-align: center">
                        <span class="js_sort" data-name="commentNum" style="cursor: pointer;">评论数</span>
                        <span class="layui-table-sort layui-inline"
                            <#if RequestParameters["sortFieldName"]?? && RequestParameters["sortName"]??
                            && RequestParameters["sortFieldName"]=='commentNum'>lay-sort="${RequestParameters["sortName"]!}"</#if>>
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                        </span>
                    </th>
                    <th style="text-align: center">
                        <span class="js_sort" data-name="likeNum" style="cursor: pointer;">点赞数</span>
                        <span class="layui-table-sort layui-inline"
                              <#if RequestParameters["sortFieldName"]?? && RequestParameters["sortName"]??
                              && RequestParameters["sortFieldName"]=='likeNum'>lay-sort="${RequestParameters["sortName"]!}"</#if>>
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                        </span>
                    </th>
                    <th style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if questions?? && (questions?size > 0) >
                    <#list questions as question>
                        <tr>
                            <td class="td_i">${pagerSpec.offset + question_index + 1}</td>
                            <td align="center">
                                <#if question.type == 'question'>话题<#else>秘籍</#if>
                            </td>
                            <td align="center">${(question.title)!}</td>
                            <td align="center">${(question.answerNum)!}</td>
                            <td align="center">${(question.commentNum)!}</td>
                            <td align="center">${(question.likeNum)!}</td>
                            <td align="center">
                                <a class="js_view" data-href="${ctx}/bangbang/topicRecommend/view/${question.type}/${question.id}.php">查看</a> |
                                <#if question.isRecommend?? && question.isRecommend>
                                    <a class="js_recommend" data-val="0" data-href="${ctx}/bangbang/topicRecommend/recommend/${question.type}/${question.id}/false.php">取消推荐</a>
                                <#else>
                                    <a class="js_recommend" <#if question.type == 'question'>data-val="0"<#else>data-val="1"</#if>  data-href="${ctx}/bangbang/topicRecommend/recommend/${question.type}/${question.id}/true.php">推荐</a>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>
<#if questions?? && (questions?size > 0) >
<table width="100%" class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>

<div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal1" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">上传图片</h4>
            </div>
            <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td>
                                图片：
                            </td>
                            <td>
                                <img id="imgshow" height='126' width='160' style='margin-left:10px;max-width:206px' onerror="javascript:this.src='${ctx}/images/default.jpg';" src='${imgPth!}${(couponActivityRs.imageUrl)!}' />
                                <input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><#--<label style='color:red;'>*</label>-->
                                <button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
                                <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                                <input type="hidden" id="imageUrl" name="imageUrl"  value='${(couponActivityRs.imageUrl)!}'>
                                <div style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出1MB（jpg、png、bmp）</div>
                            </td>
                        </tr>
                    </table>
            </div>
            <div class="modal-footer">
                <input type="button" value="确定" id="submit" name="submit" class="layui-btn layui-btn-normal" data-val="2">
                <input type="button" value="关闭" id="back" class="layui-btn layui-btn-primary" data-val="2">
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/bangbang/topicRecommend/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>