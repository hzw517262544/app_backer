package com.bootdo.app.dao;

import com.bootdo.app.domain.WorkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-14 15:10:56
 */
@Mapper
public interface WorkDao {

	WorkDO get(Long id);
	
	List<WorkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WorkDO work);
	
	int update(WorkDO work);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
