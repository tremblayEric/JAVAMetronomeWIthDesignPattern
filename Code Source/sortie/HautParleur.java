/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie;

public class HautParleur {

    private InterfaceUtilisateur _iu;

    public HautParleur(InterfaceUtilisateur ui) {
        _iu = ui;
    }

    public void emettreSon() {
        _iu.emettreSon();
    }
}
