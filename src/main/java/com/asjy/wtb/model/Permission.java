package com.asjy.wtb.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Permission {
	private int id;
	private String permissionName;
	private String permissionConText;
	private int parentId;
	private String addTime;
	private String permissionUrl;
	private List<Permission> minorPermissinoList;

	public String getPermissionUrl() {
		return permissionUrl;
	}
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionConText() {
		return permissionConText;
	}
	public void setPermissionConText(String permissionConText) {
		this.permissionConText = permissionConText;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public List<Permission> getMinorPermissinoList() {
		return minorPermissinoList;
	}
	public void setMinorPermissinoList(List<Permission> minorPermissinoList) {
		this.minorPermissinoList = minorPermissinoList;
	}
	
	
}
