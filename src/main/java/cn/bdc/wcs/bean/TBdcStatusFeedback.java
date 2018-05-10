package cn.bdc.wcs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_BDC_Status_Feedback")  
public class TBdcStatusFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, precision=20)//设置属性preparationOrderId对应的字段为preparation_order_id，20位数字可保留0小数，不可以为空  
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int statusFeedbackId;
	
//	@Column(nullable = false, precision=20, insertable= false, updatable= false)
//	private int preparationOrderId;
	
	@JoinColumn(name = "preparationOrderId", referencedColumnName = "preparationOrderId", nullable = false)//设置对应数据表的列名和引用的数据表的列名       
	@ManyToOne//设置在“一方”pojo的外键字段上       
	private TBdcPreparationOrder preparationOrderId;
	
	@Column(nullable = true, precision=20)
	private int largeEmergencyUnits;
	
	@Column(nullable = true, precision=20)
	private int largeEmergencyHumans;
	
	@Column(nullable = true, precision=20)
	private int midEmergencyUnits;
	
	@Column(nullable = true, precision=20)
	private int midEmergencyHumans;
	
	@Column(nullable = true, precision=20)
	private int smallEmergencyUnits;
	
	@Column(nullable = true, precision=20)
	private int smallEmergencyHumans;
	
	@Column(nullable = true, precision=20)
	private int vehiclePatrolsUnits;
	
	@Column(nullable = true, precision=20)
	private int vehiclePatrolsHumans;
	
	@Column(nullable = true, precision=20)
	private int salvageUnits;
	
	@Column(nullable = true, precision=20)
	private int salvageHumans;
	
	@Column(nullable = true, precision=20)
	private int repairemergencyUnits;
	
	@Column(nullable = true, precision=20)
	private int repairemergencyHumans;
	
	@Column(nullable = true, precision=20)
	private int pumpHumans;
	
	@Column(nullable = true, precision=20)
	private int commandPost;
	
	@Column(nullable = true, precision=20)
	private int allHumans;

	@Column(nullable = true, precision=2)
	private int status;

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

	public int getStatusFeedbackId() {
		return statusFeedbackId;
	}

	public void setStatusFeedbackId(int statusFeedbackId) {
		this.statusFeedbackId = statusFeedbackId;
	}

	public TBdcPreparationOrder getPreparationOrderId() {
		return preparationOrderId;
	}

	public void setPreparationOrderId(TBdcPreparationOrder preparationOrderId) {
		this.preparationOrderId = preparationOrderId;
	}

	public int getLargeEmergencyUnits() {
		return largeEmergencyUnits;
	}

	public void setLargeEmergencyUnits(int largeEmergencyUnits) {
		this.largeEmergencyUnits = largeEmergencyUnits;
	}

	public int getLargeEmergencyHumans() {
		return largeEmergencyHumans;
	}

	public void setLargeEmergencyHumans(int largeEmergencyHumans) {
		this.largeEmergencyHumans = largeEmergencyHumans;
	}

	public int getMidEmergencyUnits() {
		return midEmergencyUnits;
	}

	public void setMidEmergencyUnits(int midEmergencyUnits) {
		this.midEmergencyUnits = midEmergencyUnits;
	}

	public int getMidEmergencyHumans() {
		return midEmergencyHumans;
	}

	public void setMidEmergencyHumans(int midEmergencyHumans) {
		this.midEmergencyHumans = midEmergencyHumans;
	}

	public int getSmallEmergencyUnits() {
		return smallEmergencyUnits;
	}

	public void setSmallEmergencyUnits(int smallEmergencyUnits) {
		this.smallEmergencyUnits = smallEmergencyUnits;
	}

	public int getSmallEmergencyHumans() {
		return smallEmergencyHumans;
	}

	public void setSmallEmergencyHumans(int smallEmergencyHumans) {
		this.smallEmergencyHumans = smallEmergencyHumans;
	}

	public int getVehiclePatrolsUnits() {
		return vehiclePatrolsUnits;
	}

	public void setVehiclePatrolsUnits(int vehiclePatrolsUnits) {
		this.vehiclePatrolsUnits = vehiclePatrolsUnits;
	}

	public int getVehiclePatrolsHumans() {
		return vehiclePatrolsHumans;
	}

	public void setVehiclePatrolsHumans(int vehiclePatrolsHumans) {
		this.vehiclePatrolsHumans = vehiclePatrolsHumans;
	}

	public int getSalvageUnits() {
		return salvageUnits;
	}

	public void setSalvageUnits(int salvageUnits) {
		this.salvageUnits = salvageUnits;
	}

	public int getSalvageHumans() {
		return salvageHumans;
	}

	public void setSalvageHumans(int salvageHumans) {
		this.salvageHumans = salvageHumans;
	}

	public int getRepairemergencyUnits() {
		return repairemergencyUnits;
	}

	public void setRepairemergencyUnits(int repairemergencyUnits) {
		this.repairemergencyUnits = repairemergencyUnits;
	}

	public int getRepairemergencyHumans() {
		return repairemergencyHumans;
	}

	public void setRepairemergencyHumans(int repairemergencyHumans) {
		this.repairemergencyHumans = repairemergencyHumans;
	}

	public int getPumpHumans() {
		return pumpHumans;
	}

	public void setPumpHumans(int pumpHumans) {
		this.pumpHumans = pumpHumans;
	}

	public int getCommandPost() {
		return commandPost;
	}

	public void setCommandPost(int commandPost) {
		this.commandPost = commandPost;
	}

	public int getAllHumans() {
		return allHumans;
	}

	public void setAllHumans(int allHumans) {
		this.allHumans = allHumans;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
