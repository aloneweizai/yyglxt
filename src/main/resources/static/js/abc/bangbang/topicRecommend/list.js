require(["../../../config"], function () {
    require(["jquery.full","jquery.easyui.min","abc.common","../abc/util/page","layui"], function ($) {

        var init_layUi = function() {
            layui.use('form', function () {
                var form = layui.form;
                form.render();
            });
        }
        init_layUi();

        var queryList = function(){
            var sortFieldName = $.trim($("[name='sortFieldName']").val());
            var sortName = $.trim($("[name='sortName']").val());
            var keywords = $.trim($("[name='keywords']").val());
            var isRecommend = $.trim($("[name='isRecommend']").val());
            var type = $.trim($("[name='type']").val());
            location.href = ctx+'/bangbang/topicRecommend/list.php?type='+type+'&sortFieldName='+sortFieldName+"&sortName="+sortName+"&keywords="+keywords+"&isRecommend="+isRecommend;
            init_layUi();
        };
        $('body').on('click', '.js-query', function(){queryList();});

        /* 点击上升箭头 */
        $('body').on('click', '.layui-table-sort-asc', function(e){
            e.preventDefault();
            $(".layui-table-sort").attr("lay-sort","");
            $(this).parent().attr("lay-sort","asc");
            $("[name='sortFieldName']").val($(this).parent().prev().attr("data-name"));
            $("[name='sortName']").val("asc");
            queryList();
        });
        /* 点击下降箭头 */
        $('body').on('click', '.layui-table-sort-desc', function(e){
            e.preventDefault();
            $(".layui-table-sort").attr("lay-sort","");
            $(this).parent().attr("lay-sort","desc");
            $("[name='sortFieldName']").val($(this).parent().prev().attr("data-name"));
            $("[name='sortName']").val("desc");
            queryList();
        });

        /* 点击 */
        $('body').on('click', '.js_sort', function(){
            var sort_obj = $(this).next();
            var lay_sort = sort_obj.attr("lay-sort");
            $(".layui-table-sort").attr("lay-sort","");
            $("[name='sortFieldName']").val($(this).attr("data-name"));
            if(lay_sort=='asc'){
                sort_obj.attr("lay-sort","desc");
                $("[name='sortName']").val("desc");
            }else if(lay_sort =='desc'){
                sort_obj.attr("lay-sort","");
                $("[name='sortName']").val("");
                $("[name='sortFieldName']").val("");
            }else{
                sort_obj.attr("lay-sort","asc");
                $("[name='sortName']").val("asc");
            }
            queryList();
        });
var url;
        /*点击推荐*/
        $('body').on('click', '.js_recommend', function(){
            url = $(this).attr("data-href");
            var val=$(this).attr('data-val');
            if(val=="1") {
                $("#myModal1").show();
                $("#myModal1").find(".modal-dialog").animate({'top': abc.winscrollTop(100)}, 300);
            }
            else{
                abc.layerAjaxConfirm("post", $(this).attr("data-href"), '', $(".js_currLink").val());
            }
        });

        $("[id='submit']").click(function(){
            var imgUrl = $("#imageUrl").val();
            abc.layerAjaxConfirm("post", url+"?imgUrl="+imgUrl, '', $(".js_currLink").val());
        });

        $("[id='back']").click(function(){
            $("#myModal1").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
                $("#myModal1").hide();
            });
        });

        $("#upload_btn").click(function(){
            updat($(this));
        });

        function updat(obj){
            var _o=$("#uploadFile");
            var _val=_o.val();
            if(_val==""){
                abc.layerAlert(false,'请选择上传图片');
                return;
            }
            var size=_o[0].files[0].size/1024;
            if(size>1024){
                abc.layerAlert(false,'上传图片超过1MB!');
                return;
            }
            var types=_o.attr('data-type').split(';');
            var type=_val.substring(_val.lastIndexOf(".")+1);
            obj.hide();
            $("#uploadMsg").html('正在上传.....')

            if(types.indexOf(type)<0){
                abc.layerAlert(false,'允许类型:['+_o.attr('data-type')+"]");
                obj.show();
                $("#uploadMsg").html('');
                return;
            }
            $.ajaxFileUpload({
                url : ctx+'/util/doFileupload.php?path=vipgift',
                type : 'post',
                secureuri : false, // 一般设置为false
                fileElementId : 'uploadFile', // 上传文件的id、name属性名
                dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                success : function(data, status) {
                    $("#upload_btn").show();
                    $("#uploadMsg").html('');
                    if(data.code=='200'){
                        $("#imgshow").attr('src',imgurl+data.message);
                        $("#imageUrl").val(data.message).blur();
                    }else{
                        abc.layerAlert(false,data.message);
                    }
                },
                error : function(data, status, e) {
                    alert(e);
                }
            });
        }
        $('body').on('click', '.js_view', function(e){
            e.preventDefault();
            $.ajax({
                type: "GET",
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    var index=layer.open({
                        type: 1,
                        title: "话题详情",
                        skin: 'layui-layer-molv',
                        closeBtn: 2,
                        area: ['680px',"500px"],
                        offset: [200],
                        shadeClose: false,
                        content: data,
                        success: function (elem, i) {
                            var $target = $(elem);
                            $target.find("button[name='close-layer-btn']").on("click", function () {
                                layer.close(i);
                            });
                        },
                    });
                }
            });
        });


    })
});
