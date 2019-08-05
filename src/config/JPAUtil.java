package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private final static String UNIT_NAME = "ULS_lps";
	private static EntityManagerFactory factory;
	
	public static EntityManager newEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(UNIT_NAME);
		}
		return factory.createEntityManager();
	}

	public static void closeEntityManagerFactory() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
	}
}
