import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/*
 * Questa classe definische i metodi e gli attributi dell'oggetto portale immobiliare, qui definiti in termini generici, vengono riscritti nelle classi più specifiche
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

public abstract class PortaleImmobiliare implements parametriGenerali{

	//Attributi
	String urlIcona;
	String valoreLabel;
	String idPortale;
	
	//Costruttori	
	public PortaleImmobiliare() {
		
	}
	
	public PortaleImmobiliare (String urlIcona, String valoreLabel, String idPortale) {
	
		this.urlIcona = urlIcona;
		this.valoreLabel = valoreLabel;
		this.idPortale = idPortale;

	}
	
	
	//Metodi
	
	//Inserimento scheda (sovrascritto nelle sottoclassi)
	public abstract void inserisciScheda(SchedaImmobile scheda);
	
	//Visualizzazione scheda (sovrascritto nelle sottoclassi)
	public abstract void visualizzaScheda(SchedaImmobile scheda);

	//Eliminazione scheda (sovrascritto nelle sottoclassi)
	public abstract void cancellaScheda(SchedaImmobile scheda);
	
	//Invio mail in caso di errori runtime
	static void sendErrorMail(String stackTrace, String errorCode)   {
				
		final String USERNAME = "marcopavan.mp@gmail.com";
		final String PASSWORD = "IsMPsd80";
		final String RECIPENTS = "marcopavan.mp@gmail.com";
		final String SUBJECT = "Runtime error in imagination. Code: " + errorCode;
		
	    final String GMAIL_SMTP_HOST = "smtp.gmail.com";
	    final int GMAIL_SMTP_PORT = 465;
	    final Session session = Session.getInstance(System.getProperties(), null);
	    final Message msg = new MimeMessage(session);
	    final String senderEmail = USERNAME.contains("@") ? USERNAME : (USERNAME + "@gmail.com");
	    try {
			msg.setFrom(new InternetAddress(senderEmail));
			final Address[] recipientAddresses = InternetAddress.parse(RECIPENTS);
		    msg.setRecipients(Message.RecipientType.TO, recipientAddresses);
		
		    msg.setSentDate(new Date());
		    msg.setSubject(SUBJECT);
		    msg.setText("Errore runtime, see the report: \n\n" + stackTrace);
		
		    final Transport transport = session.getTransport("smtps");
		    transport.connect(GMAIL_SMTP_HOST, GMAIL_SMTP_PORT, USERNAME, PASSWORD);
		    transport.sendMessage(msg, recipientAddresses);
		    transport.close();
			
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	
	//printStackTrace into String
    public static String readStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
    
    
    
    //Invio mail di conferma inserzione
  	static void sendConfirmationMail(SchedaImmobile scheda, String nomePortale, String codInserzione)   {
  				
  		final String USERNAME = EMAIL_1;
  		final String PASSWORD = EMAIL_1_PSW;
  		final String RECIPENTS = EMAIL_1 + "," + EMAIL_BACKEND;
  		final String SUBJECT = "Scheda immobile inserita: " + scheda.codice + " " + scheda.titolo;
  		
  	    final String GMAIL_SMTP_HOST = EMAIL_1_SMTP_HOST;
  	    final int GMAIL_SMTP_PORT = EMAIL_1_SMTP_PORT;
  	    final Session session = Session.getInstance(System.getProperties(), null);
  	    final Message msg = new MimeMessage(session);
  	    final String senderEmail = USERNAME.contains("@") ? USERNAME : (USERNAME + EMAIL_1_DOMAIN);
  	    
  	    String textBody = "";
  	    textBody += "\nProvincia: " + scheda.provincia + "\n";
  	    textBody += "Comune: " + scheda.comune + "\n";
  	    textBody += "CAP: " + scheda.cap + "\n";
	  	textBody += "Via/Piazza/Località: " + scheda.via + "\n";
	  	textBody += "Descrizione breve: " + scheda.descrSintetica + "\n";
	  	textBody += "Descrizione: " + scheda.descrEstesa + "\n";
	  	textBody += "Categoria: " + scheda.categoria + "\n";
	  	textBody += "Tipologia: " + scheda.tipologia + "\n";
	  	textBody += "Contratto: " + scheda.contratto + "\n";
	  	textBody += "Nr. locali: " + scheda.numLocali + "\n";
	  	textBody += "Nr. bagni: " + scheda.numBagni + "\n";
	  	textBody += "Nr. camere: " + scheda.numCamere + "\n";
	  	textBody += "Superficie abitazione: " + scheda.supAbitazione + "\n";
	  	textBody += "Riscaldamento: " + scheda.riscaldamento + "\n";
	  	textBody += "Clima: " + scheda.clima + "\n";
	  	textBody += "Certificazione: " + scheda.certificazioni + "\n";
	  	textBody += "Giardino: " + scheda.giardino + "\n";
	  	textBody += "Parcheggio: " + scheda.parcheggio + "\n";
	  	if(scheda.immagine1!=null) {
	  		textBody += "Immagine : " + scheda.immagine1.getName() + "\n";
	  	}
	  	if(scheda.immagine2!=null) {
	  		textBody += "Immagine : " + scheda.immagine2.getName() + "\n";
	  	}
	  	if(scheda.immagine3!=null) {
	  		textBody += "Immagine : " + scheda.immagine3.getName() + "\n";
	  	}
	  	if(scheda.immagine4!=null) {
	  		textBody += "Immagine : " + scheda.immagine4.getName() + "\n";
	  	}
	  	if(scheda.immagine5!=null) {
	  		textBody += "Immagine : " + scheda.immagine5.getName() + "\n";
	  	}
	  	if(scheda.immagine6!=null) {
	  		textBody += "Immagine : " + scheda.immagine6.getName() + "\n";
	  	}
	  	textBody += "Provvigione: " + scheda.provvigione + "\n";
	  	textBody += "Prezzo: " + scheda.prezzo + "\n";
	  	
	  	textBody += "Scheda inserita in: " + nomePortale + " " + "con codice " + codInserzione;
  	    
  	    
  	    try {
  			msg.setFrom(new InternetAddress(senderEmail));
  			final Address[] recipientAddresses = InternetAddress.parse(RECIPENTS);
  		    msg.setRecipients(Message.RecipientType.TO, recipientAddresses);
  		
  		    msg.setSentDate(new Date());
  		    msg.setSubject(SUBJECT);
  		    
  		    msg.setText("Dati scheda: \n\n " + textBody);
  		
  		    final Transport transport = session.getTransport("smtps");
  		    transport.connect(GMAIL_SMTP_HOST, GMAIL_SMTP_PORT, USERNAME, PASSWORD);
  		    transport.sendMessage(msg, recipientAddresses);
  		    transport.close();
  			
  		} catch (AddressException e) {
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
  			e.printStackTrace();
  		} catch (MessagingException e) {
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
  			e.printStackTrace();
  		}
  	}
    
  	
  	
  	//Valutazione similarità tra stringhe
  	public static List<char[]> bigram(String input)
    {
        ArrayList<char[]> bigram = new ArrayList<char[]>();
        for (int i = 0; i < input.length() - 1; i++)
        {
            char[] chars = new char[2];
            chars[0] = input.charAt(i);
            chars[1] = input.charAt(i+1);
            bigram.add(chars);
        }
        return bigram;
    }  
    public static double dice(List<char[]> bigram1, List<char[]> bigram2)
    {
        List<char[]> copy = new ArrayList<char[]>(bigram2);
        int matches = 0;
        for (int i = bigram1.size(); --i >= 0;)
        {
            char[] bigram = bigram1.get(i);
            for (int j = copy.size(); --j >= 0;)
            {
                char[] toMatch = copy.get(j);
                if (bigram[0] == toMatch[0] && bigram[1] == toMatch[1])
                {
                    copy.remove(j);
                    matches += 2;
                    break;
                }
            }
        }
        return (double) matches / (bigram1.size() + bigram2.size());
    }
  
    //Gestione degli errori
    public static void manageErrors(Exception e , int type) {
    	String errorType = e.getClass().getName();
    	
    	sendErrorMail(readStackTrace(e), errorType);
    	
    	switch (type)
		{
		    case 1: //Errore di connessione
		    	JOptionPane.showMessageDialog(null, errorType + "durante l'inserimento della scheda.\n Verificare la connessione ad Internet", errorType, JOptionPane.ERROR_MESSAGE);
		        break;
		    case 2:	//Errore di login
		    	JOptionPane.showMessageDialog(null, errorType + "durante l'inserimento della scheda.\n Verificare che il proprio account sia ancora valido per il portale", errorType, JOptionPane.ERROR_MESSAGE);
		    	break;
		    default://Errore generico
		    	JOptionPane.showMessageDialog(null, errorType + "durante l'inserimento della scheda.\n E' stato inviato un rapporto allo sviluppatore del software", errorType, JOptionPane.ERROR_MESSAGE);
		}   
    	
        e.printStackTrace();
    }
    
    //Stampa dei parametri inviati
    public static void printSentParameters(List<NameValuePair> sentParameters) {
    	System.out.println("Parametri inviati...");
    	Iterator<NameValuePair> iterator = sentParameters.iterator();
    	while(iterator.hasNext()) {
    		NameValuePair currentParam = (NameValuePair) iterator.next();
    		System.out.println("Nome: " + currentParam.getName() + "\tValore: " + currentParam.getValue() + "\n");
    	}
    }
    
    //Metodo per ottenere le coordinate della città
  	public String getCoord(String indirizzo, String comune, String provincia, String regione) throws ParserConfigurationException, SAXException, IOException {
  		String latitudine;
  		String longitudine;
  		Document doc = null;
  		DocumentBuilder db = null;
  		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          
  		db = dbf.newDocumentBuilder();    
  		doc = db.parse("http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=" + indirizzo + "%20" + comune + "%20" + provincia + "%20" + regione + "%20" + "Italia");
          
  		doc.getDocumentElement().normalize();
  		
        System.out.println("Root element " + doc.getDocumentElement().getNodeName());
        NodeList nodeLstLat = doc.getElementsByTagName("lat");       
        Node lat = nodeLstLat.item(1);
        NodeList nodeLstLon = doc.getElementsByTagName("lng");       
        Node lon = nodeLstLon.item(1);
             
        System.out.println("Latitudine e longitudine: " + lat.getTextContent() + " - " + lon.getTextContent());
      
        latitudine = lat.getTextContent();
        longitudine = lon.getTextContent();
        
        return latitudine + "$$$" + longitudine;       	
  	}
  
    
}