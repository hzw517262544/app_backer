package com.bootdo.app.domain;

import com.bootdo.activiti.vo.TaskVO;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


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

	private String applyStatusName;
	//创建时间
	private Date createTime;
	//创建人
	private String createUser;
	//更新时间
	private Date updateTime;
	//更新人
	private String updateUser;

	private TaskVO taskVO;
	//流转记录
	private List<FlowDocDO> flowDocs;

	private String taskId;
	//素材
	private List<TopicDO> topicDOS;
	//当前处理人
	private String currentHandlerUserName;
	//当前处理人
	private String currentHandlerName;
	//标题
	private String applyTitle;
	//编号
	private String applyNo;

	private String sendPlatformName;
	private String sendPlatform;
	private String sendGradeName;
	private String sendGrade;
	private String dutyEditorOpinion;
	private String presidentEditorOpinion;

	private String sendTime;
	private String publishTime;
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

	public String getApplyStatusName() {
		return applyStatusName;
	}

	public void setApplyStatusName(String applyStatusName) {
		this.applyStatusName = applyStatusName;
	}

	public List<FlowDocDO> getFlowDocs() {
		return flowDocs;
	}

	public void setFlowDocs(List<FlowDocDO> flowDocs) {
		this.flowDocs = flowDocs;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<TopicDO> getTopicDOS() {
		return topicDOS;
	}

	public void setTopicDOS(List<TopicDO> topicDOS) {
		this.topicDOS = topicDOS;
	}

	public String getCurrentHandlerUserName() {
		return currentHandlerUserName;
	}

	public void setCurrentHandlerUserName(String currentHandlerUserName) {
		this.currentHandlerUserName = currentHandlerUserName;
	}

	public String getCurrentHandlerName() {
		return currentHandlerName;
	}

	public void setCurrentHandlerName(String currentHandlerName) {
		this.currentHandlerName = currentHandlerName;
	}

	public String getApplyTitle() {
		return applyTitle;
	}

	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getSendPlatformName() {
		return sendPlatformName;
	}

	public void setSendPlatformName(String sendPlatformName) {
		this.sendPlatformName = sendPlatformName;
	}

	public String getSendPlatform() {
		return sendPlatform;
	}

	public void setSendPlatform(String sendPlatform) {
		this.sendPlatform = sendPlatform;
	}

	public String getSendGradeName() {
		return sendGradeName;
	}

	public void setSendGradeName(String sendGradeName) {
		this.sendGradeName = sendGradeName;
	}

	public String getSendGrade() {
		return sendGrade;
	}

	public void setSendGrade(String sendGrade) {
		this.sendGrade = sendGrade;
	}

	public String getDutyEditorOpinion() {
		return dutyEditorOpinion;
	}

	public void setDutyEditorOpinion(String dutyEditorOpinion) {
		this.dutyEditorOpinion = dutyEditorOpinion;
	}

	public String getPresidentEditorOpinion() {
		return presidentEditorOpinion;
	}

	public void setPresidentEditorOpinion(String presidentEditorOpinion) {
		this.presidentEditorOpinion = presidentEditorOpinion;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
}
