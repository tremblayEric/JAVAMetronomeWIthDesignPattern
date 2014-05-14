/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package coucheControle;
/*
 * Interface Observateur récupère l'information venant
 * d'un sujet et traite cette information selon 
 * l'implementation de sa fonction mise_a_jour
 */
public interface Observateur {

    public void mise_a_jour(Detecteur s);
}
