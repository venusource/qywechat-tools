package com.venusource.app.qywechat.base.util.http;

import java.io.IOException;

import com.venusource.app.qywechat.base.error.WxErrorException;

/**
 * http请求执行器
 *
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 * @author Daniel Qian
 */
public interface RequestExecutor<T, E> {

  /**
   * @param uri  uri
   * @param data 数据
   */
  T execute(String uri, E data) throws WxErrorException, IOException;
}
