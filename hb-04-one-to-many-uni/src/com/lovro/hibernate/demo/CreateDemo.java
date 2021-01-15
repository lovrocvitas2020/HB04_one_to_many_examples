package com.lovro.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lovro.hibernate.demo.entity.Instructor;
import com.lovro.hibernate.demo.entity.InstructorDetail;

/**
 * Added javadoc
 * 
 * @author lovro.cvitas
 *
 */
public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();				
				
		// create session
		Session session = factory.getCurrentSession();	
		
		try {
			
			// create the objects
			Instructor tempInstructor = new Instructor("Josip","Cvitas","josip@mail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"http://www.bug.hr","news");
			
			// associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			// start a transaction
		  session.beginTransaction();
		  
		  // save the instructor
		  // this will ALSO save the details object
		  
		  System.out.println("Saving instructor: "+tempInstructor);
		  session.save(tempInstructor);
			
		
			
			// commit transaction
			session.getTransaction().commit();
			
			 System.out.println("Done !");
			
		}
		finally {
			factory.close();
		}
		
		
	}

}
