define(function(){

    var parseQueryString = function() {
        var searchStr = location.search;
        if(!searchStr){
            return {};
        }
        var json = {};
        var arr = searchStr.substr(searchStr.indexOf('?') + 1).split('&');
        arr.forEach(function(item) {
            var tmp = item.split('=');
            json[tmp[0]] = tmp[1];
        })
        return json;
    }

    var stringifyQueryString = function(param){
        var queryString = "";
        for(var field in param){
            if (param.hasOwnProperty(field)) { //filter,只输出man的私有属性
                if(queryString){
                    queryString += "&" + field+"="+param[field];
                }else{
                    queryString += "?" +field+"="+param[field];
                }
            };
        }
        return queryString;
    }

    return {
        parseQueryString : parseQueryString,
        stringifyQueryString : stringifyQueryString
    };

});