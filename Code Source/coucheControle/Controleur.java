/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package coucheControle;

import lectureEcriture.DAO;
import lectureEcriture.Donnee;
import lectureEcriture.DonneeDAO;
import lectureEcriture.Injection;
import sortie.Ecran;
import sortie.HautParleur;
import sortie.InterfaceUtilisateur;
import sortie.Led;
import sortie.LedMesure;
import sortie.LedTempo;
import sortie.commande.*;


/*
 * La classe Controleur est un singleton et un observateur du sujet classe Detecteur
 * elle attends de recevoir toute information venant de la classe Detecteur le traite
 * et l'envoie a la GestionAffichage
 */
public final class Controleur implements Observateur {

    private static volatile Controleur instanceControleur = null;             //Singleton      
    private InterfaceUtilisateur iu;
    private GestionAffichage ga;
    private int tempo;
    private int mesure;
    private Ecran ecran;
    private Led ledTempo;
    private Led ledMesure;
    private HautParleur hp;
    private CmdEcran cmdE;
    private CmdHautParleur chp;
    private CmdLedAllumer cTempoLedAllumee;
    private CmdLedEteindre cTempoLedEteindre;
    private CmdLedAllumer cMesureLedAllumer;
    private CmdLedEteindre cMesureLedEteindre;
    private DAO leDAO;
    private Injection injection;
    private Donnee lesDonnees;

    private Controleur(InterfaceUtilisateur iu) {

        this.iu = iu;
        injection = new Injection(this);
        lesDonnees = (Donnee) leDAO.charger();
        mesure = lesDonnees.getMesure();
        tempo = lesDonnees.getTempo();
        ecran = new Ecran(iu, tempo, mesure);
        ledTempo = new LedTempo(iu);
        ledMesure = new LedMesure(iu);
        hp = new HautParleur(iu);
        cmdE = new CmdEcran(ecran);
        chp = new CmdHautParleur(hp);
        cTempoLedAllumee = new CmdLedAllumer(ledTempo);
        cTempoLedEteindre = new CmdLedEteindre(ledTempo);
        cMesureLedAllumer = new CmdLedAllumer(ledMesure);
        cMesureLedEteindre = new CmdLedEteindre(ledMesure);

        demarrerAffichageDeBase();

    }

    
    /*
     * L'injection du DAO dans le controleur via
     * l'injecteur.
     */
    public void setDAO(DAO unDAO) {
        leDAO = unDAO;
    }

    
    /*
     *  Methode getInstance de singleton
     */
    public static Controleur getInstance(InterfaceUtilisateur iu) {

        if (instanceControleur == null) {
            instanceControleur = new Controleur(iu);
        }
        return instanceControleur;

    }
    

    /*
     * Calcule a quelle vitesse le thread d'affichage doit
     * raffraichir les informations sur l'interface utilisateur.
     * Correspond a la vitesse du led mesure, qui est l'element
     * qui doit etre raffraichi le plus rapidement.
     */
    private int calculeVitesse() {

        double temp = ((60.0 / (double) tempo) * 1000) / mesure;
        return (int) temp;

    }

    
    /*
     * S'assure que la mesure reste toujours entre 2 et 7.
     */
    private int analyseMesure(Detecteur s) {

        if (s.getBoutonPressePlusMesure() == true && mesure < 7) {
            mesure++;
        } else if (s.getBoutonPresseMoinsMesure() == true && mesure > 2) {
            mesure--;
        }

        return mesure;
    }

    
    /*
     * Fonction permettant de recuperer l'information du detecteur 
     * et mettre a jour l'affichage
     */
    @Override
    public void mise_a_jour(Detecteur s) {

        tempo = s.getTempo();
        mesure = analyseMesure(s);
        lesDonnees.setMesure(mesure);
        lesDonnees.setTempo(tempo);

        if (s.getBoutonFermerLogiciel()) {
            leDAO.sauvegarder(lesDonnees);
            System.exit(0);
        }

        ecran.modifierTempo(tempo);
        ecran.modifierMesure(mesure);

        if (ga != null) {
            ga.modifierTemps(calculeVitesse(), mesure);
        }

        if (s.getBoutonPresseDemarrer()) {

            ga.stop();
            affichageComplet();


        } else if (s.getBoutonPresseArreter()) {

            ga.stop();
            demarrerAffichageDeBase();

        }

    }
   
    
    /*
     * Fonction permettant d'ajouter 
     * toutes les commandes de mise a jour
     * sur l'interface graphique
     */
    private void affichageComplet() {

        ga = new GestionAffichage(calculeVitesse(), mesure, false);
        ga.ajouterCommande(cmdE);
        ga.ajouterCommande(chp);
        ga.ajouterCommandeLedAllumer(cTempoLedAllumee);
        ga.ajouterCommandeLedAllumer(cMesureLedAllumer);
        ga.ajouterCommandeLedEteindre(cTempoLedEteindre);
        ga.ajouterCommandeLedEteindre(cMesureLedEteindre);
        ga.start();

    }

    
    /*
     * Affiche seulement le tempo et la mesure sur l'ecran
     */
    private void demarrerAffichageDeBase() {

        ga = new GestionAffichage(calculeVitesse(), mesure, true);
        ga.ajouterCommande(cmdE);
        ga.ajouterCommandeLedEteindre(cTempoLedEteindre);
        ga.ajouterCommandeLedEteindre(cMesureLedEteindre);
        ga.start();

    }
}
