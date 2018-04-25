<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
        var imgurl = "${imgPth!}";
    </script>
    <style>
        .mdtablethtd input {
            border: 0;
        }

        .mdtablethtd .jia {
            background: #2e9ad0;
            color: #fff;
            width: 22px;
            line-height: 22px;
            margin-right: 5px;
            text-align: center;
            display: inline-block;
            cursor: pointer
        }

        .mdtablethtd .jian {
            background: #d87271;
            color: #fff;
            width: 22px;
            line-height: 22px;
            margin-right: 5px;
            text-align: center;
            display: inline-block;
            cursor: pointer
        }
    </style>
    <!--评论管理修改-->
</head>

<body>
<div id=loading
     style="position:fixed; width:100%; height:100%; z-index:10000;display: block;background-color: black;opacity: 0.4">
    <img src="${ctx}/images/loading1.gif"
         style="position: fixed;width: 100px;height: 100px;left: 50%;top: 50%;margin-left: -50px;margin-top: -50px">
</div>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="notice_add_form" action="" enctype="multipart/form-data" method="post"
          next-url="${ctx}/cszjs/notice/list.php">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" id='productname' name="productname" value="${ProductSpreadRq.productname!}"/>
            <input type="hidden" id='page' name="page" value="${ProductSpreadRq.page!}"/>
            <input type="hidden" id='size' name="size" value="${ProductSpreadRq.size!}"/>
        <#if productSpreadRs??>
            <input type="hidden" id='productSpreadId' name="productSpreadId" value="${productSpreadRs.id!}"/>
            <tr>
                <td width="30%">产品名称</td>
                <td colspan="3">
                    <input type="text" id="name" name="name" class="layui-input" style="width:330px;"
                                       value="${(productSpreadRs.name)!}"
                                       data-rule="required;length[1~50]">
                </td>
            </tr>
            <tr>
                <td width="30%">产品链接</td>
                <td colspan="3">
                    <input type="text" id="url" name="url" class="layui-input" style="width:330px;"
                           value="${(productSpreadRs.url)!}"
                           data-rule="length[3~300]">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table class="mdtable" id="producttable" cellspacing="1" cellpadding="5">
                        <tr>
                            <th width="72%" class="mdtablethtd">图片</th>
                            <th width="20%" class="mdtablethtd">图片描述</th>
                            <th width="8%" class="mdtablethtd">操作</th>
                        </tr>
                        <#if productSpreadRs??&&productSpreadRs.productImgs?? && (productSpreadRs.productImgs?size > 0) >
                            <#list productSpreadRs.productImgs as productImg>
                                <tr did="${productImg_index}">
                                    <td width="72%">
                                        <img id="imgshow" height="126" width="206"
                                             style="margin-left:10px;max-width:206px"
                                             onerror="javascript:this.src='${ctx}/images/default.jpg';"
                                            <#if productImg.imageUrl??>
                                             src='${imgPth!}${(productImg.imageUrl)!}'
                                            <#else>
                                             src='${ctx}/images/default.jpg'
                                            </#if>/>
                                        <input type="file" id="uploadFile${productImg_index}"
                                               name="uploadFile${productImg_index}"
                                               data-type="jpg;jpeg;png;gif;bmp"><label style='color:red;'>*</label>
                                        <button style="height:26px;line-height:13px;" id="upload_btn" type="button"
                                                class="layui-btn">上传
                                        </button>
                                        <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                                        <input type="hidden" id="imageUrl${productImg_index}"
                                               name="imageUrl${productImg_index}" data-rule="required;length[~200]"
                                               value='${(productImg.imageUrl)!}'>
                                        <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出5MB（jpg、jpeg、png、gif、bmp）</span>
                                    </td>
                                    <td width="20%" class="mdtablethtd">
                                        <input type="hidden" style="width: 100px;" class="sizeee"
                                               value="${productSpreadRs.productImgs?size}">
                                        <input type="hidden" id='productId${productImg_index}'
                                               name="productId${productImg_index}" value="${productImg.productId!}"/>
                                        <input type="hidden" id='imgid${productImg_index}'
                                               name="imgid${productImg_index}" value="${productImg.id!}"/>
                                        <textarea name="description${productImg_index}"
                                                  id="description${productImg_index}" maxlength="150"
                                                  style="width: 200px;height: 120px"
                                                  value='${(productImg.description)!}'>${(productImg.description)!}</textarea>
                                    </td>
                                    <td width="8%" class="mdtablethtd">
                                        <span name="jia" class="jia">+</span>
                                        <span class="jian" name="jian">-</span>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                    </table>
                </td>
            </tr>
        <#else>
            <tr>
                <td width="90">产品名称</td>
                <td colspan="3">
                    <input type="text" id="name" name="name" class="layui-input" style="width:330px;"
                                       data-rule="required;length[1~50]"></td>
            </tr>
            <tr>
                <td width="30%">产品链接</td>
                <td colspan="3">
                    <input type="text" id="url" name="url" class="layui-input" style="width:330px;"
                           data-rule="length[3~300]">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table class="mdtable" id="producttable" cellspacing="1" cellpadding="5">
                        <tr>
                            <th width="72%" class="mdtablethtd">图片</th>
                            <th width="20%" class="mdtablethtd">图片描述</th>
                            <th width="8%" class="mdtablethtd">操作</th>
                        </tr>
                        <tr did="0">
                            <td>
                                <img id="imgshow" height="126" width="206" style="margin-left:10px;max-width:206px"
                                     onerror="javascript:this.src='${ctx}/images/default.jpg';"
                                     src='${ctx}/images/default.jpg'/>
                                <input type="file" id="uploadFile0" name="uploadFile0" data-type="jpg;jpeg;png;gif;bmp"><label
                                    style='color:red;'>*</label>
                                <button style="height:26px;line-height:13px;" id="upload_btn" type="button"
                                        class="layui-btn">上传
                                </button>
                                <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                                <input type="hidden" id="imageUrl0" name="imageUrl0" data-rule="required;length[~200]"
                                       value=''>
                                <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出5MB（jpg、jpeg、png、gif、bmp）</span>
                            </td>
                            <td class="mdtablethtd">
                                <input type="hidden" style="width: 100px;" class="sizeee" value="0">
                                <textarea name="description0" id="description0" maxlength="150"
                                          style="width: 200px;height: 120px"></textarea>
                            </td>
                            <td class="mdtablethtd">
                                <span name="jia" class="jia">+</span>
                                <span class="jian" name="jian">-</span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </#if>
            <tr>
                <td colspan="4">
                    <input type="submit" id="submit_btn" status-val="0" value="保存"
                           class="layui-btn">
                    <a style="text-decoration:none;" id="back" class="layui-btn layui-btn-primary">返回</a></td>
                </td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/system/productspread.js" src="${ctx}/js/require.js"></script>
</body>
</html>