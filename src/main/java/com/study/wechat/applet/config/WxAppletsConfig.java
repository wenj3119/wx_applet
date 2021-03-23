package com.study.wechat.applet.config;

import com.study.wechat.applet.config.WxConfig.Template;
import com.study.wechat.applet.utils.SendMiniAppUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjun
 * @date 2021/3/19 18:30
 */
@Configuration
public class WxAppletsConfig {

  @Autowired
  private WxConfig wxConfig;

  public static Map<String, Template> wxTemplates = new HashMap<>();

  @Bean
  public SendMiniAppUtils sendMiniAppUtils() {
    wxTemplates = wxConfig.getTemplates().stream()
        .collect(Collectors.toMap(Template::getType, template -> template));
    return new SendMiniAppUtils(wxConfig.getAppId(), wxConfig.getAppSecret());
  }


}
