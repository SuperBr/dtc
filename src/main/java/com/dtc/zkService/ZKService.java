package com.dtc.zkService;

import com.dtc.bean.ZkDateBean;
import org.apache.curator.framework.CuratorFramework;

import java.util.Map;

public interface ZKService {


    CuratorFramework openZk(String connectionInfo);

    CuratorFramework getCacheZk(String connectionInfo);

    ZkDateBean getPathDate(CuratorFramework curatorFramework, String path) throws Exception;


}
