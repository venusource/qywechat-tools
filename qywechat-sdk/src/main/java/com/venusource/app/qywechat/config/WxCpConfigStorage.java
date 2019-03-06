package com.venusource.app.qywechat.config;

import java.io.File;

import com.venusource.app.qywechat.base.bean.WxAccessToken;
import com.venusource.app.qywechat.base.util.http.apache.ApacheHttpClientBuilder;

/**
 * 微信客户端配置存储
 *
 * @author Daniel Qian
 */
public interface WxCpConfigStorage {

  String getAccessToken();

  boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉
   */
  void expireAccessToken();

  void updateAccessToken(WxAccessToken accessToken);

  void updateAccessToken(String accessToken, int expiresIn);

  String getJsapiTicket();

  boolean isJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉
   */
  void expireJsapiTicket();

  /**
   * 应该是线程安全的
   *
   * @param jsapiTicket
   */
  void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);
  
  boolean isLocalVersion();
  
  String getCorpId();
  
  /**
   * add by cj.zhao for 企业微信私有化版
   * @return
   */
  String getWxworkServer();

  String getCorpSecret();

  Integer getAgentId();

  String getToken();

  String getAesKey();

  long getExpiresTime();

  String getOauth2redirectUri();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  File getTmpDirFile();

  /**
   * http client builder
   *
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();
}
