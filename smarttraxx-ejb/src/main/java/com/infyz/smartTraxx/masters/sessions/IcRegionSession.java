package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.infyz.smartTraxx.master.Resouces.IcRegionResource;
import com.infyz.smartTraxx.master.entities.IcRegion;


@Named("icRegionSession")
@SessionScoped
@Path("/region")
public class IcRegionSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	@Named
	@Produces
	private  IcRegion selectedregionActivity;
	@Inject
	IcRegionResource resource;

	private List<IcRegion> regions;
     
	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;
	@GET
	@Path("/list")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public List<IcRegion> getIcRegionsList() { 
		regions =resource.getIcRegion_Records();
		return regions;
		}
		
		public List<IcRegion> getRegions() {
			regions =resource.getIcRegion_Records();
			return regions;
		}
	public void add() {
		System.out.println("add method called");
		selectedregionActivity = new IcRegion();
	}
	public void Edit(int id) {
		System.out.println("hi edit"+id);
		selectedregionActivity=resource.getRegionById(id);
	}
	public void Select(int id)
	{
		System.out.println("hi edit"+id);
		selectedregionActivity=resource.getRegionById(id);

	}
	@Transactional
	public String persist() {
		System.out.println("persist method executed");
		
		selectedregionActivity. setCreatedBy(01);
		selectedregionActivity. setModifiedBy(01);
		selectedregionActivity. setCreatedDate(new Date());
		selectedregionActivity. setModifiedDate(new Date());
		em.persist(selectedregionActivity);
		getRegions();
		return "/Test/region_view.xhtml?faces-redirect=true";
	}

	@Transactional
	public String update() {
		System.out.println("hi update");
		selectedregionActivity. setCreatedBy(01);
		selectedregionActivity. setModifiedBy(01);
		selectedregionActivity. setCreatedDate(new Date());
		selectedregionActivity. setModifiedDate(new Date());
		em.merge(selectedregionActivity);
		getRegions();
		return "/Test/region_view.xhtml?faces-redirect=true";
	}
@Transactional
public String delete()
{
	System.out.println("hi delete");
	selectedregionActivity= em.find(IcRegion.class,selectedregionActivity.getRegionId());
	em.remove(selectedregionActivity);
	return "/Test/region_view.xhtml?faces-redirect=true";
}
	public String cancel() {
		return"index.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "/Test/region_view.xhtml?faces-redirect=true";
	}
	public String navigate(){
		return "/Test/region_view.xhtml?faces-redirect=true";
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
	@GET
	@Path("/msg")
	@javax.ws.rs.Produces("Text/Plain")
	public String getMessage() {
		return "Hello,Smatrtraxx";
	}
	@POST
	@Path("/query1")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String persistData(IcRegion region)
	{
		try {
			region.setCreatedBy(111);
			region.setModifiedBy(111);
			region.setCreatedDate(new Date());
			region.setModifiedDate(new Date());
		  em.persist(region);
	}catch(Exception e){
		e.printStackTrace();
	}
		return "Data saved Sucessfully";
	}
	@GET
	@Path("/query2")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public IcRegion getRecords(@QueryParam("id") int id)
	{
		return resource.getRegionById(id);
	}
	@POST
	@Path("/query3")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String deleteData(@QueryParam("id") int id)
	{
		IcRegion region=new IcRegion();
		region = resource.getRegionById(id);
		em.remove(region);
		return "Data Deleted Sucessfully";
	}
	@PUT
	@Path("/query4")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String updateData(IcRegion region) {
		region.setCreatedBy(111);
		region.setModifiedBy(111);
		region.setCreatedDate(new Date());
		region.setModifiedDate(new Date());
		em.merge(region);
		return "Update SucessFully";
	}
}
