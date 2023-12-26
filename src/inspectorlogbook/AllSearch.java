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
 * @author 19950014
 */
public class AllSearch {
    String control_no;
    String petsa;
    String project;
    String location;
    String idnumber;
    String grupo;
    String fname;
    String mname;
    String lname;
    String kasagawa;
    String bitao;
    String nagug;
    String kuplan;
    String kumater;
    String porga;
    String badap;
    String nagpap;

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getPetsa() {
        return petsa;
    }

    public void setPetsa(String petsa) {
        this.petsa = petsa;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getKasagawa() {
        return kasagawa;
    }

    public void setKasagawa(String kasagawa) {
        this.kasagawa = kasagawa;
    }

    public String getBitao() {
        return bitao;
    }

    public void setBitao(String bitao) {
        this.bitao = bitao;
    }

    public String getNagug() {
        return nagug;
    }

    public void setNagug(String nagug) {
        this.nagug = nagug;
    }

    public String getKuplan() {
        return kuplan;
    }

    public void setKuplan(String kuplan) {
        this.kuplan = kuplan;
    }

    public String getKumater() {
        return kumater;
    }

    public void setKumater(String kumater) {
        this.kumater = kumater;
    }

    public String getPorga() {
        return porga;
    }

    public void setPorga(String porga) {
        this.porga = porga;
    }

    public String getBadap() {
        return badap;
    }

    public void setBadap(String badap) {
        this.badap = badap;
    }

    public String getNagpap() {
        return nagpap;
    }

    public void setNagpap(String nagpap) {
        this.nagpap = nagpap;
    }
    public boolean searchInspectorID(String idnu){
        boolean isExists = false;
        String sql = "SELECT i.fname, i.mname, i.lname, g.grpname, n.petsa FROM tb_inspector i, tb_group g, tb_nasiyasat n"
                + " WHERE i.inspid = n.inspid AND g.gid = n.groupid AND i.idnumber = ? ORDER BY n.petsa DESC";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = ConnectDB();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, idnu);
            rs = ps.executeQuery();
            if(rs.next()){
                
                setGrupo(rs.getString(4));
                
                isExists =true;
            }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in searchInspectorID :: "+e);
        }
        return isExists;
    }
   
    public boolean searchAll(String conu){
        
        boolean kita = false;
        String sql = "SELECT n.control_no, n.petsa, p.proj_name, p.proj_location, i.idnumber, g.grpname, "
                + "i.fname, i.mname, i.lname, n.current_work, n.mpr_count, n.nagugol, n.kulangnaplano, n.kulangnamateryales, "
                + "n.percentcompletion, n.puna, n.nagpatotoo FROM tb_group g, tb_inspector i, tb_nasiyasat n, tb_project p "
                + "WHERE i.inspid = n.inspid AND g.gid = n.groupid AND p.projid = n.projid and n.control_no LIKE ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = ConnectDB();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, conu.trim());
            rs = ps.executeQuery();
            //JOptionPane.showMessageDialog(null, conu);
            if(rs.next()){
                setControl_no(rs.getString(1));
                setPetsa(rs.getString(2));
                setProject(rs.getString(3));
                setLocation(rs.getString(4));
                setIdnumber(rs.getString(5));
                setGrupo(rs.getString(6));
                setFname(rs.getString(7));
                setMname(rs.getString(8));
                setLname(rs.getString(9));
                setKasagawa(rs.getString(10));
                setBitao(rs.getString(11));
                setNagug(rs.getString(12));
                setKuplan(rs.getString(13));
                setKumater(rs.getString(14));
                setPorga(rs.getString(15));
                setBadap(rs.getString(16));
                setNagpap(rs.getString(17));
                
                kita = true;
            }
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,"Error in AllSearch :: "+ e);
        }finally{
            try{
                ps.close();
                rs.close();
                con.close();
            }catch(SQLException e){JOptionPane.showMessageDialog(null,"Finally Error in AllSearch :: "+ e);}
        }
        
        return kita;
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
