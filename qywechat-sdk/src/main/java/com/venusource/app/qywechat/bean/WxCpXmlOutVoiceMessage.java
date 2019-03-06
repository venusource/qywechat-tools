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
public class WxCpXmlOutVoiceMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = -7947384031546099340L;

  @XStreamAlias("Voice")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  private String mediaId;

  public WxCpXmlOutVoiceMessage() {
    this.msgType = WxConsts.XmlMsgType.VOICE;
  }

}
