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

import org.primefaces.PrimeFaces;

import com.infyz.smartTraxx.master.Resouces.BookingStatusResource;
import com.infyz.smartTraxx.master.Resouces.IcEquipmentresource;
import com.infyz.smartTraxx.master.Resouces.IcRoutedeatisResource;
import com.infyz.smartTraxx.master.Resouces.IcStatusResource;
import com.infyz.smartTraxx.master.Resouces.ItBookikgRouteResource;
import com.infyz.smartTraxx.master.Resouces.ItBookingResource;
import com.infyz.smartTraxx.master.Resouces.ItbookingeventsResource;
import com.infyz.smartTraxx.master.Resouces.RouteResorce;
import com.infyz.smartTraxx.master.entities.IcBookingStatus;
import com.infyz.smartTraxx.master.entities.IcEquipment;
import com.infyz.smartTraxx.master.entities.IcRoute;
import com.infyz.smartTraxx.master.entities.IcRouteDetail;
import com.infyz.smartTraxx.master.entities.IcStatus;
import com.infyz.smartTraxx.master.entities.ItBookikgRouteDetail;
import com.infyz.smartTraxx.master.entities.ItBooking;
import com.infyz.smartTraxx.master.entities.ItBookingEvent;

@Named
@SessionScoped
public class BookingTransationSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	@Inject
	ItbookingeventsResource evetresorce;

	@Inject
	BookingStatusResource	bstatusResorce;

	@Inject
	IcEquipmentresource equpimentresorce;

	@Inject
	ItBookikgRouteResource brouteresource;

	@Inject
	RouteResorce routeresource;

	@Inject
	IcRoutedeatisResource routedeatilresorce;

	@Inject
	ItBookingResource booingresorce;

	@Inject
	IcStatusResource statusresource;
	@Named
	@Produces
	private ItBooking selectbook;

	@Named
	@Produces
	private ItBookingEvent SelectedEvet;

	@Inject
	ItbookingeventsSession bsession;
	@Inject
	ItBookingRouteDetailSession brsession;

	@Named
	@Produces
	private ItBookikgRouteDetail selectedroutre;

	private List<ItBookingEvent> evetList;
	private List<ItBooking> bookList;
	private List<ItBookikgRouteDetail> routeList;
	private List<IcEquipment> elist;
	private List<IcStatus> Slist;
	private List<IcRoute> rList;
	private List<IcRouteDetail> rdList;
	private List<IcBookingStatus> BsList;
	private List<ItBooking> booking;

	private List<ItBookikgRouteDetail> getrouteList=new ArrayList<ItBookikgRouteDetail>();
	private List<ItBookingEvent> getevetList=new ArrayList<ItBookingEvent>();


	private boolean managed;
	private String code;
	private boolean disabled;
	private Integer id;

	public void getBookingList() { 
		booking =booingresorce.getBooking_Records();
	}

	public List<ItBooking> getBooking() {
		return booking;
	}

	public void setBooking(List<ItBooking> booking) {
		this.booking = booking;
	}
	public void Edit(int id) {
		System.out.println("hi edit"+id);
		selectbook=booingresorce.getbookingId(id);
	}
	public void Select(int id)
	{
		System.out.println("hi edit"+id);
		selectbook=booingresorce.getbookingId(id);
	}
	public String add() {
		System.out.println("hi add");

		selectbook=new ItBooking();
		return "/Test/ItBookingTransation.xhtml?faces-redirect=true";
	}
	public void addroutes() {
		System.out.println("addevent");
		selectedroutre =new ItBookikgRouteDetail();
		routeList.add(selectedroutre);
		getrouteList.add(selectedroutre);
	}
	public void addeventrow()
	{
		System.out.println("addevent");
		SelectedEvet =new ItBookingEvent();
		evetList.add(SelectedEvet);
		getevetList.add(SelectedEvet);
	}
	public void addroutes1() {
		System.out.println("addevent");
		selectedroutre =new ItBookikgRouteDetail();
		routeList.add(selectedroutre);
		getrouteList.add(selectedroutre);
	}
	public void addeventrow1()
	{
		System.out.println("addevent");
		SelectedEvet =new ItBookingEvent();
		evetList.add(SelectedEvet);
	}
	@Transactional
	public String persist()
	{
		System.out.println("persist method executed");
		selectbook. setCreatedBy(143);
		selectbook. setModifiedBy(143);
		selectbook. setCreatedDate(new Date());
		selectbook. setModifiedDate(new Date());
		em.persist(selectbook);
		SelectedEvet.setCreatedBy(143); SelectedEvet.setCreatedDate(new Date());
		SelectedEvet.setModifiedBy(143); SelectedEvet.setModifiedDate(new Date());
		em.persist(SelectedEvet); selectedroutre.setCreatedBy(143);
		selectedroutre.setModifiedBy(143); selectedroutre.setCreatedDate(new Date());
		selectedroutre.setActualCompleteTime(new Date());
		selectedroutre.setActualStartTime(new Date());
		selectedroutre.setModifiedDate(new Date()); em.persist(selectedroutre);

		/*
		 * for (ItBookingEvent b : evetList) {
		 * b.setIcBookingStatus(b.getIcBookingStatus());
		 * b.setIcEquipment(b.getIcEquipment()); b.setItBooking(b.getItBooking());
		 * b.setIcRouteDetail(b.getIcRouteDetail()); b.setCreatedBy(143);
		 * b.setCreatedDate(new Date()); b.setModifiedBy(143); b.setModifiedDate(new
		 * Date());
		 * 
		 * } for (ItBookikgRouteDetail b1 : routeList) {
		 * b1.setIcRouteDetail(b1.getIcRouteDetail());
		 * b1.setIcStatus(b1.getIcStatus());; b1.setItBooking(b1.getItBooking());
		 * b1.setCreatedBy(143); b1.setModifiedBy(143); b1.setActualCompleteTime(new
		 * Date()); b1.setActualStartTime(new Date()); b1.setModifiedDate(new Date());
		 * em.persist(b1); }
		 */
		System.out.println("end");
		getEvetList();
		getRouteList();
		return "/Test/ItBookingTransation.xhtml?faces-redirect=true";
	}
	public void removerouterow()
	{
		routeList.remove(selectedroutre);
	}
	public void removeeventrow()
	{
		evetList.remove(SelectedEvet);
	}
	@Transactional
	public String update() {
		System.out.println("hi update");
		selectbook. setCreatedBy(01);
		selectbook. setModifiedBy(01);
		selectbook. setCreatedDate(new Date());
		selectbook. setModifiedDate(new Date());
		em.merge(selectbook);
		getBooking();
		return "/Test/ItBooking.xhtml?faces-redirect=true";
	}
	@Transactional
	public String delete()
	{
		System.out.println("hi delete");
		selectbook= em.find(ItBooking.class,selectbook.getBookingId());
		em.remove(selectbook);
		getBooking();
		return  "/Test/ItBooking.xhtml?faces-redirect=true";
	}
	public String cancel() {
		return"/index.xhtml?faces-redirect=true";
	}
	public String close() {
		System.out.println("-----------we are in close");
		return  "/Test/ItBooking.xhtml?faces-redirect=true";
	}
	public String navigate(){
		return "/Test/ItBooking.xhtml?faces-redirect=true";
	}

	public void reset() {
		PrimeFaces.current().resetInputs("form:panel");
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


	public List<ItBookingEvent> getEvetList() {
		return evetList;
	}
	public void setEvetList(List<ItBookingEvent> evetList) {
		this.evetList = evetList;
	}
	public List<ItBooking> getBookList() {
		return bookList;
	}
	public void setBookList(List<ItBooking> bookList) {
		this.bookList = bookList;
	}
	public List<ItBookikgRouteDetail> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<ItBookikgRouteDetail> routeList) {
		this.routeList = routeList;
	}
	public List<IcEquipment> getElist() {
		return elist;
	}
	public void setElist(List<IcEquipment> elist) {
		this.elist = elist;
	}
	public List<IcRoute> getrList() {
		return rList;
	}
	public void setrList(List<IcRoute> rList) {
		this.rList = rList;
	}
	public List<IcRouteDetail> getRdList() {
		return rdList;
	}
	public void setRdList(List<IcRouteDetail> rdList) {
		this.rdList = rdList;
	}
	public List<IcBookingStatus> getBsList() {
		return BsList;
	}
	public void setBsList(List<IcBookingStatus> bsList) {
		BsList = bsList;
	}
	public List<IcStatus> getSlist() {
		return Slist;
	}
	public void setSlist(List<IcStatus> slist) {
		Slist = slist;
	}

	public List<ItBookikgRouteDetail> getGetrouteList() {
		return getrouteList;
	}
	public void setGetrouteList(List<ItBookikgRouteDetail> getrouteList) {
		this.getrouteList = getrouteList;
	}
	public List<ItBookingEvent> getGetevetList() {
		return getevetList;
	}
	public void setGetevetList(List<ItBookingEvent> getevetList) {
		this.getevetList = getevetList;
	}

	@PostConstruct 
	public void getTransation() {
		bookList=booingresorce.getBooking_Records(); 
		elist=new ArrayList<IcEquipment>();
		elist=equpimentresorce.getEquipment_Records();
		rList=new ArrayList<IcRoute>();
		rList=routeresource.getroute_Records();

		routeList=brouteresource.getRecords();
		rdList=new ArrayList<IcRouteDetail>();
		rdList=routedeatilresorce.findAllRouteDeatils(); 
		Slist=new ArrayList<IcStatus>();
		Slist=statusresource.getIcStatus_Records(); 
		bookList=new ArrayList<ItBooking>(); 
		bookList=booingresorce.getBooking_Records();

		evetList =evetresorce.getBooking_Records();
		BsList=new ArrayList<IcBookingStatus>();
		BsList=bstatusResorce.findAllOrderedByCode();
		elist=new ArrayList<IcEquipment>();
		elist=equpimentresorce.getEquipment_Records(); 
		rdList=new ArrayList<IcRouteDetail>();
		rdList=routedeatilresorce.findAllRouteDeatils(); 
		bookList=new ArrayList<ItBooking>(); 
		bookList=booingresorce.getBooking_Records();
	}
}
