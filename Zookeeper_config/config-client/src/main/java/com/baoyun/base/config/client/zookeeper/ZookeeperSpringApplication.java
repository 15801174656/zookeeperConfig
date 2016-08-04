package com.baoyun.base.config.client.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

public class ZookeeperSpringApplication extends SpringApplication{
	public static ZookeeperProperties<?> zookeeperProperties;
    public ZookeeperSpringApplication(Class<?> loader){
    	super(loader);
    }
    
	/**
	 * 已经读取了所有的配置
	 * 在这里增加 zookeeper读取的配置
	 */
	@Override
	protected void postProcessApplicationContext(
			ConfigurableApplicationContext context) {
		ConfigurableEnvironment env = context.getEnvironment();
		MutablePropertySources mps = env.getPropertySources();
		
		@SuppressWarnings("rawtypes")
		ZookeeperProperties zp = new ZookeeperProperties();
		zp.setZookeeperAddress(env.getProperty("config.zookeeperAddress"));
		zp.setPropertiesPath(env.getProperty("config.propertiesPath"));
		mps.addLast(zp);
		zp.afterPropertiesSet();
		
		ZookeeperSpringApplication.zookeeperProperties = zp;
		
		super.postProcessApplicationContext(context);
	}
	
}
