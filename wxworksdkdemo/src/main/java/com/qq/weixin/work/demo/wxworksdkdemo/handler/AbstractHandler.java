package com.qq.weixin.work.demo.wxworksdkdemo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venusource.app.qywechat.message.WxCpMessageHandler;


/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public abstract class AbstractHandler implements WxCpMessageHandler {
  protected Logger logger = LoggerFactory.getLogger(getClass());
}
