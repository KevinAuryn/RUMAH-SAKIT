package com.mpe.common.interceptor;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.backend.model.Users;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.util.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class AuthenticationInterceptorWithoutView extends AbstractInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());
    private static final String authenticationRequiredResult = "authentication_required_without_view";
    //private static final String privilegeRequiredResult = "privilege_required";
    
    BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		//HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users)session.get(Constants.USER);
		// cek user session
		if (user==null) {
			return authenticationRequiredResult;
		} else {
			return invocation.invoke();
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

	
	
