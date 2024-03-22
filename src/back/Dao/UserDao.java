package back.Dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import back.Models.User;

import org.hibernate.Criteria;
//import org.hibernate.CriteriaBuilder;
import org.hibernate.criterion.Restrictions;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDao {
	
public void saveUser(User employee) {
		
		Transaction transaction = null;
		
		try {
			Session session = 
					HibernateUtil.
					getSessionFactory().
					openSession();
			
			transaction = session.beginTransaction();
			
			session.save(employee);
			
			transaction.commit();
			
			session.close();
			
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	

	public void updateUser(User employee) {
		
		Transaction transaction = null;
		
		try {
			Session session = 
					HibernateUtil.
					getSessionFactory().
					openSession();
			
			transaction = session.beginTransaction();
			
			session.update(employee);
			
			transaction.commit();
			
			session.close();
			
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}	

	
	public void deleteUser(User employee) {
	    try {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
	        session.delete(employee);
	        session.getTransaction().commit();
	        session.close();
	    } catch (Exception ex) {
	        ex.printStackTrace(); 
	    }
	}
	
    public User searchUser(int id){
   try{
  		Session ss = 
				HibernateUtil.
				getSessionFactory().
				openSession();
  		User xbook = (User) ss.get(User.class, id);
       ss.close();
       return xbook;
   }catch(Exception ex){
       ex.printStackTrace();
   }
   return null;
}
    
    public User getUserByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root).where(builder.equal(root.get("email"), username));
            Query<User> query = session.createQuery(criteria);
            user = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    
    
    

    public User searchUser(String email) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root).where(builder.equal(root.get("email"), email)); 
            Query<User> query = session.createQuery(criteria);
            User user = query.uniqueResult();
            session.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }   
 
    public List<User> selectAllUser() {
        Transaction transaction = null;
        List<User> student = null;

        try (Session session = HibernateUtil.getSessionFactory()) {
            transaction = session.beginTransaction();

            // Perform data retrieval logic here
            student = session.createQuery("FROM User", User.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }  
}
