package com.dtc.action;


import com.dtc.bean.ComBean;
import com.dtc.bean.User;
import com.dtc.bean.ZkDateBean;
import com.dtc.zkService.DefaultUserContext;
import com.dtc.zkService.RegisterApi;
import com.dtc.zkService.ZKService;
import com.dtc.zkService.DefaultZkServiceImpl;
import com.google.common.collect.Maps;
import com.suber.api.ICity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/context/userContext")
    public void userContext(Model model) throws IntrospectionException {
        User user = new User().setUserName("wyc").setAccount("wyc").setPasswd("123456");

        DefaultUserContext defaultUserContext = new DefaultUserContext(user);

        List<Method> methods = RegisterApi.registerApi(defaultUserContext, ICity.class);

        model.addAttribute("methods",methods);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Object getBean() {

        return new User();
    }
    @RequestMapping("/comBean")
    public void getComBean(Model model) {

        Map<String, Class> classMap = Maps.newHashMap();

        for (Field field : ComBean.class.getDeclaredFields()) {
            classMap.put(field.getName(), field.getType());
        }
        model.addAttribute("comBean", classMap);
    }

}
