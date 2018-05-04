package cn.bdc.wcs.repository;


import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.Cat;


public interface CatRepository extends CrudRepository<Cat, Integer> {

}
