package cat.prv.om.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="TRANS_DTL",schema="OMADM")
/*@NamedNativeQueries({
	@NamedNativeQuery(name="TransdDtlId", query= "select * from OMADM.TRANS_DTL where TRANS_DTL_ID = :transDtlId",resultClass=TransDtl.class)
}
)*/
public class TransDtl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3368719598983075290L;

	@Id
	@Column(name="TRANS_DTL_ID")
	private String transDtlId;
	
	@Column(name="TRANS_TYPE_ID")
	private String transTypeId;
	
	@Column(name="PROCESS_CODE")
	private String processCode;
	
	@Column(name="LINK_ID")
	private Integer linkId;
	
	@Column(name="MSISDN")
	private String msisdn;
	
	@Column(name="ICCID")
	private String iccid;
	
	@Column(name="IMSI")
	private String imsi;
	
	@Column(name="OFFER_ID")
	private Integer offerId;
	
	@Column(name="RATING_STATE")
	private Integer ratingState;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PROVISION_STATUS")
	private String provisionStatus;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="SYSTEM_CODE")
	private String systemCode;
	
	@Column(name="SYSTEM_SEQ")
	private Integer systemSeq;
	
	@Column(name="ERROR_CODE")
	private String errorCode;
	
	@Column(name="TRANS_ID",insertable=false,updatable=false)
	private String transId;
	
	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

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

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
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

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
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

	public String getProvisionStatus() {
		return provisionStatus;
	}

	public void setProvisionStatus(String provisionStatus) {
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

	@Override
	public String toString() {
		return "TransDtl [transDtlId=" + transDtlId + ", transTypeId=" + transTypeId + ", processCode=" + processCode
				+ ", linkId=" + linkId + ", msisdn=" + msisdn + ", iccid=" + iccid + ", imsi=" + imsi + ", offerId="
				+ offerId + ", ratingState=" + ratingState + ", status=" + status + ", provisionStatus="
				+ provisionStatus + ", orderId=" + orderId + ", systemCode=" + systemCode + ", systemSeq=" + systemSeq
				+ ", errorCode=" + errorCode + ", transId=" + transId + ", transHdr=" + transHdr + "]";
	}
	
	
	
}
