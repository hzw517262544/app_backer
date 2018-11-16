package com.bootdo.app.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 流程流转记录表
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-16 10:56:15
 */
public class FlowDocDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//业务id
	private String businessId;
	//业务类型：1-请假申请
	private String businessType;
	//操作做id
	private String hdlActionId;
	//操作动作名称
	private String hdlAction;
	//操作意见
	private String hdlContent;
	//创建人id
	private String createUserId;
	//创建人name
	private String createUserName;
	//创建时间
	private Date createTime;
	//更新人id
	private String updateUserId;
	//更新人name
	private String updateUserName;
	//更新时间
	private Date updateTime;
	//
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
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：业务id
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 * 获取：业务id
	 */
	public String getBusinessId() {
		return businessId;
	}
	/**
	 * 设置：业务类型：1-请假申请
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * 获取：业务类型：1-请假申请
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * 设置：操作做id
	 */
	public void setHdlActionId(String hdlActionId) {
		this.hdlActionId = hdlActionId;
	}
	/**
	 * 获取：操作做id
	 */
	public String getHdlActionId() {
		return hdlActionId;
	}
	/**
	 * 设置：操作动作名称
	 */
	public void setHdlAction(String hdlAction) {
		this.hdlAction = hdlAction;
	}
	/**
	 * 获取：操作动作名称
	 */
	public String getHdlAction() {
		return hdlAction;
	}
	/**
	 * 设置：操作意见
	 */
	public void setHdlContent(String hdlContent) {
		this.hdlContent = hdlContent;
	}
	/**
	 * 获取：操作意见
	 */
	public String getHdlContent() {
		return hdlContent;
	}
	/**
	 * 设置：创建人id
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人id
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建人name
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * 获取：创建人name
	 */
	public String getCreateUserName() {
		return createUserName;
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
	 * 设置：更新人id
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取：更新人id
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}
	/**
	 * 设置：更新人name
	 */
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	/**
	 * 获取：更新人name
	 */
	public String getUpdateUserName() {
		return updateUserName;
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
	 * 设置：
	 */
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	/**
	 * 获取：
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
