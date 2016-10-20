package cat.prv.om.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_SERVICES",schema="OMADM")
@NamedQueries({
    @NamedQuery(name="TblServices.findByMsisdn",
                query="SELECT s FROM TblServices s where s.msisdn = :msisdn ")
}) 
public class TblServices implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6695548299965197221L;

	@Id
	@Column(name="SERVICES_ID")
	private Integer servicesId;
	
	@Column(name="ACCOUNTS_ID")
	private Integer accountsId;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="SERVICE_TYPE")
	private Integer serviceType;
	
	@Column(name="RT_ACCOUNT_INTERNAL_ID")
	private Integer rtAccountInternald;
	
	@Column(name="RT_SERVICE_INTERNAL_ID")
	private Integer rtServiceInternalId;
	
	@Column(name="STATUS_REASON_ID")
	private Integer statusReasonId;
	
	@Column(name="SERVICE_EXTERNALID_01")
	private String msisdn;
	
	@Column(name="SERVICE_ICCID_FX")
	private String iccid;
	
	@Column(name="SERVICE_EXTERNALID_02")
	private String imsi;
	
	@Column(name="SERVICE_EXTERNALID_TYPE_01_RT")
	private Integer rtServiceType;
	
	@Column(name="RATING_STATE")
	private Integer ratingState;
	
	@Column(name="STATUS")
	private String status;
	
	@OneToMany(mappedBy="servicesId",targetEntity=TblRtOffers.class,fetch=FetchType.EAGER)
	private List<TblRtOffers> tblRtOffersList;

	@OneToMany(mappedBy="servicesId",targetEntity=TblRtOffersDel.class,fetch=FetchType.EAGER)
	private List<TblRtOffersDel> tblRtOffersDelList;
	
	public Integer getServicesId() {
		return servicesId;
	}

	public void setServicesId(Integer servicesId) {
		this.servicesId = servicesId;
	}

	public Integer getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Integer accountsId) {
		this.accountsId = accountsId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getRtAccountInternald() {
		return rtAccountInternald;
	}

	public void setRtAccountInternald(Integer rtAccountInternald) {
		this.rtAccountInternald = rtAccountInternald;
	}

	public Integer getRtServiceInternalId() {
		return rtServiceInternalId;
	}

	public void setRtServiceInternalId(Integer rtServiceInternalId) {
		this.rtServiceInternalId = rtServiceInternalId;
	}

	public Integer getStatusReasonId() {
		return statusReasonId;
	}

	public void setStatusReasonId(Integer statusReasonId) {
		this.statusReasonId = statusReasonId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Integer getRtServiceType() {
		return rtServiceType;
	}

	public void setRtServiceType(Integer rtServiceType) {
		this.rtServiceType = rtServiceType;
	}

	public Integer getRatingState() {
		return ratingState;
	}

	public void setRatingState(Integer ratingState) {
		this.ratingState = ratingState;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public List<TblRtOffers> getTblRtOffersList() {
		return tblRtOffersList;
	}

	public void setTblRtOffersList(List<TblRtOffers> tblRtOffersList) {
		this.tblRtOffersList = tblRtOffersList;
	}

	public List<TblRtOffersDel> getTblRtOffersDelList() {
		return tblRtOffersDelList;
	}

	public void setTblRtOffersDelList(List<TblRtOffersDel> tblRtOffersDelList) {
		this.tblRtOffersDelList = tblRtOffersDelList;
	}

	@Override
	public String toString() {
		return "TblServices [servicesId=" + servicesId + ", accountsId=" + accountsId + ", orderId=" + orderId
				+ ", serviceType=" + serviceType + ", rtAccountInternald=" + rtAccountInternald
				+ ", rtServiceInternalId=" + rtServiceInternalId + ", statusReasonId=" + statusReasonId + ", msisdn="
				+ msisdn + ", iccid=" + iccid + ", imsi=" + imsi + ", rtServiceType=" + rtServiceType + ", ratingState="
				+ ratingState + ", status=" + status + "]";
	}
	
}
