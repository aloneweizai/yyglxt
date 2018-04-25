/**
 * Created by LiuQi
 * 选择商品模块
 */
define(['jquery-3.1.0','layui'],function(){

    /* 点击选择商品按钮 */
    $(".js_selectGoods_input").click(function(e){
        e.preventDefault();

        var init_html = '<table class="ny_tab_t">' +
                    '<tbody>' +
                    '<tr>' +
                    '<td style="text-align:left" width="400">' +
                    '<span><input type="text" value="" id="goodsName_input" placeholder="商品名" style="width:130px;height:30px;"></span>' +
                    '<input type="button" value="查询" id="goods_query" class="layui-btn">' +
                    '</td>' +
                    '</tr>' +
                    '</tbody>' +
                    '</table><div class="js_goods_div">';
        $.ajax({
            type: "GET",
            url: ctx + "/financed/ajaxGoodList.php?status=true&goodsType=6",
            async: false,
            success: function(data){
                var html = '<table class="layui-table" lay-size="sm">' +
                    '<thead class="pn-lthead">'+
                    '<tr><th>商品名称</th><th>销售价格</th><th>赠送积分</th>' +
                    '</tr>' +
                    '</thead>'+
                    '<tbody class="pn-ltbody">';
                $.each(data.dataList, function(i, item){
                    var name= item.name;//商品名称
                    var tradeMethod = item.tradeMethod;
                    var sellingPrice = item.sellingPrice;//销售价格
                    if(tradeMethod == 'RMB'){
                        sellingPrice = sellingPrice+'元';
                    }else{
                        sellingPrice = sellingPrice+'积分';
                    }
                    var giftPoints = item.giftPoints;//赠送积分
                    var tr = '<tr style="cursor:pointer" class="js_selectGoods_btn" data-id="'+item.id+'" data-name="'+name+'">' +
                        '<td>'+name+'</td><td>'+sellingPrice+'</td><td>'+giftPoints+'</td>' +
                        '</tr>';
                    html = html+tr;
                });
                html = html + '</tbody></table>';
                init_html = init_html + html + '</div>';
            },
            error: function(){
                layer.msg('获取商品信息失败', {icon: 5});
            }
        });
        layer.open({
            title:"选择商品",
            type: 1,
            area: ['600px','600px'],
            //offset: '100px',
            fixed: false, //不固定
            content: init_html,
            success:function(){
                $('body').on('click', '#goods_query', function(e){
                    var goodsName = $("#goodsName_input").val();
                    //ajax查询 商品列表
                    $.ajax({
                        type: "GET",
                        url: ctx + "/financed/ajaxGoodList.php?status=true&page=0&size=0&goodsType=6&name="+goodsName,
                        async: false,
                        success: function(data){
                            var html = '<table class="layui-table" lay-size="sm">' +
                                    '<thead class="pn-lthead">'+
                                    '<tr><th>商品名称</th><th>销售价格</th><th>赠送积分</th>' +
                                '</tr>' +
                                    '</thead>'+
                                    '<tbody class="pn-ltbody">';
                            $.each(data.dataList, function(i, item){
                                var name= item.name;//商品名称
                                var tradeMethod = item.tradeMethod;
                                var sellingPrice = item.sellingPrice;//销售价格
                                if(tradeMethod == 'RMB'){
                                    sellingPrice = sellingPrice+'元';
                                }else{
                                    sellingPrice = sellingPrice+'积分';
                                }
                                var giftPoints = item.giftPoints;//赠送积分
                                var tr = '<tr style="cursor:pointer" class="js_selectGoods_btn" data-id="'+item.id+'" data-name="'+name+'">' +
                                    '<td>'+name+'</td><td>'+sellingPrice+'</td><td>'+giftPoints+'</td>' +
                                    '</tr>';
                                html = html+tr;
                            });
                            html = html + '</tbody></table>';
                            $(".js_goods_div").html(html);
                        },
                        error: function(){
                            layer.msg('获取商品信息失败', {icon: 5});
                        }
                    });
                });
                $('body').on('click', '.js_selectGoods_btn', function(e){
                    layer.msg('操作成功', {icon: 1});
                    $(".js_selectGoods_input").val($(this).attr("data-name"));
                    $("[name='goodsId']").val($(this).attr("data-id"));
                });
            }
        });
    });

});