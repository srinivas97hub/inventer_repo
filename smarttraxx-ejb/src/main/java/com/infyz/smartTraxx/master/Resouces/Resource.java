package com.infyz.smartTraxx.master.Resouces;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
@SuppressWarnings("serial")
@ApplicationScoped
public class Resource  implements java.io.Serializable {

        @PersistenceUnit
        private EntityManagerFactory emf;

        private Session session;

        public Session getSession() {
                return session;
        }

        public void setSession(Session session) {
                this.session = session;
        }

        @Produces
        @Default
        @RequestScoped
        public EntityManager create() {
			/*
			 * SessionFactory sessionFactory = emf.unwrap(SessionFactory.class); session =
			 * sessionFactory.openSession();
			 */
                return this.emf.createEntityManager();
        }

        public void dispose(@Disposes @Default EntityManager em) {
                if (em.isOpen()) {
                        em.close();
                }
        }

        @Produces
        @RequestScoped
        public FacesContext produceFacesContext() {
                return FacesContext.getCurrentInstance();
        }

        @Produces
        public Logger produceLog(InjectionPoint injectionPoint) {
                return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        }
}