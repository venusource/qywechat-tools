package com.qq.weixin.work.demo.wxworksdkdemo.handler;

import java.util.Map;

import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.session.WxSessionManager;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.bean.WxCpXmlMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutMessage;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/8/27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class EnterAgentHandler extends AbstractHandler {

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService wxCpService, WxSessionManager sessionManager) throws WxErrorException {
        // do something
    	this.logger.debug(wxMessage.getContent());
        return null;
    }
}
