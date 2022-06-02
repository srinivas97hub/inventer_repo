package com.infyz.smarttraxx.pojos;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name="ic_status")
@XmlRootElement
public class statusPojo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="STATUS_ID")
	private Integer statusId;

	@Column(name="NAME")
	private String name;

	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="STATUS_DESC")
	private String statusDesc;

	@Column(name="STATUS_INTERNAL_DESC")
	private String statusInternalDesc;

	@Column(name="STATUS_SEQUENCE")
	private int statusSequence;
	
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getStatusInternalDesc() {
		return statusInternalDesc;
	}
	public void setStatusInternalDesc(String statusInternalDesc) {
		this.statusInternalDesc = statusInternalDesc;
	}
	public int getStatusSequence() {
		return statusSequence;
	}
	public void setStatusSequence(int statusSequence) {
		this.statusSequence = statusSequence;
	}
}
