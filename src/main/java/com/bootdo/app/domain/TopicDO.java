package com.bootdo.app.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-20 21:23:23
 */
public class TopicDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//申请信息id
	private String applyId;
	//题材类型
	private String topicType;
	//题材名称
	private String topicName;
	//题材url
	private String topicUrl;
	//创建人
	private String createUser;
	//创建时间
	private Date createTime;
	//更新人
	private String updateUser;
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
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：申请信息id
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：申请信息id
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置：题材类型
	 */
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	/**
	 * 获取：题材类型
	 */
	public String getTopicType() {
		return topicType;
	}
	/**
	 * 设置：题材名称
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * 获取：题材名称
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * 设置：题材url
	 */
	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}
	/**
	 * 获取：题材url
	 */
	public String getTopicUrl() {
		return topicUrl;
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
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateUser() {
		return updateUser;
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
