package com.dtc.zkService;

import org.apache.curator.framework.CuratorFramework;

import java.util.Map;

public interface ZKService {


    CuratorFramework openZk(String connectionInfo);

    CuratorFramework getCacheZk(String connectionInfo);

    Map<String, String> getPathDate(CuratorFramework curatorFramework, String path) throws Exception;


}
