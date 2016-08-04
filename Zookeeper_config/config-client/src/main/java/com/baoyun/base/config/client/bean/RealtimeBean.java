package com.baoyun.base.config.client.bean;

import java.lang.reflect.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 实施刷新bean
 *
 */
@Getter
@Setter
@ToString
public class RealtimeBean {

    /**
     * 需要刷新的Bean
     */
    private Object bean;

    /**
     * 需要刷新的Bean名称
     */
    private String beanName;

    /**
     * 字段
     */
    private Field field;

    /**
     * 需要刷新的 键值
     */
    private String key;

}
