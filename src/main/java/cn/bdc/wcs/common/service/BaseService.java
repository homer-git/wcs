package cn.bdc.wcs.common.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class BaseService {

	
	@PersistenceContext
	protected EntityManager em;
	
}
