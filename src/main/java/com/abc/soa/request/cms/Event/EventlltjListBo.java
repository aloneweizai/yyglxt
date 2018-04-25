package com.abc.soa.request.cms.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/7/4.
 */
public class EventlltjListBo {

    private Map<String,List<EventlltjBo>> lltj;

    public Map<String, List<EventlltjBo>> getLltj() {
        return lltj;
    }

    public void setLltj(Map<String, List<EventlltjBo>> lltj) {
        this.lltj = lltj;
    }
}
