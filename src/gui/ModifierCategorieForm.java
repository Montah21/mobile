/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Categorie;
import services.CategorieServices;


/**
 *
 * @author ncib
 */
public class ModifierCategorieForm extends BaseForm {
    
    Form current;
    public ModifierCategorieForm(Resources res , Categorie c) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout categorie");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField libelle = new TextField("", "entrer libelle");
        libelle.setUIID("TextFieldBlack");
        addStringValue("libelle",libelle);
        
 
        
        TextField quantite = new TextField("", "entrer quantite");
        quantite.setUIID("TextFieldBlack");
        addIntegerValue("quantite",quantite);
        
        
 
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           c.setLibelle(libelle.getText());
           c.setQuantite(Integer.parseInt(quantite.getText())));
           
          
      
       
       //appel fonction modfier reclamation men service
       
       if(CategorieServices.getInstance().modifierCategorie(c)) { // if true
           new ListCategorieForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCategorieForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(libelle),
                createLineSeparator(),
                new FloatingHint(quantite),
                createLineSeparator(),
               
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
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
