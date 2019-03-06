package com.venusource.app.qywechat.base.util.http;

import com.venusource.app.qywechat.base.util.http.apache.ApacheSimplePostRequestExecutor;
import com.venusource.app.qywechat.base.util.http.jodd.JoddHttpSimplePostRequestExecutor;
import com.venusource.app.qywechat.base.util.http.okhttp.OkHttpSimplePostRequestExecutor;

/**
 * 用装饰模式实现
 * 简单的POST请求执行器，请求的参数是String, 返回的结果也是String
 *
 * @author Daniel Qian
 */
public abstract class SimplePostRequestExecutor<H, P> implements RequestExecutor<String, String> {
  protected RequestHttp<H, P> requestHttp;

  public SimplePostRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<String, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheSimplePostRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpSimplePostRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpSimplePostRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
