package cat.prv.om.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_SERVICES_HISTORY",schema="OMADM")
public class TblServicesHistory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7748790981079148660L;

	@Id
	@Column(name="HISTORY_ID")	
	private Integer historyId;
	
	@Column(name="HISTORY_REASON")
	private String historyReason;
	
	@Column(name="SERVICES_ID")
	private Integer servicesId;
	
	@Column(name="ORDER_ID_OLD")
	private String orderIdOld;
	
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

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public String getHistoryReason() {
		return historyReason;
	}

	public void setHistoryReason(String historyReason) {
		this.historyReason = historyReason;
	}

	public Integer getServicesId() {
		return servicesId;
	}

	public void setServicesId(Integer servicesId) {
		this.servicesId = servicesId;
	}

	public String getOrderIdOld() {
		return orderIdOld;
	}

	public void setOrderIdOld(String orderIdOld) {
		this.orderIdOld = orderIdOld;
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

	@Override
	public String toString() {
		return "TblServicesHistory [historyId=" + historyId + ", historyReason=" + historyReason + ", servicesId="
				+ servicesId + ", orderIdOld=" + orderIdOld + ", accountsId=" + accountsId + ", orderId=" + orderId
				+ ", serviceType=" + serviceType + ", rtAccountInternald=" + rtAccountInternald
				+ ", rtServiceInternalId=" + rtServiceInternalId + ", statusReasonId=" + statusReasonId + ", msisdn="
				+ msisdn + ", iccid=" + iccid + ", imsi=" + imsi + ", rtServiceType=" + rtServiceType + ", ratingState="
				+ ratingState + ", status=" + status + "]";
	}
	
	
}