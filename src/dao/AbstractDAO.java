package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import config.JPAUtil;

public abstract class AbstractDAO {

    protected Object findById(Class<?> clazz, Integer id) {
    	EntityManager em = JPAUtil.newEntityManager();
        Object obj = em.find(clazz, id);
        em.close();
        return obj;
    }
    
    protected List findAll(Class<?> clazz) {
    	EntityManager em = JPAUtil.newEntityManager();
    	Query query = em.createQuery("from " + clazz.getName());
    	List objects = query.getResultList();
    	em.close();
    	return objects;
    }
	
    protected void create(Object object) {
    	EntityManager em = JPAUtil.newEntityManager();
    	em.getTransaction().begin();
    	em.persist(object);
    	em.flush();
    	em.getTransaction().commit();
    	em.close();
    }
    
    protected boolean delete(int id, Class clazz) {
    	EntityManager em = JPAUtil.newEntityManager();
    	em.getTransaction().begin();
    	int isSuccessful = em.createQuery("delete from " + clazz.getSimpleName() + " t where t.id=:id").setParameter("id", id).executeUpdate();
    	em.getTransaction().commit();
    	em.close();
    	if (isSuccessful == 0) {
    		System.err.println(">>> Error while Deleting, does the user exist?");
    		return false;
    	} else {
    		return true;
    	}
    }
}
