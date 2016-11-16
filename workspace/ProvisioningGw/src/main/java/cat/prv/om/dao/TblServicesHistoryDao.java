package cat.prv.om.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cat.prv.om.entity.TblServicesHistory;

@Repository
public class TblServicesHistoryDao {
	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public TblServicesHistory getTblServices(Integer historyId){
		return em.find(TblServicesHistory.class, historyId);
	}
	
	@Transactional
	public List<TblServicesHistory> getTblServicesHistoryByMsisdn(String msisdn){
		CriteriaBuilder cb = em.getCriteriaBuilder();

		// Query for a List of objects.
		CriteriaQuery cq = cb.createQuery();
		Root e = cq.from(TblServicesHistory.class);
		cq.select(e).where(cb.equal(e.get("msisdn"), cb.parameter(String.class, "msisdn")));
		
		
		Query query = em.createQuery(cq);
		query.setParameter("msisdn", msisdn);
		List<TblServicesHistory> result = query.getResultList();
		
		return result;
		
	}
}