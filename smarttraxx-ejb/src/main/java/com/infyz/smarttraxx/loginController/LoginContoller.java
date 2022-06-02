package com.infyz.smarttraxx.loginController;

import java.io.Serializable;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class LoginContoller implements Serializable{

	private String uname;
	private String pword;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	public String login()
	{
		System.out.println("hi login");
		System.out.println(uname);
		System.out.println(pword);
		if((uname.equals("infyz"))&&(pword.equals("logica"))) {
		return "adminpage.xhtml?faces-redirect=true";
		}
		else {
			return "failure";
		}
	}
	  public void reset() {
	        PrimeFaces.current().resetInputs("myform:panel");
	    }
}
