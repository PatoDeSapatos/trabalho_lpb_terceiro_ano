package model.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import model.DAO;

public class UserDAO extends DAO {

    public UserDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public boolean register(UserVO user) {
    	EntityManager em = emf.createEntityManager();
    	
    	try {
    		em.getTransaction().begin();
    		em.persist(user);
    		em.getTransaction().commit();
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	} finally {
			em.close();
		}
    }

	public boolean delete(String id) {
		EntityManager em = emf.createEntityManager();
		
    	try {
    		UserVO user = em.find(UserVO.class, id);
    		
    		em.getTransaction().begin();
    		em.remove(user);
    		em.getTransaction().commit();
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	} finally {
			em.close();
		}
	}

    public UserVO getUserByLogin(String login) {
		EntityManager em = emf.createEntityManager();
		
    	try {
    		Query query = em.createQuery("from " + UserVO.class.getName() + " where login = :u");
    		query.setParameter("u", login);
    		UserVO user = (UserVO) query.getSingleResult();
    		
    		return user;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	} finally {
			em.close();
		}
    }

	public UserVO getUserById(String id) {
		EntityManager em = emf.createEntityManager();
		
    	try {
    		return em.find(UserVO.class, id);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	} finally {
			em.close();
		}
	}

}
