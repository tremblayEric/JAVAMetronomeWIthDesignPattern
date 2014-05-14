/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie;

public interface InterfaceUtilisateur {

    public void allumerLedTempo();

    public void eteindreLedTempo();
    
    public void allumerLedMesure();

    public void eteindreLedMesure(); 

    public void afficherEcran(int tempo, int mesure);

    public void emettreSon();

    public boolean getEtatDemarrer();

    public boolean getEtatArreter();

    public boolean getEtatPlus();

    public boolean getEtatMoins();

    public int getMolette();
    
    public boolean getEtatBoutonFermerLogiciel();
    
}
