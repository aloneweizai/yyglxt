package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.system.Area;
import com.abc.soa.response.system.City;
import com.abc.soa.response.system.Province;
import com.abc.soa.response.system.bo.AreaListRs;
import com.abc.soa.response.system.bo.CityListRs;
import com.abc.soa.response.system.bo.ProvinceListRs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 省市区
 * @Author liuqi
 * @Date 2017/5/25 15:43
 */
@Controller
public class RegionController {

    /* 列表 GET */
    @GetMapping("/system/region/province/list.php")
    public @ResponseBody List<Province> provinceList(HttpServletRequest request,Model model){
        ProvinceListRs list = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
        return list.getDataList();
    }

    /* 列表 GET */
    @GetMapping("/system/region/city/list.php")
    public @ResponseBody List<City> cityList(@RequestParam(value = "pid") String pid, HttpServletRequest request,Model model){
        CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, pid);
        return cityListRs.getDataList();
    }

    /* 列表 GET */
    @GetMapping("/system/region/county/list.php")
    public @ResponseBody List<Area> countyList(@RequestParam(value = "pid") String pid, HttpServletRequest request,Model model){
        AreaListRs areaListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.AREA, null, AreaListRs.class, pid);
        return areaListRs.getDataList();
    }

}
