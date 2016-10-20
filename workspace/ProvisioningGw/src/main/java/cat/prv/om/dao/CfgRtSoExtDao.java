package cat.prv.om.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cat.prv.om.entity.CfgRtSoExt;


@Repository
public class CfgRtSoExtDao {
	
	private static Logger logger = LoggerFactory.getLogger(CfgRtSoExtDao.class);
	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public CfgRtSoExt getCfgRtSoExt(String offerId){
		CfgRtSoExt result = null;
		try {
			result = em.find(CfgRtSoExt.class, offerId);
		} catch (Exception e) {
			logger.error("Error Get Offer {} ",offerId,e);
		}
		return result;
	}
	
}
