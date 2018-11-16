package com.bootdo.app.service;

import com.bootdo.app.domain.FlowDocDO;

import java.util.List;
import java.util.Map;

/**
 * 流程流转记录表
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-16 10:56:15
 */
public interface FlowDocService {
	
	FlowDocDO get(Long id);
	
	List<FlowDocDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FlowDocDO flowDoc);
	
	int update(FlowDocDO flowDoc);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
