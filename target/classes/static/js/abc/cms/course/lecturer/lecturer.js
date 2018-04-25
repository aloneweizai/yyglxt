/**
 * Created by LiuQi
 * 选择讲师模块
 */

define(['jquery-3.1.0','layui'],function(){

    /* 点击选择讲师按钮 */
    $(".js_select_lecturer").click(function(e){
        e.preventDefault();

        var init_html = '<table class="ny_tab_t">' +
            '<tbody>' +
            '<tr>' +
            '<td style="text-align:left" width="400">' +
            '<span><input type="text" value="" id="lecturerName_input" placeholder="讲师名" style="width:130px;height:30px;"></span>' +
            '<input type="button" value="查询" id="lecturer_query" class="layui-btn">' +
            '</td>' +
            '</tr>' +
            '</tbody>' +
            '</table><div class="js_lecturer_div">';

        $.ajax({
            type: "GET",
            url: ctx + "/cms/course/lecturer/ajaxList.php",
            async: false,
            success: function(data){
                var html = '<table class="layui-table" lay-size="sm">' +
                    '<thead class="pn-lthead">'+
                    '<tr><th>讲师名</th><th>所在单位</th></tr>' +
                    '</thead>'+
                    '<tbody class="pn-ltbody">';
                $.each(data.dataList, function(i, item){
                    var id = item.lecturerId;
                    var name= item.lecturerName;//讲师名称
                    var company = (item.company)?item.company:'' ;//所在单位
                    var tr = '<tr style="cursor:pointer" class="js_select_lecturer_tr" data-id="'+id+'" data-name="'+name+'">' +
                        '<td>'+name+'</td><td>'+company+'</td>' +
                        '</tr>';
                    html = html+tr;
                });
                html = html + '</tbody></table>';
                init_html = init_html + html + '</div>';
            },
            error: function(){
                layer.msg('获取讲师信息失败', {icon: 5});
            }
        });



        layer.open({
            title:"选择讲师",
            type: 1,
            area: ['600px','600px'],
            //offset: '100px',
            fixed: false, //不固定
            content: init_html,
            success:function(){
                $('body').on('click', '#lecturer_query', function(e){
                    var lecturerName = $("#lecturerName_input").val();
                    //if(!lecturerName){
                    //    layer.msg('请输入讲师名', {icon: 5});
                    //    return;
                    //}
                    //ajax查询 讲师列表
                    $.ajax({
                        type: "GET",
                        url: ctx + "/cms/course/lecturer/ajaxList.php?lecturerName="+lecturerName,
                        async: false,
                        success: function(data){
                            var html = '<table class="layui-table" lay-size="sm">' +
                                '<thead class="pn-lthead">'+
                                '<tr><th>讲师名</th><th>所在单位</th></tr>' +
                                '</thead>'+
                                '<tbody class="pn-ltbody">';
                            $.each(data.dataList, function(i, item){
                                var id = item.lecturerId;
                                var name= item.lecturerName;//讲师名称
                                var company = (item.company)?item.company:'' ;//所在单位
                                var tr = '<tr style="cursor:pointer" class="js_select_lecturer_tr" data-id="'+id+'" data-name="'+name+'">' +
                                    '<td>'+name+'</td><td>'+company+'</td>' +
                                    '</tr>';
                                html = html+tr;
                            });
                            html = html + '</tbody></table>';
                            $(".js_lecturer_div").html(html);
                        },
                        error: function(){
                            layer.msg('获取讲师信息失败', {icon: 5});
                        }
                    });
                });
            }
        });
    });

    /* 选择讲师为栏位赋值 */
    $('body').on('click', '.js_select_lecturer_tr', function(e){
        e.preventDefault();
        var flag = true, _this = $(this);
        $("#lecturer_arr_div input").each(function(e, i){
            if($(this).val() == _this.attr("data-id")){
                layer.msg('已添加', {icon: 5,time:1000});
                flag = false;
                return false;
            }
        });
        if(!flag){
            return;
        }
        var lecturerId = _this.attr("data-id");
        var lecturerName = _this.attr("data-name");

        var lecturer_div_html= "<span onclick='$(this).remove();'><input name='lecturerId' type='hidden' value='"+lecturerId+"'>"
            +"<button type='button' class='btn btn-default'>"+lecturerName+"<i class='glyphicon glyphicon-remove'></i></button>";
            +"</span>";
        $("#lecturer_arr_div").append(lecturer_div_html);
        layer.msg("操作成功", {icon: 1});
    });






});