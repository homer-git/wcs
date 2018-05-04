package cn.bdc.wcs.bean;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_BDC_Preparation_Order")  
public class TBdcPreparationOrder implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Column(name = "preparation_order_id", nullable = false, precision=12, scale=2)//设置属性preparationOrderId对应的字段为preparation_order_id，12位数字可保留两位小数，可以为空  
	@Id
	@Column(nullable = false, precision=20)//设置属性preparationOrderId对应的字段为preparation_order_id，20位数字可保留0小数，不可以为空  
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int preparationOrderId;
	
	@Column(nullable = false, length=100)
	private String preparationTitle;

	@Column(nullable = true, length=20)
	private String preparationVersion;

	@Column(nullable = false, length=1000)
	private String preparationContent;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)//设置为时间类型    
	private Date publishDate;

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

	public int getPreparationOrderId() {
		return preparationOrderId;
	}

	public void setPreparationOrderId(int preparationOrderId) {
		this.preparationOrderId = preparationOrderId;
	}

	public String getPreparationTitle() {
		return preparationTitle;
	}

	public void setPreparationTitle(String preparationTitle) {
		this.preparationTitle = preparationTitle;
	}

	public String getPreparationVersion() {
		return preparationVersion;
	}

	public void setPreparationVersion(String preparationVersion) {
		this.preparationVersion = preparationVersion;
	}

	public String getPreparationContent() {
		return preparationContent;
	}

	public void setPreparationContent(String preparationContent) {
		this.preparationContent = preparationContent;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "preparationOrderId")//指向多的那方的pojo的关联外键字段       
	private Collection<TBdcStatusFeedback> tBdcStatusFeedbackCollection;     
	
}
