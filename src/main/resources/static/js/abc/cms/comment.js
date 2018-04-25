/**
 * Created by Administrator on 2017-06-13.
 * 评论管理
 */
require(["../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {
        $("#commitBtn").on("click", function () {
            layer.confirm('是否保存？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                function(index){
                    $.ajax({
                        type: "POST",
                        url: ctx+"/cms/comment/save.php",
                        data: $("#commentForm").serializeJson(),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (data.result.code == '2000') {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                setTimeout(function () {
                                    window.location.href = ctx+"/cms/comment/list.php";
                                }, 2000);
                            } else {
                                layer.msg(data.result.message, {icon: 5});
                            }
                        }
                    });
                }
            );
        })

        $("#queryBtn").on("click", function () {
            window.location.href = ctx+"/cms/comment/list.php?isChecked=" + $("#isChecked option:selected").val() + "&isRecommend=" + $("#isRecommend option:selected").val();
        })

        $('.nycon_sel_btn').on('click',function (){
            var oselectall = $('input[name="ids"]')
            oselectall.each(function() {
                if (this.checked == false) {

                    this.checked = true;
                } else { this.checked = false }
            })
        })

        $('#batch_del').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                }
            })
            if(ids==''){
                layer.msg("请选择需要批量删除的项!", {icon: 5});
            }else{
                layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/comment/batchDel.php" ,
                            data:{ids:ids},
                            success: function (data){
                                //alert(data.result.code);
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        window.location.href = ctx+"/cms/comment/list.php";
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

        $('#batch_review').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var bool=false;
            oselectall.each(function() {
                if (this.checked == true) {
                    var status=$(this).attr('status');
                    ids+=this.value+','
                    if(status==1){
                        bool=true;
                    }
                }
            })
            if(ids==''){
                layer.msg("请选择需要批量审核的项!", {icon: 5});
            }else {
                if(bool){
                    layer.msg("选择的项有已审核，审核失败!", {icon: 5});
                    return;
                }
                layer.confirm('是否审核？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/comment/batchReview.php" ,
                            data:{ids:ids},
                            success: function (data){
                                //alert(data.result.code);
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        window.location.href = ctx+"/cms/comment/list.php";
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


        });

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
                                    //alert(data.result.code);
                                    if(data.result.code=='2000'){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            window.location.href = ctx+"/cms/comment/list.php";
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
                            //alert(data.result.code);
                            if(data.result.code=='2000'){
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                setTimeout(function () {
                                    window.location.href = ctx+"/cms/comment/list.php";
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

        $('input[name="isChecked"]').on('click',function (){
            var isChecked=$(this).val();
            if(isChecked=='1'){
                $('#huifu').show();
            }else{
                $('#huifu').hide();
            }
        })

        $('#reset').on('click',function (){
            $('#reply').val('');
        })

    });
})