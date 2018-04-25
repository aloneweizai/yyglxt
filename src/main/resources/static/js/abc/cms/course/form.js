/**
 * Created by LiuQi
 */
window.UEDITOR_HOME_URL = ctx+"/js/plugin/ueditor/";
require(["../../../config"], function () {
    require(["jquery-3.1.0", "zeroClipboard", "../abc/util/date","abc.common", "layui","ztree","abc.ajaxfileupload",
        "../abc/cms/knowledge/tag","../abc/cms/course/lecturer/lecturer","../abc/cms/course/goods",
        "../abc/cms/course/courseware/form","ueditor","zeroClipboard"], function ($, zeroClipboard, abc_date) {
        window.ZeroClipboard=zeroClipboard;
        var ue = UE.getEditor("editor_curriculumidIntro",{
            initialFrameWidth: 700,
            initialFrameHeight: 300,
            autoHeightEnabled: false
        });
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        /*提醒：关于图片的配置，在ueditor.all.js中设置了可选的图片类型，默认为 accept="image/*", 已改为 accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp"*/
        UE.Editor.prototype.getActionUrl = function(action) {
            if(action == 'config'){
                return ctx+'/util/initSoaUeditorFileUpload.php';
            }
            else if(action == "uploadimage"){
                return ctx+'/util/ueditorSoaFileUpload.php?subsystem=csw&fileTypeTag=image';
            }else {
                return this._bkGetActionUrl.call(this, action);
            }
        };

        //初始化富文本框
        //var editor = new Editor(".jd_editor");
        //editor.customConfig.uploadImgTimeout = 20000;
        //editor.customConfig.uploadImgServer = ctx+'/util/wangEditorUpload/course.php';
        //editor.customConfig.menus =['head', 'bold', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'image', 'table', 'video', 'code', 'undo', 'redo'];
        //editor.create();
        //if(readonly == "true"){
        //    editor.$textElem.attr('contenteditable', false);
        //}

        layui.use('form', function(){
            var form = layui.form;
            form.render();
            form.on('checkbox(level)', function(data){
                var vipCode = data.value;
                var tr = $("tr[data-vip-code='"+vipCode+"']");
                if(data.elem.checked){
                    tr.attr("style", "display:none;");
                }else{
                    tr.attr("style","display:block");
                }
            });
        });
        //编辑课程切换
        $(".room-subnav a").click(function(){
            var html=$.trim($(this).text());
            $(this).addClass('active').siblings().removeClass('active');
            if(html=="课时信息"){
                $("table.courseware_table").show();
                $("table.course_table").hide();
            }else{
                $("table.course_table").show();
                $("table.courseware_table").hide();
            }
        });

        //课程收费
        $(".charge input[type='radio']").click(function(){
            if($(this).val()=="1"){
                $('tr.goods,tr.member').hide();
                $(".js_sign_fee_tr").hide();
            }else{
                $('tr.goods,tr.member').show();
                if($("[name='teachingMethod']:checked").val() != 'recording'){
                    $(".js_sign_fee_tr").show();
                }
            }
        });

        //授课方式切换
        $("[name='teachingMethod']").click(function(){
            //如果是录播，培训时间/是否需要报名/报名时交费/是否需要签到/人数限制/培训地点  不需要
            if($(this).val()=='recording'){
                $(".js_train_tr").hide();
            }else{
                $(".js_train_tr").show();
            }
            if($(".charge input[type='radio']:checked").val()=='1'){
                $(".js_sign_fee_tr").hide();
            }
        });

        //报名时间
        $("[name='isApply']").click(function(){
            if($(this).val() == '0'){
                $("#applyTime_td").hide();
                $(".js_sign_fee_tr").hide();
            }else{
                $("#applyTime_td").show();

                var isFree = $("#course_form [name='isFree']:checked").val();//是否收费
                if(isFree =='0'){
                    $(".js_sign_fee_tr").show();
                }
            }
        });
        //签到时间
        $("[name='isSign']").click(function(){
            if($(this).val() == '0'){
                $("#signTime_td").hide();
            }else{
                $("#signTime_td").show();
            }
        });



        //点击选择分类
        $("#selectCateTree").on('click', function(){
            var cateBox = layer.open({
                title:"选择分类",
                type: 1,
                offset: '100px',
                fixed: false,
                content: '<div name="column_tree_layer" class="nycon_list_b"><div><ul id="abc_tree" class="ztree">' +
                '</ul></div><input value="确认" id="ok_btn" class="js_close btn btn-info"></div>',
                success:function(){
                    initTree();
                    $("#ok_btn").on("click", function(){
                        layer.close(cateBox);
                    });
                }
            });
        });
        //初始化分类
        function initTree(){
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                callback:{
                    onClick: function(e,treeId, treeNode) {
                        //点击给分类栏位赋值；
                        if(treeNode.id == 0 || treeNode.pId == 0 ){
                            $("#selectCateTree").val('');
                            $("[name='classify']").val('');
                        }else{
                            $("#selectCateTree").val(treeNode.name);
                            $("[name='classify']").val(treeNode.id);
                        }
                    }
                },
                data: {
                    simpleData: {enable: true}
                }
            };
            $.ajax({
                type:'GET',
                url:ctx +"/cms/course/cate/ajaxList.php",
                dataType:'json',
                success : function(data){
                    var zNodes =  [{ id:0, pId:"-1", name:"全部分类", open:true}];
                    $.each(data.dataList,function(key, val){
                        var obj = {};
                        obj.idx = val.classifyId;
                        obj.id = val.classifyId;
                        obj.pId = val.parentId;
                        obj.name = val.classifyName;
                        obj.open = true;
                        if(val.parentCode==0){
                            obj.isParent = 1;
                            obj.open = false;
                        }
                        zNodes.push(obj);
                    });
                    zTreeObj = $.fn.zTree.init($("#abc_tree"), setting, zNodes);
                }
            });
        }

        //上传课程图片
        $("#upload_img_btn").click(function(){
            var obj = $(this);
            var _val=$("#uploadFile").val();
            if(_val==""){
                abc.layerAlert(false,'请选择上传图片');
                return;
            }
            if($("#uploadFile")[0].files[0].size>307200){
                $("#uploadFile").val('');
                abc.layerAlert(false,'图片大小不能超过300KB');
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
                    if(width<480 || height<270){
                        $("#uploadFile").val('');
                        abc.layerAlert(false,'图片尺寸至少为480px*270px(像素)');
                        return;
                    }else{
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
                            url : ctx+'/util/doFileupload.php?path=course',
                            type : 'post',
                            secureuri : false, // 一般设置为false
                            fileElementId : 'uploadFile', // 上传文件的id、name属性名
                            dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                            success : function(data, status) {
                                obj.show();
                                $("#uploadMsg").html('');
                                if(data.code=='200'){
                                    $("#imgshow").html("<img height='90' width='90' style='margin-left:10px;' src='"+imgUrl+data.message+"' />");
                                    $("[name='cover']").val(data.message).blur();
                                }
                            },
                            error : function(data, status, e) {
                                obj.show();
                            }
                        });
                    }
                };
                image.src= data;
            };
        });


        //保存课程信息
        $(".js_save_course").click(function(e){
            e.preventDefault();
            if(!isValid()){
                return false;
            }
            var formObj = {};
            formObj.curriculumId = $("#course_form [name='curriculumId']").val();//课程id
            formObj.title = $("#course_form [name='title']").val();//标题
            formObj.recommend = $("#course_form [name='recommend']:checked").val();//推荐
            var isFree = $("#course_form [name='isFree']:checked").val();//是否收费
            formObj.isFree = isFree;
            if(isFree == '0'){
                //设置免费会员
                var membergradeList=[];
                $(".js_free_member:checked").each(function(){
                    membergradeList.push({"memberGrade":$(this).val()});
                });
                formObj.membergradeList = membergradeList;
                /*设置课程价格*/
                formObj.costPrice = $.trim($("[name='costPrice']").val());
                formObj.marketPrice = $.trim($("[name='marketPrice']").val());
                var uvipPriceList = [];
                $(".js_vip_price:visible").each(function(){
                    var curriculumGiftBoList = [];
                    var points = {};
                    var pointoper = $.trim($(this).find("[name='points']").val());
                    var pointValue  = $.trim($(this).find("[name='giveIntegral']").val());
                    if ((pointValue != null && pointValue != "") && (pointoper != null && pointoper != "")) {
                        points["operSymbol"] = pointoper;
                        points["operType"] = "POINTS";
                        points['operValue'] = pointValue;
                        curriculumGiftBoList.push(points);
                    }
                    var vips = {};
                    var vipsoper = $.trim($(this).find("[name='vip']").val());
                    var vipsValue  = $.trim($(this).find("[name='givelevel']").val());
                    if ((vipsValue != null && vipsValue != "") && (vipsoper != null && vipsoper != "")) {
                        vips["operSymbol"] = vipsoper;
                        vips["operType"] = "VIP";
                        vips['operValue'] = vipsValue;
                        curriculumGiftBoList.push(vips);
                    }
                    var coupon = {};
                    var couponoper = $.trim($(this).find("[name='coupon']").val());
                    var couponValue  = $.trim($(this).find("[name='couponId']").val());
                    if ((couponValue != null && couponValue != "") && (couponoper != null && couponoper != "")) {
                        coupon["operSymbol"] = couponoper;
                        coupon["operType"] = "COUPON";
                        coupon['operValue'] =  couponValue;
                        curriculumGiftBoList.push(coupon);
                    }
                    var amount = {};
                    var amountoper = $.trim($(this).find("[name='amount']").val());
                    var amountValue  =  $.trim($(this).find("[name='giveamount']").val());
                    if ((amountValue != null && amountValue != "") && (amountoper != null && amountoper != "")) {
                        amount["operSymbol"] = amountoper;
                        amount["operType"] = "AMOUNT";
                        amount['operValue'] =  amountValue;
                        curriculumGiftBoList.push(amount);
                    }
                    var vipGrade = $(this).attr("data-vip-code");
                    var vipPrice = $.trim($(this).find("[name='vipPrice']").val());
                    var vipIntegral = $.trim($(this).find("[name='vipIntegral']").val());
                    var giveIntegral = $.trim($(this).find("[name='giveIntegral']").val());
                    uvipPriceList.push({"curriculumId":formObj.curriculumId,"vipPrice":vipPrice,"vipIntegral":vipIntegral,"curriculumGiftBoList":curriculumGiftBoList,"vipGrade":vipGrade});
                    if(vipGrade =='VIP0'){
                        formObj.sellPrice = vipPrice;
                        formObj.integralPrice = vipIntegral;
                    }
                });
                formObj.uvipPriceBoList = uvipPriceList;
            }
            var teachingMethod = $("#course_form [name='teachingMethod']:checked").val();//授课方式
            formObj.teachingMethod = teachingMethod;//授课方式
            if($.inArray(teachingMethod, ['live','face']) >= 0){
                // 培训时间
                formObj.trainTimeBegin = abc_date.getDate($("#trainTimeBegin").val());
                formObj.trainTimeEnd = abc_date.getDate($("#trainTimeEnd").val());
                var isApply = $("#course_form [name='isApply']:checked").val();
                formObj.isApply = isApply;//是否需要报名
                //报名时间
                if(isApply == '1'){
                    formObj.applyTimeBegin = abc_date.getDate($("#applyTimeBegin").val());
                    formObj.applyTimeEnd = abc_date.getDate($("#applyTimeEnd").val());
                }
                var isSign = $("#course_form [name='isSign']:checked").val();//是否需要签到
                formObj.isSign = isSign;
                //签到时间
                if(isSign == '1'){
                    formObj.signTimeBegin = abc_date.getDate($("#signTimeBegin").val());
                    formObj.signTimeEnd = abc_date.getDate($("#signTimeEnd").val());
                }
                if(isFree == '0'){
                    formObj.applyPay = $("#course_form [name='applyPay']:checked").val();//是否需要报名时交费
                }
                formObj.peopleLimit = $("#course_form [name='peopleLimit']").val();//人数限制
                formObj.trainSite = $("#course_form [name='trainSite']").val();//培训地点
            }

            //设置标签
            var lables = [], lecturerGxList=[];
            $("[name='tagIds']").each(function(){
                lables.push({'labelId':$(this).val()});
            });
            $("[name='lecturerId']").each(function(){
                lecturerGxList.push({"lecturerId":$(this).val()});
            });
            formObj.cover = $("#course_form [name='cover']").val();//课程封面
            formObj.labelList = lables;//相关标签
            formObj.issueScope = $("#course_form [name='issueScope']:checked").val();//发布范围
            formObj.lecturerGxList = lecturerGxList;//设置讲师
            formObj.curriculumidIntro = ue.getContent();//课程简介
            formObj.status = $(this).attr("data-status");//课程状态
            formObj.classify = $("#course_form [name='classify']").val();




            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                function(){
                    abc.block();
                    $.ajax({
                        type: "POST",
                        url: ctx+'/cms/course/save.php',
                        data: JSON.stringify(formObj),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            $.unblockUI();
                            if (data && data.code == "2000") {
                                if(formObj.status == '1'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    window.location.href = $(".js_currLink").val();
                                }else{
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    window.location.href =  ctx+'/cms/course/edit.php?id='+data.data.curriculumId+"&currLink="+encodeURIComponent($(".js_currLink").val());
                                }
                            } else {
                                abc.layerAlert(false, '操作失败: '+data.message);
                            }
                        }
                    });
                }
            );
        });

        $(".js_keyup_number").keyup(function(){
            $(this).val($(this).val().replace(/[^0-9.]/g,''));
        }).bind("paste",function(){  //CTR+V事件处理
            $(this).val($(this).val().replace(/[^0-9.]/g,''));
        }).css("ime-mode", "disabled"); //CSS设置输入法不可用


        var isValid = function(){
            //校验表单数据
            if($("[name='title']","#course_form").val() ==''){
                abc.layerAlert(false, '操作失败: 课程标题不能为空');
                return false;
            }
            if($("[name='classify']","#course_form").val() ==''){
                abc.layerAlert(false, '操作失败: 课程分类不能为空');
                return false;
            }
            if($("[name='isFree']:checked","#course_form").val() =='0'){
                var isValid = true;
                /*如果是收费，校验课程价格设置*/
                var costPrice = $.trim($("[name='costPrice']").val());//成本价格
                var marketPrice = $.trim($("[name='marketPrice']").val());//市场价格
                var sellPrice = '';//销售价格
                $(".js_vip_price:visible").each(function(){
                    var vipPrice = $.trim($(this).find("[name='vipPrice']").val());
                    var vipGrade = $(this).attr("data-vip-code");
                    if(vipGrade =='VIP0'){
                        sellPrice = vipPrice;
                    }
                    var pointoper = $.trim($(this).find("[name='points']").val());
                    var pointValue  = $.trim($(this).find("[name='giveIntegral']").val());
                    if ((pointoper == null || pointoper == "") && (pointValue != null && pointValue != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已输入赠送积分，请选择相应的赠送积分操作选项！");
                        return false;
                    }
                    if ((pointValue == null || pointValue == "") && (pointoper != null && pointoper != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已选择相应的赠送积分操作选项，请输入赠送积分！");
                        return false;
                    }

                    var amountoper = $.trim($(this).find("[name='amount']").val());
                    var amountValue  =  $.trim($(this).find("[name='giveamount']").val());
                    if ((amountoper == null || amountoper == "") && (amountValue != null && amountValue != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已输入赠送礼包金额，请选择相应的赠送礼包金额操作选项！");
                        return false;
                    }
                    if ((amountValue == null || amountValue == "") && (amountoper != null && amountoper != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已选择相应的赠送礼包金额操作选项，请输入赠送礼包金额！");
                        return false;
                    }

                    var couponoper = $.trim($(this).find("[name='coupon']").val());
                    var couponValue  = $.trim($(this).find("[name='couponId']").val());
                    if ((couponoper == null || couponoper == "") && (couponValue != null && couponValue != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已选择赠送优惠券，请选择相应的赠送优惠券操作选项！");
                        return false;
                    }
                    if ((couponValue == null || couponValue == "") && (couponoper != null && couponoper != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已选择相应的赠送优惠券操作选项，请选择赠送优惠券！");
                        return false;
                    }

                    var vipsoper = $.trim($(this).find("[name='vip']").val());
                    var vipsValue  = $.trim($(this).find("[name='givelevel']").val());
                    if ((vipsoper == null || vipsoper == "") && (vipsValue != null && vipsValue != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已选择赠送会员，请选择相应的赠送会员操作选项！");
                        return false;
                    }
                    if ((vipsValue == null || vipsValue == "") && (vipsoper != null && vipsoper != "")) {
                        isValid = false;
                        abc.layerAlert(false, "已选择相应的赠送会员操作选项，请选择赠送会员！");
                        return false;
                    }
                });
                if(costPrice =='' || marketPrice ==''){
                    abc.layerAlert(false, '操作失败: 课程收费时价格，积分设置不能为空');
                    return false;
                }
                var reg=/^[1-9]\d*(\.[0-9]{0,2})?$/;
                if(!reg.test(costPrice)  || !reg.test(marketPrice)|| !reg.test(sellPrice)){
                    abc.layerAlert(false, '操作失败: 课程收费时价格，积分设置需为正整数或最多带2位小数');
                    return false;
                }
                if(!(parseFloat(costPrice)<= parseFloat(sellPrice) && parseFloat(sellPrice)< parseFloat(marketPrice))){
                    abc.layerAlert(false, '操作失败: 课程收费价格设置需满足条件  (成本价格<=会员销售价<=普通用户销售价<市场价格)');
                    return false;
                }

                $(".js_vip_price:visible").each(function(){

                    var vipPrice = $.trim($(this).find("[name='vipPrice']").val());
                    var vipIntegral = $.trim($(this).find("[name='vipIntegral']").val());
                    var giveIntegral = $.trim($(this).find("[name='giveIntegral']").val());
                    if(vipPrice =='' || vipIntegral ==''){
                        isValid = false;
                        abc.layerAlert(false, '操作失败: 课程收费时价格，积分设置不能为空');
                        return false;
                    }
                    if(!reg.test(vipPrice) || !reg.test(vipPrice) || !reg.test(vipPrice)){
                        isValid = false;
                        abc.layerAlert(false, '操作失败: 课程收费时价格，积分设置需为正整数或最多带2位小数');
                        return false;
                    }
                    if(!(parseFloat(costPrice)<= parseFloat(vipPrice) && parseFloat(vipPrice)<= parseFloat(sellPrice))){
                        isValid = false;
                        abc.layerAlert(false, '操作失败: 课程收费价格设置需满足条件  (成本价格<=会员销售价<=普通用户销售价<市场价格)');
                        return false;
                    }
                });
                if(!isValid){
                    return false;
                }
            }
            var teachingMethod = $("[name='teachingMethod']:checked","#course_form").val();
            if($.inArray(teachingMethod, ['live','face']) >= 0){
                if($("#trainTimeBegin").val()=='' || $("#trainTimeEnd").val()==''){
                    abc.layerAlert(false, '操作失败: 培训时间不能为空');
                    return false;
                }
                if($("#trainTimeBegin").val() >= $("#trainTimeEnd").val()){
                    abc.layerAlert(false, '操作失败: 培训截止时间需大于开始时间');
                    return false;
                }
                //如果需要报名
                if($("[name='isApply']:checked","#course_form").val() =='1'){
                    if($("#applyTimeBegin").val()=='' || $("#applyTimeEnd").val()==''){
                        abc.layerAlert(false, '操作失败: 报名时间不能为空');
                        return false;
                    }
                    if($("#applyTimeBegin").val() >= $("#applyTimeEnd").val()){
                        abc.layerAlert(false, '操作失败: 报名截止时间需大于开始时间');
                        return false;
                    }
                    if($("#applyTimeEnd").val() > $("#trainTimeBegin").val()){
                        abc.layerAlert(false, '操作失败: 报名时间需在培训时间之前');
                        return false;
                    }
                }
                //如果需要签到
                if($("[name='isSign']:checked","#course_form").val() =='1'){
                    if($("#signTimeBegin").val()=='' || $("#signTimeEnd").val()==''){
                        abc.layerAlert(false, '操作失败: 签到时间不能为空');
                        return false;
                    }
                    if($("#signTimeBegin").val() >= $("#signTimeEnd").val()){
                        abc.layerAlert(false, '操作失败: 签到截止时间需大于开始时间');
                        return false;
                    }
                    if($("#signTimeBegin").val() < $("#trainTimeBegin").val() || $("#signTimeEnd").val() > $("#trainTimeEnd").val()){
                        abc.layerAlert(false, '操作失败: 签到时间需在培训时间内');
                        return false;
                    }
                }
                //人数限制
                var peopleLimit = $("[name='peopleLimit']").val();
                if(!peopleLimit.match(/^(\d|[1-9]\d{0,4})$/)){
                    abc.layerAlert(false, '操作失败: 人数限制范围[0-9999]');
                    return false;
                }
                if($("[name='trainSite']","#course_form").val() ==''){
                    abc.layerAlert(false, '操作失败: 培训地点不能为空');
                    return false;
                }
            }
            if($("[name='cover']","#course_form").val() ==''){
                abc.layerAlert(false, '操作失败: 未上传课程封面');
                return;
            }
            if($("[name='lecturerId']","#course_form").length ==0){
                abc.layerAlert(false, '操作失败: 请选择讲师');
                return false;
            }
            return true;
        };



        /* 课程选择标签 */
        $(".js_select_courseTag").on('click',function(e){
            e.preventDefault();
            var tagType = $(this).attr("data-type");
            var classifyId = $("[name='classify']","#course_form").val();
            if(classifyId ==''){
                abc.layerAlert(false, '操作失败: 请选择课程分类');
                return false;
            }
            $.ajax({
                type: "GET",
                url: ctx + "/cms/course/cateTag.php?classifyId="+classifyId,
                async: false,
                success: function(html){
                    layer.open({
                        title:"选择标签",
                        type: 1,
                        area: ['1000px','380px'],
                        fixed: true, //不固定
                        content: html,
                        success:function(){
                            /* 点击标签 */
                            $("#tag_query").on('click', function(){
                                var tagName = $.trim($("#tag_input").val());
                                $.ajax({
                                    url: ctx + "/cms/course/cateTag/ajaxList.php?tagName="+tagName+"&classifyId="+classifyId,
                                    type: "GET",
                                    success: function (result) {
                                        if(result.code == '2000'){
                                            var html='';
                                            $.each(result.dataList, function (i, item) {
                                                if(item.tagName){
                                                    html = html+ '<span><button type="button" class="layui-btn layui-btn-primary" name="tagBtn" value="'+item.tagId+'">'+item.tagName+'</button></span>&nbsp;&nbsp;'
                                                }
                                            });
                                            $("#labelDIV_").html(html);
                                        }
                                    },
                                    error: function (err) {
                                        layer.msg("操作失败", {icon: 5},function () {
                                        });
                                    }
                                });
                            });
                            $("button[name='tagBtn']").on('click', function(){
                                var flag = true, _this = $(this);
                                $("#labelDiv input").each(function(e, i){
                                    if($(this).val() == _this.val()){
                                        flag = false;
                                        return false;
                                    }
                                });
                                if(flag){
                                    $("#labelDiv").append("<span name='labelSpan' onclick='$(this).remove();'><input name='tagIds' type='hidden' value='"+$(this).val()+"'/><button type='button' class='btn btn-default'>"+$(this).text()+"<i class='glyphicon glyphicon-remove'></i></button></span>");
                                }
                            });
                            $("#add_label_btn").on('click', function(){
                                var labelStr = $.trim($("#label").val());
                                if(!labelStr){
                                    layer.alert('请输入标签', {icon: 5});
                                    return;
                                }
                                var tags = labelStr.split(",");
                                var param = [];
                                for(var i=0;i<tags.length;i++){
                                    var obj = {};
                                    obj.classifyId = classifyId;
                                    obj.tagName = tags[i];
                                    param.push(obj);
                                }

                                $.ajax({
                                    url: ctx + "/cms/course/addTagAndRefClassify.php",
                                    type: "POST",
                                    data: JSON.stringify(param),
                                    async: false,
                                    contentType: "application/json",
                                    dataType: "JSON",
                                    success: function (result) {
                                        if (result.code == '2000') {
                                            layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                                                $("#label").val("");
                                                var labelList = result.dataList;
                                                if(labelList!=null && labelList.length>0){
                                                    for(var i=0;i<labelList.length;i++){
                                                        $("#labelDIV_").append("<span><button type='button' class='btn btn-default' name='tagBtn' value='"+labelList[i].tagId+"'>"+labelList[i].tagName+"</button></span>&nbsp;&nbsp;");
                                                    }
                                                }
                                                $("button[name='tagBtn']").on('click', selectLabelClick);
                                            });
                                        }else{
                                            layer.alert(result.message ||"操作失败", {icon: 1,closeBtn:0,title:""});
                                        }
                                    },
                                    error: function (err) {
                                        layer.msg("操作失败", {icon: 5},function () {
                                        });
                                    }
                                });
                            });
                        }
                    });
                },
                error: function(){
                    layer.msg('获取标签信息失败', {icon: 5});
                }
            });
        });

        //弹出的标签框 点击标签事件
        function selectLabelClick(){
            var flag = true, _this = $(this);
            $("#labelDiv input").each(function(e, i){
                if($(this).val() == _this.val()){
                    flag = false;
                    return false;
                }
            });
            if(flag){
                $("#labelDiv").append("<span name='labelSpan' onclick='$(this).remove();'><input name='tagIds' type='hidden' value='"+$(this).val()+"'/><button type='button' class='btn btn-default'>"+$(this).text()+"<i class='glyphicon glyphicon-remove'></i></button></span>");
            }
        }


    });
});