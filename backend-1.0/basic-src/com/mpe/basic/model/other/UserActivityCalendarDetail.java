package com.mpe.basic.model.other;

import java.io.Serializable;

public class UserActivityCalendarDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int date;
	int activityCount = 0;
	boolean holiday;
	
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getActivityCount() {
		return activityCount;
	}
	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}
	public boolean isHoliday() {
		return holiday;
	}
	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}
	
	

}
