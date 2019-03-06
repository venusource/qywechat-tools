package com.venusource.app.qywechat.base.util.http;

import java.io.File;

import com.venusource.app.qywechat.base.bean.result.WxMediaUploadResult;
import com.venusource.app.qywechat.base.util.http.apache.ApacheMediaUploadRequestExecutor;
import com.venusource.app.qywechat.base.util.http.jodd.JoddHttpMediaUploadRequestExecutor;
import com.venusource.app.qywechat.base.util.http.okhttp.OkHttpMediaUploadRequestExecutor;

/**
 * 上传媒体文件请求执行器，请求的参数是File, 返回的结果是String
 *
 * @author Daniel Qian
 */
public abstract class MediaUploadRequestExecutor<H, P> implements RequestExecutor<WxMediaUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MediaUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMediaUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMediaUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMediaUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
