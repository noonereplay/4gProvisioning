package cat.prv.om.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cat.prv.om.entity.TblRtOffersDel;

@Repository
public class TblRtOffersDelDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public TblRtOffersDel getTblRtOffer(Integer rtOfferId){
		return em.find(TblRtOffersDel.class, rtOfferId);
	}
}
