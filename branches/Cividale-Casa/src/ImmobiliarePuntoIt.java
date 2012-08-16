/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

/**
 *
 * @author marco
 */

//La classe principale
public class ImmobiliarePuntoIt extends PortaleImmobiliare {     

    //Parametri generali
	private final String NOMEPORTALE = "immobiliare.it";
	private final String URL_ROOT = "http://getrix.ekbl.net";	//immobiliare.it si appoggia a Getrix
    private final String USERNAME = "jyvqcgbg@sharklasers.com";
    private final String PASSWORD = "jsncqdtp";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:12.0) Gecko/20100101 Firefox/12.0";
    private String SESSIONCOOKIE_HEADER = "";
    private String SESSIONCOOKIE_NAME = "";
    private String SESSIONCOOKIE_VALUE = "";
    String SESSIONCOOKIE_DOMAIN = ".getrix.ekbl.net";
    private String LOCATION;
    private String CODICEINSERZIONE;
    private boolean credenzialiValide = true;
    private boolean inserimentoOK = false;
    
    //Parametri scheda (elaborati in base al portale)
    String categoria = "";
    String contratto = "";
    String fasciaprezzo = "";
    String gtxNumeroCamereDaLetto = "";
    String codice = "";
    String prezzo = "";
    String tipologia = "";
    String sottotipologia = "";
    String locali = "";
    String superficie = "";
    String classe_energetica = "";
    String descrizione_it = "";
    String remLen_it = "";
    String idRegione = "";
    String idProvincia = "";
    String idComune = "";
    String regione = "";
    String provincia = "";
    String comune = "";
    String cap = "";
    String indirizzo = "";
    String gtxAnnoCostruzione="";
    String piano = "";
    String numeroPiani="";
    String stato = "";
    String latitudine="";
    String longitudine="";
    
    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   
	
	//Costruttore
	public ImmobiliarePuntoIt (String urlIcona, String valoreLabel, String idPortale) {		
		this.urlIcona = urlIcona;
		this.valoreLabel = valoreLabel;
		this.idPortale = idPortale;	
	}

	//GET della home/login page
	/*private void connessione_0(HttpGet httpget) {
        System.out.println("CONNESSIONE 0");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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

            //Response properties
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
 
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }*/
    
    
    //Non utilizzata
    /*private void connessione_1(HttpGet httpget) {
    	System.out.println("CONNESSIONE 1");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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

            //Response properties
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
	            __EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
	            System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
	            
	            Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
	            __EVENTTARGET = __EVENTTARGETElement.attr("value");
	            System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
	            
	            Element __EVENTVALIDATIONElement = doc.getElementById("__EVENTVALIDATION");
	            __EVENTVALIDATION = __EVENTVALIDATIONElement.attr("value");
	            System.out.println("Value __EVENTVALIDATION: " + __EVENTVALIDATION);
	            
	            Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");
	            __VIEWSTATE = __VIEWSTATEElement.attr("value");
	            System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
	            System.out.println("----------------------------------------");
            }
            
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }*/
    
    
    //POST della pagina home/login
    private void connessione_2(HttpPost httppost) throws Exception {
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
        postParameters.add(new BasicNameValuePair("accedi", "accedi"));
        postParameters.add(new BasicNameValuePair("backurl", "/home_gestionale.php"));
        postParameters.add(new BasicNameValuePair("openTab", ""));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        postParameters.add(new BasicNameValuePair("username", USERNAME));            
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Response properties
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        	//Get session cookie
        	if(currentHeader.getName().contains("Set-Cookie")) {
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
        		LOCATION = currentHeader.getValue();
        		System.out.println("Location value: " + LOCATION);
        	}
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);
	        
	        //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);           
            
            Elements invalidUserElement = doc.getElementsByClass("errore");   
            
            //Verifico la presenza di errori nel body, errori riferiti a credenziali non corrette
            if(!invalidUserElement.isEmpty()) {
            	System.out.println("Elemento di controllo credenziali utente: " + invalidUserElement.html());
            	JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare le credenziali utente.", "Errore I030-9", JOptionPane.ERROR_MESSAGE);
            	credenzialiValide = false;
            }
	        System.out.println("----------------------------------------");
        }
        
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //GET della home page
    /*private void connessione_3(HttpGet httpget) {
    	System.out.println("CONNESSIONE 3");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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

            //Response properties
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

        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }*/
    
    
    //GET della pagina "BackOffice" (step 1)
    /*private void connessione_4(HttpGet httpget) {
    	System.out.println("CONNESSIONE 4");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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

            //Response properties
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
                    
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }*/
    
    
    //GET della pagina "BackOffice" (step 2)
    /*private void connessione_5(HttpGet httpget) throws Exception {
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

        //Response properties
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
        
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }*/
        
       
    //POST della pagina "Backoffice" (step 2)
    private void connessione_6(HttpPost httppost) throws Exception {
    	System.out.println("CONNESSIONE 6");
    	HttpClient httpclient = new DefaultHttpClient();
    	
    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("tipo", contratto));
        postParameters.add(new BasicNameValuePair("step", "2"));
        postParameters.add(new BasicNameValuePair("categoria", categoria));
        
        valutaFasciaprezzo();
        postParameters.add(new BasicNameValuePair("fasciaprezzo", fasciaprezzo));
        
        postParameters.add(new BasicNameValuePair("terreno_proprieta", "")); // commerciale
        
        valutaGtxNumeroCamereDaLetto();
        postParameters.add(new BasicNameValuePair("gtxNumeroCamereDaLetto", gtxNumeroCamereDaLetto));
        
        valutaGtxAnnoCostruzione();
        postParameters.add(new BasicNameValuePair("gtxAnnoCostruzione", gtxAnnoCostruzione));
        
        valutaCodice();
        postParameters.add(new BasicNameValuePair("codice", codice));
        
        valutaPrezzo();
        postParameters.add(new BasicNameValuePair("prezzo", prezzo));
        
        postParameters.add(new BasicNameValuePair("gtxNumeroSoggiornoSalotto", ""));
        
        valutaPiano();
        postParameters.add(new BasicNameValuePair("piano", piano));
        
        valutaTipologia();
        postParameters.add(new BasicNameValuePair("tipologia", tipologia));
        
        if(categoria=="2") { //Solo se commerciale
        	valutaSottotipologia();
        	postParameters.add(new BasicNameValuePair("sottotipologia", sottotipologia));
        }
        
        valutaLocali();
        postParameters.add(new BasicNameValuePair("locali", locali));
        
        postParameters.add(new BasicNameValuePair("postiAuto", "")); //commerciale
        postParameters.add(new BasicNameValuePair("gtxNumeroAltreCamereStanze", ""));
        
        valutaNumeroPiani();
        postParameters.add(new BasicNameValuePair("numeroPiani", numeroPiani));
        
        valutaStato();
        postParameters.add(new BasicNameValuePair("stato", stato));
        
        valutaSuperficie();
        postParameters.add(new BasicNameValuePair("superficie", superficie));
        
        postParameters.add(new BasicNameValuePair("gtxNumeroPostiAuto", ""));
        postParameters.add(new BasicNameValuePair("tipoProprieta", ""));
        postParameters.add(new BasicNameValuePair("tipoMandato", ""));
        postParameters.add(new BasicNameValuePair("gtxIdContatto", ""));
        postParameters.add(new BasicNameValuePair("idCont", ""));
        postParameters.add(new BasicNameValuePair("classeImmobile", ""));
        postParameters.add(new BasicNameValuePair("annoCostruzione", "")); //commerciale
        postParameters.add(new BasicNameValuePair("superficieEsterna", "")); //commerciale
        postParameters.add(new BasicNameValuePair("carroponte", "")); //commerciale se capannone
        postParameters.add(new BasicNameValuePair("altezzaSottotrave", "")); //commerciale se capannone
        postParameters.add(new BasicNameValuePair("superficieUffici", "")); //commerciale se capannone
        postParameters.add(new BasicNameValuePair("garage", "")); //commerciale
        postParameters.add(new BasicNameValuePair("idContrattoAffitto", "")); //Se affitto
        postParameters.add(new BasicNameValuePair("spese_condominiali", ""));
        postParameters.add(new BasicNameValuePair("terreno_proprieta", ""));
        postParameters.add(new BasicNameValuePair("spese", "")); //Se affitto
        
        valutaClasse_energetica();
        postParameters.add(new BasicNameValuePair("classe_energetica", classe_energetica));
        
        //postParameters.add(new BasicNameValuePair("certificato", "")); se classe_energetica non è indicata allora questo è "on"
        
        postParameters.add(new BasicNameValuePair("ipe", "≥ 175"));
        postParameters.add(new BasicNameValuePair("ipe_unita_misura", "m2"));
        postParameters.add(new BasicNameValuePair("hidden_ipe", "-1"));
        postParameters.add(new BasicNameValuePair("hidden_default_mq", "≥ 175"));
        postParameters.add(new BasicNameValuePair("hidden_default_mc", "≥ 65"));
        postParameters.add(new BasicNameValuePair("costruttore", ""));
        postParameters.add(new BasicNameValuePair("statoCantiere", ""));
        postParameters.add(new BasicNameValuePair("gtxIngresso", "0")); //default
        postParameters.add(new BasicNameValuePair("gtxRipostiglio", "0"));	//default
        postParameters.add(new BasicNameValuePair("gtxCantina", "0"));//default
        postParameters.add(new BasicNameValuePair("gtxMansarda", "0"));//default
        postParameters.add(new BasicNameValuePair("gtxTaverna", "0"));//default
        postParameters.add(new BasicNameValuePair("gtxInfissiEsterni", "0"));//default
        postParameters.add(new BasicNameValuePair("gtxImpiantoTv", "0"));//default
        postParameters.add(new BasicNameValuePair("flag_auto_it", ""));
        
        valutaDescrizione_it();
        postParameters.add(new BasicNameValuePair("descrizione_it", descrizione_it));
        
        postParameters.add(new BasicNameValuePair("flag_auto_en", ""));
        postParameters.add(new BasicNameValuePair("descrizione_en", ""));
        postParameters.add(new BasicNameValuePair("flag_auto_de", ""));
        postParameters.add(new BasicNameValuePair("descrizione_de", ""));
        postParameters.add(new BasicNameValuePair("flag_auto_fr", ""));
        postParameters.add(new BasicNameValuePair("descrizione_fr", ""));
        postParameters.add(new BasicNameValuePair("flag_auto_es", ""));
        postParameters.add(new BasicNameValuePair("descrizione_es", ""));
        postParameters.add(new BasicNameValuePair("flag_auto_pt", ""));
        postParameters.add(new BasicNameValuePair("descrizione_pt", ""));
        postParameters.add(new BasicNameValuePair("flag_auto_ru", ""));
        postParameters.add(new BasicNameValuePair("descrizione_ru", ""));
        postParameters.add(new BasicNameValuePair("flag_auto_gr", ""));
        postParameters.add(new BasicNameValuePair("descrizione_gr", ""));
        
        valutaRemLen_it();
        postParameters.add(new BasicNameValuePair("remLen_it", remLen_it));
        
        postParameters.add(new BasicNameValuePair("remLen_en", "3000"));
        postParameters.add(new BasicNameValuePair("remLen_de", "3000"));
        postParameters.add(new BasicNameValuePair("remLen_fr", "3000"));
        postParameters.add(new BasicNameValuePair("remLen_es", "3000"));
        postParameters.add(new BasicNameValuePair("remLen_pt", "3000"));
        postParameters.add(new BasicNameValuePair("remLen_ru", "3000"));
        postParameters.add(new BasicNameValuePair("remLen_gr", "3000"));
        postParameters.add(new BasicNameValuePair("gtxTitolo_per_riviste", ""));
        postParameters.add(new BasicNameValuePair("gtxDescrizione_per_riviste", ""));
        postParameters.add(new BasicNameValuePair("virtual_tour", ""));
        postParameters.add(new BasicNameValuePair("callback", "submit"));                        
        
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

        //Response properties
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        	//Get the redirect location
        	if(currentHeader.getName().contains("Location")) {
        		LOCATION = currentHeader.getValue();
        		System.out.println("Location value: " + LOCATION);
        		int start = LOCATION.indexOf("idAnnuncio=") + 11;
		        int end = LOCATION.length();
        		CODICEINSERZIONE = LOCATION.substring(start, end);
        		System.out.println("Codice inserzione: " + CODICEINSERZIONE);
        	}
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);		        
	        System.out.println("----------------------------------------");
        }                                             
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //GET della pagina "Backoffice" (step 3)
    private void connessione_7(HttpGet httpget) throws Exception {
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
        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
        cookie.setPath("/");
        
        cookieStore.addCookie(cookie); 
        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);

        //Send the request
        HttpResponse response = httpclient.execute(httpget);

        //Response properties
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
        
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
        
    //POST della pagina "Backoffice" (step 3)
    private void connessione_8(HttpPost httppost) throws Exception {
    	System.out.println("CONNESSIONE 8");
    	HttpClient httpclient = new DefaultHttpClient();
    	
    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("action", "save"));
        //postParameters.add(new BasicNameValuePair("callback", "refresh")); //l'ultima volta questo non c'er più
        
        valutaCap();
        postParameters.add(new BasicNameValuePair("cap", cap));
        
        postParameters.add(new BasicNameValuePair("flagZona", ""));
        postParameters.add(new BasicNameValuePair("idAnnuncio", CODICEINSERZIONE));
        
        postParameters.add(new BasicNameValuePair("idLocalita", ""));
        postParameters.add(new BasicNameValuePair("idNazione", "IT"));
               
        valutaIdRegione();
        postParameters.add(new BasicNameValuePair("idRegione", idRegione));
        
        valutaIdProvincia();
        postParameters.add(new BasicNameValuePair("idProvincia", idProvincia));
        
        valutaIdComune();
        postParameters.add(new BasicNameValuePair("idComune", idComune));
        
        postParameters.add(new BasicNameValuePair("idZona", ""));
        
        valutaIndirizzo();
        postParameters.add(new BasicNameValuePair("indirizzo", indirizzo));
        
        valutaLatitudineELongitudine();
        postParameters.add(new BasicNameValuePair("latitudine", latitudine));
        postParameters.add(new BasicNameValuePair("longitudine", longitudine));
        //postParameters.add(new BasicNameValuePair("nascondiIndirizzo", "on"));
        postParameters.add(new BasicNameValuePair("flagIndirizzo2", "on"));
        postParameters.add(new BasicNameValuePair("numeroCivico", ""));
        postParameters.add(new BasicNameValuePair("step", "3"));
        postParameters.add(new BasicNameValuePair("sublocality", ""));
        postParameters.add(new BasicNameValuePair("tipo", categoria));
        postParameters.add(new BasicNameValuePair("youTubeCode", ""));
        postParameters.add(new BasicNameValuePair("zoom", ""));                      
        
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

        //Response properties
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        	//Get the redirect location
        	if(currentHeader.getName().contains("Location")) {
        		LOCATION = currentHeader.getValue();
        		System.out.println("Location value: " + LOCATION);
        	}
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);		        
	        System.out.println("----------------------------------------");
        }                                             
        
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //GET della pagina "Backoffice" (step 4)
    /*private void connessione_9(HttpGet httpget) {
    	System.out.println("CONNESSIONE 9");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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

            //Response properties
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
                    
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }*/
   
        
    //POST della pagina "Backoffice" (step 4)
    private void connessione_10(HttpPost httppost) throws Exception {
    	System.out.println("CONNESSIONE 10");
    	HttpClient httpclient = new DefaultHttpClient();
    	
    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("SelTipoPagamento", "borsellino"));
        postParameters.add(new BasicNameValuePair("address1", ""));
        postParameters.add(new BasicNameValuePair("amount", ""));
        postParameters.add(new BasicNameValuePair("azionePayPal", "compraSubito"));
        postParameters.add(new BasicNameValuePair("bn", "PP-BuyNowBF"));
        postParameters.add(new BasicNameValuePair("business", "SELLER"));
        postParameters.add(new BasicNameValuePair("check_1", "on")); //inserimanto in immobiliare.it
        postParameters.add(new BasicNameValuePair("cmd", "_xclick"));
        postParameters.add(new BasicNameValuePair("currency_code", "EUR"));
        postParameters.add(new BasicNameValuePair("first_name", ""));
        postParameters.add(new BasicNameValuePair("idAnnuncio", CODICEINSERZIONE));
        postParameters.add(new BasicNameValuePair("item_name", "acquisto pubblicazione e visibilita"));
        postParameters.add(new BasicNameValuePair("item_number", "pubblicazione"));
        postParameters.add(new BasicNameValuePair("last_name", ""));
        postParameters.add(new BasicNameValuePair("lc", "IT"));
        postParameters.add(new BasicNameValuePair("no_note", "1"));
        postParameters.add(new BasicNameValuePair("no_shipping", "1"));
        postParameters.add(new BasicNameValuePair("notify_url", "NOTIFY_URL"));
        postParameters.add(new BasicNameValuePair("prezzoTotale", ""));
        postParameters.add(new BasicNameValuePair("return", URL_ROOT + "/inserimento_annuncio.php?step=5&azionePayPal=notifica&idAnnuncio=" + CODICEINSERZIONE + "&tipo=" + "1" + "&buyedVis=0"));
        postParameters.add(new BasicNameValuePair("return_cancel", URL_ROOT + "/inserimento_annuncio.php?step=4&azionePayPal=show&idAnnuncio=" + CODICEINSERZIONE + "&tipo=" + "1" + "&buyedVis=0"));
        postParameters.add(new BasicNameValuePair("richiesta", ""));
        postParameters.add(new BasicNameValuePair("step", "4"));
        postParameters.add(new BasicNameValuePair("tipo", categoria));
        postParameters.add(new BasicNameValuePair("zip", ""));                      
        
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

        //Response properties
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	Header currentHeader = responseHeaders[i];
        	System.out.println(currentHeader);
        	//Get the redirect location
        	if(currentHeader.getName().contains("Location")) {
        		LOCATION = currentHeader.getValue();
        		System.out.println("Location value: " + LOCATION);
        	}
        }
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);		        
	        System.out.println("----------------------------------------");
        }                                             

        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
        }
    
    
    //GET della pagina "Backoffice" (step 5)
    private void connessione_11(HttpGet httpget) throws Exception {
    	System.out.println("CONNESSIONE 11");
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

        //Response properties
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
            
            Element OKElement = doc.getElementById("msg");        
            if(OKElement!=null && OKElement.text().contains("Immobile inserito correttamente")) {
            	System.out.println("Elemento di controllo inserimento: " + OKElement.html());
            	inserimentoOK = true;
            }
            System.out.println("----------------------------------------");
        }  
                
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
        }
       
    
    //GET della pagina "Backoffice" (eliminazione)
    private void connessione_12(HttpGet httpget) throws Exception {
    	System.out.println("CONNESSIONE 12");
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

        //Response properties
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
               
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina "Backoffice" (step 3) per il recupero di idProvincia
    private void connessione_13(HttpPost httppost, String provincia) throws Exception {
    	System.out.println("CONNESSIONE 13");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("xjxargs[]", "S" + idRegione));
        postParameters.add(new BasicNameValuePair("xjxargs[]", "S" + "IT"));
        postParameters.add(new BasicNameValuePair("xjxargs[]", "N0"));
        postParameters.add(new BasicNameValuePair("xjxfun", "getAjaxProvincePagIndex"));
        postParameters.add(new BasicNameValuePair("xjxr", Long.toString(new Date().getTime())));
         
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

        //Response properties
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
	        
	        //Get the idProvincia parameter
	        int start = responseBody.indexOf("<select name=\"idProvincia");
	        int end = responseBody.lastIndexOf("</select");
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody.substring(start, end));           

            Elements optionsProvincia = doc.getElementsByTag("option"); 
            Iterator<Element> iterator = optionsProvincia.iterator();
            //Comparazione della similarità tra stringhe
            double resultComparation = 0;
            while(iterator.hasNext()) {
            	Element currentOption = iterator.next();
            	
            	List<char[]> charListPortale = bigram(currentOption.text());
        		List<char[]> charListImagination = bigram(provincia);
        		
        		double actualResultComparation = dice(charListPortale, charListImagination);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			idProvincia = currentOption.attr("value");            		
        		}       		
        		System.out.println("Risultato comparazione: " + resultComparation);
        		System.out.println("idProvincia: " + idProvincia);      		
            }
            System.out.println("----------------------------------------");
        }                                             

        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina "Backoffice" (step 3) per il recupero di idComune
    private void connessione_14(HttpPost httppost, String comune) throws Exception {
    	System.out.println("CONNESSIONE 14");
    	HttpClient httpclient = new DefaultHttpClient();
    	
    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        postParameters.add(new BasicNameValuePair("xjxargs[]", "S" + idProvincia));
        postParameters.add(new BasicNameValuePair("xjxargs[]", "S" + idRegione));
        postParameters.add(new BasicNameValuePair("xjxargs[]", "S" + "IT"));           
        postParameters.add(new BasicNameValuePair("xjxfun", "getAjaxComuniPagIndex"));
        postParameters.add(new BasicNameValuePair("xjxr", Long.toString(new Date().getTime())));
         
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

        //Response properties
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
	        
	        //Get the idProvincia parameter
	        int start = responseBody.indexOf("<select name=\"idComune");
	        int end = responseBody.lastIndexOf("</select");
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody.substring(start, end));           

            Elements optionsProvincia = doc.getElementsByTag("option"); 
            Iterator<Element> iterator = optionsProvincia.iterator();
            //Comparazione della similarità tra stringhe
            double resultComparation = 0;
            while(iterator.hasNext()) {
            	Element currentOption = iterator.next();
            	
            	List<char[]> charListPortale = bigram(currentOption.text());
        		List<char[]> charListImagination = bigram(comune);
        		
        		double actualResultComparation = dice(charListPortale, charListImagination);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			idComune = currentOption.attr("value");            		
        		}       		
        		System.out.println("Risultato comparazione: " + resultComparation);
        		System.out.println("idComune: " + idComune);      		
            }
            System.out.println("----------------------------------------");
        }                                             
        
        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina "Backoffice" (step 3) per l'inserimento delle immagini
    private void connessione_15(HttpPost httppost, File image) throws Exception {
    	System.out.println("CONNESSIONE 15");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("action", new StringBody("add"));
        reqEntity.addPart("imageid", new StringBody(""));
        reqEntity.addPart("pdftoremove", new StringBody(""));
        reqEntity.addPart("tipo", new StringBody(categoria));
        reqEntity.addPart("idAnnuncio", new StringBody(CODICEINSERZIONE));
        //L'immagine
        System.out.println("Inserimento immagine: " + image.getName());
        FileBody bin = new FileBody(image);
        reqEntity.addPart("immagine", bin );

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

        //Response properties
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

        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
    
    
    //POST della pagina "Backoffice" (step 4) per l'inserimento nei vari portali partner di Getrix
    private void connessione_16(HttpPost httppost) throws Exception {
    	System.out.println("CONNESSIONE 16");
    	HttpClient httpclient = new DefaultHttpClient();

    	//Request URL
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
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
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("action", new StringBody("add"));
        reqEntity.addPart("idAnnuncio", new StringBody(CODICEINSERZIONE));
        reqEntity.addPart("idPortale", new StringBody("1")); //1 è l'id di immobiliare.it             

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

        //Response properties
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

        //Rilascia le risorse create
        httpclient.getConnectionManager().shutdown();
    }
         
       
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public void inserisciScheda(SchedaImmobile scheda) {
    	System.out.println("Inserimento scheda: " + scheda.codice + "...");
    	
    	//Inizializzazione parametri
    	this.scheda=scheda;
    	
        //connessione_0(new HttpGet(URL_ROOT)); //Non necessaria 
        //connessione_1(new HttpGet(URL_ROOT + "/pubblica-annunci")); //Da non usare
           	
		try {
			//Se le credenziali non sono valide esce dal metodo e ritorna il controllo al pannello chiamante
			connessione_2(new HttpPost(URL_ROOT));
			//Ritorna il controllo all'oggetto chiamante se le credenziali non sono valide
			if(!credenzialiValide) {
				return;
			}
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}

    	//connessione_3(new HttpGet(URL_ROOT + LOCATION)); //Non necessaria   	
    	//connessione_4(new HttpGet(URL_ROOT + "/inserimento_annuncio.php")); //Non necessaria 
    	
    	//Determino la Url della prossima connessione (step 1)  
        valutaContrattoECategoria();
		
        /*try {
			connessione_5(new HttpGet(URL_ROOT + "/inserimento_annuncio.php" + "?step=2&categoria=" + categoria + "&tipo=" + contratto + "&callback=refresh"));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}*/
  
		try {
			connessione_6(new HttpPost(URL_ROOT + "/inserimento_annuncio.php" + "?step=2&categoria=" + categoria + "&tipo=" + contratto + "&callback=refresh"));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}     
       
		try {
			connessione_7(new HttpGet(URL_ROOT + LOCATION));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}
       
        //Inserimeno immagini
        if(scheda.immagine1!=null) {       	
        	try {
				connessione_15(new HttpPost("http://media.getrix.ekbl.net/inserimento_immagini.php"), scheda.immagine1);
			} catch (Exception e) {
				sendErrorMail(readStackTrace(e), "Errore I030-3");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento delle immagini nella scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}	
        }
        if(scheda.immagine2!=null) {      	
			try {
				connessione_15(new HttpPost("http://media.getrix.ekbl.net/inserimento_immagini.php"), scheda.immagine2);
			} catch (Exception e) {
				sendErrorMail(readStackTrace(e), "Errore I030-3");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento delle immagini nella scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
        if(scheda.immagine3!=null) {     
			try {
				connessione_15(new HttpPost("http://media.getrix.ekbl.net/inserimento_immagini.php"), scheda.immagine3);
			} catch (Exception e) {
				sendErrorMail(readStackTrace(e), "Errore I030-3");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento delle immagini nella scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}	
        }
        if(scheda.immagine4!=null) {
			try {
				connessione_15(new HttpPost("http://media.getrix.ekbl.net/inserimento_immagini.php"), scheda.immagine4);
			} catch (Exception e) {
				sendErrorMail(readStackTrace(e), "Errore I030-3");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento delle immagini nella scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}		
        }
        if(scheda.immagine5!=null) {        	
			try {
				connessione_15(new HttpPost("http://media.getrix.ekbl.net/inserimento_immagini.php"), scheda.immagine5);
			} catch (Exception e) {
				sendErrorMail(readStackTrace(e), "Errore I030-3");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento delle immagini nella scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}		
        }
        if(scheda.immagine6!=null) {
			try {
				connessione_15(new HttpPost("http://media.getrix.ekbl.net/inserimento_immagini.php"), scheda.immagine6);
			} catch (Exception e) {
				sendErrorMail(readStackTrace(e), "Errore I030-3");
	            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento delle immagini nella scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
			}
        }
            
		try {
			connessione_8(new HttpPost(URL_ROOT + LOCATION));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}
		      
		
		//Inserimento sui portali partner di Getrix
		try {
			connessione_16(new HttpPost("http://getrix.ekbl.net/registraAnnuncio.php"));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
		}
               
		try {
			connessione_10(new HttpPost(URL_ROOT + "/" + LOCATION));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}       
        
		try {
			connessione_11(new HttpGet(URL_ROOT + "/" + LOCATION));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}
		
		//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
    	if(inserimentoOK) {
    		System.out.println("Inserita in: " + NOMEPORTALE);
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, CODICEINSERZIONE);
            
        	//Aggiorna i pulsanti del pannello inserimento 	
        	Main.imaginationGUI.pannelloInserimento.updatePanello(scheda);
        	
        	//Invio mail di conferma inserimento (in sviluppo...)
        	sendConfirmationMail(scheda, NOMEPORTALE, CODICEINSERZIONE);
       	
        	//Stampo a video un messaggio informativo
            JOptionPane.showMessageDialog(null, "Scheda immobile inserita in: " + NOMEPORTALE, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else {
    		//Stampo a video un messaggio informativo
    		JOptionPane.showMessageDialog(null, "Problemi nell'inserimento scheda in: " + NOMEPORTALE, "Errore", JOptionPane.ERROR_MESSAGE);
    	}
        
	}
	
    
    //Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public void visualizzaScheda(SchedaImmobile scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codice + "...");
		//Apro il browser e inserisco credenziali		
		try {
			String url = URL_ROOT  + "/annunci_agenzia.php?stato=attivo";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e) {
			sendErrorMail(readStackTrace(e), "Errore I030-4");
            JOptionPane.showMessageDialog(null, "Errore durante la visualizzazione della scheda. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-4", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
		}
		System.out.println("Visualizzata in: " + NOMEPORTALE);
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public void cancellaScheda(SchedaImmobile scheda) {
		System.out.println("Eliminazione scheda: " + scheda.codice + "...");
			
		try {
			connessione_2(new HttpPost(URL_ROOT));
			//Se le credenziali non sono valide esce dal metodo e ritorna il controllo al pannello chiamante
			if(!credenzialiValide) {
				return;
			}
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-5");
            JOptionPane.showMessageDialog(null, "Errore durante la cancellazione della scheda. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-5", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}
		
		try {
			connessione_12(new HttpGet(URL_ROOT + "/annunci_agenzia.php?multimedia=no&action=delete&stato=attivo&newStatus=&pag=1&selectedItems=&idAnnuncio=&numAnnunci=20&selectAll=true&idAnnuncio%5B%5D=" + scheda.getCodiceInserimento(idPortale)));
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante la cancellazione della scheda. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
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
	
	
	
	
	//Metodi per la valutazione dei parametri
	
	public void valutaFasciaprezzo() {
		int prezzo = scheda.prezzo;

		switch (scheda.contratto)
    	{
    	    case "Vendita":
    	    	if(prezzo==0){
    	        	fasciaprezzo = "12";
    	        }
    	        if(prezzo>500000){
    	        	fasciaprezzo = "6";
    	        }
    	        if(prezzo<=500000){
    	        	fasciaprezzo = "5";
    	        }
    	        if(prezzo<=300000){
    	        	fasciaprezzo = "4";
    	        }
    	        if(prezzo<=200000){
    	        	fasciaprezzo = "3";
    	        }
    	        if(prezzo<=150000){
    	        	fasciaprezzo = "2";
    	        }
    	        if(prezzo<=100000){
    	        	fasciaprezzo = "1";
    	        }
    	        break;
    	    case "Affitto":
    	    	if(prezzo==0){
    	        	fasciaprezzo = "13";
    	        }
    	    	if(prezzo>0){
    	        	fasciaprezzo = "7";
    	        }
    	    	if(prezzo>500){
    	        	fasciaprezzo = "8";
    	        }
    	    	if(prezzo>1000){
    	        	fasciaprezzo = "9";
    	        }
		        if(prezzo>1500){
		        	fasciaprezzo = "10";
		        }
    	        if(prezzo>2000){
    	        	fasciaprezzo = "11";
    	        }
    	    	break;
    	}
		
		
	        

        System.out.println("fasciaprezzo: " + fasciaprezzo);
	}
	
	public void valutaContrattoECategoria() {
		switch (scheda.contratto)
    	{
    	    case "Vendita":
    	    	contratto = "1";
    	        break;
    	    case "Affitto":
    	    	contratto = "2";
    	    	break;
    	}
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
    	    case "Turistico":
    	    	categoria = "3";
    	    	contratto = "2"; //Se "Turistico" l'affitto è predefinito
    	    	break;
    	    default:
    	    	categoria = "1";
    	}
        System.out.println("contratto: " + contratto);
        System.out.println("categoria: " + categoria);
	}
	
	public void valutaGtxNumeroCamereDaLetto() {
		gtxNumeroCamereDaLetto = Integer.toString(scheda.numCamere);
		System.out.println("gtxNumeroCamereDaLetto: " + gtxNumeroCamereDaLetto);
	}
	
	public void valutaCodice() {
		//Problemi con l'encoding...
		codice = codice.replace("à", "Ã ");
		codice = codice.replace("è", "Ã¨");
		codice = codice.replace("ì", "Ã¬");
		codice = codice.replace("ò", "Ã²");
		codice = codice.replace("ù", "Ã¹");
		codice = codice.replace("€", "â‚¬");
		codice = codice.replace("&", "Â£ ");
		codice = scheda.codice;
		
		System.out.println("codice: " + codice);
	}
	
	public void valutaPrezzo() {
		prezzo = Integer.toString(scheda.prezzo);
		System.out.println("prezzo: " + prezzo);
	}
	
	public void valutaTipologia() {
		switch (scheda.tipologia)
    	{
    	    case "Appartamento":
    	    	tipologia = "4";
    	        break;
    	    case "Attico":
    	    	tipologia = "5";
    	        break;
    	    case "Bifamiliare":
    	    	tipologia = "14"; //Non hnno una voce per bifamiliare
    	        break;
    	    case "Casa":
    	    	tipologia = "7";
    	        break;
    	    case "Villaschiera":
    	    	tipologia = "13";
    	        break;
    	    case "Villa":
    	    	tipologia = "12";
    	        break;
    	    case "Rustico":
    	    	tipologia = "11";
    	        break;
    	    case "Ufficio":
    	    	tipologia = "63";
    	        break;
    	    case "Negozio":
    	    	tipologia = "16";
    	        break;
    	    case "Capannone":
    	    	tipologia = "63";
    	        break;
    	    case "Magazzino":
    	    	tipologia = "63";
    	        break;
    	    case "Laboratorio":
    	    	tipologia = "63";
    	        break;
    	    case "Albergo":
    	    	tipologia = "63";
    	        break;
    	    case "Terreno":
    	    	tipologia = "28";
    	        break;
    	    case "Garage":
    	    	tipologia = "6";
    	        break;
    	    case "Palazzo":
    	    	tipologia = "10";
    	        break;
    	    case "Bar":
    	    	tipologia = "16";
    	        break;
    	    case "Ristorante":
    	    	tipologia = "16";
    	        break;
    	    case "Agriturismo":
    	    	tipologia = "16";
    	        break;
    	}
		System.out.println("tipologia: " + tipologia);
	}
	
	public void valutaSottotipologia() {
		switch (scheda.tipologia)
    	{
    	    case "Ufficio":
    	    	sottotipologia = "81";
    	        break;
    	    case "Negozio":
    	    	sottotipologia = "79";
    	        break;
    	    case "Capannone":
    	    	sottotipologia = "73";
    	        break;
    	    case "Magazzino":
    	    	sottotipologia = "91";
    	        break;
    	    case "Laboratorio":
    	    	sottotipologia = "99";
    	        break;
    	    case "Albergo":
    	    	sottotipologia = "101";
    	        break;
    	    case "Terreno":
    	    	sottotipologia = "71";	//ci sono altre tipologie di terreno...
    	        break;
    	    case "Bar":
    	    	sottotipologia = "7";
    	        break;
    	    case "Ristorante":
    	    	sottotipologia = "3";
    	        break;
    	    case "Agriturismo":
    	    	sottotipologia = "9";
    	        break;
    	}
		System.out.println("sottotipologia: " + sottotipologia);
	}
	
	public void valutaLocali() {
		switch (scheda.numLocali)
    	{
    	    case "1":
    	    	locali = "1";
    	        break;
    	    case "2":
    	    	locali = "2";
    	    	break;
    	    case "3":
    	    	locali = "3";
    	    	break;
    	    case "4":
    	    	locali = "4";
    	    	break;
    	    case "5":
    	    	locali = "5";
    	    	break;
    	    default:
    	    	locali = ">5";
    	}
        System.out.println("locali: " + locali);
	}
	
	public void valutaSuperficie() {
		superficie = Integer.toString(scheda.supAbitazione);
		System.out.println("superficie: " + superficie);
	}
	
	public void valutaClasse_energetica() {
		switch (scheda.certificazioni)
    	{
    	    case "Nessuna":
    	    	classe_energetica = "NC";
    	        break;
    	    case "Certificazione energetica A++":
    	    	classe_energetica = "A+";
    	    	break;
    	    case "Certificazione energetica A+":
    	    	classe_energetica = "A+";
    	    	break;
    	    case "Certificazione energetica A":
    	    	classe_energetica = "A";
    	    	break;
    	    case "Certificazione energetica B":
    	    	classe_energetica = "B";
    	    	break;
    	    case "Certificazione energetica C":
    	    	classe_energetica = "C";
    	    	break;
    	    case "Certificazione energetica D":
    	    	classe_energetica = "D";
    	    	break;
    	    case "Certificazione energetica E":
    	    	classe_energetica = "E";
    	    	break;
    	    case "Certificazione energetica F":
    	    	classe_energetica = "F";
    	    	break;
    	    case "Certificazione energetica G":
    	    	classe_energetica = "G";
    	    	break;
    	    default:
    	    	classe_energetica = "NC";
    	}
        System.out.println("classe_energetica: " + classe_energetica);
	}
	
	public void valutaDescrizione_it() {
		descrizione_it = scheda.descrEstesa;
		//Problemi con l'encoding...
		descrizione_it = descrizione_it.replace("�", "a'");
		descrizione_it = descrizione_it.replace("�", "è");
		descrizione_it = descrizione_it.replace("�", "ì");
		descrizione_it = descrizione_it.replace("�", "ò");
		descrizione_it = descrizione_it.replace("�", "ù");
		descrizione_it = descrizione_it.replace("�", "€");
		descrizione_it = descrizione_it.replace("&", "£ ");
		System.out.println("descrizione_it: " + descrizione_it);
	}
	
	public void valutaRemLen_it() {
		int lunghezzaDescrizione = descrizione_it.length();
		remLen_it = Integer.toString(3000 - lunghezzaDescrizione);
		System.out.println("remLen_it: " + remLen_it);
	}
	
	public void valutaIdRegione() {
		//int start = scheda.provincia.indexOf("(")+1;
		//int end = scheda.provincia.indexOf(")");
		//String regione = scheda.provincia.substring(start, end);
		regione = scheda.regione;
		System.out.println("regione: " + regione);
		switch (regione)
    	{
    	    case "Abruzzo":
    	    	idRegione = "abr";
    	        break;
    	    case "Basilicata":
    	    	idRegione = "bas";
    	    	break;
    	    case "Calabria":
    	    	idRegione = "cal";
    	    	break;
    	    case "Campania":
    	    	idRegione = "cam";
    	    	break;
    	    case "Emilia-Romagna":
    	    	idRegione = "emi";
    	    	break;
    	    case "Friuli-Venezia Giulia":
    	    	idRegione = "fri";
    	    	break;
    	    case "Lazio":
    	    	idRegione = "laz";
    	    	break;
    	    case "Liguria":
    	    	idRegione = "lig";
    	    	break;
    	    case "Lombardia":
    	    	idRegione = "lom";
    	    	break;
    	    case "Marche":
    	    	idRegione = "mar";
    	    	break;
    	    case "Molise":
    	    	idRegione = "mol";
    	    	break;
    	    case "Piemonte":
    	    	idRegione = "pie";
    	    	break;
    	    case "Puglia":
    	    	idRegione = "pug";
    	    	break;
    	    case "Sardegna":
    	    	idRegione = "sar";
    	    	break;
    	    case "Sicilia":
    	    	idRegione = "sic";
    	    	break;
    	    case "Toscana":
    	    	idRegione = "tos";
    	    	break;
    	    case "Trentino-Alto Adige":
    	    	idRegione = "tre";
    	    	break;
    	    case "Umbria":
    	    	idRegione = "umb";
    	    	break;
    	    case "Valle d'Aosta":
    	    	idRegione = "val";
    	    	break;
    	    case "Veneto":
    	    	idRegione = "ven";
    	    	break;
    	    default:
    	    	idRegione = "fri";
    	}		
		System.out.println("idRegione: " + idRegione);
	}
 
	public void valutaIdProvincia() {
		//int end = scheda.provincia.indexOf(" ");
		//String provincia = scheda.provincia.substring(0, end);
		provincia = scheda.provincia;
		
		try {
			connessione_13(new HttpPost(URL_ROOT + LOCATION), provincia);
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
		}
			
		System.out.println("idProvincia: " + idProvincia);
	}
	
	public void valutaIdComune() {
		comune = scheda.comune;

		try {
			connessione_14(new HttpPost(URL_ROOT + LOCATION), comune);
		} catch (Exception e) {
			sendErrorMail(readStackTrace(e), "Errore I030-3");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore I030-3", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
		}	
		System.out.println("idComune: " + idComune);
	}
	
	public void valutaCap() {
		cap = scheda.cap;
		System.out.println("cap: " + cap);
	}
	
	public void valutaIndirizzo() {
		indirizzo = scheda.via;
		indirizzo = indirizzo.replace("à", "Ã ");
		indirizzo = indirizzo.replace("è", "Ã¨");
		indirizzo = indirizzo.replace("ì", "Ã¬");
		indirizzo = indirizzo.replace("ò", "Ã²");
		indirizzo = indirizzo.replace("ù", "Ã¹");
		indirizzo = indirizzo.replace("€", "â‚¬");
		indirizzo = indirizzo.replace("&", "Â£ ");
		System.out.println("indirizzo: " + indirizzo);
	}
	
	public void valutaGtxAnnoCostruzione() {
		gtxAnnoCostruzione = scheda.annoCostr;
	}
	
	public void valutaPiano() {
		piano = scheda.piano;
	}

	public void valutaNumeroPiani() {
		numeroPiani = scheda.totPiani;
	}
	
	public void valutaStato() {
		switch (scheda.statoImmobile)
    	{
    	    case "Nuovo":
    	    	stato = "1";
    	        break;
    	    case "Ristrutturato":
    	    	stato = "6";
    	    	break;
    	    case "Da ristrutturare":
    	    	stato = "5";
    	    	break;
    	    case "In buono stato":
    	    	stato = "2";
    	    	break;
    	    case "Abitabile":
    	    	stato = "2";
    	    	break;
    	    case "Ottimo":
    	    	stato = "6";
    	    	break;
    	    case "In costruzione":
    	    	stato = "1";
    	    	break;
    	    default:
    	    	stato = "";
    	}
        System.out.println("stato: " + stato);
	}
	
	public void valutaLatitudineELongitudine() {
		
		try {
			String entrambi = getCoord(indirizzo, comune, provincia, regione);
			latitudine=entrambi.substring(0, entrambi.indexOf("$$$"));
			longitudine=entrambi.substring(entrambi.indexOf("$$$")+3, entrambi.length());
			System.out.println("latitudine: " + latitudine);
			System.out.println("longitudine: " + longitudine);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			sendErrorMail(readStackTrace(e), "Errore");
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della scheda, si prega di verificare i dati inseriti. \n E' stato inviato un rapporto allo sviluppatore del software", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
		}
		
		
		
		
	}
		
}