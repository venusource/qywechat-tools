package com.venusource.app.qywechat.message;

import java.util.Map;

import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;

/**
 * 微信消息拦截器，可以用来做验证
 *
 * @author Daniel Qian
 */
public interface WxCpMessageInterceptor {

  /**
   * 拦截微信消息
   *
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxCpService
   * @param sessionManager
   * @return true代表OK，false代表不OK
   */
  boolean intercept(WxCpXmlMessage wxMessage,
                    Map<String, Object> context,
                    WxCpService wxCpService,
                    WxSessionManager sessionManager) throws WxErrorException;

}
