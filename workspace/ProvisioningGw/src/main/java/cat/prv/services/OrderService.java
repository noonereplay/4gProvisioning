package cat.prv.services;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.swing.text.rtf.RTFEditorKit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.prv.om.dao.CfgRtSoExtDao;
import cat.prv.om.dao.TblRtOffersDao;
import cat.prv.om.dao.TblRtOffersDelDao;
import cat.prv.om.dao.TblServicesDao;
import cat.prv.om.dao.TblServicesHistoryDao;
import cat.prv.om.dao.TransDtlDao;
import cat.prv.om.dao.TransHdrDao;
import cat.prv.om.entity.CfgRtSoExt;
import cat.prv.om.entity.TblRtOffers;
import cat.prv.om.entity.TblRtOffersDel;
import cat.prv.om.entity.TblServices;
import cat.prv.om.entity.TransDtl;
import cat.prv.om.entity.TransHdr;
import cat.prv.util.OrderType;
import cat.rtc.gw.rs.domain.offer.OfferInfo;
import cat.rtc.gw.rs.domain.subscriber.SubscriberInfo;

@Service
public class OrderService {

	Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private TransHdrDao transHdrDao;
	@Autowired
	private TransDtlDao transDtlDao;
	@Autowired
	private TblServicesDao tblServicesDao;
	@Autowired
	private TblServicesHistoryDao tblServicesHistoryDao;
	@Autowired
	private TblRtOffersDao tblRtOffersDao;
	@Autowired
	private TblRtOffersDelDao tblRtOffersDelDao;
	@Autowired
	private CfgRtSoExtDao cfgRtSoExtDao;
	
	@Autowired
	private C1rtGwService c1rtGwService;
	


	@Autowired
	private Provisioning4GService provisioning4gService;

	public void setTransHdrDao(TransHdrDao transHdrDao) {
		this.transHdrDao = transHdrDao;
	}

	public void setTransDtlDao(TransDtlDao transDtlDao) {
		this.transDtlDao = transDtlDao;
	}

	public void setTblServicesDao(TblServicesDao tblServicesDao) {
		this.tblServicesDao = tblServicesDao;
	}

	public void setTblServicesHistoryDao(TblServicesHistoryDao tblServicesHistoryDao) {
		this.tblServicesHistoryDao = tblServicesHistoryDao;
	}

	public void setTblRtOffersDao(TblRtOffersDao tblRtOffersDao) {
		this.tblRtOffersDao = tblRtOffersDao;
	}

	public void setTblRtOffersDelDao(TblRtOffersDelDao tblRtOffersDelDao) {
		this.tblRtOffersDelDao = tblRtOffersDelDao;
	}

	public void setCfgRtSoExtDao(CfgRtSoExtDao cfgRtSoExtDao) {
		this.cfgRtSoExtDao = cfgRtSoExtDao;
	}

	public void setProvisioning4gService(Provisioning4GService provisioning4gService) {
		this.provisioning4gService = provisioning4gService;
	}
	
	public void setC1rtGwService(C1rtGwService c1rtGwService) {
		this.c1rtGwService = c1rtGwService;
	}

