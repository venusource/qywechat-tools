package com.venusource.app.qywechat.demo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.venusource.app.qywechat.base.util.xml.XStreamInitializer;
import com.venusource.app.qywechat.config.WxCpInMemoryConfigStorage;

import java.io.InputStream;

/**
 * @author Daniel Qian
 */
@XStreamAlias("xml")
class WxCpDemoInMemoryConfigStorage extends WxCpInMemoryConfigStorage {

  public static WxCpDemoInMemoryConfigStorage fromXml(InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxCpDemoInMemoryConfigStorage.class);
    return (WxCpDemoInMemoryConfigStorage) xstream.fromXML(is);
  }

  @Override
  public String toString() {
    return "SimpleWxConfigProvider [appidOrCorpid=" + this.corpId + ", corpSecret=" + this.corpSecret + ", accessToken=" + this.accessToken
      + ", expiresTime=" + this.expiresTime + ", token=" + this.token + ", aesKey=" + this.aesKey + "]";
  }

}
