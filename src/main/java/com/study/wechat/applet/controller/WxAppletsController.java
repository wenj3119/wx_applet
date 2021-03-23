package com.study.wechat.applet.controller;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage.Data;
import com.study.wechat.applet.config.WxConfig.Template;
import com.study.wechat.applet.manager.WeChatManager;
import com.study.wechat.applet.utils.SendMiniAppUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenjun
 * @date 2021/3/22 17:16
 */
@Api("微信小程序订阅消息推送接口")
@RestController
@RequestMapping("/wx/msg/")
@Slf4j
public class WxAppletsController {

  @Autowired
  SendMiniAppUtils sendMiniAppUtils;

  @Autowired
  private WeChatManager weChatManager;

  @ApiOperation(value = "微信小程序推送订阅消息", notes = "微信小程序推送订阅消息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "code", value = "用户code", dataType = "String", paramType = "query")})
  @GetMapping(value = "/getOpenIdByCode")
  @ResponseBody
  public Object getOpenIdByCode(String code) {
    String openIdByCode = sendMiniAppUtils.getOpenIdByCode(code);
    log.info("openID：{}", openIdByCode);

    return openIdByCode;
  }

  /**
   * 微信小程序推送订阅消息 create By KingYiFan on 2020/01/06
   */
  @ApiOperation(value = "微信小程序推送订阅消息", notes = "微信小程序推送订阅消息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "openId", value = "openId", dataType = "String", paramType = "query")})
  @GetMapping(value = "/sendDYTemplateMessage")
  @ResponseBody
  public Object sendDYTemplateMessage(String openId) {

    Template template = weChatManager.getTemplateByType("sensorRelease");
    List<Data> dataList = new ArrayList<>();
    Data data = new Data();
    data.setName(template.getStationDetails());

    data.setValue("01 08 09 13 16 33+01");
    dataList.add(data);
    Data data1 = new Data();
    data1.setName(template.getBookingTime());
    data1.setValue("最终开奖结果请已彩票官网为准");
    dataList.add(data1);
    Data data2 = new Data();
    data2.setName(template.getBookingStaff());
    data2.setValue("孙先生");
    dataList.add(data2);
    Data data3 = new Data();
    data3.setName(template.getCheckInTime());
    data3.setValue("2020-05-26前");
    dataList.add(data3);
    Data data4 = new Data();
    data4.setName(template.getTipInstruction());
    data4.setValue("202009182021331001");
    dataList.add(data4);
    try {
      /* String openIdByCode = sendMiniAppUtils.getOpenIdByCode(code);
      log.info("openID：{}",openIdByCode);*/
      sendMiniAppUtils
          .sendSubscribeMsg(openId,
              template.getId(), dataList,
              template.getPage());
      log.info("推送成功！！！");
    } catch (WxErrorException e) {
      log.error("错误消息：{}", e.getMessage());
      e.printStackTrace();
    }

    return "推送失败";
  }
}
