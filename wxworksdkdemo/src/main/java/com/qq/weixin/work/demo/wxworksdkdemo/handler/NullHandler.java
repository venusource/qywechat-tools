package com.qq.weixin.work.demo.wxworksdkdemo.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class NullHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    return null;
  }

}
