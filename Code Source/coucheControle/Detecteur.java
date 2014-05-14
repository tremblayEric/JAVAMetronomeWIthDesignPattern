/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package coucheControle;

import java.util.ArrayList;
import java.util.Iterator;
import sortie.InterfaceUtilisateur;

/*
 * La classe détecteur permet de verifier a temps regulier tout
 * changement d'etat des boutons ou molette sur l'Interface Graphique
 * 
 * Elle est un singleton et un sujet pour le controleur qui l'observe
 * 
 */

public final class Detecteur extends Thread implements Sujet {

    private ArrayList<Observateur> listesObservateur = new ArrayList();
    private boolean boutonPressePlusMesure = false;
    private boolean boutonPresseMoinsMesure = false;
    private boolean boutonPresseDemarrer = false;
    private boolean boutonPresseArreter = false;
    private boolean boutonFermerLogiciel = false;
    private int boutonMolette = 0;
    private InterfaceUtilisateur iu;

    private static volatile Detecteur instanceDetecteur = null;             //Singleton
    
    
    private Detecteur(InterfaceUtilisateur iu) {                            //Constructeur prive de singleton
        this.iu = iu;
    }

    
    public static Detecteur getInstance(InterfaceUtilisateur iu) {    //Methode getInstance de singleton
   
         if (instanceDetecteur == null) {
                 instanceDetecteur = new Detecteur(iu);
         }
         return instanceDetecteur;
         
    }
    
    
    
    @Override
    public void attacher(Observateur o) {
        listesObservateur.add(o);
    }

    @Override
    public void dettacher(Observateur o) {
        listesObservateur.remove(o);
    }
/*
 * Fonction qui permet de signaler au controleur de tous changement d'état
 * dans la liste d'observateurs
 * 
 */
    public void notifier(Detecteur s) {

        Iterator<Observateur> iterateur = listesObservateur.iterator();

        while (iterateur.hasNext()) {
            iterateur.next().mise_a_jour(s);
        }
        
        boutonPressePlusMesure = false;
        boutonPresseMoinsMesure = false;
        boutonPresseDemarrer = false;
        boutonPresseArreter = false;
        boutonFermerLogiciel = false;

    }
/*
 * Fonction principal du Détecteur avec sa boucle infinie à temps déterminé
 * qui vérifie les états des boutons et la molette sur l'interface
 * 
 */
    public void run() {

        Boolean notifier = false;

        while (true) {
            
            if (boutonPresseDemarrer != iu.getEtatDemarrer()) {

                boutonPresseDemarrer = iu.getEtatDemarrer();
                notifier = true;

            }

            if (boutonPresseArreter != iu.getEtatArreter()) {

                boutonPresseArreter = iu.getEtatArreter();
                notifier = true;
            }



            if (boutonPressePlusMesure != iu.getEtatPlus()) {

                boutonPressePlusMesure = iu.getEtatPlus();
                notifier = true;
            }


            if (boutonPresseMoinsMesure != iu.getEtatMoins()) {

                boutonPresseMoinsMesure = iu.getEtatMoins();
                notifier = true;
            }


            if (boutonMolette != iu.getMolette()) {

                boutonMolette = iu.getMolette();
                notifier = true;

            }
            
            
            if (boutonFermerLogiciel != iu.getEtatBoutonFermerLogiciel()){

                boutonFermerLogiciel = iu.getEtatBoutonFermerLogiciel();
                notifier = true;

            }
            

            if (notifier == true) {

                notifier = false;
                notifier(this);     //Notifie les observateurs du changement de l'un de ses etats

            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
       
        }

    }

    public boolean getBoutonPresseDemarrer() {
        return boutonPresseDemarrer;
    }

    public boolean getBoutonPresseArreter() {
        return boutonPresseArreter;
    }

    public int getTempo() {
        return boutonMolette;
    }

    public boolean getBoutonPressePlusMesure() {
        return boutonPressePlusMesure;
    }

    public boolean getBoutonPresseMoinsMesure() {
        return boutonPresseMoinsMesure;
    }
    
    public boolean getBoutonFermerLogiciel(){
        return boutonFermerLogiciel;
    }
    
}
