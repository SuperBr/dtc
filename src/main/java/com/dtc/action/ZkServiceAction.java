package com.dtc.action;


import com.dtc.zkService.ZKService;
import com.dtc.zkService.defaultZkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ZkServiceAction {

    private ZKService zkService = new defaultZkServiceImpl();

    @ResponseBody
    @RequestMapping("/zkDate")
    public Map<String, String> getZkDate(String path) throws Exception {


        return zkService.getPathDate(zkService.getCacheZk("127.0.0.1:2181"), path);
    }
}
