package com.venusource.app.qywechat.bean.outxmlbuilder;

import com.venusource.app.qywechat.bean.WxCpXmlOutVoiceMessage;

/**
 * 语音消息builder
 *
 * @author Daniel Qian
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder, WxCpXmlOutVoiceMessage> {

  private String mediaId;

  public VoiceBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxCpXmlOutVoiceMessage build() {
    WxCpXmlOutVoiceMessage m = new WxCpXmlOutVoiceMessage();
    setCommon(m);
    m.setMediaId(this.mediaId);
    return m;
  }

}
