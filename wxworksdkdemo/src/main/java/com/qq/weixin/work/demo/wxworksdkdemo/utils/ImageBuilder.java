package com.qq.weixin.work.demo.wxworksdkdemo.utils;

import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutImageMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;


/**
 *  @author Binary Wang(https://github.com/binarywang)
 */
public class ImageBuilder extends AbstractBuilder {

  @Override
  public WxCpXmlOutMessage build(String content, WxCpXmlMessage wxMessage,
                                 WxCpService service) {

    WxCpXmlOutImageMessage m = WxCpXmlOutMessage.IMAGE().mediaId(content)
        .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
        .build();

    return m;
  }

}
