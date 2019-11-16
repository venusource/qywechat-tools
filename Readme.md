## 企业微信开发 Java SDK（支持私有化版本）

本项目源码基于GitHub：https://github.com/wechat-group/WxJava 3.2版本进行改造，支持私有化版本企业微信。源代码除对私有化版本支持部分外，版权均属于【微信开发者联盟】

## 使用方法

### maven
```
<dependency>
  <groupId>com.venusource.app.qywechat</groupId>
  <artifactId>qywechat-sdk</artifactId>
  <version>3.2.0</version>
</dependency>
```

### gradle

```
implementation 'com.venusource.app.qywechat:qywechat-sdk:3.2.0'
```
## example

### 基本使用

[qywechat-demo](https://github.com/venusource/qywechat-tools/tree/master/qywechat-demo)

### SpringBoot

Spring boot用户可以参考下面的例子，或者以此模板直接开发：

[wxworksdkdemo](https://github.com/venusource/qywechat-tools/tree/master/wxworksdkdemo)

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