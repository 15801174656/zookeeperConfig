package com.baoyun.base.config.client.zookeeper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RefreshZookeeper {
	@Scheduled(cron = "0 * * * * ?")
    public void autoFreshProp() {
        log.info(" autoFreshProp start ");
        try {
        	if(ZookeeperSpringApplication.zookeeperProperties != null){
	            ZookeeperSpringApplication.zookeeperProperties.freshZookeeperValue();
        	}
        } catch (Exception e) {
            log.error(" autoFreshProp exception:{} ", e);
        }

    }
}
