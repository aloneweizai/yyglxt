require(["../../../config"], function () {
    require(["jquery.full","wangEditor", "swfupload.full","../abc/util/date"], function ($,Editor) {

        var editor = new Editor("#_topic_description_area");
        editor.customConfig.uploadImgServer = ctx + '/util/wangEditorUpload.php';
        editor.create();

        $(function (){
            $('#huodong_fanhui').attr("href",sessionStorage.getItem("activity_url"));
        })


        $('#query').on("click",function (){
            var title=$('#title').val();
            var status=$('#status').val();
            var category=$('#category').val();
            var begintime=$('#begintime').val();
            var endtime=$('#endtime').val();
            window.location.href = ctx+"/cms/activity/list.php?title=" + title + "&status=" + status+"&category="+category+"&begintime="+begintime+"&endtime="+endtime;
        })
        $(".toupiao-title").click(function(){
            $(this).next().toggle();
            var classjt=$(this).find('i').attr('class');
            if(classjt=="glyphicon glyphicon-menu-up"){
                $(this).find('i').attr("class","glyphicon glyphicon-menu-down")
            }else{
                $(this).find('i').attr("class","glyphicon glyphicon-menu-up")
            }
        })

        $('.FancyForm').on('click',function (){
            if($(".mycard-plus[type='"+$(this).attr('type')+"']").is(":hidden")){
                $(this).find('i').attr('class','glyphicon glyphicon-minus');
                $(".mycard-plus[type='"+$(this).attr('type')+"']").show();
            }else{
                $(this).find('i').attr('class','glyphicon glyphicon-plus');
                $(".mycard-plus[type='"+$(this).attr('type')+"']").hide();
            }
        })
        $('#province').on('change',function (){
            var id=$(this).val();
            if(id!=''){
                $.ajax({
                    type: "GET",
                    url: ctx+"/system/region/city/list.php?pid=" + id,
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            $("#city").find("option:not(:first)").remove();
                            $.each(data, function (i, item) {
                                $("#city").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                            });
                        } else {
                        }
                    }
                });
            }
        })

        $(document).on('click', '.mycard-plus a', function(e) {
            var value=$(this).attr('value');
            var type=$(this).attr('type');
            var title=$(this).attr('title');
            if(type=='tjbq'){
                var tag=$('#tag').val();
                $('#tag').val(tag+value+",");
            }else if(type=='hdfl'){
                var category=$('#category').val();
                if(category==''){
                    $('#category').val(category+value);
                }else{
                    return;
                }
            }else if(type=="hdxs"){
                var shape=$('#shape').val();
                if(shape==''){
                    $('#shape').val(shape+value);
                }else{
                    return;
                }
            }
            var html=$('.plus-tag[type="'+type+'"]').html();
            html+='<a value="'+value+'" title="'+title+'" href="javascript:void(0);" type="'+type+'"><span>'+title+'</span><em></em></a>';
            $('.plus-tag[type="'+type+'"]').html(html);
            $(this).remove();
            var xxhtml=$('.mycard-plus[type="'+type+'"]').html();
            var qxxhtml=$.trim(xxhtml);
            if(qxxhtml===''){
                $(".mycard-plus[type='"+type+"']").hide();
            }
        });

        $(document).on('click', '.plus-tag a', function(e) {
            var value=$(this).attr('value');
            var type=$(this).attr('type');
            var title=$(this).attr('title');
            if(type=='tjbq'){
                var tag=$('#tag').val();
                var t=tag.replace(value+',','');
                $('#tag').val(t)
            }else if(type=='hdfl'){
                $('#category').val('')
            }else if(type=="hdxs"){
                $('#shape').val('')
            }
            var html=$('.mycard-plus[type="'+type+'"]').html();
            html+='<a value="'+value+'" title="'+title+'" href="javascript:void(0);" type="'+type+'"><span>'+title+'</span><em></em></a>';
            $('.mycard-plus[type="'+type+'"]').html(html);
            $(this).remove();
            var xxhtml=$('.mycard-plus[type="'+type+'"]').html();
            var qxxhtml=$.trim(xxhtml);
            if(qxxhtml!==''){
                $(".mycard-plus[type='"+type+"']").show();
            }
        });

        $('.shoujixinxi a').on("click",function (){
            var bools=true
            var value=$(this).attr("value");
            var title=$(this).attr("title");
            var th="";
            var html=$('#table-zy').html();
            if($.trim(html)==''){
                th="报名用户必填资料";
            }else{
                var answer_q=$('.survey_form_checkbox[name="fields"]');
                for(var i=0;i<answer_q.length;i++){
                    var chvalue=$(answer_q[i]).attr("value");
                    if(chvalue.indexOf(value)>-1){
                        bools=false;
                        break;
                    }
                }
            }
            if(bools){
                var addhtml='<tr id="tr_'+value+'">';
                addhtml+='<th>'+th+'</th>';
                addhtml+='<td width="20%">';
                addhtml+='   <div class="option_item" style="width: 100%; margin: 0;"  >';
                addhtml+='   <input class="survey_form_checkbox" type="checkbox" lay-skin="primary" name="fields"data-oid="o-100-ABCD" data-exclusive="0" id="'+value+'" value="'+value+','+title+'">';
                addhtml+='    <label for="'+value+'" style="min-height: 0; height: 26px; width: auto; float: left;">';
                addhtml+='    <i class="survey_form_ui"></i>';
                addhtml+='   <span class="option_text" style="display: initial;">';
                addhtml+='   '+title+'';
                addhtml+='   </span>';
                addhtml+='   </label>';
                addhtml+='<em class="sucvey" abc-id="tr_'+value+'"></em>';
                addhtml+='   </div>';
                addhtml+='   </td>';
                addhtml+='  </tr>';
                $('#table-zy').append(addhtml);
            }else{
                layer.msg('不能重复添加 ', {offset: abc.winscrollTop(200),icon: 5});
            }

        })

        $("#querymd").on('click',function (){
            var status=$('#status').val();
            var name=$('#name').val();
            window.location.href = ctx+"/cms/activity/hd_md_list.php?name=" + name + "&status=" + status;
        })

        $('#mddel').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                }
            })
            if(ids==''){
                layer.msg('请选择需要删除的项 ', {offset: abc.winscrollTop(200),icon: 5});
            }else{
                $.ajax({
                    type: "POST",
                    url: ctx+"/cms/activity/batchDel" ,
                    data:{ids:ids},
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(data.result.code=='2000'){
                            window.location.href = ctx+"/cms/activity/hd_md_list.php";
                        }else{
                            layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
                        }
                    }
                });
            }
        })

        //数据校验
        var $validatorWsysVoFrom = $("form").validator({
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                zzs: [/^\d*$/, '请输入大于0的5位正整数'],
                dy0:function (element){
                    var v2=element.value;
                    if(v2<=0){
                        return "请输入大于0的5位正整数";
                    }else{
                        return true;
                    }
                },
                tests:function (element,begintime){
                    var v1=$("#begintime").val();
                    var v2=element.value;
                    v1 = v1.substring(0,19);
                    v1 = v1.replace(/-/g,'/');
                    v2 = v2.substring(0,19);
                    v2 = v2.replace(/-/g,'/');
                    var timestamp = new Date(v1).getTime();
                    var timestamp2 = new Date(v2).getTime();
                    return timestamp2>=timestamp?true:'结束时间必须大于等于开始时间';
                },
                bmjzsj:function (element){
                    var v1=$('#endtime').val();
                    var v2=element.value;
                    v1 = v1.substring(0,19);
                    v1 = v1.replace(/-/g,'/');
                    v2 = v2.substring(0,19);
                    v2 = v2.replace(/-/g,'/');
                    var timestamp = new Date(v1).getTime();
                    var timestamp3 = new Date().getTime();
                    var timestamp2 = new Date(v2).getTime();
                    if(timestamp3<=timestamp2){
                        return timestamp2<timestamp?true:'报名截止时间必须小于结束时间';
                    }else{
                        return '报名截止时间必须大于当前时间';
                    }

                },
                kssjdydq:function (element){
                    var v2=element.value;
                    v2 = v2.substring(0,19);
                    v2 = v2.replace(/-/g,'/');
                    var timestamp = new Date().getTime();
                    var timestamp2 = new Date(v2).getTime();
                    return timestamp2>=timestamp?true:'开始时间必须大于当前时间';
                }
            },
            fields: {
                "title": {
                    rule:"标题:required"
                },
                "province": {
                    rule:"省份:required"
                },
                "city":{
                    rule:"市:required"
                },
                "address": {
                    rule:"详细地址:required"
                },
                "peoppleNum": {
                    rule:"参与人数:required;zzs;dy0;length[1~5];"
                },
                "begintime":{
                    rule:"开始时间:required;kssjdydq;"
                },
                "endtime":{
                    rule:"结束时间:required;tests(#begintime)"
                },
                "bmendtime":{
                   rule:"报名截止时间:required;bmjzsj"
                }
            }
        });



       // $validatorWsysVoFrom.validator().trigger("showtip");

        $('#editor_cancel_btn_bc').on('click',function (){
            var description=editor.txt.html();
            $('#description').val(description);
            var oselectall = $('input[name="fields"]')
            var ids=''
            var field='';
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+'*'
                }
                field+=this.value+'#';
            })
            $('#fieldsf').val(ids);
            $('#field').val(field);
            var zfb=$('input[name="zbfids"]');
            var zbfids='';
            var count=0;
            var index=0;
            $('#status').val(1);
            var test=$('input[name="test"]');
            var test1=$('input[name="test1"]');
            test.each(function() {
                if (this.checked == true) {
                    $('#isCheck').val(1);
                }else{
                    $('#isCheck').val(0);
                }
            })
            test1.each(function() {
                if (this.checked == true) {
                    $('#isUserGrade').val(1);
                }else{
                    $('#isUserGrade').val(0);
                }
            })

            if($validatorWsysVoFrom.isValid()){
                        zfb.each(function() {
                            if (this.checked == true) {
                                zbfids=index;
                                count++;
                            }
                            index++;
                        })

                $('#zfbindex').val(zbfids);
                        if($('#category').val()==''){
                            layer.msg("请选择活动分类!", {offset: abc.winscrollTop(200),icon: 5});
                            return;
                        }
                        if($('#shape').val()==''){
                            layer.msg("请选择活动形式!", {offset: abc.winscrollTop(200),icon: 5});
                            return;
                        }
                        if($('#summary').val().length>500){
                            layer.msg("摘要不能超过500个字符!", {offset: abc.winscrollTop(200),icon: 5});
                            return;
                        }
                        if(!zbf_vofrom()){
                             return ;
                        }
                        if(count!=1){
                            $('#huodongsz').attr("style","");
                            $('#huodongsztb').attr("class","glyphicon glyphicon-menu-up");
                            layer.msg("请选择主办方!", {offset: abc.winscrollTop(200),icon: 5});
                            return;
                        }

                layer.confirm('是否保存？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: winscrollTop(200),closeBtn: 0},
                    function(index){
                        $.ajax({
                            type: "POST",
                            url: ctx+"/cms/activity/save.php",
                            data: $('#hd').serializeJson(),
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data.result.code == '2000') {
                                    // abc.layerAlert(true,data.result.message);
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        window.location.href =sessionStorage.getItem("activity_url");
                                        //window.location.href = document.referrer;
                                    }, 2000);
                                } else {
                                    layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
                                }
                            }
                        });
                    }
                );

            }

        })

        function zbf_vofrom() {
            var bool=true;
            var type=['name','tel','lxr','email'];
            var msg=['主办方名称不能为空','手机号码不能为空','联系人不能为空','邮箱不能为空'];
            for(var i=0;i<type.length;i++){
                var zbf=$('input[abc-type="'+type[i]+'"]');
                zbf.each(function() {
                    var value=$(this).val();
                    var abc_id=$(this).attr('abc-id');
                    if(value==''){
                        document.getElementById(abc_id).innerHTML='<span class="msg-wrap n-error" role="alert"><span class="n-icon"></span><span class="n-msg">'+msg[i]+'</span></span>';
                        bool=false;
                    }else{
                        if(type[i]=='tel'){
                            var reg=/^1[3|4|5|7|8][0-9]{9}$/;
                            if(!reg.test(value)){
                                document.getElementById(abc_id).innerHTML='<span class="msg-wrap n-error" role="alert"><span class="n-icon"></span><span class="n-msg">手机号不正确!</span></span>';
                                bool=false;
                            }else{
                                document.getElementById(abc_id).innerHTML='';
                            }
                        }else if(type[i]=='email'){
                            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
                            if(!reg.test(value)){
                                document.getElementById(abc_id).innerHTML='<span class="msg-wrap n-error" role="alert"><span class="n-icon"></span><span class="n-msg">邮箱不正确!</span></span>';
                                bool=false;
                            }else{
                                document.getElementById(abc_id).innerHTML='';
                            }
                        }else{
                            document.getElementById(abc_id).innerHTML='';
                        }
                    }
                })
            }
            return bool;
        }
        
        function winscrollTop(offset){
            return window.parent?($(window.parent.document).scrollTop()+offset)+'px':
                ($(document).scrollTop()+offset)+'px';
        }


        $('#editor_confirm_btn_fb').on('click',function (){
            var description=editor.txt.html();
            $('#description').val(description);
            var oselectall = $('input[name="fields"]')
            var ids=''
            var field='';
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+'*'
                }
                field+=this.value+'#';
            })
            $('#fieldsf').val(ids);
            $('#field').val(field);
            var zfb=$('input[name="zbfids"]');
            var zbfids='';
            var count=0;
            var index=0;

            $('#status').val(2);
            var test=$('input[name="test"]');
            var test1=$('input[name="test1"]');
            test.each(function() {
                if (this.checked == true) {
                    $('#isCheck').val(1);
                }else{
                    $('#isCheck').val(0);
                }
            })
            test1.each(function() {
                if (this.checked == true) {
                    $('#isUserGrade').val(1);
                }else{
                    $('#isUserGrade').val(0);
                }
            })
            if($validatorWsysVoFrom.isValid()) {
                zfb.each(function() {
                    if (this.checked == true) {
                        zbfids=index;
                        count++;
                    }
                    index++;
                })
                if($('#category').val()==''){
                    layer.msg("请选择活动分类!", {offset: abc.winscrollTop(200),icon: 5});
                    return;
                }
                if($('#shape').val()==''){
                    layer.msg("请选择活动形式!", {offset: abc.winscrollTop(200),icon: 5});
                    return;
                }
                if($('#summary').val().length>500){
                    layer.msg("摘要不能超过500个字符!", {offset: abc.winscrollTop(200),icon: 5});
                    return;
                }
                if(count!=1){
                    $('#huodongsz').attr("style","");
                    $('#huodongsztb').attr("class","glyphicon glyphicon-menu-up");
                    layer.msg("请选择主办方!", {offset: abc.winscrollTop(200),icon: 5});
                    return;
                }
                $('#zfbindex').val(zbfids);

                layer.confirm('是否发布？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: winscrollTop(200),closeBtn: 0},
                    function(index){
                        $.ajax({
                            type: "POST",
                            url: ctx+"/cms/activity/save.php",
                            data: $('#hd').serializeJson(),
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data.result.code == '2000') {
                                    // abc.layerAlert(true,data.result.message);
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        window.location.href =sessionStorage.getItem("activity_url");
                                        //window.location.href = document.referrer;
                                    }, 2000);
                                } else {
                                    layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
                                }
                            }
                        });
                    }
                );
            }
        })

        $('#publish_survey').click(function (){
            //window.location.href = ctx+"/cms/activity/list.php";
            //window.location.href = document.referrer;
            window.location.href =sessionStorage.getItem("activity_url");
        });
        $('#reset').click(function (){
            //window.location.href = ctx+"/cms/activity/list.php";
            window.location.href = document.referrer;
        });

        // $('#filepath').on('click',function (){
        //     $("#filep").trigger("click");
        // })
        $('.filediv').on('change','.filesz',function (){
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            var $img = $("#filepath");

            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                $img.attr('src', dataURL);
            } else {
                dataURL = $file.val();
                var imgObj = document.getElementById("preview");
                // 两个坑:
                // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
                // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
                imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

            }
        })

        $('#shangchuan').on('click',function (){
            var file=$("#filep").val();
            if(file==''){
                layer.msg("请选择需要上传的海报!", {offset: abc.winscrollTop(200),icon: 5});
                return ;
            }
            $.ajaxFileUpload({
                url: ctx+"/cms/activity/upload.php?path=event",
                secureuri: false,
                fileElementId: 'filep',//file标签的id
                dataType: 'json',
                data: { },
                success: function (data) {
                    if(data.code=='200'){
                        $('#picture').val(data.message);
                        var files = $("#filep")
                        files.after(files.clone().val(""));
                        files.remove();
                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                    }else{
                        layer.msg(data.message, {offset: abc.winscrollTop(200),icon: 5});
                    }
                }
            });
        })
        $('.mdtable').on("click",".jia",function(){
            var trs=$('.mdtable tr');
            var ran=Math.random();
            var html= '<tr>';
            html+= '<td class="mdtablethtd">';
            html+='<input type="radio" name="zbfids" id="option_q-2-Tdr0_o-100-ABCD0'+trs.length+'" value="">';
            html+= '</td>';
            html+= '<td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorName" value="" abc-type="name" abc-id="name_'+ran+'"><div style="color: red;" id="name_'+ran+'"></div><input type="hidden" style="width: 100px;" name="sponsorName" value=""><input type="hidden" style="width: 100px;" name="sponsorId" value=" "><input type="hidden" style="width: 100px;" name="sponsorId" value=" "></td>';
            html+= '<td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorLxr" value="" abc-type="lxr" abc-id="lxr_'+ran+'"><div style="color: red;" id="lxr_'+ran+'"></div><input type="hidden" style="width: 100px;" name="sponsorLxr" value=""></td>';
            html+= '<td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorTel" value="" abc-type="tel" abc-id="tel_'+ran+'"><div style="color: red;" id="tel_'+ran+'"></div><input type="hidden" style="width: 100px;" name="sponsorTel" value=""></td>';
            html+= '<td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorEmail" value="" abc-type="email" abc-id="email_'+ran+'"><div style="color: red;" id="email_'+ran+'"></div><input type="hidden" style="width: 100px;" name="sponsorEmail" value=""></td>';
            html+= '<td class="mdtablethtd"><textarea name="sponsorIntro">&nbsp;</textarea><input type="hidden" style="width: 100px;" name="sponsorIntro" value=" "></td>';
            html+= '<td class="mdtablethtd"><span  name="jia" class="jia">+</span><span class="jian"  name="jian" delid="">-</span></td>';
            html+= '</tr>';
            $('.mdtable').append(html);
        })

        $('.mdtable').on("click",".jian",function(){

            var tr=$(this).parent().parent();
            var trs=$('.mdtable tr');
            if(trs.length<=2){
                layer.msg("已经是最少行不能删除!", {offset: abc.winscrollTop(200),icon: 5});
            }else{
                var delid=$(this).attr('delid');
                if(delid==''){
                    tr.remove();
                }else{
                    layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset:winscrollTop(200),closeBtn: 0},
                        function(index) {
                            $.ajax({
                                type: "POST",
                                url: ctx+"/cms/activity/sponsor/"+delid ,
                                async: false,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    if(data.result.code=='2000'){
                                        tr.remove();
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    }else{
                                        layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
                                    }
                                }
                            });
                        }
                    );
                }
            }
        })

        $("input[data-type='date']").datebox({
            editable:false,
            formatter:function(date){
                var y = date.getFullYear();
                var m = date.getMonth()+1;
                var d = date.getDate();
                var h = date.getHours();
                var M = date.getMinutes();
                var s = date.getSeconds();
                function formatNumber(value){
                    return (value < 10 ? '0' : '') + value;
                }
                return y+'-'+formatNumber(m)+'-'+formatNumber(d);
            },
            parser:function(s){
                var t = Date.parse(s);
                if (!isNaN(t)){
                    return new Date(t);
                } else {
                    return new Date();
                }
            }
        });






        var description=$('#description').val();
        editor.txt.html(description);


        $('#tongji').on('click',function (){
            var eventId=$('#eventId').val();
            if(eventId==''){
                layer.msg('请先保存！', {offset: abc.winscrollTop(200),icon: 5});
            }else{
                window.location.href=ctx+'/cms/activity/tj/'+eventId;
            }
        })

        $('#tuiguang').on('click',function (){
            var eventId=$('#eventId').val();
            if(eventId==''){
                layer.msg('请先保存！', {offset: abc.winscrollTop(200),icon: 5});
            }else{
                window.location.href=ctx+'/cms/activity/tg/'+eventId;
            }
        })

        $('#hdmd_list').on('click',function (){
            var eventId=$('#eventId').val();
            if(eventId==''){
                layer.msg('请先保存！', {offset: abc.winscrollTop(200),icon: 5});
            }else{
                window.location.href=ctx+'/cms/activity/hd_md_list.php?id='+eventId;
            }
        })

        $('#editor_cancel_btn_yl').on('click',function (){
            layer.msg('暂不支持预览功能', {offset: abc.winscrollTop(200),icon: 5});
        })

        $(document).on('click',"em[class='sucvey']",function (){
            var abc_id=$(this).attr('abc-id');
            $('#'+abc_id).remove();
        })

    });
});