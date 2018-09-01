package entity;


public class Role {
	private int id;
	private String roleCode;
	private String roleName;
	private int createdBy;
	private java.sql.Date creationDate;
	private int modifyBy;
	private java.sql.Date modifyDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public java.sql.Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public java.sql.Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(java.sql.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
