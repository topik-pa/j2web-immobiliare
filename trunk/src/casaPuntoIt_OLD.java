/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

//import java.io.BufferedReader;
//import java.io.File;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
import java.util.ArrayList;
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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * @author marco
 */

//La classe principale
public class casaPuntoIt_OLD extends PortaleImmobiliare {

    //Parametri generali
	private final String NOMEPORTALE = "casa.it";
	private final String URL_ROOT = "http://www.casa.it";
	private final String SECUREURL_ROOT = "http://admin.casa.it";
    private final String USERNAME = "280911";
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
    private String SESSIONCOOKIE_HEADER2 = "";
    private String SESSIONCOOKIE_NAME2 = "";
    private String SESSIONCOOKIE_VALUE2 = "";
    private String LOCATION;
    private String CODICEINSERZIONE;
    private boolean inserimentoOK = false;
    
    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   
	
	//Costruttore
	public casaPuntoIt_OLD (String urlIcona, String valoreLabel, String idPortale) {		
		this.urlIcona = urlIcona;
		this.valoreLabel = valoreLabel;
		this.idPortale = idPortale;	
	}

	//GET della home page
	private void connessione_0(HttpGet httpget) {
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
    }
    
    
    //GET pagina login: "Login e registrazione"
    private void connessione_1(HttpGet httpget) {
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
    }
    
    
    //POST pagina login: "Login e registrazione"
    private void connessione_2(HttpPost httppost) {
    	System.out.println("CONNESSIONE 2");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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
    }
    
    
    //GET pagina di redirect dopo la Post di login. Viene effettuato un ulteriore redirect automatico alla pagina "Bacheca"
    private void connessione_3(HttpGet httpget) {
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
            //cookie.setDomain(".immobiliare.it");
            cookie.setPath("/");           
            cookieStore.addCookie(cookie); 
            ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
            
            //Prevent redirect
            HttpParams params = new BasicHttpParams();
        	params.setParameter("http.protocol.handle-redirects",false);	
        	httpget.setParams(params);

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
            	//Get session cookie
            	if(currentHeader.getName().contains("Set-Cookie")) {
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
    }
    
    
    //GET della pagina "Nuovo annuncio" (step 1)
    private void connessione_4(HttpGet httpget) {
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
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(".admin.casa.it");
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
	            
	            Element __EVENTARGUMENTElement = doc.getElementById("__EVENTARGUMENT");
	            __EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
	            System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
	            
	            Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
	            __EVENTTARGET = __EVENTTARGETElement.attr("value");
	            System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
	            
	            Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");
	            __VIEWSTATE = __VIEWSTATEElement.attr("value");
	            System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
	            System.out.println("----------------------------------------");
	            
	            Element __LASTFOCUSElement = doc.getElementById("__LASTFOCUS");
	            __LASTFOCUS = __LASTFOCUSElement.attr("value");
	            System.out.println("Value __LASTFOCUS: " + __LASTFOCUS);
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
    }
    
    
    //POST della pagina "Nuovo annuncio" (step 1)
    private void connessione_5(HttpPost httppost) {
    	System.out.println("CONNESSIONE 5");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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
            postParameters.add(new BasicNameValuePair("__ASYNCPOST", __ASYNCPOST));
            postParameters.add(new BasicNameValuePair("__EVENTARGUMENT", "__EVENTARGUMENT"));
            postParameters.add(new BasicNameValuePair("__EVENTTARGET", "ctl00$cp$LB_Submit"));
            postParameters.add(new BasicNameValuePair("__LASTFOCUS", __LASTFOCUS));
            postParameters.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
            postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$DDL_RequestType", ""));
            postParameters.add(new BasicNameValuePair("ctl00$CustomerCare1$TB_Message", ""));
            postParameters.add(new BasicNameValuePair("ctl00$ScriptManager1", "ctl00$ScriptManager1|ctl00$cp$LB_Submit"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Address", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_AddressNumber", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Bathrooms", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_BuildingAge", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_BuildingExpenses", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_BuildingUnit", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Cap", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_EnergyEfficencyValue", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_MQBox", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_MQGarden", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Price", "8888"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Ref", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_RoomsNumber", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_Surface", "88"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TB_TotLevel", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender1_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender2_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender3_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$TextBoxWatermarkExtender4_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$UMEnergyEfficency", "RB_UMm2a"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$WM_BuildingAge_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$WM_Italian_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$cddCity_ClientState", "069022:::Chieti:::"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$cddHouseTipology_ClientState", "1:::Nuova costruzione:::"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$cddNation_ClientState", "1:::Italia:::"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$cddProvince_ClientState", "069:::Chieti:::"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$cddRegion_ClientState", "13:::Abruzzo:::"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$cddZone_ClientState", "::::::"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlBoxType", "-1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlBuildingConditions", "-1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlBuildingType", "1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlCity", "069022"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlContractType", "1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlEnergyEfficiencyRating", "0"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlGardenType", "-1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlHeatingType", "-1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlLevel", "-2"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlNation", "1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlOccupationState", "-1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlProvince", "069"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlRegion", "13"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlTipology", "1"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$ddlZone", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$flat", "42.3517929"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$flong", "14.1671511"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$fmapcenterlat", "42.3517929"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$fmapcenterlong", "14.1671511"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$fmapzoom", "15"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$fshowmarker", "0"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$geocodevalidation", "0"));
            postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrEng", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrEngMaxCharsExtender_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrFra", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrFraMaxCharsExtender_ClientState", ""));
            postParameters.add(new BasicNameValuePair("ctl00$cp$txtDescrIta", "Attenzione: Il campo descrizione dell'annuncio non pu\u00f2 contenere indirizzo e-mail e sito web. Queste informazioni sono gi\u00e0 presenti automaticamente nella scheda dell'immobile. Se desidera, può modificarle o aggiungerle nella sezione PROFILO della sua Area Riservata."));
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
            cookie.setDomain(".admin.casa.it");
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
		        //Get the insertion code
		        int start = responseBody.indexOf("Id=") + 3;
		        int end = responseBody.lastIndexOf("|");
		        CODICEINSERZIONE = responseBody.substring(start, end);
		        System.out.println("Codice inserzione: " + CODICEINSERZIONE);
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
    }
    
    
    //GET della pagina "Gestisci immagini" (step 2)
    private void connessione_6(HttpGet httpget) {
    	System.out.println("CONNESSIONE 6");
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
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(".admin.casa.it");
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
	            
	            Element __EVENTARGUMENTElement = doc.getElementById("__EVENTARGUMENT");
	            __EVENTARGUMENT = __EVENTARGUMENTElement.attr("value");
	            System.out.println("Value __EVENTARGUMENT: " + __EVENTARGUMENT);
	            
	            Element __EVENTTARGETElement = doc.getElementById("__EVENTTARGET");
	            __EVENTTARGET = __EVENTTARGETElement.attr("value");
	            System.out.println("Value __EVENTTARGET: " + __EVENTTARGET);
	            
	            Element __VIEWSTATEElement = doc.getElementById("__VIEWSTATE");
	            __VIEWSTATE = __VIEWSTATEElement.attr("value");
	            System.out.println("Value __VIEWSTATE: " + __VIEWSTATE);
	            
	            Element __EVENTVALIDATIONElement = doc.getElementById("__EVENTVALIDATION");
	            __EVENTVALIDATION = __EVENTVALIDATIONElement.attr("value");
	            System.out.println("Value __EVENTVALIDATION: " + __EVENTVALIDATION);
	            System.out.println("----------------------------------------");
            }  
            
            //Inserimeno immagini
            if(scheda.immagine1!=null) {
            	System.out.println("Inserimento immagine 1");
            	//connessione_6_1(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine1);
            }
            if(scheda.immagine2!=null) {
            	System.out.println("Inserimento immagine 2");
            	//connessione_6_1(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine2);
            }
            if(scheda.immagine3!=null) {
            	System.out.println("Inserimento immagine 3");
            	//connessione_6_1(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine3);
            }
            if(scheda.immagine4!=null) {
            	System.out.println("Inserimento immagine 4");
            	//connessione_6_1(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine4);
            }
            if(scheda.immagine5!=null) {
            	System.out.println("Inserimento immagine 5");
            	//connessione_6_1(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine5);
            }
            if(scheda.immagine6!=null) {
            	System.out.println("Inserimento immagine 6");
            	//connessione_6_1(new URL(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE), scheda.immagine6);
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
    }
    
    
    
    //POST della pagina "Gestisci immagini" (step 2) - inserimento immagini
    /*private void connessione_6_1(URL url, File image) { 
    	System.out.println("Immagine: " + image.getName());  
    	System.out.println("URL: " + url);
        try {
  	
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

            //Stampa delle proprietÃ  di connessione
            System.out.println("\nPropriet\u00e0  della chiamata...\n");         
            System.out.println("RISPOSTA: " + connessione.getResponse());
            System.out.println("METODO: " + connessione.getRequestMethod());                     
            connessione.getRequestHeaders();
            connessione.getResponseHeaders();             
        }
        catch (IOException ex) {
            System.out.println("Accesso al sito fallito: connessione inserimento immagine");
        }
    }*/
   
    
    
    //POST della pagina "Gestisci immagini" (step 2)
    private void connessione_7(HttpPost httppost) {
    	System.out.println("CONNESSIONE 7");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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
            cookie.setDomain(".admin.casa.it");
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
    }
         

    //GET della pagina "Nuovo annuncio" (step 3)
    private void connessione_8(HttpGet httpget) {
    	System.out.println("CONNESSIONE 8");
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
            
            //Set the cookies
            BasicCookieStore cookieStore = new BasicCookieStore(); 
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(".admin.casa.it");
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
            System.out.println("Response body: \n");
            String responseBody = responseHandler.handleResponse(response);
            System.out.println(responseBody);
            System.out.println("----------------------------------------");            
            
            //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);           
            
            Element OKElement = doc.getElementById("ctl00_cp_P_OK");        
            if(OKElement!=null) {
            	System.out.println("Elemento di controllo inserimento: " + OKElement.html());
            	inserimentoOK = true;
            }
            System.out.println("----------------------------------------");

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
    }
    
    
    //GET pagina gestione annunci: "Gestisci annunci"
    private void connessione_9(HttpGet httpget) {
    	System.out.println("CONNESSIONE 9");
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
            
            //Set the cookies
            BasicCookieStore cookieStore = new BasicCookieStore(); 
            BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME2, SESSIONCOOKIE_VALUE2);
            cookie.setDomain(".admin.casa.it");
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
	            
	            /*Element __LASTFOCUSElement = doc.getElementById("__LASTFOCUS");
	            __LASTFOCUS = __LASTFOCUSElement.attr("value");
	            System.out.println("Value __LASTFOCUS: " + __LASTFOCUS);
	            System.out.println("----------------------------------------");*/
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
    }
    
