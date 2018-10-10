package com.dtc.zkService;

import com.dtc.bean.ZkDateBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultZkServiceImpl implements ZKService {
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
    public ZkDateBean getPathDate(CuratorFramework curatorFramework, String path) throws Exception {
        ZkDateBean zkDateBean = new ZkDateBean();
        this.recursiveGetDate(curatorFramework, zkDateBean, path);
        return zkDateBean;
    }

    private void recursiveGetDate(CuratorFramework curatorFramework, ZkDateBean zkDateBean, String path) throws Exception {
        if (curatorFramework.checkExists().forPath(path) != null) {
            if (curatorFramework.getData().forPath(path) == null || curatorFramework.getData().forPath(path).length == 0) {
                zkDateBean.setPath(path).setDate("");
            } else {
                zkDateBean.setPath(path).setDate(curatorFramework.getData().forPath(path).toString());
            }

            List<String> child = curatorFramework.getChildren().forPath(path);
            for (String s : child) {
                if (zkDateBean.getChildNode() == null) {
                    zkDateBean.setChildNode(new ArrayList<ZkDateBean>());
                }
                ZkDateBean curDateBean = new ZkDateBean();
                if (path.endsWith("/")) {
                    this.recursiveGetDate(curatorFramework, curDateBean, path + s);
                } else {
                    this.recursiveGetDate(curatorFramework, curDateBean, path + "/" + s);
                }
                zkDateBean.getChildNode().add(curDateBean);

            }
        }

    }
}
