package com.venusource.app.qywechat.api.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.venusource.app.qywechat.api.WxCpOAuth2Service;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.api.WxConsts;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.http.URIUtil;
import com.venusource.app.qywechat.base.util.json.GsonHelper;
import com.venusource.app.qywechat.bean.WxCpUserDetail;

/**
 * <pre>
 *
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 *
 * @author Binary Wang
 * </pre>
 */
public class WxCpOAuth2ServiceImpl implements WxCpOAuth2Service {
  private WxCpService mainService;

  public WxCpOAuth2ServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public String buildAuthorizationUrl(String state) {
    return this.buildAuthorizationUrl(
      this.mainService.getWxCpConfigStorage().getOauth2redirectUri(),
      state
    );
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state) {
    return this.buildAuthorizationUrl(redirectUri, state, WxConsts.OAuth2Scope.SNSAPI_BASE);
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state, String scope) {
    StringBuilder url = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?");
    url.append("appid=").append(this.mainService.getWxCpConfigStorage().getCorpId());
    url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectUri));
    //add by cj.zhao for 企业微信本地版
    if(this.mainService.getWxCpConfigStorage().isLocalVersion()){
    	url.append("&agentid=").append(this.mainService.getWxCpConfigStorage().getAgentId());
    }
    url.append("&response_type=code");
    url.append("&scope=").append(scope);

    if (WxConsts.OAuth2Scope.SNSAPI_PRIVATEINFO.equals(scope)
      || WxConsts.OAuth2Scope.SNSAPI_USERINFO.equals(scope)) {
      url.append("&agentid=").append(this.mainService.getWxCpConfigStorage().getAgentId());
    }

    if (state != null) {
      url.append("&state=").append(state);
    }
    url.append("#wechat_redirect");
    return url.toString();
  }

  @Override
  public String[] getUserInfo(String code) throws WxErrorException {
    return this.getUserInfo(this.mainService.getWxCpConfigStorage().getAgentId(), code);
  }

  @Override
  public String[] getUserInfo(Integer agentId, String code) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getWxworkServer() + "/cgi-bin/user/getuserinfo?code=%s&agentid=%d",
      code, agentId);
    String responseText = this.mainService.get(url, null);
    JsonElement je = new JsonParser().parse(responseText);
    JsonObject jo = je.getAsJsonObject();
    return new String[]{GsonHelper.getString(jo, "UserId"),
      GsonHelper.getString(jo, "DeviceId"),
      GsonHelper.getString(jo, "OpenId"),
      GsonHelper.getString(jo, "user_ticket"),
      GsonHelper.getString(jo, "expires_in")
    };
  }

  @Override
  public WxCpUserDetail getUserDetail(String userTicket) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getWxworkServer() + "/cgi-bin/user/getuserdetail";
    JsonObject param = new JsonObject();
    param.addProperty("user_ticket", userTicket);
    String responseText = this.mainService.post(url, param.toString());
    return new GsonBuilder().create().fromJson(responseText, WxCpUserDetail.class);
  }
}
