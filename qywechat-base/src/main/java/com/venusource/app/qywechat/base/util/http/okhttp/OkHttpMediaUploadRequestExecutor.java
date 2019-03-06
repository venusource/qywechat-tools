package com.venusource.app.qywechat.base.util.http.okhttp;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venusource.app.qywechat.base.bean.result.WxMediaUploadResult;
import com.venusource.app.qywechat.base.error.WxError;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.http.MediaUploadRequestExecutor;
import com.venusource.app.qywechat.base.util.http.RequestHttp;

import java.io.File;
import java.io.IOException;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class OkHttpMediaUploadRequestExecutor extends MediaUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkHttpMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaUploadResult execute(String uri, File file) throws WxErrorException, IOException {
    logger.debug("OkHttpMediaUploadRequestExecutor is running");
    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    RequestBody body = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file))
      .build();
    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMediaUploadResult.fromJson(responseContent);
  }

}
