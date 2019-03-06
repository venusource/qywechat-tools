package com.venusource.app.qywechat.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venusource.app.qywechat.base.api.WxErrorExceptionHandler;
import com.venusource.app.qywechat.base.error.WxErrorException;


public class LogExceptionHandler implements WxErrorExceptionHandler {

  private Logger log = LoggerFactory.getLogger(WxErrorExceptionHandler.class);

  @Override
  public void handle(WxErrorException e) {

    this.log.error("Error happens", e);

  }

}
