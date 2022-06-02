package com.infyz.smartTraxx.masters.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.infyz.smartTraxx.master.Resouces.BookingStatusResource;
import com.infyz.smartTraxx.master.Resouces.IcEquipmentresource;
import com.infyz.smartTraxx.master.Resouces.IcRoutedeatisResource;
import com.infyz.smartTraxx.master.Resouces.ItBookingResource;
import com.infyz.smartTraxx.master.Resouces.ItbookingeventsResource;
import com.infyz.smartTraxx.master.entities.IcBookingStatus;
import com.infyz.smartTraxx.master.entities.IcEquipment;
import com.infyz.smartTraxx.master.entities.IcRouteDetail;
import com.infyz.smartTraxx.master.entities.IcStatus;
import com.infyz.smartTraxx.master.entities.ItBookikgRouteDetail;
import com.infyz.smartTraxx.master.entities.ItBooking;
import com.infyz.smartTraxx.master.entities.ItBookingEvent;

@Named("itbookingeventsSession")
@SessionScoped
public class ItbookingeventsSession  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	@Named
	@Produces 
	private ItBookingEvent selectedbookingevent;

	@Inject
	ItbookingeventsResource resource;
	@Inject
	ItBookingResource bookingresource;
	@Inject
	IcRoutedeatisResource routeresource;
	@Inject
	IcEquipmentresource equipmentresource;
	@Inject
	BookingStatusResource Bstatusresource;

	private List<ItBookingEvent> Bookingevent;
	private List<ItBookingEvent> bookingeventlist;


	private List<IcBookingStatus> bookingstatus;;
	private List<IcEquipment> equipment;
	private List<IcRouteDetail> routedeatil;
	private List<ItBooking> Booking;

	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;
	public void getBookingEventList() { 
		Bookingevent =resource.getBooking_Records();
	}
	public void add() {
		System.out.println("add method called");
		selectedbookingevent = new ItBookingEvent();
	}
	public void Edit(int id) {
		System.out.println("hi edit"+id);
		selectedbookingevent=resource.getbookingId(id);
	}
	public void Select(int id)
	{
		System.out.println("hi edit"+id);
		selectedbookingevent=resource.getbookingId(id);

	}
	@Transactional
	public String persist() {
		System.out.println("persist method executed");
		selectedbookingevent. setCreatedBy(01);
		selectedbookingevent. setModifiedBy(01);
		selectedbookingevent. setCreatedDate(new Date());
		selectedbookingevent. setModifiedDate(new Date());
		em.persist(selectedbookingevent);
		getBookingEventList();
		return "/Test/BookingEvent.xhtml?faces-redirect=true";
	}

	@Transactional
	public String update() {
		System.out.println("hi update");
		selectedbookingevent. setCreatedBy(01);
		selectedbookingevent. setModifiedBy(01);
		selectedbookingevent. setCreatedDate(new Date());
		selectedbookingevent. setModifiedDate(new Date());
		em.merge(			selectedbookingevent);
		getBookingEventList();
		return "/Test/BookingEvent.xhtml?faces-redirect=true";
	}
	@Transactional
	public String delete()
	{
		System.out.println("hi delete");
		selectedbookingevent= em.find(ItBookingEvent.class,selectedbookingevent.getBookingEventsId());
		em.remove(selectedbookingevent);
		return "/Test/BookingEvent.xhtml?faces-redirect=true";
	}
	public String cancel() {
		return"index.xhtml?faces-redirect=true";
	}

	public String close() {
		System.out.println("-----------we are in close");
		return "/Test/BookingEvent.xhtml?faces-redirect=true";
	}
	public String navigate(){
		return "/Test/BookingEvent.xhtml?faces-redirect=true";
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public List<ItBookingEvent> getBookingevent() {
		return Bookingevent;
	}
	public void setBookingevent(List<ItBookingEvent> bookingevent) {
		Bookingevent = bookingevent;
	}
	public boolean isManaged() {
		return managed;
	}
	public void setManaged(boolean managed) {
		this.managed = managed;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public List<IcBookingStatus> getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(List<IcBookingStatus> bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	public List<IcEquipment> getEquipment() {
		return equipment;
	}
	public void setEquipment(List<IcEquipment> equipment) {
		this.equipment = equipment;
	}
	public List<IcRouteDetail> getRoutedeatil() {
		return routedeatil;
	}
	public void setRoutedeatil(List<IcRouteDetail> routedeatil) {
		this.routedeatil = routedeatil;
	}
	public List<ItBooking> getBooking() {
		return Booking;
	}
	public void setBooking(List<ItBooking> booking) {
		Booking = booking;
	}
	public List<ItBookingEvent> getBookingeventlist() {
		return bookingeventlist;
	}
	public void setBookingeventlist(List<ItBookingEvent> bookingeventlist) {
		this.bookingeventlist = bookingeventlist;
	}
	@PostConstruct
	public void getBookingevent1() {
		Bookingevent =resource.getBooking_Records(); 
		bookingstatus=new ArrayList<IcBookingStatus>(0);
		bookingstatus=Bstatusresource.findAllOrderedByCode();
		equipment=new ArrayList<IcEquipment>(0);
		equipment=equipmentresource.getEquipment_Records();
		routedeatil=new ArrayList<IcRouteDetail>(0);
		routedeatil=routeresource.findAllRouteDeatils();
		Booking=new ArrayList<ItBooking>(0);
		Booking=bookingresource.getBooking_Records();
		}
	}

