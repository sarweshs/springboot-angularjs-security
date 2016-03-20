package com.sarwesh.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

	@Id
	@GeneratedValue
	@Column(name = "roleid")
	private long id;
	
	@Column(name = "rolename")
	private String roleName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String rolename) {
		this.roleName = rolename;
	}
	
}
