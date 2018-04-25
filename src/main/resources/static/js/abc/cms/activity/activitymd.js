require(["../../../config"], function () {
    require(["jquery.full"], function ($) {

        $("#querymd").on('click',function (){
            var status=$('#status').val();
            var name=$('#name').val();
            var id=$(this).attr("abc-id");
            window.location.href = ctx+"/cms/activity/hd_md_list.php?name=" + name + "&status=" + status+"&id="+id;
        })

        $('#quanxuanmd').click(function (){
            var bool=$(this).is(':checked');
            $('input[name="ids"]').each(function (){
                $(this).prop("checked",bool);
            })
        });


        $('#mddel').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var bool=true;
            var ids=''
            oselectall.each(function() {


                if (this.checked == true) {
                    var status=$(this).attr('status');
                    ids+=this.value+','
                    if(status==1){
                        bool=false;
                    }
                }
            })
            if(!bool){
                layer.msg('选择的项中有已审核项不能删除!', {icon: 5});
                return ;
            }
            var id=$(this).attr("abc-id");
            var status=$('#status').val();
            var name=$('#name').val();
            if(ids==''){
                layer.msg('请选择需要删除的项!', {icon: 5});
            }else{
                layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/activity/batchDel" ,
                            data:{ids:ids},
                            success: function (data){
                                //alert(data.result.code);
                                if(data.result.code=='2000'){
                                    abc.layerAlert(true,data.result.message);
                                    setTimeout(function () {
                                        window.location.href = ctx+"/cms/activity/hd_md_list.php?name=" + name + "&status=" + status+"&id="+id;
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

        $('#jjmdsh').on("click",function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var status='';
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                    var stats=$(this).attr("status");
                    if(stats=='1'){
                        status='true';
                    }
                }
            })

            if(status!=''){
                layer.msg("选择记录中有已审核记录，审核失败！",{icon:2});
                return ;
            }
            var id=$(this).attr("abc-id");
            if(ids==''){
                layer.msg('请选择需要审核的项!', {icon: 5});
            }else{
                layer.open({
                    type: 1,
                    title:"拒绝理由",
                    skin: 'layui-layer-demo', //加上边框
                    area: ['500px', '270px'], //宽高
                    content: '<div class="modal-body"><table class="layui-table" lay-size="sm"><tbody id="jiageTB"><tr><td><textarea id="refuseRson" rows="4" cols="60"></textarea><label style="color:red;">*</label></td></tr></tbody></table></div><div class="modal-footer"><button type="button" class="btn btn-primary" abc-id="'+id+'" data-save="modal">确定</button></div>'
                });
            }
        })

        $(document).on("click",'button[data-save="modal"]',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var status='';
            var userids='';
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                    var stats=$(this).attr("status");
                    var userid=$(this).attr("userid");
                    userids+=userid+",";
                    if(stats=='1'){
                        status='true';
                    }
                }
            })
            var refuseRson=$('#refuseRson').val();
            if(refuseRson==''){
                layer.msg("请输入拒绝理由!",{icon:2});
                return;
            }
            var id=$(this).attr("abc-id");
            if(ids==''){
                layer.msg('请选择需要审核的项!', {icon: 5});
            }else{
                $.ajax({
                    type:'POST',
                    url: ctx+"/cms/activity/batchNoSh" ,
                    data:{ids:ids,text:refuseRson,userids:userids,eventid:id},
                    success: function (data){
                        if(data.result.code=='2000'){
                            //abc.layerAlert(true,data.result.message);
                            layer.msg('拒绝审核成功',{icon:1},function(){
                                layer.closeAll();
                                var mdid_=$('#mdid_').val();
                                window.location.href = ctx+"/cms/activity/hd_md_list.php?id="+mdid_;
                            });
                        }else{
                            layer.msg(data.result.message, {icon: 2});
                        }
                    } ,
                    dataType: "JSON"
                });

            }
        })


        $('#mdmsg').click(function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var status='';
            var userids='';
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                    var stats=$(this).attr("status");
                    var userid=$(this).attr("userid");
                    userids+=userid+",";
                    if(stats=='2'){
                        status='true';
                    }
                }
            })
            var id=$(this).attr("abc-id");
            if(status!=''){
                layer.msg("提醒只能是审核通过的记录！",{icon:2});
                return ;
            }
            if(ids==''){
                layer.msg('请选择需要发送提醒消息的项!', {icon: 5});
            }else{
                layer.confirm('是否发送提醒消息？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/activity/eventMsg" ,
                            data:{userids:userids,eventid:id},
                            success: function (data){
                                if(data.result.code=='2000'){
                                    //abc.layerAlert(true,data.result.message);
                                    layer.msg('消息发送成功',{icon:1},function(){
                                        var mdid_=$('#mdid_').val();
                                        window.location.href = ctx+"/cms/activity/hd_md_list.php?id="+mdid_;
                                    });
                                }else{
                                    layer.msg(data.result.message, {icon: 2});
                                }
                            } ,
                            dataType: "JSON"
                        });
                    }
                );

            }
        });


        $('#mdsh').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            var status='';
            var userids='';
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                    var stats=$(this).attr("status");
                    var userid=$(this).attr("userid");
                    userids+=userid+",";
                    if(stats=='1'){
                        status='true';
                    }
                }
            })
            var id=$(this).attr("abc-id");
            if(status!=''){
                layer.msg("选择记录中有已审核记录，审核失败！",{icon:2});
                return ;
            }
            if(ids==''){
                layer.msg('请选择需要审核的项!', {icon: 5});
            }else{
                layer.confirm('是否审核？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        abc.block();
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/activity/batchSh" ,
                            data:{ids:ids,userids:userids,eventid:id},
                            success: function (data){
                                abc.unblock();
                                if(data.result.code=='2000'){
                                    //abc.layerAlert(true,data.result.message);
                                    layer.msg('审核成功',{icon:1},function(){
                                        var mdid_=$('#mdid_').val();
                                        window.location.href = ctx+"/cms/activity/hd_md_list.php?id="+mdid_;
                                    });
                                }else{
                                    layer.msg(data.result.message, {icon: 2});
                                }
                            } ,
                            dataType: "JSON"
                        });
                    }
                );

            }
        })

        $('#mddc').on('click',function (){
            var status=$('#status').val();
            var name=$('#name').val();
            var abc_id=$(this).attr('abc-id');
            window.location.href = ctx+"/cms/activity/download.php?name=" + name + "&status=" + status+"&id="+abc_id;
        })

        $('#publish_survey').click(function (){
            //window.location.href = ctx+"/cms/activity/list.php";
            //window.location.href = document.referrer;
            window.location.href =sessionStorage.getItem("activity_url");
        })
    });
});