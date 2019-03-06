package com.venusource.app.qywechat.api.impl;

import com.google.inject.Inject;
import com.venusource.app.qywechat.api.ApiTestModule;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.bean.WxCpUserDetail;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * <pre>
 *  Created by BinaryWang on 2018/4/22.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpOAuth2ServiceImplTest {
  @Inject
  private WxCpService wxService;

  @Test
  public void testGetUserDetail() throws WxErrorException {
    WxCpUserDetail userDetail = this.wxService.getOauth2Service().getUserDetail("b");
    System.out.println(userDetail);
  }

  @Test
  public void testGetUserInfo() throws WxErrorException {
    this.wxService.getOauth2Service().getUserInfo("abc");
  }
}
