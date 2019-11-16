package com.qq.weixin.work.demo.wxworksdkdemo.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.qq.weixin.work.demo.wxworksdkdemo.utils.JsonUtils;
import com.qq.weixin.work.demo.wxworksdkdemo.utils.TextBuilder;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 * 通讯录变更事件处理器.
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ContactChangeHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    String content = "收到通讯录变更事件，内容：" + JsonUtils.toJson(wxMessage);
    this.logger.info(content);

    return new TextBuilder().build(content, wxMessage, cpService);
  }

}
