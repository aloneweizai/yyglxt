require(["../../../config"], function () {
    require(["jquery.full","../abc/util/pagination","../abc/util/date"], function ($) {
        layui.use('laydate', function() {
            var laydate = layui.laydate;

            //常规用法
            laydate.render({
                elem: '#begintime'
            });
            laydate.render({
                elem: '#endtime'
            });
        })


        $(function (){
            sessionStorage.setItem("activity_url",location.href);
        });

        $("#consumer_more").click(function(){
            if($(this).hasClass('shoqilai')){
                $(this).val('更多条件').removeClass('shoqilai');
                $(".moretjYC").each(function(){
                    $(this).removeClass('moretjYC').addClass('moretj').fadeOut(500);
                });
            }else{
                $(this).val('隐藏条件').addClass('shoqilai');

                $(".moretj").each(function(){
                    $(this).removeClass('moretj').addClass('moretjYC').fadeIn(500);
                });
            }
        });
        var hasYC=false;
        $(".moretj").each(function(){
            $(this).find('select').each(function(){
                if($(this).val()!="" || $(this).val().length>0){
                    hasYC=true;
                }
            });
            $(this).find('input[type="text"]').each(function(){
                if($(this).val()!="" || $(this).val().length>0){
                    hasYC=true;
                }
            });
        });
        if(hasYC){$("#consumer_more").click();};



        $('#query').on("click",function (){
            var title=$('#title').val();
            var status=$('#status').val();
            var category=$('#category').val();
            var begintime=$('#begintime').val();
            var endtime=$('#endtime').val();
            if(typeof(begintime)==="undefined"){
                begintime='';
            }
            if(typeof(endtime)==="undefined"){
                endtime='';
            }
            if(title!=''){
                title=encodeURI(title);
            }
            window.location.href = ctx+"/cms/activity/list.php?title=" + title + "&status=" + status+"&category="+category+"&begintime="+begintime+"&endtime="+endtime;
        })


        $('a[class="pn-opt"]').on('click',function (){
            var delid=$(this).attr('delid');
            var abc_type=$(this).attr('abc-type');
            //alert(1);
            if(abc_type=='delete'){
                layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:"POST",
                            url: ctx+"/cms/activity/"+delid ,
                            success: function (data){
                                //alert(data.result.code);
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        //window.location.href = ctx+"/cms/activity/list.php";
                                        window.location.reload();
                                    }, 2000);
                                }else{
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            } ,
                            dataType: "JSON"
                        });
                    }
                );
            }else if(abc_type=='revoke'){
                layer.confirm('是否撤销发布？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:"POST",
                            url: ctx+"/cms/activity/revoke/"+delid ,
                            success: function (data){
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        //window.location.href = ctx+"/cms/activity/list.php";
                                        window.location.reload();
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
                    type:"POST",
                    url: ctx+"/cms/activity/"+delid ,
                    success: function (data){
                        //alert(data.result.code);
                        if(data.result.code=='2000'){
                            //window.location.href = ctx+"/cms/activity/list.php";
                            window.location.reload();
                        }else{
                            layer.msg(data.result.message, {icon: 5});
                        }
                    } ,
                    dataType: "JSON"
                });
            }
        })


        $('#copy').on('click',function (){
            var copyid=$(this).attr('copyid');
            $.ajax({
                type:"POST",
                url: ctx+"/cms/activity/copy/"+copyid ,
                success: function (data){
                    //alert(data.result.code);
                    if(data.result.code=='2000'){
                        //window.location.href = ctx+"/cms/activity/list.php";
                        window.location.reload();
                    }else{
                        layer.msg(data.result.message, {icon: 5});
                    }
                } ,
                dataType: "JSON"
            });
        })

    });
});