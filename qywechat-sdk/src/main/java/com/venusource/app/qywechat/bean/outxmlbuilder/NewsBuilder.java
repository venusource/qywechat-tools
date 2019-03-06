package com.venusource.app.qywechat.bean.outxmlbuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.venusource.app.qywechat.bean.WxCpXmlOutNewsMessage;
import com.venusource.app.qywechat.bean.WxCpXmlOutNewsMessage.Item;

/**
 * 图文消息builder
 *
 * @author Daniel Qian
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxCpXmlOutNewsMessage> {
  private List<Item> articles = new ArrayList<>();

  public NewsBuilder addArticle(Item... items) {
    Collections.addAll(this.articles, items);
    return this;
  }

  public NewsBuilder articles(List<Item> articles){
    this.articles = articles;
    return this;
  }

  @Override
  public WxCpXmlOutNewsMessage build() {
    WxCpXmlOutNewsMessage m = new WxCpXmlOutNewsMessage();
    for (Item item : this.articles) {
      m.addArticle(item);
    }
    setCommon(m);
    return m;
  }

}
