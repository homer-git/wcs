package cn.bdc.weChatService.repository;


import org.springframework.data.repository.CrudRepository;

import cn.bdc.weChatService.bean.Cat;


public interface CatRepository extends CrudRepository<Cat, Integer> {

}
