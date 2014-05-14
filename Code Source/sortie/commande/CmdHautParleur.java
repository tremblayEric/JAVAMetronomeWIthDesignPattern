/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie.commande;

import sortie.HautParleur;

public class CmdHautParleur implements Commande {

    private HautParleur _hautParleur;

    public CmdHautParleur(HautParleur hautParleur) {
        _hautParleur = hautParleur;
    }

    @Override
    public void execute() {
        _hautParleur.emettreSon();
    }
}
