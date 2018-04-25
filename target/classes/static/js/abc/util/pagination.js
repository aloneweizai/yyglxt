define(["jquery.full","../util/utils"],function($,utils) {

        var paginationTable = $("table[name='_pagination_table']");
        var formNode = $("form[name='_pagination_form']");
        formNode.on("submit",function(){
            return false;
        });

        paginationTable.find("input[name='_pagi_first_btn']").on("click", function () {
            $("table[name='_pagination_table'] input[name='page']").val(1);
            var reqParam = $.extend(true,utils.parseQueryString(),JSON.parse(formNode.serializeJson()));
            location.href = location.origin+location.pathname+utils.stringifyQueryString(reqParam);
        });

        paginationTable.find("input[name='_pagi_pre_btn']").on("click", function () {
            var node = paginationTable.find("input[name='page']");
            var curPage = parseInt(node.val());
            if (curPage == 1) {
                layer.msg('当前为第一页');
                return;
            }
            node.val(--curPage);
            var reqParam = $.extend(true,utils.parseQueryString(),JSON.parse(formNode.serializeJson()));
            location.href = location.origin+location.pathname+utils.stringifyQueryString(reqParam);
        });

        paginationTable.find("input[name='_pagi_next_btn']").on("click", function () {
            var curPageNode = paginationTable.find("input[name='page']");
            var totalPageNode = paginationTable.find("input[name='totalPage']");
            var curPage = parseInt(curPageNode.val());
            if (curPage >= parseInt(totalPageNode.val())) {
                layer.msg('当前为最后一页');
                return;
            }
            curPageNode.val(++curPage);
            var reqParam = $.extend(true,utils.parseQueryString(),JSON.parse(formNode.serializeJson()));
            location.href = location.origin+location.pathname+utils.stringifyQueryString(reqParam);
        });

        paginationTable.find("input[name='_pagi_last_btn']").on("click", function () {
            var curPageNode = paginationTable.find("input[name='page']");
            var totalPageNode = paginationTable.find("input[name='totalPage']");
            if (parseInt(curPageNode.val()) == parseInt(totalPageNode.val())) {
                return;
            }
            curPageNode.val(totalPageNode.val());
            var reqParam = $.extend(true,utils.parseQueryString(),JSON.parse(formNode.serializeJson()));
            location.href = location.origin+location.pathname+utils.stringifyQueryString(reqParam);
        });

        paginationTable.find("input[name='_pagi_goto_btn']").on("click", function () {
            var text = paginationTable.find("input[name='_pagi_goto_text']").val();
            var curPageNode = paginationTable.find("input[name='page']");
            if(!$.trim(text)){
                layer.msg("请输入页码",{icon:5});
                return;
            }
            var iText = 0;
            try{
                iText = parseInt(text);
            }catch (e){
                layer.msg("请输入数字页码",{icon:5});
                return;
            }
            var totalPageNode = paginationTable.find("input[name='totalPage']");
            if(iText>parseInt(totalPageNode.val())){
                layer.msg("输入页码不能超过最大页码",{icon:5});
                return;
            }

            curPageNode.val(iText);
            var reqParam = $.extend(true,utils.parseQueryString(),JSON.parse(formNode.serializeJson()));
            location.href = location.origin+location.pathname+utils.stringifyQueryString(reqParam);
        });

    }
);