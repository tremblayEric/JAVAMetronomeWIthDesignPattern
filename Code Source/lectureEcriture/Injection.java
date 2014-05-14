/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package lectureEcriture;

import coucheControle.Controleur;

/*
 * Instantie le bon DAO et le retourne au controleur.
 * donc enleve le couplage entre le controleur et la
 * base de donnees.
 */
public class Injection extends DonneeDAO {

    private DAO<Donnee> leDAO;

    public Injection(Controleur c) {
        
        leDAO = new DonneeDAO();
        c.setDAO(leDAO);
        
    }
    
}
