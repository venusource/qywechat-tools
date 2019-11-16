## 使用说明

使用前，请更改`application.yaml`文件中的相关配置，如下所示：
```
wechat:
  cp:
    corpId: your_corpid
    wxworkServer: your_local_wxwork_server
    appConfigs:
      - agentId: your_agentid
        secret: your_secret
        token: your_token
        aesKey: your_aesKey
      - agentId: your_agentid_2
        secret: your_secret
        token: your_token
        aesKey: your_aesKey
```