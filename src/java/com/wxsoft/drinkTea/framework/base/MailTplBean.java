/**   
 * @文件名称: MailTplBean.java
 * @类路径: com.wxltsoft.framework.bean
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-4-3 下午03:19:56  
 */

package com.wxsoft.drinkTea.framework.base;

/**
 * @类功能说明：邮件模版
 * @类修改者：kasiait
 * @修改日期：2013-4-3
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-4-3 下午03:19:56
 */

public class MailTplBean extends BaseBean {
	private static final long serialVersionUID = 8384428265078019879L;

	private String    mailcode;
	private String    subject;
	private String    content;
	private int       timeout;
	private String    type;
	public MailTplBean() {
		super();
	}
	public MailTplBean(String mailcode, String subject, String type, 
			String content, int timeout) {
		super();
		this.mailcode = mailcode;
		this.subject = subject;
		this.type    = type;
		this.content = content;
		this.timeout = timeout;
	}

	public String getMailcode() {
		return mailcode;
	}
	public void setMailcode(String mailcode) {
		this.mailcode = mailcode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
