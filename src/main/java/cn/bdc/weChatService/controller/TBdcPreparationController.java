package cn.bdc.weChatService.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.weChatService.bean.TBdcPreparationNotice;
import cn.bdc.weChatService.bean.TBdcPreparationOrder;
import cn.bdc.weChatService.service.TBdcPreparationNoticeService;
import cn.bdc.weChatService.service.TBdcPreparationOrderService;


@RestController
@RequestMapping("/preparation")
public class TBdcPreparationController {
	
	//防汛备勤令
	@Resource
	private TBdcPreparationOrderService tBdcPreparationOrderService;
	
	@RequestMapping("/order/save")
	public String saveOrder() {
		TBdcPreparationOrder tBdcPreparationOrder = new TBdcPreparationOrder();
		tBdcPreparationOrder.setPreparationTitle("Tile Preparation Order");
		tBdcPreparationOrder.setPreparationVersion("1.0");
		tBdcPreparationOrder.setPreparationContent("Content Preparation Order");
		tBdcPreparationOrder.setPublishDate(new Date());
		tBdcPreparationOrder.setCrateDate(new Date());
		tBdcPreparationOrder.setCreatedBy("-1");
		tBdcPreparationOrder.setCreatedUnit("-1");
		
		tBdcPreparationOrderService.save(tBdcPreparationOrder);
		
		return "save ok.";
	}
	
	@RequestMapping("/order/delete")
	public String deleteOrder(int id) {
		tBdcPreparationOrderService.delete(id);
		return "delete ok.";
	}

	@RequestMapping("/order/getAll")
	public Iterable<TBdcPreparationOrder> getAllOrder(){
		return tBdcPreparationOrderService.getAll();
	}
	
	
	//通知
	@Resource
	private TBdcPreparationNoticeService tBdcPreparationNoticeService;
	
	@RequestMapping("/notice/save")
	public String saveNotice() {
		TBdcPreparationNotice tBdcPreparationNotice = new TBdcPreparationNotice();
		tBdcPreparationNotice.setPreparationTitle("Tile Preparation Notice");
		tBdcPreparationNotice.setPreparationContent("Content Preparation Notice");
		tBdcPreparationNotice.setPublishDate(new Date());
		tBdcPreparationNotice.setCrateDate(new Date());
		tBdcPreparationNotice.setCreatedBy("-1");
		tBdcPreparationNotice.setCreatedUnit("-1");
		
		tBdcPreparationNoticeService.save(tBdcPreparationNotice);
		
		return "save ok.";
	}
	
	@RequestMapping("/notice/delete")
	public String deleteNotice(int id) {
		tBdcPreparationNoticeService.delete(id);
		return "delete ok.";
	}

	@RequestMapping("/notice/getAll")
	public Iterable<TBdcPreparationNotice> getAllNotice(){
		return tBdcPreparationNoticeService.getAll();
	}

}
