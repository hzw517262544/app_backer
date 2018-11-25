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
	
	ApplyInfoDO get(String id);
	
	List<ApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	String save(ApplyInfoDO applyInfo);
	
	int update(ApplyInfoDO applyInfo);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	String commit(ApplyInfoDO applyInfo);

	void backCommit(ApplyInfoDO applyInfo);

    void cancel(ApplyInfoDO applyInfo);

	List<ApplyInfoDO> listApproved(Map<String, Object> map);
}
