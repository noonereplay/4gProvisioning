package cat.prv.om.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cat.prv.om.entity.TblRtOffers;

@Repository
public class TblRtOffersDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public TblRtOffers getTblRtOffer(Integer rtOfferId){
		return em.find(TblRtOffers.class, rtOfferId);
	}
}