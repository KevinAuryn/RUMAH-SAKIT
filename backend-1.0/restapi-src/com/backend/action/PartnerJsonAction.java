package com.backend.action;

import java.util.HashMap;
import java.util.List;

import com.backend.dao.BackendDAOFactory;
import com.backend.dao.BackendDAOFactoryHibernate;
import com.backend.other.Message;
import com.backend.other.RequestList;
import com.mpe.common.action.BaseAction;


public class PartnerJsonAction  extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	HashMap<String, Object> request;
	HashMap<String, Object> callback;
	List<RequestList> requestList;
	
	BackendDAOFactory backendDAOFactory = BackendDAOFactory.instance(BackendDAOFactoryHibernate.class);
	
	public String partnerRequest(){
		
		callback = new HashMap<String, Object>();
		
		try {
			
			/*String hexaResult = DigestUtils.sha1Hex("BSM"+"1"+"1522997610000"+"123");
			 
			 System.out.println("####### :: "+hexaResult);
			
			 
			 
			 String isiXml = "";
			 FileReader fr = new FileReader("/home/aaz/KERJAAN_SMMF/KBIJ/kbij_new/contoh-xml/Renaldy_Bosito_Martin.xml");
			 BufferedReader br = new BufferedReader(fr);
			 String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					//System.out.println(sCurrentLine);
					isiXml += sCurrentLine;
				}
			 Session session =  merchantDAOFactory.getRequestResponseDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			 Transaction transaction = session.beginTransaction();
			 RequestResponse rr = merchantDAOFactory.getRequestResponseDAO().findById(212);
			 
			 rr.setErrorMessage("");
			 rr.setProviderId(1);
			 rr.setPartnerId(1);
			 rr.setSignature(hexaResult);
			 rr.setResponse(isiXml);
			 rr.setResponseFromProviderOn(new Date());
			 merchantDAOFactory.getRequestResponseDAO().update(rr,session);
			 transaction.commit();
			 
			 session.close();*/
			 
			
			///////////////////
		//	callback = backendDAOFactory.getAppConfigDAO().sendRequest(request, requestList);
			
		} catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",e.getMessage());
		}
		
		
		return "partnerRequestJsonSuccess";
	}
	
	public String sendToKBIJ(){
		
		callback = new HashMap<String, Object>();
		 
		try {
			//bikin balikans simulasi
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss.SSS");
			 String requestOn = sdf.format(new Date());
			 
			
			
			 //callback = backendDAOFactory.getAppConfigDAO().sendRequest(request, requestList);
			 String isiXml = "";
			FileReader fr = new FileReader("/home/aaz/KERJAAN_SMMF/KBIJ/kbij_new/contoh-xml/Renaldy_Bosito_Martin.xml");
			BufferedReader br = new BufferedReader(fr);
			 String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
					isiXml += sCurrentLine;
				}
				
				
			 callback.put("responseFromProviderOn",sdf.format(new Date()));
			 callback.put("requestToProviderOn", sdf.format(new Date()));
			 callback.put("xmlFromProvider", isiXml);
			 callback.put("responseStatus",Message.SUCCESS);
			 callback.put("responseMessage",Message.SUCCESS);
			 */
			 System.out.println("##### MASUK SEND KBIJ");
			 callback = backendDAOFactory.getAppConfigDAO().sendRequestKBIJ(request, requestList);
			 
		} catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",e.getMessage());
		}
		
		return "sendToKBIJJsonSuccess";
	}
	
	
	
	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String partialList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String save() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String edit() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String detail() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String cancel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setRequest(HashMap<String, Object> request) {
		this.request = request;
	}

	public HashMap<String, Object> getCallback() {
		return callback;
	}

	public void setRequestList(List<RequestList> requestList) {
		this.requestList = requestList;
	}


}
