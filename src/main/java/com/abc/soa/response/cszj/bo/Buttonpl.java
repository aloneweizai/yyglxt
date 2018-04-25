package com.abc.soa.response.cszj.bo;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 微信菜单按钮
 *
 * @author zhushuai 2017-7-31
 */
public class Buttonpl extends Button   {
     private String erji1;
    private String erji2;
    private String erji3;
    private String erji4;
    private String erji5;
    public String getErji1() {
        return erji1;
    }
    public String getErji(int i){
    if (i == 0) return erji1;
    if (i == 1) return erji2;
        if (i == 2) return erji3;

        if (i == 3) return erji4;

   return erji5;

    }
    public void setErji1(String erji1) {
        this.erji1 = erji1;
    }

    public String getErji2() {
        return erji2;
    }

    public void setErji2(String erji2) {
        this.erji2 = erji2;
    }

    public String getErji3() {
        return erji3;
    }

    public void setErji3(String erji3) {
        this.erji3 = erji3;
    }

    public String getErji4() {
        return erji4;
    }

    public void setErji4(String erji4) {
        this.erji4 = erji4;
    }

    public String getErji5() {
        return erji5;
    }

    public void setErji5(String erji5) {
        this.erji5 = erji5;
    }



}
