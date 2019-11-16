package com.qq.weixin.work.demo.wxworksdkdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qq.weixin.work.demo.wxworksdkdemo.config.WxCpConfiguration;
import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.bean.WxCpMessage;
import com.venusource.app.qywechat.bean.article.NewArticle;

@RestController
public class SendMsgController {
	
	@RequestMapping("/pushmsg")
	public String sendmsg(@RequestParam(name = "agentId", required = true) Integer agentId,@RequestParam(name = "userId", required = true) String userId){
		final WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
		NewArticle article = new NewArticle();
		article.setDescription("今年中秋节公司有豪礼相送");
		article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573712894685&di=48cc2a95ee9fc392a4b8a6adeb65e79c&imgtype=0&src=http%3A%2F%2Fpic45.nipic.com%2F20140805%2F12066912_090718110973_2.jpg");
		article.setTitle("中秋节礼品领取");
		article.setUrl("http://www.ahycgy.com.cn/WEB/Page/index.jsp");
		WxCpMessage message = WxCpMessage.NEWS().toUser(userId).addArticle(article).build();
		try {
			wxCpService.messageSend(message);
			return "发送成功";
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "发送失败";
	}
}
