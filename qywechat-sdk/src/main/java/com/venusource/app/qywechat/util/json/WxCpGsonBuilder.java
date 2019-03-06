package com.venusource.app.qywechat.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.venusource.app.qywechat.base.bean.menu.WxMenu;
import com.venusource.app.qywechat.base.error.WxError;
import com.venusource.app.qywechat.base.util.json.WxErrorAdapter;
import com.venusource.app.qywechat.bean.WxCpDepart;
import com.venusource.app.qywechat.bean.WxCpMessage;
import com.venusource.app.qywechat.bean.WxCpTag;
import com.venusource.app.qywechat.bean.WxCpUser;

/**
 * @author Daniel Qian
 */
public class WxCpGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxCpMessage.class, new WxCpMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxCpMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
