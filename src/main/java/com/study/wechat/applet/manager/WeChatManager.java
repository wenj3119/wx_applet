package com.study.wechat.applet.manager;


import com.study.wechat.applet.config.WxConfig;
import com.study.wechat.applet.config.WxConfig.Template;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wenjun
 * @date 2021/3/20 14:18
 */
@Component
@Slf4j
public class WeChatManager {

  @Autowired
  private WxConfig config;
  public static Map<String, Template> wxTemplates = new HashMap<>();

  @PostConstruct
  public void init() {
    wxTemplates = config.getTemplates().stream()
        .collect(Collectors.toMap(Template::getType, template -> template));
  }

  public Template getTemplateByType(String type) {
    return wxTemplates.get(type);
  }

}
