/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

/**
 *
 * @author 19950014
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author 19950014
 */
    public class CboNumberKeyListener implements KeyListener{
    
    @Override
    public void keyPressed(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {
           
          char caracter = ke.getKeyChar();
          if (((caracter < '0') || (caracter > '9'))&& (caracter != '\b')){
                                    ke.consume();
          }
          
           //try{
           // if(Character.isLowerCase(ke.getKeyChar())){
               // ke.setKeyChar(Character.toUpperCase(ke.getKeyChar()));
           // }
       
           // }catch(Exception e){}
    }   
    }
