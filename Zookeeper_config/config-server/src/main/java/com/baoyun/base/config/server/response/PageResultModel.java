package com.baoyun.base.config.server.response;

import lombok.Getter;
import lombok.Setter;

import com.baoyun.base.config.server.dto.PagedListDTO;

/**
 * Created by hli on 2015/1/29.
 * 分页对象
 */
@Setter
@Getter
public class PageResultModel<T>  {
    //响应码 (0 成功 1 参数错误 2 无权限 3 内部错误)
    private  int reply;
    //字符串说明
    private String replyDesc;
    //列表数据
    private PagedListDTO<T> pagedListDTO;
}
