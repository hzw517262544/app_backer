package com.bootdo.app.service;

import com.bootdo.app.domain.ContactDO;

import java.util.List;
import java.util.Map;

/**
 * app联系人表
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-03 09:33:06
 */
public interface ContactService {
	
	ContactDO get(Long id);
	
	List<ContactDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContactDO contact);
	
	int update(ContactDO contact);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
