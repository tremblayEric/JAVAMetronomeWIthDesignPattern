/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package coucheControle;

import java.util.ArrayList;
import java.util.Iterator;
import sortie.commande.Commande;

/*
 * La classe gestionAffichage permet d'ajouter les commandes 
 * a executer dans une liste de commandes
 * 
 * Finalement cette classe permet d'executer les actions 
 * des differentes commandes present dans la listes des commandes
 * 
 */

public class GestionAffichage extends Thread {
    
    
    private ArrayList<Commande> listeCommande = new ArrayList();
    private ArrayList<Commande> listeCommandeLedAllumer = new ArrayList();
    private ArrayList<Commande> listeCommandeLedEteindre = new ArrayList();
    private int _vitesse = 0;
    private int _mesure = 0;
    private int compteurSauts = 0;
    private boolean afficheDeBase;
    
    GestionAffichage(int vitesse, int mesure, boolean typeAffichage){
        
        this._vitesse = vitesse;
        this._mesure = mesure;
        this.afficheDeBase = typeAffichage;
        
    }
    
    public void ajouterCommandeLedAllumer(Commande e ){
        
        listeCommandeLedAllumer.add(e);
    }
    public void ajouterCommandeLedEteindre(Commande e ){
        
        listeCommandeLedEteindre.add(e);
    }
    
    public void ajouterCommande(Commande e){
        
        listeCommande.add(e);
    }
    
    public void modifierTemps(int nouvelleVitesse, int mesure){
        this._vitesse = nouvelleVitesse;
        this._mesure = mesure;
    }
    /*
     * Fonction qui permet d'executer les comportements 
     * propre à chaque commande dans la liste des commandes 
     */
    @Override
    public void run() {
          
        try {
            
            while(true){
                
                listeCommande.get(0).execute(); //Ecran
                
                if(compteurSauts == 0){
                    
                    if(!afficheDeBase){
                        listeCommandeLedAllumer.get(0).execute(); //Tempo
                        listeCommandeLedAllumer.get(1).execute(); //Mesure
                        listeCommande.get(1).execute();  //Haut-Parleur
                        Thread.sleep(35);                        
                    }
  
                    listeCommandeLedEteindre.get(0).execute();
                    listeCommandeLedEteindre.get(1).execute();
                    compteurSauts = _mesure -1;               
                }else{
                   
                    if(!afficheDeBase){  
                        listeCommandeLedAllumer.get(1).execute();
                        Thread.sleep(35);  
                    }
                    
                    listeCommandeLedEteindre.get(1).execute();
                    compteurSauts--;
                }
                
                Thread.sleep(_vitesse - 35);
            }
            
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

    }

}
