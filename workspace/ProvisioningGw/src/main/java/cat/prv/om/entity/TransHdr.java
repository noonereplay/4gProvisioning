package cat.prv.om.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="TRANS_HDR",schema="OMADM")
public class TransHdr {

	@Id
	@Column(name="TRANS_ID")
	private String transId;
	
	@Column(name="REF_TRANS_ID")
	private String refTransId;
	
	@Column(name="CREATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="FUTURE_DATE")
	@Temporal(TemporalType.DATE)
	private Date futureDate;
	
	@Column(name="TRANS_GROUP_ID")
	private String transGroupId;
	
	@Column(name="PROCESS_CODE")
	private String processCode;
	
	@Column(name="PROCESS_RESULT")
	private String processResult;
	
	@Column(name="LAST_UPDATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	
	@Column(name="ERROR_CODE")
	private String errorCode;
	
	@Column(name="STATUS")
	private String status;
	
	@OneToMany(mappedBy="transHdr",targetEntity=TransDtl.class,fetch=FetchType.EAGER)
	private TransDtl transDtl;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getRefTransId() {
		return refTransId;
	}

	public void setRefTransId(String refTransId) {
		this.refTransId = refTransId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getFutureDate() {
		return futureDate;
	}

	public void setFutureDate(Date futureDate) {
		this.futureDate = futureDate;
	}

	public String getTransGroupId() {
		return transGroupId;
	}

	public void setTransGroupId(String transGroupId) {
		this.transGroupId = transGroupId;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public TransDtl getTransDtl() {
		return transDtl;
	}

	public void setTransDtl(TransDtl transDtl) {
		this.transDtl = transDtl;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}