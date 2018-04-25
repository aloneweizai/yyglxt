require(["../../config"], function () {
    require(["jquery-3.1.0","ztree","nicevalidator","nicevalidator.zh_CN","abc.ajaxfileupload","abc.common","layui"], function ($) {

        //点击组织选择按钮 弹出树形结构层
        $(".js_choose_btn").click(function(){
            $(".js_pop_ztree").show();
        });
        //点击取消 隐藏对话框
        $('body').on('click', '.js_close', function(){
            $(".js_pop_ztree").hide();
        });

        //组织-员工树--------
        var org_operator_nodes = [];
        $(".org_li").each(function(){
            var obj = {};
            obj.id= $(this).attr("data-org-id");
            obj.pId= $(this).attr("data-org-pid");
            obj.name= $(this).attr("data-org-name");
            obj.open = false;
            obj.type = 'org';
            org_operator_nodes.push(obj);
        });
        $(".operator_li").each(function(){
            var obj = {};
            if($(this).attr("data-user-orgId") !=""){
                obj.id= $(this).attr("data-user-id");
                obj.pId= $(this).attr("data-user-orgId");
                obj.name= $(this).attr("data-user-name");
                obj.open = false;
                obj.type = 'operator';
                obj.empId = $(this).attr("data-user-empId");
                obj.phone = $(this).attr("data-user-phone");
                org_operator_nodes.push(obj);
            }
        });
        var org_operator_setting = {
            view: {dblClickExpand: true,showLine: false},//屏蔽掉双击事件
            data: {simpleData: {enable: true}},
            callback: {
                onClick: function (e,treeId, treeNode) {
                    //为员工工号，姓名，联系电话 赋值
                    if(treeNode.type == 'operator'){
                        //$("#username").text(treeNode.name);
                        $("#userId").text(treeNode.empId);
                        $("#phone").text(treeNode.phone);
                        $("[name='username']").val(treeNode.name);
                        $("[name='userId']").val(treeNode.empId);
                        $("[name='phone']").val(treeNode.phone);
                    }
                }
            }
        };
        $.fn.zTree.init($("#org_operator_tree"), org_operator_setting, org_operator_nodes);
        var org_operator_tree = $.fn.zTree.getZTreeObj("org_operator_tree");

        /* 点击保存按钮 */
        $(".js_save_btn").click(function(){
            if($validatorFrom.isValid()){
                if(!$("[name='username']").val()){
                    layer.msg("请选择用户", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                }else if($("[name='userPicturePath']").val()==''){
                    layer.msg("请上传形象照片", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                }else{
                    abc.layerAjaxConfirm("POST", $("form").attr('action'), $("form").serializeJson(), $(".js_currLink").val());
                }
            }
        });
        //list页面 禁用开启
        $('body').on('click', '.js_enable,.js_delete', function(){
            var _this = $(this);
            abc.layerAjaxConfirm("POST", _this.attr("data-href"), '', $(".js_currLink").val());
        });
        //list页面 修改按钮
        $('body').on('click', '.js_edit', function(){
            location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
        });

        //list页面 修改按钮
        $('body').on('click', '.js_view', function(){
            var _this = $(this);
            $.ajax({
                type: 'GET',
                url: _this.attr("data-href"),
                success: function (content) {
                    var index = layer.open({
                        type: 1,
                        title: '客户经理详情',
                        skin: 'layui-layer-molv',
                        closeBtn: 1,
                        area: ['600px', "500px"],
                        offset: [200],
                        shadeClose: false,
                        content: content,
                        success: function (elem, i) {
                                var $target = $(elem);
                                $target.find("button[name='close-layer-btn']").on("click", function () {
                            });
                        },
                    });
                }
            });



        });


        $("#upload_img_btn").click(function(){
            var obj = $(this);
            var _val=$("#uploadFile").val();
            if(_val==""){
                abc.layerAlert(false,'请选择上传图片');
                return;
            }
            if($("#uploadFile")[0].files[0].size>204800){
                $("#uploadFile").val('');
                abc.layerAlert(false,'图片大小不能超过200KB');
                return;
            }
            //读取图片数据
            var reader = new FileReader();
            reader.readAsDataURL($("#uploadFile")[0].files[0]);
            reader.onload = function (e) {
                var data = e.target.result;
                //加载图片获取图片真实宽度和高度
                var image = new Image();
                image.onload=function(){
                    var width = image.width;
                    var height = image.height;
                    //if(width<480 || height<270){
                    //    $("#uploadFile").val('');
                    //    abc.layerAlert(false,'图片尺寸至少为480px*270px(像素)');
                    //    return;
                    //}else{
                        var types=$("#uploadFile").attr('data-type').split(';');
                        var type=_val.substring(_val.lastIndexOf(".")+1);
                        obj.hide();
                        $("#uploadMsg").html('正在上传.....')
                        if(types.indexOf(type)<0){
                            abc.layerAlert(false,'允许类型:['+obj.attr('data-type')+"]");
                            obj.show();
                            $("#uploadMsg").html('');
                            return;
                        }
                        $.ajaxFileUpload({
                            url : ctx+'/util/doFileupload.php?path=customerManager',
                            type : 'post',
                            secureuri : false, // 一般设置为false
                            fileElementId : 'uploadFile', // 上传文件的id、name属性名
                            dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                            success : function(data, status) {
                                obj.show();
                                $("#uploadMsg").html('');
                                if(data.code=='200'){
                                    $("#imgshow").html("<img height='90' width='90' style='margin-left:10px;' src='"+imgUrl+data.message+"' />");
                                    $("[name='userPicturePath']").val(data.message).blur();
                                }
                            },
                            error : function(data, status, e) {
                                obj.show();
                            }
                        });
                    //}
                };
                image.src= data;
            };
        });

        //数据校验
        var $validatorFrom = $("form").validator({
            theme: 'yellow_top',
            timely: 1,
            fields: {
                "intro": "required",
            }
        });
        $validatorFrom.validator().trigger("showtip");
    })
});
