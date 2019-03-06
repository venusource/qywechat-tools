package com.venusource.app.qywechat.bean;

import lombok.Data;

import java.io.Serializable;

import com.venusource.app.qywechat.util.json.WxCpGsonBuilder;

/**
 * 微信部门.
 *
 * @author Daniel Qian
 */
@Data
public class WxCpDepart implements Serializable {

  private static final long serialVersionUID = -5028321625140879571L;
  private Integer id;
  private String name;
  private Integer parentId;
  private Long order;

  public static WxCpDepart fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDepart.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
