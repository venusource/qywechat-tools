package com.venusource.app.qywechat.base.api;

import com.venusource.app.qywechat.base.error.WxErrorException;

/**
 * WxErrorException处理器.
 *
 * @author Daniel Qian
 */
public interface WxErrorExceptionHandler {

  void handle(WxErrorException e);

}