	public boolean makeProvisioning4G(String transId) throws Exception {

		TransHdr transHdr = transHdrDao.getTransHdr(transId);
		List<TransDtl> dtl = transHdr.getTransDtl();

		String msisdn = dtl.stream().filter(d -> d.getMsisdn() != null).findFirst().get().getMsisdn();

		boolean isProv = false;
		Date now = new Date();
		try {
			TblServices tblServices = tblServicesDao.getTblServiceByMsisdn(msisdn);
			

			if (transHdr.getOrderType() == OrderType.ADD_SO2) {
				Integer offerId = dtl.stream().filter(d -> d.getOfferId() != null).findFirst().get().getOfferId();
				CfgRtSoExt ext = cfgRtSoExtDao.getCfgRtSoExt(String.valueOf(offerId.intValue()));
				if (ext != null && ext.isProv4G()) {
					isProv = true;
					provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(),transId, isProv);
				}
			} else if (transHdr.getOrderType() == OrderType.DEL_SO2) {
				Integer offerId = dtl.stream().filter(d -> d.getOfferId() != null).findFirst().get().getOfferId();
				CfgRtSoExt ext = cfgRtSoExtDao.getCfgRtSoExt(String.valueOf(offerId.intValue()));


				try {
					/*
					 * Set<TblRtOffersDel> listDel =
					 * tblServices.getTblRtOffersDelList(); Set<TblRtOffers>
					 * listAdd = tblServices.getTblRtOffersList();
					 * 
					 * Stream<TblRtOffers> stream = listAdd.stream().filter(f ->
					 * (f.getDateInactive() == null || (f.getDateInactive() !=
					 * null && now.compareTo(f.getDateInactive()) < 0 ) ));
					 * 
					 * if(listDel != null ){ stream = stream.filter(p ->
					 * listDel.stream().noneMatch(d ->
					 * d.getOfferId().equals(p.getOfferId()) &&
					 * d.getDateActive().after(p.getDateActive()))); }
					 * 
					 * if (stream.anyMatch(p -> p.getCfgRtSoExt() != null &&
					 * p.getCfgRtSoExt().isProv4G())) { isProv = true; }
					 */
					
					/*
					if (ext != null && ext.isProv4G()) {
						isProv = false;
						provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(), transId, isProv);
					}*/
					
					
					SubscriberInfo info = c1rtGwService.info(msisdn);
					if(consist4GOffer(info) == false){
						isProv = false;
						provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(), transId, isProv);
					}
					

					System.out.println(transId + " isProv : " + isProv);

				} catch (Exception e) {
					logger.error("Error Offer 4G Not Found , msisdn : {} , offerId : {} ", msisdn, offerId, e);
				}

			} else if (transHdr.getOrderType() == OrderType.CHG_SO1 || transHdr.getOrderType() == OrderType.PRE_TO_POST1
					|| transHdr.getOrderType() == OrderType.PRE_TO_POST2
					|| transHdr.getOrderType() == OrderType.POST_TO_PRE) {

				if (transHdr.getTransGroupId().contains("-B") && transHdr.getTransGroupId().contains("-2")) {

					TransDtl transDtl = transHdr.getTransDtl().stream().filter(t -> t.getTransTypeId().equals("100104"))
							.findFirst().get();

					TblRtOffers offer = tblRtOffersDao.getTblRtOffer(transDtl.getLinkId());

					if (offer.getCfgRtSoExt() == null) {
						Set<TblRtOffersDel> listDel = tblServices.getTblRtOffersDelList();
						Set<TblRtOffers> listAdd = tblServices.getTblRtOffersList();

						Stream<TblRtOffers> stream = listAdd.stream().filter(f -> (f.getDateInactive() == null
								|| (f.getDateInactive() != null && now.compareTo(f.getDateInactive()) < 0)));

						if (listDel != null) {
							stream = stream
									.filter(p -> listDel.stream().noneMatch(d -> d.getOfferId().equals(p.getOfferId())
											&& d.getDateActive().after(p.getDateActive())));
						}

						if (stream.anyMatch(p -> p.getCfgRtSoExt() != null && p.getCfgRtSoExt().isProv4G())) {
							isProv = true;
						} else {
							isProv = false;
						}
					} else if (offer.getCfgRtSoExt().isProv4G()) {
						isProv = true;
					}

					provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(), transId, isProv);

				}

			} else if (transHdr.getOrderType() == OrderType.NEW_POSTPAID) {
				TransDtl transDtl = transHdr.getTransDtl().stream().filter(t -> t.getTransTypeId().equals("100104"))
						.findFirst().get();

				TblRtOffers offer = tblRtOffersDao.getTblRtOffer(transDtl.getLinkId());
				if (offer.getCfgRtSoExt() != null && offer.getCfgRtSoExt().isProv4G()) {
					isProv = true;
					provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(), transId, isProv);
				}

			} else if (transHdr.getOrderType() == OrderType.NEW_PREPAID
					|| transHdr.getOrderType() == OrderType.RECONNECT) {
				TransDtl transDtl = transHdr.getTransDtl().stream().filter(t -> t.getTransTypeId().equals("100202"))
						.findFirst().get();

				TblServices tblServicesLink = tblServicesDao.getTblServices(transDtl.getLinkId());

				if (tblServicesLink.getTblRtOffersList().stream()
						.anyMatch(p -> p.getCfgRtSoExt() != null && p.getCfgRtSoExt().isProv4G())) {
					isProv = true;
					provisioning(msisdn,tblServicesLink.getImsi(),tblServicesLink.getRtServiceType(), transId, isProv);
				}

			} else if (transHdr.getOrderType() == OrderType.ACTIVATE_SO) {

				TransDtl transDtl = transHdr.getTransDtl().stream().filter(t -> t.getTransTypeId().equals("100272"))
						.findFirst().get();

				TblRtOffers offer = tblRtOffersDao.getTblRtOffer(transDtl.getLinkId());
				if (offer.getCfgRtSoExt() != null && offer.getCfgRtSoExt().isProv4G()
						&& offer.getOrderId().equals(transDtl.getOrderId())) {
					isProv = true;
					provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(),transId, isProv);
				}

			} else if (transHdr.getOrderType() == OrderType.TERMINATE || transHdr.getOrderType() == OrderType.SUSPEND) {
				isProv = false;
				provisioning(msisdn,tblServices.getImsi(),tblServices.getRtServiceType(),transId, isProv);
			}

		} catch (Exception e) {
			logger.error("Error Offer 4G Not Found , msisdn : {}  ", msisdn, e);
		}

		return isProv;
	}

	private void provisioning(String msisdn,String imsi, Integer serviceType,String transId, boolean isProv4G) {
		
		String serviceTypeStr = "1";
		try {
			serviceTypeStr = serviceType.toString();
		} catch (Exception e) {
			logger.error("serviceType Parser Error",e);
		}
		if (isProv4G) {
			provisioning4gService.Enable4G(msisdn,imsi,serviceTypeStr,transId);
		} else {
			provisioning4gService.Disable4G(msisdn,imsi,serviceTypeStr,transId);
		}
	}
	
	
	public boolean consist4GOffer(SubscriberInfo info){
		
		boolean result = false;
		
		for (OfferInfo offer : info.getOfferInfoList()) {
			CfgRtSoExt ext = cfgRtSoExtDao.getCfgRtSoExt(String.valueOf(offer.getId()));
			if(ext != null){
				if(ext.isProv4G()){ return true;}
				
			}
		}
		return result;
		
	}

}
