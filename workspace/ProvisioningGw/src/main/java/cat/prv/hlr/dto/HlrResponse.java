package cat.prv.hlr.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="HLR_Res")
@XmlType(propOrder={"omTransId","action","msisdn","imsi","status","responseMsg"})
@XmlAccessorType(XmlAccessType.NONE)
public class HlrResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9036861749995001536L;

	@XmlElement(name="OMTRANS_ID")
	private String omTransId;
	
	@XmlElement(name="ACTION")
	private String action;
	
	@XmlElement(name="MSISDN")
	private String msisdn;
	
	@XmlElement(name="IMSI")
	private String imsi;
	
	@XmlElement(name="STATUS")
	private String status;
	
	@XmlElement(name="RES_MSG")
	private String responseMsg;

	public String getOmTransId() {
		return omTransId;
	}

	public void setOmTransId(String omTransId) {
		this.omTransId = omTransId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
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

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	@Override
	public String toString() {
		return "HlrResponse [omTransId=" + omTransId + ", action=" + action + ", msisdn=" + msisdn + ", imsi=" + imsi
				+ ", status=" + status + ", responseMsg=" + responseMsg + "]";
	}
	
	
	
}
