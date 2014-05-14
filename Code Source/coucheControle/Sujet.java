/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package coucheControle;
/*
 * L'interface sujet a la reseponsabilite de gerer
 * la liste des observateurs en plus de signaler tout changement
 * d'etat a des observateurs.
 */
public interface Sujet {

    public void attacher(Observateur o);

    public void dettacher(Observateur o);

    public void notifier(Detecteur s);
}
