package com.venusource.app.qywechat.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.venusource.app.qywechat.api.WxCpDepartmentService;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.json.GsonHelper;
import com.venusource.app.qywechat.bean.WxCpDepart;
import com.venusource.app.qywechat.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * <pre>
 *  部门管理接口
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpDepartmentServiceImpl implements WxCpDepartmentService {
  private WxCpService mainService;

  public WxCpDepartmentServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public Integer create(WxCpDepart depart) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getWxworkServer() + "/cgi-bin/department/create";
    String responseContent = this.mainService.post(url, depart.toJson());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return GsonHelper.getAsInteger(tmpJsonElement.getAsJsonObject().get("id"));
  }

  @Override
  public void update(WxCpDepart group) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getWxworkServer() + "/cgi-bin/department/update";
    this.mainService.post(url, group.toJson());
  }

  @Override
  public void delete(Integer departId) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getWxworkServer() + "/cgi-bin/department/delete?id=" + departId;
    this.mainService.get(url, null);
  }

  @Override
  public List<WxCpDepart> list(Integer id) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getWxworkServer() + "/cgi-bin/department/list";
    if (id != null) {
      url += "?id=" + id;
    }

    String responseContent = this.mainService.get(url, null);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.INSTANCE.create()
      .fromJson(tmpJsonElement.getAsJsonObject().get("department"),
        new TypeToken<List<WxCpDepart>>() {
        }.getType()
      );
  }
}
