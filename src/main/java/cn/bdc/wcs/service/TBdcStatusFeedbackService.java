package cn.bdc.wcs.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.bean.TBdcStatusFeedback;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcStatusFeedbackRepository;

@Service
public class TBdcStatusFeedbackService extends BaseService {
	
	@Resource
	private TBdcStatusFeedbackRepository tBdcStatusFeedbackRepository;
	
	@Transactional
	public void save(TBdcStatusFeedback tBdcStatusFeedback) {
		tBdcStatusFeedbackRepository.save(tBdcStatusFeedback);
	}

	@Transactional
	public void delete(int id) {
		tBdcStatusFeedbackRepository.deleteById(id);
	}
	
	public Iterable<TBdcStatusFeedback> getAll() {
		return tBdcStatusFeedbackRepository.findAll();
	}

	public Iterable<TBdcStatusFeedback> getAllById(Iterable<Integer> ids){
		return tBdcStatusFeedbackRepository.findAllById(ids);
	}

	public Optional<TBdcStatusFeedback> getById(int id){
		return tBdcStatusFeedbackRepository.findById(id);
	}

	public Iterable<TBdcStatusFeedback> getAllByPreparationOrderId(int preparationOrderId){
		TBdcPreparationOrder tBdcPreparationOrder = new TBdcPreparationOrder();
		tBdcPreparationOrder.setPreparationOrderId(preparationOrderId);
		return tBdcStatusFeedbackRepository.findAllByPreparationOrderId(tBdcPreparationOrder);
	}

	@Transactional
	public Optional<TBdcStatusFeedback> getSumByPreparationOrderId(int preparationOrderId){

//		Query query = em.createQuery("SELECT sum(largeEmergencyHumans), sum(largeEmergencyHumans), sum(largeEmergencyUnits), sum(midEmergencyHumans), sum(midEmergencyUnits), sum(smallEmergencyHumans), sum(smallEmergencyUnits), sum(vehiclePatrolsHumans), sum(vehiclePatrolsUnits), sum(salvageHumans), sum(salvageUnits), sum(repairemergencyHumans), sum(repairemergencyUnits), sum(pumpHumans), sum(commandPost) FROM  TBdcStatusFeedback WHERE preparationOrderId = :Id AND status = 1 group by preparationOrderId");
		Query query = em.createQuery("SELECT sum(t.largeEmergencyHumans), sum(t.largeEmergencyUnits), sum(t.midEmergencyHumans), sum(t.midEmergencyUnits), sum(t.smallEmergencyHumans), sum(t.smallEmergencyUnits), sum(t.vehiclePatrolsHumans), sum(t.vehiclePatrolsUnits), sum(t.salvageHumans), sum(t.salvageUnits), sum(t.repairemergencyHumans), sum(t.repairemergencyUnits), sum(t.pumpHumans), sum(t.commandPost), sum(t.allHumans) FROM  TBdcStatusFeedback t WHERE t.preparationOrderId = :Id AND t.status = 1 group by t.preparationOrderId");
		TBdcPreparationOrder tBdcPreparationOrder = new TBdcPreparationOrder();
		tBdcPreparationOrder.setPreparationOrderId(preparationOrderId);
		query.setParameter("Id", tBdcPreparationOrder); 
		List list = query.getResultList();
		TBdcStatusFeedback tBdcStatusFeedback = new TBdcStatusFeedback();
		tBdcStatusFeedback.setPreparationOrderId(tBdcPreparationOrder);
		if(null != list) {
			Iterator iterator = list.iterator();
			while(iterator.hasNext()) {
				Object[] row = (Object[])iterator.next();
				int largeEmergencyHumans = Integer.parseInt(row[0].toString());
				tBdcStatusFeedback.setLargeEmergencyHumans(largeEmergencyHumans);
				
				int largeEmergencyUnits = Integer.parseInt(row[1].toString());
				tBdcStatusFeedback.setLargeEmergencyUnits(largeEmergencyUnits);
				
				int midEmergencyHumans = Integer.parseInt(row[2].toString());
				tBdcStatusFeedback.setMidEmergencyHumans(midEmergencyHumans);

				int midEmergencyUnits = Integer.parseInt(row[3].toString());
				tBdcStatusFeedback.setMidEmergencyUnits(midEmergencyUnits);

				int smallEmergencyHumans = Integer.parseInt(row[4].toString());
				tBdcStatusFeedback.setSmallEmergencyHumans(smallEmergencyHumans);

				int smallEmergencyUnits = Integer.parseInt(row[5].toString());
				tBdcStatusFeedback.setSmallEmergencyUnits(smallEmergencyUnits);

				int vehiclePatrolsHumans = Integer.parseInt(row[6].toString());
				tBdcStatusFeedback.setVehiclePatrolsHumans(vehiclePatrolsHumans);

				int vehiclePatrolsUnits = Integer.parseInt(row[7].toString());
				tBdcStatusFeedback.setVehiclePatrolsUnits(vehiclePatrolsUnits);

				int salvageHumans = Integer.parseInt(row[8].toString());
				tBdcStatusFeedback.setSalvageHumans(salvageHumans);

				int salvageUnits = Integer.parseInt(row[9].toString());
				tBdcStatusFeedback.setSalvageUnits(salvageUnits);

				int repairemergencyHumans = Integer.parseInt(row[10].toString());
				tBdcStatusFeedback.setRepairemergencyHumans(repairemergencyHumans);

				int repairemergencyUnits = Integer.parseInt(row[11].toString());
				tBdcStatusFeedback.setRepairemergencyUnits(repairemergencyUnits);

				int pumpHumans = Integer.parseInt(row[12].toString());
				tBdcStatusFeedback.setPumpHumans(pumpHumans);

				int commandPost = Integer.parseInt(row[13].toString());
				tBdcStatusFeedback.setCommandPost(commandPost);

				int allHumans = Integer.parseInt(row[14].toString());
				tBdcStatusFeedback.setAllHumans(allHumans);
				
				tBdcStatusFeedback.setStatus(1);
				
				tBdcStatusFeedback.setStatusFeedbackId(-1);
			}
		}
		
		return Optional.of(tBdcStatusFeedback);
	}
}
