require(["../../config"], function () {
    require(["jquery.full", "../abc/util/date"], function ($) {
        var productImgs = [];
        layui.use('laydate', function () {
            var upload = layui.upload;
            //多图片上传
            upload.render({
                elem: '#test2'
                , url: ctx + '/util/doFileupload.php?path=vipgift'
                , multiple: true
                , before: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
                    });
                }
                , done: function (res) {
                    var image = {};
                    image["imageUrl"] = res.message;
                    productImgs.push(image);
                    //上传完毕
                }
            });
        });
        $(function () {
            //查询
            $("#consumer_query").click(function () {
                goPage(-1);
            });
            //每页大小
            $("#consumer_size").keypress(function (e) {
                if (e.which == 13) {
                    goPage(-1);
                }
            });
            //首页
            $("#consumer_first").click(function () {
                goPage(1);
            });
            //前一页
            $("#consumer_up").click(function () {
                goPage(parseInt($("#cupageVal").val()) - 1);
            });
            //下一页
            $("#consumer_down").click(function () {
                goPage(parseInt($("#cupageVal").val()) + 1);
            });
            //最后一页
            $("#consumer_last").click(function () {
                goPage(parseInt($("#topageVal").val()));
            });
            //跳转
            $("#consumer_go").keypress(function (e) {
                if (e.which == 13) {
                    goPage(isNaN($("#consumer_go").val()) ? 1 : $("#consumer_go").val());
                }
            });
            //跳转匡
            $("#consumer_gogo").click(function (e) {
                goPage(parseInt(isNaN($("#consumer_go").val()) ? 1 : $("#consumer_go").val()));
            });


            //a删除
            $("a[data-type='delSig']").click(function () {
                //abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val());
                abc.ajaxConfirm("POST", encodeURI($(this).attr("data-href")), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
            });

            $("[data-dismiss='lookModal']").click(function () {
                $("#myModal3").fadeOut();
            });

            var goPage = function (index) {
                if (isNaN($("#consumer_size").val())
                    || $("#consumer_size").val() == '') {
                    $("#consumer_size").val(10);
                }
                var curtPage = parseInt($("#cupageVal").val());
                var totalPage = parseInt($("#topageVal").val());
                if ((index < 1 || index == curtPage || index > totalPage) && index != -1) {
                    return;
                } else if (index == -1) {
                    index = 1;
                }
                $('#cupageVal').val(index);
                abc.block();
                $('#consumerListForm').submit();
            }

            $("#submit_btn").click(function () {
                if ($validatorWsysVoFrom.isValid()) {
                    var tableEl = document.getElementById("producttable");
                    var rows = tableEl.rows;
                    for (var i = 1; i < rows.length; i++) {
                        var index = rows[i].getAttribute("did");
                        var image = {};
                        image["imageUrl"] = document.getElementById("imageUrl" + index).value;
                        image["description"] = document.getElementById("description" + index).value;
                        var productId = document.getElementById("productId" + index);
                        if (productId != null) {
                            image["productId"] = productId.value;
                        }
                        var imgid = document.getElementById("imgid" + index);
                        if (imgid != null && imgid != "") {
                            image["id"] = imgid.value;
                        }
                        productImgs.push(image);
                    }
                    var productspread = {};
                    productspread["productImgs"] = productImgs;
                    productspread["name"] = $('#name').val();
                    productspread["url"] = $('#url').val();
                    if ($('#productSpreadId').val() != null && $('#productSpreadId').val() != "") {
                        productspread["id"] = $('#productSpreadId').val();
                    }
                    layer.confirm('确认操作？', {
                            title: '操作提示',
                            btn: ['确认', '取消'],
                            icon: 3,
                            offset: abc.winscrollTop(200),
                            closeBtn: 0,
                            zIndex: 90000
                        },
                        function () {
                            abc.block();
                            $.ajax({
                                type: "POST",
                                url: ctx + "/system/productspread/save.php",
                                data: JSON.stringify(productspread),
                                async: true,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    abc.unblock();
                                    if (data && data.code == "2000") {
                                        layer.msg("操作成功", {
                                            offset: abc.winscrollTop(200),
                                            shade: 0.3,
                                            icon: 1,
                                            time: 1000
                                        }, function () {
                                            window.location.href = ctx + "/system/productspread/list.php?productname=" + $('#productname').val() + "&page=" + $('#page').val() + "&size=" + $('#size').val();
                                        });
                                        setTimeout(function () {
                                            window.location.href = ctx + "/system/productspread/list.php?productname=" + $('#productname').val() + "&page=" + $('#page').val() + "&size=" + $('#size').val();
                                        }, 2000);
                                    } else {
                                        layer.alert(data.message || "操作失败", {icon: 5});
                                    }
                                }
                            });
                        }
                    );
                }
            });
            var ran = 0;
            if ($('.sizeee').val() != 0) {
                ran = parseInt($('.sizeee').val()) - 1;
            }
            else {
                ran = parseInt($('.sizeee').val());
            }
            $('.mdtable').on("click", ".jia", function () {
                ran += 1;
                var trs = $('<tr did="' + ran + '"></tr>');
                var body = $('<td class="mdtablethtd"></td>');
                var img = $('<img id="imgshow' + ran + '" height="126" width="206" style="margin-left:10px;max-width:206px"  src="' + ctx + '/images/default.jpg" onerror="javascript:this.src=\'' + ctx + '/images/default.jpg\';"/>');
                var file = $('<input type="file" name="uploadFile' + ran + '" data-type="jpg;jpeg;png;gif;bmp" id="uploadFile' + ran + '">');
                var button = $('<button style="height:26px;line-height:13px;" type="button" class="layui-btn upload_btn" data-id="' + ran + '" id="upload_btn_' + ran + '">上传</button>').click(function () {
                    updat($(this));
                });
                var message = $('<label class="uploadMsg" style="color:red" id="uploadMsg' + ran + '"></label><input type="hidden" name="imageUrl"  data-rule="required;length[~200]" id="imageUrl' + ran + '"><span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出5MB（jpg、jpeg、png、gif、bmp）</span>');
                body.append(img);
                body.append(file);
                body.append(button);
                body.append(message);
                var body1 = $('<td class="mdtablethtd"><textarea name="description" maxlength="150" style="width: 200px;height: 120px" id="description' + ran + '"></textarea></td>');
                var body2 = $('<td class="mdtablethtd"><span  name="jia" class="jia">+</span><span class="jian"  name="jian" delid="">-</span></td>');
                trs.append(body);
                trs.append(body1);
                trs.append(body2);
                $('.mdtable').append(trs);
            })

            $('.mdtable').on("click", ".jian", function () {
                var tr = $(this).parent().parent();
                var trs = $('.mdtable tr');
                if (trs.length <= 2) {
                    layer.msg("已经是最少行不能删除!", {offset: abc.winscrollTop(200), icon: 5});
                } else {
                    var delid = $(this).attr('delid');
                    tr.remove();
                }
            })

            function updat(obj) {
                var _o = obj.parent().find("input[type='file']");
                var fileid = _o.attr('name');
                var _val = _o.val();
                if (_val == "") {
                    abc.layerAlert(false, '请选择上传图片');
                    return;
                }
                var size = _o[0].files[0].size / 1024 / 1024;
                if (size > 5) {
                    abc.layerAlert(false, '上传图片超过5MB!');
                    return;
                }
                var types = _o.attr('data-type').split(';');
                var type = _val.substring(_val.lastIndexOf(".") + 1);
                obj.hide();
                obj.parent().find(".uploadMsg").html('正在上传.....');

                if (types.indexOf(type) < 0) {
                    abc.layerAlert(false, '允许类型:[' + _o.attr('data-type') + "]");
                    obj.show();
                    obj.parent().find(".uploadMsg").html('');
                    return;
                }
                $.ajaxFileUpload({
                    url: ctx + '/util/doFileupload.php?path=vipgift',
                    type: 'post',
                    secureuri: false, // 一般设置为false
                    fileElementId: fileid, // 上传文件的id、name属性名
                    dataType: 'application/json', // 返回值类型，一般设置为json、application/json
                    success: function (data, status) {
                        obj.show();
                        obj.parent().find(".uploadMsg").html('');
                        if (data.code == '200') {
                            obj.parent().children().eq(0).attr('src', imgurl + data.message);
                            obj.parent().find("input[type='hidden']").val(data.message).blur();
                        } else {
                            abc.layerAlert(false, data.message);
                        }
                    },
                    error: function (data, status, e) {
                        alert(e);
                    }
                });
            }

            $("[id='upload_btn']").click(function () {
                updat($(this));
            });

            $("[id='back']").click(function () {
                window.location.href = ctx + "/system/productspread/list.php?productname=" + $('#productname').val() + "&page=" + $('#page').val() + "&size=" + $('#size').val();
            });
            $("a[data-type='opendialog']").click(function () {
                $("#myModal1").fadeIn();
            });
            $("[data-dismiss='modal']").click(function () {
                $("#myModal1").fadeOut();
            });

            $("a[data-type='opendialog']").click(function () {
                abc.block();
                var url = $(this).attr("data-url");
                var iframe = document.getElementById("consumer_frame");
                iframe.src = url;
                if (iframe.attachEvent) {
                    iframe.attachEvent("onload", function () {
                        //$("#myModal3").fadeIn();
                        $("#myModal3").show();
                        $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    });
                } else {
                    iframe.onload = function () {
                        $("#myModal3").show();
                        $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    };
                }

            });

            $("button[data-dismi]").click(function () {
                //$("#myModal3").fadeOut();
                $("#myModal3").find(".modal-dialog").animate({'top': '-700px'}, 500, function () {
                    $("#myModal3").hide();
                });
            });

            $(".showbig").click(function () {
                var _this = $(this);
                var _shoimg = $("#shoimg");
                _shoimg.find('img').attr('src', _this.attr('src'));
                var scrol = $(document).scrollTop();
                _shoimg.css({
                    width: _this.width(),
                    height: _this.height(),
                    top: _this.offset().top - scrol,
                    left: _this.offset().left,
                    marginLeft: '0px'
                }).show();
                _shoimg.animate({
                    left: '50%',
                    top: abc.winscrollTop(50),
                    width: '800px',
                    height: '600px',
                    marginLeft: '-400px'
                }, 500);
            });

            $("#shoimg").click(function () {
                $(this).hide(500);
            });

            var $validatorWsysVoFrom = $("form").validator({
                timely: 1,
                focusCleanup: true,
                theme: 'yellow_right_effect',
                rules: {
                    allint: [/^(-|\+)?\d+$/, '请输入整数']
                }
            });
            $validatorWsysVoFrom.validator().trigger("showtip");
            $("#consumer_submit").click(function () {
                if ($validatorWsysVoFrom.isValid()) {
                    abc.layerAjaxConfirm("POST", $("form").attr('action'), $("form").serializeJson(), document.referrer);
                }
            });

            /**
             * 根据图片生成画布
             */
            function convertImageToCanvas(image) {
                var canvas = document.createElement("canvas");
                canvas.width = image.width;
                canvas.height = image.height;
                canvas.getContext("2d").drawImage(image, 0, 0);
                return canvas;
            }

            $("#xiazaierweima").click(function () {
                var sampleImage = document.getElementById("weixin_ewm"),
                    canvas = convertImageToCanvas(sampleImage);
                url = canvas.toDataURL("image/png");//PNG格式
                //以下代码为下载此图片功能
                var triggerDownload = $("#tttt").attr("href", url).attr("download", "推广二维码.png");
                triggerDownload[0].click();
            })
            $("#myspread").click(function () {
                var url = $("#productSpreadId option:selected").attr("url");
                if (url == null || url == "") {
                    abc.layerAlert(false, '选择的产品没有链接地址，必须先维护产品链接地址!');
                    return;
                }
                abc.block();
                $.ajax({
                    type: "GET",
                    url: ctx + "/financed/myspread.php?url=" + url,
                    async: true,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        abc.unblock();
                        $('#weixin_ewm').attr("src", "data:image/jpg;base64," + data.qrcode);
                        $('#wx_url').val(data.spreadurl);
                        $('#tgm').show();
                    }
                });
            });

            $('a[name="fz"]').on('click', function () {
                var fzid = $(this).attr('fzid');
                var Url2 = document.getElementById(fzid);
                Url2.select(); // 选择对象
                document.execCommand("Copy"); // 执行浏览器复制命令
                alert("已复制好，可贴粘。");
            })

            $('#productspread').click(function () {
                var productSpreadId = $("#productSpreadId").val();
                if (productSpreadId == null || productSpreadId == "") {
                    abc.layerAlert(false, '请选择公司产品!');
                    return;
                }
                abc.block();
                $.ajax({
                    type: "GET",
                    url: ctx + "/system/productspread/downloadproduct.php?productSpreadId=" + productSpreadId,
                    async: true,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        abc.unblock();
                        $('#spreadImg').empty();
                        var datalist = data.productImgs;
                        for (var i = 0; i < datalist.length; i++) {
                            var _div = $('<div class="layui-inline"></div>');
                            var _tab = $('<table style="width: 320px">');
                            var _des = $('<tr><td>' + datalist[i].description + '</td></tr>');
                            var _imgdiv = $('<tr></tr>');
                            if (datalist[i].imageUrl != null && datalist[i].imageUrl != "") {
                                var _td = $('<td></td>');
                                var _dd = $('<div class="layui-input-inline"></div>');
                                var _img = $('<img title="点击放大图片" class="showbig" height="200" width="200" src="' + imgurl + '/' + datalist[i].imageUrl + '">').click(function () {
                                    var _this = $(this);
                                    var _shoimg = $("#shoimg");
                                    _shoimg.find('img').attr('src', _this.attr('src'));
                                    var scrol = $(document).scrollTop();
                                    _shoimg.css({
                                        width: _this.width(),
                                        height: _this.height(),
                                        top: _this.offset().top - scrol,
                                        left: _this.offset().left,
                                        marginLeft: '0px'
                                    }).show();
                                    _shoimg.animate({
                                        left: '50%',
                                        top: abc.winscrollTop(50),
                                        width: '800px',
                                        height: '600px',
                                        marginLeft: '-400px'
                                    }, 500);
                                });
                                _dd.append(_img);
                                _td.append(_dd);
                                _imgdiv.append(_td);
                            }
                            else {
                                var _img = $('<td><div class="layui-input-inline"><img height="300" width="500" src="' + ctx + '/images/default.jpg"></div></td>');
                                _imgdiv.append(_img);
                            }
                            var _down = $('<tr><td style="text-align: center"><a href="' + imgurl + '/' + datalist[i].imageUrl + '" download="图片">下载</a></td></tr>');
                            _tab.append(_des);
                            _tab.append(_imgdiv);
                            _tab.append(_down);
                            _div.append(_tab);
                            $('#spreadImg').append(_div);
                        }
                    }
                });
            });

            function closeDiv() {
                if (document.getElementById('loading') != null) {
                    document.getElementById('loading').style.visibility = 'hidden';
                }
            }

            closeDiv();
        });
    })
});
