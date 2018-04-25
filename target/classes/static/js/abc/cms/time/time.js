/**
 * Created by Administrator on 2017-05-24.
 * 数据字典js
 */
require(["../../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {

        var listurl=ctx+'/cms/time/list.php';

        $('#resetBtn').on('click',function (){
            window.location.href = listurl;
        });
       $( "#tips" ).tooltip();
        $("select[data-type='ajax']").each(function(){
            var this_=$(this);
            var rule=this_.attr('data-rules').split(":");
            var urls=this_.attr('data-url');
            var val=this_.attr('data-val');
            $.ajax({
                type: "GET",
                url: urls,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (data) {
                        $.each(data, function (i, item) {
                            this_.append("<option "+ (eval("item."+rule[0])==val?"selected":"") +" value='"+ eval("item."+rule[0])+"'>" + eval("item."+rule[1]) + "</option>");
                        });
                    } else {
                    }
                }
            });
        });

        $('#batch_del').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var bool=false;
            oselectall.each(function() {

                if (this.checked == true) {
                    var status=$(this).attr("status");
                    ids+=this.value+','
                    if(status==1){
                        bool=true;
                    }
                }

            })
            if(ids==''){
                layer.msg("请选择需要批量删除的项!", {icon: 5});
            }else{
                if(bool){
                    layer.msg("选择的记录中有启用状态记录，删除失败!", {icon: 5});
                    return ;
                }
                layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/time/batchDel.php" ,
                            data:{ids:ids},
                            success: function (data){
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        window.location.href = listurl;
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
                if(abc_type=='delete'){
                    layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajax({
                                type:type,
                                url: url ,
                                data:data,
                                success: function (data){
                                    if(data.result.code=='2000'){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            window.location.href = listurl;
                                        }, 2000);
                                    }else{
                                        layer.msg(data.result.message, {icon: 5});
                                    }
                                } ,
                                dataType: "JSON"
                            });
                        }
                    );
                }else if(abc_type=='qi'){
                    layer.confirm('是否激活？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajax({
                                type:type,
                                url: url ,
                                success: function (data){
                                    if(data.result.code=='2000'){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            window.location.href = listurl;
                                        }, 2000);
                                    }else{
                                        layer.msg(data.result.message, {icon: 5});
                                    }
                                } ,
                                dataType: "JSON"
                            });
                        }
                    );
                }else if(abc_type=='ting'){
                    layer.confirm('是否暂停？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajax({
                                type:type,
                                url: url ,
                                success: function (data){
                                    if(data.result.code=='2000'){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            window.location.href = listurl;
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
                                    window.location.href = listurl;
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


        $('#execy').on('change',function (){
            var status=$(this).val();
            switch(status)
            {
                case "1":
                    $('#fen').html('<input type="text" name="intervalMinute" value="">分钟');
                    break;
                case "2":
                    $('#fen').html('<input type="text" name="intervalHour" value="">小时');
                    break;
                case "3":
                    $('#fen').html('<input type="text" name="hour"value="">时<input type="text" name="minute"value="">分');
                    break;
                case "4":
                    $('#fen').html('<input type="text" name="dayOfWeek"value="">星期<input type="text" name="hour"value="">时<input type="text" name="minute"value="">分');
                    break;
                default:
                    $('#fen').html('<input type="text" name="dayOfMonth"value="">日<input type="text" name="hour"value="">时<input type="text" name="minute"value="">分');
            }
        })

        //数据校验
        var $validatorWsysVoFrom = $("form").validator({
            theme: 'simple_right',
            timely: 1,
            fields: {
                "jobName": "required;",
                "jobGroup":"required;",
                "cronExpression":"required;"
            }
        });
        $validatorWsysVoFrom.validator().trigger("showtip");

        $("#commitBtn").on("click", function () {
            if($validatorWsysVoFrom.isValid()){
                layer.confirm('是否保存？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type: "POST",
                            url: ctx+"/cms/time/save.php",
                            data: $("#codeForm").serializeJson(),
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data.result.code=='2000') {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        window.location.href = listurl;
                                    }, 2000);
                                } else {
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            }
                        });
                    }
                );
            }
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
                })
            }else{
                oselectall.each(function() {
                    this.checked = false;
                })
            }
        })

    });

})

