package com.venusource.app.qywechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.venusource.app.qywechat.base.api.WxConsts;
import com.venusource.app.qywechat.base.util.xml.XStreamMediaIdConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutImageMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = -1099446240667237313L;

  @XStreamAlias("Image")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  private String mediaId;

  public WxCpXmlOutImageMessage() {
    this.msgType = WxConsts.XmlMsgType.IMAGE;
  }

}
