/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author marco
 */

//La classe principale
public class BancaDelleCase extends PortaleImmobiliare {     

    //Parametri generali
	private final String NOMEPORTALE = "bancadellecase.it";
	private final String URL_ROOT = "http://www.bancadellecase.it";
	private final String USERNAME = "info@cividalecasa.it";
    private final String PASSWORD = "9ifphv";
    private final String IDAGENZIA = "2013";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:12.0) Gecko/20100101 Firefox/12.0";
    private String SESSIONCOOKIE_HEADER = "";
    private String SESSIONCOOKIE_NAME = "";
    private String SESSIONCOOKIE_VALUE = "";
    private String SESSIONCOOKIE_DOMAIN = ".bancadellecase.it";
    private String LOCATION = "";
    private String CODICEINSERZIONE;
    private boolean INSERIMENTO_OK = false;
    
    //Parametri scheda (elaborati in base al portale)
    String annuncio = "";
    String catalogazione = "";
    String categoria = "";
    String codice = "";
    String codiceProvincia = "";
    String stato = "it"; //opzione di default
    String codiceRegione = "";
    String contratto = "";
    String dettagli_3 = "";
    String dettagli_5 = "";
    String istatComune = "";
    String mq = "";
    String prezzo = "";
    String tipologia = "";
    String titolo = "";
    String via = ""; 
    String annoCostruzione ="";
    String dettagli10="";
    String dettagli11="";
    String dettagli14="";
    String dettagli16="";
    String dettagli6="";
    String dettagli8="";
    String piano = "";
    String dettagli_13="";
    
    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   
    
    
	//Costruttore
	public BancaDelleCase (String urlIcona, String valoreLabel, String idPortale) {		
		super(urlIcona, valoreLabel, idPortale);			
	}

	
	//GET della home page
	private void connessione_0(HttpGet httpget) throws IOException, HttpResponseException {
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
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    	
    //POST della home page per il login
	private void connessione_1(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 1");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("lang", "it"));
        postParameters.add(new BasicNameValuePair("pwd", PASSWORD));
        postParameters.add(new BasicNameValuePair("usr", USERNAME));                
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
        	//Get session cookie
        	//Get the redirect location
        	if(currentHeader.getName().contains("Location")) {
        		//Location trovata
        		locationHeaderFound = true;
        		LOCATION = currentHeader.getValue();
        		System.out.println("Location value: " + LOCATION);
        	}
        	//Get the redirect location
        }        
        //Se non trovo le intestazioni che cerco
    	if(!cookieHeaderFound || !locationHeaderFound) {
    		throw(new HttpResponseException("HTTP response header"));
    	}
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        System.out.println("----------------------------------------");
	        throw(new WrongPostDataException("Login input data"));	        
        }
        if(!response.getStatusLine().toString().contains("302")) {
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //GET della pagina Area di amministrazione
	private void connessione_2(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 2");
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
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        	throw(new HttpResponseException("HTTP response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
	
	
	
	//GET della pagina Inserisci un nuovo annuncio (step 1)
	private void connessione_3(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 3");
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
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        	throw(new HttpResponseException("HTTP response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
	
    
	//POST della pagina Inserisci un nuovo annuncio (step 1)
    private void connessione_4(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 4");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("LatLng", "  , "));
        postParameters.add(new BasicNameValuePair("annuncio", annuncio));
        postParameters.add(new BasicNameValuePair("canone[]", "")); //non viene mai fuori... bho...
        postParameters.add(new BasicNameValuePair("catalogazione", catalogazione));
        postParameters.add(new BasicNameValuePair("categoria", categoria));
        postParameters.add(new BasicNameValuePair("civico", ""));
        postParameters.add(new BasicNameValuePair("codice", codice));
        postParameters.add(new BasicNameValuePair("codiceProvincia", codiceProvincia));
        postParameters.add(new BasicNameValuePair("codiceRegione", codiceRegione));
        postParameters.add(new BasicNameValuePair("contratto", contratto));
        postParameters.add(new BasicNameValuePair("dataA[]", ""));//non viene mai fuori... bho...
        postParameters.add(new BasicNameValuePair("dataDa[]", ""));//non viene mai fuori... bho...
        postParameters.add(new BasicNameValuePair("del_ids", ""));
        postParameters.add(new BasicNameValuePair("descPrezzo", ""));
        postParameters.add(new BasicNameValuePair("dettagli[3]", dettagli_3));
        postParameters.add(new BasicNameValuePair("dettagli[5]", dettagli_5));
        postParameters.add(new BasicNameValuePair("dettagli[13]", dettagli_13));
        postParameters.add(new BasicNameValuePair("dettagli[]", "")); //non presente in dom ma presente nella post
        postParameters.add(new BasicNameValuePair("idAnnuncio", ""));//vuoto di default
        postParameters.add(new BasicNameValuePair("ipe", ""));
        postParameters.add(new BasicNameValuePair("istatComune", istatComune));
        postParameters.add(new BasicNameValuePair("lingua", "it"));
        postParameters.add(new BasicNameValuePair("localita", ""));
        postParameters.add(new BasicNameValuePair("mq", mq));
        postParameters.add(new BasicNameValuePair("note[]", ""));
        postParameters.add(new BasicNameValuePair("prezzo", prezzo));
        postParameters.add(new BasicNameValuePair("stato", stato));
        postParameters.add(new BasicNameValuePair("tipologia", tipologia));
        postParameters.add(new BasicNameValuePair("titolo", titolo));
        postParameters.add(new BasicNameValuePair("via", via));
        postParameters.add(new BasicNameValuePair("visIndirizzo", "1")); //Indirizzo sempre visibile
        postParameters.add(new BasicNameValuePair("visPrezzo", "0")); //Prezzo sempre visibile
        postParameters.add(new BasicNameValuePair("zona", ""));
        postParameters.add(new BasicNameValuePair("zoomLvl", "12"));
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);              	       	       
            Elements locationDataElements = doc.getElementsByTag("script");
            Iterator<Element> iterator = locationDataElements.iterator();
        	while(iterator.hasNext()) {
            	Element currentElement = iterator.next();
            	if(currentElement.html().contains("window.location.href")) {
            		String scriptContent = locationDataElements.html();
            		int start = scriptContent.indexOf("?idAnnuncio=") + 12;
    		        int end = scriptContent.lastIndexOf("&step=");
    		        CODICEINSERZIONE = scriptContent.substring(start, end);
    		        System.out.println("Codice inserzione: " + CODICEINSERZIONE);
            	}
		        System.out.println("----------------------------------------");
            }
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    	
       
    //GET della pagina "Dettagli immobile" (step 2)
    private void connessione_5(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 5");
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
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }            
                
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
        
    //POST della pagina "Dettagli immobile" (step 2)
    private void connessione_6(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 6");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("annoCostruzione", annoCostruzione));
        postParameters.add(new BasicNameValuePair("dettagli[10]", dettagli10));
        postParameters.add(new BasicNameValuePair("dettagli[11]", dettagli11));
        postParameters.add(new BasicNameValuePair("dettagli[12]", ""));
        postParameters.add(new BasicNameValuePair("dettagli[14]", dettagli14));
        postParameters.add(new BasicNameValuePair("dettagli[16]", dettagli16));
        postParameters.add(new BasicNameValuePair("dettagli[1]", ""));
        postParameters.add(new BasicNameValuePair("dettagli[2]", "")); //il  piano è difficile da mettere
        postParameters.add(new BasicNameValuePair("dettagli[4]", ""));
        postParameters.add(new BasicNameValuePair("dettagli[6]", dettagli6));
        postParameters.add(new BasicNameValuePair("dettagli[8]", dettagli8));
        postParameters.add(new BasicNameValuePair("dettagli[9]", ""));
        postParameters.add(new BasicNameValuePair("finiture", ""));
        postParameters.add(new BasicNameValuePair("idAnnuncio", CODICEINSERZIONE));
        postParameters.add(new BasicNameValuePair("piano", piano)); //tot piani...          
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        if(response.getStatusLine().toString().contains("200")) { //le informazioni di redirect sono già desumibili
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);	        
	        System.out.println("----------------------------------------");
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
            
    
    //GET della pagina "Foto e Planimetrie" (step 7)
    private void connessione_7(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 7");
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
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }            
                
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
        
    //POST della pagina "Foto e Planimetrie" (step 3)
    private void connessione_8(HttpPost httppost, File image) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 8");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httppost.getURI());
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
        System.out.println("Inserimento immagine: " + image.getName());        
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("Filename", new StringBody(image.getName()));                      
		FileBody bin = new FileBody(image);
		reqEntity.addPart("Filedata", bin); 		
		reqEntity.addPart("Upload", new StringBody("Submit Query"));		
        httppost.setEntity(reqEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        if(response.getStatusLine().toString().contains("200")) { //le informazioni di redirect sono già desumibili
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);	        
	        System.out.println("----------------------------------------");
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    
    //GET della pagina "Video" (step 4)
    private void connessione_9(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 9");
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
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }            
                
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    
    //GET della pagina "Lista annunci" (controllo l'inserzione dell'annuncio)
    private void connessione_10(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 10");
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
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
            
            //Verifico l'inserzione
	        if(responseBody.contains("?idAnnuncio=" + CODICEINSERZIONE)) {
	        	INSERIMENTO_OK = true;
	        }
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }            
                
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina "Lista annunci" (eliminazione annuncio)
    private void connessione_11(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 11");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("delete_" + CODICEINSERZIONE, "Elimina"));          
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST per ottenere i codici provincia
    private String connessione_12(HttpPost httppost, String utenteProvincia) throws IOException, HttpResponseException, ParseException {
    	System.out.println("CONNESSIONE 12");
    	String valueParametroProvincia="";
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
        postParameters.add(new BasicNameValuePair("el", "provincia"));
        postParameters.add(new BasicNameValuePair("val", codiceRegione));      
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
	        
	        org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);    
            //Ottengo il valore del parametro Provincia
            Elements optionElements = doc.getElementsByTag("option");
            if(optionElements.isEmpty()) {
            	throw(new HttpResponseException("Codice provincia input data"));
            }
            else {
            	Iterator<Element> iterator = optionElements.iterator();
            	double resultComparation = 0;
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	List<char[]> charListPortale = bigram(currentElement.html());
	        		List<char[]> charListImagination = bigram(utenteProvincia);
	        		double actualResultComparation = dice(charListPortale, charListImagination);
	        		if(actualResultComparation>=resultComparation) {
	        			resultComparation = actualResultComparation;
	        			valueParametroProvincia = currentElement.attr("value");            		
	        		}       		
	        		System.out.println("Risultato comparazione: " + resultComparation);
            	}
            } 
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        return valueParametroProvincia;
    }
    
    
    
    //POST per ottenere i codici comune
    private String connessione_13(HttpPost httppost, String utenteComune) throws IOException, HttpResponseException, ParseException {
    	System.out.println("CONNESSIONE 13");
    	String valueParametroistatComune="";
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
        postParameters.add(new BasicNameValuePair("el", "comune"));
        postParameters.add(new BasicNameValuePair("val", codiceProvincia));      
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        BasicCookieStore cookieStore = new BasicCookieStore(); 
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
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
	        
	        org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);              
            //Ottengo il valore del parametro comune
            Elements optionElements = doc.getElementsByTag("option");
            if(optionElements.isEmpty()) {
            	throw(new HttpResponseException("Codice comune input data"));
            }
            else {
            	Iterator<Element> iterator = optionElements.iterator();
            	double resultComparation = 0;
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	List<char[]> charListPortale = bigram(currentElement.html());
	        		List<char[]> charListImagination = bigram(utenteComune);
	        		double actualResultComparation = dice(charListPortale, charListImagination);
	        		if(actualResultComparation>=resultComparation) {
	        			resultComparation = actualResultComparation;
	        			valueParametroistatComune = currentElement.attr("value");            		
	        		}       		
	        		System.out.println("Risultato comparazione: " + resultComparation);
            	}
            } 
        }
        else {
        	throw(new HttpResponseException("HTTP response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        return valueParametroistatComune;
    }

   

    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public void inserisciScheda(SchedaImmobile scheda) {
    	System.out.println("Inserimento scheda: " + scheda.codice + "...");
    	
    	//Inizializzazione parametri
    	this.scheda=scheda;

    	//Connessione 0 - GET della home page
        try {
			connessione_0(new HttpGet(URL_ROOT));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 1);
            return;
		}
		
   
        //Connessione 1 - POST della home page (login)
        try {
			connessione_1(new HttpPost(URL_ROOT));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 2);
            return;
		}
               
        
        //Connessione 2 - GET della pagina Area di amministrazione
        try {
			connessione_2(new HttpGet(LOCATION));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
               
        
        //Connessione 3 - GET della pagina Inserisci un nuovo annuncio (step 1)
        try {
			connessione_3(new HttpGet(URL_ROOT + "/admin/inserimento.php"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        //Valorizzazione parametri da inviare
    	valutaParametri();
               
        
        //Connessione 4 - POST della pagina Inserisci un nuovo annuncio (step 1)
    	try {
			connessione_4(new HttpPost(URL_ROOT + "/admin/inserimento.php"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
            return;
		}
               
        
        //Connessione 5 - GET della pagina "Dettagli immobile" (step 2)
        try {
			connessione_5(new HttpGet(URL_ROOT + "/admin/inserimento.php?idAnnuncio=" + CODICEINSERZIONE + "&step=2"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        //Connessione 6 - POST della pagina "Dettagli immobile" (step 2)
        try {
			connessione_6(new HttpPost(URL_ROOT + "/admin/inserimento.php?idAnnuncio=" + CODICEINSERZIONE + "&step=2"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        //Connessione 7 - GET della pagina "Foto e Planimetrie" (step 3)
        try {
			connessione_7(new HttpGet(URL_ROOT + "/admin/inserimento.php?idAnnuncio=" + CODICEINSERZIONE + "&step=3"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        //Connessione 8 - POST della pagina "Foto e Planimetrie" (step 3)
        if(scheda.immagine1!=null) {
        	System.out.println("Inserimento immagine 1");
        	try {
    			connessione_8(new HttpPost(URL_ROOT + "/admin/inserimento/upload.php?idAgenzia=" + IDAGENZIA + "&idAnnuncio=" + CODICEINSERZIONE + "&tipo=img?folder=/admin/"), scheda.immagine1);
    		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
    			manageErrors(e, 3);
                return;
    		}
        }
        if(scheda.immagine2!=null) {
        	System.out.println("Inserimento immagine 2");
        	try {
    			connessione_8(new HttpPost(URL_ROOT + "/admin/inserimento/upload.php?idAgenzia=" + IDAGENZIA + "&idAnnuncio=" + CODICEINSERZIONE + "&tipo=img?folder=/admin/"), scheda.immagine2);
    		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
    			manageErrors(e, 3);
                return;
    		}
        }
        if(scheda.immagine3!=null) {
        	System.out.println("Inserimento immagine 3");
        	try {
    			connessione_8(new HttpPost(URL_ROOT + "/admin/inserimento/upload.php?idAgenzia=" + IDAGENZIA + "&idAnnuncio=" + CODICEINSERZIONE + "&tipo=img?folder=/admin/"), scheda.immagine3);
    		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
    			manageErrors(e, 3);
                return;
    		}
        }
        if(scheda.immagine4!=null) {
        	System.out.println("Inserimento immagine 4");
        	try {
    			connessione_8(new HttpPost(URL_ROOT + "/admin/inserimento/upload.php?idAgenzia=" + IDAGENZIA + "&idAnnuncio=" + CODICEINSERZIONE + "&tipo=img?folder=/admin/"), scheda.immagine4);
    		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
    			manageErrors(e, 3);
                return;
    		}
        }
        if(scheda.immagine5!=null) {
        	System.out.println("Inserimento immagine 5");
        	try {
    			connessione_8(new HttpPost(URL_ROOT + "/admin/inserimento/upload.php?idAgenzia=" + IDAGENZIA + "&idAnnuncio=" + CODICEINSERZIONE + "&tipo=img?folder=/admin/"), scheda.immagine5);
    		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
    			manageErrors(e, 3);
                return;
    		}
        }
        if(scheda.immagine6!=null) {
        	System.out.println("Inserimento immagine 6");
        	try {
    			connessione_8(new HttpPost(URL_ROOT + "/admin/inserimento/upload.php?idAgenzia=" + IDAGENZIA + "&idAnnuncio=" + CODICEINSERZIONE + "&tipo=img?folder=/admin/"), scheda.immagine6);
    		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
    			manageErrors(e, 3);
                return;
    		}
        }
        
        
        
        //Connessione 9 - GET della pagina "Video" (step 4)
        try {
			connessione_9(new HttpGet(URL_ROOT + "/admin/inserimento.php?idAnnuncio=" + CODICEINSERZIONE + "&step=4"));
		}catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessione 10 - GET della pagina "Lista annunci" (controllo l'inserzione dell'annuncio)
        try {
			connessione_10(new HttpGet(URL_ROOT + "/admin/lista_annunci.php"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
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
			String url = URL_ROOT + "/admin/lista_annunci.php";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e ) {
			manageErrors(e, 3);
            return;
		}
		System.out.println("Visualizzata in: " + NOMEPORTALE);
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public void cancellaScheda(SchedaImmobile scheda) {
		System.out.println("Eliminazione scheda: " + scheda.codice + "...");
		CODICEINSERZIONE = scheda.getCodiceInserimento(idPortale);
	
		//Connessione 1 - POST della home page (login)
        try {
			connessione_1(new HttpPost(URL_ROOT));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
            return;
		}
			
        
        
        //Connessione 11 - POST della pagina "Lista annunci" (eliminazione annuncio)
        try {
			connessione_11(new HttpPost(URL_ROOT + "/admin/lista_annunci.php"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
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
		
		//La regione inserita dall'utente
		String utenteRegione = scheda.regione;
		
		//La provincia inserita dall'utente
		String utenteProvincia = scheda.provincia;
		
		//Il comune inserito dall'utente
		String utenteComune = scheda.comune;
				
		System.out.println("--PARAMETRI INVIATI NELLA INSERZIONE DELL'ANNUNCIO--");
		System.out.println("----------------------------------------");
				
		annuncio = scheda.descrEstesa;
		//Problemi con l'encoding...
		annuncio = annuncio.replace("à", "a'");
		annuncio = annuncio.replace("è", "Ã¨");
		annuncio = annuncio.replace("ì", "Ã¬");
		annuncio = annuncio.replace("ò", "Ã²");
		annuncio = annuncio.replace("ù", "Ã¹");
		annuncio = annuncio.replace("€", "â‚¬");
		annuncio = annuncio.replace("&", "Â£ ");
		System.out.println("annuncio: " + annuncio);
		
		switch (scheda.numLocali)
		{
		    case "1":
		    	catalogazione = "1";
		        break;
		    case "2":
		    	catalogazione = "2";
		    	break;
		    case "3":
		    	catalogazione = "3";
		    	break;
		    case "4":
		    	catalogazione = "4";
		    	break;
		    case "5":
		    	catalogazione = "5";
		    	break;
		    case "6":
		    	catalogazione = "5";
		    	break;
		    default:
		    	catalogazione = "5";
		}
	    System.out.println("catalogazione: " + catalogazione);
	    
	    switch (scheda.categoria)
		{
		    case "Residenziale":
		    	categoria = "1";
		        break;
		    case "Commerciale":
		    	categoria = "2";
		    	break;
		    case "Industriale":
		    	categoria = "2";
		    	break;
		    default:
		    	categoria = "4";
		}
	    System.out.println("categoria: " + categoria);
	    
	    codice = scheda.codice;
		System.out.println("codice: " + codice);
		
		switch (utenteRegione)
		{
		    case "Abruzzo":
		    	codiceRegione = "13";
		        break;
		    case "Basilicata":
		    	codiceRegione = "17";
		    	break;
		    case "Calabria":
		    	codiceRegione = "18";
		    	break;
		    case "Campania":
		    	codiceRegione = "15";
		    	break;
		    case "Emilia-Romagna":
		    	codiceRegione = "08";
		    	break;
		    case "Friuli-Venezia Giulia":
		    	codiceRegione = "06";
		    	break;
		    case "Lazio":
		    	codiceRegione = "12";
		    	break;
		    case "Liguria":
		    	codiceRegione = "07";
		    	break;
		    case "Lombardia":
		    	codiceRegione = "03";
		    	break;
		    case "Marche":
		    	codiceRegione = "11";
		    	break;
		    case "Molise":
		    	codiceRegione = "14";
		    	break;
		    case "Piemonte":
		    	codiceRegione = "01";
		    	break;
		    case "Puglia":
		    	codiceRegione = "16";
		    	break;
		    case "Sardegna":
		    	codiceRegione = "20";
		    	break;
		    case "Sicilia":
		    	codiceRegione = "19";
		    	break;
		    case "Toscana":
		    	codiceRegione = "09";
		    	break;
		    case "Trentino-Alto Adige":
		    	codiceRegione = "04";
		    	break;
		    case "Umbria":
		    	codiceRegione = "10";
		    	break;
		    case "Valle d'Aosta":
		    	codiceRegione = "02";
		    	break;
		    case "Veneto":
		    	codiceRegione = "05";
		    	break;
		    default:
		    	codiceRegione = "06";
		}		
		System.out.println("codiceRegione: " + codiceRegione);
		
		//Connessione 12 - POST per ottenere il codice provincia		
        try {
        	codiceProvincia = connessione_12(new HttpPost(URL_ROOT + "/admin/inc/popolaSelect.php"), utenteProvincia);
		} catch (IOException | HttpResponseException | ParseException e ) {
			manageErrors(e, 3);
            return;
		}  
        System.out.println("codiceProvincia: " + codiceProvincia);
        
        switch (scheda.contratto)
		{
		    case "Affitto":
		    	contratto = "1";
		        break;
		    case "Vendita":
		    	contratto = "0";
		    	break;
		    default:
		    	contratto = "0";
		}
	    System.out.println("contratto: " + contratto);
	    
	    switch (scheda.numBagni)
		{
		    case "1":
		    	dettagli_3 = "40";
		        break;
		    case "2":
		    	dettagli_3 = "41";
		    	break;
		    case "3":
		    	dettagli_3 = "42";
		    	break;
		    case "4":
		    	dettagli_3 = "43";
		    	break;
		    case "5":
		    	dettagli_3 = "44";
		    	break;
		    default:
		    	dettagli_3 = "45";
		}
	    System.out.println("dettagli_3: " + dettagli_3);
	    
	    switch (scheda.numCamere)
		{
		    case 1:
		    	dettagli_5 = "50";
		        break;
		    case 2:
		    	dettagli_5 = "51";
		    	break;
		    case 3:
		    	dettagli_5 = "52";
		    	break;
		    case 4:
		    	dettagli_5 = "53";
		    	break;
		    case 5:
		    	dettagli_5 = "54";
		    	break;
		    default:
		    	dettagli_5 = "55";
		}
	    System.out.println("dettagli_5: " + dettagli_5);
	    
	    //Connessione 13 - POST per ottenere il codice comune
          try {
          	istatComune = connessione_13(new HttpPost(URL_ROOT + "/admin/inc/popolaSelect.php"), utenteComune);
  		} catch (IOException | HttpResponseException | ParseException e ) {
			manageErrors(e, 3);
            return;
		}
        System.out.println("istatComune: " + istatComune);
        
        mq = Integer.toString(scheda.supAbitazione);
		System.out.println("mq: " + mq);
		
		prezzo = Integer.toString(scheda.prezzo);
		System.out.println("prezzo: " + prezzo);
		
		switch (scheda.tipologia)
		{
		    case "Appartamento":
		    	tipologia = "1";
		        break;
		    case "Attico":
		    	tipologia = "2";
		        break;
		    case "Bifamiliare":
		    	tipologia = "3";
		        break;
		    case "Casa":
		    	tipologia = "4";
		        break;
		    case "Garage":
		    	tipologia = "22";
		        break;
		    case "Palazzo":
		    	tipologia = "7";
		        break;
		    case "Rustico":
		    	tipologia = "6";
		        break;
		    case "Terreno":
		    	tipologia = "52";
		        break;
		    case "Villa":
		    	tipologia = "17";
		        break;
		    case "Villaschiera":
		    	tipologia = "20";
		        break;
		    case "Agriturismo":
		    	tipologia = "37";
		        break;
		    case "Albergo":
		    	tipologia = "9";
		        break;
		    case "Bar":
		    	tipologia = "35";
		        break;
		    case "Negozio":
		    	tipologia = "41";
		        break;
		    case "Ristorante":
		    	tipologia = "36";
		        break;
		    case "Ufficio":
		    	tipologia = "42";
		        break;
		    case "Capannone":
		    	tipologia = "47";
		        break;
		    case "Laboratorio":
		    	tipologia = "44";
		        break;
		    case "Magazzino":
		    	tipologia = "44";
		        break;
		}
		System.out.println("tipologia: " + tipologia);
		
		titolo = scheda.titolo;
		System.out.println("titolo: " + titolo);		
		
		via = scheda.via;
		System.out.println("via: " + via);
		
		annoCostruzione = scheda.annoCostr;
		System.out.println("annoCostruzione: " + annoCostruzione);
				
		switch (scheda.riscaldamento)
		{
		    case "Assente":
		    	dettagli10 = "81";
		        break;
		    case "Centralizzato":
		    	dettagli10 = "82";
		    	break;
		    case "Autonomo":
		    	dettagli10 = "85";
		    	break;
		    case "Stufa":
		    	dettagli10 = "90";
		    	break;
		    default:
		    	dettagli10 = "";
		}
	    System.out.println("dettagli10: " + dettagli10);
			    
	    switch (scheda.clima)
		{
		    case "Assente":
		    	dettagli11 = "92";
		        break;
		    case "Aria condizionata":
		    	dettagli11 = "93";
		    	break;
		    case "Climatizzatore":
		    	dettagli11 = "94";
		    	break;
		    default:
		    	dettagli11 = "";
		}
	    System.out.println("dettagli11: " + dettagli11);
			    
	    switch (scheda.giardino)
		{
		    case "Assente":
		    	dettagli14 = "111";
		        break;
		    case "Giardino condominiale":
		    	dettagli14 = "112";
		    	break;
		    case "Giardino ad uso esclusivo":
		    	dettagli14 = "113";
		    	break;
		    default:
		    	dettagli14 = "";
		}
	    System.out.println("dettagli14: " + dettagli14);
	    
	    switch (scheda.statoImmobile)
    	{
    	    case "Nuovo":
    	    	dettagli16 = "123";
    	        break;
    	    case "Ristrutturato":
    	    	dettagli16 = "124";
    	    	break;
    	    case "Da ristrutturare":
    	    	dettagli16 = "125";
    	    	break;
    	    case "In buono stato":
    	    	dettagli16 = "126";
    	    	break;
    	    case "Abitabile":
    	    	dettagli16 = "127";
    	    	break;
    	    case "Ottimo":
    	    	dettagli16 = "128";
    	    	break;
    	    case "In costruzione":
    	    	dettagli16 = "129";
    	    	break;
    	    default:
    	    	dettagli16 = "-1";
    	}
        System.out.println("dettagli16: " + dettagli16);	    
        
        switch (scheda.parcheggio)
		{
		    case "Nessuno":
		    	dettagli6 = "56";
		        break;
		    case "Garage":
		    	dettagli6 = "57";
		    	break;
		    case "Box auto":
		    	dettagli6 = "58";
		    	break;
		    case "Posto auto":
		    	dettagli6 = "61";
		    	break;
		    default:
		    	dettagli6 = "";
		}
	    System.out.println("dettagli6: " + dettagli6);	    
	    
	    switch (scheda.arredamenti)
		{
		    case "Arredato":
		    	dettagli8 = "67";
		        break;
		    case "Semi-arredato":
		    	dettagli8 = "68";
		    	break;
		    case "Non arredato":
		    	dettagli8 = "69";
		    	break;
		    default:
		    	dettagli8 = "";
		}
	    System.out.println("dettagli8: " + dettagli8);
	    	    
	    piano = scheda.totPiani;
		System.out.println("piano: " + piano);
				
		switch (scheda.certificazioni)
		{
		    case "Nessuna":
		    	dettagli_13 = "";
		        break;
		    case "Certificazione energetica A++":
		    	dettagli_13 = "147";
		    	break;
		    case "Certificazione energetica A+":
		    	dettagli_13 = "107";
		    	break;
		    case "Certificazione energetica A":
		    	dettagli_13 = "108";
		    	break;
		    case "Certificazione energetica B":
		    	dettagli_13 = "109";
		    	break;
		    case "Certificazione energetica C":
		    	dettagli_13 = "110";
		    	break;
		    case "Certificazione energetica D":
		    	dettagli_13 = "143";
		    	break;
		    case "Certificazione energetica E":
		    	dettagli_13 = "144";
		    	break;
		    case "Certificazione energetica F":
		    	dettagli_13 = "145";
		    	break;
		    case "Certificazione energetica G":
		    	dettagli_13 = "146";
		    	break;
		    default:
		    	dettagli_13 = "149";
		}
	    System.out.println("dettagli_13: " + dettagli_13);
		
	}
		



}