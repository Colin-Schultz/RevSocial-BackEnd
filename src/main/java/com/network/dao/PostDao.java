package com.network.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.network.model.Post;


@Repository
@Transactional
public class PostDao {
	
private SessionFactory sessfact;

	@Autowired
	public PostDao(SessionFactory sessfact) {
		this.sessfact = sessfact;
	}

	public List<Post> findAll() {
		List<Post> posts = new ArrayList<>();
		try (Session sess = sessfact.openSession()) {
			posts = sess.createQuery("FROM Post", Post.class).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return posts;
	}
	public List<Post> findAllByUsername(Integer user_id){
		List<Post> posts = new ArrayList<>();
		try(Session sess = sessfact.openSession()){
			posts = sess.createQuery("from Post where user_id = "+user_id,Post.class).list();
		}catch (NoResultException e) {
			return null;
		}
		return posts;
	}

	public Post findById(Integer i) {
		Post post = new Post();
		try (Session sess = sessfact.openSession()) {
			post = sess.get(Post.class, i);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return post;
	}

	public Post update(Post t) {
		try (Session sess = sessfact.openSession()) {
			Transaction tx = sess.beginTransaction();
			sess.update(t);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return t;
	}
	

	public Post save(Post t) {
		try (Session sess = sessfact.openSession()) {
			Transaction tx = sess.beginTransaction();
			sess.persist(t);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return t;
	}

	public Post delete(Integer i) {
		Post toBeDeleted = new Post();
		try (Session sess = sessfact.openSession()) {
			toBeDeleted = findById(i);
			Transaction tx = sess.beginTransaction();
			sess.delete(toBeDeleted);
			tx.commit();
		}

		return toBeDeleted;
	}

}
