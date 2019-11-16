package com.qq.weixin.work.demo.wxworksdkdemo.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.venusource.app.qywechat.base.api.WxConsts;
import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.qq.weixin.work.demo.wxworksdkdemo.utils.JsonUtils;
import com.qq.weixin.work.demo.wxworksdkdemo.utils.TextBuilder;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {

    if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
      //TODO 可以选择将消息保存到本地
    }

    //TODO 组装回复消息
    String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

    return new TextBuilder().build(content, wxMessage, cpService);

  }

}
