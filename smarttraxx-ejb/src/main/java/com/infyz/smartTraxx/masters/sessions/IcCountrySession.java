package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.infyz.smartTraxx.master.Resouces.IcRegionResource;
import com.infyz.smartTraxx.master.Resouces.IccountryResource;
import com.infyz.smartTraxx.master.entities.IcCountry;
import com.infyz.smartTraxx.master.entities.IcRegion;

@Named("icConutrySession")
@SessionScoped
@Path("/country")
public class IcCountrySession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	@Named
	@Produces
	private IcCountry selectedcontryActivity;
	@Inject
	IccountryResource resource;
	@Named
	@Produces
	private List<IcCountry> country;
	private List<IcCountry> selectCountry;
	@Inject
	IcRegionResource icRegionResource;

	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;
	private List<IcRegion> regionsList;

	public List<IcRegion> getRegionsList() {
		return regionsList;
	}

	public void setRegionsList(List<IcRegion> regionsList) {
		this.regionsList = regionsList;
	}

	@PostConstruct
	public void getCountryList() {
		country =resource.findAllOrderedByCode(); 
		regionsList=new ArrayList<IcRegion>(0);
		regionsList=icRegionResource.getIcRegion_Records();
	}
	@GET
	@Path("/msg")
	@javax.ws.rs.Produces("Text/Plain")
	public String getClichedMessage() {
		return "Hello,Smatrtraxx";
	}

	@Named
	@Produces
	public List<IcCountry> getContries() {
		return country;
	}

	public void add() {
		System.out.println("add method called");
		selectedcontryActivity = new IcCountry();
	}
	@Transactional
	public String persist() {
		selectedcontryActivity.setCreatedBy(01);
		selectedcontryActivity.setModifiedBy(01);
		selectedcontryActivity.setCreatedDate(new Date());
		selectedcontryActivity.setModifiedDate(new Date());
		em.persist(selectedcontryActivity);
		getContries();
		return "Test/country_view.xhtml?faces-redirect=true";
	}

	@Transactional
	public String update() {
		selectedcontryActivity.setCreatedBy(01);
		selectedcontryActivity.setModifiedBy(01);
		selectedcontryActivity.setCreatedDate(new Date());
		selectedcontryActivity.setModifiedDate(new Date());
		em.merge(selectedcontryActivity);
		getContries();
		return "Test/country_view.xhtml?faces-redirect=true";
	}

	@Transactional
	public String delete(int id) {
		System.out.println("hi delete");
		selectedcontryActivity = resource.getCountryById(id);
		selectedcontryActivity = em.find(IcCountry.class, selectedcontryActivity.getCountryId());
		em.remove(selectedcontryActivity);
		return "Test/country_view.xhtml?faces-redirect=true";
	}
	public void Edit(int id) {
		System.out.println("hi edit" + id);
		selectedcontryActivity = resource.getCountryById(id);
	}
	public void Select() {
		System.out.println("hi select");
		selectedcontryActivity=new IcCountry();
	}

	@Transactional
	public void BulkDelete() {
		for (IcCountry s : selectCountry ) {
			em.remove(em.contains(s) ? s : em.merge(s));
		}
		getCountry();
	}
	public List<IcCountry> getCountry() {
		return country;
	}

	public void setCountry(List<IcCountry> country) {
		this.country = country;
	}

	@Transactional
	public void Bupdate()
	{
		System.out.println("hi Bupdate");
		for(IcCountry s1:selectCountry)
		{
			s1.setName(selectedcontryActivity.getName());
			s1.setCountryCode(selectedcontryActivity.getCountryCode());
			s1.setfActive(selectedcontryActivity.getfActive());
			s1.setCompanyId(selectedcontryActivity.getCompanyId());
			System.out.println(s1.getName());
			System.out.println(s1.getCountryCode());
			System.out.println(s1.getfActive());
			System.out.println(s1.getCompanyId());
			em.merge(s1);
			getCountry();
		}
	}
	public String cancel() {
		return "Test/country_view.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "Test/country_view.xhtml?faces-redirect=true";
	}

	public String navigate() {
		return "Test/country_view.xhtml?faces-redirect=true";
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

	public List<IcCountry> getSelectCountry() {
		return selectCountry;
	}

	public void setSelectCountry(List<IcCountry> selectCountry) {
		this.selectCountry = selectCountry;
	}
	@POST
	@Path("/query1")
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String persistData(IcCountry country)
	{
		try {
			country.setCreatedBy(111);
			country.setModifiedBy(111);
			country.setCreatedDate(new Date());
			country.setModifiedDate(new Date());
			country.setIcRegion(null);
		  em.persist(country);
	}catch(Exception e){
		e.printStackTrace();
	}
		return "Data saved Sucessfully";
	}
}
