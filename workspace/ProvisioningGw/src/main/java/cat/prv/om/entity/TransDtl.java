package cat.prv.om.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRANS_DTL",schema="OMADM")
public class TransDtl {

	@Id
	@Column(name="TRANS_DTL_ID")
	private String transDtlId;
	
	@Column(name="TRANS_TYPE_ID")
	private String transTypeId;
	
	@Column(name="PROCESS_CODE")
	private String processCode;
	
	@Column(name="LINK_ID")
	private String linkId;
	
	@Column(name="MSISDN")
	private String msisdn;
	
	@Column(name="ICCID")
	private String iccid;
	
	@Column(name="IMSI")
	private String imsi;
	
	@Column(name="OFFER_ID")
	private String offerId;
	
	@Column(name="RATING_STATE")
	private String ratingState;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PROVISION_STATUS")
	private Integer provisionStatus;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="SYSTEM_CODE")
	private String systemCode;
	
	@Column(name="SYSTEM_SEQ")
	private Integer systemSeq;
	
	@Column(name="ERROR_CODE")
	private String errorCode;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="TRANS_ID",referencedColumnName="TRANS_ID")
	private TransHdr transHdr;

	public String getTransDtlId() {
		return transDtlId;
	}

	public void setTransDtlId(String transDtlId) {
		this.transDtlId = transDtlId;
	}

	public String getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(String transTypeId) {
		this.transTypeId = transTypeId;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public TransHdr getTransHdr() {
		return transHdr;
	}

	public void setTransHdr(TransHdr transHdr) {
		this.transHdr = transHdr;
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

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getRatingState() {
		return ratingState;
	}

	public void setRatingState(String ratingState) {
		this.ratingState = ratingState;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProvisionStatus() {
		return provisionStatus;
	}

	public void setProvisionStatus(Integer provisionStatus) {
		this.provisionStatus = provisionStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public Integer getSystemSeq() {
		return systemSeq;
	}

	public void setSystemSeq(Integer systemSeq) {
		this.systemSeq = systemSeq;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}