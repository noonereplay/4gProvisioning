package cat.prv.om.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cat.prv.om.entity.TblServices;

@Repository
public class TblServicesDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public TblServices getTblServices(Integer serviceId){
		return em.find(TblServices.class, serviceId);
	}
	
	@Transactional
	public TblServices getTblServiceByMsisdn(String msisdn){
		CriteriaBuilder cb = em.getCriteriaBuilder();

		// Query for a List of objects.
		CriteriaQuery cq = cb.createQuery();
		Root e = cq.from(TblServices.class);
		cq.select(e).where(cb.equal(e.get("msisdn"), cb.parameter(String.class, "msisdn")));
		
		
		Query query = em.createQuery(cq);
		query.setParameter("msisdn", msisdn);
		TblServices result = (TblServices) query.getSingleResult();
		
		return result;
		
	}
}
