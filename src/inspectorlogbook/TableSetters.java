/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 19950014
 */
public class TableSetters {
    
    public TableModel LogbookTable(TableModel model){
        JTable table = new JTable();
        table.setModel(model);
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        table.setModel(tm);
        //TableColumn column;

         String sql = "SELECT n.projid, n.id, n.groupid, i.idnumber, n.inspid,  n.petsa, n.control_no, "
                    + "g.grpname, (i.lname||\", \"||i.fname||\"  \"||i.mname) AS NAGSIYASAT, (p.proj_name||\", \"||p.proj_location) AS PROJECT, n.nagpatotoo, "
                    + "n.current_work, n.mpr_count, n.nagugol, n.kulangnaplano, n.kulangnamateryales, "
                    + "n.percentcompletion, n.puna  FROM  tb_group g, tb_inspector i, tb_project p, tb_nasiyasat n "
                    + " WHERE i.inspid = n.inspid AND g.gid = n.groupid AND p.projid = n.projid ORDER BY n.petsa ASC";

        Connection con = ConnectDB();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            ps = con.prepareStatement(sql);
            //ps.setInt(1, c_id);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Object[] rows = new Object[19]; 
                
                for(int i = 0; i < 19; i++){
                  
                    if(i<5){
                        rows[i]=rs.getObject(i+1).toString().trim();
                    }else if(i==5){
                        rows[5]=false;
                        //i++;
                    }else if(i>5){
                        
                        rows[i]=rs.getObject(i).toString().trim();
                        //System.out.print(i+"\n");
                    }
                }
                int y=0;
                ((DefaultTableModel)table.getModel()).insertRow(y, rows);
                y++;
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "checkersProjectsTable :: "+e);
        }finally{
            try{
               rs.close();
               ps.close();
               con.close();
            }catch(Exception e){}
        }
       
        return table.getModel();
    }
    public TableModel searchTable(TableModel model,String searchWord){
        JTable table = new JTable();
        table.setModel(model);
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        table.setModel(tm);
        //TableColumn column;

         String sql = "SELECT n.projid, n.id, n.groupid, i.idnumber, n.inspid,  n.petsa, n.control_no, "
                    + "g.grpname, (i.lname||\", \"||i.fname||\"  \"||i.mname) AS NAGSIYASAT, (p.proj_name||\", \"||p.proj_location) AS PROJECT, n.nagpatotoo, "
                    + "n.current_work, n.mpr_count, n.nagugol, n.kulangnaplano, n.kulangnamateryales, "
                    + "n.percentcompletion, n.puna  FROM  tb_group g, tb_inspector i, tb_project p, tb_nasiyasat n "
                    + " WHERE i.inspid = n.inspid AND g.gid = n.groupid AND p.projid = n.projid AND (i.idnumber||n.petsa||n.control_no||"
                 + "g.grpname||NAGSIYASAT||PROJECT||n.nagpatotoo||n.current_work||n.kulangnaplano||n.kulangnamateryales||n.puna) LIKE ? ORDER BY n.petsa ASC";

        Connection con = ConnectDB();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+searchWord+"%");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Object[] rows = new Object[19]; 
                
                for(int i = 0; i < 19; i++){
                  
                    if(i<5){
                        rows[i]=rs.getObject(i+1).toString().trim();
                    }else if(i==5){
                        rows[5]=false;
                        //i++;
                    }else if(i>5){
                        
                        rows[i]=rs.getObject(i).toString().trim();
                        //System.out.print(i+"\n");
                    }
                }
                int y=0;
                ((DefaultTableModel)table.getModel()).insertRow(y, rows);
                y++;
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "checkersProjectsTable :: "+e);
        }finally{
            try{
               rs.close();
               ps.close();
               con.close();
            }catch(Exception e){}
        }
       
        return table.getModel();
    }
        public Connection ConnectDB() {
            Connection con = null;
		try {
			
                        Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:./db/insplbdb.sqlite");
		
                }catch(ClassNotFoundException | SQLException e){
				JOptionPane.showMessageDialog(null, "Error in getConnection: "+e);
		}			
            return con;
}
}
