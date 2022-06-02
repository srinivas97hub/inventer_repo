package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.event.*;

import com.infyz.smartTraxx.master.Resouces.IcStatusResource;
import com.infyz.smartTraxx.master.entities.IcStatus;
import com.infyz.smarttraxx.pojos.statusPojo;
@Named("icStatusSession")
@SessionScoped
@Path("/status")
public class IcStatusSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	/*
	 * @Named
	 * 
	 * @Produces
	 */
	private IcStatus selectedstatusActivity;
	@Inject
	IcStatusResource resource;

	private List<IcStatus> icstatus;
	private List<statusPojo> pojolist;
	private List<IcStatus> selectStatus;
	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IcStatus> getIcStatusList() {
		icstatus = resource.getIcStatus_Records();
		return icstatus;
	}
	public void add() {
		System.out.println("add method called");
		selectedstatusActivity = new IcStatus();
	}

	public void Edit(int id) {
		System.out.println("hi edit" + id);
		selectedstatusActivity = resource.getStatusById(id);
	}

	public void Select() {
		System.out.println("hi select");
		selectedstatusActivity=new IcStatus();
	}
	@Transactional
	public void persist() {
		System.out.println("persist method executed");
		selectedstatusActivity.setCreatedBy(01);
		selectedstatusActivity.setModifiedBy(01);
		selectedstatusActivity.setCreatedDate(new Date());
		selectedstatusActivity.setModifiedDate(new Date());
		em.persist(selectedstatusActivity);
		icstatus.add(0, selectedstatusActivity);
	}

	@Transactional
	public String update() {
		System.out.println("hi update");
		selectedstatusActivity.setCreatedBy(01);
		selectedstatusActivity.setModifiedBy(01);
		selectedstatusActivity.setCreatedDate(new Date());
		selectedstatusActivity.setModifiedDate(new Date());
		em.merge(selectedstatusActivity);
		getIcstatus();
		return "/Test/status_view.xhtml?faces-redirect=true";
	}

	@Transactional
	public void delete(int id) {
		System.out.println("hi delete");
		selectedstatusActivity = resource.getStatusById(id);
		em.remove(selectedstatusActivity);
	}

	public String cancel() {
		return "index.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "/Test/status_view.xhtml?faces-redirect=true";
	}

	public String navigate() {
		return "/Test/status_view.xhtml?faces-redirect=true";
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	private int statusId;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public IcStatus getSelectedstatusActivity() {
		return selectedstatusActivity;
	}

	public void setSelectedstatusActivity(IcStatus selectedstatusActivity) {
		this.selectedstatusActivity = selectedstatusActivity;
	}

	public List<IcStatus> getSelectStatus() {
		return selectStatus;
	}

	public void setSelectStatus(List<IcStatus> selectStatus) {
		this.selectStatus = selectStatus;
	}
 
	public List<statusPojo> getPojolist() {
		return pojolist;
	}
	public void setPojolist(List<statusPojo> pojolist) {
		this.pojolist = pojolist;
	}
	@Transactional
	public void BulkDelete() {
		for (IcStatus s : selectStatus) {
			em.remove(em.contains(s) ? s : em.merge(s));
		}
		getIcstatus();
	}


	@Transactional 
	public void Search1(int id) { 
		icstatus=new ArrayList<IcStatus>();
		System.out.println("hi search");
		System.out.println(id);
		icstatus=resource.getsearchById(id); 
		System.out.println(icstatus.size());
		for(IcStatus s:icstatus) 
		{
			System.out.println(s.getStatusId()+" "+s.getStatusInternalDesc()); 
		}
		getIcstatus(); 
	}

	@Transactional
	public void Bupdate()
	{
		System.out.println("hi Bupdate");
		for(IcStatus s1:selectStatus)
		{
			s1.setName(selectedstatusActivity.getName());
			s1.setStatusCode(selectedstatusActivity.getStatusCode());
			s1.setfActive(selectedstatusActivity.getfActive());
			s1.setStatusInternalDesc(selectedstatusActivity.getStatusInternalDesc());
			s1.setStatusSequence(selectedstatusActivity.getStatusSequence());
			System.out.println(s1.getName());
			System.out.println(s1.getStatusCode());
			System.out.println(s1.getfActive());
			System.out.println(s1.getStatusInternalDesc());
			em.merge(s1);
			getIcstatus();
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

	public void search() {
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("From IcStatus where ");

		boolean enter = false;
		if (statusCode != null) {
			if (enter) {
				enter = true;
				queryStr.append("and statusCode='" + statusCode + "'");
			} else {
				enter = true;
				queryStr.append("statusCode='" + statusCode + "'");
			}

		}
		if (name != null) {
			if (enter) {
				enter = true;
				queryStr.append("and name='" + name + "'");
			} else {
				enter = true;
				queryStr.append("name='" + name + "'");
			}

		}
		System.out.println(queryStr.toString());
		try {
			Query query = em.createQuery(queryStr.toString());
			icstatus = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Named
	 * 
	 * @Produces
	 */
	public List<IcStatus> getIcstatus() {
		return icstatus;
	}

	public void setIcstatus(List<IcStatus> icstatus) {
		this.icstatus = icstatus;
	}

	public void onRowSelect(SelectEvent<IcStatus> event) {
		System.out.println(event);
		FacesMessage msg = new FacesMessage("status Selected", event.getObject().getStatusCode());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	@GET
	@Path("/plist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<statusPojo> getIcStatusList1() {
		pojolist = resource.getIcStatus_Records1();
		return pojolist;
	}
	@GET
	@Path("/msg")
	@Produces("Text/Plain")
	public String getMessage() {
		return "Hello,Smatrtraxx";
	}
	@POST
	@Path("/query1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String persistData(IcStatus status)
	{
		try {
			status.setCreatedBy(111);
			status.setModifiedBy(111);
			status.setCreatedDate(new Date());
			status.setModifiedDate(new Date());
		  em.persist(status);
	}catch(Exception e){
		e.printStackTrace();
	}
		return "Data saved Sucessfully";
	}
	@GET
	@Path("/query2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public statusPojo getRecords(@QueryParam("id") int id)
	{
	     return resource.getStatusById1(id);
	
	}
	@DELETE
	@Path("/query3")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String deleteData(@QueryParam("id") int id)
	{
		IcStatus selectedstatusActivity=new IcStatus();
		selectedstatusActivity = resource.getStatusById(id);
		em.remove(selectedstatusActivity);
		return "Data Deleted Sucessfully";
	}
	@PUT
	@Path("/query4")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String updateData(IcStatus status) {
		status.setCreatedBy(111);
		status.setModifiedBy(111);
		status.setCreatedDate(new Date());
		status.setModifiedDate(new Date());
		em.merge(status);
		return "Update SucessFully";
	}
}
