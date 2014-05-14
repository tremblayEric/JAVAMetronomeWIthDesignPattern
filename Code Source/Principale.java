/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
import coucheControle.Controleur;
import coucheControle.Detecteur;
import sortie.InterfaceGraphique;
import sun.security.jca.GetInstance;

public class Principale {

    public static void main(String[] args) {
        
        
        
        InterfaceGraphique iu = new InterfaceGraphique();
        iu.setTitle("Metronome");
        iu.pack();
        iu.setVisible(true);

        //Deux singletons
        Controleur controleur = Controleur.getInstance(iu);
        Detecteur detecteur = Detecteur.getInstance(iu);

        detecteur.attacher(controleur);
        detecteur.start(); //surveille tout changement dans interfaceGraphique

    }
}
