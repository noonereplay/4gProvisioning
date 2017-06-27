package cat.prv.hlr.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="HLR_IN")
@XmlType(propOrder={"action","msisdn","imsi","omtransId","subType"})
@XmlAccessorType(XmlAccessType.NONE)
public class HlrRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3342452605219270624L;

	@XmlElement(name="ACTION")
	private String action;
	
	@XmlElement(name="MSISDN")
	private String msisdn;
	
	@XmlElement(name="IMSI",nillable=true)
	private String imsi;
	
	@XmlElement(name="OMTRANS_ID")
	private String omtransId;
	
	@XmlElement(name="SUB_TYPE")
	private String subType;
	
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
	public String getOmtransId() {
		return omtransId;
	}
	public void setOmtransId(String omtransId) {
		this.omtransId = omtransId;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	@Override
	public String toString() {
		return "HlrRequest [action=" + action + ", msisdn=" + msisdn + ", imsi=" + imsi + ", omtransId=" + omtransId
				+ ", subType=" + subType + "]";
	}
	
}
