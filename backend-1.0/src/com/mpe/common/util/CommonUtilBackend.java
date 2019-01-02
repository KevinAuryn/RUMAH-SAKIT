package com.mpe.common.util;

import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class CommonUtilBackend {

	
	public static boolean isValidText(String text, Boolean isLowerAlpha, Boolean isUpperAlpha, Boolean isNumeric) {
		boolean result = true;
		if (isLowerAlpha) {
			Pattern.matches(".*[a-z].*", text);
		}
		
		if (isUpperAlpha) {
			Pattern.matches(".*[A-Z].*", text);
		}
		
		if (isNumeric) {
			Pattern.matches(".*[0-9].*", text);
		}
		return result;
	}
	
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
	
	public static boolean validIdCard(String idCard, Date birthDate, String gender) throws Exception {
    	/*
    	 * 31-02-01-44-03-91-0312
    	 * 31: Mengacu pada kode provinsi. Menurut Permendagri No 39 tahun 2015, saat ini ada 34 provinsi yang terdaftar dalam Republik Indonesia
    	 * 02: Kode kota/kabupaten, kamu bisa melihat kode kota kamu dalam laman ini http://www.kemendagri.go.id/pages/data-wilayah
    	 * 01: Kode kecamatan, setiap kecamatan memiliki kode yang berbeda. Makanya saat kamu membuat e-KTP selalu dianjurkan untuk datang langsung ke kantor kecamatan masing-masing
    	 * 44: Tanggal lahir. Nah, di sini ada perbedaan antara kode laki-laki dan perempuan. Kode untuk laki-laki adalah tanggal lahir 01-31. sedangkan untuk perempuan berbeda lagi, tanggal lahir ditambah 40, jadinya adalah 41-71. Jadi kalau kamu seorang perempuan yang lahir tanggal 12 maka kodenya adalah 40 + 12 yaitu 52
    	 * 03: Mengacu pada bulan lagi, 01 untuk Januari hingga seterusnya 12 untuk Desember.
    	 * 91: Tahun lahir, ditulis dua angka terakhir. Seperti jika kamu lahir tahun 1991 maka hanya ditulis 91 saja
    	 * 0312: Nomor komputerisasi, ini nomor random yang memang sudah diatur oleh komputer agar tidak kembar dengan yang lainnya. Namun biasanya untuk kepala keluarga akan ditulis xx01, untuk anak pertama xx02, begitu seterusnya
    	 * 
    	 */
    	if (birthDate!=null && idCard!=null && idCard.length()>12 && gender!=null && gender.length()==1) {
	    	String d = Formater.getFormatedDate(birthDate, "ddMMyy");
	    	if ((idCard.substring(10, 12)).equalsIgnoreCase(d.substring(4))) {
	    		if ((idCard.substring(8, 10)).equalsIgnoreCase(d.substring(2, 4))) {
	    			if (gender.equalsIgnoreCase("L")) {
	    				if ((idCard.substring(6, 8)).equalsIgnoreCase(d.substring(0, 2))) {
	    					return true;
	    				}
	    			} else if (gender.equalsIgnoreCase("P")) {
	    				int i = Integer.parseInt(d.substring(0, 2));
	    				i = i + 40;
	    				if ((idCard.substring(6, 8)).equalsIgnoreCase(String.valueOf(i))) {
	    					return true;
	    				}
	    			}
	    		}
	    	}
    	}
    	return false;
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
	
	public static String getStringFromDate(Date input, String format) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.format(input);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	public static List<String> getListOfLastDays(int maxResult, String format, Date dateOfFounding) {
		List<String> result = new LinkedList<String>();
		
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= maxResult; i++) {
			String dateInString = new java.text.SimpleDateFormat(format).format(calendar.getTime());
			result.add(dateInString);
			
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			if (dateOfFounding.compareTo(calendar.getTime()) > 0) 
				break;
		}
		
		return result;
	}
	
	public static List<String> getListOfLastWeeks(int maxResult, String format, Date dateOfFounding) {
		List<String> result = new LinkedList<String>();
		
		Calendar calendarForFirstDayOfWeek = Calendar.getInstance();
		for (int i = 1; i <= maxResult; i++) {
			String date = "";
			calendarForFirstDayOfWeek.set(Calendar.DAY_OF_WEEK, calendarForFirstDayOfWeek.getFirstDayOfWeek());
			calendarForFirstDayOfWeek.add(Calendar.DAY_OF_WEEK, 1);
			String dateInString = new java.text.SimpleDateFormat(format).format(calendarForFirstDayOfWeek.getTime());
			date += dateInString +" - ";
			
			calendarForFirstDayOfWeek.set(Calendar.DAY_OF_WEEK, calendarForFirstDayOfWeek.getFirstDayOfWeek());
			calendarForFirstDayOfWeek.add(Calendar.DAY_OF_WEEK, 7);			
			dateInString = new java.text.SimpleDateFormat(format).format(calendarForFirstDayOfWeek.getTime());
			date += dateInString;
			
			result.add(date);
			calendarForFirstDayOfWeek.add(Calendar.DAY_OF_WEEK, -8);
			if (dateOfFounding.compareTo(calendarForFirstDayOfWeek.getTime()) > 0) 
				break;
		}
		
		return result;
	}
	
	public static List<String> getListOfLastMonths(int maxResult, String format, Date dateOfFounding) {
		List<String> result = new LinkedList<String>();
		
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= maxResult; i++) {
			String dateInString = new java.text.SimpleDateFormat(format).format(calendar.getTime());
			result.add(dateInString);
			calendar.add(Calendar.MONTH, -1);
			
			if (dateOfFounding.compareTo(calendar.getTime()) > 0) 
				break;
		}
		
		return result;
	}

	public static List<String> getListOfLastYears(int maxResult, String format, Date dateOfFounding) {
		List<String> result = new LinkedList<String>();
		
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= maxResult; i++) {
			String dateInString = new java.text.SimpleDateFormat(format).format(calendar.getTime());
			result.add(dateInString);
			calendar.add(Calendar.YEAR, -1);
			
		}
		
		return result;
	}
	
	public static String convertText(String text, boolean isNotEmail) {
		String pattern = "_petik_"; 
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		if (m.find( )) 
			text = m.replaceAll("'");
		
		pattern = "_comma_";
		r = Pattern.compile(pattern);
		m = r.matcher(text);
		if (m.find( )) 
			text = m.replaceAll(",");
		
		pattern = "_at_"; 
		r = Pattern.compile(pattern);
		m = r.matcher(text);
		if (m.find( )) 
			text = m.replaceAll("@");
		
		pattern = "_spasi_"; 
		r = Pattern.compile(pattern);
		m = r.matcher(text);
		if (m.find( )) 
			text = m.replaceAll(" ");
		
		pattern = "_space_"; 
		r = Pattern.compile(pattern);
		m = r.matcher(text);
		if (m.find( )) 
			text = m.replaceAll(" ");
		
		pattern = "_dot_";
		r = Pattern.compile(pattern);
		m = r.matcher(text);
		if (m.find( )) 
			text = m.replaceAll(".");
		
		if (isNotEmail) {
			pattern = "_";
			r = Pattern.compile(pattern);
			m = r.matcher(text);
			if (m.find( )) 
				text = m.replaceAll(" ");
		}
		
		return text;
	}
	
	public static Integer getRandomInt() {
		int result = new Random().nextInt(1000 - 1 + 1) + 1;
		return result;
	}
	
	public static Date getDate(Integer addHours, Integer addMinutes, Integer addSeconds) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, addHours);
		cal.add(Calendar.MINUTE, addMinutes);
		cal.add(Calendar.SECOND, addSeconds);
		
		return cal.getTime();
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
	
	public static HostnameVerifier byPass(final String strHost){
		
		 HostnameVerifier allHostsValid = null;
		try {
			TrustManager[] trustAllCerts = new TrustManager[] {
		       new X509TrustManager() {
				
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}
		       }
			};
			
			SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    
		 //  final  String strHost ="sit.secure.smfs.co.id";
		    // Create all-trusting host name verifier
		    allHostsValid = new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					//System.out.println(" hostname :: "+hostname);
					if(hostname.equalsIgnoreCase(strHost)){
		                return true;
		            }
		            return false;
				}
			};
			return allHostsValid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allHostsValid;
	}
	
	
}
