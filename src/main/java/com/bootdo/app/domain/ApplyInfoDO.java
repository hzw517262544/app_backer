package com.bootdo.app.domain;

import com.bootdo.activiti.vo.TaskVO;
import org.activiti.engine.task.Task;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-04 17:17:32
 */
public class ApplyInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//用户账号
	private String username;
	//用户姓名
	private String name;
	//申请类型
	private String applyType;
	//申请类型名称
	private String applyTypeName;
	//申请子类型
	private String applySecodType;
	//申请子类型名称
	private String applySecodTypeName;
	//申请开始时间
	private String applyStartTime;
	//申请结束时间
	private String applyEndTime;
	//申请内容
	private String applyContent;
	//申请状态
	private String applyStatus;
	//创建时间
	private Date createTime;
	//创建人
	private String createUser;
	//更新时间
	private Date updateTime;
	//更新人
	private String updateUser;

	private TaskVO taskVO;

	/**
	 * 设置：id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置：用户账号
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户账号
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：申请类型
	 */
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	/**
	 * 获取：申请类型
	 */
	public String getApplyType() {
		return applyType;
	}
	/**
	 * 设置：申请类型名称
	 */
	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}
	/**
	 * 获取：申请类型名称
	 */
	public String getApplyTypeName() {
		return applyTypeName;
	}
	/**
	 * 设置：申请子类型
	 */
	public void setApplySecodType(String applySecodType) {
		this.applySecodType = applySecodType;
	}
	/**
	 * 获取：申请子类型
	 */
	public String getApplySecodType() {
		return applySecodType;
	}
	/**
	 * 设置：申请子类型名称
	 */
	public void setApplySecodTypeName(String applySecodTypeName) {
		this.applySecodTypeName = applySecodTypeName;
	}
	/**
	 * 获取：申请子类型名称
	 */
	public String getApplySecodTypeName() {
		return applySecodTypeName;
	}
	/**
	 * 设置：申请开始时间
	 */
	public void setApplyStartTime(String applyStartTime) {
		this.applyStartTime = applyStartTime;
	}
	/**
	 * 获取：申请开始时间
	 */
	public String getApplyStartTime() {
		return applyStartTime;
	}
	/**
	 * 设置：申请结束时间
	 */
	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}
	/**
	 * 获取：申请结束时间
	 */
	public String getApplyEndTime() {
		return applyEndTime;
	}
	/**
	 * 设置：申请内容
	 */
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}
	/**
	 * 获取：申请内容
	 */
	public String getApplyContent() {
		return applyContent;
	}
	/**
	 * 设置：申请状态
	 */
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	/**
	 * 获取：申请状态
	 */
	public String getApplyStatus() {
		return applyStatus;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	public TaskVO getTaskVO() {
		return taskVO;
	}

	public void setTaskVO(TaskVO taskVO) {
		this.taskVO = taskVO;
	}
}
