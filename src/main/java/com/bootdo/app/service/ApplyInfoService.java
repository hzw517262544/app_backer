package com.bootdo.app.service;

import com.bootdo.app.domain.ApplyInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-04 17:17:32
 */
public interface ApplyInfoService {
	
	ApplyInfoDO get(Long id);
	
	List<ApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ApplyInfoDO applyInfo);
	
	int update(ApplyInfoDO applyInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
