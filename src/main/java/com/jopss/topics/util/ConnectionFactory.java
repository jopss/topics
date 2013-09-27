package com.jopss.topics.util;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.googlecode.flyway.core.Flyway;
import com.jopss.topics.exception.TopicsException;

/**
 * Fabrica de conexoes JPA com o banco de dados. Cria a 'EntityManagerFactory' como Singleton.
 * Ainda, inicializa as Migrations.
 */
public class ConnectionFactory {

	private static EntityManagerFactory factory;

	private static EntityManagerFactory getFactory() throws TopicsException {
		if (factory == null) {
			Properties propsBase = new Properties();

			try {
				propsBase.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("topics_db.properties"));
			} catch (IOException e) {
				throw new TopicsException(e);
			}

			factory = Persistence.createEntityManagerFactory("topics_pu", propsBase);
			
                        //Migrations
			Properties properties = new Properties();
			properties.setProperty("flyway.user", propsBase.getProperty("hibernate.connection.username"));
			properties.setProperty("flyway.password", propsBase.getProperty("hibernate.connection.password"));
			properties.setProperty("flyway.url", propsBase.getProperty("hibernate.connection.url"));
			properties.setProperty("flyway.driver", propsBase.getProperty("hibernate.connection.driver_class"));

			Flyway flyway = new Flyway();
			flyway.configure(properties);
                        flyway.setDisableInitCheck(true);
			flyway.setLocations("db/migrations");
			flyway.migrate();
		}
		return factory;
	}

	public static EntityManager create() throws TopicsException {

		if (factory == null) {
			factory = getFactory();
		}

		return factory.createEntityManager();
	}
}