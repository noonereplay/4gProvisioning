package cat.prv.om.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CFG_RT_SO_EXT",schema="OMADM")
public class CfgRtSoExt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3449434326849438526L;

	@Id
	@Column(name="OFFER_ID")
	private String offerId;
	
	@Column(name="PROV_4G")
	private Integer prov4G;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public Integer getProv4G() {
		return prov4G;
	}

	public void setProv4G(Integer prov4g) {
		prov4G = prov4g;
	}
	
	public boolean isProv4G(){
		return prov4G.intValue() == 1;
	}

	
}
