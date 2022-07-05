package com.simplilearn.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.simplilearn.entity.EProduct;

public class HibernateUtil {

	static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(EProduct.class)
				.buildSessionFactory();
		return sessionFactory;
	}
}