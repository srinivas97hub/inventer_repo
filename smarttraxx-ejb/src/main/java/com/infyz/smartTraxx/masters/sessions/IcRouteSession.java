package com.infyz.smartTraxx.masters.sessions;


import java.io.Serializable; import java.util.ArrayList; import
java.util.Date; import java.util.List;

import javax.enterprise.context.SessionScoped; import
javax.enterprise.inject.Produces; import
javax.faces.application.FacesMessage; import
javax.faces.context.FacesContext; import javax.inject.Inject; import
javax.inject.Named; import javax.persistence.EntityManager; import
javax.persistence.Query; import javax.transaction.Transactional;

import org.primefaces.event.*;

import com.infyz.smartTraxx.master.Resouces.IcStatusResource; import
com.infyz.smartTraxx.master.Resouces.RouteResorce; import
com.infyz.smartTraxx.master.entities.IcRoute; import
com.infyz.smartTraxx.master.entities.IcStatus;

@Named()

@SessionScoped public class IcRouteSession implements Serializable { private
	static final long serialVersionUID = 1L;

@Inject 
EntityManager em;

@Named

@Produces 
private IcRoute selectedrouteActivity;

@Inject 
RouteResorce resource;

private List<IcRoute> route; 
private List<IcRoute> selectroute; 
private boolean managed; 
private String code; 
private boolean disabled; 
private
Integer id;

public void getIcRouteList() 
{
	route = resource.getroute_Records();
}

public void add()
{ System.out.println("add method called");
selectedrouteActivity = new IcRoute(); 
}

public void Edit(int id) 
{ 
	System.out.println("hi edit" + id);
	selectedrouteActivity = resource.getrouteId(id); 
}

public void Select() {
	System.out.println("hi select");
	selectedrouteActivity=new IcRoute();
}

@Transactional public void persist() 
{
	System.out.println("persist method executed");
	selectedrouteActivity.setCreatedBy(01);
	selectedrouteActivity.setModifiedBy(01);
	selectedrouteActivity.setCreatedDate(new Date());
	selectedrouteActivity.setModifiedDate(new Date());
	em.persist(selectedrouteActivity); 
	route.add(0, selectedrouteActivity);

}

@Transactional public String update() 
{ System.out.println("hi update");
selectedrouteActivity.setCreatedBy(01);
selectedrouteActivity.setModifiedBy(01);
selectedrouteActivity.setCreatedDate(new Date());
selectedrouteActivity.setModifiedDate(new Date());
em.merge(selectedrouteActivity); getRoute(); return
		"/Test/status_view.xhtml?faces-redirect=true"; 
}

@Transactional public void delete(int id) 
{ System.out.println("hi delete");
selectedrouteActivity = resource.getrouteId(id);
em.remove(selectedrouteActivity); 
}

public String cancel() 
{ 
	return "index.xhtml?faces-redirect=true"; 
}

public String close() 
{ 
	System.out.println("-----------we are in close");
	return "/Test/status_view.xhtml?faces-redirect=true"; 
}

public String navigate() 
{ 
	return "/Test/Route.xhtml?faces-redirect=true"; 
}

public boolean isManaged() 
{ 
	return managed; 
}

public void setManaged(boolean managed)
{ 
	this.managed = managed;
}

public String getCode() 
{ return code;
}

public void setCode(String code)
{ 
	this.code = code; 
}

public boolean isDisabled() 
{ 
	return disabled; 
}

public void setDisabled(boolean disabled)
{
	this.disabled = disabled;
}

public Integer getId() {
	return id; 
}

public void setId(Integer id)
{ 
	this.id = id; 
} 
private int statusId;

public int getStatusId()
{ return statusId;
}

public void setStatusId(int statusId) 
{ 
	this.statusId = statusId;
}


@Transactional 
public void BulkDelete() 
{ 
	for (IcRoute s : selectroute) {
		em.remove(em.contains(s) ? s : em.merge(s));
	}
	getRoute(); 
}

@Transactional 
public void Search1(int id) 
{ 
	route=new ArrayList<IcRoute>();
	System.out.println("hi search"); 
	System.out.println(id);
	route= resource.getsearchById(getId()); 
	System.out.println(route.size());
	for(IcRoute s:route) 
	{ System.out.println(s.getRouteId()+" "+s.getDescription()); 
	}
	getRoute(); 
}

@Transactional public void Bupdate() { System.out.println("hi Bupdate");
for(IcRoute s1:selectroute) { s1.setCode(selectedrouteActivity.getCode());
s1.setColourCode(selectedrouteActivity.getColourCode());
s1.setFActive(selectedrouteActivity.getFActive());
s1.setVersion(selectedrouteActivity.getVersion());
s1.setDescription(selectedrouteActivity.getDescription());
 em.merge(s1); ; 
 } 
}
private String statusCode;
private String name;

public String getStatusCode() 
{
	return statusCode; 
}
public void setStatusCode(String statusCode) { this.statusCode = statusCode;
}

public String getName() { return name; }

public void setName(String name) { this.name = name; }


/*
 * public void search() { StringBuilder queryStr = new StringBuilder();
 * 
 * queryStr.append("From IcStatus where ");
 * 
 * boolean enter = false; if (statusCode != null) { if (enter) { enter = true;
 * queryStr.append("and statusCode='" + statusCode + "'"); } else { enter =
 * true; queryStr.append("statusCode='" + statusCode + "'"); }
 * 
 * } if (name != null) { if (enter) { enter = true; queryStr.append("and name='"
 * + name + "'"); } else { enter = true; queryStr.append("name='" + name + "'");
 * }
 * 
 * } System.out.println(queryStr.toString()); try { Query query =
 * em.createQuery(queryStr.toString()); icstatus = query.getResultList(); }
 * catch (Exception e) { e.printStackTrace(); } }
 */

public List<IcRoute> getRoute() {
	return route;
}

public void setRoute(List<IcRoute> route) {
	this.route = route;
}

public List<IcRoute> getSelectroute() {
	return selectroute;
}

public void setSelectroute(List<IcRoute> selectroute) {
	this.selectroute = selectroute;
}

public void onRowSelect(SelectEvent<IcStatus> event) {
	System.out.println(event); FacesMessage msg = new
			FacesMessage("status Selected", event.getObject().getStatusCode());
	FacesContext.getCurrentInstance().addMessage(null, msg); } }

