/*
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda immobile
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.JOptionPane;


public class SchedaImmobile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Attributi della scheda immobile	
	long idScheda = new Date().getTime();	//id univoco riferito alla scheda
	
	String codice;
	String titolo;
	
	String regione="";
	String provincia="";
	String comune="";	
	String cap="";
	String via="";
	
	String descrSintetica="";
	String descrEstesa="";
	String categoria="";
	String tipologia="";
	String contratto="";
	String numLocali="";
	String numBagni="";
	int numCamere = 0;
	int supAbitazione = 0;
	//String supGiardino="";
	String annoCostr="";
	String piano="";
	String totPiani="";
	//String status="";	
	String statoImmobile="";
	//String numIngressi="";
	String arredamenti="";
	String riscaldamento="";
	String clima="";
	String certificazioni="";
	String giardino="";
	String parcheggio="";
	
	File immagine1;
	File immagine2;
	File immagine3;
	File immagine4;
	File immagine5;
	File immagine6;

	//String riferimento="";
	String provvigione="";
	int prezzo=0;
	//String speseCond="";
	
	//Gli indici delle combobox 
	int regioneIndex=0;
	int provinciaIndex=0;
	int comuneIndex=0;
	int categoriaImmobileIndex=0;
	int tipologiaImmobileIndex=0;
	int tipologiaContrattoIndex=0;
	int tipologiaCatalogazioneIndex=0;
	int nroBagniIndex=0;
	int nroCamereIndex=0;	
	int riscaldamentoIndex=0;
	int climaIndex=0;
	int certificazioniIndex=0;
	int giardinoIndex=0;
	int parcheggioIndex=0;
	int statoImmobileIndex=0;
	int arredamentiIndex=0;

	//Una scheda immobile può essere ospitata in diversi portali, la seguente tabella hash contiene i codici dei portali(key) e il codice di inserimento(value) in cui la scheda è attualmente inserita
	Map<String,String> mappaPortaliOspitanti = new Hashtable<String,String>();	
	
	//Costruttore
	public SchedaImmobile () {	 	
	
		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		codice = Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.getText().trim();
		if(codice.length()>10) {
			codice.substring(0, 10);
		}
		
		titolo = Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.getText().trim();
		if(titolo.length()>150) {
			titolo.substring(0, 150);
		}
		
		/*int indexStato = Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.listStato.getSelectedIndex();
        if(indexStato==0) 
        	stato = "Italia";
        else
        	stato = "Austria";*/  
		
		regione = (String)Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.getSelectedItem();
		
        provincia = (String)Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.getSelectedItem();
        
        comune = (String)Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxComune.getSelectedItem();
        
        cap = Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldCap.getText().trim();
        if(cap.length()>7) {
        	cap.substring(0, 7);
		}
        
        via = Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldVia.getText().trim();
        if(via.length()>150) {
        	via.substring(0, 150);
		}
                
        descrSintetica = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.getText().trim();
        if(descrSintetica.length()>200) {
        	descrSintetica.substring(0, 200);
		}
        
        descrEstesa = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.getText().trim();
        if(descrEstesa.length()>2000) {
        	descrEstesa.substring(0, 2000);
		}
        
        categoria = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.getSelectedItem();
           
        tipologia = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaImmobile.getSelectedItem();
        
        contratto = (String)Main.imaginationGUI.pannelloForm.panelDatiContratto.cmboxTipologiaContratto.getSelectedItem();
        
        numLocali = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.getSelectedItem();
        
        numBagni = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.getSelectedItem();
        
        numCamere = Integer.parseInt(Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroCamere.getSelectedItem().toString());
        
        supAbitazione = Integer.parseInt(Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.getText().trim());
        
        //supGiardino = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieGiardino.getText();
        
        annoCostr = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldAnnoCostruzione.getText().trim();
        
        piano = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldPiano.getText().trim();
        
        totPiani = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuPiani.getText().trim();
        
        statoImmobile = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxStatoImmobile.getSelectedItem();
        
        //numIngressi = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxIngressiImmobile.getSelectedItem();
        
        arredamenti = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxArredamentiImmobile.getSelectedItem();
        
        riscaldamento = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxRiscaldamentoImmobile.getSelectedItem();
        
        clima = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxClimaImmobile.getSelectedItem();
        
        certificazioni = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCertificazioniImmobile.getSelectedItem();
        
        giardino = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxGiardinoImmobile.getSelectedItem();
        
        parcheggio = (String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxParcheggioImmobile.getSelectedItem();    
        
        immagine1 = Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine1;
        immagine2 = Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine2;
        immagine3 = Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine3;
        immagine4 = Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine4;
        immagine5 = Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine5;
        immagine6 = Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine6;
             	
        provvigione = Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.getText().trim();
        if(provvigione.length()>10) {
        	provvigione.substring(0, 10);
		}
        
        prezzo = Integer.parseInt(Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.getText().trim());
        
        //speseCond = Main.imaginationGUI.pannelloForm.panelDatiContratto.fldSpeseCondominiali.getText();
      
        
        //Salvo gli indici delle select
        regioneIndex=Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.getSelectedIndex();
        provinciaIndex=Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.getSelectedIndex();
        comuneIndex=Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxComune.getSelectedIndex();
        categoriaImmobileIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.getSelectedIndex();
        tipologiaImmobileIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaImmobile.getSelectedIndex();
        tipologiaContrattoIndex = Main.imaginationGUI.pannelloForm.panelDatiContratto.cmboxTipologiaContratto.getSelectedIndex();
        tipologiaCatalogazioneIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.getSelectedIndex();
        nroBagniIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.getSelectedIndex();
        nroCamereIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroCamere.getSelectedIndex();
        statoImmobileIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxStatoImmobile.getSelectedIndex();
        arredamentiIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxArredamentiImmobile.getSelectedIndex();
        riscaldamentoIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxRiscaldamentoImmobile.getSelectedIndex();
        climaIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxClimaImmobile.getSelectedIndex();
        certificazioniIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCertificazioniImmobile.getSelectedIndex();
        giardinoIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxGiardinoImmobile.getSelectedIndex();
        parcheggioIndex = Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxParcheggioImmobile.getSelectedIndex();
        
	}

	
	//Metodi
	
	//Carica tabella quando premi il radio button
	//Lettura tabella
	@SuppressWarnings("unchecked")
	public void caricaMappaPortaliOspitanti() {
		
		//Lettura schede dal file .dat
        File file = new File("./schede/" + codice + "-" + idScheda + ".dat");
    	if(file.exists()) {
    		System.out.println("File .dat scheda trovato." + "./schede/" + codice + "-" + idScheda + ".dat");
    		try {
    			if(file.length()!=0) {
    				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
    				mappaPortaliOspitanti = (Hashtable<String,String>)inputFile.readObject();
					inputFile.close();
    			}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File hash non trovato: impossibile caricare la hashtable", "Errore", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al file hash: impossibile caricare la hashtable", "Errore", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Errore generico", "Errore", JOptionPane.ERROR_MESSAGE);
			}   		
    	}
    	else {
    		System.out.println("File hash non trovato.");
    		try {
				//@SuppressWarnings("unused")
				FileOutputStream newFile = new FileOutputStream("./schede/" + codice + "-" + idScheda + ".dat");
				System.out.println("File hash non trovato. Creazione del file...: " + newFile.toString());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File hash non trovato: impossibile caricare la hash table", "Errore", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    	}
	}
	
	
	//Verifico la presenza della scheda immobile in un dato portale
	public boolean isOnThisPortal(String idPortale) {
		System.out.println("idportale: " + idPortale);
		if(mappaPortaliOspitanti.containsKey(idPortale)) {
			System.out.println("Scheda presente nel portale: " + idPortale);
			return true; 
		}
		else {
			System.out.println("La scheda non è presente nel portale: " + idPortale);
			return false;
		}
	}
	
	
	//Aggiorno la lista dei portali in cui la scheda è inserita (nuovo inserimento)
	public void aggiungiInserimentoPortale(String idPortale, String codiceInserzione) {
		mappaPortaliOspitanti.put(idPortale, codiceInserzione);
				
		//Salvataggio tabella
        try {
 		   File file = new File("./schede/" + codice + "-" + idScheda + ".dat");
 	    	if(file.exists()) {
 	    		System.out.println("File hash scheda trovato.");
 	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
					outputFile.writeObject(mappaPortaliOspitanti);
					outputFile.close();
 	    	}
 	    	else {
 	    		System.out.println("File hash scheda non trovato.");
 	    	}
			} catch (FileNotFoundException e0) {
	            JOptionPane.showMessageDialog(null, "File hash scheda non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
	            e0.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al file hash scheda: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}	
	}

	
	//Aggiorno la lista dei portali in cui la scheda è inserita (cancellazione)	
	public void eliminaInserimentoPortale(String idPortale) {		
		mappaPortaliOspitanti.remove(idPortale);
		
		//Salvataggio tabella
        try {
 		   File file = new File("./schede/" + codice + "-" + idScheda + ".dat");
 	    	if(file.exists()) {
 	    		System.out.println("File hash scheda trovato.");
 	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
					outputFile.writeObject(mappaPortaliOspitanti);
					outputFile.close();
 	    	}
 	    	else {
 	    		System.out.println("File hash scheda non trovato.");
 	    	}
			} catch (FileNotFoundException e0) {
	            JOptionPane.showMessageDialog(null, "File hash scheda non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
	            e0.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al file .hash scheda: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}      
	}
	
	
	//Ottenere il codice di inserimento di una scheda tramite l'id del portale (cancellazione)	
	public String getCodiceInserimento(String idPortale) {		
		String codiceInserimento = "";
		if(mappaPortaliOspitanti.containsKey(idPortale)) {
			codiceInserimento = mappaPortaliOspitanti.get(idPortale);
		}
		return codiceInserimento;
	}
		
	
}