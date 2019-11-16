package com.qq.weixin.work.demo.wxworksdkdemo.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.qq.weixin.work.demo.wxworksdkdemo.utils.TextBuilder;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpUser;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUserName());

    // 获取微信用户基本信息
    WxCpUser userWxInfo = cpService.getUserService().getById(wxMessage.getFromUserName());

    if (userWxInfo != null) {
      // TODO 可以添加关注用户到本地
    }

    WxCpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    if (responseResult != null) {
      return responseResult;
    }

    try {
      return new TextBuilder().build("感谢关注", wxMessage, cpService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  private WxCpXmlOutMessage handleSpecial(WxCpXmlMessage wxMessage) {
    //TODO
    return null;
  }

}
