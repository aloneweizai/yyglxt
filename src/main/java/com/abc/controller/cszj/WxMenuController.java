package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;

import com.abc.soa.response.cszj.WxMenuRs;
import com.abc.soa.response.cszj.bo.Button;
import com.abc.soa.response.cszj.bo.Buttonpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/gzmenu")
public class WxMenuController {

    protected static Logger LOGGER = LoggerFactory.getLogger(WxMenuController.class);

    @RequestMapping("/list.php")
    public String adpageList(  HttpServletRequest request, Model model) {
        return "cszj/wxgzh/wxmenu";
    }
    @PostMapping("/getmenu.php")
    public @ResponseBody  BaseResponse  getMenu(HttpServletRequest request) {
        WxMenuRs returnObj = SoaConnectionFactory.get(request, ConstantsUri.GZHMENU_LIST, null, WxMenuRs.class);
        return returnObj;
    }


    /**
     * 添加
     */
    @PostMapping("/insert_menu.php")
    public @ResponseBody  BaseResponse  insertMenu(@RequestBody Button button, HttpServletRequest request) {
        LOGGER.info("insertMenu  {}", button.toString());
        BaseResponse returnObj = null;
        button.setWxStatus(1);
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.MSGAUTO_GZHMENU_creat, button, WxMenuRs.class);

        return returnObj;
    }
    @PostMapping("/wxmenu.php")
    public @ResponseBody  BaseResponse  insertWxMenu( HttpServletRequest request) {

        BaseResponse returnObj = null;

        returnObj = SoaConnectionFactory.post(request, ConstantsUri.MSGAUTO_GZHWXMENU_creat, null, BaseResponse.class);

        return returnObj;
    }

    /**
     * 删除
     */
    @PostMapping("/del_menu.php")
    public @ResponseBody  BaseResponse  delMenu(String id, HttpServletRequest request) {
        LOGGER.info("delMenu  {}", id.toString());
        BaseResponse returnObj = null;

        returnObj = SoaConnectionFactory.delete(request, ConstantsUri.MSGAUTO_GZHMENU_update, null, WxMenuRs.class,id);

        return returnObj;
    }

    /**
     * 修改
     */
    @PostMapping("/update_menu.php")
    public @ResponseBody  BaseResponse  updateMenu(@RequestBody Button button, HttpServletRequest request) {
        LOGGER.info("updateMenu  {}", button.toString());
        String id = button.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            LOGGER.error("updateGzhsz id错误");
            throw( new RuntimeException("updateGzhsz id错误"));
        } else {
            if(button.getParentId() != null && button.getParentId().isEmpty()){
                button.setParentId(null);
            }
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.MSGAUTO_GZHMENU_update, button, WxMenuRs.class, id);
        }
        return returnObj;
    }
    /**
     * 修改
     */
    @PostMapping("/update_plmenu.php")
    public @ResponseBody  BaseResponse  updateplMenu(@RequestBody Buttonpl buttonpl, HttpServletRequest request) {
        LOGGER.info("updateplMenu  {}", buttonpl.toString());
        BaseResponse returnObj = null;
        returnObj= updateMenu(buttonpl,request);
        WxMenuRs menus = (WxMenuRs)getMenu(request);
        Button[] btnArr =  menus.getData().getButton();
        Button[] subBtn =null;
        for (Button btn:btnArr             ) {
            if(btn.getId().equals(buttonpl.getId())){
                 subBtn = btn.getSub_button();

                break;
            }
        }
        boolean isAdd = false;
        if(subBtn == null){
            isAdd = true;
        }
        for (int fori =0;fori<5;fori++){
            if (buttonpl.getErji(fori) == null || buttonpl.getErji(fori).isEmpty()){
                //存在就删除
                if(subBtn != null && subBtn.length > fori){
                    delMenu(subBtn[fori].getId(),request);
                }
            }else {
                if (isAdd || subBtn.length <= fori) {
                    isAdd = true;
                    Button newBut = new Button();
                    newBut.setName(buttonpl.getErji(fori));
                    newBut.setType("view");
                    newBut.setParentId(buttonpl.getId());
                    newBut.setSort(0);
                    BaseResponse tmpObj = insertMenu(newBut, request);
                } else {
                    subBtn[fori].setName(buttonpl.getErji(fori));
                    BaseResponse tmpObj =  updateMenu(subBtn[fori], request);
                }
            }
        }
        return returnObj;
    }

}
