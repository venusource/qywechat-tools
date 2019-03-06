package com.venusource.app.qywechat.message;

import java.util.Map;

import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 * 处理微信推送消息的处理器接口
 *
 * @author Daniel Qian
 */
public interface WxCpMessageHandler {

  /**
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxCpService
   * @param sessionManager
   * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
   */
  WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
                           Map<String, Object> context,
                           WxCpService wxCpService,
                           WxSessionManager sessionManager) throws WxErrorException;

}
