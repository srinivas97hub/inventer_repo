package com.infyz.Bean;

import java.io.Serializable;

public class Mail_Bean implements Serializable
{
	private static final long serialVersionUID = 1L;
private String to;
private String sub;
private String msg;
private String file;
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
