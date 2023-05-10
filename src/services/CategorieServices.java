/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Categorie;
import entities.Produit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;
/**
 *
 * @author ncib
 */
public class CategorieServices {
    
    //singleton 
    public static ProduitServices instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    public static ProduitServices getInstance() {
        if(instance == null )
            instance = new ProduitServices();
        return instance ;
    }
    
    
    
    public CategorieServices() {
        req = new ConnectionRequest();
        
    }
    
     //ajout 
     public void ajouterCategorie(Categorie c){
        String url =Statics.Base_URL+ "addcategoriestJSON/new?libelle="+c.getLibelle()+"&quantite="+c.getQuantite() ;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
            System.out.println("data =="+str);
           
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        }
    
    
    
    
    
     //affichage
    
   
 public ArrayList<Categorie>affichiercategorie() {
        ArrayList<Categorie> result = new ArrayList<>();
        String url = Statics.Base_URL+"AllCategories";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
        @Override
    public void actionPerformed(NetworkEvent evt) {
    JSONParser jsonp;
    jsonp = new JSONParser();
    
    try {
        Map<String,Object> mapCategories = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
        
        
        List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCategories.get("root");
    for(Map<String, Object> obj :listOfMaps){
        Categorie c = new Categorie();
        
        float id = Float.parseFloat(obj.get("id").toString());
        String Libelle = obj.get("libelle").toString();
        String Quantite = obj.get("quantite").toString();
        c.setId((int) id);
        c.setLibelle(Libelle);
        c.setQuantite(0);
        result.add(c);
    }
    }catch (Exception ex){
        ex.printStackTrace();
    }
        
    }
    
    
});
        NetworkManager.getInstance().addToQueueAndWait(req);

return result;
}

    public boolean modifierCategorie(Categorie c) {
        String url = Statics.BASE_URL +"/updateCategorie?id="+c.getId()+"&libelle="+c.getLibelle()+"&quantite="+c.getQuantite(); 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
      //Delete 
    public boolean deleteCategorie(int id ) {
        String url = Statics.BASE_URL +"/deleteStudentJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    } 
    

    
    
}
