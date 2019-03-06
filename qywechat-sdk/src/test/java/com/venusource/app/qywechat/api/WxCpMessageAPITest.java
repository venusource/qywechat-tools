package com.venusource.app.qywechat.api;

import com.google.inject.Inject;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.api.WxConsts;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.bean.WxCpMessage;
import com.venusource.app.qywechat.bean.WxCpMessageSendResult;

import org.testng.annotations.*;

import static org.testng.Assert.*;

/***
 * 测试发送消息
 * @author Daniel Qian
 *
 */
@Test(groups = "customMessageAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpMessageAPITest {

  @Inject
  protected WxCpService wxService;

  private ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  @BeforeTest
  public void setup() {
    configStorage = (ApiTestModule.WxXmlCpInMemoryConfigStorage) this.wxService.getWxCpConfigStorage();
  }

  public void testSendMessage() throws WxErrorException {
    WxCpMessage message = new WxCpMessage();
//    message.setAgentId(configStorage.getAgentId());
    message.setMsgType(WxConsts.KefuMsgType.TEXT);
    message.setToUser(configStorage.getUserId());
    message.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");

    WxCpMessageSendResult messageSendResult = this.wxService.messageSend(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }

  @Test
  public void testSendMessage1() throws WxErrorException {
    WxCpMessage message = WxCpMessage
      .TEXT()
//      .agentId(configStorage.getAgentId())
      .toUser(configStorage.getUserId())
      .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
      .build();

    WxCpMessageSendResult messageSendResult = this.wxService.messageSend(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }
}
