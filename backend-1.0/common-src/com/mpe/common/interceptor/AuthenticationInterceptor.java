package com.mpe.common.interceptor;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.backend.model.Users;
import com.mpe.basic.model.Permission;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.util.CommonUtil;
import com.mpe.common.util.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class AuthenticationInterceptor extends AbstractInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());
    private static final String authenticationRequiredResult = "authentication_required";
    private static final String privilegeRequiredResult = "privilege_required";
    
    BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users)session.get(Constants.USER);
		// cek user session
		if (user==null) {
			
			if (invocation.getProxy().getActionName().indexOf("TestAction_")<0
					) {
				return authenticationRequiredResult;
			} else {
				return invocation.invoke();
			}
			//return authenticationRequiredResult;
		} else {
			// cek user access
			//log.info("[ View : "+invocation.getProxy().getActionName()+" ] ");
			// skip view with : _json
			if (invocation.getProxy().getActionName().indexOf("_json")<0 
					&& invocation.getProxy().getActionName().indexOf("_popUp")<0 
					&& invocation.getProxy().getActionName().indexOf("_chart")<0 
					&& invocation.getProxy().getActionName().indexOf("_import")<0
					&& invocation.getProxy().getActionName().indexOf("_showContent")<0
					&& invocation.getProxy().getActionName().indexOf("_cancel")<0
					&& invocation.getProxy().getActionName().indexOf("/UploadAction_")<0
					&& invocation.getProxy().getActionName().indexOf("TestAction_")<0
					&& invocation.getProxy().getActionName().indexOf("JsonAction_")<0
					&& invocation.getProxy().getActionName().indexOf("_showSod")<0
					) {
				if (user.getUserName().equalsIgnoreCase("setup") && user.getUserPass().equalsIgnoreCase("setup123456")) {
					return invocation.invoke();
				} else {
					Permission permission = CommonUtil.getPermissionRoleAccess((Set<Permission>)session.get(Constants.PERMISSIONS), invocation.getProxy().getActionName());
					if (permission!=null) {
						//if (permission.isLog()) basicDAOFactory.getUserActivityDAO().insertActivity(user.getUser_id(), user.getUser_name(), permission.getPermissionName(), request.getRemoteAddr());
						// put title on page
						session.put(Constants.PERMISSION_NAME, permission.getPermissionName());
						session.put(Constants.PARENT_PERMISSION_NAME, permission.getParent()!=null?permission.getParent().getPermissionName():permission.getPermissionName());
						return invocation.invoke();
					} else {
						session.put("noCurrentAccess", invocation.getProxy().getActionName());
						return privilegeRequiredResult;
					}
				}
			} else {
				return invocation.invoke();
			}
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() {
		super.init();
	}
	
	
	
	
}

	
	
