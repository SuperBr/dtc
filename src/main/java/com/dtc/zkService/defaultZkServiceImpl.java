package com.dtc.zkService;

import com.google.common.collect.Maps;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class defaultZkServiceImpl implements ZKService {
    private static ConcurrentHashMap<String, CuratorFramework> cachedZk = new ConcurrentHashMap<>();

    @Override
    public CuratorFramework openZk(String connectionInfo) {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.newClient(
                        connectionInfo,
                        5000,
                        3000,
                        retryPolicy);
        client.start();
        return client;
    }

    @Override
    public CuratorFramework getCacheZk(String connectionInfo) {
        if (cachedZk.containsValue(connectionInfo)) {
            return cachedZk.get(connectionInfo);
        }
        CuratorFramework zk = openZk(connectionInfo);
        cachedZk.put(connectionInfo, zk);
        return zk;
    }

    @Override
    public Map<String, String> getPathDate(CuratorFramework curatorFramework, String path) throws Exception {
        Map<String, String> dateMap = Maps.newHashMap();
        this.recursiveGetDate(curatorFramework, dateMap, path);
        return dateMap;
    }

    private void recursiveGetDate(CuratorFramework curatorFramework, Map<String, String> dateMap, String path) throws Exception {
        if (curatorFramework.checkExists().forPath(path) != null) {
            if (curatorFramework.getData().forPath(path) == null) {
                dateMap.put(path, "");
            }else {
                dateMap.put(path, curatorFramework.getData().forPath(path).toString());
            }

            List<String> child = curatorFramework.getChildren().forPath(path);
            for (String s : child) {
                if (path.endsWith("/")) {
                    this.recursiveGetDate(curatorFramework, dateMap, path+s);
                }else {
                    this.recursiveGetDate(curatorFramework, dateMap, path+"/"+s);
                }

            }
        }

    }
}
