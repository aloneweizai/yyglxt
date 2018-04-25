var MyApi = {};
require(["../../config"], function () {
    require(["jquery.full", "qrcode"], function ($, qrCode) {
    var g_button=null;
        $(function(){
            onload();
        });
        function onload(){
            $.post("getmenu.php",function(data){
                if(data.code=="2000"){
                    g_button = data.data.button;
                    loadMenu(g_button);
                    MyApi.foo = function(id){
                        fun1ji_click(id);
                    };
                }else{
                    //console.log("错误:" + data.message);
                }

            });
        }
        var g_ul=null;
        function funLi_click(){
            var li = $(this);
            var ul = li.parent();
            ul.css('display','block');
            g_ul = ul;


            var url = li.data('url');
            var type = li.data('type');
            var name = li.data('name');
            var id = li.data('id');
            var parentId = li.data('parentId');
            var sort = li.data('sort');

            var mkey = li.data('mkey');
            var media_id = li.data('media_id');
            var appid = li.data('appid');
            var pagepath = li.data('pagepath');

            var wxStatus = li.data('wxStatus');

            $("input[name='id']").val(id);
            $("input[name='sort']").val(sort);
            $("input[name='name']").val(name);
            $("textarea[name='url']").val(url);
            $("input:radio").attr('checked',false);
            $("input:radio[value="+ wxStatus + "]").attr('checked',true);

            $("input[name='parentId']").val(parentId);
            $("input[name='mkey']").val(mkey);
            $("input[name='media_id']").val(media_id);
            $("input[name='appid']").val(appid);
            $("input[name='pagepath']").val(pagepath);

            setType(type);

            return false;
//            console.log($(this).data('url'));
        }
        function fun1ji_click(id){
            //1级菜单点击
          //  console.log($(this));
            if(g_ul != null){
                g_ul.css('display','');
                g_ul = null;
            }

            menu1ji(id);
            $("#noDiv").css('display','');
        }
        function setType(type){
            if(type == "view"){
                $("#type").val("view");
                $('#noDiv').attr("class", "noDiv_1");
            }else{
                $("#type").val("0");
                $('#noDiv').attr("class", "noDiv_0");
            }
        }
        $("#type").change(function(){
            setType($(this).val());
         });
        //处理单击1级菜单
        function menu1ji(id){

            if(id){

            }else return false;
            var list = g_button;
            for(var i in list){
                var btn =list[i];
                if(btn.parentId==null||btn.parentId==''){
                    if(btn.id == id){

                        $("input[name='id']").val(btn.id);
                        $("input[name='sort']").val(btn.sort);
                        $("input[name='name']").val(btn.name);
                        $("textarea[name='url']").val(btn.url);
                        $("input:radio").attr('checked',false);
                        $("input:radio[value="+ btn.wxStatus + "]").attr('checked',true);

                        $("input[name='parentId']").val(btn.parentId);
                        $("input[name='mkey']").val(btn.mkey);
                        $("input[name='media_id']").val(btn.media_id);
                        $("input[name='appid']").val(btn.appid);
                        $("input[name='pagepath']").val(btn.pagepath);
                        setType(btn.type);
                        if(btn.type == "view"){
                            $("#menuList ul").css('display','none');

                        }else{
                            $("#menuList ul").css('display','');

                        }
                        var fori =0;
                        for(;fori<5;fori++){
                            $("input[name='erji"+ (fori+1) + "']").val("");
                        }
                        if(btn.sub_button != null){
                            fori =0;
                            for(;fori<btn.sub_button.length;fori++ ){
                                var subbtn = btn.sub_button[fori];

                                $("input[name='erji"+ (fori+1) + "']").val(subbtn.name);

                            }

                        }
                    }
                }

            }
        }
        function loadMenu(list){

            //加载一级菜单
            var menuList = $('#menuList');
            for(var i in list){
                if(list[i].parentId==null||list[i].parentId==''){
                    var template = '<li onclick="MyApi.foo('+list[i].id+');" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"'+
                        ' role="button" aria-haspopup="true" aria-expanded="false">'+list[i].name+'<span class='+
                        '"caret"></span></a><ul class="dropdown-menu"  id="menu'+list[i].id+'"></ul></li>';

                    menuList.append(template);
                   // $('#menu'+list[i].id).click(fun1ji_click);
                    if(list[i].sub_button != null){
                        //加载二级菜单

                        var listc=list[i].sub_button;
                        for(var i in listc){
                            if(listc[i].parentId!=null && listc[i].parentId!=''){
                                var parentEle = $('#menu'+listc[i].parentId);
                                var li = $('<li></li>');
                                li.data('url',listc[i].url);
                                li.data('id',listc[i].id);

                                li.data('name',listc[i].name);
                                li.data('type',listc[i].type);
                                li.data('sort',listc[i].sort);

                                li.data('parentId',listc[i].parentId);
                                li.data('mkey',listc[i].mkey);
                                li.data('media_id',listc[i].media_id);
                                li.data('appid',listc[i].appid);
                                li.data('pagepath',listc[i].pagepath);
                                li.data('wxStatus',listc[i].wxStatus);




                                li.click(funLi_click);
                                var temp = '<a   class="menuBtn">'+listc[i].name+'</a>';
                                temp = li.append(temp);
                                var separetor = '<li role="separator" class="divider"></li>';
                                parentEle.append(temp);
                                if(i != listc.length-1){
                                    parentEle.append(separetor);
                                }
                            }
                        }
                    }

                }
            }

        }
        function editDo(){
            var url = "update_menu.php";
            var data1 = JSON.parse($("#linkForm").serializeJson());

            data1.type = "view";
            data1.sort=parseInt(data1.sort);
            if (data1.sort){

            }else{
                data1.sort=0;
            }
            if(data1.name){

            }else{
                layer.msg("请填写名称！！！", {icon: 5});
                return ;
            }
            if(data1.url){

            }else{
                layer.msg("请填写路径！！！", {icon: 5});
                return ;
            }
            data1=JSON.stringify(data1);

            $.ajax({
                type: "POST",
                url: url,
                data: data1,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    //console.log(data);
                    if (data.code == '2000') {
                        layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                        setTimeout(function () {
                            $('#back').click();

                        }, 2000);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            });
        }

        $('#wxtongbu').click(function () {
            var url = "wxmenu.php";
            var data1 = "";

            $.ajax({
                type: "POST",
                url: url,
                data: data1,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    //console.log(data);
                    if (data.code == '2000') {
                        layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                        setTimeout(function () {


                        }, 2000);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            });
        });
        $('#submit').click(function () {

            if( $("#type").val()=="view"){
                editDo();

            }
            else{

                piliangDo();
            }

        });
        //批量保存的时候
        function piliangDo(){
            var url = "update_plmenu.php";
            var data1 = JSON.parse($("#linkForm").serializeJson());

            data1.type = "0";
            if(data1.parentId){
                layer.msg("子菜单不能继续此操作", {icon: 5});
                return false;
            }
            data1.url="";
            data1=JSON.stringify(data1);

            $.ajax({
                type: "POST",
                url: url,
                data: data1,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    //console.log(data);
                    if (data.code == '2000') {
                        layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                        setTimeout(function () {

                            $('#back').click();
                        }, 2000);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            });

        }

        $('#back').click(function () {
            window.location.reload();
        });



    });
});