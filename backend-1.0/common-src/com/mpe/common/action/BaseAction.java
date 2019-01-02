package com.mpe.common.action;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.backend.model.Users;
import com.mpe.common.util.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Agung Hadiwaluyo
 *
 */
public abstract class BaseAction extends ActionSupport implements SessionAware, Serializable {

	private static final long serialVersionUID = 1L;
	
	//public Log logger = LogFactory.getFactory().getInstance(this.getClass());
	public Logger logger = Logger.getLogger(this.getClass());
	
	int start=0, count=10;
	String previousPage, ascDesc="desc", orderBy="", pager="", pagerItem="", search="", subaction="";
	private boolean readOnly=false;
	private String mappedRequest;
	Map<String, Object> session;
	
	String createBy;
	String changeBy;
	
	public boolean isPostRequest() {
        HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(req!=null && req.getMethod().equalsIgnoreCase("post")) return true;
        return false;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getChangeBy() {
		return changeBy;
	}

	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
	}
	
	

	/*public String getPreviousPage() {
		try{
			this.createBy = ((User)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try{
			this.changeBy = ((User)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try {
			this.previousPage = ActionContext.getContext().getActionInvocation().getProxy().getMethod();
			logger.info(" [ previousPage = "+this.previousPage+" ] ");
			return this.previousPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
		try{
			this.createBy = ((User)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try{
			this.changeBy = ((User)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
	}*/
	
	/*
	 * list action for list data
	 */
	@SkipValidation
	public abstract String list();
	
	/*
	 * paging list action for partial data
	 */
	@SkipValidation
	public abstract String partialList();
	
	/*
	 * add action for add data form
	 */
	@SkipValidation
	public abstract String add();
	
	/*
	 * save action for save data into database
	 */
	public abstract String save();
	
	/*
	 * edit action for edit data form
	 */
	@SkipValidation
	public abstract String edit();
	
	/*
	 * update action for update data into database
	 */
	public abstract String update();
	
	/*
	 * show readonly data
	 */
	@SkipValidation
	public abstract String detail();
	
	/*
	 * delete action for delete data from database
	 */
	@SkipValidation
	public abstract String delete();
	
	/*
	 * cancel action for cancel current activity
	 */
	@SkipValidation
	public abstract String cancel();
	
	
	public String getActionClass() {
		return getClass().getSimpleName();
	}
	
	public String getDestination() {
		return getClass().getSimpleName();
	}
		
	public String getActionMethod() {
		return mappedRequest!=null?(mappedRequest.indexOf(".action")>-1?(mappedRequest.substring(0, mappedRequest.indexOf(".action"))):mappedRequest):mappedRequest;
	}
		
	// when invalid, the request parameter will restore command action
	public void setActionMethod(String method) {
		this.mappedRequest = method;
	}
		
	// this prepares command for button on initial screen write
	public void setMappedRequest(String actionMethod) {
		this.mappedRequest = getActionClass() + "_" + actionMethod+".action";
		try{
			this.createBy = ((Users)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try{
			this.changeBy = ((Users)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try {
			this.previousPage = ActionContext.getContext().getActionInvocation().getProxy().getMethod();
			//logger.info(" [ previousPage = "+this.previousPage+" ] ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setMappedRequest(String actionMethod, String param) {
		this.mappedRequest = getActionClass() + "_" + actionMethod+".action"+param;
		try{
			this.createBy = ((Users)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try{
			this.changeBy = ((Users)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try {
			this.previousPage = ActionContext.getContext().getActionInvocation().getProxy().getMethod();
			//logger.info(" [ previousPage = "+this.previousPage+" ] ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setMappedRequestFullPath(String actionMethod) {
		this.mappedRequest = actionMethod;
		try{
			this.createBy = ((Users)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try{
			this.changeBy = ((Users)getSession().get(Constants.USER)).getUserName();
		} catch (Exception e) {}
		try {
			this.previousPage = ActionContext.getContext().getActionInvocation().getProxy().getMethod();
			//logger.info(" [ previousPage = "+this.previousPage+" ] ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public String getMappedRequest() {
		return mappedRequest;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	public boolean isReadOnly() {
		return readOnly;
	}
	
	public int getCount() {
		return count;
	}

	public int getStart() {
		return start;
	}

	public void setCount(int count) {
		this.count = count;
		
	}

	public void setStart(int start) {
		this.start = start;		
	}

	public String getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(String ascDesc) {
		this.ascDesc = ascDesc;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getPagerItem() {
		return pagerItem;
	}

	public void setPagerItem(String pagerItem) {
		this.pagerItem = pagerItem;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String getSubaction() {
		return subaction;
	}

	public void setSubaction(String subaction) {
		this.subaction = subaction;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}
	
	

}
