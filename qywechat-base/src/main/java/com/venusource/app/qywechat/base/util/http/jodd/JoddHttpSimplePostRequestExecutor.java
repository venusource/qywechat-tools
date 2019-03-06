package com.venusource.app.qywechat.base.util.http.jodd;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.IOException;

import com.venusource.app.qywechat.base.error.WxError;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.http.RequestHttp;
import com.venusource.app.qywechat.base.util.http.SimplePostRequestExecutor;

/**
 * Created by ecoolper on 2017/5/4.
 */
public class JoddHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity) throws WxErrorException, IOException {
    HttpConnectionProvider provider = requestHttp.getRequestHttpClient();
    ProxyInfo proxyInfo = requestHttp.getRequestHttpProxy();

    HttpRequest request = HttpRequest.post(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    request.withConnectionProvider(provider);
    if (postEntity != null) {
      request.bodyText(postEntity);
    }
    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);

    String responseContent = response.bodyText();
    if (responseContent.isEmpty()) {
      throw new WxErrorException(WxError.builder().errorCode(9999).errorMsg("无响应内容")
        .build());
    }

    if (responseContent.startsWith("<xml>")) {
      //xml格式输出直接返回
      return responseContent;
    }

    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }

}
