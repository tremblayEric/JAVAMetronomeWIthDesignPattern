/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package lectureEcriture;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DonneeDAO implements DAO<Donnee> {
    
    ObjectInputStream ois;
    ObjectOutputStream oos;
    String chemin = "donnee.txt";
    Donnee donnee;
    
    /*
     * Sauvegarde la mesure et le tempo dans la base de donnees.
     */
    @Override
    public void sauvegarder(Donnee donnee) {
        try {        
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(
                    new File(chemin))));
            
            oos.writeObject(donnee);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(DonneeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
        
    }

    
    /*
     * Charge la mesure et le tempo via la base de donnees.
     */
    @Override
    public Donnee charger() {
       
        try {
            ois = new ObjectInputStream(
                        new BufferedInputStream(
                        new FileInputStream(
                        new File(chemin))));
            try {
                donnee = ((Donnee)ois.readObject());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DonneeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                ois.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(DonneeDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            donnee = new Donnee();
        }
        return donnee;
    }
    
}
