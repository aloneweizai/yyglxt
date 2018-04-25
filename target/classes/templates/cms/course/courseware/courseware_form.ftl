<table id="courseware_form" class="popup-courseware" style="display:none;">
    <input type="hidden" name="courseware_chapterId"  <#if courseware??>value="${courseware.chapterId}"<#else> value="${RequestParameters["chapterId"]!}" </#if> >
    <input type="hidden" name="courseware_Id" value="${(courseware.coursewareId)!}">
    <thead>
    <tr>
        <th>添加课件<u class="glyphicon glyphicon-remove"></u></th>
    </tr>
    <tr>
        <td>类型</td>
        <td>
            <label class="radio-inline label-video">
                <input type="radio" name="coursewareType"  value="video" <#if !courseware?? || (courseware.type=='video')>checked</#if>>视频
            </label>
            <label class="radio-inline label-audio">
                <input type="radio"  name="coursewareType"  value="audio" <#if courseware?? && courseware.type=='audio'>checked</#if>>音频
            </label>
            <label class="radio-inline label-doucment">
                <input type="radio"  name="coursewareType"  value="document" <#if courseware?? && courseware.type=='document'>checked</#if>>文档
            </label>
            <label class="radio-inline label-live">
                <input type="radio"  name="coursewareType"  value="live" <#if courseware?? && courseware.type=='live'>checked</#if>>直播
            </label>
        </td>
    </tr>
    <tr>
        <td>标题</td>
        <td><input type="text" style="width:480px;" name="coursewareTitle" value="${(courseware.title)!}"></td>
    </tr>
    <tr>
        <td>课件排序</td>
        <td><input type="text" style="width:180px;" name="coursewareOrder" value="${(courseware.coursewareSeq)!}"></td>
    </tr>
    <tr id="courseware_isFree_tr">
        <td>免费播放</td>
        <td>
            <label class="radio-inline">
                <input type="radio"  name="isFree" value="1" <#if courseware?? && courseware.isFree?? && courseware.isFree==1>checked</#if>>允许
            </label>
            <label class="radio-inline">
                <input type="radio" name="isFree" value="0" <#if !courseware?? || (courseware.isFree?? && courseware.isFree==0)>checked</#if>>禁止
            </label>
        </td>
    </tr>
    </thead>
    <tbody class="popup-courseware-video js_media_tbody" <#if courseware?? && courseware.type != 'video'>style="display:none;"</#if>>
        <tr>
            <td>上传方式</td>
            <td class="js_uploadWay_td">
                <label class="radio-inline">
                    <input type="radio" name="upload-video" value="1" class="upload-video-one" <#if !courseware?? || courseware.type!='video' || (courseware.type=='video' && courseware.uploadWay==1)>checked</#if>>文件上传
                </label>
                <label class="radio-inline">
                    <input type="radio" name="upload-video" value="0" class="upload-video-two" <#if courseware?? && courseware.type=='video' && courseware.uploadWay==0>checked</#if>>文件外链
                </label>
                <label class="radio-inline">
                    <input type="radio" name="upload-video" value="2" class="upload-video-two" <#if courseware?? && courseware.type=='video' && courseware.uploadWay==2>checked</#if>>第三方视频链接
                </label>
            </td>
        </tr>
        <tr class="video-one js_media_file_tr" <#if courseware?? && courseware.type=='video' && (courseware.uploadWay==0 || courseware.uploadWay==2)>style="display:none;"</#if>>
            <td>视频</td>
            <td>
                <label class="radio-inline">
                    <input type="radio" name="media_select_type" value="upload"<#if !courseware?? || courseware.type!='video' || (courseware.type=='video' && courseware.uploadWay==1)>checked</#if>>上传视频
                </label>
                <label class="radio-inline">
                    <input type="radio" name="media_select_type" value="select_library">从视频素材库中选择
                </label>
            </td>
        </tr>
        <tr class="video-one js_media_file_tr" <#if courseware?? && courseware.type=='video' && (courseware.uploadWay==0 || courseware.uploadWay==2)>style="display:none;"</#if>>
            <td></td>
            <td>
                <div class="upload" style="height: auto">
                    <div class="head">
                        <span>文件名</span>
                        <span style="width: 10%">大小</span>
                        <span style="width: 53%">地址</span>
                    </div>
                    <div class="head js_media_div">
                        <#if courseware?? && courseware.type=='video' && courseware.uploadWay==1>
                            <span>${courseware.fileName!}</span>
                            <span style="width: 10%">${courseware.fileSize!}</span>
                            <span style="width: 53%">${courseware.fileSite!}</span>
                        </#if>
                    </div>
                    <div class="content">
                        <div class="middle js_media_upload_div" style="height: 40px">
                            <span></span>
                            <input style="float: left;margin-top: 10px" type="file" id="upload_video" name="uploadVideo" data-type="mp4;zip">
                            <label class="uploadMsg" style="color:red;margin-top: 10px"></label>
                            <button style="height:26px;line-height:13px;float: right;margin-top: 10px" data-id="upload_video" type="button" class="layui-btn js_upload_media_btn">上传</button>
                        </div>
                        <div class="middle js_media_select_library_div" style="display: none">
                            <span></span><button style="height:26px;line-height:13px;margin-top: 5px"type="button" class="layui-btn js_media_select_library_btn">素材库选择</button>
                        </div>
                        <div class="down">
                            <ul>
                                <li><span>支持<b>mp4</b>格式的视频文件上传，文件大小不能超过500MB</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr class="js_media_link_tr" <#if !courseware?? || courseware.type!='video' ||(courseware.type=='video' && courseware.uploadWay==1)>style="display:none;"</#if>>
            <td>视频链接：</td>
            <td>
                <input class="js_link_input" id="video_link" type="text" style="width:480px;" <#if courseware?? && courseware.type=='video'>value="${(courseware.link)!}"</#if>>
            </td>
        </tr>
        <tr>
            <td>视频时长:</td>
            <td>
                <input class="js_duration_input" type="text" <#if courseware?? && courseware.type=='video'>value="${courseware.duration!}"</#if>>
            </td>
        </tr>
    </tbody>
    <tbody class="popup-courseware-audio js_media_tbody" <#if !courseware?? || courseware.type != 'audio'>style="display:none;"</#if>>
        <tr>
            <td>上传方式</td>
            <td class="js_uploadWay_td">
                <label class="radio-inline">
                    <input type="radio" name="upload-audio"  value="1" <#if !courseware?? || courseware.type!='audio' || (courseware.type=='audio' && courseware.uploadWay==1)>checked</#if>>文件上传
                </label>
                <label class="radio-inline">
                    <input type="radio" name="upload-audio"  value="0" <#if courseware?? && courseware.type=='audio' && courseware.uploadWay==0>checked</#if>>文件外链
                </label>
            </td>
        </tr>
        <tr class="js_media_file_tr" <#if courseware?? && courseware.type=='audio' && courseware.uploadWay==0>style="display: none"</#if>>
            <td>音频</td>
            <td>
                <div class="upload" style="height: auto">
                    <div class="head">
                        <span>文件名</span>
                        <span style='width: 10%'>大小</span>
                        <span style='width: 53%'>地址</span>
                    </div>
                    <div class="head js_media_div">
                        <#if courseware?? && courseware.type=='audio' && courseware.uploadWay==1>
                            <span>${courseware.fileName!}</span>
                            <span style='width: 10%'>${courseware.fileSize!}</span>
                            <span style='width: 53%'>${courseware.fileSite!}</span>
                        </#if>
                    </div>
                    <div class="content">
                        <div class="middle"style="height: 40px">
                            <span></span>
                            <input style="float: left;margin-top: 10px" type="file" id="upload_Audio" name="uploadAudio" data-type="mp3">
                            <label class="uploadMsg" style="color:red;margin-top: 10px"></label>
                            <button style="height:26px;line-height:13px;float: right;margin-top: 10px" data-id="upload_Audio" type="button" class="layui-btn js_upload_media_btn">上传</button>
                        </div>
                        <div class="down">
                            <ul>
                                <li><span>支持<b>mp3</b>格式的音频文件上传，文件大小不能超过40MB</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr class="js_media_link_tr" <#if !courseware?? || courseware.type!='audio' ||(courseware.type=='audio' && courseware.uploadWay==1)>style="display: none"</#if>>
            <td>音频链接：</td>
            <td>
                <input class="js_link_input" type="text" style="width:480px;" <#if courseware?? && courseware.type=='audio'>value="${(courseware.link)!}"</#if>>
            </td>
        </tr>
        <tr>
            <td>音频时长:</td>
            <td>
                <input class="js_duration_input" type="text" <#if courseware?? && courseware.type=='audio'>value="${courseware.duration!}"</#if>>
            </td>
        </tr>
    </tbody>
    <tbody class="popup-courseware-document js_media_tbody" <#if !courseware?? || courseware.type != 'document'>style="display:none;"</#if>>
    <tr>
        <td>是否可以下载</td>
        <td class="js_isDownload_td">
            <label class="radio-inline">
                <input type="radio" name="down" value="1" <#if courseware?? && courseware.type=='document' && courseware.isDownload==1>checked</#if>>允许
            </label>
            <label class="radio-inline">
                <input type="radio" name="down" value="0" <#if !courseware?? || courseware.type!='document'|| (courseware.type=='document' && courseware.isDownload==0)>checked</#if>>禁止
            </label>
        </td>
    </tr>
    <tr>
        <td>上传方式</td>
        <td class="js_uploadWay_td">
            <label class="radio-inline">
                <input type="radio" name="upload-document" value="1" <#if !courseware?? || courseware.type!='document' || (courseware.type=='document' && courseware.uploadWay==1)>checked</#if>>文件上传
            </label>
            <label class="radio-inline">
                <input type="radio" name="upload-document" id="upload-document" value="0" <#if courseware?? && courseware.type=='document' && courseware.uploadWay==0>checked</#if>>文件外链
            </label>
        </td>
    </tr>
    <tr class="js_media_file_tr" <#if courseware?? && courseware.type=='document' && courseware.uploadWay==0>style="display: none"</#if>>
        <td>文档</td>
        <td>
            <div class="upload" style="height: auto">
                <div class="head">
                    <span>文件名</span>
                    <span style='width: 10%'>大小</span>
                    <span style='width: 53%'>地址</span>
                </div>
                <div class="head js_media_div">
                    <#if courseware?? && courseware.type=='document' && courseware.uploadWay==1>
                        <span>${courseware.fileName!}</span>
                        <span style='width: 10%'>${courseware.fileSize!}</span>
                        <span style='width: 53%'>${courseware.fileSite!}</span>
                    </#if>
                </div>
                <div class="content">
                    <div class="middle" style="height: 40px">
                        <span></span>
                        <input style="float: left;margin-top: 10px" type="file" id="upload_document" name="uploadDocument" data-type="pdf">
                        <label class="uploadMsg" style="color:red;margin-top: 10px"></label>
                        <button style="height:26px;line-height:13px;float: right;margin-top: 10px" data-id="upload_document" type="button" class="layui-btn js_upload_media_btn">上传</button>
                    </div>
                    <div class="down">
                        <ul>
                            <li><span>支持<b>pdf</b>格式的文件上传，文件大小不能超过40MB</span></li>
                            <li><span>所有文档类型，请确保文档内容不为空，否则文档可能转化失败</span></li>
                            <li><span>为了达到更好的浏览效果，该文档需要花费几分钟的时间进行转码，请耐心等待</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    <tr class="js_media_link_tr" <#if !courseware?? || (courseware.type=='document' && courseware.uploadWay==1)>style="display: none"</#if>>
        <td>文档链接：</td>
        <td>
            <input class="js_link_input" type="text" style="width:480px;" <#if courseware?? && courseware.type=='document'>value="${courseware.link!}"</#if>>
        </td>
    </tr>
    </tbody>
    <tbody class="popup-courseware-live" <#if !courseware?? || courseware.type != 'live'>style="display:none;"</#if>>
    <tr>
        <td>直播链接：</td>
        <td><input class="js_link_input" type="text" style="width:480px;" <#if courseware?? && courseware.type=='live'>value="${courseware.link!}"</#if>   ></td>
    </tr>
    <tr class="">
        <td>直播时长:</td>
        <td>
            <input class="js_duration_input" type="text" <#if courseware?? && courseware.type=='live'>value="${courseware.duration!}"</#if> >
        </td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td>
            <button type="button" class="btn btn-danger js_cancel">取消</button>
            <button type="button" class="btn btn-success js_courseware_save">保存</button>
        </td>
    </tr>
    </tfoot>
</table>