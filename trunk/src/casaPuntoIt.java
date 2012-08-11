/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author marco
 */

//La classe principale
public class casaPuntoIt extends PortaleImmobiliare {     

    //Parametri generali
	private final String NOMEPORTALE = "casa.it";
	private final String URL_ROOT = "http://www.casa.it";
	private final String SECUREURL_ROOT = "http://admin.casa.it";
	private final String USERNAME = "290547";
    private final String PASSWORD = "test1234";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:12.0) Gecko/20100101 Firefox/12.0";
    private String __EVENTARGUMENT = "";
    private String __EVENTTARGET = "";
    private String __EVENTVALIDATION = "";
    private String __VIEWSTATE = "";
    private String __ASYNCPOST = "true";
    private String __LASTFOCUS = "";
    private String SESSIONCOOKIE_HEADER = "";
    private String SESSIONCOOKIE_NAME = "";
    private String SESSIONCOOKIE_VALUE = "";
    private String SESSIONCOOKIE_DOMAIN = "www.casa.it";
    private String SESSIONCOOKIE_HEADER2 = "";
    private String SESSIONCOOKIE_NAME2 = "";
    private String SESSIONCOOKIE_VALUE2 = ""; 
    private String SESSIONCOOKIE_DOMAIN2 = "admin.casa.it";   
    private String LOCATION;
    private String CODICEINSERZIONE;
    private boolean INSERIMENTO_OK = false;
    
    //Parametri scheda (elaborati in base al portale)
    String ctl00$cp$TB_Address = "";
    String ctl00$cp$TB_Bathrooms = "";
    String ctl00$cp$TB_Cap = "";
    String ctl00$cp$TB_Price = "";
    String ctl00$cp$TB_RoomsNumber = "";
    String ctl00$cp$TB_Surface = "";
    String ctl00$cp$cddHouseTipology_ClientState = "";
    String ctl00$cp$ddlBoxType = "";
    String ctl00$cp$ddlBuildingType = "";
    String ctl00$cp$ddlContractType = "";
    String ctl00$cp$ddlEnergyEfficiencyRating = "";
    String ctl00$cp$ddlGardenType = "";
    String ctl00$cp$ddlHeatingType = "";
    String ctl00$cp$ddlTipology = "";
    String ctl00$cp$txtDescrIta = "";
    String ctl00$cp$cddRegion_ClientState = "";
    String ctl00$cp$ddlRegion = "";
    String ctl00$cp$cddProvince_ClientState = "";
    String ctl00$cp$ddlCity = "";
    String ctl00$cp$cddCity_ClientState = "";
    String ctl00$cp$ddlProvince = "";
    String paramRegione = "";
    String latitudine= "";
    String longitudine = "";
    String ctl00$cp$TB_BuildingAge = "";
    String ctl00$cp$TB_TotLevel ="";
    String ctl00$cp$ddlBuildingConditions ="";
    String ctl00$cp$ddlLevel = "";
    String ctl00$cp$TB_Ref = "";

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   
	
    
	//Costruttore
	public casaPuntoIt (String urlIcona, String valoreLabel, String idPortale) {		
		super(urlIcona, valoreLabel, idPortale);			
	}

	
	//GET della home page
	private void connessione_0(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
        System.out.println("CONNESSIONE 0");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httpget.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httpget.getMethod());
        System.out.println("Protocol version: " + httpget.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        httpget.addHeader(requestHeader0);
        
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httpget.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Send the request
        HttpResponse response = httpclient.execute(httpget);
        
        
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        Header[] responseHeaders;
        int statusCode = response.getStatusLine().getStatusCode();
        switch (statusCode)
    	{
    	    case 200:
    	    	//Show response headers
    	        responseHeaders = response.getAllHeaders(); 
    	        System.out.println("Response status: " + response.getStatusLine());
    	        System.out.println("Response headers: " + responseHeaders.length + "\n");
    	        for(int i=0; i<responseHeaders.length; i++) {
    	        	System.out.println(responseHeaders[i]);
    	        }
    	        System.out.println("----------------------------------------");
    	    	//Show response body
		        System.out.println("Response body: \n");
		        String responseBody = responseHandler.handleResponse(response);
		        System.out.println(responseBody);
		        System.out.println("----------------------------------------");
    	        break;
    	    case 302:
    	    	//Show response headers
    	        responseHeaders = response.getAllHeaders(); 
    	        System.out.println("Response status: " + response.getStatusLine());
    	        System.out.println("Response headers: " + responseHeaders.length + "\n");       
    	        boolean cookieHeaderFound = false;
    	    	boolean locationHeaderFound = false;
    	        for(int i=0; i<responseHeaders.length; i++) {       	
    	        	Header currentHeader = responseHeaders[i];
    	        	System.out.println(currentHeader);
    	        	//Get session cookie
    	        	if(currentHeader.getName().contains("Set-Cookie")) {
    	        		//Cookie trovato
    	        		cookieHeaderFound = true;
    	        		SESSIONCOOKIE_HEADER = currentHeader.getValue();
    	        		int end = SESSIONCOOKIE_HEADER.indexOf("=");
    	                SESSIONCOOKIE_NAME = SESSIONCOOKIE_HEADER.substring(0, end);                   
    	                int start = end + 1;
    	                end = SESSIONCOOKIE_HEADER.indexOf(";");
    	                SESSIONCOOKIE_VALUE = SESSIONCOOKIE_HEADER.substring(start, end);
    	                System.out.println("Session cookie name: " + SESSIONCOOKIE_NAME);
    	                System.out.println("Session cookie value: " + SESSIONCOOKIE_VALUE);
    	        	}
    	        	//Get the redirect location
    	        	if(currentHeader.getName().contains("Location")) {
    	        		//Location trovata
    	        		locationHeaderFound = true;
    	        		LOCATION = currentHeader.getValue();
    	        		System.out.println("Location value: " + LOCATION);
    	        	}        	
    	        }        
    	        //Se non trovo le intestazioni che cerco
    	    	if(!cookieHeaderFound || !locationHeaderFound) {
    	    		throw(new HttpResponseException("Response header"));
    	    	}
    	        System.out.println("----------------------------------------");
    	    	break;
    	    default:
    	    	throw(new HttpResponseException("Response code"));
    	}

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    	
    //GET della pagina di login
    private void connessione_1(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 1");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httpget.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httpget.getMethod());
        System.out.println("Protocol version: " + httpget.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        httpget.addHeader(requestHeader0);
        
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httpget.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Send the request
        HttpResponse response = httpclient.execute(httpget);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	System.out.println(responseHeaders[i]);
        }
        System.out.println("----------------------------------------");
        
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
            System.out.println("Response body: \n");
            String responseBody = responseHandler.handleResponse(response);
            System.out.println(responseBody);
            System.out.println("----------------------------------------");            
            
            //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                      
            Element __EVENTARGUMENTElement = doc.getElementById("__EVENTARGUMENT");
            Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
            Element __EVENTVALIDATIONElement = doc.getElementById("__EVENTVALIDATION");
            Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");           
            if(__EVENTARGUMENTElement!=null && __EVENTTARGETElement!=null && __EVENTVALIDATIONElement!=null && __VIEWSTATEElement!=null) {
            	__EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
                System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
                
                __EVENTTARGET = __EVENTTARGETElement.attr("value");
                System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
                
                __EVENTVALIDATION = __EVENTVALIDATIONElement.attr("value");
                System.out.println("Value __EVENTVALIDATION: " + __EVENTVALIDATION);
                
                __VIEWSTATE = __VIEWSTATEElement.attr("value");
                System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
            }
            else {
            	throw(new HttpResponseException("Response body"));
            }
            System.out.println("----------------------------------------");
            
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina login
    private void connessione_2(HttpPost httppost) throws ClientProtocolException, IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 2");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader0);
                   
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httppost.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Add request parameters
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("__EVENTARGUMENT", __EVENTARGUMENT));
        postParameters.add(new BasicNameValuePair("__EVENTTARGET", "ctl00$MasterContentPlaceHolder$lbLogin"));
        postParameters.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION));
        postParameters.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
        postParameters.add(new BasicNameValuePair("ctl00$MasterContentPlaceHolder$f_login_codice", USERNAME));
        postParameters.add(new BasicNameValuePair("ctl00$MasterContentPlaceHolder$f_login_password", PASSWORD));        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");       
        boolean cookieHeaderFound = false;
    	boolean locationHeaderFound = false;
        for(int i=0; i<responseHeaders.length; i++) {       	
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        	//Get session cookie
        	if(currentHeader.getName().contains("Set-Cookie")) {
        		//Cookie trovato
        		cookieHeaderFound = true;
        		SESSIONCOOKIE_HEADER = currentHeader.getValue();
        		int end = SESSIONCOOKIE_HEADER.indexOf("=");
                SESSIONCOOKIE_NAME = SESSIONCOOKIE_HEADER.substring(0, end);                   
                int start = end + 1;
                end = SESSIONCOOKIE_HEADER.indexOf(";");
                SESSIONCOOKIE_VALUE = SESSIONCOOKIE_HEADER.substring(start, end);
                System.out.println("Session cookie name: " + SESSIONCOOKIE_NAME);
                System.out.println("Session cookie value: " + SESSIONCOOKIE_VALUE);
        	}
        	//Get the redirect location
        	if(currentHeader.getName().contains("Location")) {
        		//Location trovata
        		locationHeaderFound = true;
        		LOCATION = currentHeader.getValue();
        		System.out.println("Location value: " + LOCATION);
        	}        	
        }        
        //Se non trovo le intestazioni che cerco
    	if(!cookieHeaderFound || !locationHeaderFound) {
    		throw(new HttpResponseException("Response header"));
    	}
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        
	        //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
            Elements invalidUserElement = doc.getElementsByClass("error");              
            //Verifico la presenza di errori nel body, errori riferiti a credenziali non corrette
            if(invalidUserElement!=null && !invalidUserElement.isEmpty()) {
            	throw(new WrongPostDataException(invalidUserElement.html()));
            }
	        System.out.println("----------------------------------------");
        }
        
        if(!response.getStatusLine().toString().contains("200") && !response.getStatusLine().toString().contains("302")) {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
        
    //GET pagina di redirect dopo la Post di login. Viene effettuato un ulteriore redirect automatico alla pagina "Bacheca"
    private void connessione_3(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 3");
    	HttpClient httpclient = new DefaultHttpClient();
        	
    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httpget.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httpget.getMethod());
        System.out.println("Protocol version: " + httpget.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);  
        httpget.addHeader(requestHeader0);            
        
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httpget.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");   
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
        cookie.setPath("/");           
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
        
        //Prevent redirect
        HttpParams params = new BasicHttpParams();
    	params.setParameter("http.protocol.handle-redirects",false);	
    	httpget.setParams(params);

        //Send the request
        HttpResponse response = httpclient.execute(httpget);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        
        boolean cookieHeaderFound = false;
        for(int i=0; i<responseHeaders.length; i++) {       	
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        	//Get session cookie
        	if(currentHeader.getName().contains("Set-Cookie")) {
        		cookieHeaderFound = true;
        		SESSIONCOOKIE_HEADER2 = currentHeader.getValue();
        		int end = SESSIONCOOKIE_HEADER2.indexOf("=");
                SESSIONCOOKIE_NAME2 = SESSIONCOOKIE_HEADER2.substring(0, end);                   
                int start = end + 1;
                end = SESSIONCOOKIE_HEADER2.indexOf(";");
                SESSIONCOOKIE_VALUE2 = SESSIONCOOKIE_HEADER2.substring(start, end);
                System.out.println("Session cookie name 2: " + SESSIONCOOKIE_NAME2);
                System.out.println("Session cookie value 2: " + SESSIONCOOKIE_VALUE2);
        	}       	
        }
        //Se non trovo le intestazioni che cerco
    	if(!cookieHeaderFound) {
    		throw(new HttpResponseException("Response header"));
    	}
        System.out.println("----------------------------------------");
        
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
        }
        
        if(!response.getStatusLine().toString().contains("302")) {
        	throw(new HttpResponseException("Response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //GET della pagina "Bacheca"
    private void connessione_4(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 4");
    	HttpClient httpclient = new DefaultHttpClient();
        	
    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httpget.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httpget.getMethod());
        System.out.println("Protocol version: " + httpget.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);  
        httpget.addHeader(requestHeader0);            
        
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httpget.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");   
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
        cookie.setPath("/");           
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httpget);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);          	
        }
        System.out.println("----------------------------------------");
        
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //GET della pagina "Nuovo annuncio" (step 1)
    private void connessione_5(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 5");
    	HttpClient httpclient = new DefaultHttpClient();

        	//Request URL
            System.out.println("----------------------------------------");
            System.out.println("executing request " + httpget.getURI());
            System.out.println("----------------------------------------");
            
            //Request properties
            System.out.println("Request method: " + httpget.getMethod());
            System.out.println("Protocol version: " + httpget.getProtocolVersion());
            System.out.println("----------------------------------------");            
            
            //Add request headers
            BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
            httpget.addHeader(requestHeader0);

            //Show request headers
            Header[] requestHeaders;
            requestHeaders = httpget.getAllHeaders(); 
            System.out.println("Request headers: " + requestHeaders.length + "\n");
            for(int i=0; i<requestHeaders.length; i++) {
            	System.out.println(requestHeaders[i]);
            }
            System.out.println("----------------------------------------");  
            
            //Set the cookies
            BasicCookieStore cookieStore = new BasicCookieStore(); 
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
            cookie.setPath("/");
            
            cookieStore.addCookie(cookie); 
            ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

            //Send the request
            HttpResponse response = httpclient.execute(httpget);

            //Show response headers
            ResponseHandler<String> responseHandler = new BasicResponseHandler();           
            Header[] responseHeaders;
            responseHeaders = response.getAllHeaders(); 
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response headers: " + responseHeaders.length + "\n");
            for(int i=0; i<responseHeaders.length; i++) {
            	System.out.println(responseHeaders[i]);
            }
            System.out.println("----------------------------------------");
            
            //Show response body
            if(response.getStatusLine().toString().contains("200")) {
	            System.out.println("Response body: \n");
	            String responseBody = responseHandler.handleResponse(response);
	            System.out.println(responseBody);
	            System.out.println("----------------------------------------");  
	            
	            //Parse HMTL to retrieve some informations
	            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);           
	            
	            Element __EVENTARGUMENTElement = doc.getElementById("__EVENTARGUMENT");
	            Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
	            Element __LASTFOCUSElement = doc.getElementById("__VIEWSTATE");
	            Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");
	            
	            if(__EVENTARGUMENTElement!=null && __EVENTTARGETElement!=null && __LASTFOCUSElement!=null && __VIEWSTATEElement!=null) {
	            	__EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
	                System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
	                
	                __EVENTTARGET = __EVENTTARGETElement.attr("value");
	                System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
	                
	                __LASTFOCUS = __LASTFOCUSElement.attr("value");
	                System.out.println("Value __LASTFOCUS: " + __LASTFOCUS);
	                
	                __VIEWSTATE = __VIEWSTATEElement.attr("value");
	                System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
	            }
	            else {
	            	throw(new HttpResponseException("Response body"));
	            }
	            System.out.println("----------------------------------------");
            }
            else {
            	throw(new HttpResponseException("Response code"));
            }
                    
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST dello step 1
    private void connessione_6(HttpPost httppost) throws ClientProtocolException, IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 6");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader0);
                   
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httppost.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Add request parameters
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("__ASYNCPOST", __ASYNCPOST));
        postParameters.add(new BasicNameValuePair("__EVENTARGUMENT", "__EVENTARGUMENT"));
        postParameters.add(new BasicNameValuePair("__EVENTTARGET", "ctl00$cp$LB_Submit"));
        postParameters.add(new BasicNameValuePair("__LASTFOCUS", __LASTFOCUS));
        postParameters.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
        postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$DDL_RequestType", "")); //customer care
        postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$TB_Message", ""));//customer care
        postParameters.add(new BasicNameValuePair("ctl00$ScriptManager1", "ctl00$ScriptManager1|ctl00$cp$LB_Submit"));        
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Address", ctl00$cp$TB_Address));     
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_AddressNumber", ""));           
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Bathrooms", ctl00$cp$TB_Bathrooms));    
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_BuildingAge", ctl00$cp$TB_BuildingAge));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_BuildingExpenses", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_BuildingUnit", ""));   
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Cap", ctl00$cp$TB_Cap));    
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_EnergyEfficencyValue", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_MQBox", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_MQGarden", ""));     
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Price", ctl00$cp$TB_Price));    
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Ref", ctl00$cp$TB_Ref));   
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$TB_RoomsNumber", ctl00$cp$TB_RoomsNumber));    
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Surface", ctl00$cp$TB_Surface));   
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_TotLevel", ctl00$cp$TB_TotLevel));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender1_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender2_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender3_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender4_ClientState", ""));       
        postParameters.add(new BasicNameValuePair("ctl00$cp$UMEnergyEfficency", "RB_UMm2a")); //Lascio il valore di default        
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_BuildingAge_ClientState", ""));//???
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_Italian_ClientState", ""));    //???        
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$cddCity_ClientState", ctl00$cp$cddCity_ClientState));        
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$cddHouseTipology_ClientState", ctl00$cp$cddHouseTipology_ClientState));
        postParameters.add(new BasicNameValuePair("ctl00$cp$cddNation_ClientState", "1:::Italia:::"));  //Lascio i valori per l'Italia    
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$cddProvince_ClientState", ctl00$cp$cddProvince_ClientState));
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$cddRegion_ClientState", ctl00$cp$cddRegion_ClientState));
        postParameters.add(new BasicNameValuePair("ctl00$cp$cddZone_ClientState", "::::::"));
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlBoxType", ctl00$cp$ddlBoxType));       
        postParameters.add(new BasicNameValuePair("ctl00$cp$ddlBuildingConditions", ctl00$cp$ddlBuildingConditions));       
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlBuildingType", ctl00$cp$ddlBuildingType));      
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlCity", ctl00$cp$ddlCity));
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlContractType", ctl00$cp$ddlContractType));      
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlEnergyEfficiencyRating", ctl00$cp$ddlEnergyEfficiencyRating));
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlGardenType", ctl00$cp$ddlGardenType));
        postParameters.add(new BasicNameValuePair("ctl00$cp$ddlHeatingType", ctl00$cp$ddlHeatingType));     
        postParameters.add(new BasicNameValuePair("ctl00$cp$ddlLevel", ctl00$cp$ddlLevel));
        postParameters.add(new BasicNameValuePair("ctl00$cp$ddlNation", "1")); //Default 1 "Italia"        
        postParameters.add(new BasicNameValuePair("ctl00$cp$ddlOccupationState", "-1"));
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlProvince", ctl00$cp$ddlProvince));       
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlRegion", ctl00$cp$ddlRegion));    
        /*OK*/postParameters.add(new BasicNameValuePair("ctl00$cp$ddlTipology", ctl00$cp$ddlTipology));     
        postParameters.add(new BasicNameValuePair("ctl00$cp$ddlZone", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$flat", latitudine));
        postParameters.add(new BasicNameValuePair("ctl00$cp$flong", longitudine));
        postParameters.add(new BasicNameValuePair("ctl00$cp$fmapcenterlat", latitudine));
        postParameters.add(new BasicNameValuePair("ctl00$cp$fmapcenterlong", longitudine));
        postParameters.add(new BasicNameValuePair("ctl00$cp$fmapzoom", "15"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$fshowmarker", "0"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$geocodevalidation", "0"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrEng", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrEngMaxCharsExtender_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrFra", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrFraMaxCharsExtender_ClientState", ""));     
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrIta", ctl00$cp$txtDescrIta));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrFraMaxCharsExtender_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrItaMaxCharsExtender_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrSpa", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrSpaMaxCharsExtender_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrTed", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrTedMaxCharsExtender_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtEnergyEfficiencyNotes", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$txtEnergyEfficiencyNotesMaxCharsExtender_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00_ScriptManager1_HiddenField", ";;AjaxControlToolkit, Version=3.5.40412.0, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:it-IT:1547e793-5b7e-48fe-8490-03a375b13a33:de1feab2:f9cec9bc:ca57ef3c:a67c2700:f2c8e708:8613aea7:3202a5a2:ab09e3fe:87104b7c:be6fb298;;;AjaxControlToolkit, Version=3.5.40412.0, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:it-IT:1547e793-5b7e-48fe-8490-03a375b13a33:8ad18101:720a52bf:589eaa30:698129cf:59fb9c6f;;AjaxControlToolkit, Version=3.5.40412.0, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:it-IT:1547e793-5b7e-48fe-8490-03a375b13a33:35576c48"));
        postParameters.add(new BasicNameValuePair("hiddenInputToUpdateATBuffer_CommonToolkitScripts", "1"));            
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
        cookie.setPath("/");
        
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        
	        //Verifico la presenza di errori nel body
	        //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
            Elements invalidDataElements = doc.getElementsByTag("span");
            if(invalidDataElements!=null) {
	            Iterator<Element> iterator = invalidDataElements.iterator();
	            while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	if(currentElement.getElementsByAttributeValueContaining("style", "Red")!=null && (currentElement.getElementsByAttributeValueContaining("style", "visible")!=null || currentElement.getElementsByAttributeValueContaining("style", "inline")!=null)) {
	            		throw(new WrongPostDataException(currentElement.html()));
	            	}
	            }
            }
	        System.out.println("----------------------------------------");
	        
	        //Ottengo il codice di inserzione dell'annuncio
	        if(responseBody.contains("ListingId=")) {
	        	//Get the insertion code
		        int start = responseBody.indexOf("Id=") + 3;
		        int end = responseBody.lastIndexOf("|");
		        CODICEINSERZIONE = responseBody.substring(start, end);
		        System.out.println("Codice inserzione: " + CODICEINSERZIONE);
		        System.out.println("----------------------------------------");
	        }
	        else {
	        	throw(new HttpResponseException("Response body"));
	        }
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    	
       
    //GET della pagina "Nuovo annuncio" (step 2)
    private void connessione_7(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 7");
    	HttpClient httpclient = new DefaultHttpClient();

        	//Request URL
            System.out.println("----------------------------------------");
            System.out.println("executing request " + httpget.getURI());
            System.out.println("----------------------------------------");
            
            //Request properties
            System.out.println("Request method: " + httpget.getMethod());
            System.out.println("Protocol version: " + httpget.getProtocolVersion());
            System.out.println("----------------------------------------");            
            
            //Add request headers
            BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
            httpget.addHeader(requestHeader0);

            //Show request headers
            Header[] requestHeaders;
            requestHeaders = httpget.getAllHeaders(); 
            System.out.println("Request headers: " + requestHeaders.length + "\n");
            for(int i=0; i<requestHeaders.length; i++) {
            	System.out.println(requestHeaders[i]);
            }
            System.out.println("----------------------------------------");  
            
            //Set the cookies
            BasicCookieStore cookieStore = new BasicCookieStore(); 
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
            cookie.setPath("/");
            
            cookieStore.addCookie(cookie); 
            ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

            //Send the request
            HttpResponse response = httpclient.execute(httpget);

            //Show response headers
            ResponseHandler<String> responseHandler = new BasicResponseHandler();           
            Header[] responseHeaders;
            responseHeaders = response.getAllHeaders(); 
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response headers: " + responseHeaders.length + "\n");
            for(int i=0; i<responseHeaders.length; i++) {
            	System.out.println(responseHeaders[i]);
            }
            System.out.println("----------------------------------------");
            
            //Show response body
            if(response.getStatusLine().toString().contains("200")) {
	            System.out.println("Response body: \n");
	            String responseBody = responseHandler.handleResponse(response);
	            System.out.println(responseBody);
	            System.out.println("----------------------------------------"); 
	            
	            //Parse HMTL to retrieve some informations
	            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);           
	            
	            Element __EVENTARGUMENTElement = doc.getElementById("__EVENTARGUMENT");
	            Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
	            Element __EVENTVALIDATIONElement = doc.getElementById("__EVENTVALIDATION");
	            Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");	            
	            if(__EVENTARGUMENTElement!=null && __EVENTTARGETElement!=null && __EVENTVALIDATIONElement!=null && __VIEWSTATEElement!=null) {
	            	__EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
	                System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
	                
	                __EVENTTARGET = __EVENTTARGETElement.attr("value");
	                System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
	                
	                __EVENTVALIDATION = __EVENTVALIDATIONElement.attr("value");
	                System.out.println("Value __EVENTVALIDATION: " + __EVENTVALIDATION);
	                
	                __VIEWSTATE = __VIEWSTATEElement.attr("value");
	                System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
	            }
	            else {
	            	throw(new HttpResponseException("Response body"));
	            }
	            System.out.println("----------------------------------------");
            }
            else {
            	throw(new HttpResponseException("Response code"));
            }            
                    
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
    }
    
        
    //POST dello step 2
    private void connessione_8(HttpPost httppost) throws ClientProtocolException, IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 8");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader0);
                   
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httppost.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Add request parameters
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("ctl00_ScriptManager1_HiddenField", ";;AjaxControlToolkit, Version=3.5.40412.0, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:it-IT:1547e793-5b7e-48fe-8490-03a375b13a33:de1feab2:f9cec9bc:a67c2700:f2c8e708:8613aea7:3202a5a2:ab09e3fe:87104b7c:be6fb298"));            
        postParameters.add(new BasicNameValuePair("__EVENTTARGET", "ctl00$cp$lbPubblica"));
        postParameters.add(new BasicNameValuePair("__EVENTARGUMENT", __EVENTARGUMENT));
        postParameters.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
        postParameters.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION));           
        postParameters.add(new BasicNameValuePair("ctl00$cp$fuPhotos", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$fuPlans", ""));
        postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$DDL_RequestType", ""));
        postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$TB_Message", ""));            
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
        cookie.setPath("/");
        
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
        }
        
        if(!response.getStatusLine().toString().contains("302")) {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
            
    
    //GET della pagina "Nuovo annuncio" (step 3)
    private void connessione_9(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 9");
    	HttpClient httpclient = new DefaultHttpClient();

        	//Request URL
            System.out.println("----------------------------------------");
            System.out.println("executing request " + httpget.getURI());
            System.out.println("----------------------------------------");
            
            //Request properties
            System.out.println("Request method: " + httpget.getMethod());
            System.out.println("Protocol version: " + httpget.getProtocolVersion());
            System.out.println("----------------------------------------");            
            
            //Add request headers
            BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
            httpget.addHeader(requestHeader0);

            //Show request headers
            Header[] requestHeaders;
            requestHeaders = httpget.getAllHeaders(); 
            System.out.println("Request headers: " + requestHeaders.length + "\n");
            for(int i=0; i<requestHeaders.length; i++) {
            	System.out.println(requestHeaders[i]);
            }
            System.out.println("----------------------------------------");  
            
            //Set the cookies
            BasicCookieStore cookieStore = new BasicCookieStore(); 
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
            cookie.setPath("/");
            
            cookieStore.addCookie(cookie); 
            ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

            //Send the request
            HttpResponse response = httpclient.execute(httpget);

            //Show response headers
            ResponseHandler<String> responseHandler = new BasicResponseHandler();           
            Header[] responseHeaders;
            responseHeaders = response.getAllHeaders(); 
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response headers: " + responseHeaders.length + "\n");
            for(int i=0; i<responseHeaders.length; i++) {
            	System.out.println(responseHeaders[i]);
            }
            System.out.println("----------------------------------------");
            
            //Show response body
            if(response.getStatusLine().toString().contains("200")) {
	            System.out.println("Response body: \n");
	            String responseBody = responseHandler.handleResponse(response);
	            System.out.println(responseBody);
	            System.out.println("----------------------------------------"); 
	            
	            //Parse HMTL to retrieve some informations
	            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);           

	            Element OKElement = doc.getElementById("ctl00_cp_P_OK");        
	            if(OKElement!=null) {
	            	System.out.println("Elemento di controllo inserimento: " + OKElement.html());
	            	INSERIMENTO_OK = true;
	            }
	            System.out.println("----------------------------------------");
            }
            else {
            	throw(new HttpResponseException("Response code"));
            }            
                    
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
    }
        
    
    //GET della pagina "Gestisci annunci"
    private void connessione_10(HttpGet httpget) throws ClientProtocolException, IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 10");
    	HttpClient httpclient = new DefaultHttpClient();

        	//Request URL
            System.out.println("----------------------------------------");
            System.out.println("executing request " + httpget.getURI());
            System.out.println("----------------------------------------");
            
            //Request properties
            System.out.println("Request method: " + httpget.getMethod());
            System.out.println("Protocol version: " + httpget.getProtocolVersion());
            System.out.println("----------------------------------------");            
            
            //Add request headers
            BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
            httpget.addHeader(requestHeader0);

            //Show request headers
            Header[] requestHeaders;
            requestHeaders = httpget.getAllHeaders(); 
            System.out.println("Request headers: " + requestHeaders.length + "\n");
            for(int i=0; i<requestHeaders.length; i++) {
            	System.out.println(requestHeaders[i]);
            }
            System.out.println("----------------------------------------");  
            
            //Set the cookies
            BasicCookieStore cookieStore = new BasicCookieStore(); 
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
            cookie.setPath("/");
            
            cookieStore.addCookie(cookie); 
            ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

            //Send the request
            HttpResponse response = httpclient.execute(httpget);

            //Show response headers
            ResponseHandler<String> responseHandler = new BasicResponseHandler();           
            Header[] responseHeaders;
            responseHeaders = response.getAllHeaders(); 
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response headers: " + responseHeaders.length + "\n");
            for(int i=0; i<responseHeaders.length; i++) {
            	System.out.println(responseHeaders[i]);
            }
            System.out.println("----------------------------------------");
            
            //Show response body
            if(response.getStatusLine().toString().contains("200")) {
                System.out.println("Response body: \n");
                String responseBody = responseHandler.handleResponse(response);
                System.out.println(responseBody);
                System.out.println("----------------------------------------");            
                
                //Parse HMTL to retrieve some informations
                org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);           
                
                Element __EVENTARGUMENTElement = doc.getElementById("__EVENTARGUMENT");
                Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
                Element __EVENTVALIDATIONElement = doc.getElementById("__EVENTVALIDATION");
                Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");
                
                if(__EVENTARGUMENTElement!=null && __EVENTTARGETElement!=null && __EVENTVALIDATIONElement!=null && __VIEWSTATEElement!=null) {
                	__EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
                    System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
                    
                    __EVENTTARGET = __EVENTTARGETElement.attr("value");
                    System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
                    
                    __EVENTVALIDATION = __EVENTVALIDATIONElement.attr("value");
                    System.out.println("Value __EVENTVALIDATION: " + __EVENTVALIDATION);
                    
                    __VIEWSTATE = __VIEWSTATEElement.attr("value");
                    System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
                }
                else {
                	throw(new HttpResponseException("Response body"));
                }
                System.out.println("----------------------------------------");
            }
            else {
            	throw(new HttpResponseException("Response code"));
            }         
                    
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina "Gestisci annunci" (cancellazione annuncio)
    private void connessione_11(HttpPost httppost) throws ClientProtocolException, IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 11");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader0);
                   
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httppost.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Add request parameters
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("__EVENTARGUMENT", CODICEINSERZIONE));
        postParameters.add(new BasicNameValuePair("__EVENTTARGET", "DEL"));
        postParameters.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION));
        postParameters.add(new BasicNameValuePair("__LASTFOCUS", __LASTFOCUS));           
        postParameters.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));           
        postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$DDL_RequestType", ""));
        postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$TB_Message", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$DDL_ListingActions", "1"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$DDL_Order", "7"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$DDL_PageSize", "20"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$DDL_Pages", "1"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$DDL_SortDirection", "2"));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_FilterRef", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_LocaliMax", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_LocaliMin", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_MqMax", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_MqMin", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_PriceMax", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$TB_PriceMin", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_LocaliMax_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_LocaliMin_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_MqMin_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_PriceMax_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_PriceMin_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00$cp$WM_TB_MqMin_ClientState", ""));
        postParameters.add(new BasicNameValuePair("ctl00_ScriptManager1_HiddenField", ";;AjaxControlToolkit, Version=3.5.40412.0, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:it-IT:1547e793-5b7e-48fe-8490-03a375b13a33:de1feab2:f2c8e708:720a52bf:f9cec9bc:589eaa30:698129cf:7a92f56c:35576c48:8ad18101:a67c2700:8613aea7:3202a5a2:ab09e3fe:87104b7c:be6fb298"));
        postParameters.add(new BasicNameValuePair("hiddenInputToUpdateATBuffer_CommonToolkitScripts", "1"));            
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
        cookie.setPath("/");
        
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
      
    //POST della pagina "Gestisci immagini" (step 2) - inserimento immagini
    private void connessione_12(URL url, File image) throws IOException {
    	System.out.println("CONNESSIONE 12");
    	System.out.println("Immagine: " + image.getName());  
    	System.out.println("URL: " + url);
  	
            //Impostazione della connessione alla risorsa
            httpRequest connessione = new httpRequest(url);
            
            //Definizione del request method
            connessione.setconnectionProperty("POST");
  
            //Inserimento cookie
            connessione.setCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            connessione.postCookies();
            
            //Inserimento parametri
            connessione.setParameter("ctl00_ScriptManager1_HiddenField", ";;AjaxControlToolkit, Version=3.5.40412.0, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:it-IT:1547e793-5b7e-48fe-8490-03a375b13a33:de1feab2:f9cec9bc:a67c2700:f2c8e708:8613aea7:3202a5a2:ab09e3fe:87104b7c:be6fb298");
            connessione.setParameter("__EVENTTARGET", "ctl00$cp$lbUploadPhoto");
            connessione.setParameter("__EVENTARGUMENT", __EVENTARGUMENT);
            
            connessione.setParameter("__VIEWSTATE", __VIEWSTATE);
            connessione.setParameter("__EVENTVALIDATION", __EVENTVALIDATION);
            
            connessione.setParameter("ctl00$cp$fuPhotos", image);
            connessione.setParameter("ctl00$cp$fuPlans", "");
            
            connessione.setParameter("ctl00$CustomerCare1$DDL_RequestType", "");
            connessione.setParameter("ctl00$CustomerCare1$TB_Message", "");           
                          
            //Invio della richiesta e stampa a video della risorsa creata
            System.out.println("\n\n######################\n\n");
            System.out.println("Stampa della risorsa...");
            BufferedReader rd1 = new BufferedReader(new InputStreamReader(connessione.post()));
            String line1;   //Per visualizzare la risorsa Web generata dalla connessione 5
            while ((line1 = rd1.readLine()) != null) {
              //Process line...
              System.out.println(line1);
            }
            System.out.println("\n\n######################\n\n");

            //Stampa delle proprietà  di connessione
            System.out.println("\nPropriet\u00e0  della chiamata...\n");         
            System.out.println("RISPOSTA: " + connessione.getResponse());
            System.out.println("METODO: " + connessione.getRequestMethod());                     
            connessione.getRequestHeaders();
            connessione.getResponseHeaders();             
        }
    
    
    //POST per ottenere i codici provincia
    private String connessione_13(HttpPost httppost, String provincia) throws ClientProtocolException, IOException, HttpResponseException, ParseException {
    	System.out.println("CONNESSIONE 13");
    	String nameProvincia="";
    	String valueProvincia="";
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        BasicHeader requestHeader6 = new BasicHeader("Content-Type", "application/json; charset=utf-8");
        httppost.addHeader(requestHeader0);
        httppost.addHeader(requestHeader6);
                   
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httppost.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Add request parameters
        JSONObject jsontoSent = new JSONObject();
        jsontoSent.put("category", "\"Province\"");
        jsontoSent.put("knownCategoryValues", "\"Nation:1;Region:" + ctl00$cp$ddlRegion + ";\"");
        StringEntity se = new StringEntity( jsontoSent.toString());
        httppost.setEntity(se);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
        cookie.setPath("/");
        
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
	        
	        JSONObject json = new JSONObject(responseBody);
	        JSONArray jsonResults = json.getJSONArray("d"); 
	        
	        System.out.println("JSON: " + jsonResults);
	        System.out.println("JSON: " + jsonResults.length());
	        double resultComparation = 0;
	        
	        for(int i=0; i<jsonResults.length(); i++) {
	        	JSONObject currentJson = jsonResults.getJSONObject(i);
	        	
	        	List<char[]> charListPortale = bigram(currentJson.getString("name"));
        		List<char[]> charListImagination = bigram(provincia);
        		
        		double actualResultComparation = dice(charListPortale, charListImagination);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			nameProvincia = currentJson.getString("name");            		
        			valueProvincia = currentJson.getString("value");
        		}       		
        		System.out.println("Risultato comparazione: " + resultComparation);        		        	
	        }
	        
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        return nameProvincia + "&&&" + valueProvincia;
    } 
    
    
    //POST per ottenere i codici comune
    private String connessione_14(HttpPost httppost, String region, String province, String comune) throws ClientProtocolException, IOException, HttpResponseException, ParseException {
    	System.out.println("CONNESSIONE 14");
    	String nameComune = "";
    	String valueComune = "";
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Add request headers
        BasicHeader requestHeader0 = new BasicHeader("User-Agent", USER_AGENT);
        BasicHeader requestHeader6 = new BasicHeader("Content-Type", "application/json; charset=utf-8");
        httppost.addHeader(requestHeader0);
        httppost.addHeader(requestHeader6);
                   
        //Show request headers
        Header[] requestHeaders;
        requestHeaders = httppost.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");

        //Add request parameters
        JSONObject jsontoSent = new JSONObject();
        jsontoSent.put("category", "\"City\"");
        jsontoSent.put("knownCategoryValues", "\"Nation:1;Region:" + region + ";Province:" + province + ";\",\"category\":\"City\"");
        StringEntity se = new StringEntity( jsontoSent.toString());
        httppost.setEntity(se);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN2);
        cookie.setPath("/");
        
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
	        
	        JSONObject json = new JSONObject(responseBody);
	        JSONArray jsonResults = json.getJSONArray("d"); 
	        
	        System.out.println("JSON: " + jsonResults);
	        System.out.println("JSON: " + jsonResults.length());
	        double resultComparation = 0;
	        
	        for(int i=0; i<jsonResults.length(); i++) {
	        	JSONObject currentJson = jsonResults.getJSONObject(i);
	        	
	        	List<char[]> charList1 = bigram(currentJson.getString("name"));
        		List<char[]> charList2 = bigram(comune);
        		
        		double actualResultComparation = dice(charList1, charList2);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			nameComune = currentJson.getString("name");            		
        			valueComune = currentJson.getString("value");
        		}       		
        		System.out.println("Risultato comparazione: " + resultComparation);
        		System.out.println("nameComune: " + nameComune);
        		System.out.println("valueComune: " + valueComune);
	        }	        
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        return nameComune + "&&&" + valueComune;
    }
         

    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public void inserisciScheda(SchedaImmobile scheda) {
    	System.out.println("Inserimento scheda: " + scheda.codice + "...");
    	
    	//Inizializzazione parametri
    	this.scheda=scheda;
    	
    	//Valorizzazione parametri da inviare
    	valutaParametri();
    	
    	//Ottengo le coordinate delal città nelal scheda immobile
    	try {
			getCoord();
		} catch (ParserConfigurationException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-6");
            e.printStackTrace();
		} catch (SAXException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-7");
            e.printStackTrace();
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-8");
            e.printStackTrace();
		}
        
           	
    	//Connessione 0
        try {
			connessione_0(new HttpGet(URL_ROOT + "/vendita"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 1
        try {
			connessione_1(new HttpGet(URL_ROOT + "/pubblica-annunci"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 2
        try {
			connessione_2(new HttpPost(URL_ROOT + "/pubblica-annunci"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (WrongPostDataException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-4 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server riporta errori nella compilazione delle form. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 3
        try {
			connessione_3(new HttpGet(LOCATION));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 4
        try {
			connessione_4(new HttpGet(LOCATION));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 5
        try {
			connessione_5(new HttpGet(SECUREURL_ROOT + "/modifylisting_step1.aspx"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}

        
        
        //Connessione 6
        try {
			connessione_6(new HttpPost(SECUREURL_ROOT + "/modifylisting_step1.aspx"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (WrongPostDataException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-4 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server riporta errori nella compilazione delle form. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 7
        try {
			connessione_7(new HttpGet(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Inserimeno immagini
        if(scheda.immagine1!=null) {
        	System.out.println("Inserimento immagine 1");
        	try {
				connessione_12(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine1);
			} catch (MalformedURLException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-5");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			} catch (IOException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
        if(scheda.immagine2!=null) {
        	System.out.println("Inserimento immagine 2");
        	try {
				connessione_12(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine2);
			} catch (MalformedURLException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-5");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			} catch (IOException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
        if(scheda.immagine3!=null) {
        	System.out.println("Inserimento immagine 3");
        	try {
				connessione_12(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine3);
			} catch (MalformedURLException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-5");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			} catch (IOException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
        if(scheda.immagine4!=null) {
        	System.out.println("Inserimento immagine 4");
        	try {
				connessione_12(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine4);
			} catch (MalformedURLException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-5");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			} catch (IOException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
        if(scheda.immagine5!=null) {
        	System.out.println("Inserimento immagine 5");
        	try {
				connessione_12(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine5);
			} catch (MalformedURLException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-5");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			} catch (IOException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
        if(scheda.immagine6!=null) {
        	System.out.println("Inserimento immagine 6");
        	try {
				connessione_12(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine6);
			} catch (MalformedURLException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-5");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			} catch (IOException e) {
				sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, immagine non inserita. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }


        
        //Connessione 8
        try {
			connessione_8(new HttpPost(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (WrongPostDataException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-4 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server riporta errori nella compilazione delle form. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
       
        
        
        //Connessione 9
        try {
			connessione_9(new HttpGet(SECUREURL_ROOT + "/modifylisting_step3.aspx?ListingId=" + CODICEINSERZIONE));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
  
		//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
    	if(INSERIMENTO_OK) {
    		System.out.println("Inserita in: " + NOMEPORTALE);
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, CODICEINSERZIONE);
            
        	//Aggiorna i pulsanti del pannello inserimento 	
        	Main.imaginationGUI.pannelloInserimento.updatePanello(scheda);
        	
        	//Invio mail di conferma inserimento 
        	sendConfirmationMail(scheda, NOMEPORTALE, CODICEINSERZIONE);
       	
        	//Stampo a video un messaggio informativo
            JOptionPane.showMessageDialog(null, "Scheda immobile inserita in: " + NOMEPORTALE, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else {
    		//Stampo a video un messaggio informativo
    		JOptionPane.showMessageDialog(null, "Problemi nell'inserimento scheda in: " + NOMEPORTALE + ".\n Verificare l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
    	}
      
	}
	
    
    //Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public void visualizzaScheda(SchedaImmobile scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codice + "...");
		//Apro il browser e inserisco credenziali		
		try {
			//String url = SECUREURL_ROOT  + "/modifylisting_step1.aspx?ListingId=" + scheda.getCodiceInserimento(idPortale);
			String url = "http://admin.casa.it/annuncifp.aspx";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante la visualizzazione della scheda. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
		}
		System.out.println("Visualizzata in: " + NOMEPORTALE);
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public void cancellaScheda(SchedaImmobile scheda) {
		System.out.println("Eliminazione scheda: " + scheda.codice + "...");
		CODICEINSERZIONE = scheda.getCodiceInserimento(idPortale);
	
		//Connessione 1
        try {
			connessione_1(new HttpGet(URL_ROOT + "/pubblica-annunci"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
			
        
        
		//Connessione 2 (login)
        try {
			connessione_2(new HttpPost(URL_ROOT + "/pubblica-annunci"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (WrongPostDataException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-4 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server riporta errori nella compilazione delle form. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 3
        try {
			connessione_3(new HttpGet(LOCATION));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
		
        
        
        //Connessione 10
        try {
			connessione_10(new HttpGet(SECUREURL_ROOT + "/annuncifp.aspx"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
        
        
        
        //Connessione 11
        try {
			connessione_11(new HttpPost(SECUREURL_ROOT + "/annuncifp.aspx"));
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		} catch (WrongPostDataException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-4 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server riporta errori nella compilazione delle form. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;         
		}
				
		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			
	
		//Aggiorno i pulsanti del pannello inserimento
		Main.imaginationGUI.pannelloInserimento.updatePanello(scheda);
		
		System.out.println("Eliminata da: " + NOMEPORTALE);
	
		//Stampo a video un messaggio informativo
        JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
	}
		
	
	//Metodo per la valutazione dei parametri
	public void valutaParametri() {

		System.out.println("--PARAMETRI INVIATI NELLA CONNESSIONE --");
		System.out.println("----------------------------------------");
		
		ctl00$cp$TB_Address = scheda.via;
		System.out.println("ctl00$cp$TB_Address: " + ctl00$cp$TB_Address);


	    switch (scheda.numBagni)
		{
		    case "1":
		    	ctl00$cp$TB_Bathrooms = "1";
		        break;
		    case "2":
		    	ctl00$cp$TB_Bathrooms = "2";
		    	break;
		    case "3":
		    	ctl00$cp$TB_Bathrooms = "3";
		    	break;
		    case "4":
		    	ctl00$cp$TB_Bathrooms = "4";
		    	break;
		    case "5":
		    	ctl00$cp$TB_Bathrooms = "5";
		    	break;
		    default:
		    	ctl00$cp$TB_Bathrooms = "";
		}
	    System.out.println("ctl00$cp$TB_Bathrooms: " + ctl00$cp$TB_Bathrooms);
    
    
	    ctl00$cp$TB_Cap = scheda.cap;
		System.out.println("ctl00$cp$TB_Cap: " + ctl00$cp$TB_Cap);
    
	
		ctl00$cp$TB_Price = Integer.toString(scheda.prezzo);
		System.out.println("ctl00$cp$TB_Price: " + ctl00$cp$TB_Price);
    	
		switch (scheda.numLocali)
		{
		    case "1":
		    	ctl00$cp$TB_RoomsNumber = "1";
		        break;
		    case "2":
		    	ctl00$cp$TB_RoomsNumber = "2";
		    	break;
		    case "3":
		    	ctl00$cp$TB_RoomsNumber = "3";
		    	break;
		    case "4":
		    	ctl00$cp$TB_RoomsNumber = "4";
		    	break;
		    case "5":
		    	ctl00$cp$TB_RoomsNumber = "5";
		    	break;
		    case "6":
		    	ctl00$cp$TB_RoomsNumber = "6";
		    	break;
		    case "7":
		    	ctl00$cp$TB_RoomsNumber = "7";
		    	break;
		    /*case ">7":
		    	ctl00$cp$TB_RoomsNumber = "";
		    	break;*/
		    default:
		    	ctl00$cp$TB_RoomsNumber = "";
		}
	    System.out.println("ctl00$cp$TB_RoomsNumber: " + ctl00$cp$TB_RoomsNumber);
	
    
	    ctl00$cp$TB_Surface = Integer.toString(scheda.supAbitazione);
		System.out.println("ctl00$cp$TB_Surface: " + ctl00$cp$TB_Surface);
    
	
		switch (scheda.tipologia)
		{
		    case "Appartamento":
		    	ctl00$cp$cddHouseTipology_ClientState = "4:::Appartamento:::";
		    	ctl00$cp$ddlTipology = "4";
		        break;
		    case "Attico":
		    	ctl00$cp$cddHouseTipology_ClientState = "5:::Attico:::";
		    	ctl00$cp$ddlTipology = "5";
		        break;
		    case "Bifamiliare":
		    	ctl00$cp$cddHouseTipology_ClientState = "9:::Casa Bi/Trifamiliare:::";
		    	ctl00$cp$ddlTipology = "9";
		        break;
		    case "Casa":
		    	ctl00$cp$cddHouseTipology_ClientState = "8:::Casa indipendente:::";
		    	ctl00$cp$ddlTipology = "8";
		        break;
		    case "Garage":
		    	ctl00$cp$cddHouseTipology_ClientState = "17:::Garage/Box auto:::";
		    	ctl00$cp$ddlTipology = "17";
		        break;
		    case "Palazzo":
		    	ctl00$cp$cddHouseTipology_ClientState = "7:::Immobile di prestigio:::";
		    	ctl00$cp$ddlTipology = "7";
		        break;
		    case "Rustico":
		    	ctl00$cp$cddHouseTipology_ClientState = "14:::Rustico/Casale:::";
		    	ctl00$cp$ddlTipology = "14";
		        break;
		    case "Terreno agricolo":
		    	ctl00$cp$cddHouseTipology_ClientState = "37:::Terreno:::";
		    	ctl00$cp$ddlTipology = "37";
		        break;
		    case "Terreno edificabile":
		    	ctl00$cp$cddHouseTipology_ClientState = "16:::Terreno edificabile:::";
		    	ctl00$cp$ddlTipology = "16";
		        break;
		    case "Villa":
		    	ctl00$cp$cddHouseTipology_ClientState = "12:::Villa:::";
		    	ctl00$cp$ddlTipology = "12";
		        break;
		    case "Villaschiera":
		    	ctl00$cp$cddHouseTipology_ClientState = "13:::Villetta a schiera:::";
		    	ctl00$cp$ddlTipology = "13";
		        break;
		    case "Agriturismo":
		    	ctl00$cp$cddHouseTipology_ClientState = "33:::Azienda agricola:::";
		    	ctl00$cp$ddlTipology = "33";
		        break;
		    case "Albergo":
		    	ctl00$cp$cddHouseTipology_ClientState = "30:::Albergo:::";
		    	ctl00$cp$ddlTipology = "30";
		        break;
		    case "Bar":
		    	ctl00$cp$cddHouseTipology_ClientState = "31:::Bar:::";
		    	ctl00$cp$ddlTipology = "31";
		        break;
		    case "Negozio":
		    	ctl00$cp$cddHouseTipology_ClientState = "20:::Negozio:::";
		    	ctl00$cp$ddlTipology = "20";
		        break;
		    case "Ristorante":
		    	ctl00$cp$cddHouseTipology_ClientState = "40:::Ristorante:::";
		    	ctl00$cp$ddlTipology = "40";
		        break;
		    case "Ufficio":
		    	ctl00$cp$cddHouseTipology_ClientState = "21:::Ufficio:::";
		    	ctl00$cp$ddlTipology = "21";
		        break;
		    case "Capannone":
		    	ctl00$cp$cddHouseTipology_ClientState = "19:::Capannone:::";
		    	ctl00$cp$ddlTipology = "19";
		        break;
		    case "Laboratorio":
		    	ctl00$cp$cddHouseTipology_ClientState = "24:::Laboratorio:::";
		    	ctl00$cp$ddlTipology = "24";
		        break;
		    case "Magazzino":
		    	ctl00$cp$cddHouseTipology_ClientState = "18:::Magazzino:::";
		    	ctl00$cp$ddlTipology = "18";
		        break;
		}
		System.out.println("ctl00$cp$cddHouseTipology_ClientState: " + ctl00$cp$cddHouseTipology_ClientState);
		System.out.println("ctl00$cp$ddlTipology: " + ctl00$cp$ddlTipology);
    
		switch (scheda.parcheggio)
		{
		    case "Nessuno":
		    	ctl00$cp$ddlBoxType = "0";
		        break;
		    case "Garage":
		    	ctl00$cp$ddlBoxType = "1";
		    	break;
		    case "Box auto":
		    	ctl00$cp$ddlBoxType = "1";
		    	break;
		    case "Posto auto":
		    	ctl00$cp$ddlBoxType = "3";
		    	break;
		    default:
		    	ctl00$cp$ddlBoxType = "-1";
		}
	    System.out.println("ctl00$cp$ddlBoxType: " + ctl00$cp$ddlBoxType);
    
	    switch (scheda.categoria)
		{
		    case "Residenziale":
		    	ctl00$cp$ddlBuildingType = "1";
		        break;
		    case "Commerciale":
		    	ctl00$cp$ddlBuildingType = "4";
		    	break;
		    case "Industriale":
		    	ctl00$cp$ddlBuildingType = "4";
		    	break;
		    default:
		    	ctl00$cp$ddlBuildingType = "0";
		}
	    System.out.println("ctl00$cp$ddlBuildingType: " + ctl00$cp$ddlBuildingType);
    
	    switch (scheda.contratto)
		{
		    case "Affitto":
		    	ctl00$cp$ddlContractType = "1";
		        break;
		    case "Vendita":
		    	ctl00$cp$ddlContractType = "2";
		    	break;
		    default:
		    	ctl00$cp$ddlContractType = "0";
		}
	    System.out.println("ctl00$cp$ddlContractType: " + ctl00$cp$ddlContractType);
    
	    switch (scheda.certificazioni)
		{
		    case "Nessuna":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "0";
		        break;
		    case "Certificazione energetica A++":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "1";
		    	break;
		    case "Certificazione energetica A+":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "1";
		    	break;
		    case "Certificazione energetica A":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "2";
		    	break;
		    case "Certificazione energetica B":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "3";
		    	break;
		    case "Certificazione energetica C":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "4";
		    	break;
		    case "Certificazione energetica D":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "5";
		    	break;
		    case "Certificazione energetica E":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "6";
		    	break;
		    case "Certificazione energetica F":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "7";
		    	break;
		    case "Certificazione energetica G":
		    	ctl00$cp$ddlEnergyEfficiencyRating = "8";
		    	break;
		    default:
		    	ctl00$cp$ddlEnergyEfficiencyRating = "9";
		}
	    System.out.println("ctl00$cp$ddlEnergyEfficiencyRating: " + ctl00$cp$ddlEnergyEfficiencyRating);
    
	    switch (scheda.giardino)
		{
		    case "Assente":
		    	ctl00$cp$ddlGardenType = "0";
		        break;
		    case "Giardino condominiale":
		    	ctl00$cp$ddlGardenType = "2";
		    	break;
		    case "Giardino ad uso esclusivo":
		    	ctl00$cp$ddlGardenType = "1";
		    	break;
		    default:
		    	ctl00$cp$ddlGardenType = "-1";
		}
	    System.out.println("ctl00$cp$ddlGardenType: " + ctl00$cp$ddlGardenType);
    
	    switch (scheda.riscaldamento)
		{
		    case "Assente":
		    	ctl00$cp$ddlHeatingType = "1";
		        break;
		    case "Centralizzato":
		    	ctl00$cp$ddlHeatingType = "3";
		    	break;
		    case "Autonomo":
		    	ctl00$cp$ddlHeatingType = "2";
		    	break;
		    case "Stufa":
		    	ctl00$cp$ddlHeatingType = "-1";
		    	break;
		    default:
		    	ctl00$cp$ddlHeatingType = "-1";
		}
	    System.out.println("ctl00$cp$ddlHeatingType: " + ctl00$cp$ddlHeatingType);
    
	    ctl00$cp$txtDescrIta = scheda.descrEstesa;
		System.out.println("ctl00$cp$txtDescrIta: " + ctl00$cp$txtDescrIta);
    
		String regione = scheda.regione;
		switch (regione)
		{
		    case "Abruzzo":
		    	ctl00$cp$cddRegion_ClientState = "13:::Abruzzo:::";
		    	ctl00$cp$ddlRegion = "13";
		    	paramRegione="Abruzzo";
		        break;
		    case "Basilicata":
		    	ctl00$cp$cddRegion_ClientState = "17:::Basilicata:::";
		    	ctl00$cp$ddlRegion = "17";
		    	paramRegione="Basilicata";
		    	break;
		    case "Calabria":
		    	ctl00$cp$cddRegion_ClientState = "18:::Calabria:::";
		    	ctl00$cp$ddlRegion = "18";
		    	paramRegione="Calabria";
		    	break;
		    case "Campania":
		    	ctl00$cp$cddRegion_ClientState = "15:::Campania:::";
		    	ctl00$cp$ddlRegion = "15";
		    	paramRegione="Campania";
		    	break;
		    case "Emilia-Romagna":
		    	ctl00$cp$cddRegion_ClientState = "08:::Emilia-Romagna:::";
		    	ctl00$cp$ddlRegion = "08";
		    	paramRegione="Emilia-Romagna";
		    	break;
		    case "Friuli-Venezia Giulia":
		    	ctl00$cp$cddRegion_ClientState = "06:::Friuli-Venezia Giulia:::";
		    	ctl00$cp$ddlRegion = "06";
		    	paramRegione="Friuli-Venezia Giulia";
		    	break;
		    case "Lazio":
		    	ctl00$cp$cddRegion_ClientState = "12:::Lazio:::";
		    	ctl00$cp$ddlRegion = "12";
		    	paramRegione="Lazio";
		    	break;
		    case "Liguria":
		    	ctl00$cp$cddRegion_ClientState = "07:::Liguria:::";
		    	ctl00$cp$ddlRegion = "07";
		    	paramRegione="Liguria";
		    	break;
		    case "Lombardia":
		    	ctl00$cp$cddRegion_ClientState = "03:::Lombardia:::";
		    	ctl00$cp$ddlRegion = "03";
		    	paramRegione="Lombardia";
		    	break;
		    case "Marche":
		    	ctl00$cp$cddRegion_ClientState = "11:::Marche:::";
		    	ctl00$cp$ddlRegion = "11";
		    	paramRegione="Marche";
		    	break;
		    case "Molise":
		    	ctl00$cp$cddRegion_ClientState = "14:::Molise:::";
		    	ctl00$cp$ddlRegion = "14";
		    	paramRegione="Molise";
		    	break;
		    case "Piemonte":
		    	ctl00$cp$cddRegion_ClientState = "01:::Piemonte:::";
		    	ctl00$cp$ddlRegion = "01";
		    	paramRegione="Piemonte";
		    	break;
		    case "Puglia":
		    	ctl00$cp$cddRegion_ClientState = "16:::Puglia:::";
		    	ctl00$cp$ddlRegion = "16";
		    	paramRegione="Puglia";
		    	break;
		    case "Sardegna":
		    	ctl00$cp$cddRegion_ClientState = "20:::Sardegna:::";
		    	ctl00$cp$ddlRegion = "20";
		    	paramRegione="Sardegna";
		    	break;
		    case "Sicilia":
		    	ctl00$cp$cddRegion_ClientState = "19:::Sicilia:::";
		    	ctl00$cp$ddlRegion = "19";
		    	paramRegione="Sicilia";
		    	break;
		    case "Toscana":
		    	ctl00$cp$cddRegion_ClientState = "09:::Toscana:::";
		    	ctl00$cp$ddlRegion = "09";
		    	paramRegione="Toscana";
		    	break;
		    case "Trentino-Alto Adige":
		    	ctl00$cp$cddRegion_ClientState = "04:::Trentino-Alto Adige:::";
		    	ctl00$cp$ddlRegion = "04";
		    	paramRegione="Trentino-Alto Adige";
		    	break;
		    case "Umbria":
		    	ctl00$cp$cddRegion_ClientState = "10:::Umbria:::";
		    	ctl00$cp$ddlRegion = "10";
		    	paramRegione="Umbria";
		    	break;
		    case "Valle d'Aosta":
		    	ctl00$cp$cddRegion_ClientState = "02:::Valle D'Aosta:::";
		    	ctl00$cp$ddlRegion = "02";
		    	paramRegione="Valle D'Aosta";
		    	break;
		    case "Veneto":
		    	ctl00$cp$cddRegion_ClientState = "05:::Veneto:::";
		    	ctl00$cp$ddlRegion = "05";
		    	paramRegione="Veneto";
		    	break;
		    default:
		    	ctl00$cp$cddRegion_ClientState = "06:::Friuli-Venezia Giulia:::";
		    	ctl00$cp$ddlRegion = "06";
		    	paramRegione="Friuli-Venezia Giulia";
		}		
		System.out.println("ctl00$cp$cddRegion_ClientState: " + ctl00$cp$cddRegion_ClientState);
		System.out.println("ctl00$cp$ddlRegion: " + ctl00$cp$ddlRegion);
		System.out.println("paramRegione: " + paramRegione);
	   
		String provincia = scheda.provincia;
		String GetProvinces = "";
		//Connessione 13
	    try {
	    	GetProvinces = connessione_13(new HttpPost("http://admin.casa.it/GeoDropDownService.asmx/GetProvinces"), provincia);
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
	        JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	        JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
	        JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;         
		} catch (ParseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-5 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda.", "Errore I030-1-5", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}   
	    String nameProvincia = GetProvinces.substring(0, GetProvinces.indexOf("&&&"));
	    String valueProvincia = GetProvinces.substring(GetProvinces.indexOf("&&&")+3, GetProvinces.length());
	    ctl00$cp$cddProvince_ClientState = nameProvincia + ":::" + valueProvincia + ":::";
	    ctl00$cp$ddlProvince = valueProvincia;
	    System.out.println("ctl00$cp$cddProvince_ClientState: " + ctl00$cp$cddProvince_ClientState);
	    System.out.println("ctl00$cp$ddlProvince: " + ctl00$cp$ddlProvince);
        
		String comune = scheda.comune;
	    String GetCities = "";
		//Connessione 14
	    try {
	    	GetCities = connessione_14(new HttpPost("http://admin.casa.it/GeoDropDownService.asmx/GetCities"), ctl00$cp$ddlRegion, valueProvincia, comune);
		} catch (ClientProtocolException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-1");
	        JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-1", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-2");
	        JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, verificare la connessione ad Internet. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-2", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;         
		} catch (HttpResponseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-3 - " + e.getMessage());
	        JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della scheda, il server ha risposto in una maniera non prevista. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-1-3", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;         
		} catch (ParseException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-1-5 - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda.", "Errore I030-1-5", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}   
	    String nameComune = GetCities.substring(0, GetCities.indexOf("&&&"));
	    String valueComune = GetCities.substring(GetCities.indexOf("&&&")+3, GetCities.length());
	    ctl00$cp$cddCity_ClientState = valueComune + ":::" + nameComune + ":::";
	    ctl00$cp$ddlCity = valueComune;
	    System.out.println("ctl00$cp$cddCity_ClientState: " + ctl00$cp$cddCity_ClientState);
	    System.out.println("ctl00$cp$ddlCity: " + ctl00$cp$ddlCity);
	    
	    ctl00$cp$TB_BuildingAge = scheda.annoCostr;
		System.out.println("ctl00$cp$TB_BuildingAge: " + ctl00$cp$TB_BuildingAge);
		
		ctl00$cp$TB_TotLevel = scheda.totPiani;
		System.out.println("ctl00$cp$TB_TotLevel: " + ctl00$cp$TB_TotLevel);
		
		switch (scheda.statoImmobile)
    	{
    	    case "Nuovo":
    	    	ctl00$cp$ddlBuildingConditions = "2";
    	        break;
    	    case "Ristrutturato":
    	    	ctl00$cp$ddlBuildingConditions = "3";
    	    	break;
    	    case "Da ristrutturare":
    	    	ctl00$cp$ddlBuildingConditions = "4";
    	    	break;
    	    case "In buono stato":
    	    	ctl00$cp$ddlBuildingConditions = "1";
    	    	break;
    	    case "Abitabile":
    	    	ctl00$cp$ddlBuildingConditions = "1";
    	    	break;
    	    case "Ottimo":
    	    	ctl00$cp$ddlBuildingConditions = "1";
    	    	break;
    	    case "In costruzione":
    	    	ctl00$cp$ddlBuildingConditions = "-1";
    	    	break;
    	    default:
    	    	ctl00$cp$ddlBuildingConditions = "-1";
    	}
        System.out.println("ctl00$cp$ddlBuildingConditions: " + ctl00$cp$ddlBuildingConditions);
        
        ctl00$cp$ddlLevel = scheda.piano;
		System.out.println("ctl00$cp$ddlLevel: " + ctl00$cp$ddlLevel);
		
		ctl00$cp$TB_Ref = scheda.codice;
		System.out.println("ctl00$cp$TB_Ref: " + ctl00$cp$TB_Ref);
	}             
	
	
	//Metodo per ottenere le coordinate della città
	public void getCoord() throws ParserConfigurationException, SAXException, IOException {
		Document doc = null;
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
		db = dbf.newDocumentBuilder();    
		doc = db.parse("http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=" + ctl00$cp$TB_Address + "%20" + ctl00$cp$ddlCity + "%20" + ctl00$cp$ddlProvince + "%20" + paramRegione + "%20" + "Italia");
        
		doc.getDocumentElement().normalize();
		
        System.out.println("Root element " + doc.getDocumentElement().getNodeName());
        NodeList nodeLstLat = doc.getElementsByTagName("lat");       
        Node lat = nodeLstLat.item(1);
        NodeList nodeLstLon = doc.getElementsByTagName("lng");       
        Node lon = nodeLstLon.item(1);
               
        System.out.println("Latitudine e longitudine: " + lat.getTextContent() + " - " + lon.getTextContent());
        
        latitudine = lat.getTextContent();
        longitudine = lon.getTextContent();
		
	}


}