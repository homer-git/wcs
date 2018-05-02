package cn.bdc.weChatService.bean;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "T_BDC_Preparation_Order")
@Entity
public class TBdcPreparationOrder {
	
	private int preparationOrderId;
	private String preparationTitle;
	private String preparationVersion;
	private String preparationContent;
	private Date publishDate;
	private String createdBy;

}
