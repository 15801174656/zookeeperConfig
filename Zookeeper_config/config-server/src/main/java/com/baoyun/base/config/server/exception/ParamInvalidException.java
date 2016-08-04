package com.baoyun.base.config.server.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParamInvalidException extends RuntimeException {
    private static final long serialVersionUID = -3036373656309547674L;
    
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 错误信息
     */
    private String errorInfo;
    
    /**
     * 
     * @param fieldName 字段名
     * @param errorInfo 错误信息
     */
    public ParamInvalidException(String fieldName, String errorInfo) {
        super(fieldName+" "+errorInfo);
        this.fieldName = fieldName;
        this.errorInfo = errorInfo;
    }
}
