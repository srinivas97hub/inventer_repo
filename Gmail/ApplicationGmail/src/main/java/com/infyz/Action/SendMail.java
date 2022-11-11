package com.infyz.Action;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.infyz.Bean.Mail_Bean;
import com.infyz.Resource.AuthenticateMail;
@SessionScoped
@Named
@Path("/mail")
public class SendMail implements Serializable{
	private static final long serialVersionUID = 1L;
	
private Mail_Bean mailbean;
@Inject
AuthenticateMail authenticateMail;
private String to;
private String sub;
private String msg;
private String file;
public String SentMail()
{ 
	System.out.println(file);
	authenticateMail.sendMail(to, sub, msg,file);
	System.out.println("mail sent Sucessfully");
	return "";
}
public String cancel()
{
	return "";
}
@GET
@Path("/send")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public String JsonMail(Mail_Bean bean)
{     
	authenticateMail.sendMail(bean.getTo(), bean.getSub(), bean.getMsg(), bean.getFile());
	System.out.println("mailsent sucessfully");
	return "";

}
public Mail_Bean getMailbean() {
	return mailbean;
}
public void setMailbean(Mail_Bean mailbean) {
	this.mailbean = mailbean;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getSub() {
	return sub;
}
public void setSub(String sub) {
	this.sub = sub;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getFile() {
	return file;
}
public void setFile(String file) {
	this.file = file;
}

}