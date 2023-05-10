/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Categorie;
import entities.Produit;


import java.text.SimpleDateFormat;
import java.util.Date;
import services.ProduitServices;

/**
 *
 * @author ncib
 */
public class AjoutProduitForm extends BaseForm {
    
    Form current;
    public AjoutProduitForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout User");
        getContentPane().setScrollVisible(false);
        
        
      
        TextField nom = new TextField("", "entrer Nom");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);

  

    TextField prix = new TextField("", "entrer le prix");
    prix.setUIID("TextFieldBlack");
addStringValue("Prix", prix);

TextField stock = new TextField("", "entrer le stock");
stock.setUIID("TextFieldBlack");
addStringValue("Stock", stock);

TextField image = new TextField("", "entrer l'image");
image.setUIID("TextFieldBlack");
addStringValue("Image", image);

     
       
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((ActionEvent e) -> {
            
            
            try {
                
                if(nom.getText().equals("") || prix.getText().equals("") || stock.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    
                    //njibo iduser men session (current user)
// Assuming that you have created the necessary GUI components for each field:
                     
                     
                 

// Create the new Produit object using the values from the GUI components:
                       
                     Produit p = new Produit(nom.getText(), Integer.parseInt(prix.getText(),Integer.parseInt(stock.getText()));
                     
                    System.out.println("data  produit == "+p);
                    
                    
                 //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    
                    ProduitServices.getInstance().ajoutProduit(p);
                 
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                ;
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }
    
    
      private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addIntegerValue(String numero,Component v) {
        add(BorderLayout.west(new Label(numero,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    
    
    
}
