/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie.commande;

import sortie.Led;

public class CmdLedAllumer implements Commande {

    private Led _led;

    public CmdLedAllumer(Led unLed) {
        _led = unLed;
    }

    @Override
    public void execute() {
        _led.allumer();

    }
}
