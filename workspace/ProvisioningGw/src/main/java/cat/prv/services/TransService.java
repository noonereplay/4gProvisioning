package cat.prv.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.prv.hlr.dto.HlrRequest;
import cat.prv.hlr.dto.HlrResponse;
import cat.prv.om.dao.Prv4gTransDao;
import cat.prv.om.dao.TransHdrDao;
import cat.prv.om.entity.Prv4gTrans;
import cat.prv.om.entity.TransHdr;

@Service
public class TransService {
	
	private static Logger logger = LoggerFactory.getLogger(TransService.class);

	@Autowired
	private TransHdrDao transHdrDao;
	
	@Autowired 
	private Prv4gTransDao prv4gTransDao;
	


	public void setTransHdrDao(TransHdrDao transHdrDao) {
		this.transHdrDao = transHdrDao;
	}
	
	public Prv4gTransDao getPrv4gTransDao() {
		return prv4gTransDao;
	}

	public void setPrv4gTransDao(Prv4gTransDao prv4gTransDao) {
		this.prv4gTransDao = prv4gTransDao;
	}

	public TransHdr getTransHdr(String transId){
		return transHdrDao.getTransHdr(transId);
	}
	
	public void saveHlrTrans(HlrRequest request,HlrResponse response){
		
		try {
			Prv4gTrans trans = new Prv4gTrans();
			trans.setMsisdn(request.getMsisdn());
			trans.setImsi(request.getImsi());
			trans.setAction(request.getAction());
			trans.setRefTransId(request.getOmtransId());
			trans.setServiceType(new Integer(request.getSubType()));
			trans.setStatus(response.getStatus());
			trans.setRespMsg(response.getResponseMsg());
			System.out.println("Saving ");
			prv4gTransDao.save(trans);
			System.out.println("Save Done ");
		} catch (Exception e) {
			logger.error("Error SaveHLR Request [{},] Response [{}] ",request,response,e);
		}
		
	}
	
}
