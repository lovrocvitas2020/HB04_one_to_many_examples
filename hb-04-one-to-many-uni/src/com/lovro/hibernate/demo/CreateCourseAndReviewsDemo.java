package com.lovro.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lovro.hibernate.demo.entity.Course;
import com.lovro.hibernate.demo.entity.Instructor;
import com.lovro.hibernate.demo.entity.InstructorDetail;
import com.lovro.hibernate.demo.entity.Review;


public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();				
				
		// create session
		Session session = factory.getCurrentSession();	
		
		try {
		
			// start a transaction
		   session.beginTransaction();
		   
		   // create  course
		   Course tempCourse = new Course("Pacman - How to score one illion points");
		   
		   // add some reviews
		   tempCourse.addReview(new Review("Great course .. love it!"));
		   tempCourse.addReview(new Review("Great course .. great!"));
		   tempCourse.addReview(new Review("Cool course!"));
		   
		   // save the course and leverage the cascade all
		   System.out.println("Saving the course");
		   System.out.println(tempCourse.getReviews());
		   session.save(tempCourse);
		   
		 
			// commit transaction
			session.getTransaction().commit();
			
			 System.out.println("Done !");
			
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
		
		
	}

}
