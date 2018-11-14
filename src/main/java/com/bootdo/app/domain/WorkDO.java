package com.bootdo.app.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-14 15:10:56
 */
public class WorkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//用户id
	private Long userId;
	//姓名
	private String userName;
	//标题
	private String title;
	//任务内容
	private String taskContent;
	//汇报内容
	private String reportContent;
	//完成情况
	private String completeStatus;
	//自我评分
	private String selfRating;
	//领导评分
	private String leaderRating;
	//领导点评
	private String leaderComment;
	//创建人
	private String createUser;
	//创建时间
	private Date createTime;
	//更新人
	private String upadteUser;
	//更新时间
	private Date updateTime;
	//备用1
	private String standby1;
	//备用2
	private String standby2;
	//备用3
	private String standby3;
	//备用4
	private String standby4;
	//备用5
	private String standby5;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：任务内容
	 */
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	/**
	 * 获取：任务内容
	 */
	public String getTaskContent() {
		return taskContent;
	}
	/**
	 * 设置：汇报内容
	 */
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	/**
	 * 获取：汇报内容
	 */
	public String getReportContent() {
		return reportContent;
	}
	/**
	 * 设置：完成情况
	 */
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}
	/**
	 * 获取：完成情况
	 */
	public String getCompleteStatus() {
		return completeStatus;
	}
	/**
	 * 设置：自我评分
	 */
	public void setSelfRating(String selfRating) {
		this.selfRating = selfRating;
	}
	/**
	 * 获取：自我评分
	 */
	public String getSelfRating() {
		return selfRating;
	}
	/**
	 * 设置：领导评分
	 */
	public void setLeaderRating(String leaderRating) {
		this.leaderRating = leaderRating;
	}
	/**
	 * 获取：领导评分
	 */
	public String getLeaderRating() {
		return leaderRating;
	}
	/**
	 * 设置：领导点评
	 */
	public void setLeaderComment(String leaderComment) {
		this.leaderComment = leaderComment;
	}
	/**
	 * 获取：领导点评
	 */
	public String getLeaderComment() {
		return leaderComment;
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
	 * 设置：更新人
	 */
	public void setUpadteUser(String upadteUser) {
		this.upadteUser = upadteUser;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpadteUser() {
		return upadteUser;
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
	 * 设置：备用1
	 */
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	/**
	 * 获取：备用1
	 */
	public String getStandby1() {
		return standby1;
	}
	/**
	 * 设置：备用2
	 */
	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
	/**
	 * 获取：备用2
	 */
	public String getStandby2() {
		return standby2;
	}
	/**
	 * 设置：备用3
	 */
	public void setStandby3(String standby3) {
		this.standby3 = standby3;
	}
	/**
	 * 获取：备用3
	 */
	public String getStandby3() {
		return standby3;
	}
	/**
	 * 设置：备用4
	 */
	public void setStandby4(String standby4) {
		this.standby4 = standby4;
	}
	/**
	 * 获取：备用4
	 */
	public String getStandby4() {
		return standby4;
	}
	/**
	 * 设置：备用5
	 */
	public void setStandby5(String standby5) {
		this.standby5 = standby5;
	}
	/**
	 * 获取：备用5
	 */
	public String getStandby5() {
		return standby5;
	}
}
