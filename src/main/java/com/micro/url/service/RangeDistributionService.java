package com.micro.url.service;

import com.micro.url.model.Range;
import jakarta.annotation.PostConstruct;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RangeDistributionService implements SharedCountListener {

    private String zkServer = "localhost:2181";
    private String counterPath = "/zk/counter";
    private int rangeSize = 1000;

    private CuratorFramework client;
    private DistributedAtomicLong count;

    @PostConstruct
    private void postConstruct() {
        client = CuratorFrameworkFactory.newClient(zkServer,
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        count = new DistributedAtomicLong(client, counterPath, new RetryNTimes(10, 10));
    }

    @Bean("range")
    public Range getNewRange() {
        try {
             var value = count.increment();
            if (value.succeeded()) {
                return new Range(value.preValue() * rangeSize, (value.preValue() + 1) * rangeSize - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCountReader, int i) {

    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

    }

}
