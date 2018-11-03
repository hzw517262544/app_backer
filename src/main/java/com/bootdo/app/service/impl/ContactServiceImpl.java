package com.bootdo.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.app.dao.ContactDao;
import com.bootdo.app.domain.ContactDO;
import com.bootdo.app.service.ContactService;



@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDao contactDao;
	
	@Override
	public ContactDO get(Long id){
		return contactDao.get(id);
	}
	
	@Override
	public List<ContactDO> list(Map<String, Object> map){
		return contactDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contactDao.count(map);
	}
	
	@Override
	public int save(ContactDO contact){
		return contactDao.save(contact);
	}
	
	@Override
	public int update(ContactDO contact){
		return contactDao.update(contact);
	}
	
	@Override
	public int remove(Long id){
		return contactDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return contactDao.batchRemove(ids);
	}
	
}
