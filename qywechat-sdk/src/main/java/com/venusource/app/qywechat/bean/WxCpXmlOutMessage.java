package com.venusource.app.qywechat.bean;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.venusource.app.qywechat.base.util.xml.XStreamCDataConverter;
import com.venusource.app.qywechat.bean.outxmlbuilder.ImageBuilder;
import com.venusource.app.qywechat.bean.outxmlbuilder.NewsBuilder;
import com.venusource.app.qywechat.bean.outxmlbuilder.TextBuilder;
import com.venusource.app.qywechat.bean.outxmlbuilder.VideoBuilder;
import com.venusource.app.qywechat.bean.outxmlbuilder.VoiceBuilder;
import com.venusource.app.qywechat.config.WxCpConfigStorage;
import com.venusource.app.qywechat.util.crypto.WxCpCryptUtil;
import com.venusource.app.qywechat.util.xml.XStreamTransformer;

import lombok.Data;

/**
 * 被动回复消息.
 * https://work.weixin.qq.com/api/doc#12975
 *
 * @author Daniel Qian
 */
@XStreamAlias("xml")
@Data
public abstract class WxCpXmlOutMessage implements Serializable {
  private static final long serialVersionUID = 1418629839964153110L;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String msgType;

  /**
   * 获得文本消息builder.
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder.
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder.
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder.
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得图文消息builder.
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  protected String toXml() {
    return XStreamTransformer.toXml((Class) this.getClass(), this);
  }

  /**
   * 转换成加密的xml格式.
   */
  public String toEncryptedXml(WxCpConfigStorage wxCpConfigStorage) {
    String plainXml = toXml();
    WxCpCryptUtil pc = new WxCpCryptUtil(wxCpConfigStorage);
    return pc.encrypt(plainXml);
  }
}
