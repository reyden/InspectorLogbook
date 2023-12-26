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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * @author 19950014
 */
public class Grupo {
    int grpId;
    String grpName;
    Connection con;
    
    public int getGrpId() {
        return grpId;
    }

    public void setGrpId(int grpId) {
        this.grpId = grpId;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }
    public JComboBox grpnameCombo(){
           JComboBox combo = new JComboBox();
           String qry = "SELECT DISTINCT grpname FROM tb_group ORDER BY grpname ASC";
           PreparedStatement ps = null;
           ResultSet rs = null;
           //DataAccessForm df = new DataAccessForm();
           
           try{
               con = ConnectDB();
               ps = con.prepareStatement(qry);
               rs = ps.executeQuery();
               while(rs.next()){
                   combo.addItem(rs.getString(1).toUpperCase());
               }
               rs.close();
               ps.close();
               con.close();
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "grpnameCombo :: "+e);
           }
           
           return combo;
    }
public void findGroup(int id){
        String sql = "SELECT * FROM tb_group WHERE gid = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                setGrpId(rs.getInt(1));
                setGrpName(rs.getString(2));
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Errorn in findGroup :: "+ e);
        }
    }
public boolean groupExists(String gronam){
    
        String sql = "SELECT * FROM tb_group WHERE grpname = ?";
        boolean exists = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, gronam);
            rs = ps.executeQuery();
            if(rs.next()){
                
                setGrpId(rs.getInt(1));
                setGrpName(rs.getString(2));
                
                exists = true;
                //JOptionPane.showMessageDialog(null, "Exists? => "+exists+ "  GROUP NAME => "+rs.getString(2)+"  groupId => "+rs.getInt(1));
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Errorn in isGroupExisting ::"+ e);
        }
        
        return exists;
    }
    public void updateGroup(){
        String sql = "UPDATE tb_group SET grpname = ? WHERE gid = ?";
        PreparedStatement ps = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getGrpName());
            ps.setInt(2, getGrpId());
            ps.executeUpdate();
            
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in updateGroup :: "+e);
        }
    }
    public void addNewGroup(){
        
        if(groupExists(getGrpName())==false){
            
        String sql = "INSERT INTO tb_group(grpname) VALUES(?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getGrpName());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            setGrpId(rs.getInt(1));
            
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in addNewGroup :: "+e);
        }
      }
    }
    
    public Connection ConnectDB() {
            Connection con = null;
		try {
			
                        Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:./db/insplbdb.sqlite");//"jdbc:sqlite:C:\\PLB\\InspectorLogbook\\db\\insplbdb.sqlite"
		
                }catch(ClassNotFoundException | SQLException e){
				JOptionPane.showMessageDialog(null, "Error in getConnection: "+e);
		}			
            return con;
}
}
