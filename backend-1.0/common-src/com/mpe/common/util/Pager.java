package com.mpe.common.util;

import java.util.ResourceBundle;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class Pager {
	static long totalpage,currentpage;
	private static long maxpagepersection=10;
	private static int maxitemperpage=10;
	
	static {
		//Locale locale = new Locale("en");
		try {
			ResourceBundle prop = ResourceBundle.getBundle("globalMessages");
			try {
				maxpagepersection = Long.parseLong(prop.getString("max.page.per.section"));
			} catch (Exception ex) {}
			try {
				maxitemperpage = Integer.parseInt(prop.getString("max.item.per.page"));
			} catch (Exception ex) {}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Returns the pager.
	 * @return String
	 */
	public static String generatePager(int start, int count, long total) {
		if (count > 0) maxitemperpage = count;
		total = total - 1;
		if (total < 0) total = 0;
		String pager = "";
		totalpage = (total+maxitemperpage)/maxitemperpage;
		currentpage = (start+maxitemperpage)/maxitemperpage;
		pager = "Page <b>"+currentpage+"</b> of "+totalpage+" pages.";
		return pager;
	}
	
	/** 
	 * Returns the pagerItem.
	 * @return String
	 */
	public static String generatePagerItem(int start, int count, long total) {
		if (count > 0) maxitemperpage = count;
		total = total - 1;
		if (total < 0) total = 0;
		int prev,next;
		String pagerItem = "";
		totalpage = (total+maxitemperpage)/maxitemperpage;
		currentpage = (start+maxitemperpage)/maxitemperpage;
		if (start == 0) prev = 0;
		else prev = start - maxitemperpage;
		next = start + maxitemperpage;
		if (start >= maxitemperpage) {
			pagerItem = "<a style='color:#000000' href=javascript:page("+0+","+maxitemperpage+")>first</a>&nbsp;|&nbsp;<a style='color:#000000' href=javascript:page("+prev+","+maxitemperpage+")>prev</a>";
		} else {
			pagerItem = "first&nbsp;|&nbsp;prev";
		}
		pagerItem = pagerItem + "&nbsp;|&nbsp;";
		long z;
		long sectionstart = currentpage - maxpagepersection/2;
		if (sectionstart+maxpagepersection-1 > totalpage) sectionstart = sectionstart - (sectionstart+maxpagepersection-1-totalpage);		      														      				     					
		if (sectionstart < 1) sectionstart = 1;
		for (z=sectionstart; (z<sectionstart+maxpagepersection) && (z<=totalpage); z++) {
			if (z == currentpage) {
				pagerItem = pagerItem + "<b>" + currentpage + "</b>";
			} else {
				pagerItem = pagerItem + "<a style='color:#000000' href=javascript:page("+(z-1)*maxitemperpage+","+maxitemperpage+")>"+z+"</a>";
			}
			pagerItem = pagerItem + "&nbsp;";
		}
		pagerItem = pagerItem + "|&nbsp;";
		if (currentpage < totalpage) {
			pagerItem = pagerItem + "<a style='color:#000000' href=javascript:page("+next+","+maxitemperpage+")>next</a>&nbsp;|&nbsp;<a style='color:#000000' href=javascript:page("+((totalpage-1)*maxitemperpage)+","+maxitemperpage+")>last</a>";
		} else {
			pagerItem = pagerItem + "next&nbsp;|&nbsp;last";
		}
		return pagerItem;
	}

}
