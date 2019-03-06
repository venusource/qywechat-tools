package com.venusource.app.qywechat.base.bean;

import org.testng.*;
import org.testng.annotations.*;

import com.venusource.app.qywechat.base.bean.WxAccessToken;

@Test
public class WxAccessTokenTest {

  public void testFromJson() {

    String json = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
    WxAccessToken wxError = WxAccessToken.fromJson(json);
    Assert.assertEquals(wxError.getAccessToken(), "ACCESS_TOKEN");
    Assert.assertTrue(wxError.getExpiresIn() == 7200);

  }

}
