package com.venusource.app.qywechat.bean;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.venusource.app.qywechat.bean.WxCpXmlOutImageMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

@Test
public class WxCpXmlOutImageMessageTest {

  public void test() {
    WxCpXmlOutImageMessage m = new WxCpXmlOutImageMessage();
    m.setMediaId("ddfefesfsdfef");
    m.setCreateTime(1122L);
    m.setFromUserName("from");
    m.setToUserName("to");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[image]]></MsgType>"
      + "<Image><MediaId><![CDATA[ddfefesfsdfef]]></MediaId></Image>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  public void testBuild() {
    WxCpXmlOutImageMessage m = WxCpXmlOutMessage.IMAGE().mediaId("ddfefesfsdfef").fromUser("from").toUser("to").build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[image]]></MsgType>"
      + "<Image><MediaId><![CDATA[ddfefesfsdfef]]></MediaId></Image>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(
      m
        .toXml()
        .replaceAll("\\s", "")
        .replaceAll("<CreateTime>.*?</CreateTime>", ""),
      expected
        .replaceAll("\\s", "")
        .replaceAll("<CreateTime>.*?</CreateTime>", "")
    );

  }
}
