package cat.prv.om.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.hibernate.type.TextType;

@Entity
@Table(name="TBL_RT_OFFERS_DEL",schema="OMADM")
public class TblRtOffersDel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7367965348935086099L;

	@Id
	@Column(name="RT_OFFER_ID")
	private Integer rtOfferId;
	
	@Column(name="SERVICES_ID",insertable=false,updatable=false)
	private Integer servicesId;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="OFFER_TYPE")
	private Integer offerType;
	
	@Column(name="OFFER_ID")
	private Integer offerId;
	
	
	/*@org.hibernate.annotations.Type( type = "text" )
	@Column(name="OFFER_INST_ID")
	private Long offerInstId;
	*/
	
	@Column(name="DATE_ACTIVE")
	private Date dateActive;
	
	@Column(name="DATE_INACTIVE")
	private Date dateInactive;
	
	@Column(name="CONNECT_REASON")
	private Integer connnectReason;
	
	@Column(name="SO_LEVEL")
	private Integer soLevel;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="REASON")
	private String reason;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="SERVICES_ID",referencedColumnName="SERVICES_ID",updatable=false,insertable=false,nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private TblServices tblServices;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="SERVICES_ID",referencedColumnName="SERVICES_ID",updatable=false,insertable=false,nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private TblServicesHistory tblServicesHistory;
	
	
	@ManyToOne(optional=true)
	@JoinColumn(name="OFFER_ID",referencedColumnName="OFFER_ID",updatable=false,insertable=false,nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private CfgRtSoExt cfgRtSoExt;

	public Integer getRtOfferId() {
		return rtOfferId;
	}

	public void setRtOfferId(Integer rtOfferId) {
		this.rtOfferId = rtOfferId;
	}

	public Integer getServicesId() {
		return servicesId;
	}

	public void setServicesId(Integer servicesId) {
		this.servicesId = servicesId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOfferType() {
		return offerType;
	}

	public void setOfferType(Integer offerType) {
		this.offerType = offerType;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	/*
	public Long getOfferInstId() {
		return offerInstId;
	}

	public void setOfferInstId(Long offerInstId) {
		this.offerInstId = offerInstId;
	}*/

	public Date getDateActive() {
		return dateActive;
	}

	public void setDateActive(Date dateActive) {
		this.dateActive = dateActive;
	}

	public Date getDateInactive() {
		return dateInactive;
	}

	public void setDateInactive(Date dateInactive) {
		this.dateInactive = dateInactive;
	}

	public Integer getConnnectReason() {
		return connnectReason;
	}

	public void setConnnectReason(Integer connnectReason) {
		this.connnectReason = connnectReason;
	}

	public Integer getSoLevel() {
		return soLevel;
	}

	public void setSoLevel(Integer soLevel) {
		this.soLevel = soLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public TblServices getTblServices() {
		return tblServices;
	}

	public void setTblServices(TblServices tblServices) {
		this.tblServices = tblServices;
	}

	public TblServicesHistory getTblServicesHistory() {
		return tblServicesHistory;
	}

	public void setTblServicesHistory(TblServicesHistory tblServicesHistory) {
		this.tblServicesHistory = tblServicesHistory;
	}

	public CfgRtSoExt getCfgRtSoExt() {
		return cfgRtSoExt;
	}

	public void setCfgRtSoExt(CfgRtSoExt cfgRtSoExt) {
		this.cfgRtSoExt = cfgRtSoExt;
	}

	@Override
	public String toString() {
		return "TblRtOffersDel [rtOfferId=" + rtOfferId + ", servicesId=" + servicesId + ", orderId=" + orderId
				+ ", offerType=" + offerType + ", offerId=" + offerId + ", dateActive=" + dateActive + ", dateInactive="
				+ dateInactive + ", connnectReason=" + connnectReason + ", soLevel=" + soLevel + ", status=" + status
				+ ", reason=" + reason + ", tblServices=" + tblServices + "]";
	}


	
}