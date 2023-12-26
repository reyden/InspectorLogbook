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
 *
 * @author 19950014
 */

public class Nasiyasat {
    int nid;
    String mpr_cnt;
    String gugol;
    int pid;
    int inid;
    int gid;
    String contno;
    String petsa;
    String currwork;
    String kulangPlano;
    String kulangMateryals;
    String percentComp;
    String napuna;
    String nagpatotoo;
    Connection con; 
    
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getInid() {
        return inid;
    }

    public void setInid(int inid) {
        this.inid = inid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getContno() {
        return contno;
    }

    public void setContno(String contno) {
        this.contno = contno;
    }

    public String getPetsa() {
        return petsa;
    }

    public void setPetsa(String petsa) {
        this.petsa = petsa;
    }

    public String getCurrwork() {
        return currwork;
    }

    public void setCurrwork(String currwork) {
        this.currwork = currwork;
    }

    public String getMpr_cnt() {
        return mpr_cnt;
    }

    public void setMpr_cnt(String mpr_cnt) {
        this.mpr_cnt = mpr_cnt;
    }

    public String getGugol() {
        return gugol;
    }

    public void setGugol(String gugol) {
        this.gugol = gugol;
    }

    public String getKulangPlano() {
        return kulangPlano;
    }

    public void setKulangPlano(String kulangPlano) {
        this.kulangPlano = kulangPlano;
    }

    public String getKulangMateryals() {
        return kulangMateryals;
    }

    public void setKulangMateryals(String kulangMateryals) {
        this.kulangMateryals = kulangMateryals;
    }

    public String getPercentComp() {
        return percentComp;
    }

    public void setPercentComp(String percentComp) {
        this.percentComp = percentComp;
    }

    public String getNapuna() {
        return napuna;
    }

    public void setNapuna(String napuna) {
        this.napuna = napuna;
    }

    public String getNagpatotoo() {
        return nagpatotoo;
    }

    public void setNagpatotoo(String nagpatotoo) {
        this.nagpatotoo = nagpatotoo;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }
    public JComboBox nagpatotooCombo(){
           JComboBox combo = new JComboBox();
           String qry = "SELECT DISTINCT nagpatotoo FROM tb_nasiyasat ORDER BY nagpatotoo ASC";
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
               JOptionPane.showMessageDialog(null, "nagpatotooCombo :: "+e);
           }
           
           return combo;
    }
    public boolean findNasiyasat(int id1){
        String sql = "SELECT * FROM tb_nasiyasat WHERE id = ?";
        boolean exists = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id1);
            rs = ps.executeQuery();
            if(rs.next()){
                setNid(rs.getInt(1));
                setContno(rs.getString(2));
                setPetsa(rs.getString(3));
                setCurrwork(rs.getString(4));
                setMpr_cnt(rs.getString(5));
                setGugol(rs.getString(6));
                setKulangPlano(rs.getString(7));
                setKulangMateryals(rs.getString(8));
                setPercentComp(rs.getString(9));
                setNapuna(rs.getString(10));
                setPid(rs.getInt(11));
                setInid(rs.getInt(12));
                setNagpatotoo(rs.getString(13));
                setGid(rs.getInt(14));               
                
            }
            ps.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in isRecordExist :: "+e);
        }
        return exists;
    }
    public boolean isRecordExist(String control){
        String sql = "SELECT * FROM tb_nasiyasat WHERE control_no = ?";
        boolean exists = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, control);
            rs = ps.executeQuery();
            if(rs.next()){            
                                
                exists = true;
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in isRecordExist :: "+e);
        }
        return exists;
    }
    public void updateNasiyasat(){
        String sql = "UPDATE tb_nasiyasat SET "
                + "control_no = ?, "
                + "petsa = ?, "
                + "current_work = ?, "
                + "mpr_count = ?, "
                + "nagugol = ?, "
                + "kulangnaplano = ?, "
                + "kulangnamateryales = ?, "
                + "percentcompletion = ?, "
                + "puna = ?, "
                + "projid = ?, "
                + "inspid = ?, "
                + "nagpatotoo = ?, "
                + "groupid = ? "
                + "WHERE id = ?";//na groupid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = null;
        /*
        JOptionPane.showMessageDialog(null, "Update here = "+getNid()+"\n getContno = "+getContno()+"\n getPetsa = "+getPetsa()+""
                + "\n getCurrwork() = "+getCurrwork()+"\n getMpr_cnt() = "+getMpr_cnt()+"\n getGugol() = "+getGugol()+""
                + "\n getKulangPlano() = "+getKulangPlano()+"\n getKulangMateryals() = "+getKulangMateryals()+"\n getPercentComp() = "+getPercentComp()+""
                + "\n getNapuna() = "+getNapuna()+"\n getPid() = "+getPid()+"\n getPid() = "+getPid()+"\n getInid() = "+getInid()+"\n getNagpatotoo() = "+getNagpatotoo()+""
                + "\n getGid() = "+getGid()+" \n getNid() = "+getNid());
        */
        try{
             con = ConnectDB();
             ps = con.prepareStatement(sql);
             ps.setString(1, getContno());
             ps.setString(2, getPetsa());
             ps.setString(3, getCurrwork());
             ps.setString(4, getMpr_cnt());
             ps.setString(5, getGugol());
             ps.setString(6, getKulangPlano());
             ps.setString(7, getKulangMateryals());
             ps.setString(8, getPercentComp());
             ps.setString(9, getNapuna());
             ps.setInt(10, getPid());
             ps.setInt(11, getInid());
             ps.setString(12, getNagpatotoo());
             ps.setInt(13, getGid());
             ps.setInt(14,getNid());
             ps.executeUpdate();
             
             ps.close();
             con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void deleteNasiyasat(int nasi){
        String sql="DELETE FROM tb_nasiyasat WHERE id = ?";
        PreparedStatement ps;
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nasi);
            ps.executeUpdate();
          
            ps.close();
            con.close();
        }catch(SQLException e){}
        
    }    
    public void addSiyasat(){
        String sql = "INSERT INTO tb_nasiyasat(control_no, petsa, current_work, mpr_count, nagugol,"
                + " kulangnaplano, kulangnamateryales, percentcompletion, puna, projid, inspid, "
                + "nagpatotoo, groupid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Connection con = ConnectDB();
        try{
            con = ConnectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, getContno());
            ps.setString(2, getPetsa());
            ps.setString(3, getCurrwork());
            ps.setString(4, getMpr_cnt());
            ps.setString(5, getGugol());
            ps.setString(6, getKulangPlano());
            ps.setString(7, getKulangMateryals());
            ps.setString(8, getPercentComp());
            ps.setString(9, getNapuna());
            ps.setInt(10, getPid());
            ps.setInt(11, getInid());
            ps.setString(12, getNagpatotoo());
            ps.setInt(13,getGid());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            int id = rs.getInt(1);
            setNid(id);
            
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in addSiyasat :: "+e);
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
