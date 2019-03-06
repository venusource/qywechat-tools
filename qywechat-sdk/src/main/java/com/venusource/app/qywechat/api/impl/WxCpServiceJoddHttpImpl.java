package com.venusource.app.qywechat.api.impl;

import com.venusource.app.qywechat.base.WxType;
import com.venusource.app.qywechat.base.bean.WxAccessToken;
import com.venusource.app.qywechat.base.error.WxError;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.http.HttpType;
import com.venusource.app.qywechat.config.WxCpConfigStorage;

import jodd.http.*;

public class WxCpServiceJoddHttpImpl extends WxCpServiceAbstractImpl<HttpConnectionProvider, ProxyInfo> {
  protected HttpConnectionProvider httpClient;
  protected ProxyInfo httpProxy;


  @Override
  public HttpConnectionProvider getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public ProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.JODD_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (this.configStorage.isAccessTokenExpired() || forceRefresh) {
      synchronized (this.globalAccessTokenRefreshLock) {
        if (this.configStorage.isAccessTokenExpired()) {
          String url = this.configStorage.getWxworkServer() + "/cgi-bin/gettoken?"
            + "&corpid=" + this.configStorage.getCorpId()
            + "&corpsecret=" + this.configStorage.getCorpSecret();

          HttpRequest request = HttpRequest.get(url);
          if (this.httpProxy != null) {
            httpClient.useProxy(this.httpProxy);
          }
          request.withConnectionProvider(httpClient);
          HttpResponse response = request.send();

          String resultContent = response.bodyText();
          WxError error = WxError.fromJson(resultContent, WxType.CP);
          if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
          }
          WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
          this.configStorage.updateAccessToken(
            accessToken.getAccessToken(), accessToken.getExpiresIn());
        }
      }
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = JoddHttp.httpConnectionProvider;
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }
}
