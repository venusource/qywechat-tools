package com.venusource.app.qywechat.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venusource.app.qywechat.api.WxCpService;
import com.venusource.app.qywechat.api.impl.WxCpServiceImpl;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.config.WxCpInMemoryConfigStorage;

public class Example {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final static String CORP_Id = "";
	private final static Integer AGENT_Id = 1000;
	private final static String SECRET = "";
	private final static String TOKEN = "";
	private final static String AESKEY = "";
	private final static String SERVER = "";
	
	
	public static WxCpService initWxCpService(){
		WxCpInMemoryConfigStorage configStorage = new WxCpInMemoryConfigStorage();
        configStorage.setCorpId(CORP_Id);
        configStorage.setAgentId(AGENT_Id);
        configStorage.setCorpSecret(SECRET);
        configStorage.setToken(TOKEN);
        configStorage.setAesKey(AESKEY);
        configStorage.setWxworkServer(SERVER);
        configStorage.setLocalVersion(true);
        WxCpService service = new WxCpServiceImpl();
        service.setWxCpConfigStorage(configStorage);
        return service;
	}
	
	public static void main(String[] args){
		WxCpService cps= initWxCpService();
		try {
			
			System.out.println(cps.getAgentService().list().get(0).toJson());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("haha");
	}

}
