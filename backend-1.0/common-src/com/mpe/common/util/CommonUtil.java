/*
 * Created on Jun 25, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mpe.common.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mpe.basic.model.*;


/**
 * @author Agung Hadiwaluyo
 *
 */
public class CommonUtil {
	public static final String SAMA_DENGAN = "";
	static Log log = LogFactory.getFactory().getInstance(CommonUtil.class);
	static String[] m = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	
	public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	/** 
	 * Method digest.
	 * @return String
	 */
	public static String digest(String plain) throws Exception {
		if (plain == null) return null;
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(plain.getBytes("UTF-16"));
			byte bs[] = sha.digest();
			StringBuffer res = new StringBuffer();
			for (int ix=0; ix<bs.length; ix++) {
				int i;
				byte b = bs[ix];
				if (b > 0) i = b; else i = 256 + b;
				int d = i / 16;
				if (d > 9) res.append((char) ('A' + d - 10)); else res.append((char) ('0' + d));
				d = i % 16;
				if (d > 9) res.append((char) ('A' + d - 10)); else res.append((char) ('0' + d));
			}
			return res.toString();
		} catch (Exception ex) {
			throw new Exception("Digest Exception",ex);
		}
	}
	
	public static String getMedia(byte[] b) throws Exception {
		StringBuffer out = new StringBuffer();
		out.append(new String(b, "ISO-8859-1"));
		return out.toString();
	}
	
	public static String randomin(int length, boolean num, boolean alpha, boolean upper) {
		String[] numeric = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String[] upperCase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		Random generator = new Random();
		StringBuffer result = new StringBuffer();
		
		int i = 0;
		while (i!=length) {
			if (num) {
				int idx = generator.nextInt(numeric.length);
				result.append(numeric[idx]);
				i++;
				if (i==length) break;
			}
			if (alpha) {
				int idx = generator.nextInt(alphabet.length);
				result.append(alphabet[idx]);
				i++;
				if (i==length) break;
			}
			if (upper) {
				int idx = generator.nextInt(upperCase.length);
				result.append(upperCase[idx]);
				i++;
				if (i==length) break;
			}
			//System.out.println(" i >>> "+i);
		}
		

		return result.toString();
	}
	
	public static String randomUUID(int length, boolean num, boolean alpha, boolean upper) {
		String[] numeric = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String[] upperCase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		List<String> list = new ArrayList<String>();
		if (num) list.addAll(Arrays.asList(numeric));
		if (alpha) list.addAll(Arrays.asList(alphabet));
		if (upper) list.addAll(Arrays.asList(upperCase));
		String[] string = list.toArray(new String[]{});
		
		Random generator = new Random();
		StringBuffer result = new StringBuffer();
		
		int i = 0;
		while (i!=length) {
			int idx = generator.nextInt(string.length);
			result.append(string[idx]);
			i++;
			if (i==length) break;
		}		

		return result.toString();
	}
	
	public static String htmlEntities(String value){
		String result = value;
		try{
			
			String[][] tagArray = new String[][]{{"<","&lt;"}, {">","&gt;"}, {"\\[b\\]","<b>"}, {"\\[li\\]","<li>"} ,{"\\[/b\\]","</b>"}, {"\\[i\\]","<i>"}, {"\\[/i\\]","</i>"}, {"\\[ul\\]","<ul>"}, {"\\[ul type=\"1\"\\]","<ul type=\"1\">"} ,  {"\\[ul type=\"a\"\\]","<ul type=\"a\">"} ,  {"\\[ul type=\"A\"\\]","<ul type=\"A\">"} ,{"\\[/ul\\]","</ul>"}, {"\\[center\\]","<center>"}, {"\\[/center\\]","</center>"}, {"\\[br\\]","<br>"}, {"\\[/br\\]","</br>"}, {"\\[u\\]","<u>"}, {"\\[/u\\]","</u>"}};
  			for (int i = 0; i < tagArray.length; i++) {
              result=result.replaceAll(tagArray[i][0]  , tagArray[i][1]);
            }

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}


	public static String htmlUser(String value){
		String result = value;
		try{
			
			String[][] tagArray = new String[][]{{"<","\\["}, {">","\\]"} ,{"</","\\[/"}};
  			for (int i = 0; i < tagArray.length; i++) {
              result=result.replaceAll(tagArray[i][0]  , tagArray[i][1]);
            }

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public static boolean isHasRoleAccess(Set<Permission> lst, String viewAccessPath) {
		if(lst !=null && viewAccessPath!=null) {
			for (Permission permission : lst) {
				if (permission.getLink()!=null && permission.getLink().length()>0) {
					// format maybe in /xxxxxxxxx.action OR /<modul_name>/xxxxxxxxx.action
					String string = (permission.getLink()).substring(1,(permission.getLink()).length());
					int i = string.indexOf("/");
					//log.info("URL : "+string+ " // "+viewAccessPath);
					if (i==-1) {
						string = string.substring(0, string.indexOf(".action"));
					} else {
						string = string.substring(string.indexOf("/")+1, string.indexOf(".action"));
					}
					try {
						//log.info("URL : "+string+ " // "+viewAccessPath);
						if (string.equals(viewAccessPath)) return true;
					} catch(Exception exception) {
					}
				}
			}
		}
		return false;		
	}
	
	/*public static View getViewRoleAccess(Set<View> lst, String viewAccessPath) {
		View viewSelected = null;
		if(lst !=null && viewAccessPath!=null) {
			for (View view : lst) {
				if (view.getLink()!=null && view.getLink().length()>0) {
					// format maybe in /xxxxxxxxx.action OR /<modul_name>/xxxxxxxxx.action
					String string = (view.getLink()).substring(1,(view.getLink()).length());
					int i = string.indexOf("/");
					//log.info("URL : "+string+ " // "+viewAccessPath);
					if (i==-1) {
						string = string.substring(0, string.indexOf(".action"));
					} else {
						string = string.substring(string.indexOf("/")+1, string.indexOf(".action"));
					}
					if (viewAccessPath.indexOf(".action")>-1) {
						viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
					}
					try {
						//log.info("URL : "+string+ " // "+viewAccessPath);
						if (string.equals(viewAccessPath)) viewSelected = view;
					} catch(Exception exception) {
					}
				}
			}
		}
		return viewSelected;		
	}*/
	
	public static Permission getPermissionRoleAccess(Set<Permission> lst, String permissionAccessPath) {
		Permission permissionSelected = null;
		if(lst !=null && permissionAccessPath!=null) {
			String s = "";
			int i = 0;
			if (permissionAccessPath.indexOf(".action")>-1) permissionAccessPath = permissionAccessPath.substring(0, permissionAccessPath.indexOf(".action"));
			// format maybe in /<path>.action OR /<modul_name>/<path>.action
			for (Permission permission : lst) {
				if (permission.getLink()!=null && permission.getLink().length()>0) {
					s = (permission.getLink()).substring(1,(permission.getLink()).length());
					i = s.indexOf("/");
					if (i==-1) {
						s = s.substring(0, s.indexOf(".action"));
					} else {
						s = s.substring(s.indexOf("/")+1, s.indexOf(".action"));
					}
					//if (permissionAccessPath.indexOf(".action")>-1) permissionAccessPath = permissionAccessPath.substring(0, permissionAccessPath.indexOf(".action"));
					//log.info("URL1 : "+s+ " // "+permissionAccessPath);
					if (s.equals(permissionAccessPath)) {
						permissionSelected = permission;
						break; 
					}
				}
				if (permissionSelected==null) {
					for (Permission permission2 : permission.getPermissionChilds()) {
						if (permission2.getLink()!=null && permission2.getLink().length()>0) {
							s = (permission2.getLink()).substring(1,(permission2.getLink()).length());
							i = s.indexOf("/");
							if (i==-1) {
								s = s.substring(0, s.indexOf(".action"));
							} else {
								s = s.substring(s.indexOf("/")+1, s.indexOf(".action"));
							}
							//if (permissionAccessPath.indexOf(".action")>-1) permissionAccessPath = permissionAccessPath.substring(0, permissionAccessPath.indexOf(".action"));
							//log.info("URL2 : "+s+ " // "+permissionAccessPath);
							if (s.equals(permissionAccessPath)) {
								permissionSelected = permission2;
								break;
							}
						}
						if (permissionSelected==null) {							
							for (Permission permission3 : permission2.getPermissionChilds()) {
								if (permission3.getLink()!=null && permission3.getLink().length()>0) {
									s = (permission3.getLink()).substring(1,(permission3.getLink()).length());
									i = s.indexOf("/");
									if (i==-1) {
										s = s.substring(0, s.indexOf(".action"));
									} else {
										s = s.substring(s.indexOf("/")+1, s.indexOf(".action"));
									}
									//if (viewAccessPath.indexOf(".action")>-1) viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
									//log.info("URL3 : "+s+ " // "+viewAccessPath);
									if (s.equals(permissionAccessPath)) {
										permissionSelected = permission3;
										break;
									}
								}
							}
						}
						
					}
				}
			}
		}
		return permissionSelected;
	}
	
	/*public static boolean isHasRoleAccess(Set<View> lst, String viewAccessPath) {
		if(lst !=null && viewAccessPath!=null) {
			Iterator<View> itr = lst.iterator();
			while(itr.hasNext()) {
				View view = (View)itr.next();
				if (view.getLink()!=null && view.getLink().length()>0) {
					// format maybe in /xxxxxxxxx.action OR /<modul_name>/xxxxxxxxx.action
					String string = (view.getLink()).substring(1,(view.getLink()).length());
					int i = string.indexOf("/");
					//log.info("URL : "+string+ " // "+viewAccessPath);
					if (i==-1) {
						string = string.substring(0, string.indexOf(".action"));
					} else {
						string = string.substring(string.indexOf("/")+1, string.indexOf(".action"));
					}
					try {
						//log.info("URL : "+string+ " // "+viewAccessPath);
						if (string.equals(viewAccessPath)) return true;
					} catch(Exception exception) {
					}
				}
			}
		}
		return false;		
	}*/
	
	/*public static View getViewRoleAccess(Set<View> lst, String viewAccessPath) {
		View viewSelected = null;
		if(lst !=null && viewAccessPath!=null) {
			for (View view : lst) {
				if (view.getLink()!=null && view.getLink().length()>0) {
					// format maybe in /xxxxxxxxx.action OR /<modul_name>/xxxxxxxxx.action
					String string = (view.getLink()).substring(1,(view.getLink()).length());
					int i = string.indexOf("/");
					log.info("URL : "+string+ " // "+viewAccessPath);
					if (i==-1) {
						string = string.substring(0, string.indexOf(".action"));
					} else {
						string = string.substring(string.indexOf("/")+1, string.indexOf(".action"));
					}
					if (viewAccessPath.indexOf(".action")>-1) {
						viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
					}
					try {
						//log.info("URL : "+string+ " // "+viewAccessPath);
						if (string.equals(viewAccessPath)) viewSelected = view;
					} catch(Exception exception) {
					}
					
					if (viewSelected==null) {
						for (View view2 : view.getMenuChilds()) {
							if (view2.getLink()!=null && view2.getLink().length()>0) {
								
							}
						}
					}
					
				}
			}
		}
		return viewSelected;		
	}*/
	
	
	/*public static View getViewRoleAccess(Set<View> lst, String viewAccessPath) {
		View viewSelected = null;
		if(lst !=null && viewAccessPath!=null) {
			String s = "";
			int i = 0;
			if (viewAccessPath.indexOf(".action")>-1) viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
			// format maybe in /<path>.action OR /<modul_name>/<path>.action
			for (View view : lst) {
				if (view.getLink()!=null && view.getLink().length()>0) {
					s = (view.getLink()).substring(1,(view.getLink()).length());
					i = s.indexOf("/");
					if (i==-1) {
						s = s.substring(0, s.indexOf(".action"));
					} else {
						s = s.substring(s.indexOf("/")+1, s.indexOf(".action"));
					}
					//if (viewAccessPath.indexOf(".action")>-1) viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
					//log.info("URL1 : "+s+ " // "+viewAccessPath);
					if (s.equals(viewAccessPath)) {
						viewSelected = view;
						break; 
					}
				}
				if (viewSelected==null) {
					for (View view2 : view.getMenuChilds()) {
						if (view2.getLink()!=null && view2.getLink().length()>0) {
							s = (view2.getLink()).substring(1,(view2.getLink()).length());
							i = s.indexOf("/");
							if (i==-1) {
								s = s.substring(0, s.indexOf(".action"));
							} else {
								s = s.substring(s.indexOf("/")+1, s.indexOf(".action"));
							}
							//if (viewAccessPath.indexOf(".action")>-1) viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
							//log.info("URL2 : "+s+ " // "+viewAccessPath);
							if (s.equals(viewAccessPath)) {
								viewSelected = view2;
								break;
							}
						}
						if (viewSelected==null) {							
							for (View permission3 : view2.getMenuChilds()) {
								if (permission3.getLink()!=null && permission3.getLink().length()>0) {
									s = (permission3.getLink()).substring(1,(permission3.getLink()).length());
									i = s.indexOf("/");
									if (i==-1) {
										s = s.substring(0, s.indexOf(".action"));
									} else {
										s = s.substring(s.indexOf("/")+1, s.indexOf(".action"));
									}
									//if (viewAccessPath.indexOf(".action")>-1) viewAccessPath = viewAccessPath.substring(0, viewAccessPath.indexOf(".action"));
									//log.info("URL3 : "+s+ " // "+viewAccessPath);
									if (s.equals(viewAccessPath)) {
										viewSelected = permission3;
										break;
									}
								}
							}
						}
						
					}
				}
			}
		}
		return viewSelected;
	}*/
	
	public static boolean isSameActionClass(String previous, String next) {
		boolean b = false;
		//log.info("action = "+previous+" // "+next);
		if (previous!=null && previous.length()>0 && next!=null && next.length()>0) {
			if (previous.substring(0, previous.indexOf("_")).equalsIgnoreCase(next.substring(0, next.indexOf("_")))) b = true;
		}
		return b;
	}

/*	*//** Read an input stream in its entirety into a byte array *//*
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        int bufSize = 1024 * 1024;
        byte[] content;

        List<byte[]> parts = new LinkedList<byte[]>();
        InputStream in = new BufferedInputStream(inputStream);

        byte[] readBuffer = new byte[bufSize];
        byte[] part = null;
        int bytesRead = 0;

        // read everyting into a list of byte arrays
        while ((bytesRead = in.read(readBuffer, 0, bufSize)) != -1) {
            part = new byte[bytesRead];
            System.arraycopy(readBuffer, 0, part, 0, bytesRead);
            parts.add(part);
        }

        // calculate the total size
        int totalSize = 0;
        for (byte[] partBuffer : parts) {
            totalSize += partBuffer.length;
        }

        // allocate the array
        content = new byte[totalSize];
        int offset = 0;
        for (byte[] partBuffer : parts) {
            System.arraycopy(partBuffer, 0, content, offset,partBuffer.length);
            offset += partBuffer.length;
        }
        in.close();
        return content; 
    }*/
	
	public static String getStringFromDate(Date input, String format) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.format(input);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getStringFromTime(Time input, String format) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.format(input);
		} catch (Exception e) {
			return null;
		}
	}
    
    public static Calendar getCalendarFromString(String input, String format) {
    	Calendar calendar = new GregorianCalendar();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		calendar.setTime(sdf.parse(input));
		} catch (Exception e) {
			return null;
		}
    	return calendar;
    }
    
    public static Date getDateFromString(String input, String format) {
    	Calendar calendar = new GregorianCalendar();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		calendar.setTime(sdf.parse(input));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	return calendar.getTime();
    }
    
    public static Time getTimeFromString(String input, String format) {
    	Calendar calendar = new GregorianCalendar();
    	java.sql.Time time = new java.sql.Time(calendar.getTime().getTime());
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		calendar.setTime(sdf.parse(input));
    		time = new java.sql.Time(calendar.getTime().getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	return time;
    }
    
    public static boolean alreadyContain(long[] ls, long l) {
    	for (int i=0; i<ls.length; i++) {
    		if (ls[i]==l) return true;
    	}
    	return false;
    }
    
    public static Vector<String> monthList() {
    	Vector<String> strings = new Vector<String>();
    	for (int i=0; i<m.length; i++) {
    		strings.add(m[i]);
    	}
    	return strings;
    }
    
    public static int getMonthIndex(String string) {
    	for (int i=0; i<m.length; i++) {
    		if (string.equalsIgnoreCase(m[i])) {
    			return i;
    		}
    	}
		return -1;
    }
    
    public static String getMonth(int index) {
		return m[index];
    }
    
    public static boolean[] convertFromString(boolean[] bs, String readonlyTabs) {
    	if (readonlyTabs!=null && readonlyTabs.length()>0) {
	    	StringTokenizer stringTokenizer = new StringTokenizer(readonlyTabs, ",");
	    	while (stringTokenizer.hasMoreTokens()) {
	    		try {
		    		int j = Integer.parseInt(stringTokenizer.nextToken());
					bs[j-1] = true;
				} catch (Exception e) {}
	    	}
    	}
    	return bs;
    }
    
    
    public static void main(String[] args) {
    	System.out.println("test : "+CommonUtil.randomin(6, true, true, true));
    }
    
    public static String getRequestVal(HashMap<String, Object> request, String key){
		
		String result = null;
		if(request.get(key)!=null){
			
			if(request.get(key).toString()!=null && !request.get(key).toString().isEmpty()){
				return request.get(key).toString();
			}
		}
		
		return result;
		
	}
    
}
