/*
 * Il pannello a sinistra, che contiene la form da riempire con i dati dell'immobile
 * Ultima modifica: 6 sett 2010
 *
 */


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 *
 * @author marco - marcopavan.mp@gmail.com
 */

//Il pannello form (è costituito da diversi sottopannelli: uno per ogni categoria di informazioni relative all'annuncio)
public class PannelloForm extends JPanel implements parametriGenerali {
		private static final long serialVersionUID = 1L;
		
		//Sottopannelli che compongono il PannelloForm
		PanelDatiAnnuncio panelDatiAnnuncio;
        PanelDatiPosizioneImmobile panelDatiPosizioneImmobile;
        PanelDatiDescrizioneImmobile panelDatiDescrizioneImmobile;
        PanelDatiContratto panelDatiContratto;
        PanelImmagini panelImmagini;
        PanelComandi panelComandi;     
        
        //Icona info
        String infoImagePath = "./img/information.png";
        Icon infoIcon = new ImageIcon(infoImagePath);      
        //Icona campo obbligatorio
        String AttentionImagePath = "./img/attention2.png";
        JLabel lblAttention = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention2 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention3 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention4 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention5 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention6 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention7 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention8 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention9 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention10 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention11 = new JLabel(new ImageIcon(AttentionImagePath));
        JLabel lblAttention12 = new JLabel(new ImageIcon(AttentionImagePath));

        //Costruttore
        public PannelloForm() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            //Definizione delle tooltip per i campi obbligatori
            String AttentionTooltipText = "Campo obbligatorio";
            lblAttention.setToolTipText(AttentionTooltipText);
            lblAttention2.setToolTipText(AttentionTooltipText);
            lblAttention3.setToolTipText(AttentionTooltipText); 
            lblAttention4.setToolTipText(AttentionTooltipText); 
            lblAttention5.setToolTipText(AttentionTooltipText); 
            lblAttention6.setToolTipText(AttentionTooltipText); 
            lblAttention7.setToolTipText(AttentionTooltipText); 
            lblAttention8.setToolTipText(AttentionTooltipText); 
            lblAttention9.setToolTipText(AttentionTooltipText);
            lblAttention10.setToolTipText(AttentionTooltipText);
            lblAttention11.setToolTipText(AttentionTooltipText);
            lblAttention12.setToolTipText(AttentionTooltipText);

            //Dichiarazione dei sottopannelli
            panelDatiAnnuncio = new PanelDatiAnnuncio();
            panelDatiPosizioneImmobile = new PanelDatiPosizioneImmobile();
            panelDatiDescrizioneImmobile = new PanelDatiDescrizioneImmobile();
            panelDatiContratto = new PanelDatiContratto();
            panelImmagini = new PanelImmagini();
            panelComandi = new PanelComandi();
            
            //Definisco il posizionamento dei sottopanneli all'interno del pannello Form
            add(Box.createVerticalStrut(10));   
            add(panelDatiAnnuncio);
            add(Box.createVerticalStrut(10));

            add(Box.createVerticalStrut(10));
            add(panelDatiPosizioneImmobile);
            add(Box.createVerticalStrut(10));

            add(Box.createVerticalStrut(10));
            add(panelDatiDescrizioneImmobile);
            add(Box.createVerticalStrut(10));

            add(Box.createVerticalStrut(10));
            add(panelImmagini);
            add(Box.createVerticalStrut(10));

            add(Box.createVerticalStrut(10));
            add(panelDatiContratto);
            add(Box.createVerticalStrut(10));

            add(Box.createVerticalStrut(10));
            add(Box.createVerticalStrut(10));

