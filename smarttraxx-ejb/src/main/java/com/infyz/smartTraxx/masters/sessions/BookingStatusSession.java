package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.primefaces.event.SelectEvent;

import com.infyz.smartTraxx.master.Resouces.BookingStatusResource;
import com.infyz.smartTraxx.master.entities.IcBookingStatus;
import com.infyz.smartTraxx.master.entities.IcStatus;
@Named
@SessionScoped
public class BookingStatusSession implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em;
	@Named
	@Produces
	private IcBookingStatus selectedBookingStatus;
	@Inject
	BookingStatusResource resource;

	private List<IcBookingStatus> icBookingStatus;
	private List<IcBookingStatus> selectbookStatus;
	private boolean managed;
	private boolean disabled;
	private Integer id;
     
	public void getIcStatusList() {
		icBookingStatus = resource.findAllOrderedByCode();
	}

	public void add() {
		System.out.println("add method called");
		selectedBookingStatus = new IcBookingStatus();
	}

	public void Edit(int id) {
		System.out.println("hi edit" + id);
		selectedBookingStatus = resource.getbookingStatusById(id);
	}

	public void Select() {
		System.out.println("hi select");
		selectedBookingStatus=new IcBookingStatus();
	}
	@Transactional
	public void persist() {
		System.out.println("persist method executed");
		selectedBookingStatus.setCreatedBy(143);
		selectedBookingStatus.setCreatedDate(new Date());
		selectedBookingStatus.setModifiedBy(143);
		selectedBookingStatus.setModifiedDate(new Date());
		em.persist(selectedBookingStatus);
		icBookingStatus.add(0, selectedBookingStatus);

	}


	@Transactional
	public String update() {
		System.out.println("hi update");
		selectedBookingStatus.setCreatedBy(143);
		selectedBookingStatus.setModifiedBy(143);
		selectedBookingStatus.setCreatedDate(new Date());
		selectedBookingStatus.setModifiedDate(new Date());
		em.merge(selectedBookingStatus);
		getIcBookingStatus();
		return "/Test/IcBookingStatus.xhtml?faces-redirect=true";
	}

	@Transactional
	public void delete(int id) {
		System.out.println("hi delete");
		selectedBookingStatus = resource.getbookingStatusById(id);
		em.remove(selectedBookingStatus);
	}

	public String cancel() {
		return "index.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "/Test/IcBookingStatus.xhtml?faces-redirect=true";
	}

	public String navigate() {
		return "/Test/IcBookingStatus.xhtml?faces-redirect=true";
	}


	@Transactional
	public void BulkDelete() {
		for (IcBookingStatus s : selectbookStatus) {
			em.remove(em.contains(s) ? s : em.merge(s));
		}
		getIcBookingStatus();
	}


	@Transactional 
	public void Search1(int id) { 
		icBookingStatus=new ArrayList<IcBookingStatus>();
		System.out.println("hi search");
		System.out.println(id);
		icBookingStatus=resource.getsearchById(id); 
		System.out.println(icBookingStatus.size());
		for(IcBookingStatus s:icBookingStatus) 
		{
			System.out.println(s.getBookingStatusId()+" "+s.getCode()); 
		}
		getIcBookingStatus(); 
	}

	@Transactional
	public void Bupdate()
	{
		System.out.println("hi Bupdate");
		for(IcBookingStatus s1:selectbookStatus)
		{
			s1.setCode(selectedBookingStatus.getCode());
			s1.setDescription(selectedBookingStatus.getDescription());
			System.out.println(s1.getCode());
			System.out.println(s1.getDescription());
			em.merge(s1);
			getIcBookingStatus();
		}
	}
	private String statusCode;
	private String name;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	public void onRowSelect(SelectEvent<IcBookingStatus> event) {
		System.out.println(event);
		FacesMessage msg = new FacesMessage("Bookingstatus Selected", event.getObject().getCode());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<IcBookingStatus> getIcBookingStatus() {
		return icBookingStatus;
	}

	public void setIcBookingStatus(List<IcBookingStatus> icBookingStatus) {
		this.icBookingStatus = icBookingStatus;
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<IcBookingStatus> getSelectbookStatus() {
		return selectbookStatus;
	}

	public void setSelectbookStatus(List<IcBookingStatus> selectbookStatus) {
		this.selectbookStatus = selectbookStatus;
	}

	public IcBookingStatus getSelectedBookingStatus() {
		return selectedBookingStatus;
	}

	public void setSelectedBookingStatus(IcBookingStatus selectedBookingStatus) {
		this.selectedBookingStatus = selectedBookingStatus;
	}

}