    //POST pagina login: "Login e registrazione"
    private void connessione_10(HttpPost httppost) {
    	System.out.println("CONNESSIONE 10");
    	HttpClient httpclient = new DefaultHttpClient();
        try {
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
            cookie.setDomain(".admin.casa.it");
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
    }
    
    
    
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public void inserisciScheda(SchedaImmobile scheda) {
    	
    	//Inizializzazione parametri
    	this.scheda=scheda;
    	
        connessione_0(new HttpGet(URL_ROOT + "/vendita"));
        connessione_1(new HttpGet(URL_ROOT + "/pubblica-annunci"));
        connessione_2(new HttpPost(URL_ROOT + "/pubblica-annunci"));
    	connessione_3(new HttpGet(LOCATION));
    	connessione_4(new HttpGet(SECUREURL_ROOT + "/modifylisting_step1.aspx"));
    	connessione_5(new HttpPost(SECUREURL_ROOT + "/modifylisting_step1.aspx"));
    	connessione_6(new HttpGet(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE));
    	connessione_7(new HttpPost(SECUREURL_ROOT + "/modifylisting_step2.aspx?ListingId=" + CODICEINSERZIONE));    	
    	connessione_8(new HttpGet(SECUREURL_ROOT + "/modifylisting_step3.aspx?ListingId=" + CODICEINSERZIONE));
    	
    	//connessione_7_1(new HttpPost("http://admin.casa.it/modifylisting_step2.aspx?ListingId=22030028"), scheda.immagine1);
        
    	
    	if(inserimentoOK) {
    		System.out.println("Inserita in: casaPuntoIt");
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, CODICEINSERZIONE);
            
        	//Aggiorna i pulsanti del pannello inserimento 	
        	Main.imaginationGUI.pannelloInserimento.updatePanello(scheda);
       	
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
		//Apro il browser e inserisco credenziali
		try {
			String url = SECUREURL_ROOT  + "/modifylisting_step1.aspx?ListingId=" + CODICEINSERZIONE;
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Visualizzata in: " + NOMEPORTALE);
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public void cancellaScheda(SchedaImmobile scheda) {
		
		connessione_1(new HttpGet(URL_ROOT + "/pubblica-annunci"));
        connessione_2(new HttpPost(URL_ROOT + "/pubblica-annunci"));
    	connessione_3(new HttpGet(LOCATION));
		connessione_9(new HttpGet(SECUREURL_ROOT + "/annuncifp.aspx"));
		connessione_10(new HttpPost(SECUREURL_ROOT + "/annuncifp.aspx"));
		
		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			
	
		//Aggiorno i pulsanti del pannello inserimento
		Main.imaginationGUI.pannelloInserimento.updatePanello(scheda);
		
		System.out.println("Eliminata da: ImmobiliarePuntoIt");
	
		//Stampo a video un messaggio informativo
        JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
	}  
    
}