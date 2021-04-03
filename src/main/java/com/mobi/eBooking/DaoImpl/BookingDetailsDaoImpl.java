package com.mobi.eBooking.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mobi.eBooking.Config.HibernateConfig;
import com.mobi.eBooking.Dao.BookingDetailsDAO;
import com.mobi.eBooking.Model.BookingDetailsModel;
import com.mobi.eBooking.RequestClass.BookingDetailsRequest;

@Repository
public class BookingDetailsDaoImpl implements BookingDetailsDAO {
	
	@Autowired
	HibernateConfig conf;
	
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public BookingDetailsModel saveBooking(BookingDetailsModel book) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(book);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	@Override
	public List<Map<String, Object>>  getCountForTheDay(String dt) {
		String query = null;
		List<Map<String, Object>> resultList = new ArrayList<>();
		try {
			query = "SELECT * from booking_details WHERE fromDate = '" +dt+ "' AND status = 0 or status = 1";
			resultList = jdbc.queryForList(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public void checkIn(BookingDetailsRequest request) {
		String query = null;
		try {
			query = "UPDATE booking_details SET status = '" + 1 + "' WHERE bookingId ='" + request.getBookingId() + "'";
			jdbc.execute(query);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkOut(BookingDetailsRequest request) {
		String query = null;
		try {
			query = "UPDATE booking_details SET status = '" + 2 + "' WHERE bookingId ='" + request.getBookingId() + "'";
			jdbc.execute(query);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public BookingDetailsModel getRoomDtails(BookingDetailsRequest request) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		BookingDetailsModel bookingDetails = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BookingDetailsModel> criteria = builder.createQuery(BookingDetailsModel.class);
			Root<BookingDetailsModel> root = criteria.from(BookingDetailsModel.class);
			bookingDetails = session.createQuery(criteria.select(root).where(builder.equal(root.get("bookingId"),request.getBookingId())))
					.getSingleResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return bookingDetails;
	}

	@Override
	public List<Map<String, Object>> getCountForThePreviousDay(String dt) {
		String query = null;
		List<Map<String, Object>> resultList = new ArrayList<>();
		try {
			query = "SELECT * from booking_details WHERE fromDate = '" +dt+ "'";
			resultList = jdbc.queryForList(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

}
