package com.backend.other;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class TestKbij {

	public static void main(String args []){
		try {
			System.out.println("###### START ######");
			URL url = new URL(
					"https://uat.creditbureauindonesia.co.id/enquiry/inthttp.pgm" );
					Reader requestReader = new FileReader( "request.xml" );
					Writer responseWriter = new FileWriter( "response.xml" );
					// configure local proxy settings
					String proxyAddress="proxy.smmf.co.id";
					int proxyPort = 8080;
					System.setProperty( "https.proxyHost", proxyAddress );
					System.setProperty( "https.proxyPort", proxyPort+"" );
					// configure local keystore properties
					// note: Java version 1.4 has JSSE included
					// note: use Java 'keytool' tool to manage the keystore
					System.setProperty( "javax.net.ssl.trustStore", "/opt/java/64/jdk1.8.0_131/jre/lib/security/cacerts" );
					// set an Authenticator to generate username/password
					Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication()
					{
						String username = Property.KBIJ_USERNAME;
						String password = Property.KBIJ_PASSWORD;
					return new PasswordAuthentication( username, password.toCharArray()
					);
					}
					};
					System.out.println("## :: "+requestReader.toString());
					Authenticator.setDefault( auth );
					// prepare and open an HTTP connection
					HttpsURLConnection connection
					= (HttpsURLConnection) url.openConnection();
					connection.setRequestMethod( "POST" );
					connection.setDoOutput( true );
					connection.setRequestProperty( "Content-Type", "text/xml" );
					connection.connect();
					// POST the request
					Writer connWriter
					= new OutputStreamWriter(
					connection.getOutputStream() );
					int c = 0;
					while ( (c = requestReader.read()) != -1 )
					connWriter.write( c );
					connWriter.flush();
					connection.getOutputStream().close();
					
					
					// get the response
					java.lang.Object response = connection.getContent();
					Reader connReader
					= new InputStreamReader(
					(InputStream) response );
					while ( (c = connReader.read()) != -1 )
					responseWriter.write( c );
					responseWriter.flush();
					
					System.out.println("###### END ######");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
