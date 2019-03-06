package com.venusource.app.qywechat.base.util.http.jodd;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.venusource.app.qywechat.base.error.WxError;
import com.venusource.app.qywechat.base.error.WxErrorException;
import com.venusource.app.qywechat.base.util.fs.FileUtils;
import com.venusource.app.qywechat.base.util.http.BaseMediaDownloadRequestExecutor;
import com.venusource.app.qywechat.base.util.http.HttpResponseProxy;
import com.venusource.app.qywechat.base.util.http.RequestHttp;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class JoddHttpMediaDownloadRequestExecutor extends BaseMediaDownloadRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpMediaDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public File execute(String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpRequest request = HttpRequest.get(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);

    String contentType = response.header("Content-Type");
    if (contentType != null && contentType.startsWith("application/json")) {
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new WxErrorException(WxError.fromJson(response.bodyText()));
    }

    String fileName = new HttpResponseProxy(response).getFileName();
    if (StringUtils.isBlank(fileName)) {
      return null;
    }

    try (InputStream inputStream = new ByteArrayInputStream(response.bodyBytes())) {
      return FileUtils.createTmpFile(inputStream,
        FilenameUtils.getBaseName(fileName),
        FilenameUtils.getExtension(fileName),
        super.tmpDirFile);
    }
  }


}
