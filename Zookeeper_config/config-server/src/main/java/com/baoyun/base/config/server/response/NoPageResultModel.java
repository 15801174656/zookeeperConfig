package com.baoyun.base.config.server.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoPageResultModel {
    /**
     * 响应码(1成功)
     */
    private int reply;
    /**
     * 返回说明
     */
    private String replyDesc;

    /**
     * 返回数据
     */
    private Object data;
    
    
    public NoPageResultModel(int reply, String replyDesc) {
        this.reply = reply;
        this.replyDesc = replyDesc;
        this.data = "";
    }


	public NoPageResultModel() {
		super();
	}

}
