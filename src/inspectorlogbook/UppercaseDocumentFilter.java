/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author 19950014
 */
    public class UppercaseDocumentFilter extends DocumentFilter{
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) 
                throws BadLocationException{ fb.insertString(offset, text.toUpperCase(), attr);
        }
    
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException{ fb.replace(offset, length, text.toUpperCase(), attrs);
                    
        }
        
    }
