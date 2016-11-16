package cat.prv.om.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRV_4G_TRANS",schema="PRV4G_APP")

public class Prv4gTrans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4357989216859113514L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="prvTranSeq")
	@SequenceGenerator(name="prvTranSeq", sequenceName="PRV_4G_TRANS_SEQ",allocationSize=1)
    @Basic(optional = false)
	@Column(name="TRANS_ID")
	private Long transId;
	
	@Column(name="SERVICE_TYPE")
	private Integer serviceType;
	
	@Column(name="MSISDN")
	private String msisdn;
	
	@Column(name="ACTION")
	private String action;
	
	@Column(name="IMSI")
	private String imsi;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="REF_TRANS_ID")
	private String refTransId;
	
	@Column(name="RESP_MSG")
	private String respMsg;

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRefTransId() {
		return refTransId;
	}

	public void setRefTransId(String refTransId) {
		this.refTransId = refTransId;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	
}
