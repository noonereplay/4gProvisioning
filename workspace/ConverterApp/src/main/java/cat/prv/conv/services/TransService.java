package cat.prv.conv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.prv.om.dao.TransHdrDao;
import cat.prv.om.entity.TransHdr;

@Service
public class TransService {

	@Autowired
	private TransHdrDao transHdrDao;

	public void setTransHdrDao(TransHdrDao transHdrDao) {
		this.transHdrDao = transHdrDao;
	}
	
	public TransHdr getTransHdr(String transId){
		return transHdrDao.getTransHdr(transId);
	}
}
