package com.mpe.basic.model.other;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserActivityCalendar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long userId;
	String userName;
	String fullName;
	String organizationOrCustomer;

	List<UserActivityCalendarDetail> userActivityCalendarDetails = new LinkedList<UserActivityCalendarDetail>();

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<UserActivityCalendarDetail> getUserActivityCalendarDetails() {
		return userActivityCalendarDetails;
	}

	public void setUserActivityCalendarDetails(
			List<UserActivityCalendarDetail> userActivityCalendarDetails) {
		this.userActivityCalendarDetails = userActivityCalendarDetails;
	}

	public String getOrganizationOrCustomer() {
		return organizationOrCustomer;
	}

	public void setOrganizationOrCustomer(String organizationOrCustomer) {
		this.organizationOrCustomer = organizationOrCustomer;
	}
	
	public int getTotal() {
		int i = 0;
		for (UserActivityCalendarDetail detail : userActivityCalendarDetails) {
			if (detail.getActivityCount()>0) {
				i = i + 1;
			}
		}
		return i;
	}

}
