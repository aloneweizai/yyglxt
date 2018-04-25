/**
 * Created by LiuQi
 */

require(["../../../config"], function () {

    require(["jquery-3.1.0","layui"], function ($) {
        $.ajax({
            type: "GET",
            url: initPageLink,
            async: false,
            success: function (data) {
                $(".js_page_div").html(data);
            }
        });
    });

    require(["jquery-3.1.0","../abc/cms/course/category","abc.common","layui","../abc/util/page"], function ($, category) {



        category.initUrl(ctx + "/cms/course/cate/ajaxList.php",ctx +"/cms/course/cate/save.php",
            ctx +"/cms/course/cate/delete/{id}.php",ctx +"/cms/course/cate/save.php");


        //list页面 删去
        $('body').on('click', '.js_delete', function(){
            abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '', $(".js_currLink").val());
        });

        //list页面 修改按钮
        $('body').on('click', '.js_edit,.js_view,.js_view_statistics', function(){
            location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
        });

        $('body').on('click', '.js_change_status', function(){
            abc.layerAjaxConfirm("PUT", $(this).attr("data-href"), '', $(".js_currLink").val());
        });



        zTreeObj.setting.callback.onClick = function(){
            ajaxListByCategory();
        };
        $(".js_query").click(function(){
            ajaxListByCategory();
        });

       /* var queryList = function(){
            var sortFieldName = $.trim($("[name='sortFieldName']").val());
            var sortName = $.trim($("[name='sortName']").val());
            var keywords = $.trim($("[name='keywords']").val());
            var isRecommend = $.trim($("[name='isRecommend']").val());
            var type = $.trim($("[name='type']").val());
            location.href = ctx+'/bangbang/topicRecommend/list.php?type='+type+'&sortFieldName='+sortFieldName+"&sortName="+sortName+"&keywords="+keywords+"&isRecommend="+isRecommend;
            init_layUi();
        };*/
       // $('body').on('click', '.js-query', function(){queryList();});

        /* 点击上升箭头 */
        $('body').on('click', '.layui-table-sort-asc', function(e){
            e.preventDefault();
            $(".layui-table-sort").attr("lay-sort","");
            $(this).parent().attr("lay-sort","asc");
            $("[name='sortFieldName']").val($(this).parent().prev().attr("data-name"));
            $("[name='sortName']").val("asc");
            ajaxListByCategory();
        });
        /* 点击下降箭头 */
        $('body').on('click', '.layui-table-sort-desc', function(e){
            e.preventDefault();
            $(".layui-table-sort").attr("lay-sort","");
            $(this).parent().attr("lay-sort","desc");
            $("[name='sortFieldName']").val($(this).parent().prev().attr("data-name"));
            $("[name='sortName']").val("desc");
            ajaxListByCategory();
        });
        var ajaxListByCategory = function(){
            var categoryCode = "";
            var sortFieldName = $.trim($("[name='sortFieldName']").val());
            var sortName = $.trim($("[name='sortName']").val());
            var keywords = $.trim($("#keywords").val());//关键字
            var status = $("[name='status']").val();
            var recommend = $("[name='recommend']").val();
            var isFree = $("[name='isFree']").val();
            //分类
            var nodes = zTreeObj.getSelectedNodes();
            if(nodes.length == 1){
                categoryCode = nodes[0].id;
            }
            if(categoryCode == "0"){
                categoryCode = "";
            }
            abc.block();
            $.ajax({
                type: 'GET',
                url: ctx+'/cms/course/page.php?title='+keywords+'&classify='+categoryCode+'&status='+status
                +'&sortFieldName='+sortFieldName+"&sortName="+sortName+'&isFree='+isFree+'&recommend='+recommend,
                async: false,
                success: function (data) {
                    $(".js_page_div").html(data);
                    abc.unblock();
                }
            });
        };




    });
});