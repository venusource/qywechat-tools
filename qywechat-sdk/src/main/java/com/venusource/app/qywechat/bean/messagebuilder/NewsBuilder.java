package com.venusource.app.qywechat.bean.messagebuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.venusource.app.qywechat.base.api.WxConsts;
import com.venusource.app.qywechat.bean.WxCpMessage;
import com.venusource.app.qywechat.bean.article.NewArticle;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

  private List<NewArticle> articles = new ArrayList<>();

  public NewsBuilder() {
    this.msgType = WxConsts.KefuMsgType.NEWS;
  }

  public NewsBuilder addArticle(NewArticle... articles) {
    Collections.addAll(this.articles, articles);
    return this;
  }

  public NewsBuilder articles(List<NewArticle> articles) {
    this.articles = articles;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
