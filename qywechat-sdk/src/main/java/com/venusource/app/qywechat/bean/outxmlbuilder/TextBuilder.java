package com.venusource.app.qywechat.bean.outxmlbuilder;

import com.venusource.app.qywechat.bean.WxCpXmlOutTextMessage;

/**
 * 文本消息builder
 *
 * @author Daniel Qian
 */
public final class TextBuilder extends BaseBuilder<TextBuilder, WxCpXmlOutTextMessage> {
  private String content;

  public TextBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxCpXmlOutTextMessage build() {
    WxCpXmlOutTextMessage m = new WxCpXmlOutTextMessage();
    setCommon(m);
    m.setContent(this.content);
    return m;
  }
}
