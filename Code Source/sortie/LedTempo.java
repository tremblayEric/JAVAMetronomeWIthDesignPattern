/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie;

public class LedTempo implements Led {
   
    private InterfaceUtilisateur _iu;
    
    
    public LedTempo(InterfaceUtilisateur unUi){
        _iu = unUi;
    }

    
    @Override
    public void allumer(){
         _iu.allumerLedTempo();
    }
	
    
    @Override
    public void eteindre(){
         _iu.eteindreLedTempo();
    }
        
       
}