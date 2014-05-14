/*
 Conçu par:
 Jean-François Èlie
 Ricardo Solon
 Eric Tremblay
 */
package sortie;

public class LedMesure implements Led{
    
    private InterfaceUtilisateur _iu;
    
    public LedMesure(InterfaceUtilisateur unUi){
        _iu = unUi;
    }

    @Override
    public void allumer(){
         _iu.allumerLedMesure();
    } 
    
    
    @Override
    public void eteindre(){
         _iu.eteindreLedMesure();
    }    
    
}
