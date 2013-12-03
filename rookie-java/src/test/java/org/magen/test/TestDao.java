package org.magen.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.magen.test.domain.Book;

import junit.framework.TestCase;

public class TestDao extends TestCase{
	
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application
//        sessionFactory = new Configuration()
//                .configure() // configures settings from hibernate.cfg.xml
//                .buildSessionFactory();
		 Configuration cfg = new Configuration();
		 cfg.configure();
		 ServiceRegistry  sr = new ServiceRegistryBuilder()
		 	.applySettings(cfg.getProperties()).buildServiceRegistry();  
		 sessionFactory = cfg.buildSessionFactory(sr);
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@SuppressWarnings({ "unchecked" })
	public void testBasicUsage() {
		// create a couple of events...
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(new Book("test3","shenzl"));
		session.getTransaction().commit();
		//session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery( "from Book" ).list();
		for ( Book book : (List<Book>) result ) {
			System.out.println( "Book (" +book.getName() + ") : " + book.getAuthor() );
		}
        session.getTransaction().commit();
       // session.close();
	}

}
