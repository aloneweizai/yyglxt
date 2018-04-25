/**
 * Created by Administrator on 2017-05-24.
 * 数据字典js
 */
require(["../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {

        //数据校验
        var $validatorWsysVoFrom = $("form").validator({
            theme: 'simple_right',
            timely: 1,
            fields: {
                "dictId": "required;",
                "dictName": "required;",
                "fieldKey": "required;",
                "fieldValue": "required;"
            }
        });

        $("#commitBtn").on("click", function () {
            if($validatorWsysVoFrom.isValid()){
                layer.confirm('是否保存？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type: "POST",
                            url: ctx+"/system/code/save.php",
                            data: $("#codeForm").serializeJson(),
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data.result.code == '2000') {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        //window.location.href = ctx+"/system/code/list.php";
                                        window.location.href = document.referrer;
                                    }, 2000);
                                } else {
                                    layer.msg("字典编码,值编码已存在", {icon: 5});
                                }
                            }
                        });
                    }
                );
            }
        })



        $("#queryBtn").on("click", function () {
            window.location.href = ctx+"/system/code/list.php?dictName=" + $("#dictName").val() + "&status=" + $("#status").val();
        })


        $('.nycon_sel_btn').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var bool=false;
            oselectall.each(function() {
                if (this.checked == false) {
                    bool=true;
                }
            })
            if(bool){
                oselectall.each(function() {
                    this.checked = true;
                    $(this).next().addClass("layui-form-checked");
                })
            }else{
                oselectall.each(function() {
                    this.checked = false;
                    $(this).next().removeClass("layui-form-checked");
                })
            }
        })

        $('#resetBtn').on('click',function (){
            window.location.href = document.referrer;
        });


        $('#batch_del').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var bool=true;
            oselectall.each(function() {
                if (this.checked == true) {
                    var status=$(this).attr('status');
                   ids+=this.value+','
                    if(status==1){
                       bool=false;
                    }
                }
            })
            if(ids==''){
                layer.msg("请选择需要批量删除的项!", {icon: 5});
            }else{
                if(!bool){
                    layer.msg("选择的记录中有启用记录，删除失败!", {icon: 5});
                    return;
                }
                layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/system/code/batchDel.php" ,
                            data:{ids:ids},
                            success: function (data){
                                //alert(data.result.code);
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        //window.location.href = ctx+"/system/code/list.php";
                                        $("#queryBtn").trigger("click");
                                    }, 2000);
                                }else{
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            } ,
                            dataType: "JSON"
                        });
                    }
                );
            }
        })


        $("a").on('click',function (){
            var data=$(this).attr("data");
            var url=$(this).attr("data-url");
            var type=$(this).attr('type');
            var abc_type=$(this).attr('abc-type');

            if('get'==type||'GET'==type){
                window.location.href=url;
            }else{
                if(typeof(abc_type)!="undefined"){
                    layer.confirm(abc_type, {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajax({
                                type:type,
                                url: url ,
                                data:data,
                                success: function (data){
                                    if(data.result.code=='2000'){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            window.location.reload();
                                            //window.location.href = document.referrer;
                                            //$("#queryBtn").trigger("click");
                                        }, 2000);
                                    }else{
                                        layer.msg(data.result.message, {icon: 5});
                                    }
                                } ,
                                dataType: "JSON"
                            });
                        }
                    );
                }else{
                    $.ajax({
                        type:type,
                        url: url ,
                        data:data,
                        success: function (data){
                            if(data.result.code=='2000'){
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                setTimeout(function () {
                                    //window.location.href = ctx+"/system/code/list.php";
                                    $("#queryBtn").trigger("click");
                                }, 2000);
                            }else{
                                layer.msg(data.result.message, {icon: 5});
                            }
                        } ,
                        dataType: "JSON"
                    });
                }
            }
        });

        //$("#dictName").select2();



    });

})

