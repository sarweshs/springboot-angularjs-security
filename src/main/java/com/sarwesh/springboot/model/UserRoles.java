package com.sarwesh.springboot.model;

//@Entity
//@Table(name = "user_roles")
public class UserRoles {
	/*@Id
	@GeneratedValue
	@Column(name = "user_role_id")
	private long id;*/
	
	//@Column(name = "userid")
	private long userId;
	
	//@Column(name = "roleid")
	private long roleId;

	/*public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}*/

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
