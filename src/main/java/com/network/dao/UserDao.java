package com.network.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.network.model.User;

@Repository
@Transactional
public class UserDao {

	private SessionFactory sessfact;

	public UserDao() {
		super();
	}

	@Autowired
	public UserDao(SessionFactory sessfact) {
		this.sessfact = sessfact;
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try (Session sess = sessfact.openSession()) {
			users = sess.createQuery("FROM User", User.class).list();
		}
		return users;
	}

	public User findById(Integer i) {
		User user = new User();
		try (Session sess = sessfact.openSession()) {
			user = sess.get(User.class, i);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public User findByUsername(String username) {
		User user = new User();
		try(Session sess = sessfact.openSession()){
			user = sess.createQuery("from User where username ='"+username+"'",User.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return user;
	}
	
	public User findByEmail(String email, String password) {
		User user = new User();
		try (Session sess = sessfact.openSession()) {
			user = (User) sess.createCriteria(User.class)
					.add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password))
					.uniqueResult();
						
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User update(User t) {
		try (Session sess = sessfact.openSession()) {			
			String sql = "UPDATE \"user\" SET first_name = ?, last_name = ?,"
						+ "password = ?, username = ? WHERE email = ?";
			
			Transaction tx = sess.beginTransaction();
			sess.createNativeQuery(sql)
				.setParameter(1, t.getFirstName())
				.setParameter(2, t.getLastName())
				.setParameter(3, t.getPassword())
				.setParameter(4, t.getUsername())
				.setParameter(5, t.getEmail())
				.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			return null;
		}

		return t;
	}

	public User save(User t) {
		try (Session sess = sessfact.openSession()) {
			sess.save(t);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return t;
	}
	
	public User upload(User t) {
		try (Session sess = sessfact.openSession()) {
			String sql = "UPDATE \"user\" SET user_pic = ? WHERE username = ?";
			Transaction tx = sess.beginTransaction();
			sess.createNativeQuery(sql)
				.setParameter(1, t.getPicture())
				.setParameter(2, t.getUsername())
				.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public void resetPassword(String email, String password) {
		try (Session sess = sessfact.openSession()) {
			String sql = "UPDATE \"user\" SET password = ? WHERE email = ?";
			Transaction tx = sess.beginTransaction();
			sess.createNativeQuery(sql)
				.setParameter(1, password)
				.setParameter(2, email)
				.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public User delete(Integer i) {
		User toBeDeleted = new User();
		try (Session sess = sessfact.openSession()) {
			toBeDeleted = findById(i);
			sess.delete(toBeDeleted);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return toBeDeleted;
	}

}
