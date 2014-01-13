package org.qcun.wx.message;

public class ToNewsMessage extends ToMessage
{
  private int articleCount;
  private TextImageMessage[] articles;

  public ToNewsMessage(TextImageMessage[] articles)
  {
    super.setMsgType("news");
    this.articles = articles;
    this.articleCount = articles.length;
  }

  public int getArticleCount()
  {
    return this.articleCount;
  }

  public TextImageMessage[] getArticles() {
    return this.articles;
  }

  private void createNewsMessage() {
    TextImageMessage[] arrayOfTextImageMessage;
    this.articleCount = 0;
    StringBuilder sb = new StringBuilder();
    sb.append("<Articles>");
    int j = (arrayOfTextImageMessage = this.articles).length; for (int i = 0; i < j; ++i) { TextImageMessage imageMessage = arrayOfTextImageMessage[i];

      sb.append("<item>");
      sb.append("<Title><![CDATA[" + imageMessage.getTitle() + "]]></Title>");
      sb.append("<Description><![CDATA[" + imageMessage.getDescription() + "]]></Description>");
      sb.append("<PicUrl><![CDATA[" + imageMessage.getPicUrl() + "]]></PicUrl>");
      sb.append("<Url><![CDATA[" + imageMessage.getUrl() + "]]></Url>");
      sb.append("");
      sb.append("</item>");
      this.articleCount += 1;
    }
    sb.append("</Articles>");
  }
}