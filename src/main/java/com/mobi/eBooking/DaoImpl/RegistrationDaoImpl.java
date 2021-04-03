package com.mobi.eBooking.DaoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobi.eBooking.Config.HibernateConfig;
import com.mobi.eBooking.Dao.RegistrationDAO;
import com.mobi.eBooking.Model.BookingModel;
import com.mobi.eBooking.RequestClass.RegistrationRequest;
import com.mobi.eBooking.Utill.EncryptUtil;

@Repository
public class RegistrationDaoImpl implements RegistrationDAO {
	
	@Autowired
	HibernateConfig conf;

	@Override
	public BookingModel register(BookingModel bookingModel) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(bookingModel);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookingModel;
		
	}

	@Override
	public BookingModel isUserExist(RegistrationRequest request) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		List<BookingModel> users = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BookingModel> criteria = builder.createQuery(BookingModel.class);
			Root<BookingModel> root = criteria.from(BookingModel.class);
			users = session.createQuery(criteria.select(root).where(builder.equal(root.get("emailId"), request.getEmailId())))
					.getResultList();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public BookingModel login(String emailId, String password) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		String pwd = EncryptUtil.md5(password);
		BookingModel bookingModel = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BookingModel> criteria = builder.createQuery(BookingModel.class);
			Root<BookingModel> root = criteria.from(BookingModel.class);
			bookingModel = session.createQuery(criteria.select(root).where(builder.equal(root.get("emailId"), emailId),
					builder.equal(root.get("password"), pwd))).uniqueResult();
		}catch (Exception e) {
			 e.printStackTrace();
		}finally {
			session.close();
		}
		return bookingModel;
	}

}
