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

import cat.prv.om.entity.TransDtl;

@Repository
public class TransDtlDao {

	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public TransDtl getTransDtl(String transDtlId){
		return em.find(TransDtl.class, transDtlId);
	}
	
	@Transactional
	public List<TransDtl> getTransDtlByTransId(String transId){
		CriteriaBuilder cb = em.getCriteriaBuilder();

		// Query for a List of objects.
		CriteriaQuery cq = cb.createQuery();
		Root e = cq.from(TransDtl.class);
		cq.select(e).where(cb.equal(e.get("transId"), cb.parameter(String.class, "transId")));
		
		Query query = em.createQuery(cq);
		query.setParameter("transId", transId);
		List<TransDtl> result = query.getResultList();
		
		return result;
	}
	
	
}