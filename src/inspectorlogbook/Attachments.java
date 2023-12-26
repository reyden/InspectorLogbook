/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Me
 */
public class Attachments {
    Connection con;
    
    public void addAttachment(String cono, String attach, String filePath){
        String sql = "INSERT INTO tb_attachments(controlno, attachments, filepath) VALUES(?,?,?)";
        PreparedStatement ps=null;
        //ResultSet rs=null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, cono);
            ps.setString(2, attach);
            ps.setString(3, filePath);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "File successfully attached.");
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Error in addAttachment :: "+e); 
        }
                
    }
    public void deleteAttachment(String contl){
        String sql = "DELETE FROM tb_attachments WHERE controlno =?";
        PreparedStatement ps;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, contl);
            ps.executeUpdate();
            
            ps.close();
            con.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Exception in deleteAttachment = " +e);
        }
    }
     public TableModel setTable(TableModel model, String cono){
        JTable table = new JTable();
        table.setModel(model);
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        tm.setRowCount(0);
        table.setModel(tm);
        
        String sql = "Select * FROM tb_attachments WHERE controlno = ?";
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            con=ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, cono);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Object[] rows = new Object[5]; 
                
                for(int i = 0; i < 5; i++){
                  
                    if(i<1){
                        rows[i]=rs.getObject(i+1).toString().trim();
                    }else if(i==1){
                        rows[i]=false;
                        
                    }else if(i>1){
                        
                        rows[i]=rs.getObject(i).toString().trim();
                        //System.out.print(i+"\n");
                    }
                }
                int y=0;
                ((DefaultTableModel)table.getModel()).insertRow(y, rows);
                y++;
            }
         ps.close();
         rs.close();
         con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Exception in setTable :: "+e);
        }
        return table.getModel();
    }
    public int countAttachments(String cono){
        int cnt = 0;
        //JOptionPane.showMessageDialog(null, cono);
        String sql = "SELECT COUNT(attachments) FROM tb_attachments WHERE controlno = ?";
        PreparedStatement ps;
        ResultSet rs;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, cono);
            rs = ps.executeQuery();
            if(rs.next()){
                cnt = rs.getInt(1);
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error in countAttachments :: "+e);
        }
      return cnt;  
    }
    public Connection ConnectDB() {
            Connection con = null;
		try {
			
                        Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:./db/insplbdb.sqlite");
                                                            //"jdbc:sqlite:C:\\PLB\\InspectorLogbook\\db\\insplbdb.sqlite"
                }catch(ClassNotFoundException | SQLException e){
				JOptionPane.showMessageDialog(null, "Error in getConnection: "+e);
		}			
            return con;
}
}