            add(Box.createVerticalStrut(10));
            add(panelComandi);
            add(Box.createVerticalStrut(10));

        }   // fine costruttore pannello form


    //    
    //Definizione delle classi che definiscono i sottopannelli
    //
        
    //sottopannello del pannello per i dati generali sull'annuncio    
    class PanelDatiAnnuncio extends JPanel {        
			private static final long serialVersionUID = 1L;
			JPanel panelCodice, panelTitolo/*, panelData*/;
            JLabel lblCodice, lblTitolo/*, lblData*/;
            JLabel lblInfoCodice, lblInfoTitolo/*, lblInfoData*/;
            JTextField fldCodice, fldTitolo/*, fldData*/;

            public PanelDatiAnnuncio() {
                setBorder(new TitledBorder(" - 1 -  Dati generali dell'annuncio"));
                setLayout(new GridLayout(3,1));
                
                //Icona del pannello
                JLabel labelIcoDatiGenerali = new JLabel(new ImageIcon("./img/movies.png"));
                JPanel panelIcoDatiGenerali = new JPanel();
                panelIcoDatiGenerali.add(labelIcoDatiGenerali);
                add(panelIcoDatiGenerali);

                panelCodice = new JPanel();     //Pannello Codice
                panelCodice.setLayout(new FlowLayout(FlowLayout.LEFT));
                lblCodice = new JLabel("Codice: ");                 
                panelCodice.add(lblCodice);
                
                fldCodice = new JTextField(10);
                //fldCodice.setColumns(11);
                //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
                fldCodice.addKeyListener(new KeyAdapter() {     
                @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!((c >= '0') && (c <= '9')  || (c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) ||  (c == KeyEvent.VK_MINUS)  || (c == KeyEvent.VK_DELETE)) || fldCodice.getText().length()>=10) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
                panelCodice.add(fldCodice);
                lblInfoCodice = new JLabel(infoIcon);
                lblInfoCodice.setToolTipText("Solo lettere, numeri e trattino(-). Massimo 10 caratteri.  Per esempio: IVR-52");
                panelCodice.add(lblInfoCodice);                
                panelCodice.add(lblAttention);
                add(panelCodice);

                panelTitolo = new JPanel();     //Pannello Titolo
                panelTitolo.setLayout(new FlowLayout(FlowLayout.LEFT));
                lblTitolo = new JLabel("Titolo: ");
                panelTitolo.add(lblTitolo);
                fldTitolo = new JTextField(20);
                //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
                fldTitolo.addKeyListener(new KeyAdapter() {     
                @Override
                    public void keyTyped(KeyEvent e) {
                        if (fldTitolo.getText().length()>=150) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
                panelTitolo.add(fldTitolo);
                lblInfoTitolo = new JLabel(infoIcon, SwingConstants.RIGHT);
                lblInfoTitolo.setToolTipText("Massimo 150 caratteri. Per esempio: Villa in vendita, zona Manzano.");
                panelTitolo.add(lblInfoTitolo);
                panelTitolo.add(lblAttention2);
                add(panelTitolo);

                /*Date dataDiOggi = new Date();
                panelData = new JPanel();        //Pannello Data
                panelData.setLayout(new FlowLayout(FlowLayout.LEFT));
                lblData = new JLabel("Data annuncio: ");
                panelData.add(lblData);
                fldData = new JTextField(20);
                fldData.setText(dataDiOggi.toString());
                //fldData.setEditable(false);
                panelData.add(fldData);
                lblInfoData = new JLabel(infoIcon);
                lblInfoData.setToolTipText("La data viene generata automaticamente. Se non necessario, lasciare il valore predefinito");
                panelData.add(lblInfoData);
                add(panelData);*/
            }

        }   // fine pannello dati generali


    
    
    
    //sottopannello del pannello centrale per la posizione dell'immobile
    class PanelDatiPosizioneImmobile extends JPanel {
		private static final long serialVersionUID = 1L;
		JPanel /*panelStato,*/ panelRegione, panelProvincia, panelCap, panelComune, panelVia/*, panelLinkGoogleMaps*/;
        JLabel /*lblStato,*/ lblRegione, lblProvincia, lblCap, lblComune, lblVia/*, lblLinkGoogleMaps*/;
        JLabel /*lblInfoStato,*/ lblInfoRegione, lblInfoProvincia, lblInfoCap, lblInfoComune, lblInfoVia/*, lblInfoLinkGoogleMaps*/;
        JTextField  fldCap, fldComune, fldVia /*fldLinkGoogleMaps*/;
        //JList<String> listStato;
        JComboBox<String> cmboxRegione;
        JComboBox<String> cmboxProvincia;
        JComboBox<String> cmboxComune;
        //private final String listaStati[] = {"ITALIA"/*, "AUSTRIA"*/};

        public PanelDatiPosizioneImmobile() {
            setBorder(new TitledBorder(" - 2 -  Dati sulla posizione dell'immobile"));
            setLayout(new GridLayout(6,1));

            //Icona del pannello
            JLabel labelIcoPosizione = new JLabel(new ImageIcon("./img/icoPosizione.png"));
            JPanel panelIcoPosizione = new JPanel();
            panelIcoPosizione.add(labelIcoPosizione);
            add(panelIcoPosizione);

            /*panelStato = new JPanel();     //Pannello Stato
            //panelStato.setBackground(new Color(230,230,230));    //Colore grigio scuro per i campi obbligatori
            panelStato.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblStato = new JLabel("Stato: ");
            panelStato.add(lblStato);
            listStato = new JList(listaStati);
            listStato.addListSelectionListener(new ListSelectionListener() {  //Quando viene selezionato uno stato differente
                public void valueChanged(ListSelectionEvent e) {
                    //
                    if(listStato.getSelectedIndex()==0) {
                        cmboxProvincia.setSelectedIndex(101);
                    }
                    else {
                        cmboxProvincia.setSelectedIndex(111);
                    }
                }
            }
            );
            listStato.setVisibleRowCount(5);
            listStato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listStato.setSelectedIndex(1);  //Italia stato predefinito
            panelStato.add(listStato);
            lblInfoStato = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoStato.setToolTipText("Selezionare la nazione in cui si trova l'immobile (per ora solo Italia).");
            panelStato.add(lblInfoStato);
            //Icona campo obbligatorio
            JLabel lblAttention2 = new JLabel(new ImageIcon(AttentionImagePath));
            lblAttention2.setToolTipText("Campo obbligatorio");
            panelStato.add(lblAttention2);
            add(panelStato);*/
            
            panelRegione = new JPanel();     //Pannello Regione
            panelRegione.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblRegione = new JLabel("Regione: ");
            panelRegione.add(lblRegione);
            cmboxRegione = new JComboBox<String>(listaRegioniItalia);
            //cmboxRegione.setSelectedIndex(100);  //campo predefinito
            cmboxRegione.setVisible(true);
            panelRegione.add(cmboxRegione);
            lblInfoRegione = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoRegione.setToolTipText("Selezionare la regione in cui \u00e8 situato l'immobile.");
            panelRegione.add(lblInfoRegione);
            panelRegione.add(lblAttention10);
            add(panelRegione);

            panelProvincia = new JPanel();     //Pannello Provincia
            panelProvincia.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblProvincia = new JLabel("Provincia: ");
            panelProvincia.add(lblProvincia);
            cmboxProvincia = new JComboBox<String>(listaProvincieItalia);
            //cmboxProvincia.setSelectedIndex(100);  //campo predefinito Udine
            cmboxProvincia.setVisible(true);
            cmboxProvincia.setEnabled(false);
            panelProvincia.add(cmboxProvincia);
            lblInfoProvincia = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoProvincia.setToolTipText("Selezionare la provincia in cui \u00e8 situato l'immobile.");
            panelProvincia.add(lblInfoProvincia);
            panelProvincia.add(lblAttention3);
            add(panelProvincia);
            
            panelComune = new JPanel();     //Pannello Comune
            panelComune.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblComune = new JLabel("Comune: ");
            panelComune.add(lblComune);
            cmboxComune = new JComboBox<String>();
            cmboxComune.setVisible(true);
            cmboxComune.setEnabled(false);
            panelComune.add(cmboxComune);
            lblInfoComune = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoComune.setToolTipText("Selezionare il comune in cui \u00e8 situato l'immobile.");
            panelComune.add(lblInfoComune);
            panelComune.add(lblAttention12);
            add(panelComune);
            
            //Listener per il campo regione
            cmboxRegione.addActionListener(new ActionListener() { 
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		//Rimuovo tutte le opzioni della select Provincia
            		cmboxProvincia.removeAllItems();
            		//Creo una select temporanea
            		JComboBox<String> tempComboBox2 = new JComboBox<String>();            		
            		//Ottengo la provincia selezionata dall'utente
            		String regioneSelezionata = (String)cmboxRegione.getSelectedItem();
            		//Se quella corrente non è la option predefinita (la selezione è valida)
            		if(!regioneSelezionata.contains("---")) {
            			tempComboBox2.addItem(listaProvincieItalia[0]); //La prima voce delle select
                		//Ottengo le provincie relative alla regione selezionata e popolo la select temporanea con questi valori
                		for(int i=0; i<listaProvincieItalia.length; i++) {
                			if(listaProvincieItalia[i].contains(regioneSelezionata)) {
                				String elementToAdd = listaProvincieItalia[i];
                				//La select delle provincie viene popolata solo con l'effettivo nome della provincia (tolgo il riferimento alla regione)
                				elementToAdd = elementToAdd.substring(0, elementToAdd.indexOf("(")-1);
                				tempComboBox2.addItem(elementToAdd);
                			}               			
                		}              		
                		cmboxProvincia.setEnabled(true);
                		//La select "ufficiale" diventa una copia di quella temporanea
                		cmboxProvincia.setModel(tempComboBox2.getModel());
            		}          		             	
            	}
            }
            );
            
            //Listener per il campo provincia
            cmboxProvincia.addItemListener(new ItemListener() { 
            	public void itemStateChanged(ItemEvent  e) {
            		//Rimuovo tutte le opzioni della select Comune
            		cmboxComune.removeAllItems();
            		//Creo una select temporanea
            		JComboBox<String> tempComboBox = new JComboBox<String>();            		
            		//Ottengo la provincia selezionata dall'utente
            		String provinciaSelezionata = (String)cmboxProvincia.getSelectedItem();
            		//Se non quella corrente non è la option predefinita (la selezione è valida), devo anche verificare che non sia null
            		if(provinciaSelezionata!=null && !provinciaSelezionata.contains("---")) {
                		//Ottengo i comuni relativi alla provincia selezionata e popolo la select temporanea con questi valori
                		for(int i=0; i<listaComuniProvincie.length; i++) {
                			if(listaComuniProvincie[i].contains(provinciaSelezionata)) {
                				String elementToAdd = listaComuniProvincie[i];
                				elementToAdd = elementToAdd.substring(elementToAdd.indexOf("$")+1, elementToAdd.length());
                				tempComboBox.addItem(elementToAdd);
                			}               			
                		}              		
                		cmboxComune.setEnabled(true);
                		//La select "ufficiale" diventa una copia di quella temporanea
                		cmboxComune.setModel(tempComboBox.getModel());
            		}       		             	
            	}
            }
            );

            panelCap = new JPanel();     //Pannello CAP
            panelCap.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblCap = new JLabel("CAP: ");
            panelCap.add(lblCap);
            fldCap = new JTextField(10);
            fldCap.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || fldCap.getText().length()>=7 ) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelCap.add(fldCap);
            lblInfoCap = new JLabel(infoIcon);
            lblInfoCap.setToolTipText("Massimo 7 caratteri. Inserire solo cifre.  Per esempio: 33100.");
            panelCap.add(lblInfoCap);
            add(panelCap);

            panelVia = new JPanel();     //Pannello Via/Piazza
            panelVia.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblVia = new JLabel("Via/Piazza/Localit\u00e0: ");
            panelVia.add(lblVia);
            fldVia = new JTextField(10);
            fldVia.addKeyListener(new KeyAdapter() {     
                @Override
                    public void keyTyped(KeyEvent e) {
                        if (fldVia.getText().length()>=60) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
            panelVia.add(fldVia);
            lblInfoVia = new JLabel(infoIcon);
            lblInfoVia.setToolTipText("Massimo 150 caratteri. Per esempio: via San Rocco 120");
            panelVia.add(lblInfoVia);
            add(panelVia);

            /*panelLinkGoogleMaps = new JPanel();     //Pannello Mappa Google Maps
            panelLinkGoogleMaps.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblLinkGoogleMaps = new JLabel("Keyword Google Maps: ");
            panelLinkGoogleMaps.add(lblLinkGoogleMaps);
            fldLinkGoogleMaps = new JTextField(15);
            fldLinkGoogleMaps.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9')  || (c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelLinkGoogleMaps.add(fldLinkGoogleMaps);
            lblInfoLinkGoogleMaps = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoLinkGoogleMaps.setToolTipText("Solo lettere e numeri. Preferibile inserire CAP, CITTA', VIA.  Per esempio: 33100 Udine via Buttrio");
            panelLinkGoogleMaps.add(lblInfoLinkGoogleMaps);
            add(panelLinkGoogleMaps);*/
        }

    }   // fine pannello dati posizione


    
    
    
    //sottopannello del pannello centrale per la descrizione generale dell'immobile
    class PanelDatiDescrizioneImmobile extends JPanel {
		private static final long serialVersionUID = 1L;
		private JPanel panelDescrizioneSintetica, panelDescrizioneGenerale, panelCategoriaImmobile, panelTipologiaImmobile/*, panelTipologiaContratto*/, panelTipologiaCatalogazione, panelNroBagni, panelNroCamere, /*panelCondizioniImmobile,*/ panelSuperficieAbitazione, /*panelSuperficieGiardino,*/ panelAnnoCostruzione, panelPiano, panelStatoImmobile, /*panelIngressiImmobile,*/ panelArredamentiImmobile, panelRiscaldamentoImmobile, panelClimaImmobile, panelCertificazioniImmobile, panelGiardinoImmobile, panelParcheggioImmobile/*,panelAltri*/;
        private JLabel lblDescrizioneSintetica, lblDescrizioneGenerale, lblCategoriaImmobile, lblTipologiaImmobile, /*lblTipologiaContratto,*/ lblTipologiaCatalogazione, lblNroBagni, lblNroCamere, /*lblCondizioniImmobile,*/ lblSuperficieAbitazione, /*lblSuperficieGiardino,*/ lblAnnoCostruzione, lblPiano, lblSuPiani, lblStatoImmobile, /*lblIngressiImmobile,*/ lblArredamentiImmobile, lblRiscaldamentoImmobile, lblClimaImmobile, lblCertificazioniImmobile, lblGiardinoImmobile, lblParcheggioImmobile;
        private JLabel lblInfoDescrizioneSintetica, lblInfoDescrizioneGenerale, lblInfoCategoriaImmobile, lblInfoTipologiaImmobile, /*lblInfoTipologiaContratto,*/ lblInfoTipologiaCatalogazione, lblInfoNroBagni, lblInfoNroCamere, /*lblInfoCondizioniImmobile,*/ lblInfoSuperficieAbitazione, /*lblInfoSuperficieGiardino,*/ lblInfoAnnoCostruzione, lblInfoSuPiani, lblInfoStatoImmobile, /*lblInfoIngressiImmobile,*/ lblInfoArredamentiImmobile, lblInfoRiscaldamentoImmobile, lblInfoClimaImmobile, lblInfoCertificazioniImmobile, lblInfoGiardinoImmobile, lblInfoParcheggioImmobile/*,lblInfoAltri*/;
        JTextField fldDescrizioneSintetica, fldSuperficieAbitazione/*, fldSuperficieGiardino*/, fldAnnoCostruzione, fldPiano, fldSuPiani;
        JTextArea txtareaDescrizioneGenerale;
        JComboBox<String> cmboxCategoriaImmobile, cmboxTipologiaImmobile/*, cmboxTipologiaContratto*/, cmboxTipologiaCatalogazione, cmboxNroBagni, cmboxNroCamere, cmboxStatoImmobile,/*, cmboxIngressiImmobile, */cmboxArredamentiImmobile, cmboxRiscaldamentoImmobile, cmboxClimaImmobile, cmboxCertificazioniImmobile, cmboxGiardinoImmobile, cmboxParcheggioImmobile;
        //ButtonGroup radioGrpCondizioniAttuali;
        //JRadioButton radioBtnLibero, radioBtnOccupato;
        private final String vociCategoria[] = {" --- Seleziona la categoria --- ", "Residenziale", "Commerciale", "Industriale" /*"Turistico"*/};
        private final String vociStatoImmobile[] = {"Nuovo", "Ristrutturato", "Da ristrutturare", "In buono stato", "Abitabile" , "Ottimo", "In costruzione"};
        //private final String vociIngressi[] = {"1 ingresso", "2 ingressi", "3 ingressi", "4 o piï¿½ ingressi"};
        private final String vociArredamenti[] = {"Arredato", "Semi-arredato", "Non arredato"};
        private final String vociRiscaldamento[] = {"Assente", "Centralizzato", "Autonomo", "Stufa"};
        private final String vociClima[] = {"Assente", "Aria condizionata", "Climatizzatore"};
        private final String vociCertificazioni[] = {"Nessuna", /*"Libretto impianti",*/ /*"Certificazione energia",*/ /*"Pannelli fotovoltaici",*/ /*"Certificazione energetica A++",*/ "Certificazione energetica A+", "Certificazione energetica A", "Certificazione energetica B", "Certificazione energetica C", "Certificazione energetica D", "Certificazione energetica E", "Certificazione energetica F", "Certificazione energetica G",};
        private final String vociGiardino[] = {"Assente", "Giardino condominiale", "Giardino ad uso esclusivo"};
        private final String vociParcheggio[] = {"Nessuno", "Garage", "Box auto", "Posto auto"};
        private final String tipologieImmobile1[] = {"Appartamento", "Attico", "Bifamiliare", "Casa", "Garage", "Palazzo", "Rustico", "Terreno agricolo", "Terreno edificabile", "Villa", "Villaschiera"};	//Residenziale        
        private final String tipologieImmobile2[] = {"Agriturismo", "Albergo", "Bar", "Negozio", "Ristorante", "Ufficio"};	//Commerciale
        private final String tipologieImmobile3[] = {"Capannone", "Laboratorio", "Magazzino"};	//Industriale
        //private final String tipologieCatalogazione[] = {"Monolocale", "Monocamera", "Bicamere", "Tricamere", "Pluricamere", "Altro"};
        private final String tipologieCatalogazione[] = {"1", "2", "3", "4", "5", "6", "7", ">7"};
        private final String numeroBagni[] = {"1", "2", "3", "4", "5", ">5"};
        private final String numeroCamere[] = {"1", "2", "3", "4", "5", ">5"};
        //JRadioButton[] radioCondizioniImmobile;
        //private final String strCondizioniImmobile[] = {"Libero", "Affittato"};

        public PanelDatiDescrizioneImmobile() {
            setBorder(new TitledBorder(" - 3 -  Descrizione generale dell'immobile"));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            //icone del pannello
            JLabel labelIcoCasa = new JLabel(new ImageIcon("./img/icoCasa.png"));
            JPanel panelIcoCasa = new JPanel();
            panelIcoCasa.add(labelIcoCasa);
            add(panelIcoCasa);

            panelDescrizioneSintetica = new JPanel();     //Pannello Descrizione sintetica
            panelDescrizioneSintetica.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblDescrizioneSintetica = new JLabel("<html>Descrizione<br>breve: </html>");
            panelDescrizioneSintetica.add(lblDescrizioneSintetica);
            fldDescrizioneSintetica = new JTextField(20);
            fldDescrizioneSintetica.addKeyListener(new KeyAdapter() {     
                @Override
                    public void keyTyped(KeyEvent e) {
                        if (fldDescrizioneSintetica.getText().length()>=200) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
            panelDescrizioneSintetica.add(fldDescrizioneSintetica);
            lblInfoDescrizioneSintetica = new JLabel(infoIcon);
            lblInfoDescrizioneSintetica.setToolTipText("Massimo 200 caratteri. Inserire una breve frase introduttiva.");
            panelDescrizioneSintetica.add(lblInfoDescrizioneSintetica);
            panelDescrizioneSintetica.add(lblAttention4);
            add(panelDescrizioneSintetica);

            panelDescrizioneGenerale = new JPanel();     //Pannello Descrizione generale
            panelDescrizioneGenerale.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblDescrizioneGenerale = new JLabel("<html>Testo<br>scheda: </html>");
            panelDescrizioneGenerale.add(lblDescrizioneGenerale);
            txtareaDescrizioneGenerale = new JTextArea(7,21);
            txtareaDescrizioneGenerale.addKeyListener(new KeyAdapter() {     
                @Override
                    public void keyTyped(KeyEvent e) {
                        if (txtareaDescrizioneGenerale.getText().length()>=2000) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
            txtareaDescrizioneGenerale.setLineWrap(true);
            panelDescrizioneGenerale.add(new JScrollPane(txtareaDescrizioneGenerale));
            lblInfoDescrizioneGenerale = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoDescrizioneGenerale.setToolTipText("Massimo 2000 caratteri. Inserire una descrizione generale dell'immobile.");
            panelDescrizioneGenerale.add(lblInfoDescrizioneGenerale);
            panelDescrizioneGenerale.add(lblAttention5);
            add(panelDescrizioneGenerale);

            panelCategoriaImmobile = new JPanel();     //Pannello Categoria immobile
            panelCategoriaImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblCategoriaImmobile = new JLabel("Categoria: ");
            panelCategoriaImmobile.add(lblCategoriaImmobile);
            cmboxCategoriaImmobile = new JComboBox<String>(vociCategoria);
            panelCategoriaImmobile.add(cmboxCategoriaImmobile);
            lblInfoCategoriaImmobile = new JLabel(infoIcon);
            lblInfoCategoriaImmobile.setToolTipText("Selezionare la categoria di immobile");
            panelCategoriaImmobile.add(lblInfoCategoriaImmobile);
            panelCategoriaImmobile.add(lblAttention6);
            add(panelCategoriaImmobile);

            panelTipologiaImmobile = new JPanel();     //Pannello Tipologia immobile
            panelTipologiaImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblTipologiaImmobile = new JLabel("Tipologia: ");
            panelTipologiaImmobile.add(lblTipologiaImmobile);
            cmboxTipologiaImmobile = new JComboBox<String>();
            cmboxTipologiaImmobile.setEnabled(false);
            panelTipologiaImmobile.add(cmboxTipologiaImmobile);
            lblInfoTipologiaImmobile = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoTipologiaImmobile.setToolTipText("Selezionare la tipologia di immobile");
            panelTipologiaImmobile.add(lblInfoTipologiaImmobile);
            add(panelTipologiaImmobile);
            
            //Listener per il campo Categoria
            cmboxCategoriaImmobile.addActionListener(new ActionListener() { 
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		//Rimuovo tutte le opzioni della select Tipologia immobile
            		cmboxTipologiaImmobile.removeAllItems();
            		//Creo una select temporanea
            		JComboBox<String> tempComboBox = null;            		
            		//Ottengo la categoria selezionata dall'utente e popolo la select temporanea con i valori corretti
            		String categoriaSelezionata = (String)cmboxCategoriaImmobile.getSelectedItem();
            		switch (categoriaSelezionata)
                	{
                	    case "Residenziale":
                	    	tempComboBox = new JComboBox<String>(tipologieImmobile1);
                	    	cmboxTipologiaImmobile.setEnabled(true);
                	    	cmboxTipologiaImmobile.setModel(tempComboBox.getModel());
                	    	
                	    	//Abilito la select "Nr. camere"
                	    	cmboxNroCamere.setEnabled(true);
                	        break;
                	    case "Commerciale":
                	    	tempComboBox = new JComboBox<String>(tipologieImmobile2);
                	    	cmboxTipologiaImmobile.setEnabled(true);
                	    	cmboxTipologiaImmobile.setModel(tempComboBox.getModel());
                	    	//Abilito la select "Nr. camere"
                	    	cmboxNroCamere.setEnabled(false);
                	    	break;
                	    case "Industriale":
                	    	tempComboBox = new JComboBox<String>(tipologieImmobile3);
                	    	cmboxTipologiaImmobile.setEnabled(true);
                	    	cmboxTipologiaImmobile.setModel(tempComboBox.getModel()); 
                	    	//Abilito la select "Nr. camere"
                	    	cmboxNroCamere.setEnabled(false);
                	    	break;
                	    default:
                	    	cmboxNroCamere.setEnabled(false);
                	    	//              	    	
                	}             		            		
            	}
            }
            );

            panelTipologiaCatalogazione = new JPanel();     //Pannello Tipologia catalogazione
            panelTipologiaCatalogazione.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblTipologiaCatalogazione = new JLabel("Nr. locali: ");
            panelTipologiaCatalogazione.add(lblTipologiaCatalogazione);
            cmboxTipologiaCatalogazione = new JComboBox<String>(tipologieCatalogazione);
            panelTipologiaCatalogazione.add(cmboxTipologiaCatalogazione);
            lblInfoTipologiaCatalogazione = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoTipologiaCatalogazione.setToolTipText("Selezionare la tipologia di catalogazione valida per l'immobile");
            panelTipologiaCatalogazione.add(lblInfoTipologiaCatalogazione);
            add(panelTipologiaCatalogazione);

            panelNroBagni = new JPanel();     //Pannello Nr bagni
            panelNroBagni.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblNroBagni = new JLabel("Nr. bagni: ");
            panelNroBagni.add(lblNroBagni);
            cmboxNroBagni = new JComboBox<String>(numeroBagni);
            panelNroBagni.add(cmboxNroBagni);
            lblInfoNroBagni = new JLabel(infoIcon);
            lblInfoNroBagni.setToolTipText("Selezionare il numero di bagni");
            panelNroBagni.add(lblInfoNroBagni);
            add(panelNroBagni);

            panelNroCamere = new JPanel();     //Pannello Nr camere
            panelNroCamere.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblNroCamere = new JLabel("Nr. camere da letto: ");
            panelNroCamere.add(lblNroCamere);
            cmboxNroCamere = new JComboBox<String>(numeroCamere);
            //La select è disabilitata di default
            cmboxNroCamere.setEnabled(false);
            panelNroCamere.add(cmboxNroCamere);
            lblInfoNroCamere = new JLabel(infoIcon);
            lblInfoNroCamere.setToolTipText("Selezionare il numero di camere");
            panelNroCamere.add(lblInfoNroCamere);
            add(panelNroCamere);

            panelSuperficieAbitazione = new JPanel();     //Pannello Superficie abitazione
            panelSuperficieAbitazione.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblSuperficieAbitazione = new JLabel("Superficie abitazione (in mq.): ");
            panelSuperficieAbitazione.add(lblSuperficieAbitazione);
            fldSuperficieAbitazione = new JTextField(10);
            fldSuperficieAbitazione.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))  || fldSuperficieAbitazione.getText().length()>=10) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelSuperficieAbitazione.add(fldSuperficieAbitazione);
            lblInfoSuperficieAbitazione = new JLabel(infoIcon);
            lblInfoSuperficieAbitazione.setToolTipText("Massimo 10 caratteri. Inserire solo cifre, indicare la superficie interna calpestabile. Per esempio: 85");
            panelSuperficieAbitazione.add(lblInfoSuperficieAbitazione);
            panelSuperficieAbitazione.add(lblAttention7);
            add(panelSuperficieAbitazione);

            /*panelSuperficieGiardino = new JPanel();     //Pannello Superficie giardino
            panelSuperficieGiardino.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblSuperficieGiardino = new JLabel("Superficie giardino (in mq.): ");
            panelSuperficieGiardino.add(lblSuperficieGiardino);
            fldSuperficieGiardino = new JTextField(10);
            fldSuperficieGiardino.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelSuperficieGiardino.add(fldSuperficieGiardino);
            lblInfoSuperficieGiardino = new JLabel(infoIcon);
            lblInfoSuperficieGiardino.setToolTipText("Solo numeri. Indicare la superficie del giardino esterno calpestabile.  Per esempio: 230");
            panelSuperficieGiardino.add(lblInfoSuperficieGiardino);
            add(panelSuperficieGiardino);*/

            panelAnnoCostruzione = new JPanel();     //Pannello Anno di costruzione
            panelAnnoCostruzione.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblAnnoCostruzione = new JLabel("Anno costruzione: ");
            panelAnnoCostruzione.add(lblAnnoCostruzione);
            fldAnnoCostruzione = new JTextField(10);
            fldAnnoCostruzione.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelAnnoCostruzione.add(fldAnnoCostruzione);
            lblInfoAnnoCostruzione = new JLabel(infoIcon);
            lblInfoAnnoCostruzione.setToolTipText("Solo numeri. Indicare l'anno di costruzione.  Per esempio: 1985");
            panelAnnoCostruzione.add(lblInfoAnnoCostruzione);
            add(panelAnnoCostruzione);

            panelPiano = new JPanel();     //Pannello Piano
            panelPiano.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblPiano = new JLabel("Piano: ");
            panelPiano.add(lblPiano);
            fldPiano = new JTextField(2);
            fldPiano.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelPiano.add(fldPiano);
            lblSuPiani = new JLabel("...su un totale di piani: ");
            panelPiano.add(lblSuPiani);
            fldSuPiani = new JTextField(2);
            fldSuPiani.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelPiano.add(fldSuPiani);
            lblInfoSuPiani = new JLabel(infoIcon);
            lblInfoSuPiani.setToolTipText("Solo numeri per entrambi. Indicare il piano in cui si trova l'appartamento e il numero di piani totali dell'immobile (inserire 0 per il piano terra).");
            panelPiano.add(lblInfoSuPiani);
            add(panelPiano);

            /*panelCondizioniImmobile = new JPanel();     //Pannello Condizioni immobile
            panelCondizioniImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            radioGrpCondizioniAttuali = new ButtonGroup();
            radioCondizioniImmobile = new JRadioButton[2];
            lblCondizioniImmobile = new JLabel("Status: ");
            panelCondizioniImmobile.add(lblCondizioniImmobile);
            for (int i=0; i<2; i++) {
                radioCondizioniImmobile[i]=new JRadioButton(strCondizioniImmobile[i], false);
                radioCondizioniImmobile[i].setActionCommand(strCondizioniImmobile[i]);
                radioGrpCondizioniAttuali.add(radioCondizioniImmobile[i]);
                //radioPanel1.add(radioStatus[i]);
                panelCondizioniImmobile.add(radioCondizioniImmobile[i]);
            }
            lblInfoCondizioniImmobile = new JLabel(infoIcon);
            lblInfoCondizioniImmobile.setToolTipText("Indicare se l'immobile \u00e8 libero o occupato");
            panelCondizioniImmobile.add(lblInfoCondizioniImmobile);
            add(panelCondizioniImmobile);
            radioCondizioniImmobile[0].setSelected(true);*/

            panelStatoImmobile = new JPanel();     //Pannello stato immobile
            panelStatoImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblStatoImmobile = new JLabel("Stato: ");
            panelStatoImmobile.add(lblStatoImmobile);
            cmboxStatoImmobile = new JComboBox<String>(vociStatoImmobile);
            panelStatoImmobile.add(cmboxStatoImmobile);
            lblInfoStatoImmobile = new JLabel(infoIcon);
            lblInfoStatoImmobile.setToolTipText("Selezionare lo stato attuale dell'immobile");
            panelStatoImmobile.add(lblInfoStatoImmobile);
            add(panelStatoImmobile);

            /*panelIngressiImmobile = new JPanel();     //Pannello ingressi immobile
            panelIngressiImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblIngressiImmobile = new JLabel("Ingressi: ");
            panelIngressiImmobile.add(lblIngressiImmobile);
            cmboxIngressiImmobile = new JComboBox(vociIngressi);
            panelIngressiImmobile.add(cmboxIngressiImmobile);
            lblInfoIngressiImmobile = new JLabel(infoIcon);
            lblInfoIngressiImmobile.setToolTipText("Selezionare il numero di ingressi dell'immobile");
            panelIngressiImmobile.add(lblInfoIngressiImmobile);
            add(panelIngressiImmobile);*/

            panelArredamentiImmobile = new JPanel();     //Pannello Arredamenti immobile
            panelArredamentiImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblArredamentiImmobile = new JLabel("Arredamenti: ");
            panelArredamentiImmobile.add(lblArredamentiImmobile);
            cmboxArredamentiImmobile = new JComboBox<String>(vociArredamenti);
            panelArredamentiImmobile.add(cmboxArredamentiImmobile);
            lblInfoArredamentiImmobile = new JLabel(infoIcon);
            lblInfoArredamentiImmobile.setToolTipText("Selezionare la tipologia di arredamento dell'immobile");
            panelArredamentiImmobile.add(lblInfoArredamentiImmobile);
            add(panelArredamentiImmobile);

            panelRiscaldamentoImmobile = new JPanel();     //Pannello Riscaldamento immobile
            panelRiscaldamentoImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblRiscaldamentoImmobile = new JLabel("Riscaldamento: ");
            panelRiscaldamentoImmobile.add(lblRiscaldamentoImmobile);
            cmboxRiscaldamentoImmobile = new JComboBox<String>(vociRiscaldamento);
            panelRiscaldamentoImmobile.add(cmboxRiscaldamentoImmobile);
            lblInfoRiscaldamentoImmobile = new JLabel(infoIcon);
            lblInfoRiscaldamentoImmobile.setToolTipText("Selezionare la tipologia di riscaldamento dell'immobile");
            panelRiscaldamentoImmobile.add(lblInfoRiscaldamentoImmobile);
            add(panelRiscaldamentoImmobile);

            panelClimaImmobile = new JPanel();     //Pannello Clima immobile
            panelClimaImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblClimaImmobile = new JLabel("Clima: ");
            panelClimaImmobile.add(lblClimaImmobile);
            cmboxClimaImmobile = new JComboBox<String>(vociClima);
            panelClimaImmobile.add(cmboxClimaImmobile);
            lblInfoClimaImmobile = new JLabel(infoIcon);
            lblInfoClimaImmobile.setToolTipText("Selezionare la tipologia di climatizzazione dell'immobile");
            panelClimaImmobile.add(lblInfoClimaImmobile);
            add(panelClimaImmobile);

            panelCertificazioniImmobile = new JPanel();     //Pannello Certificazioni immobile
            panelCertificazioniImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblCertificazioniImmobile = new JLabel("Certificazioni: ");
            panelCertificazioniImmobile.add(lblCertificazioniImmobile);
            cmboxCertificazioniImmobile = new JComboBox<String>(vociCertificazioni);
            panelCertificazioniImmobile.add(cmboxCertificazioniImmobile);
            lblInfoCertificazioniImmobile = new JLabel(infoIcon);
            lblInfoCertificazioniImmobile.setToolTipText("Selezionare la tipologia di certificazione energetica dell'immobile");
            panelCertificazioniImmobile.add(lblInfoCertificazioniImmobile);
            add(panelCertificazioniImmobile);

            panelGiardinoImmobile = new JPanel();     //Pannello Giardino immobile
            panelGiardinoImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblGiardinoImmobile = new JLabel("Giardino: ");
            panelGiardinoImmobile.add(lblGiardinoImmobile);
            cmboxGiardinoImmobile = new JComboBox<String>(vociGiardino);
            panelGiardinoImmobile.add(cmboxGiardinoImmobile);
            lblInfoGiardinoImmobile = new JLabel(infoIcon);
            lblInfoGiardinoImmobile.setToolTipText("Selezionare la tipologia di giardino dell'immobile");
            panelGiardinoImmobile.add(lblInfoGiardinoImmobile);
            add(panelGiardinoImmobile);

            panelParcheggioImmobile = new JPanel();     //Pannello Parcheggio immobile
            panelParcheggioImmobile.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblParcheggioImmobile = new JLabel("Parcheggio: ");
            panelParcheggioImmobile.add(lblParcheggioImmobile);
            cmboxParcheggioImmobile = new JComboBox<String>(vociParcheggio);
            panelParcheggioImmobile.add(cmboxParcheggioImmobile);
            lblInfoParcheggioImmobile = new JLabel(infoIcon);
            lblInfoParcheggioImmobile.setToolTipText("Selezionare la tipologia di parcheggi dell'immobile");
            panelParcheggioImmobile.add(lblInfoParcheggioImmobile);
            add(panelParcheggioImmobile);

            /*panelAltri = new JPanel();     //Pannello Altre info
            panelAltri.setLayout(new FlowLayout(FlowLayout.LEFT));
            checkBoxGarage = new JCheckBox("Garage");
            panelAltri.add(checkBoxGarage);
            checkBoxGiardino = new JCheckBox("Giardinio");
            panelAltri.add(checkBoxGiardino);
            checkBoxAriaCondizionata = new JCheckBox("Aria condizionata");
            panelAltri.add(checkBoxAriaCondizionata);
            checkBoxRiscaldamentoAutonomo = new JCheckBox("Riscaldamento autonomo");
            panelAltri.add(checkBoxRiscaldamentoAutonomo);
            lblInfoAltri = new JLabel(infoIcon);
            lblInfoAltri.setToolTipText("Indicare la presenza di questi ulteriori elementi");
            panelAltri.add(lblInfoAltri);
            add(panelAltri);*/
        }

    }   // fine pannello dati descrizione


    
    
    
    //sottopannello del pannello centrale per l'inserimento di immagini
    class PanelImmagini extends JPanel {
		private static final long serialVersionUID = 1L;
		JTextField fldFoto1, fldFoto2, fldFoto3, fldFoto4, fldFoto5, fldFoto6;
        JButton btnFoto1, btnFoto2, btnFoto3, btnFoto4, btnFoto5, btnFoto6;
        JLabel lblInfoFoto;
        
        //Puntatoti ai file immagine
        File fileImmagine1, fileImmagine2, fileImmagine3, fileImmagine4, fileImmagine5, fileImmagine6;

        public PanelImmagini() {
            setBorder(new TitledBorder(" - 4 -  Immagini a corredo della scheda"));
            setLayout(new GridLayout(7,1));
            
            //Dimensioni massime delle immagini
            final int maxFileSize = 1048576;
            //Formato immagini consentito
            final String format=".jpg";
            
            //icone del pannello
            JLabel labelIcoFoto = new JLabel(new ImageIcon("./img/icoFoto.png"));
            JPanel panelIcoFoto = new JPanel();
            panelIcoFoto.add(labelIcoFoto);
            add(panelIcoFoto);
            
            JPanel panelFoto1 = new JPanel();     //Pannello Foto 1
            panelFoto1.setLayout(new FlowLayout(FlowLayout.LEFT));
            btnFoto1 = new JButton("Immagine 1");
            //Ascoltatore interno per il pulsante...
            btnFoto1.addActionListener(new ActionListener() {
                JFileChooser dlgFile;
                String absPath;

                public void actionPerformed(ActionEvent ev) {
                    dlgFile = new JFileChooser();
                    if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    	//Controllo il formato del file
                    	File selectedFile = dlgFile.getSelectedFile(); 
                    	Long fileSize = selectedFile.length();                    	
                    	String selectedFileName = selectedFile.getName().toLowerCase();                   	
                        if(selectedFile.isFile() && selectedFileName.contains(format) && fileSize<maxFileSize) {
                        	fileImmagine1 = dlgFile.getSelectedFile();
                            absPath = fileImmagine1.getAbsolutePath();
                            fldFoto1.setText(absPath);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 mega.");
                        }	
                    }
                }               
            });
            panelFoto1.add(btnFoto1);
            fldFoto1 = new JTextField(20);
            fldFoto1.setEnabled(false);
            panelFoto1.add(fldFoto1);
            lblInfoFoto = new JLabel(infoIcon);
            lblInfoFoto.setToolTipText("Le immagini devono essere in formato JPG. Inserirle in ordine di importanza.");
            panelFoto1.add(lblInfoFoto);
            add(panelFoto1);

            JPanel panelFoto2 = new JPanel();     //Pannello Foto 2
            panelFoto2.setLayout(new FlowLayout(FlowLayout.LEFT));
            btnFoto2 = new JButton("Immagine 2");
            btnFoto2.addActionListener(new ActionListener() {
                JFileChooser dlgFile;
                String absPath;

                public void actionPerformed(ActionEvent ev) {
                    dlgFile = new JFileChooser();
                    if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    	//Controllo il formato del file
                    	File selectedFile = dlgFile.getSelectedFile();
                    	Long fileSize = selectedFile.length();
                    	String selectedFileName = selectedFile.getName().toLowerCase();
                    	if(selectedFile.isFile() && selectedFileName.contains(format) && fileSize<maxFileSize) {
                        	fileImmagine2 = dlgFile.getSelectedFile();
                            absPath = fileImmagine2.getAbsolutePath();
                            fldFoto2.setText(absPath);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
                        }
                    }
                }
            });
            panelFoto2.add(btnFoto2);
            fldFoto2 = new JTextField(20);
            fldFoto2.setEnabled(false);
            panelFoto2.add(fldFoto2);
            add(panelFoto2);

            JPanel panelFoto3 = new JPanel();     //Pannello Foto 3
            panelFoto3.setLayout(new FlowLayout(FlowLayout.LEFT));
            btnFoto3 = new JButton("Immagine 3");
            btnFoto3.addActionListener(new ActionListener() {
                JFileChooser dlgFile;
                String absPath;

                public void actionPerformed(ActionEvent ev) {
                    dlgFile = new JFileChooser();
                    if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    	//Controllo il formato del file
                    	File selectedFile = dlgFile.getSelectedFile();  
                    	Long fileSize = selectedFile.length();
                    	String selectedFileName = selectedFile.getName().toLowerCase();
                    	if(selectedFile.isFile() && selectedFileName.contains(format) && fileSize<maxFileSize) {
                        	fileImmagine3 = dlgFile.getSelectedFile();
                            absPath = fileImmagine3.getAbsolutePath();
                            fldFoto3.setText(absPath);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
                        }
                    }
                }
            });
            panelFoto3.add(btnFoto3);
            fldFoto3 = new JTextField(20);
            fldFoto3.setEnabled(false);
            panelFoto3.add(fldFoto3);
            add(panelFoto3);

            JPanel panelFoto4 = new JPanel();     //Pannello Foto 4
            panelFoto4.setLayout(new FlowLayout(FlowLayout.LEFT));
            btnFoto4 = new JButton("Immagine 4");
            btnFoto4.addActionListener(new ActionListener() {
                JFileChooser dlgFile;
                String absPath;

                public void actionPerformed(ActionEvent ev) {
                    dlgFile = new JFileChooser();
                    if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    	//Controllo il formato del file
                    	File selectedFile = dlgFile.getSelectedFile(); 
                    	Long fileSize = selectedFile.length();
                    	String selectedFileName = selectedFile.getName().toLowerCase();
                    	if(selectedFile.isFile() && selectedFileName.contains(format) && fileSize<maxFileSize) {
                        	fileImmagine4 = dlgFile.getSelectedFile();
                            absPath = fileImmagine4.getAbsolutePath();
                            fldFoto4.setText(absPath);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
                        }
                    }
                }
            });
            panelFoto4.add(btnFoto4);
            fldFoto4 = new JTextField(20);
            fldFoto4.setEnabled(false);
            panelFoto4.add(fldFoto4);
            add(panelFoto4);

            JPanel panelFoto5 = new JPanel();     //Pannello Foto 5
            panelFoto5.setLayout(new FlowLayout(FlowLayout.LEFT));
            btnFoto5 = new JButton("Immagine 5");
            btnFoto5.addActionListener(new ActionListener() {
                JFileChooser dlgFile;
                String absPath;

                public void actionPerformed(ActionEvent ev) {
                    dlgFile = new JFileChooser();
                    if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    	//Controllo il formato del file
                    	File selectedFile = dlgFile.getSelectedFile();
                    	Long fileSize = selectedFile.length();
                    	String selectedFileName = selectedFile.getName().toLowerCase();
                    	if(selectedFile.isFile() && selectedFileName.contains(format) && fileSize<maxFileSize) {
                        	fileImmagine5 = dlgFile.getSelectedFile();
                            absPath = fileImmagine5.getAbsolutePath();
                            fldFoto5.setText(absPath);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
                        }
                    }
                }
            });
            panelFoto5.add(btnFoto5);
            fldFoto5 = new JTextField(20);
            fldFoto5.setEnabled(false);
            panelFoto5.add(fldFoto5);
            add(panelFoto5);

            JPanel panelFoto6 = new JPanel();       //Pannello Foto 6
            panelFoto6.setLayout(new FlowLayout(FlowLayout.LEFT));
            btnFoto6 = new JButton("Immagine 6");
            btnFoto6.addActionListener(new ActionListener() {
                JFileChooser dlgFile;
                String absPath;

                public void actionPerformed(ActionEvent ev) {
                    dlgFile = new JFileChooser();
                    if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    	//Controllo il formato del file
                    	File selectedFile = dlgFile.getSelectedFile(); 
                    	Long fileSize = selectedFile.length();
                    	String selectedFileName = selectedFile.getName().toLowerCase();
                    	if(selectedFile.isFile() && selectedFileName.contains(format) && fileSize<maxFileSize) {
                        	fileImmagine6 = dlgFile.getSelectedFile();
                            absPath = fileImmagine6.getAbsolutePath();
                            fldFoto6.setText(absPath);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
                        }
                    }
                }
            });
            panelFoto6.add(btnFoto6);     
            fldFoto6 = new JTextField(20);
            fldFoto6.setEnabled(false);
            panelFoto6.add(fldFoto6);
            add(panelFoto6);
        }

    }   // fine pannello immagini


    
    
    
    //sottopannello del pannello centrale che ospita i dati riferiti al tipo di contratto
    class PanelDatiContratto extends JPanel {        
		private static final long serialVersionUID = 1L;
		private JPanel /*panelRiferimento,*/ panelProvvigione, panelPrezzo /*panelSpeseCondominiali*/,panelTipologiaContratto;
        private JLabel /*lblRiferimento,*/ lblProvvigione, lblPrezzo,lblTipologiaContratto /*lblSpeseCondominiali*/;
        private JLabel /*lblInfoRiferimento,*/ lblInfoProvvigione, lblInfoPrezzo, /*lblInfoSpeseCondominiali*/ lblInfoTipologiaContratto;
        JTextField fldProvvigione, fldPrezzo /*fldSpeseCondominiali*/;
        JComboBox<String> cmboxTipologiaContratto;
        //JList<String> listRiferimento;
        //private final String listaRiferimenti[] = {"Alessandro Morossi", "Alessia Piccinin"};
        private final String tipologieContratto[] = {"Affitto", "Vendita"};
        
        public PanelDatiContratto() {
            setBorder(new TitledBorder(" - 5 -  Dati riguardanti le condizioni contrattuali"));
            setLayout(new GridLayout(4,1));

            JLabel labelIcoContratto = new JLabel(new ImageIcon("./img/icoContratto.png"));
            JPanel panelIcoContratto = new JPanel();
            panelIcoContratto.add(labelIcoContratto);
            add(panelIcoContratto);

            /*panelRiferimento = new JPanel();     //Pannello Riferimento
            //panelRiferimento.setBackground(new Color(230,230,230));    //Colore grigio scuro per i campi obbligatori
            panelRiferimento.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblRiferimento = new JLabel("Riferimento: ");
            panelRiferimento.add(lblRiferimento);
            listRiferimento = new JList(listaRiferimenti);
            listRiferimento.setVisibleRowCount(5);
            listRiferimento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listRiferimento.setSelectedIndex(0);
            panelRiferimento.add(listRiferimento);
            lblInfoRiferimento = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoRiferimento.setToolTipText("Selezionare il nominativo di riferimento.");
            panelRiferimento.add(lblInfoRiferimento);
            //Icona campo obbligatorio
            JLabel lblAttention10 = new JLabel(new ImageIcon(AttentionImagePath));
            lblAttention10.setToolTipText("Campo obbligatorio");
            panelRiferimento.add(lblAttention10);
            add(panelRiferimento);*/
            
            panelTipologiaContratto = new JPanel();      //Pannello Tipologia contratto
            panelTipologiaContratto.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblTipologiaContratto = new JLabel("Tipologia di contratto: ");
            panelTipologiaContratto.add(lblTipologiaContratto);
            cmboxTipologiaContratto = new JComboBox<String>(tipologieContratto);
            panelTipologiaContratto.add(cmboxTipologiaContratto);
            lblInfoTipologiaContratto = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoTipologiaContratto.setToolTipText("Selezionare la tipologia di contratto valida per l'immobile");
            panelTipologiaContratto.add(lblInfoTipologiaContratto);
            panelTipologiaContratto.add(lblAttention11);
            add(panelTipologiaContratto);

            panelProvvigione = new JPanel();     //Pannello Provvigione
            panelProvvigione.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblProvvigione = new JLabel("Provvigione(%): ");
            panelProvvigione.add(lblProvvigione);
            fldProvvigione = new JTextField(10);
            fldProvvigione.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))  || fldProvvigione.getText().length()>=10) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelProvvigione.add(fldProvvigione);
            lblInfoProvvigione = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoProvvigione.setToolTipText("Massimo 10 caratteri. Inserire solo cifre (non inserire il carattere %). Indicare la percentuale di provvigione spettante allo Studio.  Per esempio: 4.");
            panelProvvigione.add(lblInfoProvvigione);
            panelProvvigione.add(lblAttention8);
            add(panelProvvigione);

            panelPrezzo = new JPanel();     //Pannello Prezzo
            panelPrezzo.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblPrezzo = new JLabel("Prezzo(€): ");
            panelPrezzo.add(lblPrezzo);
            fldPrezzo = new JTextField(5);
            fldPrezzo.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || fldPrezzo.getText().length()>=15) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelPrezzo.add(fldPrezzo);
            lblInfoPrezzo = new JLabel(infoIcon, SwingConstants.RIGHT);
            lblInfoPrezzo.setToolTipText("Massimo 15 caratteri. Inserire solo cifre, non inserire il carattere € o separatori numerari. Indicare il prezzo (che sia di affitto o di vendita) dell'immobile.  Per esempio: 105000.");
            panelPrezzo.add(lblInfoPrezzo);
            panelPrezzo.add(lblAttention9);
            add(panelPrezzo);

            /*panelSpeseCondominiali = new JPanel();     //Pannello Spese condominiali
            panelSpeseCondominiali.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblSpeseCondominiali = new JLabel("Spese condominiali(\u0080): ");
            panelSpeseCondominiali.add(lblSpeseCondominiali);
            fldSpeseCondominiali = new JTextField(5);
            fldSpeseCondominiali.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }
            });
            panelSpeseCondominiali.add(fldSpeseCondominiali);
            lblInfoSpeseCondominiali = new JLabel(infoIcon);
            lblInfoSpeseCondominiali.setToolTipText("Solo numeri, non inserire il carattere \u0080 o separatori numerari. Indicare l'importo annuo delle spese condominiali.  Per esempio: 450.");
            panelSpeseCondominiali.add(lblInfoSpeseCondominiali);
            add(panelSpeseCondominiali);*/
        }

    }   // fine pannello dati contratto


    
    
    
    //sottopannello del pannello centrale per i comandi principali dell'applicazione
    class PanelComandi extends JPanel {
		private static final long serialVersionUID = 1L;
			private JButton btnProcedi, btnResetta, btnChiudi;
            private ImageIcon icoProcedi, icoResetta, icoChiudi;

            public PanelComandi() {
                setBorder(new TitledBorder(" - 6 -  Comandi "));
                setLayout(new FlowLayout(FlowLayout.CENTER));

                //icone dei pulsanti
                icoProcedi = new ImageIcon("./img/forward.png");
                icoResetta = new ImageIcon("./img/refresh.png");
                icoChiudi = new ImageIcon("./img/exit.png");
                
                //Pulsante Riempi form, pulsante per il debug: visibile solo in fase di sviluppo
                /*JButton btnRiempiTest = new JButton("Riempi campi");
                btnRiempiTest.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.setText("001");
                       Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.setText("Casetta in Canadà");

                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.setText("Piccola casetta in Canada  con tante finestrelle e fiori di lillà.");
                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.setText("45");
                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut nec metus ante, ut facilisis neque. Phasellus facilisis turpis quis nibh convallis id semper lacus semper. Nam semper augue non turpis pharetra pulvinar. Donec placerat nulla quis quam luctus volutpat. Nunc elementum sapien vitae ipsum auctor congue nec eget felis. Suspendisse potenti. Aenean bibendum, quam eu pulvinar faucibus, massa risus fermentum ipsum, non gravida risus lectus et metus. Nulla facilisi. Pellentesque lorem risus, suscipit at interdum at, lacinia eget nibh. Phasellus aliquet blandit elit, et pharetra ligula bibendum at. Vivamus convallis lorem eu orci commodo consequat in vitae eros. Proin sit amet justo tortor. Quisque pellentesque, ante sit amet aliquam vestibulum, tortor erat blandit eros, eget cursus nibh leo nec massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ");
                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.setSelectedIndex(2);
                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.setSelectedIndex(2);
                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaContratto.setSelectedIndex(1);

                       Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.setText("105000");
                       Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.setText("20");

                       Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldCap.setText("33100");
                       Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.setSelectedIndex(2);
                       Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.setSelectedIndex(2);
                       Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldVia.setText("via Buttrio");                   
                   }
                });
                btnRiempiTest.setEnabled(true);
                add(btnRiempiTest);*/


                //Pulsante AGGIUNGI - aggiunge la scheda immobile appena compilata nel pannello centrale
                btnProcedi = new JButton("AGGIUNGI", icoProcedi);
                btnProcedi.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {

                       System.out.println("Premuto AGGIUNGI");	//Debug

                       if(isFormValid()) { //Verifica la presenza di dati nei campi form obbligatori e attiva i pulsanti di inserimento
                    	   
                    	   //Disabilito i campi della form
                    	   disabilitaCampiForm();
                    	   
                    	   //Viene creata l'oggetto scheda immobile utilizzando i dati presenti al momento nella form
                    	   SchedaImmobile nuovaSchedaImmobile = new SchedaImmobile(); 
                    	   
                    	   //Il nuovo oggetto scheda immobile viene inserito nella struttura dati e salvato nel file .dat
                    	   Main.imaginationGUI.pannelloSchedeImmobili.listSchedeImmobile.add(nuovaSchedaImmobile);
                    	   try {
                    		   File file = new File(datFilePath);
                    	    	if(file.exists()) {
                    	    		System.out.println("File .dat trovato.");
                    	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
        							outputFile.writeObject(Main.imaginationGUI.pannelloSchedeImmobili.listSchedeImmobile);
        							outputFile.close();
                    	    	}
                    	    	else {
									FileOutputStream newFile = new FileOutputStream(datFilePath);
									ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
        							outputFile.writeObject(Main.imaginationGUI.pannelloSchedeImmobili.listSchedeImmobile);
        							outputFile.close();
									System.out.println("File .dat non trovato. Creazione del file...: " + newFile.toString());
                    	    	}
							} catch (FileNotFoundException e0) {
					            JOptionPane.showMessageDialog(null, "File .dat non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
					            e0.printStackTrace();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "Impossibile accedere al file .dat: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
                    	   
                    	   //Il pannello centrale viene ridisegnato
                    	   Main.imaginationGUI.pannelloSchedeImmobili.updatePanello();
                    	   
                    	   //Il pannello di destra viene ridisegnato
                    	   Main.imaginationGUI.pannelloInserimento.updatePanello();
                       }
                       else {
                    	   //Stampo a video un messaggio informativo
                           JOptionPane.showMessageDialog(null, "Alcuni campi obbligatori non sono stati compilati.");
                       }
         
                   }
                });
                add(btnProcedi);



                //add(new JLabel("     "));



                //Pulsante RESETTA
                btnResetta = new JButton("RESETTA", icoResetta);
                btnResetta.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       System.out.println("Premuto RESETTA");
                       abilitaCampiForm();
                       resettaCampiForm();    
                       Main.imaginationGUI.pannelloSchedeImmobili.updatePanello();
                   }                   
                });
                add(btnResetta);



                add(new JLabel("   "));



                //Pulsante CHIUDI APPLICAZIONE
                btnChiudi = new JButton("CHIUDI", icoChiudi);
                btnChiudi.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       System.exit(0);
                   }
                }
                );
                add(btnChiudi);



            }   //Fine costruttore PanelComandi
            
    } //Fine pannello comandi
    

    
    
    
    //Metodo per disabilitare tutti i campi della form dopo averla compilata e premuto "Procedi"
	static void disabilitaCampiForm() {
	    
		Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.setEnabled(false);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxComune.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldCap.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldVia.setEnabled(false);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroCamere.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldAnnoCostruzione.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldPiano.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuPiani.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxStatoImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxArredamentiImmobile.setEnabled(false);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxRiscaldamentoImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxClimaImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCertificazioniImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxGiardinoImmobile.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxParcheggioImmobile.setEnabled(false);
	    
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto1.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto2.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto3.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto4.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto5.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto6.setEnabled(false);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.cmboxTipologiaContratto.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.setEnabled(false);
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.setEnabled(false);
	
	}   //Fine metodo disabilitaCampiForm

	
	
	
	
	// Metodo per abilitare tutti i campi della form dopo aver premuto "Resetta"
	static void abilitaCampiForm() {
		
		Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.setEnabled(true);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxComune.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldCap.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldVia.setEnabled(true);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaImmobile.setEnabled(true);	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroCamere.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldAnnoCostruzione.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldPiano.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuPiani.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxStatoImmobile.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxArredamentiImmobile.setEnabled(true);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxRiscaldamentoImmobile.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxClimaImmobile.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCertificazioniImmobile.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxGiardinoImmobile.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxParcheggioImmobile.setEnabled(true);
	    
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto1.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto2.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto3.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto4.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto5.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto6.setEnabled(true);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.cmboxTipologiaContratto.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.setEnabled(true);
	    
	}   //Fine metodo abilitaCampiForm

	
	
	
	
	// Metodo per resettare tutti i campi della form dopo averla compilata e premuto "Resetta"
	static void resettaCampiForm() {
		
		Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.setText("");
	    
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldCap.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldVia.setText("");
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.setSelectedIndex(0);	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroCamere.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.setText("");
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldAnnoCostruzione.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldPiano.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuPiani.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxStatoImmobile.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxArredamentiImmobile.setSelectedIndex(0);
	    
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxRiscaldamentoImmobile.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxClimaImmobile.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCertificazioniImmobile.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxGiardinoImmobile.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxParcheggioImmobile.setSelectedIndex(0);
	    
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto1.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto2.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto3.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto4.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto5.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.btnFoto6.setEnabled(true);
	    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto1.setText("");
	    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto2.setText("");
	    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto3.setText("");
	    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto4.setText("");
	    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto5.setText("");
	    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto6.setText("");
	    Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine1 = null;
	    Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine2 = null;
	    Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine3 = null;
	    Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine4 = null;
	    Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine5 = null;
	    Main.imaginationGUI.pannelloForm.panelImmagini.fileImmagine6 = null;
	    
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.cmboxTipologiaContratto.setSelectedIndex(0);
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.setText("");
	    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.setText("");

	}   //Fine metodo resettaCampiForm

	
	
	
	
	//Codice per verificare se tutti i campi obbligatori sono stati compilati
	static boolean isFormValid() {
	    boolean campiObbligatoriCompilati = true;	    
	
	        //Verifico i campi obbligatori
	       if(Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       }
	       if(Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       } 
	       if(((String)Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.getSelectedItem()).contains("---")) {
	    	   campiObbligatoriCompilati = false;
       		}
	       /*if(((String)Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.getSelectedItem()).contains("---")) {
	    	   campiObbligatoriCompilati = false;
       		}*/
	       if(Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       }           
	       if(Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       }
	       if(((String)Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.getSelectedItem()).contains("---")) {
           campiObbligatoriCompilati = false;
       		}
	       if(Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       }        
	       if(Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       }
	       if(Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.getText().equalsIgnoreCase("")) {
	           campiObbligatoriCompilati = false;
	       }
	       
	       //Se tutti i campi obbligatori hanno dati validi
	       if(campiObbligatoriCompilati) {  	
	           return true;
	       }
	       else {               	           
	           return false;
	       }
	}   //Fine metodo isFormValid
	

	
	// Metodo per ricompilare i dati della form con quelli presenti in una scheda data
	public void mostraSchedaSalvata(SchedaImmobile scheda) {
			
			resettaCampiForm();
			
		    Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldCodice.setText(scheda.codice);
		    Main.imaginationGUI.pannelloForm.panelDatiAnnuncio.fldTitolo.setText(scheda.titolo);
		
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldDescrizioneSintetica.setText(scheda.descrSintetica);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuperficieAbitazione.setText(Integer.toString(scheda.supAbitazione));
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.txtareaDescrizioneGenerale.setText(scheda.descrEstesa);
		
		    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldPrezzo.setText(Integer.toString(scheda.prezzo));
		    Main.imaginationGUI.pannelloForm.panelDatiContratto.fldProvvigione.setText(scheda.provvigione);
		
		    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldCap.setText(scheda.cap);
		    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.fldVia.setText(scheda.via);
		
		    if(scheda.immagine1!=null) {
		    	Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto1.setText(scheda.immagine1.getPath());
		    }
		    if(scheda.immagine2!=null) {
			    Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto2.setText(scheda.immagine2.getPath());
		    }
			if(scheda.immagine3!=null) {
				Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto3.setText(scheda.immagine3.getPath());
			}
			if(scheda.immagine4!=null) {
				Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto4.setText(scheda.immagine4.getPath());
			}
			if(scheda.immagine5!=null) {
				Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto5.setText(scheda.immagine5.getPath());
			}
			if(scheda.immagine6!=null) {
				Main.imaginationGUI.pannelloForm.panelImmagini.fldFoto6.setText(scheda.immagine6.getPath());
			}

			Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxRegione.setSelectedIndex(scheda.regioneIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxProvincia.setSelectedIndex(scheda.provinciaIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiPosizioneImmobile.cmboxComune.setSelectedIndex(scheda.comuneIndex);

		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCategoriaImmobile.setSelectedIndex(scheda.categoriaImmobileIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaImmobile.setSelectedIndex(scheda.tipologiaImmobileIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxTipologiaCatalogazione.setSelectedIndex(scheda.tipologiaCatalogazioneIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroBagni.setSelectedIndex(scheda.nroBagniIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxNroCamere.setSelectedIndex(scheda.nroCamereIndex);
		    
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldAnnoCostruzione.setText(scheda.annoCostr);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldPiano.setText(scheda.piano);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.fldSuPiani.setText(scheda.totPiani);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxStatoImmobile.setSelectedIndex(scheda.statoImmobileIndex);
		    
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxRiscaldamentoImmobile.setSelectedIndex(scheda.riscaldamentoIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxClimaImmobile.setSelectedIndex(scheda.climaIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxCertificazioniImmobile.setSelectedIndex(scheda.certificazioniIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxGiardinoImmobile.setSelectedIndex(scheda.giardinoIndex);
		    Main.imaginationGUI.pannelloForm.panelDatiDescrizioneImmobile.cmboxParcheggioImmobile.setSelectedIndex(scheda.parcheggioIndex);
		    
		    Main.imaginationGUI.pannelloForm.panelDatiContratto.cmboxTipologiaContratto.setSelectedIndex(scheda.tipologiaContrattoIndex);

		    disabilitaCampiForm();
		    
		    
		}   //Fine metodo mostraSchedaSalvata
	


	
}   //Fine Pannello Form