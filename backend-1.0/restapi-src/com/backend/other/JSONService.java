/**
 * 
 */
package com.backend.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * @author Mas AH
 * @create on Mar 3, 2015 4:49:10 PM
 */
public class JSONService {
	
	static Log log = LogFactory.getLog("JSONService");
	
	public static Map<String, String> createJournal(String path, int timeout,
			Long branchId,
			Long currencyId,
			Long organizationId,
			Long journalTypeId,
			String journalReference,
			String journalDescription,
			String journalDate,
			String linkCode,
			Long linkId,
			String userName,
			List<Long> debitChartOfAccountIds,
			List<BigDecimal> debitAmounts,
			List<Long> debitCurrencyIds,
			List<BigDecimal> debitInbaseCurrencies,
			List<String> debitLineDescriptions,
			List<BigDecimal> debitForexRates,
			List<Integer> debitChartOfAccountSeqs,
			List<Long> creditChartOfAccountIds,
			List<BigDecimal> creditAmounts,
			List<Long> creditCurrencyIds,
			List<BigDecimal> creditInbaseCurrencies,
			List<String> creditLineDescriptions,
			List<BigDecimal> creditForexRates,
			List<Integer> creditChartOfAccountSeqs) throws Exception {
		
		/*Long branchId,
		Long currencyId,
		Long organizationId,
		Long journalTypeId,
		String journalReference,
		String journalDescription,
		String journalDate,
		String linkCode,
		Long linkId,
		String userName,
		List<Long> debitChartOfAccountIds,
		List<BigDecimal> debitAmounts,
		List<Long> debitCurrencyIds,
		List<BigDecimal> debitInbaseCurrencies,
		List<String> debitLineDescriptions,
		List<BigDecimal> debitForexRates,
		List<Integer> debitChartOfAccountSeqs,
		List<Long> creditChartOfAccountIds,
		List<BigDecimal> creditAmounts,
		List<Long> creditCurrencyIds,
		List<BigDecimal> creditInbaseCurrencies,
		List<String> creditLineDescriptions,
		List<BigDecimal> creditForexRates,
		List<Integer> creditChartOfAccountSeqs
		*/
		
		//String string = null;
		Map<String, String> mapResult = new HashMap<String, String>();
		InputStream is = null;
		JsonReader rdr = null;
		OutputStreamWriter out = null;
		try {
			
			URL url = new URL(path+"/soap/JournalJsonAction_createJournal.action");
	        URLConnection connection = url.openConnection();
	        connection.setDoOutput(true); // Triggers POST
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        connection.setConnectTimeout(timeout);
	        connection.setReadTimeout(timeout);
	        
	        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
	        
	        
	        objectBuilder.add("branchId", branchId)
	        			 .add("currencyId", currencyId)
	        			 .add("organizationId", organizationId)
	        			 .add("journalTypeId", journalTypeId)
	        			 .add("journalReference", journalReference)
	        			 .add("journalDescription", journalDescription)
	        			 .add("journalDate", journalDate)
	        			 .add("linkCode", linkCode)
	        			 .add("linkId", linkId)
	        			 .add("userName", userName);
	        
	        Map<String, JsonArrayBuilder> mapBuilder = new HashMap<String, JsonArrayBuilder>();
		       
	        // untuk add list long
		    mapBuilder.putAll(setMapLong("debitChartOfAccountIds", debitChartOfAccountIds));
		    mapBuilder.putAll(setMapLong("debitCurrencyIds", debitCurrencyIds));
		    mapBuilder.putAll(setMapLong("creditChartOfAccountIds", creditChartOfAccountIds));
		    mapBuilder.putAll(setMapLong("creditCurrencyIds", creditCurrencyIds));
		    
		    
		    //untuk add list String
		    mapBuilder.putAll(setMapString("debitLineDescriptions", debitLineDescriptions));
		    mapBuilder.putAll(setMapString("creditLineDescriptions", creditLineDescriptions));
		    
		    //untuk add list BigDecimal
		    mapBuilder.putAll(setMapBigDecimal("debitAmounts", debitAmounts));
		    mapBuilder.putAll(setMapBigDecimal("debitInbaseCurrencies", debitInbaseCurrencies));
		    mapBuilder.putAll(setMapBigDecimal("debitForexRates", debitForexRates));
		    mapBuilder.putAll(setMapBigDecimal("creditAmounts", creditAmounts));
		    mapBuilder.putAll(setMapBigDecimal("creditInbaseCurrencies", creditInbaseCurrencies));
		    mapBuilder.putAll(setMapBigDecimal("creditForexRates", creditForexRates));
		    
		    //untuk add list Integer
		    mapBuilder.putAll(setMapInteger("debitChartOfAccountSeqs", debitChartOfAccountSeqs));
		    mapBuilder.putAll(setMapInteger("creditChartOfAccountSeqs", creditChartOfAccountSeqs));
		    
		        
		    for(Map.Entry<String, JsonArrayBuilder> map : mapBuilder.entrySet()){
		    	   objectBuilder.add(map.getKey(), map.getValue().build());
		    }
		    
		    
	        JsonObject glObj = objectBuilder.build();
	        
	        out = new OutputStreamWriter(connection.getOutputStream());
	        out.write(glObj.toString());
			out.close();
	        
	        is = connection.getInputStream();
			rdr = Json.createReader(is);
			
			JsonObject obj = rdr.readObject();
			JsonObject obj2 = obj.getJsonObject("responseResult");
			JsonObject obj3 = obj2.getJsonObject("responseCode");
			
			//int responCode = Integer.parseInt(obj3.getString("code"));
			System.out.println("obj2 ::: "+obj.getJsonObject("responseResult"));
			System.out.println("obj23 ::: "+obj2.getJsonObject("responseCode"));
			
			
			
			mapResult.put("code", obj3.getString("code"));
			mapResult.put("journalNumber", obj2.getString("result"));
			
			/*if (responCode==0) {
				string = obj3.getString("code");
				map
				JsonString  noVoucher = obj.ob
			} else {
				
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (is!=null) {
				is.close(); is=null;
			}
			if (rdr!=null) {
				rdr.close(); rdr=null;
			}
		}
		return mapResult;
	}
	

	
	
	
	public static   Map<String, JsonArrayBuilder> setMapLong(String nameList, List<Long> list){
		
		  Map<String, JsonArrayBuilder> hasilMap =  new HashMap<String, JsonArrayBuilder>();
		try {
			 JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			 for (Long jk : list){ 
		        	arrayBuilder.add(jk);
		      }
			 
			 hasilMap.put(nameList, arrayBuilder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hasilMap;
		
	}
	
	public static  Map<String, JsonArrayBuilder> setMapString(String nameList, List<String> list){
		
		  Map<String, JsonArrayBuilder> hasilMap =  new HashMap<String, JsonArrayBuilder>();
		try {
			 JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			 for (String jk : list){ 
		        	arrayBuilder.add(jk);
		      }
			 
			 hasilMap.put(nameList, arrayBuilder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hasilMap;
		
	}
	
	public static   Map<String, JsonArrayBuilder> setMapBigDecimal(String nameList, List<BigDecimal> list){
		
		  Map<String, JsonArrayBuilder> hasilMap =  new HashMap<String, JsonArrayBuilder>();
		try {
			 JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			 for (BigDecimal jk : list){ 
		        	arrayBuilder.add(jk);
		      }
			 
			 hasilMap.put(nameList, arrayBuilder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hasilMap;
		
	}
	
	public static   Map<String, JsonArrayBuilder> setMapInteger(String nameList, List<Integer> list){
		
		  Map<String, JsonArrayBuilder> hasilMap =  new HashMap<String, JsonArrayBuilder>();
		try {
			 JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			 for (Integer jk : list){ 
		        	arrayBuilder.add(jk);
		      }
			 
			 hasilMap.put(nameList, arrayBuilder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hasilMap;
		
	}
	

	public static HashMap<String, Object> sendRequestToSMMF(String targetURL, String timeOut, String readTimeout, String jsonListRequest) throws Exception {
		
		HashMap<String, Object> callback = new HashMap<String, Object>();
		URL url;
		HttpURLConnection connection = null;  
		OutputStreamWriter out = null;
		
		try {
			//hardcode dulu
			 //kirim ke json ke smmf
			 //Create connection
				url = new URL(targetURL);
				Logging.lineSeparator(String.format("Connecting to %s", url), 60);
		      	connection = (HttpURLConnection)url.openConnection();
		        connection.setDoOutput(true); // Triggers POST
		        connection.setRequestProperty("Accept", "application/json");
		        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		        connection.setConnectTimeout(Integer.parseInt(timeOut));
		        connection.setReadTimeout(Integer.parseInt(readTimeout));
			    connection.setUseCaches (false);
			    connection.setDoInput(true);
			    connection.setDoOutput(true);
		      
		      //Send request
		      	out = new OutputStreamWriter(connection.getOutputStream());
		        out.write(jsonListRequest);
				out.close();

		      //Get Response	
				boolean connected = false ;
				String connError = "";
				
				 switch (connection.getResponseCode()) {
		            case HttpURLConnection.HTTP_OK:
		            	connError = "** Connection OK ** URL : "+targetURL;
		            	log.info(connError);
		                connected = true;
		                break;
		            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
		            	connError = "** Connection Gateway Timeout ** URL : "+targetURL;
		            	log.warn(connError);
		                break;// retry
		            case HttpURLConnection.HTTP_UNAVAILABLE:
		                connError = "** Connection Unavailable ** URL : "+targetURL;
		            	log.warn(connError);
		                break;// retry, server is unstable
		            default:
		            	  connError = "** Connection Unknown ** URL : "+targetURL;
			            	log.warn(connError);
				 }
				 
				if(connected){
					// System.out.println("Conecct ::  "+);
				      InputStream is = connection.getInputStream();
				      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				      String line;
				      StringBuffer response = new StringBuffer(); 
				      while((line = rd.readLine()) != null) {
				        response.append(line);
				        response.append('\r');
				      }
				      rd.close();
				      if (is!=null) {
							is.close(); is=null;
				      }
				
				      JSONObject jsonObject = new JSONObject(response.toString());
				      JSONObject jsonObject2 = new JSONObject(jsonObject.get("callback").toString());
				      if(!jsonObject2.get("responseStatus").equals(Message.RUNTIME_EXCEPTION) && !jsonObject2.get("responseStatus").toString().isEmpty()){
				    	  callback.put("xmlFromProvider", jsonObject2.get("xmlFromProvider"));
					      callback.put("requestToProviderOn", jsonObject2.get("requestToProviderOn"));
					      callback.put("responseFromProviderOn", jsonObject2.get("responseFromProviderOn"));
				      }
				      
				      callback.put("responseStatus", jsonObject2.get("responseStatus"));
				      callback.put("responseMessage", jsonObject2.get("responseMessage"));
				}else{
					callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
					callback.put("responseMessage",connError);
				}
			
		      return callback;
			
		}
		catch (java.net.SocketTimeoutException e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage","SEND TO SMMF "+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage","SEND TO SMMF "+e.getMessage());
		} finally {
			
		    if(connection != null) {
		        connection.disconnect(); 
		        connection = null;
		    }
		}
		
		
		return callback;
	}
	
	public static   Map<String, JsonArrayBuilder> setMapRequestList(String parameterName, List<RequestList> listRequest){
		
		Map<String, JsonArrayBuilder> hasilMap =  new HashMap<String, JsonArrayBuilder>();
		  
		try {
			Gson gson = new Gson();
			 JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			 arrayBuilder.add(gson.toJson(listRequest));
			 
			 hasilMap.put(parameterName, arrayBuilder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hasilMap;
		
	}
	
	public static HashMap<String, Object> kbijSend(String targetURL, String timeOut, String readTimeOut, String xmlString, String kbijUsername, String kbijPassword) throws IOException, URISyntaxException{
		
		HashMap<String, Object> callback = new HashMap<String, Object>();
		HttpURLConnection connection = null;  
		OutputStreamWriter out = null;
		try {
					System.out.println("#### SEND TO KBIJ #######");
			 		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss.SSS");
					callback.put("requestToProviderOn", sdf.format(new Date()));
					URL url = new URL(targetURL);
					//Reader requestReader = new FileReader( "request.xml" );
					Writer responseWriter = new StringWriter();
					// configure local proxy settings
					String proxyAddress="proxy.smmf.co.id";
					int proxyPort = 8080;
				//	System.setProperty( "https.proxyHost", proxyAddress );
				//	System.setProperty( "https.proxyPort", proxyPort+"" );
					// configure local keystore properties
					// note: Java version 1.4 has JSSE included
					// note: use Java 'keytool' tool to manage the keystore
					//System.setProperty( "javax.net.ssl.trustStore", ".keystore" );
					//System.setProperty( "javax.net.ssl.trustStore", "/usr/java/jdk/jre/lib/security/cacerts" );
				
					// set an Authenticator to generate username/password
					Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication()
					{
				//	String username = Property.KBIJ_USERNAME;
				//	String password = Property.KBIJ_PASSWORD;
					return new PasswordAuthentication( kbijUsername, kbijPassword.toCharArray()
					);
					}
					};
					Authenticator.setDefault( auth );
					
					// prepare and open an HTTP connection
					Logging.lineSeparator(String.format("Connecting to %s", url), 60);
					
					/*connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod( "POST" );
					//connection.setDoOutput( true );
					connection.setRequestProperty( "Content-Type", "text/xml" );
					connection.addRequestProperty("Content-Type", "text/xml");
					connection.setConnectTimeout(Integer.parseInt(timeOut));
					connection.setReadTimeout(Integer.parseInt(readTimeOut));
					connection.setUseCaches (false);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					
					//Send request
			      	out = new OutputStreamWriter(connection.getOutputStream());
			        out.write(xmlString);
					out.close();
					
					//
					InputStream is = connection.getInputStream();
				    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				    String line;
				    StringBuffer response = new StringBuffer();
				      while((line = rd.readLine()) != null) {
				        response.append(line);
				       // response.append('\r');
				    }*/
					
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod( "POST" );
					connection.setDoOutput( true );
					connection.setRequestProperty( "Content-Type", "text/xml" );
					connection.setConnectTimeout(Integer.parseInt(timeOut));
				    connection.setReadTimeout(Integer.parseInt(readTimeOut));
					connection.connect();
					// POST the request
					Writer connWriter= new OutputStreamWriter(connection.getOutputStream() );
					connWriter.write( xmlString );
					connWriter.flush();
					connection.getOutputStream().close();
					// get the response
					java.lang.Object response = connection.getContent();
					Reader connReader= new InputStreamReader((InputStream) response );
					int currentLine;
					while ( (currentLine = connReader.read()) != -1 )
					responseWriter.write( currentLine );
					responseWriter.flush();
					
				  /*InputStream is = connection.getInputStream();
			        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				    String line;
				    StringBuffer response = new StringBuffer(); 
				     while((line = rd.readLine()) != null) {
				        response.append(line);
				        response.append('\r');
				     }
				     rd.close();
					*/
					
					callback.put("responseFromProviderOn", sdf.format(new Date()));
					callback.put("xmlFromProvider",responseWriter.toString());
					callback.put("responseStatus",Message.SUCCESS);
					callback.put("responseMessage",Message.SUCCESS);
					
		} 
		catch (ConnectException e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage","SEND TO KBIJ "+e.getMessage());
			
		}
		catch (java.net.SocketTimeoutException e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage","SEND TO KBIJ "+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage","SEND TO KBIJ "+e.getMessage());
			
		}finally {
			if(connection!=null){
				connection.disconnect();
				connection = null;
			}
			
		}
		
		return callback;
		
	}
}
