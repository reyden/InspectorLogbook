/*
 * To change this license header, choose License Headers in Proyekto Properties.
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
public class Proyekto {
    int proid;
    String pronam;
    String proloca;
    Connection con;
    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public String getPronam() {
        return pronam;
    }

    public void setPronam(String pronam) {
        this.pronam = pronam;
    }

    public String getProloca() {
        return proloca;
    }

    public void setProloca(String proloca) {
        this.proloca = proloca;
    }
    public JComboBox localeCombo(){
           JComboBox combo = new JComboBox();
           String qry = "SELECT DISTINCT proj_location FROM tb_project ORDER BY proj_location ASC";
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
               ps.close();
               rs.close();
               con.close();               
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "localeCombo :: "+e);
           }
           
           return combo;
    }
    public JComboBox projectCombo(){
           JComboBox combo = new JComboBox();
           String qry = "SELECT DISTINCT proj_name FROM tb_project ORDER BY proj_name ASC";
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
               
               ps.close();
               rs.close();
               con.close();
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "projectCombo :: "+e);
           }
           
           return combo;
    }
    public boolean projectExists(String proname, String prolocat){
        boolean proExs = false;
        String sql = "SELECT * FROM tb_project WHERE proj_name = ? AND proj_location = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getPronam());
            ps.setString(2, getProloca());
            rs=ps.executeQuery();
            if(rs.next()){
                //JOptionPane.showMessageDialog(null, rs.getString(2)+" "+rs.getString(3));
                setProid(rs.getInt(1));
                setPronam(rs.getString(2));
                setProloca(rs.getString(3));
                
                proExs = true;
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Error in projectExists :: "+ e);
        }
         
        return proExs;
    }
    public void findProject(int id){
       
        String sql = "SELECT * FROM tb_project WHERE projid = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            if(rs.next()){
                setProid(rs.getInt(1));
                setPronam(rs.getString(2));
                setProloca(rs.getString(3));
            }
            ps.close();
            rs.close();
            con.close();        
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Error in findProject :: "+ e);
        }
    }
    public void updateProyekto(){
          String sql = "UPDATE tb_project SET proj_name = ?, proj_location = ? WHERE projid = ?";
          PreparedStatement ps = null;
          try{
              con = ConnectDB();
              ps = con.prepareStatement(sql);
              ps.setString(1, getPronam());
              ps.setString(2, getProloca());
              ps.setInt(3, getProid());
              ps.executeUpdate();
              
              ps.close();
              con.close();
          }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Error in updateProyekto :: "+e);
          }
    }
    public void addProyekto(){
        //JOptionPane.showMessageDialog(null, "natanggap \n proname = "+getPronam()+"\n"
        //        + "prolocation = "+getProloca());
        if(projectExists(getPronam(),getProloca())==false){
        String sql = "INSERT INTO tb_project(proj_name, proj_location) VALUES (?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getPronam());
            ps.setString(2, getProloca());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            setProid(rs.getInt(1));
            
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Error in addProyekto :: "+e);
        }
        }
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
