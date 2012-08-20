/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
public class Case24 extends PortaleImmobiliare {     

    //Parametri generali
	private final String NOMEPORTALE = "case24.it";
	private final String URL_ROOT = "http://www.case24.it";
	private final String USERNAME = "info@cividalecasa.it";
    private final String PASSWORD = "m193evro";
    private final String CODICE_CLIENTE ="1332323000";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:12.0) Gecko/20100101 Firefox/12.0";
    private String SESSIONCOOKIE_HEADER = "";
    private String SESSIONCOOKIE_NAME = "";
    private String SESSIONCOOKIE_VALUE = "";
    private String SESSIONCOOKIE_DOMAIN = "www.case24.it";  
    private String CODICEINSERZIONE;    
    private String NOME_IMMAGINE_1;
    private String NOME_IMMAGINE_2;
    private String NOME_IMMAGINE_3;
    private String NOME_IMMAGINE_4;
    private String NOME_IMMAGINE_5;
    private String NOME_IMMAGINE_6;
    private boolean INSERIMENTO_OK = false;
    
    //Parametri scheda (elaborati in base al portale)
    String data_annuncio = "";
    String no_reload = "";
    String rif_agenzia = "";
    String codice_provincia_inserzione = "";
    String codice_comune_inserzione = "";
    String nameComune = "";
    String indirizzo = "";
    String tipo_proposta = "";
    String tipo_immobile = "";
    String superficie = "";
    String prezzo = "";
    String numero_camere_bis = "";
    String numero_bagni = "";
    String descrizione = "";
    String stato_immobile ="";
    String riscaldamento="";
    String tipologia_giardino="";
    String classe_energetica ="";

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   	
    
	//Costruttore
	public Case24 (String urlIcona, String valoreLabel, String idPortale) {		
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
        	throw(new HttpResponseException("Response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    	
	
	//GET della pagina di login
    private void connessione_1(HttpGet httpget) throws IOException, HttpResponseException {
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
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    
    //GET della pagina "Area Riservata"
    private void connessione_2(HttpGet httpget) throws IOException, HttpResponseException {
    	System.out.println("CONNESSIONE 2");
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
        else {
        	throw(new HttpResponseException("Response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    

    
    //GET della pagina "Inserisci annuncio" (step 1)
    private void connessione_3(HttpGet httpget) throws IOException, HttpResponseException {
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
    
    
    
    //POST della pagina Gestione annunci per ottenere la pagina di inserzione annuncio
    private void connessione_4(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 4");
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
        postParameters.add(new BasicNameValuePair("inserisci_annuncio", "1"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("y", "10"));
        //Stampa dei parametri inviati
        printSentParameters(postParameters);
        
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
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
    
    
    //GET di una pagina per passargli il codice agenzia e riferimento annuncio
    private void connessione_5(HttpGet httpget) throws IOException, HttpResponseException {
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
        	throw(new HttpResponseException("Response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    	
    
    //POST di inserimento immmagine
  	private void connessione_6(String index, File image) throws IOException {
  		System.out.println("\n\n\n");
    	System.out.println("######################\n######################\n######################");
    	System.out.println("\n\n\n");
        System.out.println("CONNESSIONE 6 - VECCHIO METODO");                               
          
        //URL a cui si accede
        String serverAccesso = URL_ROOT + "/area_clienti/include/upload_foto.php?i=" + index + "&codice_cliente=" + CODICE_CLIENTE;
        	
        //Impostazione della connessione alla risorsa
        httpRequest connessione6 = new httpRequest(serverAccesso);
        
        //Definizione del request method
        connessione6.setconnectionProperty("POST");
        
        //Recupero nome e valore cookie
        //nomeCookie = connessione2.getHeaderNameByIndex(3);      //Nome del cookie ricevuto dopo l'inserimento delle credenziali di accesso
        //valoreCookie = connessione2.getHeaderValueByIndex(3);   //Valore del cookie ricevuto dopo l'inserimento delle credenziali di accesso
        
        //Inserimento cookie
        connessione6.setCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        connessione6.postCookies();
        
        //Inserimento parametri (POST)
        connessione6.setParameter("image_" + index, image);        
                      
        //Invio della richiesta e stampa a video della risorsa creata
        System.out.println("\n\n######################\n\n");
        System.out.println("Stampa della risorsa 6...");
        BufferedReader rd1 = new BufferedReader(new InputStreamReader(connessione6.post()));
        String line1;   //Per visualizzare la risorsa Web generata
        while ((line1 = rd1.readLine()) != null) {
          //Process line...
          System.out.println(line1);
          
          boolean control = true;
          int start;
          int end;
          if(line1.contains("document.formAnnuncio.foto_tmp_") && control) {
        	  start = line1.indexOf("value='")+7;
        	  end = line1.indexOf("';");
        	  switch (index)
      		{
      		    case "0":
      		    	NOME_IMMAGINE_1 = line1.substring(start, end);
      		    	control=false;
      		        break;
      		    case "1":
      		    	NOME_IMMAGINE_2 = line1.substring(start, end);
      		    	control=false;
      		    	break;
      		    case "2":
      		    	NOME_IMMAGINE_3 = line1.substring(start, end);
      		    	control=false;
      		    	break;
      		    case "3":
      		    	NOME_IMMAGINE_4 = line1.substring(start, end);
      		    	control=false;
      		    	break;
      		    case "4":
      		    	NOME_IMMAGINE_5 = line1.substring(start, end);
      		    	control=false;
      		    	break;
      		    default:
      		    	NOME_IMMAGINE_6 = line1.substring(start, end);
      		    	control=false;
      		}
          }
        }
        System.out.println("\n\n######################\n\n");

        //Stampa delle proprietà di connessione
        System.out.println("\nProprietà della chiamata...\n");         
        System.out.println("RISPOSTA: " + connessione6.getResponse());
        System.out.println("METODO: " + connessione6.getRequestMethod());                     
        connessione6.getRequestHeaders();
        connessione6.getResponseHeaders();
  	}

    
  	
    //POST dello step 1 (ed unico...)
    private void connessione_7(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 7");
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
        postParameters.add(new BasicNameValuePair("action", "inserisci_nuovo_annuncio"));
        postParameters.add(new BasicNameValuePair("data_annuncio", data_annuncio));
        postParameters.add(new BasicNameValuePair("no_reload", no_reload));
        postParameters.add(new BasicNameValuePair("codice_cliente", CODICE_CLIENTE));
        postParameters.add(new BasicNameValuePair("InserzionistaPrivato", "0"));
        postParameters.add(new BasicNameValuePair("codice_inserzione", ""));
        postParameters.add(new BasicNameValuePair("foto_tmp_0", NOME_IMMAGINE_1));
        postParameters.add(new BasicNameValuePair("foto_tmp_1", NOME_IMMAGINE_2));
        postParameters.add(new BasicNameValuePair("foto_tmp_2", NOME_IMMAGINE_3));
        postParameters.add(new BasicNameValuePair("foto_tmp_3", NOME_IMMAGINE_4));
        postParameters.add(new BasicNameValuePair("foto_tmp_4", NOME_IMMAGINE_5));
        postParameters.add(new BasicNameValuePair("foto_tmp_5", NOME_IMMAGINE_6));
        postParameters.add(new BasicNameValuePair("foto_tmp_6", ""));
        postParameters.add(new BasicNameValuePair("foto_tmp_7", ""));
        postParameters.add(new BasicNameValuePair("galleria_valore", "0"));
        postParameters.add(new BasicNameValuePair("rif_agenzia", rif_agenzia));
        postParameters.add(new BasicNameValuePair("codice_provincia_inserzione", codice_provincia_inserzione));
        postParameters.add(new BasicNameValuePair("codice_comune_inserzione", codice_comune_inserzione));
        postParameters.add(new BasicNameValuePair("zona", "0"));
        postParameters.add(new BasicNameValuePair("indirizzo", indirizzo));
        postParameters.add(new BasicNameValuePair("visua_indirizzo", "1"));
        postParameters.add(new BasicNameValuePair("tipo_proposta", tipo_proposta));
        postParameters.add(new BasicNameValuePair("tipo_immobile", tipo_immobile));
        postParameters.add(new BasicNameValuePair("superficie", superficie));
        postParameters.add(new BasicNameValuePair("prezzo", prezzo));
        postParameters.add(new BasicNameValuePair("numero_camere_old", ""));
        postParameters.add(new BasicNameValuePair("numero_camere_bis", numero_camere_bis));
        postParameters.add(new BasicNameValuePair("stato_immobile", stato_immobile));
        postParameters.add(new BasicNameValuePair("numero_bagni", numero_bagni));
        postParameters.add(new BasicNameValuePair("numero_piani", ""));//il  numero piani è difficile da mettere
        postParameters.add(new BasicNameValuePair("numero_terrazze", "0"));
        postParameters.add(new BasicNameValuePair("numero_posti_auto_coperti", "0"));
        postParameters.add(new BasicNameValuePair("numero_posti_auto", "0"));
        postParameters.add(new BasicNameValuePair("numero_posti_auto_garage", ""));
        postParameters.add(new BasicNameValuePair("riscaldamento", riscaldamento));
        postParameters.add(new BasicNameValuePair("riscaldamento_tipo", "")); 
        postParameters.add(new BasicNameValuePair("anno", "")); //una select...
        postParameters.add(new BasicNameValuePair("spese_condominiali", ""));
        postParameters.add(new BasicNameValuePair("stato_rogito", ""));
        postParameters.add(new BasicNameValuePair("orientamento", ""));
        postParameters.add(new BasicNameValuePair("tipologia_giardino", tipologia_giardino));
        postParameters.add(new BasicNameValuePair("scoperto", ""));
        postParameters.add(new BasicNameValuePair("classe_energetica", classe_energetica));
        postParameters.add(new BasicNameValuePair("ipe", ""));
        postParameters.add(new BasicNameValuePair("descrizione", descrizione));
        postParameters.add(new BasicNameValuePair("attivo", "1"));
        postParameters.add(new BasicNameValuePair("opt_ascensore", "0"));
        postParameters.add(new BasicNameValuePair("opt_servizi_disabili", "0"));
        postParameters.add(new BasicNameValuePair("opt_angolo_cottura", "0"));
        postParameters.add(new BasicNameValuePair("opt_satellite", "0"));
        postParameters.add(new BasicNameValuePair("opt_arredato", "0"));
        postParameters.add(new BasicNameValuePair("opt_bagno_con_vasca", "0"));
        postParameters.add(new BasicNameValuePair("opt_caminetto", "0"));
        postParameters.add(new BasicNameValuePair("opt_cantina", "0"));
        postParameters.add(new BasicNameValuePair("opt_cassaforte", "0"));
        postParameters.add(new BasicNameValuePair("opt_climatizzatore", "0"));
        postParameters.add(new BasicNameValuePair("opt_cucina_vivibile", "0"));
        postParameters.add(new BasicNameValuePair("opt_idromassaggio", "0"));
        postParameters.add(new BasicNameValuePair("opt_allarme", "0"));
        postParameters.add(new BasicNameValuePair("opt_lavanderia", "0"));
        postParameters.add(new BasicNameValuePair("opt_mansarda", "0"));
        postParameters.add(new BasicNameValuePair("opt_fotovoltaico", "0"));
        postParameters.add(new BasicNameValuePair("opt_pannelli_solari", "0"));
        postParameters.add(new BasicNameValuePair("opt_piscina", "0"));
        postParameters.add(new BasicNameValuePair("opt_porta_blindata", "0"));
        postParameters.add(new BasicNameValuePair("opt_possibilita_animali", "0"));
        postParameters.add(new BasicNameValuePair("opt_ripostiglio", "0"));
        postParameters.add(new BasicNameValuePair("opt_riscaldamento_pavimento", "0"));
        postParameters.add(new BasicNameValuePair("opt_sauna", "0"));
        postParameters.add(new BasicNameValuePair("opt_taverna", "0"));
        postParameters.add(new BasicNameValuePair("opt_terra_cielo", "0"));
        postParameters.add(new BasicNameValuePair("opt_travi", "0"));
        postParameters.add(new BasicNameValuePair("opt_vista_panoramica", "0"));
        postParameters.add(new BasicNameValuePair("opt_in_campagna", "0"));
        postParameters.add(new BasicNameValuePair("optional_valore", "0"));
        //Stampa dei parametri inviati
        printSentParameters(postParameters);
        
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
                        
            if(responseBody.contains("ANNUNCIO INSERITO CORRETTAMENTE")) {
            	INSERIMENTO_OK = true;
            }
            else {
            	throw(new HttpResponseException("Response body"));
            }
            
	        //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
            Elements linkElements = doc.getElementsByTag("a");
            if(linkElements!=null) {
	            Iterator<Element> iterator = linkElements.iterator();
	            while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	if(currentElement.html().contains("CODICE RIFERIMENTO AGENZIA:")) {
	            		String text = currentElement.html().substring(currentElement.html().indexOf("AGENZIA:")+8).trim();
	            		System.out.println("Codice riferimento agenzia: " + text);
	            		if(scheda.codice.equals(text)) {
	            			CODICEINSERZIONE = currentElement.attr("name");
	            		}		
	            		System.out.println("CODICEINSERZIONE: " + CODICEINSERZIONE);
	            	}
	            }
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
    
    
    
    //POST della pagina Gestione annunci per eliminare un annuncio
    private void connessione_8(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
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
        postParameters.add(new BasicNameValuePair("bottone_cancella", "Cancella annuncio"));
        postParameters.add(new BasicNameValuePair("cancella_annuncio", "cancella"));
        postParameters.add(new BasicNameValuePair("codice_inserzione", CODICEINSERZIONE));
        postParameters.add(new BasicNameValuePair("order_by", ""));
        postParameters.add(new BasicNameValuePair("order_direction", ""));
        postParameters.add(new BasicNameValuePair("page", ""));
        //Stampa dei parametri inviati
        printSentParameters(postParameters);
        
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
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    


    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public void inserisciScheda(SchedaImmobile scheda) {
    	System.out.println("Inserimento scheda: " + scheda.codice + "...");
    	
    	//Inizializzazione parametri
    	this.scheda=scheda;
    	
    	//Valorizzazione parametri da inviare
    	valutaParametri();
    	
    	
    	    	
    	//Connessione 0 - GET della home page
        try {
			connessione_0(new HttpGet(URL_ROOT));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 1);
            return;
		}
        
        
        
        //Connessione 1 - GET della pagina di login
        try {
			connessione_1(new HttpGet(URL_ROOT + "/mycase24-areariservata-vendita-appartamenti.php"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 2);
            return;
		}
        
        
        
        //Connessione 2 - GET della pagina "Area Riservata"
        try {
			connessione_2(new HttpGet(URL_ROOT + "/area_clienti/include/ajax.php?tabella=utenti&username=" + USERNAME + "&password=" + PASSWORD));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessione 3 - GET della pagina "Inserisci annuncio" (step 1)
        try {
			connessione_3(new HttpGet(URL_ROOT + "/area_clienti/annunci.php?pagina=1"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessione 4 - POST della pagina Gestione annunci per ottenere la pagina di inserzione annuncio
    	try {
			connessione_4(new HttpPost(URL_ROOT + "/area_clienti/annunci.php?pagina=1"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
            return;
		}
    	
    	
    	
    	//Connessione 5 - GET di una pagina per passargli il codice agenzia e riferimento annuncio
        try {
        	String encodedSchedaCodice = URLEncoder.encode(scheda.codice,"UTF-8");
			connessione_5(new HttpGet(URL_ROOT + "/area_clienti/include/ajax.php?edit=valida_rif_agenzia&rif_agenzia=" + encodedSchedaCodice + "&codice_inserzione=&codice_cliente=" + CODICE_CLIENTE));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessioni 6 - inserimento immagine
        if(scheda.immagine1!=null) {
        	try {
				connessione_6("0", scheda.immagine1);
			} catch (IOException e) {
				manageErrors(e, 3);
                return;
			}
        }
        if(scheda.immagine2!=null) {
        	try {
				connessione_6("1", scheda.immagine2);
			} catch (IOException e) {
				manageErrors(e, 3);
                return;
			}
        }
        if(scheda.immagine3!=null) {
        	try {
				connessione_6("2", scheda.immagine3);
			} catch (IOException e) {
				manageErrors(e, 3);
                return;
			}
        }
        if(scheda.immagine4!=null) {
        	try {
				connessione_6("3", scheda.immagine4);
			} catch (IOException e) {
				manageErrors(e, 3);
                return;
			}
        }
        if(scheda.immagine5!=null) {
        	try {
				connessione_6("4", scheda.immagine5);
			} catch (IOException e) {
				manageErrors(e, 3);
                return;
			}
        }
        if(scheda.immagine6!=null) {
        	try {
				connessione_6("5", scheda.immagine6);
			} catch (IOException e) {
				manageErrors(e, 3);
                return;
			}
        }
        
    	

        
        //Connessione 7 - POST dello step 1 (e unico...)
    	try {
			connessione_7(new HttpPost(URL_ROOT + "/area_clienti/annunci.php?pagina=1"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
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
			String url = URL_ROOT + "/area_clienti/annunci.php";
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
	
		
		
		//Connessione 2 - GET della pagina "Area Riservata"
        try {
			connessione_2(new HttpGet(URL_ROOT + "/area_clienti/include/ajax.php?tabella=utenti&username=" + USERNAME + "&password=" + PASSWORD));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessione 8 - POST della pagina Gestione annunci per eliminare un annuncio
    	try {
			connessione_8(new HttpPost(URL_ROOT + "/area_clienti/annunci.php?pagina=1"));
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
				
		System.out.println("--ELABORAZIONE PARAMETRI--");
		System.out.println("----------------------------------------");
		
		Date now = new Date();  		  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		String data_annuncio = df.format(now); 
		no_reload = Long.toString(now.getTime());
		no_reload = no_reload.substring(0, no_reload.length()-3);
		System.out.println("data_annuncio: " + data_annuncio);
		System.out.println("no_reload: " + no_reload);
		
		nameComune = scheda.comune;
		System.out.println("nameComune: " + nameComune);
		
		rif_agenzia = scheda.codice;
		System.out.println("rif_agenzia: " + rif_agenzia);
		
		String provincia = scheda.provincia;
		switch (provincia)
		{
		    case "Agrigento":
		    	codice_provincia_inserzione = "084";
		        break;
		    case "Alessandria":
		    	codice_provincia_inserzione = "006";
		        break;
		    case "Ancona":
		    	codice_provincia_inserzione = "042";
		        break;
		    case "Arezzo":
		    	codice_provincia_inserzione = "051";
		        break;
		    case "Ascoli Piceno":
		    	codice_provincia_inserzione = "044";
		        break;
		    case "Asti":
		    	codice_provincia_inserzione = "005";
		        break;
		    case "Avellino":
		    	codice_provincia_inserzione = "064";
		        break;
		    case "Bari":
		    	codice_provincia_inserzione = "072";
		        break;
		    case "Barletta-Andria-Trani":
		    	codice_provincia_inserzione = "110";
		        break;
		    case "Belluno":
		    	codice_provincia_inserzione = "025";
		        break;
		    case "Benevento":
		    	codice_provincia_inserzione = "062";
		        break;
		    case "Bergamo":
		    	codice_provincia_inserzione = "016";
		        break;
		    case "Biella":
		    	codice_provincia_inserzione = "096";
		        break;
		    case "Bologna":
		    	codice_provincia_inserzione = "037";
		        break;
		    case "Bolzano":
		    	codice_provincia_inserzione = "021";
		        break;
		    case "Brescia":
		    	codice_provincia_inserzione = "017";
		        break;
		    case "Brindisi":
		    	codice_provincia_inserzione = "074";
		        break;
		    case "Cagliari":
		    	codice_provincia_inserzione = "092";
		        break;
		    case "Caltanissetta":
		    	codice_provincia_inserzione = "085";
		        break;
		    case "Campobasso":
		    	codice_provincia_inserzione = "070";
		        break;
		    case "Carbonia-Iglesias":
		    	codice_provincia_inserzione = "107";
		        break;
		    case "Caserta":
		    	codice_provincia_inserzione = "061";
		        break;
		    case "Catania":
		    	codice_provincia_inserzione = "087";
		        break;
		    case "Catanzaro":
		    	codice_provincia_inserzione = "079";
		        break;
		    case "Chieti":
		    	codice_provincia_inserzione = "069";
		        break;
		    case "Como":
		    	codice_provincia_inserzione = "013";
		        break;
		    case "Cosenza":
		    	codice_provincia_inserzione = "078";
		        break;
		    case "Cremona":
		    	codice_provincia_inserzione = "019";
		        break;
		    case "Crotone":
		    	codice_provincia_inserzione = "101";
		        break;
		    case "Cuneo":
		    	codice_provincia_inserzione = "004";
		        break;
		    case "Enna":
		    	codice_provincia_inserzione = "086";
		        break;
		    case "Fermo":
		    	codice_provincia_inserzione = "109";
		        break;
		    case "Ferrara":
		    	codice_provincia_inserzione = "038";
		        break;
		    case "Firenze":
		    	codice_provincia_inserzione = "048";
		        break;
		    case "Foggia":
		    	codice_provincia_inserzione = "071";
		        break;
		    case "Forlì-Cesena":
		    	codice_provincia_inserzione = "040";
		        break;
		    case "Frosinone":
		    	codice_provincia_inserzione = "060";
		        break;
		    case "Genova":
		    	codice_provincia_inserzione = "010";
		        break;
		    case "Gorizia":
		    	codice_provincia_inserzione = "031";
		        break;
		    case "Grosseto":
		    	codice_provincia_inserzione = "053";
		        break;
		    case "Imperia":
		    	codice_provincia_inserzione = "008";
		        break;
		    case "Isernia":
		    	codice_provincia_inserzione = "094";
		        break;
		    case "L'Aquila":
		    	codice_provincia_inserzione = "066";
		        break;
		    case "La Spezia":
		    	codice_provincia_inserzione = "011";
		        break;
		    case "Latina":
		    	codice_provincia_inserzione = "059";
		        break;
		    case "Lecce":
		    	codice_provincia_inserzione = "075";
		        break;
		    case "Lecco":
		    	codice_provincia_inserzione = "097";
		        break;
		    case "Livorno":
		    	codice_provincia_inserzione = "049";
		        break;
		    case "Lodi":
		    	codice_provincia_inserzione = "098";
		        break;
		    case "Lucca":
		    	codice_provincia_inserzione = "046";
		        break;
		    case "Macerata":
		    	codice_provincia_inserzione = "043";
		        break;
		    case "Mantova":
		    	codice_provincia_inserzione = "020";
		        break;
		    case "Massa-Carrara":
		    	codice_provincia_inserzione = "045";
		        break;
		    case "Matera":
		    	codice_provincia_inserzione = "077";
		        break;
		    case "Medio Campidano":
		    	codice_provincia_inserzione = "106";
		        break;
		    case "Messina":
		    	codice_provincia_inserzione = "083";
		        break;
		    case "Milano":
		    	codice_provincia_inserzione = "015";
		        break;
		    case "Modena":
		    	codice_provincia_inserzione = "036";
		        break;
		    case "Monza e della Brianza":
		    	codice_provincia_inserzione = "108";
		        break;
		    case "Napoli":
		    	codice_provincia_inserzione = "063";
		        break;
		    case "Novara":
		    	codice_provincia_inserzione = "003";
		        break;
		    case "Nuoro":
		    	codice_provincia_inserzione = "091";
		        break;
		    case "Ogliastra":
		    	codice_provincia_inserzione = "105";
		        break;
		    case "Olbia-Tempio":
		    	codice_provincia_inserzione = "104";
		        break;
		    case "Oristano":
		    	codice_provincia_inserzione = "095";
		        break;
		    case "Padova":
		    	codice_provincia_inserzione = "028";
		        break;
		    case "Palermo":
		    	codice_provincia_inserzione = "082";
		        break;
		    case "Parma":
		    	codice_provincia_inserzione = "034";
		        break;
		    case "Perugia":
		    	codice_provincia_inserzione = "054";
		        break;
		    case "Pesaro e Urbino":
		    	codice_provincia_inserzione = "041";
		        break;
		    case "Pescara":
		    	codice_provincia_inserzione = "068";
		        break;
		    case "Piacenza":
		    	codice_provincia_inserzione = "033";
		        break;
		    case "Pisa":
		    	codice_provincia_inserzione = "050";
		        break;
		    case "Pistoia":
		    	codice_provincia_inserzione = "047";
		        break;
		    case "Pordenone":
		    	codice_provincia_inserzione = "093";
		        break;
		    case "Potenza":
		    	codice_provincia_inserzione = "076";
		        break;
		    case "Prato":
		    	codice_provincia_inserzione = "100";
		        break;
		    case "Ragusa":
		    	codice_provincia_inserzione = "088";
		        break;
		    case "Ravenna":
		    	codice_provincia_inserzione = "039";
		        break;
		    case "Reggio di Calabria":
		    	codice_provincia_inserzione = "080";
		        break;
		    case "Reggio nell'Emilia":
		    	codice_provincia_inserzione = "036";
		        break;
		    case "Rieti":
		    	codice_provincia_inserzione = "057";
		        break;
		    case "Rimini":
		    	codice_provincia_inserzione = "099";
		        break;
		    case "Roma":
		    	codice_provincia_inserzione = "058";
		        break;
		    case "Rovigo":
		    	codice_provincia_inserzione = "029";
		        break;
		    case "Salerno":
		    	codice_provincia_inserzione = "065";
		        break;
		    case "Sassari":
		    	codice_provincia_inserzione = "090";
		        break;
		    case "Siena":
		    	codice_provincia_inserzione = "052";
		        break;
		    case "Siracusa":
		    	codice_provincia_inserzione = "089";
		        break;
		    case "Sondrio":
		    	codice_provincia_inserzione = "014";
		        break;
		    case "Taranto":
		    	codice_provincia_inserzione = "073";
		        break;
		    case "Teramo":
		    	codice_provincia_inserzione = "067";
		        break;
		    case "Terni":
		    	codice_provincia_inserzione = "055";
		        break;
		    case "Torino":
		    	codice_provincia_inserzione = "001";
		        break;
		    case "Trapani":
		    	codice_provincia_inserzione = "081";
		        break;
		    case "Trento":
		    	codice_provincia_inserzione = "022";
		        break;
		    case "Treviso":
		    	codice_provincia_inserzione = "026";
		        break;
		    case "Trieste":
		    	codice_provincia_inserzione = "032";
		        break;
		    case "Udine":
		    	codice_provincia_inserzione = "030";
		        break;
		    case "Valle d'Aosta":
		    	codice_provincia_inserzione = "007";
		        break;
		    case "Varese":
		    	codice_provincia_inserzione = "012";
		        break;
		    case "Venezia":
		    	codice_provincia_inserzione = "027";
		        break;
		    case "Verbano-Cusio-Ossola":
		    	codice_provincia_inserzione = "103";
		        break;
		    case "Vercelli":
		    	codice_provincia_inserzione = "002";
		        break;
		    case "Verona":
		    	codice_provincia_inserzione = "023";
		        break;
		    case "Vibo Valentia":
		    	codice_provincia_inserzione = "102";
		        break;
		    case "Vicenza":
		    	codice_provincia_inserzione = "024";
		        break;
		    case "Viterbo":
		    	codice_provincia_inserzione = "056";
		        break;		        
		}
	    System.out.println("codice_provincia_inserzione: " + codice_provincia_inserzione);
		
		
		try {
			codice_comune_inserzione = get_codice_comune_inserzione();
			System.out.println("codice_comune_inserzione: " + codice_comune_inserzione);
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 3);
            return;
		}
		
		indirizzo = scheda.via;
		System.out.println("indirizzo: " + indirizzo);
		
		switch (scheda.contratto)
		{
		    case "Affitto":
		    	tipo_proposta = "Affitti";
		        break;
		    case "Vendita":
		    	tipo_proposta = "Vendita";
		    	break;
		}
	    System.out.println("tipo_proposta: " + tipo_proposta);
	    
	    switch (scheda.tipologia)
		{
		    case "Appartamento":
		    	tipo_immobile = "16";
		        break;
		    case "Attico":
		    	tipo_immobile = "26";
		        break;
		    case "Bifamiliare":
		    	tipo_immobile = "19";
		        break;
		    case "Casa":
		    	tipo_immobile = "19";
		        break;
		    case "Garage":
		    	tipo_immobile = "22";
		        break;
		    case "Palazzo":
		    	tipo_immobile = "20";
		        break;
		    case "Rustico":
		    	tipo_immobile = "24";
		        break;
		    case "Terreno":
		    	tipo_immobile = "27";
		        break;
		    case "Villa":
		    	tipo_immobile = "20";
		        break;
		    case "Villaschiera":
		    	tipo_immobile = "19";
		        break;
		    case "Agriturismo":
		    	tipo_immobile = "25";
		        break;
		    case "Albergo":
		    	tipo_immobile = "25";
		        break;
		    case "Bar":
		    	tipo_immobile = "21";
		        break;
		    case "Negozio":
		    	tipo_immobile = "23";
		        break;
		    case "Ristorante":
		    	tipo_immobile = "25";
		        break;
		    case "Ufficio":
		    	tipo_immobile = "23";
		        break;
		    case "Capannone":
		    	tipo_immobile = "23";
		        break;
		    case "Laboratorio":
		    	tipo_immobile = "23";
		        break;
		    case "Magazzino":
		    	tipo_immobile = "23";
		        break;
		}
		System.out.println("tipo_immobile: " + tipo_immobile);
				
		superficie = Integer.toString(scheda.supAbitazione);
		System.out.println("superficie: " + superficie);
		
		prezzo = Integer.toString(scheda.prezzo);
		System.out.println("prezzo: " + prezzo);
		
		switch (scheda.numCamere)
		{
		    case 1:
		    	numero_camere_bis = "1";
		        break;
		    case 2:
		    	numero_camere_bis = "2";
		    	break;
		    case 3:
		    	numero_camere_bis = "3";
		    	break;
		    case 4:
		    	numero_camere_bis = "4";
		    	break;
		    case 5:
		    	numero_camere_bis = "5";
		    	break;
		    default:
		    	numero_camere_bis = "6";
		}
	    System.out.println("numero_camere_bis: " + numero_camere_bis);
		
	    switch (scheda.numBagni)
		{
		    case "1":
		    	numero_bagni = "1";
		        break;
		    case "2":
		    	numero_bagni = "2";
		    	break;
		    case "3":
		    	numero_bagni = "3";
		    	break;
		    case "4":
		    	numero_bagni = "4";
		    	break;
		    case "5":
		    	numero_bagni = "5";
		    	break;
		    default:
		    	numero_bagni = "";
		}
	    System.out.println("numero_bagni: " + numero_bagni);
		
	    descrizione = scheda.descrEstesa;
		System.out.println("descrizione: " + descrizione);
			
		switch (scheda.statoImmobile)
    	{
    	    case "Nuovo":
    	    	stato_immobile = "88";
    	        break;
    	    case "Ristrutturato":
    	    	stato_immobile = "91";
    	    	break;
    	    case "Da ristrutturare":
    	    	stato_immobile = "4";
    	    	break;
    	    case "In buono stato":
    	    	stato_immobile = "";
    	    	break;
    	    case "Abitabile":
    	    	stato_immobile = "";
    	    	break;
    	    case "Ottimo":
    	    	stato_immobile = "";
    	    	break;
    	    case "In costruzione":
    	    	stato_immobile = "87";
    	    	break;
    	    default:
    	    	stato_immobile = "";
    	}
        System.out.println("stato_immobile: " + stato_immobile);
		        
        switch (scheda.riscaldamento)
		{
		    case "Assente":
		    	riscaldamento = "";
		        break;
		    case "Centralizzato":
		    	riscaldamento = "107";
		    	break;
		    case "Autonomo":
		    	riscaldamento = "106";
		    	break;
		    case "Stufa":
		    	riscaldamento = "";
		    	break;
		    default:
		    	riscaldamento = "";
		}
	    System.out.println("riscaldamento: " + riscaldamento);
			    
	    switch (scheda.giardino)
		{
		    case "Assente":
		    	tipologia_giardino = "";
		        break;
		    case "Giardino condominiale":
		    	tipologia_giardino = "117";
		    	break;
		    case "Giardino ad uso esclusivo":
		    	tipologia_giardino = "116";
		    	break;
		    default:
		    	tipologia_giardino = "";
		}
	    System.out.println("tipologia_giardino: " + tipologia_giardino);
	    	    
	    switch (scheda.certificazioni)
		{
		    case "Nessuna":
		    	classe_energetica = "130";
		        break;
		    case "Certificazione energetica A++":
		    	classe_energetica = "122";
		    	break;
		    case "Certificazione energetica A+":
		    	classe_energetica = "122";
		    	break;
		    case "Certificazione energetica A":
		    	classe_energetica = "123";
		    	break;
		    case "Certificazione energetica B":
		    	classe_energetica = "124";
		    	break;
		    case "Certificazione energetica C":
		    	classe_energetica = "125";
		    	break;
		    case "Certificazione energetica D":
		    	classe_energetica = "126";
		    	break;
		    case "Certificazione energetica E":
		    	classe_energetica = "127";
		    	break;
		    case "Certificazione energetica F":
		    	classe_energetica = "128";
		    	break;
		    case "Certificazione energetica G":
		    	classe_energetica = "129";
		    	break;
		    default:
		    	classe_energetica = "";
		}
	    System.out.println("classe_energetica: " + classe_energetica);
	    
		
		

	}
	

	public String get_codice_comune_inserzione() throws ClientProtocolException, IOException, HttpResponseException {
		System.out.println("CONNESSIONE 8");
		String valueComune = "";
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpGet httpget = new HttpGet(URL_ROOT + "/area_clienti/include/ajax.php?funzione=select_geografico&etichetta=denominazione_comune&zona=X&valore_etichetta=" + codice_provincia_inserzione + "&valore_selezionato=&tabindex=3");
        	
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
	        
	        org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);              
            //Ottengo il valore del parametro Provincia
            Elements optionElements = doc.getElementsByTag("option");
            if(optionElements.isEmpty()) {
            	System.out.println("Errore: " + "Rilevati errori");
            	throw(new HttpResponseException("Rilevati errori nella risposta del server"));
            }
            else {
            	Iterator<Element> iterator = optionElements.iterator();
            	double resultComparation = 0;
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	List<char[]> charListPortale = bigram(currentElement.html());
	        		List<char[]> charListImagination = bigram(nameComune);
	        		double actualResultComparation = dice(charListPortale, charListImagination);
	        		if(actualResultComparation>=resultComparation) {
	        			resultComparation = actualResultComparation;
	        			valueComune = currentElement.attr("value");            		
	        		}       		
	        		System.out.println("Risultato comparazione: " + resultComparation);
            	}
            } 
        }
        else {
        	throw(new HttpResponseException("Response code"));
        }

        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        
        return valueComune;
	}
	
}