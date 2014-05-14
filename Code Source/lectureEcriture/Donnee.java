/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package lectureEcriture;

import java.io.Serializable;

/*
 * Objet comportant le tempo et la mesure et etant utilise
 * pour la sauvegarde via la serialisation.
 */
public class Donnee implements Serializable {
    
    int tempo = 100;
    int mesure = 3;
    
    public Donnee(){
        
    }
    
    public int getTempo(){
        
        return tempo;
    }
    
    public void setTempo(int _tempo){
        
        this.tempo = _tempo;
    }
    
    public int getMesure(){
        
        return mesure;
    }
    
    public void setMesure(int _mesure){
        
        this.mesure = _mesure;
    }
    
    public String toString(){
        
        return "Mesure: "+this.mesure+"\n"
                +"Tempo:"+this.tempo+"\n";
    }
}
