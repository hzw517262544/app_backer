package com.bootdo.app.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * app联系人表
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-03 09:33:06
 */
public class ContactDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id
	private String userId;
	//电话_1
	private String mobile1;
	//备注
	private String remark;
	//创建时间
	private Date createDate;
	//
	private String createUser;
	//更新时间
	private Date updateDate;
	//更新人
	private String updateUser;
	//
	private String contactUserName;
	//电话_2
	private String mobile2;
	//电话_3
	private String mobile3;
	//电话_4
	private String mobile4;
	//电话_5
	private String mobile5;
	//邮箱
	private String email;

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
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：电话_1
	 */
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	/**
	 * 获取：电话_1
	 */
	public String getMobile1() {
		return mobile1;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
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
	 * 设置：
	 */
	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName;
	}
	/**
	 * 获取：
	 */
	public String getContactUserName() {
		return contactUserName;
	}
	/**
	 * 设置：电话_2
	 */
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	/**
	 * 获取：电话_2
	 */
	public String getMobile2() {
		return mobile2;
	}
	/**
	 * 设置：电话_3
	 */
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	/**
	 * 获取：电话_3
	 */
	public String getMobile3() {
		return mobile3;
	}
	/**
	 * 设置：电话_4
	 */
	public void setMobile4(String mobile4) {
		this.mobile4 = mobile4;
	}
	/**
	 * 获取：电话_4
	 */
	public String getMobile4() {
		return mobile4;
	}
	/**
	 * 设置：电话_5
	 */
	public void setMobile5(String mobile5) {
		this.mobile5 = mobile5;
	}
	/**
	 * 获取：电话_5
	 */
	public String getMobile5() {
		return mobile5;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
}
