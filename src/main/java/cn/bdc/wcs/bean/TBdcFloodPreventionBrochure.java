package cn.bdc.wcs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_BDC_FloodPrevention_Brochure")  
public class TBdcFloodPreventionBrochure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, precision=20) 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int floodPreventionId;

	@Column(nullable = false, length=100)
	private String floodPreventionTitle;

	@Column(nullable = false, length=500)
	private String floodPreventionLink;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)//设置为时间类型 
	private Date crateDate;

	@Column(nullable = false, length=20)
	private String createdBy;

	@Column(nullable = false, length=100)
	private String createdUnit;

	@Column(nullable = true, length=100)
	private String createdSubUnit;

	@Column(nullable = true, length=100)
	private String attribute1;

	@Column(nullable = true, length=100)
	private String attribute2;

	@Column(nullable = true, length=100)
	private String attribute3;

	@Column(nullable = true, length=100)
	private String attribute4;

	@Column(nullable = true, length=100)
	private String attribute5;

	public int getFloodPreventionId() {
		return floodPreventionId;
	}

	public void setFloodPreventionId(int floodPreventionId) {
		this.floodPreventionId = floodPreventionId;
	}

	public String getFloodPreventionTitle() {
		return floodPreventionTitle;
	}

	public void setFloodPreventionTitle(String floodPreventionTitle) {
		this.floodPreventionTitle = floodPreventionTitle;
	}

	public String getFloodPreventionLink() {
		return floodPreventionLink;
	}

	public void setFloodPreventionLink(String floodPreventionLink) {
		this.floodPreventionLink = floodPreventionLink;
	}

	public Date getCrateDate() {
		return crateDate;
	}

	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedUnit() {
		return createdUnit;
	}

	public void setCreatedUnit(String createdUnit) {
		this.createdUnit = createdUnit;
	}

	public String getCreatedSubUnit() {
		return createdSubUnit;
	}

	public void setCreatedSubUnit(String createdSubUnit) {
		this.createdSubUnit = createdSubUnit;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	
}
