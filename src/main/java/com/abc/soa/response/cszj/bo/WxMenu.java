package com.abc.soa.response.cszj.bo;



/**
 * 微信菜单
 *
 * @author zhushuai 2017-7-31
 */
public class WxMenu extends BaseWxRespon {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
