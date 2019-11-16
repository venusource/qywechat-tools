package com.qq.weixin.work.demo.wxworksdkdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 *  @author Binary Wang(https://github.com/binarywang)
 */
public abstract class AbstractBuilder {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract WxCpXmlOutMessage build(String content, WxCpXmlMessage wxMessage, WxCpService service);
}
