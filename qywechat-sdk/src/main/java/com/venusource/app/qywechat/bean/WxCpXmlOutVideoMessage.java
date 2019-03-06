package com.venusource.app.qywechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.venusource.app.qywechat.base.api.WxConsts;
import com.venusource.app.qywechat.base.util.xml.XStreamCDataConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutVideoMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = -8672761162722733622L;

  @XStreamAlias("Video")
  protected final Video video = new Video();

  public WxCpXmlOutVideoMessage() {
    this.msgType = WxConsts.XmlMsgType.VIDEO;
  }

  public String getMediaId() {
    return this.video.getMediaId();
  }

  public void setMediaId(String mediaId) {
    this.video.setMediaId(mediaId);
  }

  public String getTitle() {
    return this.video.getTitle();
  }

  public void setTitle(String title) {
    this.video.setTitle(title);
  }

  public String getDescription() {
    return this.video.getDescription();
  }

  public void setDescription(String description) {
    this.video.setDescription(description);
  }

  @Data
  @XStreamAlias("Video")
  public static class Video {

    @XStreamAlias("MediaId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mediaId;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

  }

}
