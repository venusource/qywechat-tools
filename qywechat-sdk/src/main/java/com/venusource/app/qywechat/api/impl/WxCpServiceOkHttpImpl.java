package com.venusource.app.qywechat.api.impl;

import okhttp3.*;

import java.io.IOException;

import com.venusource.app.qywechat.base.WxType;
import com.venusource.app.qywechat.base.bean.WxAccessToken;
import com.venusource.app.qywechat.base.error.WxError;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.http.HttpType;
import com.venusource.app.qywechat.base.util.http.okhttp.OkHttpProxyInfo;
import com.venusource.app.qywechat.config.WxCpConfigStorage;

public class WxCpServiceOkHttpImpl extends WxCpServiceAbstractImpl<OkHttpClient, OkHttpProxyInfo> {
  protected OkHttpClient httpClient;
  protected OkHttpProxyInfo httpProxy;


  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    this.log.debug("WxCpServiceOkHttpImpl is running");
    if (this.configStorage.isAccessTokenExpired() || forceRefresh) {
      synchronized (this.globalAccessTokenRefreshLock) {
        if (this.configStorage.isAccessTokenExpired()) {
          String url = this.configStorage.getWxworkServer() + "/cgi-bin/gettoken?"
            + "&corpid=" + this.configStorage.getCorpId()
            + "&corpsecret=" + this.configStorage.getCorpSecret();
          //得到httpClient
          OkHttpClient client = getRequestHttpClient();
          //请求的request
          Request request = new Request.Builder().url(url).get().build();
          String resultContent = null;
          try {
            Response response = client.newCall(request).execute();
            resultContent = response.body().string();
          } catch (IOException e) {
            this.log.error(e.getMessage(), e);
          }

          WxError error = WxError.fromJson(resultContent, WxType.CP);
          if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
          }
          WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
          this.configStorage.updateAccessToken(accessToken.getAccessToken(),
            accessToken.getExpiresIn());
        }
      }
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    this.log.debug("WxCpServiceOkHttpImpl initHttp");
    //设置代理
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(configStorage.getHttpProxyHost(),
        configStorage.getHttpProxyPort(),
        configStorage.getHttpProxyUsername(),
        configStorage.getHttpProxyPassword());
    }

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    if (httpProxy != null) {
      clientBuilder.proxy(getRequestHttpProxy().getProxy());

      //设置授权
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
        }
      });
    }
    httpClient = clientBuilder.build();
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }
}
