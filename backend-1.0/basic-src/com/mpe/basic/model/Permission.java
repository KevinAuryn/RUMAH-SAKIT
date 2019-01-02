package com.mpe.basic.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Entity implementation class for Entity: Permission
 *
 */
@Entity
@Table(name="permission",
	//uniqueConstraints={@UniqueConstraint(columnNames={"permission_name","parent_id"})},
	indexes={@Index(columnList="permission_name,link,parent_id", unique=true)}
)
/*@org.hibernate.annotations.Table(
		appliesTo="permissions",
		indexes={@Index(name="IDX_VIEW",columnNames={"permission_name","link","parent_id"})}
)*/
@BatchSize(size=100)
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="permission_id",nullable=false)
	long permissionId;
	
	@NotBlank(message="Permission name can't blank")
	@Column(length=255,name="permission_name",nullable=false)
	String permissionName;
	
	@Column(length=255,name="link")
	String link;
	
	@Column(name="icon_name",length=50)
	String iconName;
	
	@Column(length=1,name="is_show",nullable=false)
	@Type(type="true_false")
	boolean show = false;
	
	@Column(length=1,name="is_workflow")
	@Type(type="true_false")
	boolean workflow = false;
	
	@Column(length=1,name="is_log")
	@Type(type="true_false")
	boolean log = false;
	
	@ManyToOne
	@JoinColumn(name="parent_id",nullable=true)
	@Fetch(FetchMode.JOIN)
	Permission parent;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=100)
	Set<Permission> childs = new LinkedHashSet<Permission>();
	
	@Transient
	int length;
	
	@Transient
	int childSum;
	
	@Transient
	Set<Permission> permissionChilds = new LinkedHashSet<Permission>();

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}


	public int getChildSum() {
		return childSum;
	}

	public void setChildSum(int childSum) {
		this.childSum = childSum;
	}

	public Permission() {
		super();
	}

	public String getParentPermissionName() {
		if (getParent()!=null) {
			if (getParent().getParent()!=null) return getParent().getParent().getPermissionName() + " :: " + getParent().getPermissionName()+" :: " + getPermissionName();
			else return getParent().getPermissionName()+" :: " + getPermissionName();
		} else return getPermissionName();
	}
	
	public int getSumOfChild() {
		return getChilds()!=null?getChilds().size():0;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isWorkflow() {
		return workflow;
	}

	public void setWorkflow(boolean workflow) {
		this.workflow = workflow;
	}

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	public Set<Permission> getChilds() {
		return childs;
	}

	public void setChilds(Set<Permission> childs) {
		this.childs = childs;
	}

	public Set<Permission> getPermissionChilds() {
		return permissionChilds;
	}

	public void setPermissionChilds(Set<Permission> permissionChilds) {
		this.permissionChilds = permissionChilds;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	
	
   
}
