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
public class ProduitServices {
    
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
    
    
    
    public ProduitServices() {
        req = new ConnectionRequest();
        
    }
    
     //ajout 
    public void ajoutProduit(Produit p) {
        
        String url =Statics.BASE_URL+"/ajout_produit_json?nom="+p.getNom()+"&prix="+p.getPrix()+"&stock="+p.getStock(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
     
    
      //Delete 
    public boolean deleteProduit(int id ) {
        String url = Statics.BASE_URL +"/deleteproduit?id="+id;
        
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
    
     //affichage
    
    public ArrayList<Produit>affichageProduit() {
        ArrayList<Produit> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayuserJson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Produit re = new Produit();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("libelle").toString();
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float stock = Float.parseFloat(obj.get("stock").toString());
                       
                
                        //insert data into ArrayList result
                        result.add(re);
                
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    
     
    
    
}
