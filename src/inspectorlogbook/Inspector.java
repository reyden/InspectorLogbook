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
public class Inspector {
    int insid;
    String idnum;
    String firstn;
    String middlen;
    String lastn;
    Connection con;
    public int getInsid() {
        return insid;
    }

    public void setInsid(int insid) {
        this.insid = insid;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getFirstn() {
        return firstn;
    }

    public void setFirstn(String firstn) {
        this.firstn = firstn;
    }

    public String getMiddlen() {
        return middlen;
    }

    public void setMiddlen(String middlen) {
        this.middlen = middlen;
    }

    public String getLastn() {
        return lastn;
    }

    public void setLastn(String lastn) {
        this.lastn = lastn;
    }
        public JComboBox idnumberCombo(){
           JComboBox combo = new JComboBox();
           String qry = "SELECT DISTINCT idnumber FROM tb_inspector ORDER BY idnumber ASC";
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
               JOptionPane.showMessageDialog(null, "idnumberCombo :: "+e);
           
           }
           
           return combo;
    }
    public void searchInspector(int id1){
        String sql = "SELECT * FROM tb_inspector WHERE inspid = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
         
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id1);
            rs = ps.executeQuery();
            if(rs.next()){
                setInsid(rs.getInt(1));
                setIdnum(rs.getString(2));
                setFirstn(rs.getString(3));
                setMiddlen(rs.getString(4));
                setLastn(rs.getString(5));
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){ 
            JOptionPane.showMessageDialog(null, "Error in searchInspector :: "+e);
        }
    }
    public int getInspectorID(String idnum){
        int id1 = 0;
        String sql = "SELECT inspid FROM tb_inspector WHERE idnumber = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, idnum);
            rs = ps.executeQuery();
            if(rs.next()){
                id1 = rs.getInt(1);
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error in getInspectorID :: "+ e);
        }
        return id1;
    }
    public boolean findInspector(String id){
        String sql = "SELECT * FROM tb_inspector WHERE idnumber LIKE ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                setInsid(rs.getInt(1));
                setIdnum(rs.getString(2));
                setFirstn(rs.getString(3));
                setMiddlen(rs.getString(4));
                setLastn(rs.getString(5));
                
                exists = true;
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){ 
            JOptionPane.showMessageDialog(null, "Error in findInspector :: "+e);
        }
        
        return exists;
        
    }
    public void updateInspector(){
        String sql = "UPDATE tb_inspector SET idnumber = ?, fname = ?, mname = ?, lname = ? "
                + " WHERE inspid = ?";
        PreparedStatement ps = null;
        
        try{
           // JOptionPane.showMessageDialog(null,getIdnum()+" <=> "+getFirstn()+" <=> "+getMiddlen()+" <=> "+getLastn()+" <=> "+getInsid());
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getIdnum());
            ps.setString(2, getFirstn());
            ps.setString(3, getMiddlen());
            ps.setString(4, getLastn());
            ps.setInt(5,getInsid());
            ps.executeUpdate();
            
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error in updateInspector :: "+ e);
        }
    }
    public void addInspector(){
        
       // if(findInspector(getIdnum())==false){
        
        String sql = "INSERT INTO tb_inspector(idnumber, fname, mname, lname) values(?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getIdnum());
            ps.setString(2, getFirstn());
            ps.setString(3, getMiddlen());
            ps.setString(4, getLastn());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            setInsid(rs.getInt(1));
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in addInspector :: "+e);
        }
       //}
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
