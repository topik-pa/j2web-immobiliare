/*
 * GUI principale del programma
 * Ultima modifica: 9 dic 2010
 *
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Marco Pavan - marcopavan.mp@gmail.com
 */

//La GUI principale
public class imagination extends JFrame { 
	private static final long serialVersionUID = 1L;
	
	private Container cnt;                      	//Il contenitore che ospita tutti i pannelli
    PannelloForm pannelloForm;                  	//Pannello che ospita il form
    PannelloInserimento pannelloInserimento;    	//Pannello per l'inserimento nei singoli portali immobiliari
    //PannelloIstruzioni pannelloIstruzioni;    	//Pannello informazioni ed istruzioni - per utilizzi futuri
    //PannelloHeader pannelloHeader;				//Pannello per intestazioni - per utilizzi futuri
    PannelloSchedeImmobili pannelloSchedeImmobili;	//Pannello contenente le schede immobili create

    //Icona della finestra principale
    Image frameIcon = new ImageIcon("./img/imaginationLogo.png").getImage();   

    //Costruttore
    public imagination() {
    	//Proprietà principali della finestra del programma
        setTitle("J2Web Immobiliare - Cividale Casa -  versione 0.3.0.5 \u03B2");
        setSize(1200,600);
        setIconImage(frameIcon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pannelloForm = new PannelloForm();
        pannelloInserimento = new PannelloInserimento();  
        pannelloSchedeImmobili = new PannelloSchedeImmobili();
        //pannelloIstruzioni = new PannelloIstruzioni();  
        //pannelloHeader = new PannelloHeader();

        //Inserimento dei pannelli all'interno della finestra principale e definizione di un layout
        cnt = getContentPane();
        
        //BorderLayout
        //cnt.setLayout(new BorderLayout());
        //cnt.add(new JScrollPane(pannelloForm), BorderLayout.CENTER);
        //cnt.add(new JScrollPane(pannelloSchedeImmobili), BorderLayout.WEST);
        //cnt.add(new JScrollPane(pannelloInserimento), BorderLayout.EAST);
        //cnt.add(new JScrollPane(pannelloIstruzioni), BorderLayout.SOUTH);
        
        //GridLayout
        cnt.setLayout(new GridLayout(1,3));
        cnt.add(new JScrollPane(pannelloForm));
        cnt.add(new JScrollPane(pannelloSchedeImmobili));
        cnt.add(new JScrollPane(pannelloInserimento));

        //Prima istruzione visualizzata nel pannello istruzioni
        //pannelloIstruzioni.lblIstruzioni.setText("Compilare la form nel pannello a sinistra e  premere il tasto \"Procedi\"");

        setVisible(true);

    }   // fine del costruttore principale

}   //Fine GUI principale