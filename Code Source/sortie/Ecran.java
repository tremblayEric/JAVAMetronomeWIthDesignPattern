/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie;

public class Ecran {

    private int _tempo;
    private int _mesure;
    private InterfaceUtilisateur _iu;

    public Ecran(InterfaceUtilisateur unUi, int tempo, int mesure) {

        _iu = unUi;
        _tempo = tempo;
        _mesure = mesure;

    }

    public void afficher() {
        _iu.afficherEcran(_tempo, _mesure);
    }
    
    public void modifierTempo(int tempo){
        _tempo = tempo;
    }
    
    public void modifierMesure(int mesure){
        _mesure = mesure;
    } 
    
}
