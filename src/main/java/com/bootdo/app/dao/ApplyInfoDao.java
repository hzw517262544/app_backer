package com.bootdo.app.dao;

import com.bootdo.app.domain.ApplyInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-04 17:17:32
 */
@Mapper
public interface ApplyInfoDao {

	ApplyInfoDO get(String id);
	
	List<ApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ApplyInfoDO applyInfo);
	
	int update(ApplyInfoDO applyInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
