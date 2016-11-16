package cat.prv.om.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.prv.om.entity.TransHdr;

@Repository
public class TransHdrDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*
	@Transactional
	public void save(TransHdr transHdr){
		Session session = sessionFactory.getCurrentSession();
		session.save(transHdr);
	}
	
	@Transactional
	public void update(TransHdr transHdr){
		Session session = sessionFactory.getCurrentSession();
		session.update(transHdr);
	}
	*/
	
	@Transactional
	public TransHdr getTransHdr(String transId){
		Session session = sessionFactory.getCurrentSession();
		TransHdr transHdr = (TransHdr)session.get(TransHdr.class, transId);
		return transHdr;
	}
}
