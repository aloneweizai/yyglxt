require(["../../config"], function () {
    require(["jquery.full", "tagEditor"], function ($) {
        $(function () {
            layui.use('laydate', function () {
                var form = layui.form;
                form.on('checkbox(ids)', function (data) {
                    var username = window.parent.$('#userNames').val();
                    var userId = window.parent.$('#userIds').val();
                    if (data.elem.checked) {
                        _names = [];
                        _userIds = [];
                        if(username !=null&&username!=""){
                            _names =  username.split(",");
                        }
                        if(userId !=null&&userId!=""){
                            _userIds =  userId.split(",");
                        }
                        var _userId = data.value;
                        var _name = data.elem.getAttribute("data-name");
                        if(_names.indexOf(_name) != 0){
                            if (!window.parent.$('#userName').tagExist(_name)) {
                                window.parent.$('#userName').addTag(_name);
                                _names.push(_name);
                                _userIds.push(_userId);
                            }
                        }
                        window.parent.$('#userIds').val(_userIds.join(","));
                        window.parent.$('#userNames').val(_names.join(","));
                    }
                    else {
                        var userNames = username.split(",");
                        var userIds = userId.split(",");
                        var _name = data.elem.getAttribute("data-name");
                        var _userId =  data.value;
                        window.parent.$('#userName').removeTag(_name);
                        userNames.splice(indexOf (_name,userNames),1);
                        userIds.splice(indexOf (_userId,userIds),1);
                        window.parent.$('#userNames').val(userNames.join(","));
                        window.parent.$('#userIds').val(userIds.join(","));
                    }
                });
            })

            var _names = [];
            var _userIds = [];
            $("body").on("click",".nycon_sel_btn",function(){
                var username = window.parent.$('#userNames').val();
                var userId = window.parent.$('#userIds').val();
                if($(this).attr("data-check")=='true'){
                    $(this).attr("data-check",false);
                    var userNames = username.split(",");
                    var userIds = userId.split(",");
                    $.each($(".js_checkbox"),function (){
                        this.checked=false;
                        $(this).next().removeClass("layui-form-checked");
                        var _name = $(this).attr('data-name');
                        var _userId = $(this).val();
                        window.parent.$('#userName').removeTag(_name);
                        userNames.splice(indexOf (_name,userNames),1);
                        userIds.splice(indexOf (_userId,userIds),1);
                    });
                    window.parent.$('#userNames').val(userNames.join(","));
                    window.parent.$('#userIds').val(userIds.join(","));
                }else{
                    _names = [];
                    _userIds = [];
                    if(username !=null&&username!=""){
                        _names =  username.split(",");
                    }
                    if(userId !=null&&userId!=""){
                        _userIds =  userId.split(",");
                    }
                    $(this).attr("data-check",true);
                    $.each($(".js_checkbox"),function (){
                        this.checked="checked";
                        $(this).next().addClass("layui-form-checked");
                        var _userId = $(this).val();
                        var _name = $(this).attr('data-name');
                        if(_names.indexOf(_name) != 0) {
                            if (!window.parent.$('#userName').tagExist(_name)) {
                                window.parent.$('#userName').addTag(_name);
                                _names.push(_name);
                                _userIds.push(_userId);
                            }
                        }
                        window.parent.$('#userIds').val(_userIds.join(","));
                        window.parent.$('#userNames').val(_names.join(","));
                    });
                }
            });

            function indexOf (val,attr) {
                for(var i = 0; i < attr.length; i++){
                    if(attr[i] == val){return i;}
                }
                return -1;
            }

            //查询
            $("#consumer_query").click(function(){
                goPage(-1);
            });
            //每页大小
            $("#consumer_size").keypress(function(e) {
                if(e.which == 13) {
                    goPage(-1);
                }
            });
            //首页
            $("#consumer_first").click(function(){
                goPage(1);
            });
            //前一页
            $("#consumer_up").click(function(){
                goPage(parseInt($("#cupageVal").val())-1);
            });
            //下一页
            $("#consumer_down").click(function(){
                goPage(parseInt($("#cupageVal").val())+1);
            });
            //最后一页
            $("#consumer_last").click(function(){
                goPage(parseInt($("#topageVal").val()));
            });
            //跳转
            $("#consumer_go").keypress(function(e) {
                if(e.which == 13) {
                    goPage(isNaN($("#consumer_go").val())?1:$("#consumer_go").val());
                }
            });
            //跳转匡
            $("#consumer_gogo").click(function(e) {
                goPage(parseInt(isNaN($("#consumer_go").val())?1:$("#consumer_go").val()));
            });

            var goPage= function(index){
                if(isNaN($("#consumer_size").val())
                    ||$("#consumer_size").val()==''){
                    $("#consumer_size").val(10);
                }
                var curtPage=parseInt($("#cupageVal").val());
                var totalPage=parseInt($("#topageVal").val());
                if((index<1||index==curtPage||index>totalPage)&&index!=-1){
                    return;
                }else if(index==-1){
                    index=1;
                }
                $('#cupageVal').val(index);
                abc.block();
                $('#userIds').val(window.parent.$('#userNames').val());
                $('#consumerListForm').submit();
            }

        });
    });
});
