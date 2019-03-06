package com.venusource.app.qywechat.base.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.venusource.app.qywechat.base.bean.WxAccessToken;
import com.venusource.app.qywechat.base.bean.menu.WxMenu;
import com.venusource.app.qywechat.base.bean.result.WxMediaUploadResult;
import com.venusource.app.qywechat.base.error.WxError;

public class WxGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
