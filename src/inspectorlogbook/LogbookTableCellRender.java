/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author 19950014
 */
public class LogbookTableCellRender extends DefaultTableCellRenderer{
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, 
                                                   boolean hasFocus, int row, int column){
        
        Component c = super.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, column);
               
        
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                    if(column==13){
                         
                    }
         /*   SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            String tentaFin = table.getValueAt(row, 10).toString();
            String finalFin = table.getValueAt(row, 11).toString();
            
            LocalDateTime now = LocalDateTime.now();
            String currDate =   now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth();
           
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            
            int numDaysToFinish=0;
            try{
                /////This is to count projects due numDaysToFinish////////////////////////////
                Date tentativeDate = myFormat.parse(tentaFin);
                Date actualDate = myFormat.parse(currDate);
                long daysToTentativeFinish = actualDate.getTime()-tentativeDate.getTime();
                numDaysToFinish = (int)(TimeUnit.DAYS.convert(daysToTentativeFinish, TimeUnit.MILLISECONDS));
               
            }catch(ParseException e){
            }
            
            if(column==8){

                      if(finalFin.length() <= 0){
                          if((numDaysToFinish < 0) && (numDaysToFinish < -20 )){
                              c.setForeground(new java.awt.Color(0,128,0));//(47,255,47));
                          }else if((numDaysToFinish >= -20) && (numDaysToFinish < 0 )){
                              c.setForeground(new java.awt.Color(255,255,128));
                          }else if(numDaysToFinish >= 0){
                              c.setForeground(Color.red);
                          }
                      }else{
                          c.setForeground(Color.BLACK);
                      }
            }
            */

        return c;
    }
    
}
