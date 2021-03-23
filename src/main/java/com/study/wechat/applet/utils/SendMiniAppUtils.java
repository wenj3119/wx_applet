package com.study.wechat.applet.utils;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage.Data;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import java.util.List;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.util.StringUtils;

/**
 * @author wenjun
 * @date 2021/3/13 16:13
 */
public class SendMiniAppUtils {

  private WxMaMsgService msgService;

  private WxMaServiceImpl wxMaService;

  /**
   * description: 构造函数（初始化配置） param: wxMpConfig 配置内容
   */
  public SendMiniAppUtils(String appId, String appSercret) {
    if (StringUtils.isEmpty(appId)) {
      throw new RuntimeException("appId不能为空");
    }
    if (StringUtils.isEmpty(appSercret)) {
      throw new RuntimeException("secret不能为空");
    }
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid(appId);
    config.setSecret(appSercret);
    wxMaService = new WxMaServiceImpl();
    wxMaService.setWxMaConfig(config);
    msgService = wxMaService.getMsgService();

  }

  /**
   * 根据code获取openID
   */
  public String getOpenIdByCode(String code) {
    WxMaJscode2SessionResult session = null;
    try {
      session = wxMaService.getUserService().getSessionInfo(code);
      return session.getOpenid();
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * description: 发送订阅消息 param: openId 用户的openid param: templateId 模板id param: dataParam 参数内容
   */
  public void sendSubscribeMsg(String openId, String templateId,
      List<Data> dataParam) throws WxErrorException {

    // 3.8.0版本使用的使用WxMaSubscribeMessage
    WxMaSubscribeMessage.WxMaSubscribeMessageBuilder builder = WxMaSubscribeMessage.builder();

    builder.toUser(openId);//推送消息的目标对象openId
    builder.templateId(templateId); //这里填写的就是在后台申请添加的模板ID
    builder.data(dataParam);//添加请求参数
    WxMaSubscribeMessage msg = builder.build();
    msgService.sendSubscribeMsg(msg);
  }

  /**
   * description: 发送订阅消息 param: openId 用户的openid param: templateId 模板id param: dataParam 参数内容 param:
   * page 跳转链接
   */
  public void sendSubscribeMsg(String openId, String templateId,
      List<WxMaSubscribeMessage.Data> dataParam, String page) throws WxErrorException {

    // 3.8.0版本使用的使用WxMaSubscribeMessage
    WxMaSubscribeMessage.WxMaSubscribeMessageBuilder builder = WxMaSubscribeMessage.builder();

    builder.toUser(openId);//推送消息的目标对象openId
    builder.templateId(templateId); //这里填写的就是在后台申请添加的模板ID
    builder.data(dataParam);//添加请求参数
    builder.page(page); //添加跳转链接，如果目标用户点击了推送的消息，则会跳转到小程序主页
    WxMaSubscribeMessage msg = builder.build();
    msgService.sendSubscribeMsg(msg);
  }


}
