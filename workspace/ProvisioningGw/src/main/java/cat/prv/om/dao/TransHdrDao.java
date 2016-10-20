package cat.prv.om.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cat.prv.om.entity.TransHdr;

@Repository
public class TransHdrDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public TransHdr getTransHdr(String transId){
		return em.find(TransHdr.class, transId);
	}
	
}
