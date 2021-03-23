package com.study.wechat.applet.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxConfig {

  private String appId;

  private String appSecret;

  private List<Template> templates;

  @Data
  public static class Template {

    /**
     * 模板id
     */
    private String id;

    /**
     * 模板类型
     */
    private String type;

    /**
     * 工位详情
     */
    private String stationDetails;

    /**
     * 预订时间
     */
    private String bookingTime;

    /**
     * 预订人员
     */
    private String bookingStaff;

    /**
     * 签到时间
     */
    private String checkInTime;

    /**
     * 提示说明
     */
    private String tipInstruction;

    /**
     * 跳转到哪个页面
     */
    private String page;
  }
}