package com.bootdo.app.dao;

import com.bootdo.app.domain.FlowDocDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 流程流转记录表
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-16 10:56:15
 */
@Mapper
public interface FlowDocDao {

	FlowDocDO get(Long id);
	
	List<FlowDocDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FlowDocDO flowDoc);
	
	int update(FlowDocDO flowDoc);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
