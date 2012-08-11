/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.File;
import java.io.IOException;
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
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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
public class PagineCasa extends PortaleImmobiliare {     

    //Parametri generali
	private final String NOMEPORTALE = "paginecasa.com";
	private final String CODICE_PORTALE = "005";
	private final String URL_ROOT = "http://paginecasa.com";
	private final String USERNAME = "vgltoove@sharklasers.com";
    private final String PASSWORD = "test1234";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:12.0) Gecko/20100101 Firefox/12.0";
    private String CODICEINSERZIONE;
    private boolean INSERIMENTO_OK = false;
    private String sys_sid = ""; //è un input hidden passato al momento del submit di una nuova scheda
    
    //Parametri scheda (elaborati in base al portale)
    String tipo = "";
    String classe = "";
    String prov = "";
    String com = "";
    String loc = "";
    String mq = "";
    String pre = "";
    String desc = "";
    File image1;
    File image2;
    File image3;
    File image4;
    File image5;
    File image6;

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   
	  
	//Costruttore
	public PagineCasa (String urlIcona, String valoreLabel, String idPortale) {		
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
    
    
    
    //POST della pagina login
	private void connessione_1(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
    	System.out.println("CONNESSIONE 1");
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
        postParameters.add(new BasicNameValuePair("Entra.x", "10"));
        postParameters.add(new BasicNameValuePair("Entra.y", "10"));
        postParameters.add(new BasicNameValuePair("cmd", "enter"));
        postParameters.add(new BasicNameValuePair("nav", "0"));
        postParameters.add(new BasicNameValuePair("pwd", PASSWORD));
        postParameters.add(new BasicNameValuePair("usr", USERNAME));
        //Stampa dei parametri inviati
        printSentParameters(postParameters);
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
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
	        System.out.println("Response body: \n");
	        String responseBody = responseHandler.handleResponse(response);
	        System.out.println(responseBody);	        
	        System.out.println("----------------------------------------"); 
	        
	        System.out.println("Parsing della risposta"); 
	        org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);
	        Elements elements = doc.select("input[name=sys_sid]");
	        if(elements.isEmpty()) {
            	throw(new HttpResponseException("Elemento non trovato"));
            }
	        else {
            	Iterator<Element> iterator = elements.iterator();
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next(); 
	            	sys_sid = currentElement.attr("value");
	            	System.out.println("sys_sid: " + sys_sid); 
            	}
            }
        }
        if(!response.getStatusLine().toString().contains("200")) {
        	throw(new HttpResponseException("Response code"));
        }
        
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
    }
    
        
    
    //POST dello pagina inserimento immobile
	private void connessione_2(HttpPost httppost) throws IOException, HttpResponseException, WrongPostDataException {
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
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("cmd", new StringBody("newschsave"));
        reqEntity.addPart("sys_sid", new StringBody(sys_sid));
        reqEntity.addPart("nav", new StringBody("0"));
        reqEntity.addPart("cod", new StringBody(scheda.codice));
        reqEntity.addPart("cont", new StringBody("v"));
        reqEntity.addPart("tipo", new StringBody("r#Appartamento"));
        reqEntity.addPart("classe", new StringBody(classe));
        if(image1!=null) {
			 System.out.println("Inserimento immagine: " + image1.getName());
		     FileBody bin1 = new FileBody(image1);
		     reqEntity.addPart("foto1", bin1 );
        }
        if(image2!=null) {
        	System.out.println("Inserimento immagine: " + image2.getName());
            FileBody bin2 = new FileBody(image2);
            reqEntity.addPart("foto2", bin2 );
        }
        if(image3!=null) {
        	System.out.println("Inserimento immagine: " + image3.getName());
            FileBody bin3 = new FileBody(image3);
            reqEntity.addPart("foto3", bin3 );
        }
        if(image4!=null) {
        	System.out.println("Inserimento immagine: " + image4.getName());
            FileBody bin4 = new FileBody(image4);
            reqEntity.addPart("foto4", bin4 );
        }
        if(image5!=null) {
        	System.out.println("Inserimento immagine: " + image5.getName());
            FileBody bin5 = new FileBody(image5);
            reqEntity.addPart("foto5", bin5 );
        }
        if(image6!=null) {
        	System.out.println("Inserimento immagine: " + image6.getName());
            FileBody bin6 = new FileBody(image6);
            reqEntity.addPart("foto6", bin6 );
        }
        reqEntity.addPart("prov", new StringBody(prov));
        reqEntity.addPart("com", new StringBody(com));
        reqEntity.addPart("loc", new StringBody(loc));
        reqEntity.addPart("mq", new StringBody(mq));
        reqEntity.addPart("pre", new StringBody(pre));
        reqEntity.addPart("desc", new StringBody(desc));
        httppost.setEntity(reqEntity);

        //Send the request
        HttpResponse response = httpclient.execute(httppost);

        //Show response headers
        ResponseHandler<String> responseHandler = new BasicResponseHandler();           
        Header[] responseHeaders;
        responseHeaders = response.getAllHeaders(); 
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");               
        System.out.println("----------------------------------------");
            
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
            System.out.println("Response body: \n");
            String responseBody = responseHandler.handleResponse(response);
            System.out.println(responseBody);
            System.out.println("----------------------------------------"); 
            
            //Verifico l'inserzione
	        if(responseBody.contains("Scheda memorizzata")) {
	        	INSERIMENTO_OK = true;
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
	
	
	
	//GET della pagina degli annunci (per ricavare il CODICEINSERZIONE)
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
            
            System.out.println("Parsing della risposta"); 
	        org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);
	        Elements elements = doc.select("table[cellpadding=3] tr:contains(" + scheda.codice + ") input[name=id]");
	        if(elements.isEmpty()) {
            	throw(new HttpResponseException("Elemento non trovato"));
            }
	        else {
            	Iterator<Element> iterator = elements.iterator();
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	CODICEINSERZIONE = currentElement.attr("value");		            	
	            	System.out.println("CODICEINSERZIONE: " + CODICEINSERZIONE);
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
    }
	
	
	
	//POST per cancellare un immobile
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
        postParameters.add(new BasicNameValuePair("cmd", "delsch"));
        postParameters.add(new BasicNameValuePair("sys_sid", sys_sid));
        postParameters.add(new BasicNameValuePair("nav", "0"));
        postParameters.add(new BasicNameValuePair("id", CODICEINSERZIONE));
        postParameters.add(new BasicNameValuePair("ret", "a:8:{s:3:\"cmd\";s:6:\"modsch\";s:7:\"sys_sid\";s:32:\"" + sys_sid + "\";s:3:\"nav\";s:1:\"0\";s:3:\"cod\";s:0:\"\";s:4:\"cont\";s:2:\"vl\";s:4:\"prov\";s:0:\"\";s:3:\"com\";s:0:\"\";s:4:\"page\";s:1:\"1\";}"));
        //Stampa dei parametri inviati
        printSentParameters(postParameters);
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
			connessione_0(new HttpGet(URL_ROOT + "/admin/pc/pc.php?cmd=ric&nav=0"));
		} catch (IOException | HttpResponseException e ) {
			manageErrors(e, 1);
            return;
		}
        
        
        
        //Connessione 1 - POST della pagina login (ricava il sys_sid)
        try {
			connessione_1(new HttpPost(URL_ROOT + "/admin/pc/amm.php"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 2);
            return;
		}
        
        
        
        //Connessione 2 - POST dello pagina inserimento immobile
        try {
			connessione_2(new HttpPost(URL_ROOT + "/admin/pc/amm.php"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessione 3 - GET della pagina degli annunci (per ricavare il CODICEINSERZIONE)
        try {
			connessione_3(new HttpGet(URL_ROOT + "/admin/pc/amm.php?cmd=modsch&sys_sid=" + sys_sid + "&nav=0&cod=&cont=vl&prov=&com=&page=1"));
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
        	sendConfirmationMail(scheda, NOMEPORTALE, CODICE_PORTALE);
       	
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
			String url = URL_ROOT;
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
	
		
		
		//Connessione 1 - POST della pagina login (ricava il sys_sid)
        try {
			connessione_1(new HttpPost(URL_ROOT + "/admin/pc/amm.php"));
		} catch (IOException | HttpResponseException | WrongPostDataException e ) {
			manageErrors(e, 3);
            return;
		}
        
        
        
        //Connessione 4 - POST per cancellare un immobile
        try {
			connessione_4(new HttpPost(URL_ROOT + "/admin/pc/amm.php"));
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
	
		switch (scheda.tipologia)
		{
		    case "Appartamento":
		    	tipo = "r#Appartamento";
		        break;
		    case "Attico":
		    	tipo = "r#Attici e mansarde";
		        break;
		    case "Bifamiliare":
		    	tipo = "r#Casa indipendente / semindipendente";
		        break;
		    case "Casa":
		    	tipo = "r#Casa indipendente / semindipendente";
		        break;
		    case "Garage":
		    	tipo = "r#Box";
		        break;
		    case "Palazzo":
		    	tipo = "r#Stabile / Palazzo";
		        break;
		    case "Rustico":
		    	tipo = "r#Rustico / Soluzioni in corte";
		        break;
		    case "Terreno":
		    	tipo = "t#Terreno agricolo";
		        break;
		    case "Villa":
		    	tipo = "r#Villa";
		        break;
		    case "Villaschiera":
		    	tipo = "r#Villa";
		        break;
		    case "Agriturismo":
		    	tipo = "a#Agriturismo";
		        break;
		    case "Albergo":
		    	tipo = "a#Alberghi / Hotels";
		        break;
		    case "Bar":
		    	tipo = "a#Attività commerciali / Aziende / Licenze";
		        break;
		    case "Negozio":
		    	tipo = "i#Negozio";
		        break;
		    case "Ristorante":
		    	tipo = "a#Alberghi / Hotels";
		        break;
		    case "Ufficio":
		    	tipo = "i#Ufficio";
		        break;
		    case "Capannone":
		    	tipo = "i#Capannone";
		        break;
		    case "Laboratorio":
		    	tipo = "a#Attività commerciali / Aziende / Licenze";
		        break;
		    case "Magazzino":
		    	tipo = "i#Magazzino";
		        break;
		}
		System.out.println("tipo: " + tipo);
		
		classe = scheda.certificazioni;
		System.out.println("classe: " + classe);
		
		prov = scheda.provincia;
		prov = prov.toUpperCase();
		System.out.println("prov: " + prov);
		
		com = scheda.comune;
		System.out.println("com: " + com);
		
		switch (scheda.numLocali)
		{
		    case "1":
		    	loc = "1";
		        break;
		    case "2":
		    	loc = "2";
		    	break;
		    case "3":
		    	loc = "3";
		    	break;
		    case "4":
		    	loc = "4";
		    	break;
		    case "5":
		    	loc = "5";
		    	break;
		    default:
		    	loc = "6";
		}
	    System.out.println("loc: " + loc);
	    
	    mq = Integer.toString(scheda.supAbitazione);
		System.out.println("mq: " + mq);
		
		pre = Integer.toString(scheda.prezzo);
		System.out.println("pre: " + pre);
		
		desc = scheda.descrEstesa;
		//Problemi con l'encoding...
		desc = desc.replace("à", "a'");
		desc = desc.replace("è", "e'");
		desc = desc.replace("ì", "i'");
		desc = desc.replace("ò", "o'");
		desc = desc.replace("ù", "u'");
		//€desc = desc.replace("€", "â‚¬");
		//desc = desc.replace("&", "Â£ ");
		System.out.println("desc: " + desc);
		
		//Le immagini...
		if(scheda.immagine1!=null) { 
			image1 = scheda.immagine1;
		}
		if(scheda.immagine2!=null) { 
			image2 = scheda.immagine2;
		}
		if(scheda.immagine3!=null) { 
			image3 = scheda.immagine3;
		}
		if(scheda.immagine4!=null) { 
			image4 = scheda.immagine4;
		}
		if(scheda.immagine5!=null) { 
			image5 = scheda.immagine5;
		}
		if(scheda.immagine6!=null) { 
			image6 = scheda.immagine6;
		}
	}


}