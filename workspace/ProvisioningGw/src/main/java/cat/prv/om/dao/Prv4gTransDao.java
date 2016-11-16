package cat.prv.om.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cat.prv.om.entity.Prv4gTrans;

@Repository
public class Prv4gTransDao {

	private static Logger logger = LoggerFactory.getLogger(Prv4gTransDao.class);
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Prv4gTrans getPrv4gTrans(Integer transId){
		Prv4gTrans result = null;
		try {
			result = em.find(Prv4gTrans.class, transId);
		} catch (Exception e) {
			logger.error("Error Get transId {} ",transId,e);
		}
		return result;
	}
	
	@Transactional(dontRollbackOn=Exception.class)
	public void save(Prv4gTrans trans){
		try {
			em.persist(trans);
		} catch (Exception e) {
			logger.error("Error Save {} ",trans,e);
		}
	}
	
}
