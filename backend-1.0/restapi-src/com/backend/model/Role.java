package com.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ListIndexBase;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.mpe.basic.model.Permission;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity
@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
@Table(name="role",
	uniqueConstraints={@UniqueConstraint(columnNames={"role_name"})}
)
@BatchSize(size=100)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

public class Role implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="role_seq",sequenceName="role_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_seq")
	@Column(name="role_id",nullable=false)
	long roleId;
	
	@NotBlank(message="Role name can't blank")
	@Column(length=128,nullable=false,name="role_name")
	String roleName;
	
	@NotAudited
	@NotEmpty(message="Permission can't empty")
	@Size(min=2,message="Select min home and logoff")
	@ManyToMany(fetch=FetchType.LAZY)
	//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
	@JoinTable(
		name = "role_permission",
		joinColumns = {@JoinColumn(name = "role_id")},
		inverseJoinColumns = {@JoinColumn(name = "permission_id")},
		uniqueConstraints={@UniqueConstraint(columnNames={"role_id","permission_id"})}
	)
	@ListIndexBase(value=0)
	@OrderColumn(name="permission_seq")
	//@Fetch(FetchMode.SELECT)
	@BatchSize(size=100)
	List<Permission> permissions = new LinkedList<Permission>();
	
	@Column(length=128,name="create_by",insertable=true,updatable=false)
	String createBy;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_on",insertable=true,updatable=false)
    Date createOn;
    
    @Column(length=128,name="change_by",insertable=false,updatable=true)
    String changeBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="change_on",insertable=false,updatable=true)
    Date changeOn;

	public Role() {
		super();
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public String getChangeBy() {
		return changeBy;
	}

	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
	}

	public Date getChangeOn() {
		return changeOn;
	}

	public void setChangeOn(Date changeOn) {
		this.changeOn = changeOn;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
   
}
