package ca.on.conestogac;

import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestJobSearch {
	@Test
	public void testJobSeeker()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{ 
			session.beginTransaction();
			JobSeeker user = new JobSeeker();
			 
			user.setsEmailAddress("rhildred@conestogac.on.ca");
			user.setsFirstName("Rich");
			user.setsLastName("Hildred");
			 
			session.save(user);
			session.getTransaction().commit();
			assertTrue(true);
		}catch(Exception e){
			System.out.println(e.toString());
			System.out.println(e.getStackTrace());
		}
	}
}
