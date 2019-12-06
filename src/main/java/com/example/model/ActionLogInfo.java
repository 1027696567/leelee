package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chen
 * @program leelee
 * @description 操作日志
 * @create 2019-12-06 18:47
 */
@Data
@NoArgsConstructor
public class ActionLogInfo {

    public ActionLogInfo(String ip, String action, String time, String user) {
        this.ip = ip;
        this.action = action;
        this.time = time;
        this.user = user;
        //初始化操作时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.visitTime = sdf.format(date);
    }

    /**
     * id 主键
     * */
    private Integer id;
    /**
     * ip地址
     * */
    private String ip;
    /**
     * 操作详细
     * */
    private String action;
    /**
     * 时间
     * */
    private String time;
    /**
     * 操作用户
     * */
    private String user;
    /**
     * 访问时间
     * */
    private String visitTime;
}
