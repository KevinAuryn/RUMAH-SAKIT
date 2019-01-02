package com.mpe.basic.action;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Permission;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class PermissionAction extends BaseAction implements Preparable, ModelDriven<Permission> {

	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	Permission permission;
	List<Permission> permissionList;
	List<Permission> parentList;
	long permissionId;
	String permissionNameX="",linkX="";
	Long parentId;
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="permissionName",trim=true,key="validation.name.required")
			}
	)
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String add() {
		return Constants.SUCCESS;
	}

	@Override
	public String delete() {
		try {
			basicDAOFactory.getPermissionDAO().delete(permissionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String detail() {
		setReadOnly(true);
		setMappedRequest(Constants.LIST);
		return Constants.SUCCESS;
	}

	@Override
	public String edit() {
		setPreviousPage(Constants.EDIT);
		setMappedRequest(Constants.UPDATE);
		return Constants.SUCCESS;
	}

	@Override
	public Permission getModel() {
		return permission;
	}

	@Override
	public String list() {
		try {
			permissionList = basicDAOFactory.getPermissionDAO().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.LIST);
		return Constants.SUCCESS;
	}

	@Override
	public String partialList() {
		try {
			PartialList<Permission> partialList = basicDAOFactory.getPermissionDAO().findByCriteria(getStart(), getCount(), getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"permissionId"):Order.desc(getOrderBy().length()>0?getOrderBy():"permissionId"), 
					permissionNameX.length()>0?Restrictions.ilike("permissionName", permissionNameX, MatchMode.ANYWHERE):Restrictions.ne("permissionId", new Long(-1)), 
					linkX.length()>0?Restrictions.ilike("link", linkX, MatchMode.ANYWHERE):Restrictions.ne("permissionId", new Long(-1)));
			permissionList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.SUCCESS;
	}
	
	public String ajaxPartialList() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.SUCCESS;
	}

	@Override
	public String save() {
		try {
			basicDAOFactory.getPermissionDAO().save(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String update() {
		try {
			basicDAOFactory.getPermissionDAO().update(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public void prepare() throws Exception {
		if (parentList==null) parentList = basicDAOFactory.getPermissionDAO().findAll(Order.asc("permissionId")); 
		if (permissionId==0) {
			permission = new Permission();
			parentId = permission.getParent()!=null?permission.getParent().getPermissionId():null;
		} else {
			permission = basicDAOFactory.getPermissionDAO().findById(permissionId);
			parentId = permission.getParent()!=null?permission.getParent().getPermissionId():null;
		}
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<Permission> getParentList() {
		return parentList;
	}

	public void setParentList(List<Permission> parentList) {
		this.parentList = parentList;
	}

	public String getPermissionNameX() {
		return permissionNameX;
	}

	public void setPermissionNameX(String permissionNameX) {
		this.permissionNameX = permissionNameX;
	}

	public String getLinkX() {
		return linkX;
	}

	public void setLinkX(String linkX) {
		this.linkX = linkX;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	
	

}
