package com.bootdo.common.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.dao.FileDao;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class FileServiceImpl implements FileService {
	@Resource
	private FileDao sysFileMapper;
	@Resource
	private BootdoConfig bootdoConfig;
	@Resource
	private UserService userService;
	@Override
	public FileDO get(Long id){
		return sysFileMapper.get(id);
	}
	
	@Override
	public List<FileDO> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(FileDO sysFile){
		return sysFileMapper.save(sysFile);
	}
	
	@Override
	public int update(FileDO sysFile){
		return sysFileMapper.update(sysFile);
	}
	
	@Override
	public int remove(Long id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}

    @Override
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = bootdoConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public R saveBase64(Long userId, String imgData, Long sourceType,String filePath) {
		imgData = imgData.split(",")[1];
		if (imgData == null) //图像数据为空
		{
			return R.error();
		}
		BASE64Decoder decoder = new BASE64Decoder();
		FileDO sysFile = null;
		try
		{
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgData);
			for(int i=0;i<b.length;++i)
			{
				if(b[i]<0)
				{//调整异常数据
					b[i]+=256;
				}
			}
			UserDO userDO = userService.get(userId);
			String fileName = userDO.getUsername()+".png";
			fileName = FileUtil.renameToUUID(fileName);
			sysFile = new FileDO(FileType.fileType(fileName), "/files/"+filePath + fileName, new Date());
			if(userDO != null){
				sysFile.setSourceId(userDO.getUserId());
			}
			sysFile.setSourceType(sourceType);
			FileUtil.uploadFile(b, bootdoConfig.getUploadPath()+filePath, fileName);
			if(save(sysFile) > 0){
				userDO.setPicId(sysFile.getId());
				userService.update(userDO);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return R.error();
		}
		return R.ok().put("file",sysFile);
	}
}
