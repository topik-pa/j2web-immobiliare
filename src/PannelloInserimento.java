/*
 * Il pannello a destra, che contiene la lista dei portali immobiliari in cui inserire le schede immobile
 * Ultima modifica: 5 sett 2010
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class PannelloInserimento extends JPanel implements parametriGenerali {
		private static final long serialVersionUID = 1L;
	
		//Pannello per l'inserimento dati nei portali immobiliari
        
		//Elementi del pannello
		JPanel inserimentoPortale;
        JButton btnInserisci, btnVisualizza, btnCancella;

        //Costruttore - definisce la prima visualizzazione del pannello
        public PannelloInserimento() {	
        	
        	//Gestisco la lista concatenata che contiene le informazioni sui portali immobiliari
        	//E' qui che inserisco i portali abilitati attualmente nel programma
        	PortaleImmobiliare immobiliarePuntoIt = new ImmobiliarePuntoIt("./img/immobiliarePuntoIt.gif", "1 - immobiliare.it", "001");
        	listPortaliImmobiliari.add(immobiliarePuntoIt);
        	
        	//PortaleImmobiliare casaPuntoIt = new casaPuntoIt("./img/casaPuntoIt.png", "2 - casa.it", "002");
        	//listPortaliImmobiliari.add(casaPuntoIt);
        	
        	PortaleImmobiliare bancaDelleCase = new BancaDelleCase("./img/banca_delle_case.gif", "2 - bancadellecase.it", "003");
        	listPortaliImmobiliari.add(bancaDelleCase);
        	
        	PortaleImmobiliare case24 = new Case24("./img/case24.gif", "3 - case24.it", "004");
        	listPortaliImmobiliari.add(case24);      	
        	
        	PortaleImmobiliare pagineCasa = new PagineCasa("./img/paginecasa.gif", "4 - paginecasa.it", "005");
        	listPortaliImmobiliari.add(pagineCasa);
        	
        	/*PortaleImmobiliare cuboCasa = new CuboCasa("./img/cubocasa.gif", "5 - cubocasa.it", "005");
        	listPortaliImmobiliari.add(cuboCasa);*/
        	
        	//PortaleImmobiliare caseFvg = new CaseFvg("./img/casefvg.gif", "7 - casefvg.it", "007");
        	//listPortaliImmobiliari.add(caseFvg);
        	
        	//Gestione del pannello - prima visualizzazione di questo pannello
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(Box.createVerticalStrut(20));
            
            //Ciclo tra i portali immobiliari presenti nella lista concatenata e per ognuno creo dei sottopannelli e dei pulsanti (fitizzi: non hanno alcuna funzionalità )
            ListIterator<PortaleImmobiliare> iterator = listPortaliImmobiliari.listIterator();
        	while(iterator.hasNext()) {        		
        		PortaleImmobiliare portaleCorrente = iterator.next();
        		
        		//Pulsante Inserisci
        		btnInserisci = new JButton("Inserisci");
        		btnInserisci.setEnabled(false);
        		
        		//Pulsante Visualizza
        		btnVisualizza = new JButton("Visualizza");
        		btnVisualizza.setEnabled(false);
        		
        		//Pulsante Cancella
         		btnCancella= new JButton("Cancella");
         		btnCancella.setEnabled(false);
        		
        		//Pannello
        		ImageIcon iconPortale = new ImageIcon(portaleCorrente.urlIcona);
                JLabel labelPortale = new JLabel(iconPortale, JLabel.CENTER);
                inserimentoPortale = new JPanel();
                inserimentoPortale.setLayout(new BoxLayout(inserimentoPortale, BoxLayout.X_AXIS));
                inserimentoPortale.setBorder(new TitledBorder(portaleCorrente.valoreLabel));
                inserimentoPortale.add(labelPortale);
                inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnInserisci);
                inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnVisualizza);
                inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnCancella);
                add(inserimentoPortale);
                
                add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli
        	}
                       
            //PANNELLO VUOTO
            /*JPanel fakePanel5 = new JPanel();
            fakePanel5.setLayout(new BoxLayout(fakePanel5, BoxLayout.X_AXIS));
            fakePanel5.setBorder(new LineBorder(new Color(184,208,229)));
            fakePanel5.add(new JLabel(new ImageIcon("./img/fake.jpeg")));
            fakePanel5.add(new JLabel("                     "));
            fakePanel5.add(new JLabel("                     "));
            fakePanel5.add(new JLabel("                     "));
            add(fakePanel5);

            add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli*/


        }    //Fine costruttore PannelloInserimento
        
        
        
        //Update del pannello, eseguito alla selezione di una scheda sul pannello centrale e dopo l'inserimento/rimozione di una scheda in un portale
        public void updatePanello(final SchedaImmobile scheda) {

        	removeAll();  
        	
        	add(Box.createVerticalStrut(20));
        	
	    	 //Ciclo ogni oggetto PortaleImmobiliare presente nella lista concatenata
	    	 ListIterator<PortaleImmobiliare> iterator = listPortaliImmobiliari.listIterator();
	     	 while(iterator.hasNext()) {
	     		
	     		final PortaleImmobiliare portaleCorrente = iterator.next();
	     		
	     		//Pulsante Inserisci
	     		btnInserisci = new JButton("Inserisci");
	     		//Controllo se la scheda è inserita nel portale corrente
	     		if(scheda.isOnThisPortal(portaleCorrente.idPortale)){
	     			//Pulsante abilitato
	     			btnInserisci.setEnabled(false);
	     		}
	     		else {
	     			//Pulsante disabilitato
	     			btnInserisci.setEnabled(true);
	     		}
	     		btnInserisci.addActionListener(new ActionListener() {
	     			public void actionPerformed(ActionEvent e) {
	     				System.out.println("Inserisci: " + scheda.codice);	     				
	     				//Il cursone viene messo in modalità attesa
	     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per inserire una scheda immobile, il metoso chiamata sarà quella della sottoclasse effettiva
	     				portaleCorrente.inserisciScheda(scheda);
	     				//Il cursone viene messo in modalità standard
	     				setCursor(Cursor.getDefaultCursor());
	     			}
	     		});
	     			     		
	     		//Pulsante Visualizza
	     		btnVisualizza = new JButton("Visualizza");
	     		//Controllo se la scheda è inserita nel portale corrente
	     		if(scheda.isOnThisPortal(portaleCorrente.idPortale)) {
	     			btnVisualizza.setEnabled(true);
	     		}
	     		else {
	     			btnVisualizza.setEnabled(false);
	     		}
	     		btnVisualizza.addActionListener(new ActionListener() {
	     			public void actionPerformed(ActionEvent e) {
	     				//Il cursone viene messo in modalità attesa
	     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	     				System.out.println("Visualizza: " + scheda.codice);
	     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per visualizzare una scheda immobile
	     				portaleCorrente.visualizzaScheda(scheda);
	     				//Il cursone viene messo in modalità standard
	     				setCursor(Cursor.getDefaultCursor());
	     			}
	     		});
	     		
	     		//Pulsante Cancella
	     		btnCancella = new JButton("Cancella");
	     		//Controllo se la scheda Ã¨ inserita nel portale corrente
	     		if(scheda.isOnThisPortal(portaleCorrente.idPortale)){
	     			btnCancella.setEnabled(true);
	     		}
	     		else {
	     			btnCancella.setEnabled(false);
	     		}
	     		btnCancella.addActionListener(new ActionListener() {
	     			public void actionPerformed(ActionEvent e) {
	     				System.out.println("Cancella: " + scheda.codice);
	     				//Il cursone viene messo in modalità attesa
	     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per cancellare una scheda immobile
	     				portaleCorrente.cancellaScheda(scheda);
	     				//Il cursone viene messo in modalità standard
	     				setCursor(Cursor.getDefaultCursor());
	     			}
	     		});
	     		
	     		//Pannello
	     		ImageIcon iconPortale = new ImageIcon(portaleCorrente.urlIcona);
	            JLabel labelPortale = new JLabel(iconPortale, JLabel.CENTER);
	            inserimentoPortale = new JPanel();
	            inserimentoPortale.setLayout(new BoxLayout(inserimentoPortale, BoxLayout.X_AXIS));
	            inserimentoPortale.setBorder(new TitledBorder(portaleCorrente.valoreLabel));
	            inserimentoPortale.add(labelPortale);
	            inserimentoPortale.add(new JLabel("  "));
	            inserimentoPortale.add(btnInserisci);
	            inserimentoPortale.add(new JLabel("  "));
	            inserimentoPortale.add(btnVisualizza);
	            inserimentoPortale.add(new JLabel("  "));
	            inserimentoPortale.add(btnCancella);
	            add(inserimentoPortale);
	             
	            add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli
	     	}
	     	 
	     	updateUI();
        }
        
        
        //Update del pannello eseguito al momento in cui viene cancellata una scheda sul pannello centrale o inserita una dal pannello Form: nessuna scheda selezionata
        public void updatePanello() {
        	removeAll(); 
        	
        	add(Box.createVerticalStrut(20));
                          
            ListIterator<PortaleImmobiliare> iterator = listPortaliImmobiliari.listIterator();
         	while(iterator.hasNext()) {
         		
         		PortaleImmobiliare portaleCorrente = iterator.next();
         		
         		//Pulsante Inserisci
         		btnInserisci = new JButton("Inserisci");
         		btnInserisci.setEnabled(false);
         		
         		//Pulsante Visualizza
         		btnVisualizza = new JButton("Visualizza");
         		btnVisualizza.setEnabled(false);
         		
         		//Pulsante Cancella
         		btnCancella = new JButton("Cancella");
         		btnCancella.setEnabled(false);
         		
         		//Pannello
         		ImageIcon iconPortale = new ImageIcon(portaleCorrente.urlIcona);
                JLabel labelPortale = new JLabel(iconPortale, JLabel.CENTER);
                inserimentoPortale = new JPanel();
                inserimentoPortale.setLayout(new BoxLayout(inserimentoPortale, BoxLayout.X_AXIS));
                inserimentoPortale.setBorder(new TitledBorder(portaleCorrente.valoreLabel));
                inserimentoPortale.add(labelPortale);
                inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnInserisci);
                inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnVisualizza);
                inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnCancella);
                add(inserimentoPortale);
                 
                 add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli
         	}
         	
         	updateUI();
        	
        }

}   //Fine PannelloInserimento