package com.venusource.app.qywechat.api.impl;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import com.venusource.app.qywechat.api.ApiTestModule;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.api.WxCpTagService;
import com.venusource.app.qywechat.api.impl.WxCpTagServiceImpl;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.bean.WxCpTag;
import com.venusource.app.qywechat.bean.WxCpTagAddOrRemoveUsersResult;
import com.venusource.app.qywechat.bean.WxCpTagGetResult;
import com.venusource.app.qywechat.bean.WxCpUser;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * <pre>
 *
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpTagServiceImplTest {
  @Inject
  protected WxCpService wxService;

  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  protected String tagId;

  @Test
  public void testCreate() throws Exception {
    this.tagId = this.wxService.getTagService().create("测试标签" + System.currentTimeMillis());
    System.out.println(this.tagId);
  }

  @Test(dependsOnMethods = "testCreate")
  public void testUpdate() throws Exception {
    this.wxService.getTagService().update(this.tagId, "测试标签-改名" + System.currentTimeMillis());
  }

  @Test(dependsOnMethods = {"testUpdate", "testCreate"})
  public void testListAll() throws Exception {
    List<WxCpTag> tags = this.wxService.getTagService().listAll();
    assertNotEquals(tags.size(), 0);
  }

  @Test(dependsOnMethods = {"testListAll", "testUpdate", "testCreate"})
  public void testAddUsers2Tag() throws Exception {
    List<String> userIds = Splitter.on("|").splitToList(this.configStorage.getUserId());
    WxCpTagAddOrRemoveUsersResult result = this.wxService.getTagService().addUsers2Tag(this.tagId, userIds, null);
    assertEquals(result.getErrCode(), Integer.valueOf(0));
  }

  @Test(dependsOnMethods = {"testAddUsers2Tag", "testListAll", "testUpdate", "testCreate"})
  public void testListUsersByTagId() throws Exception {
    List<WxCpUser> users = this.wxService.getTagService().listUsersByTagId(this.tagId);
    assertNotEquals(users.size(), 0);
  }

  @Test(dependsOnMethods = {"testListUsersByTagId", "testAddUsers2Tag", "testListAll", "testUpdate", "testCreate"})
  public void testRemoveUsersFromTag() throws Exception {
    List<String> userIds = Splitter.on("|").splitToList(this.configStorage.getUserId());
    WxCpTagAddOrRemoveUsersResult result = this.wxService.getTagService().removeUsersFromTag(this.tagId, userIds, null);
    assertEquals(result.getErrCode(), Integer.valueOf(0));
  }

  @Test(dependsOnMethods = {"testRemoveUsersFromTag", "testListUsersByTagId", "testAddUsers2Tag", "testListAll", "testUpdate", "testCreate"})
  public void testDelete() throws Exception {
    this.wxService.getTagService().delete(this.tagId);
  }

  @Test
  public void testGet() throws WxErrorException {
    String apiResultJson = "{\"errcode\": 0,\"errmsg\": \"ok\",\"userlist\": [{\"userid\": \"0124035\",\"name\": \"王五\"},{\"userid\": \"0114035\",\"name\": \"梦雪\"}],\"partylist\": [9576,9567,9566],\"tagname\": \"测试标签-001\"}";
    WxCpService wxService = mock(WxCpService.class);
    when(wxService.get("https://qyapi.weixin.qq.com/cgi-bin/tag/get?tagId=150", null)).thenReturn(apiResultJson);
    when(wxService.getTagService()).thenReturn(new WxCpTagServiceImpl(wxService));

    WxCpTagService wxCpTagService = wxService.getTagService();

    WxCpTagGetResult wxCpTagGetResult = wxCpTagService.get(String.valueOf(150));

    assertEquals(0, wxCpTagGetResult.getErrcode().intValue());

    assertEquals(2, wxCpTagGetResult.getUserlist().size());
    assertEquals(3, wxCpTagGetResult.getPartylist().size());
    assertEquals("测试标签-001", wxCpTagGetResult.getTagname());


  }

}
