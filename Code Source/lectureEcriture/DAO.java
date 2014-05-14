/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package lectureEcriture;

public interface DAO<T> {
    
    
    public void sauvegarder(T element);
    
    public T charger();
    
}
