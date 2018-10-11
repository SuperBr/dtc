package com.dtc.action;


import com.dtc.bean.ZkDateBean;
import com.dtc.zkService.ZKService;
import com.dtc.zkService.DefaultZkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ZkServiceAction {

    private ZKService zkService = new DefaultZkServiceImpl();

    @ResponseBody
    @RequestMapping(value = "/zkDate")
    public ZkDateBean getZkDate(String path) throws Exception {


        return zkService.getPathDate(zkService.getCacheZk("127.0.0.1:2181"), path);
    }


    @RequestMapping("/zkDate/zkList")
    public void getZkDateForDubbo(Model model) throws Exception {
        ZkDateBean zkDateBean = zkService.getPathDate(zkService.getCacheZk("10.1.10.215:2181,10.1.10.216:2181,10.1.10.217:2181"), "/dubbo");
        model.addAttribute("zkDates", zkDateBean);
    }

}
