package com.baoyun.base.config.client.zookeeper;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import junit.framework.TestCase;

import org.mockito.Mockito;

import com.baoyun.base.config.client.bean.PojoTestBean;
import com.baoyun.base.config.client.bean.RealtimeBean;
import com.baoyun.base.config.client.util.BeanToString;

public class ZookeeperValueBeanProcessorTest extends TestCase {

    ZookeeperValueBeanProcessor zookeeperValueBeanProcessor = new ZookeeperValueBeanProcessor();

    ZookeeperProperties<String> zookeeperProperties = null;

    String mockInfo = "testInfoMock";
    String mockAddr = "mock地址";
    String mockPhoneNo = "13601009999";
    String mockWeburl = "http://weburl.com";
    String mockBlogurl = "blogurl.mock.com";

    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        zookeeperProperties = Mockito.mock(ZookeeperProperties.class);
        Mockito.when(zookeeperProperties.get(Mockito.eq("test.info"))).thenReturn(mockInfo);
        Mockito.when(zookeeperProperties.get(Mockito.eq("test.addr"))).thenReturn(mockAddr);
        Mockito.when(zookeeperProperties.get(Mockito.eq("test.tel.phoneno"))).thenReturn(mockPhoneNo);
        Mockito.when(zookeeperProperties.get(Mockito.eq("test.weburl"))).thenReturn(mockWeburl);
        Mockito.when(zookeeperProperties.get(Mockito.eq("test.blogurl"))).thenReturn(mockBlogurl);
        Mockito.when(zookeeperProperties.registerRealtimeBean(Mockito.any(RealtimeBean.class))).thenReturn(true);
        Mockito.when(zookeeperProperties.getRealtimeBeanList(Mockito.anyString())).thenReturn(new CopyOnWriteArrayList<SoftReference<RealtimeBean>>());

        // ReflectUtil.setFieldValue(zookeeperValueBeanProcessor, "zookeeperProperties", zookeeperProperties);
        ZookeeperSpringApplication.zookeeperProperties = zookeeperProperties;

    }

    public void testPostProcessBeforeInitialization() {

        String beanName = "pojoTestBean";
        PojoTestBean pojoTestBean = new PojoTestBean();
        Object object = zookeeperValueBeanProcessor.postProcessBeforeInitialization(pojoTestBean, beanName);
        boolean isTarget = (object instanceof PojoTestBean);
        TestCase.assertTrue(isTarget);

        System.out.println(" \t pojoTestBean=" + pojoTestBean);
        System.out.println(" \t pojoTestBean=" + BeanToString.string(pojoTestBean));
        TestCase.assertEquals(pojoTestBean.getAddr(), mockAddr);
        TestCase.assertEquals(pojoTestBean.getTelPhoneNo(), mockPhoneNo);

        // ZookeeperProperties zookeeperProperties = new ZookeeperProperties();

        String key1 = "test.tel.phoneno";
        List<SoftReference<RealtimeBean>> softRealtimeBeanList1 = zookeeperProperties.getRealtimeBeanList(key1);
        TestCase.assertNotNull(softRealtimeBeanList1);
        // TestCase.assertEquals(1, realtimeBeanList1.size());
    }

}
