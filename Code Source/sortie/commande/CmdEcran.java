/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie.commande;

import sortie.Ecran;


public class CmdEcran implements Commande {

    private Ecran _ecran;

    public CmdEcran(Ecran ecran) {
        _ecran = ecran;
    }

    @Override
    public void execute() {
        _ecran.afficher();

    }
}
