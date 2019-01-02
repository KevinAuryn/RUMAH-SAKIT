package com.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.mpe.basic.model.Permission;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
//@NamedQuery(name="user.findByUserName", query="select u from User u where u.userName like ?")
@Table(name="users",
	indexes=@Index(columnList="user_name",name="IDX_USERS",unique=false),
	uniqueConstraints={@UniqueConstraint(columnNames={"user_name"})}
)
@BatchSize(size=100)
/*@org.hibernate.annotations.Table(
	appliesTo="users", 
	indexes=@org.hibernate.annotations.Index(name="IDX_USERS",columnNames={"user_name","organization_id","email"})
)*/
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Logger logger = Logger.getLogger(this.getClass());
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="users_seq",sequenceName="users_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="users_seq")
	@Column(name="user_id",nullable=false)
	long userId;
	
	@NotNull(message="Username can't blank")
	//@Pattern(regexp="((?=.*\\d)(?=.*[0-9a-zA-Z])(?!.*[()+}{;=`~:\\|'?/><,]).{5,})",message="Username min 5 characters and numeric combination")
	@Column(length=128,name="user_name",nullable=false)
	String userName;
	
	@NotNull(message="Name can't blank")
	@Column(length=128,name="name",nullable=false)
	String name;
	
	@NotNull(message="Password can't blank")
	@Column(length=128,name="user_pass",nullable=false)
	String userPass;
	
    @NotEmpty(message="Role can't empty")
    @Size(min=1,message="Select min 1 role")
    @ManyToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
		name = "user_role",
		joinColumns = {@JoinColumn(name = "user_id")},
		inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	@BatchSize(size=100)
	@org.hibernate.annotations.OrderBy(clause="role_id asc")
	Set<Role> roles = new HashSet<Role>();
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login_date")
    Date lastLoginDate;
    
    @Type(type="true_false")
    @Column(name="is_totp",length=1)
    Boolean totp;
    
    @Column(name="secret_key",length=100)
    String secretKey;
    
    //@Column(name="business_unit_id")
    //Long businessUnitId;
    
    //@Column(name="duration_expired_user_pass",length=2)
    //int durationExpiredUserPass;
    
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

	public Users() {
		super();
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}
	
	/*public String getUserNameFullName() {
		return userName + ((fullName!=null && fullName.length()>0)?" - ":"") + fullName;
	}*/


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPass() {
		return userPass;
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

	public String getChangeBy() {
		return changeBy;
	}


	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
	}


	public Date getChangeOn() {
		return changeOn;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}


	public void setChangeOn(Date changeOn) {
		this.changeOn = changeOn;
	}


	

	public Date getLastLoginDate() {
		return lastLoginDate;
	}


	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public Set<Permission> getPermissionAccessByParentChild() {
		Set<Permission> set = new LinkedHashSet<Permission>();
		Set<Permission> level1Set = new LinkedHashSet<Permission>();
		Set<Permission> level2Set = new LinkedHashSet<Permission>();
		//Set<Permission> level3Set = new LinkedHashSet<Permission>();
		for (Role role : getRoles()) {
			for (Permission permission : role.getPermissions()) {
				
				//System.out.println(" permission >> "+permission.getPermissionName());
				
				// level #1
				if (permission.isShow() && permission.getParent()==null) level1Set.add(permission);
				// level #2
				else if (permission.isShow() && permission.getParent()!=null) level2Set.add(permission);
				else if (!permission.isShow()) set.add(permission);
				// level #3
				//else if (permission.isShow()) level3Set.add(permission);		
			}
		}
		for (Permission permission1 : level1Set) {
			// level 1
			for (Permission permission2 : level2Set) {
				//System.out.println(" permission2 >> "+permission2.getPermissionName());
				// level #2
				if (permission2.getParent()!=null && permission2.getParent().getPermissionId()==permission1.getPermissionId()) {
					for (Permission permission3 : level2Set) {
						// level #3
						if (permission3.getParent()!=null && permission3.getParent().getPermissionId()==permission2.getPermissionId()) {
							permission2.getPermissionChilds().add(permission3);
						}
					}
					permission1.getPermissionChilds().add(permission2);
				}
				
			}
			set.add(permission1);
		}
		return set;
	}


	public Boolean getTotp() {
		return totp;
	}


	public void setTotp(Boolean totp) {
		this.totp = totp;
	}


	public String getSecretKey() {
		return secretKey;
	}


	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
   
}
