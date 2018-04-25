requirejs.config({
    baseUrl :ctx+"/js/lib",
    paths : {
        "jquery-3.1.0" : "../plugin/jquery-3.1.0",
        "owl.carousel" : "../plugin/owl.carousel",
        "bootstrap" : "../plugin/bootstrap",
        "jquery.validate" : "../plugin/jquery.validate",
        "cropper" : "../plugin/cropper",
        "jquery.easyui.min" : "../plugin/jquery.easyui.min",
        "ztree":"ztree/jquery.ztree.core",
        "ztree.excheck":"ztree/jquery.ztree.excheck",
        "ztree.exedit":"ztree/jquery.ztree.exedit",
        "nicevalidator":"nicevalidator/jquery.validator",
        "nicevalidator.zh_CN":"nicevalidator/zh_CN",
        "layui":"layui/layui.all",
        "abc.common":"../abc/util/common",
        "swfupload.handlers":"swfupload/handlers",
        "swfupload.swfupload":"swfupload/swfupload",
        "abc.ajaxfileupload":"../abc/util/ajaxfileupload",
        "commenttj": "../abc/cms/commenttj/statistics",
        "colpick": "../plugin/colpick",
        "jqplot": "../abc/util/jquery.jqplot",
        "highlighter": "../abc/util/jqplot.highlighter.min",
        "jquery.zoom": "../plugin/jquery.zoom",
        "barRenderer": "../abc/util/jqplot.barRenderer",
        "categoryAxisRenderer": "../abc/util/jqplot.categoryAxisRenderer",
        "dateAxisRenderer": "../plugin/jqplot.dateAxisRenderer",
        "canvasAxisLabelRenderer": "../plugin/jqplot.canvasAxisLabelRenderer",
        "canvasAxisTickRenderer": "../abc/util/jqplot.canvasAxisTickRenderer",
        "pointLabels.min": "../plugin/jqplot.pointLabels.min",
        "canvasTextRenderer": "../plugin/jqplot.canvasTextRenderer",
        "uploadify" : "uploadify/jquery.uploadify.min",
        "blockUI": "../plugin/jquery.blockUI",
        "autocomplete": "autocomplete/jquery.bigautocomplete",
        "tagEditor": "tagEditor/jquery.tagsinput",
        "ueditor": "../plugin/ueditor/ueditor.all",
        "ueditor.config": "../plugin/ueditor/ueditor.config",
        "zeroClipboard" : "../plugin/ueditor/third-party/zeroclipboard/ZeroClipboard",
        "pieRenderer": "../plugin/jqplot.pieRenderer.min",
        "input": "../abc/util/input",
        "clipboard": "../abc/util/clipboard.min"
    },
    waitSeconds: 0,
    shim: {
    	"autocomplete": {
            deps: ["jquery-3.1.0"]
        },
        "owl.carousel": {
            deps: ["jquery-3.1.0"]
        },
        "input": {
            deps: ["jquery-3.1.0"]
        },
        "bootstrap": {
            deps: ["jquery-3.1.0"]
        },
        "ztree": {
            deps: ["jquery-3.1.0"]
        },
        "ztree.excheck": {
            deps: ["jquery-3.1.0", "ztree"]
        },
        "ztree.exedit": {
            deps: ["jquery-3.1.0", "ztree"]
        },
        "nicevalidator": {
            deps: ["jquery-3.1.0"]
        },
        "nicevalidator.zh_CN": {
            deps: ["jquery-3.1.0", "nicevalidator"]
        },
        "layer": {
            deps: ["jquery-3.1.0"]
        },
        "colpick": {
            deps: ["jquery-3.1.0"]
        },
        "abc.common": {
            deps: ["jquery-3.1.0","layui","blockUI"]
        },
        "abc.ajaxfileupload": {
            deps: ["jquery-3.1.0"]
        },
        "jqplot": {
            deps: ["jquery-3.1.0"]
        },
        "highlighter": {
            deps: ["jquery-3.1.0", "jqplot"]
        },
        "uploadify": {
            deps: ["jquery-3.1.0"]
        },
        "jquery.zoom": {
            deps: ["jquery-3.1.0"]
        },
        "barRenderer": {
            deps: ["jquery-3.1.0", "jqplot"]
        },
        "categoryAxisRenderer": {
            deps: ["jquery-3.1.0", "jqplot","barRenderer"]
        },
        "dateAxisRenderer": {
            deps: ["jquery-3.1.0","jqplot"]
        },
        "canvasAxisLabelRenderer": {
            deps: ["jquery-3.1.0","jqplot","canvasTextRenderer"]
        },
        "pointLabels.min": {
            deps: ["jquery-3.1.0","jqplot"]
        },
        "canvasTextRenderer":
        {
            deps: ["jquery-3.1.0", "jqplot"]
        },
        "tagEditor":{
        	deps:["jquery-3.1.0"]
        },
        'ueditor': {
            deps: ["ueditor.config"]
        },
        "canvasAxisTickRenderer": {
            deps: ["jquery-3.1.0","jqplot"]
        },
        "pieRenderer": {
            deps: ["jquery-3.1.0", "jqplot"]
        }
    },
    urlArgs: "v=" + (new Date()).getTime()
});

define("jquery.full",["jquery-3.1.0","blockUI","jquery.easyui.min","bootstrap","ztree", "ztree.excheck","ztree.exedit","nicevalidator","nicevalidator.zh_CN","abc.common","abc.ajaxfileupload","colpick","jquery.zoom","layui"],function($){
    layui.layer.config({
        offset: abc.winscrollTop(100)
    });
    return $;
});

define("swfupload.full", ["swfupload.handlers", "swfupload.swfupload"], function ($) {
    //return $.noConflict(true);
    //return SWFUpload;
    return $;
});



define("ajaxfileupload.full", ["abc.ajaxfileupload"], function ($) {
    //return $.noConflict(true);
    return $;
});

define("highlighter.full", ["highlighter"], function ($) {
    //return $.noConflict(true);
    return $;
});

define("jqplot.full", ["jqplot","barRenderer","categoryAxisRenderer","canvasAxisLabelRenderer","canvasAxisTickRenderer","pointLabels.min","canvasTextRenderer","pieRenderer"], function ($) {
    //return $.noConflict(true);
    return $;
});