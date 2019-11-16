package com.qq.weixin.work.demo.wxworksdkdemo.utils;

import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutTextMessage;

/**
 *  @author Binary Wang(https://github.com/binarywang)
 */
public class TextBuilder extends AbstractBuilder {

  @Override
  public WxCpXmlOutMessage build(String content, WxCpXmlMessage wxMessage,
                                 WxCpService service) {
    WxCpXmlOutTextMessage m = WxCpXmlOutMessage.TEXT().content(content)
        .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
        .build();
    return m;
  }

}
