package com.venusource.app.qywechat.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.venusource.app.qywechat.util.json.WxCpGsonBuilder;

/**
 * Created by Daniel Qian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCpTag implements Serializable {
  private static final long serialVersionUID = -7243320279646928402L;

  private String id;

  private String name;


  public static WxCpTag fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTag.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
