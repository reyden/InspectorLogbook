/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author 19950014
 */
public final class LogbookMain extends javax.swing.JFrame {

    /**
     * Creates new form LogbookMain
     */
    boolean onEditMode = false;
    int taggedProID = 0;
    int taggedNasID = 0;
    int taggedGrpID = 0;
    String taggedIdNumber = "";
    String taggedControlNo = "";
    int taggedInsID = 0;
    
    public LogbookMain() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(".\\ECD.png"));
        setLocationRelativeTo(null);
        setTitle("   Inspectors Logbook");
        
        ///beautify comboBoxes..............

        
        //initialize table "tbl_logbook"////////////////////////////////////////
        
        initializeCombos();
        
        tableColumnsAndWidth();
        tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
         tbtn_edit.setEnabled(false);
        //onAndOffFields(false);
        
        
        /*
        if(tbtn_edit.isEnabled()){
            tbtn_edit.setSelected(false);
            tbtn_edit.setEnabled(false);
                    
        }
        if(!tbl_logbook.isEnabled()){
           tbl_logbook.setEnabled(true);
        }
        */
        //tbtn_edit.setEnabled(false);
    }
    public void tableActions(){
        if(tbl_logbook.isEnabled()){
        try{
        //if(tbtn_edit.getText().equals("Save Update")){
        //    onAndOffFields(true);
        //} else {
        //    onAndOffFields(false);
        //}
        int row = tbl_logbook.getSelectedRow();
        int id_proj = Integer.parseInt(tbl_logbook.getValueAt(row, 0).toString());
        int id_nas = Integer.parseInt(tbl_logbook.getValueAt(row, 1).toString());
        int id_grp = Integer.parseInt(tbl_logbook.getValueAt(row, 2).toString());
        String id_number = tbl_logbook.getValueAt(row, 3).toString();
        int id_insp = Integer.parseInt(tbl_logbook.getValueAt(row, 4).toString());
        String controlNo = tbl_logbook.getValueAt(row, 7).toString();
    //for the primary keys tagged for edit use.....
        taggedGrpID = id_grp;
        taggedIdNumber = id_number;
        taggedInsID = id_insp;
        taggedNasID = id_nas;
        taggedProID = id_proj;
        taggedControlNo = controlNo; 
        lbl_attachments.setText(new Attachments().countAttachments(controlNo)+"");
    //
    //for project///////////////////////////////////////////////////////////////    
        Proyekto prykto =new Proyekto();
        prykto.findProject(id_proj);
        cbo_project.setSelectedItem(prykto.getPronam());
        cbo_location.setSelectedItem(prykto.getProloca());
    
    //for nasiyasat/////////////////////////////////////////////////////////////
        Nasiyasat nas = new Nasiyasat(); 
        nas.findNasiyasat(id_nas);
        txt_controlno.setText(nas.getContno());
        ((JTextField)jdc_petsa.getDateEditor().getUiComponent())
                      .setText(nas.getPetsa());
        txt_kasalukuyan.setText(nas.getCurrwork());
        txt_bilang.setText(nas.getMpr_cnt());
        txt_nagugol.setText(nas.getGugol());
        txt_kulangplano.setText(nas.getKulangPlano());
        txt_kulangmateryales.setText(nas.getKulangMateryals());
        txt_porsyento.setText(nas.getPercentComp());
        txt_dapatipaabot.setText(nas.getNapuna());
        cbo_nagpatotoo.setSelectedItem(nas.getNagpatotoo());
    
    //for group/////////////////////////////////////////////////////////////////
        Grupo grpo = new Grupo();
        grpo.findGroup(id_grp);
        cbo_group.setSelectedItem(grpo.getGrpName());
     
        
    //for inspector/////////////////////////////////////////////////////////////
        Inspector insp = new Inspector();
        insp.searchInspector(id_insp);
        cbo_idnumber.setSelectedItem(insp.getIdnum());
        txt_fname.setText(insp.getFirstn());
        txt_mname.setText(insp.getMiddlen());
        txt_lname.setText(insp.getLastn());
        
        tbtn_edit.setEnabled(true);
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null, "Error in tableActions :: "+e);
        }
        }else{
            JOptionPane.showMessageDialog(null, "You are in \"New\" data Entry mode.\n "
                    + "You may exit by clicking \"Cancel\" to use this table for viewing data.");
        }
    }
    public void tableColumnsAndWidth(){
        
        ///Table CellRenderers..................
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            //centerRenderer.setForeground(new java.awt.Color(0,0,0));
            //centerRenderer.setBackground(new Color(174,228,255));
           // LogbookTableCellRender currencyRenderer = new LogbookTableCellRender();
           // currencyRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        
        
        TableColumn column;
        for(int i = 0; i < 12; i++){
            
            column = tbl_logbook.getColumnModel().getColumn(i);
            
            switch (i) {
                case 0:
                    column.setWidth(0);
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                    
                    break;
                case 1:
                    column.setWidth(0);
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                    
                    break;
                case 2:
                    column.setWidth(0);
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                    
                    break;
                case 3:
                    column.setWidth(0);
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                    
                    break;
                case 4:
                    column.setWidth(0);
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                    
                    break;
                case 5:
                    column.setWidth(20);
                    column.setMinWidth(20);
                    column.setMaxWidth(20);
                    
                    break;
                case 6:
                    column.setWidth(100);
                    column.setMinWidth(100);
                    column.setMaxWidth(100);
                    column.setCellRenderer(centerRenderer);
                    break;
                case 7:
                    column.setWidth(100);
                    column.setMinWidth(100);
                    column.setMaxWidth(100);
                    column.setPreferredWidth(100);
                    column.setCellRenderer(centerRenderer);
                    break;
            /*    case 8:
                    column.setMinWidth(150);
                    column.setMaxWidth(150);
                    column.setMaxWidth(150);
                    break;   
                case 9:
                    column.setMinWidth(250);
                    column.setMaxWidth(250);
                    column.setMaxWidth(250);
                    break;
                case 10:
                    column.setMinWidth(250);
                    column.setMaxWidth(250);
                    column.setMaxWidth(250);
                    break;
                case 11:
                    column.setMinWidth(250);
                    column.setMaxWidth(250);
                    column.setMaxWidth(250);
                    break;
            */  case 13:
                    column.setMinWidth(50);
                    column.setMaxWidth(50);
                    column.setMaxWidth(50);
                    column.setPreferredWidth(50);
                    column.setCellRenderer(centerRenderer);
                    break;
                case 14:
                    column.setWidth(100);
                    column.setMinWidth(100);
                    column.setMaxWidth(100);
                    break;
            /*    case 14:
                    column.setMinWidth(250);
                    column.setMaxWidth(250);
                    column.setMaxWidth(250);
                    break;
                case 15:
                    column.setWidth(250);
                    column.setMinWidth(250);
                    column.setMaxWidth(250);
                    break;
             */ case 17:
                    column.setWidth(80);
                    column.setMinWidth(80);
                    column.setMaxWidth(80);
                    column.setCellRenderer(centerRenderer);
                    break;
              /* case 18:
                    column.setWidth(300);
                    column.setMinWidth(300);
                    column.setMaxWidth(300);
                    break;
                default:
                    break;   */
            }
        }
        //ALTERNATE TABLE ROWS COLOR
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            if (defaults.get("Table.alternateRowColor") == null)
                defaults.put("Table.alternateRowColor", new Color(223,237,242)); 
        //END FOR  ALTERNATE TABLE ROW COLORS..
        
        
        ///Enable horizontal scrollbar here///////////////////////////////////  
            tbl_logbook.getParent().addComponentListener(new ComponentAdapter(){
                @Override
                public void componentResized(final ComponentEvent e){
                    if(tbl_logbook.getPreferredSize().width < tbl_logbook.getParent().getWidth())
                    {
                        tbl_logbook.setAutoResizeMode(1);
                        
                    }else{
                        tbl_logbook.setAutoResizeMode(0);
                    }
                }
       
            });
            
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 1), new java.awt.Dimension(0, 1), new java.awt.Dimension(32767, 1));
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txt_controlno = new javax.swing.JTextField();
        jdc_petsa = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_kasalukuyan = new javax.swing.JTextArea();
        cbo_location = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        txt_fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_mname = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_lname = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbo_idnumber = new javax.swing.JComboBox<>();
        cbo_group = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbo_project = new javax.swing.JComboBox<>();
        txt_bilang = new javax.swing.JTextField();
        lbl_attachments = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_kulangmateryales = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_kulangplano = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_dapatipaabot = new javax.swing.JTextArea();
        txt_nagugol = new javax.swing.JFormattedTextField();
        txt_porsyento = new javax.swing.JTextField();
        cbo_nagpatotoo = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_logbook = new javax.swing.JTable();
        btn_print = new javax.swing.JButton();
        btn_deleteRecord = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btn_fileAttach = new javax.swing.JButton();
        tbtn_check = new javax.swing.JToggleButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn_new = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        tbtn_edit = new javax.swing.JToggleButton();
        btn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(223, 237, 242));

        jPanel1.setBackground(new java.awt.Color(0, 63, 94));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0, 0, 1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("COMPANY XXX");
        jLabel15.setToolTipText("");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel15);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("E n g i n e e r i n g   &   C o n s t r u c t i o n   D e p a r t m e n t");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel14);

        filler1.setBackground(new java.awt.Color(204, 204, 204));
        filler1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(187, 233, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("<html>KASALUKUYANG<br> GAWAIN:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CONTROL NO:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("LOCATION:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("PETSA:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("PROJECT:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("NAGSIYASAT:");

        jPanel7.setBackground(new java.awt.Color(223, 237, 242));
        jPanel7.setOpaque(false);

        txt_controlno.setBackground(new java.awt.Color(255,255,255)
        );
        txt_controlno.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_controlno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_controlnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_controlnoKeyTyped(evt);
            }
        });

        jdc_petsa.setBackground(new java.awt.Color(255, 255, 255));
        jdc_petsa.setDateFormatString("yyyy-MM-dd");
        jdc_petsa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txt_kasalukuyan.setColumns(20);
        txt_kasalukuyan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_kasalukuyan.setLineWrap(true);
        txt_kasalukuyan.setRows(5);
        txt_kasalukuyan.setWrapStyleWord(true);
        DocumentFilter filter5 = new UppercaseDocumentFilter();
        AbstractDocument remDoc = (AbstractDocument)txt_kasalukuyan.getDocument();
        remDoc.setDocumentFilter(filter5);
        jScrollPane1.setViewportView(txt_kasalukuyan);

        cbo_location.setBackground(new java.awt.Color(255,255,255)
        );
        cbo_location.setEditable(true);
        cbo_location.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(187, 233, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txt_fname.setBackground(new java.awt.Color(255,255,255));
        txt_fname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("F i r s t   n a m e");

        txt_mname.setBackground(new java.awt.Color(255,255,255));
        txt_mname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("M i d d l e   n a m e");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txt_lname.setBackground(new java.awt.Color(255,255,255));
        txt_lname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Last name");

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("ID Number");

        cbo_idnumber.setBackground(new java.awt.Color(255,255,255)
        );
        cbo_idnumber.setEditable(true);
        cbo_idnumber.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbo_idnumber.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_idnumberItemStateChanged(evt);
            }
        });

        cbo_group.setBackground(new java.awt.Color(255,255,255));
        cbo_group.setEditable(true);
        cbo_group.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Group");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(txt_fname, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_mname)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_lname, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addGap(1, 1, 1))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbo_idnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_group, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbo_idnumber)
                    .addComponent(cbo_group))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)))
        );

        DocumentFilter filter2 = new UppercaseDocumentFilter();
        AbstractDocument fnameDoc = (AbstractDocument)txt_fname.getDocument();
        fnameDoc.setDocumentFilter(filter2);
        DocumentFilter filter3 = new UppercaseDocumentFilter();
        AbstractDocument mnameDoc = (AbstractDocument)txt_mname.getDocument();
        mnameDoc.setDocumentFilter(filter3);
        DocumentFilter filter4 = new UppercaseDocumentFilter();
        AbstractDocument lnameDoc = (AbstractDocument)txt_lname.getDocument();
        lnameDoc.setDocumentFilter(filter4);
        cbo_idnumber.getEditor().getEditorComponent().addKeyListener(new CboNumberKeyListener());
        ((JTextField)cbo_idnumber.getEditor().getEditorComponent()).setHorizontalAlignment(JTextField.CENTER);
        ((JLabel)cbo_idnumber.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        AutoCompleteDecorator.decorate(cbo_idnumber);
        cbo_group.getEditor().getEditorComponent().addKeyListener(new CboToUppercaseListener());
        ((JTextField)cbo_group.getEditor().getEditorComponent()).setHorizontalAlignment(JTextField.CENTER);
        ((JLabel)cbo_group.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        AutoCompleteDecorator.decorate(cbo_group);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("[yyyy-MM-dd]");

        cbo_project.setBackground(new java.awt.Color(255, 255, 255));
        cbo_project.setEditable(true);
        cbo_project.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbo_project.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_projectItemStateChanged(evt);
            }
        });

        txt_bilang.setBackground(new java.awt.Color(255,255,255)
        );
        txt_bilang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_bilang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bilangKeyTyped(evt);
            }
        });

        lbl_attachments.setBackground(new java.awt.Color(255, 255, 255));
        lbl_attachments.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_attachments.setForeground(new java.awt.Color(255, 0, 51));
        lbl_attachments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_attachments.setText("0");
        lbl_attachments.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_attachments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_attachmentsMouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel24.setText("A t t a c h m e n t");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(cbo_project, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_location, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jdc_petsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_controlno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txt_bilang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addGap(6, 6, 6)
                        .addComponent(lbl_attachments, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(txt_controlno, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdc_petsa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_project, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_location, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bilang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_attachments, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(2, 2, 2))
        );

        cbo_location.getEditor().getEditorComponent().addKeyListener(new CboToUppercaseListener());
        AutoCompleteDecorator.decorate(cbo_location);
        cbo_project.getEditor().getEditorComponent().addKeyListener(new CboToUppercaseListener());
        AutoCompleteDecorator.decorate(cbo_project);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("BILANG NG TAO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        jPanel3.setBackground(new java.awt.Color(187, 233, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("<html>BAGAY NA DAPAT IPA-ABOT<br><sp><sp><sp> SA TANGGAPAN:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("KULANG NA MATERYALES:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("PORSIYENTO NG GAWAIN:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("KULANG NA PLANO:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("NAGUGOL:");

        jPanel8.setBackground(new java.awt.Color(231, 230, 230));
        jPanel8.setOpaque(false);

        txt_kulangmateryales.setColumns(20);
        txt_kulangmateryales.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_kulangmateryales.setLineWrap(true);
        txt_kulangmateryales.setRows(5);
        txt_kulangmateryales.setWrapStyleWord(true);
        DocumentFilter filter7 = new UppercaseDocumentFilter();
        AbstractDocument matDoc = (AbstractDocument)txt_kulangmateryales.getDocument();
        matDoc.setDocumentFilter(filter7);
        jScrollPane3.setViewportView(txt_kulangmateryales);

        txt_kulangplano.setColumns(20);
        txt_kulangplano.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_kulangplano.setLineWrap(true);
        txt_kulangplano.setRows(5);
        txt_kulangplano.setWrapStyleWord(true);
        DocumentFilter filter6 = new UppercaseDocumentFilter();
        AbstractDocument searchDoc = (AbstractDocument)txt_kulangplano.getDocument();
        searchDoc.setDocumentFilter(filter6);
        jScrollPane2.setViewportView(txt_kulangplano);

        txt_dapatipaabot.setColumns(20);
        txt_dapatipaabot.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_dapatipaabot.setLineWrap(true);
        txt_dapatipaabot.setRows(5);
        txt_dapatipaabot.setWrapStyleWord(true);
        DocumentFilter filter8 = new UppercaseDocumentFilter();
        AbstractDocument punaDoc =(AbstractDocument)txt_dapatipaabot.getDocument();
        punaDoc.setDocumentFilter(filter8);
        jScrollPane4.setViewportView(txt_dapatipaabot);

        txt_nagugol.setBackground(new java.awt.Color(255,255,255)
        );
        txt_nagugol.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00#"))));
        txt_nagugol.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nagugol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nagugolKeyTyped(evt);
            }
        });

        txt_porsyento.setBackground(new java.awt.Color(255,255,255)
        );
        txt_porsyento.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_porsyento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_porsyentoKeyTyped(evt);
            }
        });

        cbo_nagpatotoo.setBackground(new java.awt.Color(255,255,255)
        );
        cbo_nagpatotoo.setEditable(true);
        cbo_nagpatotoo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbo_nagpatotoo.setModel(new Nasiyasat().nagpatotooCombo().getModel());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_nagpatotoo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nagugol, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_porsyento, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txt_nagugol, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_porsyento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_nagpatotoo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        cbo_nagpatotoo.getEditor().getEditorComponent().addKeyListener(new CboToUppercaseListener());
        AutoCompleteDecorator.decorate(cbo_nagpatotoo);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("NAGPATOTOO:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(65, 65, 65)
                .addComponent(jLabel8)
                .addGap(55, 55, 55)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(20, 20, 20))
        );

        jPanel4.setBackground(new java.awt.Color(187, 233, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icon-search.gif"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_search.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        tbl_logbook.setAutoCreateRowSorter(true);
        tbl_logbook.setFont(new java.awt.Font("Aerial Narrow", 0, 14));
        tbl_logbook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "<html>PRO_ID<br>&nbsp ", "NAS_ID", "GRP_ID", "ID_NUMBER", "INS_ID", "", "<html><br>PETSA<br>&nbsp", "CONTROL NO", "GRUPO", "NAGSIYASAT", "PROJECT", "NAGPATOTOO", "KASALUKUYANG GAWAIN", "<html>BILANG<br>NG TAO", "NAGUGOL", "<html>KULANG NA PLANO", "KULANG NA MATERYALES", "<html>PORSIYENTO <br>NG GAWAIN", "BAGAY NA DAPAT IPAABOT SA TANGGAPAN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_logbook.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_logbook.setFillsViewportHeight(true);
        tbl_logbook.setRowHeight(26);
        tbl_logbook.getTableHeader().setReorderingAllowed(false);
        tbl_logbook.getTableHeader().setFont(new java.awt.Font("Aerial Narrow",1,12));
        tbl_logbook.getTableHeader().setBackground(new java.awt.Color(0,63,94));
        tbl_logbook.getTableHeader().setForeground(new java.awt.Color(255,255,255));
        tbl_logbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_logbookMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_logbookMouseReleased(evt);
            }
        });
        tbl_logbook.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_logbookKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_logbook);
        if (tbl_logbook.getColumnModel().getColumnCount() > 0) {
            tbl_logbook.getColumnModel().getColumn(0).setMinWidth(0);
            tbl_logbook.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbl_logbook.getColumnModel().getColumn(0).setMaxWidth(0);
            tbl_logbook.getColumnModel().getColumn(1).setMinWidth(0);
            tbl_logbook.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbl_logbook.getColumnModel().getColumn(1).setMaxWidth(0);
            tbl_logbook.getColumnModel().getColumn(2).setMinWidth(0);
            tbl_logbook.getColumnModel().getColumn(2).setPreferredWidth(0);
            tbl_logbook.getColumnModel().getColumn(2).setMaxWidth(0);
            tbl_logbook.getColumnModel().getColumn(3).setMinWidth(0);
            tbl_logbook.getColumnModel().getColumn(3).setPreferredWidth(0);
            tbl_logbook.getColumnModel().getColumn(3).setMaxWidth(0);
            tbl_logbook.getColumnModel().getColumn(4).setMinWidth(0);
            tbl_logbook.getColumnModel().getColumn(4).setPreferredWidth(0);
            tbl_logbook.getColumnModel().getColumn(4).setMaxWidth(0);
            tbl_logbook.getColumnModel().getColumn(5).setMinWidth(20);
            tbl_logbook.getColumnModel().getColumn(5).setPreferredWidth(20);
            tbl_logbook.getColumnModel().getColumn(5).setMaxWidth(20);
            tbl_logbook.getColumnModel().getColumn(6).setMinWidth(100);
            tbl_logbook.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbl_logbook.getColumnModel().getColumn(6).setMaxWidth(100);
            tbl_logbook.getColumnModel().getColumn(7).setMinWidth(100);
            tbl_logbook.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbl_logbook.getColumnModel().getColumn(7).setMaxWidth(100);
            tbl_logbook.getColumnModel().getColumn(13).setMinWidth(50);
            tbl_logbook.getColumnModel().getColumn(13).setPreferredWidth(50);
            tbl_logbook.getColumnModel().getColumn(13).setMaxWidth(50);
            tbl_logbook.getColumnModel().getColumn(14).setMinWidth(120);
            tbl_logbook.getColumnModel().getColumn(14).setPreferredWidth(120);
            tbl_logbook.getColumnModel().getColumn(14).setMaxWidth(120);
            tbl_logbook.getColumnModel().getColumn(17).setMinWidth(80);
            tbl_logbook.getColumnModel().getColumn(17).setPreferredWidth(80);
            tbl_logbook.getColumnModel().getColumn(17).setMaxWidth(80);
            tbl_logbook.getColumnModel().getColumn(18).setMinWidth(250);
            tbl_logbook.getColumnModel().getColumn(18).setPreferredWidth(250);
            tbl_logbook.getColumnModel().getColumn(18).setMaxWidth(250);
        }
        /*
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
        defaults.put("Table.alternateRowColor", new Color(223,237,242));

        tbl_logbook.getParent().addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(final ComponentEvent e){
                if(tbl_logbook.getPreferredSize().width < tbl_logbook.getParent().getWidth())
                {
                    tbl_logbook.setAutoResizeMode(1);

                }else{
                    tbl_logbook.setAutoResizeMode(0);
                }
            }

        });
        */

        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/print-icon.png"))); // NOI18N
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        btn_deleteRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/delete.png"))); // NOI18N
        btn_deleteRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteRecordActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Search:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/refresh.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_fileAttach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/attach.png"))); // NOI18N
        btn_fileAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fileAttachActionPerformed(evt);
            }
        });

        tbtn_check.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/checkbox_on.png"))); // NOI18N
        tbtn_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtn_checkActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel23.setText("XXXXXXXX XXXXX XXXXX XXXXX XXXXX XXXXX XXXXX XXX");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tbtn_check, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_deleteRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_fileAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel23)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_deleteRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(btn_fileAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tbtn_check, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jLabel23)
                .addGap(2, 2, 2))
        );

        DocumentFilter filter = new UppercaseDocumentFilter();
        AbstractDocument txtsearchDoc = (AbstractDocument)txt_search.getDocument();
        txtsearchDoc.setDocumentFilter(filter);

        jPanel5.setBackground(new java.awt.Color(0, 63, 94));

        btn_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/new-file-btn.png"))); // NOI18N
        btn_new.setMnemonic('N');
        btn_new.setText("New");
        btn_new.setPreferredSize(new java.awt.Dimension(125, 125));
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });
        btn_new.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_newKeyPressed(evt);
            }
        });

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/save-disk1.png"))); // NOI18N
        btn_save.setMnemonic('S');
        btn_save.setText("Save");
        btn_save.setPreferredSize(new java.awt.Dimension(125, 125));
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        btn_save.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_saveKeyPressed(evt);
            }
        });

        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/Exit1.png"))); // NOI18N
        btn_exit.setMnemonic('X');
        btn_exit.setText("Exit");
        btn_exit.setPreferredSize(new java.awt.Dimension(125, 125));
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });
        btn_exit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_exitKeyPressed(evt);
            }
        });

        tbtn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icon18_edit_allbkg.gif"))); // NOI18N
        tbtn_edit.setMnemonic('U');
        tbtn_edit.setText("Update Record");
        tbtn_edit.setPreferredSize(new java.awt.Dimension(125, 125));
        tbtn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtn_editActionPerformed(evt);
            }
        });
        tbtn_edit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbtn_editKeyPressed(evt);
            }
        });

        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/s_cancel.png"))); // NOI18N
        btn_cancel.setMnemonic('a');
        btn_cancel.setText("Cancel");
        btn_cancel.setPreferredSize(new java.awt.Dimension(125, 125));
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        btn_cancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_cancelKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbtn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(filler1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bilangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bilangKeyTyped
        char caracter = evt.getKeyChar();
            if (((caracter < '0') || (caracter > '9'))&& (caracter != '\b'))
                                    evt.consume();
    }//GEN-LAST:event_txt_bilangKeyTyped

    private void txt_nagugolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nagugolKeyTyped
        char caracter = evt.getKeyChar();
            if (((caracter < '0') || (caracter > '9'))&& (caracter != '\b') && (caracter != '.') && (caracter != ','))
                                        evt.consume();
    }//GEN-LAST:event_txt_nagugolKeyTyped

    private void txt_porsyentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_porsyentoKeyTyped
        char caracter = evt.getKeyChar();
            if (((caracter < '0') || (caracter > '9'))&& (caracter != '\b') && (caracter != '.') && (caracter != '%'))
                                    evt.consume();
    }//GEN-LAST:event_txt_porsyentoKeyTyped

    private void tbtn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtn_editActionPerformed
        
          editBtnInvoked();
           
    }
    private void editBtnInvoked(){    
        if(tbtn_edit.isSelected()){
            
            tbtn_edit.setForeground(Color.red);
            //tbtn_edit.setText("Save Update");
            btn_save.setEnabled(false);
            btn_exit.setEnabled(false);
            btn_new.setEnabled(false);
            //tbtn_edit.setMnemonic('U');
            onEditMode = true;
            onAndOffFields(true);
            
        }else{
            boolean nasi = false;
            if(allInputsValid()){
                
            try{    //
                 
                Nasiyasat siyat = new Nasiyasat();
                nasi = siyat.isRecordExist(txt_controlno.getText());
                if(nasi){
                    if(!taggedControlNo.equals(txt_controlno.getText())){
                        JOptionPane.showMessageDialog(null, "Control number is already used. Input another.");//\n"+taggedControlNo+" = "+txt_controlno.getText()); 
                        txt_controlno.setText(taggedControlNo);
                        txt_controlno.requestFocus();                        
                        
                           
                    }else{
                        //JOptionPane.showMessageDialog(null, "Good to go!");
                        updateRecord();
                        tbtn_edit.setForeground(Color.black);
                       // tbtn_edit.setText("Edit Record");
                        btn_save.setEnabled(true);
                        btn_exit.setEnabled(true);
                        btn_new.setEnabled(true);
                       // tbtn_edit.setMnemonic('E');
                        onEditMode = false;
                        onAndOffFields(false);
                    }
                }else{
                    //JOptionPane.showMessageDialog(null, "Wala pong katulad.");
                    updateRecord();
                    tbtn_edit.setForeground(Color.black);
                    //tbtn_edit.setText("Edit Record");
                    btn_save.setEnabled(true);
                    btn_exit.setEnabled(true);
                    btn_new.setEnabled(true);
                   // tbtn_edit.setMnemonic('E');
                    onEditMode = false;
                    onAndOffFields(false);
                }
                //initializeCombos();
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error in Update :: "+e);
            }   
                
            }
        }
        
    }//GEN-LAST:event_tbtn_editActionPerformed
    
    private void updateRecord(){
        int groupID;
       
        int inspectorID;
        String pro = cbo_project.getSelectedItem().toString();
        String loc = cbo_location.getSelectedItem().toString();
        String inum = cbo_idnumber.getSelectedItem().toString();
        String ngp = cbo_nagpatotoo.getSelectedItem().toString();
        String gpo = cbo_group.getSelectedItem().toString();
        
        try{
    /*     JOptionPane.showMessageDialog(null, taggedInsID+"  "+cbo_idnumber.getSelectedItem().toString()+"  "+ txt_fname.getText()+"  "+txt_mname.getText()+"  "+ txt_lname.getText());   
         InspectorUpdate iu = new InspectorUpdate(taggedInsID,cbo_idnumber.getSelectedItem().toString(), txt_fname.getText(),txt_mname.getText(), txt_lname.getText());
         inspectorID=iu.checkInspectorData();
        JOptionPane.showMessageDialog(null, "returnedID -> "+inspectorID+"   taggedID -> "+taggedInsID);
        if(inspectorID == taggedInsID){
            Inspector inptr = new Inspector();
            inptr.updateInspector(inspectorID,cbo_idnumber.getSelectedItem().toString(), txt_fname.getText(),txt_mname.getText(), txt_lname.getText());
            //take note that the reason why database locked error was always appearing was beacause of the the invokecation of combo boxes inside main events properties///
        }
    */    //instantiate then save Inspector..................................................
        
            Inspector insp = new Inspector();
            boolean findInspector = insp.findInspector(cbo_idnumber.getSelectedItem().toString());
            insp.setInsid(taggedInsID);
            insp.setIdnum(cbo_idnumber.getSelectedItem().toString());
            insp.setFirstn(txt_fname.getText());
            insp.setMiddlen(txt_mname.getText());
            insp.setLastn(txt_lname.getText());          
            
        //->    insp.setInsid(inspectorID);
            if(findInspector){ 
               int id = insp.getInspectorID(cbo_idnumber.getSelectedItem().toString());
               if(taggedInsID == id){
                    insp.updateInspector();
                    inspectorID=insp.getInsid();
                }else{
                    insp.setInsid(id);
                    insp.updateInspector();
                    inspectorID=insp.getInsid();
                
            }
            //JOptionPane.showMessageDialog(null, "taggedID => "+taggedInsID+"\n id => "+id+"\n insprectorID => "+inspectorID);
            
            }else{
                insp.setIdnum(cbo_idnumber.getSelectedItem().toString());
                insp.setFirstn(txt_fname.getText());
                insp.setMiddlen(txt_mname.getText());
                insp.setLastn(txt_lname.getText());
                insp.addInspector();
                inspectorID = insp.getInsid();
            }
        /*    
            JOptionPane.showMessageDialog(null, "idnum = "+insp.getIdnum()+"\n"
                    + "fn => "+insp.getFirstn()+"\n mn => "+insp.getMiddlen()+"\n ln => "+insp.getLastn()+"\n"
                            + "insId => "+inspectorID);
        */    
        //instantiate then save Grupo......................................................
           Grupo grp = new Grupo();
            boolean findGroup = grp.groupExists(cbo_group.getSelectedItem().toString());
            if(findGroup){
                if(taggedGrpID == grp.getGrpId()){
                    grp.setGrpId(taggedGrpID);
                    grp.setGrpName(cbo_group.getSelectedItem().toString());                
                    grp.updateGroup();
                    groupID = grp.getGrpId();                
                }else{ 
                    
                    grp.setGrpName(cbo_group.getSelectedItem().toString());
                    grp.updateGroup();
                    groupID=grp.getGrpId();
            }
            }else{
                grp.setGrpName(cbo_group.getSelectedItem().toString());
                grp.addNewGroup();
                groupID=grp.getGrpId();
            }
            
            
           // JOptionPane.showMessageDialog(null, "taggedGroupID => "+taggedGrpID+"\n grupo => "+grp.getGrpName()+"\n grpId => "+groupID);
           
        //instantiate then save Proyekto...................................................
            //JOptionPane.showMessageDialog(null, "pn => "+cbo_project.getSelectedItem().toString()+ "\n pl => "+cbo_location.getSelectedItem().toString());//+ "\n proyID => "+proyektoID);
             int proyektoID=0;
            Proyekto proy = new Proyekto();
            boolean findProyekto = proy.projectExists(cbo_project.getSelectedItem().toString(), cbo_location.getSelectedItem().toString());
            if(findProyekto){
                if(taggedProID==proy.getProid()){
                    proy.setPronam(cbo_project.getSelectedItem().toString());
                    proy.setProloca(cbo_location.getSelectedItem().toString());
                    proy.updateProyekto();
                    proyektoID = proy.getProid();
                }else{
                    proy.setPronam(cbo_project.getSelectedItem().toString());
                    proy.setProloca(cbo_location.getSelectedItem().toString());
                    proy.updateProyekto();
                    proyektoID = proy.getProid();
                }
            }else{
                
                //proy.setProid(taggedProID);
                
                proy.setPronam(cbo_project.getSelectedItem().toString());
                proy.setProloca(cbo_location.getSelectedItem().toString());
                proy.addProyekto();
                proyektoID = proy.getProid();
            }
           /// 
            
            
           //JOptionPane.showMessageDialog(null, "inspectorID => "+inspectorID+ "\n groupID => "+groupID+ "\n proyektoID => "+proyektoID);
           
        //instantiate then save Nasiyasat..................................................
        Nasiyasat siya = new Nasiyasat();
            //if(siya.isRecordExist(txt_controlno.getText())){
                //if(taggedIdNumber.equals(txt_controlno.getText())){
                    siya.setNid(taggedNasID);
                    siya.setContno(txt_controlno.getText());
                    siya.setPetsa(((JTextField)jdc_petsa.getDateEditor().getUiComponent()).getText());
                    siya.setCurrwork(txt_kasalukuyan.getText());
                    siya.setMpr_cnt(txt_bilang.getText());
                    siya.setGugol(txt_nagugol.getText());
                    siya.setKulangPlano(txt_kulangplano.getText());
                    siya.setKulangMateryals(txt_kulangmateryales.getText());
                    siya.setPercentComp(txt_porsyento.getText());
                    siya.setNapuna(txt_dapatipaabot.getText());
                    siya.setPid(proyektoID);
                    siya.setInid(inspectorID);
                    siya.setNagpatotoo(cbo_nagpatotoo.getSelectedItem().toString());
                    siya.setGid(groupID);
                
                    siya.updateNasiyasat();
                //}else{
                   
               // }
            
        /*   JOptionPane.showMessageDialog(null, "contNo = "+siya.getContno()+"\n petsa = "+siya.getPetsa()+"\n"
                   + "currentWork = "+siya.getCurrwork()+"\n mprCnt = "+siya.getMpr_cnt()+"\n gugol = "+siya.getGugol()+"\n"
                           + " kulangNaPlano = "+siya.getKulangPlano()+"\n kulangNaMateryales = "+siya.getKulangMateryals()+"\n"
                                   + "percentComp = "+siya.getPercentComp()+"\n napuna = "+siya.getNapuna()+"\n pid = "+siya.getPid()+"\n"
                                           + "Inid = "+siya.getInid()+"\n nagpatotoo = "+siya.getNagpatotoo()+"\n gid = "+siya.getGid()+"\n"
                                                   + "siyid = "+siya.getNid());
        */    
            JOptionPane.showMessageDialog(null, "Successfully saved!");
            initializeCombos();
            cbo_project.setSelectedItem(pro);        
            cbo_location.setSelectedItem(loc);        
            cbo_idnumber.setSelectedItem(inum);        
            cbo_group.setSelectedItem(gpo);
            cbo_nagpatotoo.setSelectedItem(ngp);
            tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error initializing accessors...  "+ e);
        }
        }
 
        
    
    
    private void initializeCombos(){
       
        cbo_idnumber.setModel(new Inspector().idnumberCombo().getModel());
        cbo_group.setModel(new Grupo().grpnameCombo().getModel());
        cbo_project.setModel(new Proyekto().projectCombo().getModel());
        cbo_location.setModel(new Proyekto().localeCombo().getModel());
        cbo_nagpatotoo.setModel(new Nasiyasat().nagpatotooCombo().getModel());
        cbo_idnumber.setSelectedItem("");
        cbo_group.setSelectedItem("");
        cbo_project.setSelectedItem("");
        cbo_location.setSelectedItem("");
        cbo_nagpatotoo.setSelectedItem("");
        
    }
    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        
            cancelBtnInvoked();

    }
    private void cancelBtnInvoked(){    
        int choice = JOptionPane.showConfirmDialog(null, "This will cancel/clear all the operations. Youre unsaved data will be lost.\n "
                + "Click \"Ok\" to proceed.","Cancel operation.",2);
        if(choice==0){
            tbtn_edit.setForeground(Color.black);
            tbtn_edit.setText("Edit Record");
            btn_save.setEnabled(true);
            btn_exit.setEnabled(true);
            btn_new.setEnabled(true);
            cbo_project.setEditable(true);
            cbo_location.setEditable(true);
            cbo_idnumber.setEditable(true);
            cbo_group.setEditable(true);
            cbo_nagpatotoo.setEditable(true);
            clearFields();
            cbo_project.setEditable(false);
            cbo_location.setEditable(false);
            cbo_idnumber.setEditable(false);
            cbo_group.setEditable(false);
            cbo_nagpatotoo.setEditable(false);
            
            if(tbtn_edit.isEnabled()){
                    tbtn_edit.setSelected(false);
                    tbtn_edit.setEnabled(false);
                    onEditMode=false;
            }
            if(!tbl_logbook.isEnabled()){
                tbl_logbook.setEnabled(true);
            }
            onAndOffFields(true);
            tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
           saveBtnInvoked();
    }    
    private void saveBtnInvoked(){    
        try{   
            if(allInputsValid()){
                //......
                boolean controlOk = new Nasiyasat().isRecordExist(txt_controlno.getText());
        //JOptionPane.showMessageDialog(null, controlOk);
        if(controlOk){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Control Number is already used.</font> You may use the \"Edit Record\" button \n "
                    + "to update this record or click \"New\" to add new record.");
            txt_controlno.requestFocus();
            return;
        }
                
                //.....
                saveTheData();
                cbo_project.setEditable(true);
                cbo_location.setEditable(true);
                cbo_idnumber.setEditable(true);
                cbo_group.setEditable(true);
                
                clearFields();
                cbo_project.setEditable(false);
                cbo_location.setEditable(false);
                cbo_idnumber.setEditable(false);
                cbo_group.setEditable(false);
                txt_controlno.requestFocus();
               //tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
               //onAndOffFields(true);
                if(tbtn_edit.isEnabled()){
                    tbtn_edit.setSelected(false);
                    tbtn_edit.setEnabled(false);
                    
                }
                if(!tbl_logbook.isEnabled()){
                    tbl_logbook.setEnabled(true);
                }
               
                onAndOffFields(true);
                tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
                }
             initializeCombos();
        }catch(Exception e){}
    }//GEN-LAST:event_btn_saveActionPerformed

    private void txt_controlnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_controlnoKeyTyped
         
        char caracter = evt.getKeyChar();
            if (((caracter < '0') || (caracter > '9'))&& (caracter != '\b'))
                                    evt.consume();
            
        //controlNumberSearch();
        
    }//GEN-LAST:event_txt_controlnoKeyTyped

    private void tbl_logbookKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_logbookKeyReleased
        tableActions();
    }//GEN-LAST:event_tbl_logbookKeyReleased

    private void tbl_logbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_logbookMouseClicked
        tableActions();
    }//GEN-LAST:event_tbl_logbookMouseClicked

    private void tbl_logbookMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_logbookMouseReleased
        tableActions();
    }//GEN-LAST:event_tbl_logbookMouseReleased

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
    
          newBtnInvoked();
    }
    private void newBtnInvoked(){
        
        int choice = JOptionPane.showConfirmDialog(null, "This will clear all the data fields youre unsaved data will be lost.\n "
                + "Click \"Ok\" to proceed.","Confirm to add new record.",2);
        
        if(choice == 0){
            onAndOffFields(true);
            clearFields();
            tbtn_edit.setEnabled(false);
            tbl_logbook.setEnabled(false);
            txt_controlno.requestFocus();
        }
    }//GEN-LAST:event_btn_newActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
       tbl_logbook.setModel(new TableSetters().searchTable(tbl_logbook.getModel(), txt_search.getText()));
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_controlnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_controlnoKeyReleased
        //JOptionPane.showMessageDialog(null,onEditMode);
        if(onEditMode==false){
            controlNumberSearch();
        }
        //new TableSetters().searchTable(tbl_logbook.getModel(), txt_controlno.getText());
    }//GEN-LAST:event_txt_controlnoKeyReleased
    private void onAndOffFields(boolean on){
        if(on){
              txt_controlno.setEditable(on);
              ((JTextField)jdc_petsa.getDateEditor().getUiComponent()).setEditable(on);
              //jdc_petsa.getDateEditor().setEnabled(on);
              cbo_project.setEditable(on);
              cbo_location.setEditable(on);
              cbo_idnumber.setEditable(on);
              cbo_group.setEditable(on);
              txt_fname.setEditable(on);
              txt_mname.setEditable(on);
              txt_lname.setEditable(on);
              txt_kasalukuyan.setEditable(on);
              txt_bilang.setEditable(on);
              txt_nagugol.setEditable(on);
              txt_kulangplano.setEditable(on);
              txt_kulangmateryales.setEditable(on);
              txt_porsyento.setEditable(on);
              txt_dapatipaabot.setEditable(on);
              cbo_nagpatotoo.setEditable(on);
              
        }else{
              txt_controlno.setEditable(on);
              ((JTextField)jdc_petsa.getDateEditor().getUiComponent()).setEditable(on);
              //jdc_petsa.getDateEditor().setEnabled(on);
              cbo_project.setEditable(on);
              cbo_location.setEditable(on);
              cbo_idnumber.setEditable(on);
              cbo_group.setEditable(on);
              txt_fname.setEditable(on);
              txt_mname.setEditable(on);
              txt_lname.setEditable(on);
              txt_kasalukuyan.setEditable(on);
              txt_bilang.setEditable(on);
              txt_nagugol.setEditable(on);
              txt_kulangplano.setEditable(on);
              txt_kulangmateryales.setEditable(on);
              txt_porsyento.setEditable(on);
              txt_dapatipaabot.setEditable(on);
              cbo_nagpatotoo.setEditable(on);
              
        }
    }
    
    private void controlNumberSearch(){
        AllSearch alse = new AllSearch();
        boolean ret = alse.searchAll(txt_controlno.getText());
        //JOptionPane.showMessageDialog(null, ret);
        if(alse.searchAll(txt_controlno.getText())){
            
              //txt_controlno.setText(alse.getControl_no());
              ((JTextField)jdc_petsa.getDateEditor().getUiComponent()).setText(alse.getPetsa());
              cbo_project.setSelectedItem(alse.getProject());
              cbo_location.setSelectedItem(alse.getLocation());
              cbo_idnumber.setSelectedItem(alse.getIdnumber());
              cbo_group.setSelectedItem(alse.getGrupo());
              txt_fname.setText(alse.getFname());
              txt_mname.setText(alse.getMname());
              txt_lname.setText(alse.getLname());
              txt_kasalukuyan.setText(alse.getKasagawa());
              txt_bilang.setText(alse.getBitao());
              txt_nagugol.setText(alse.getNagug());
              txt_kulangplano.setText(alse.getKuplan());
              txt_kulangmateryales.setText(alse.getKumater());
              txt_porsyento.setText(alse.getPorga());
              txt_dapatipaabot.setText(alse.getBadap());
              cbo_nagpatotoo.setSelectedItem(alse.getNagpap());
              lbl_attachments.setText(new Attachments().countAttachments(txt_controlno.getText())+"");
              //onAndOffFields(false);
        }else{
            //->((JTextField)jdc_petsa.getDateEditor().getUiComponent()).setText("");
              cbo_project.setSelectedItem("");
              cbo_location.setSelectedItem("");
              cbo_idnumber.setSelectedItem("");
              cbo_group.setSelectedItem("");
              txt_fname.setText("");
              txt_mname.setText("");
              txt_lname.setText("");
              txt_kasalukuyan.setText("");
              txt_bilang.setText("");
              txt_nagugol.setText("");
              txt_kulangplano.setText("");
              txt_kulangmateryales.setText("");
              txt_porsyento.setText("");
              txt_dapatipaabot.setText("");
              cbo_nagpatotoo.setSelectedItem("");
              lbl_attachments.setText("0");
        }
        
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbo_projectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_projectItemStateChanged
       // try{
        //new TableSetters().searchTable(tbl_logbook.getModel(), cbo_project.getSelectedItem().toString());
       // }catch(Exception e){}
    }//GEN-LAST:event_cbo_projectItemStateChanged

    private void cbo_idnumberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_idnumberItemStateChanged
        
        if(!tbtn_edit.isEnabled()){   
        Inspector ins = new Inspector();
        AllSearch als = new AllSearch();
        try{
        if(ins.findInspector(cbo_idnumber.getSelectedItem().toString())){
            txt_fname.setText(ins.getFirstn());
            txt_mname.setText(ins.getMiddlen());
            txt_lname.setText(ins.getLastn());
            als.searchInspectorID(cbo_idnumber.getSelectedItem().toString());
            cbo_group.setSelectedItem(als.getGrupo());
        }
        }catch(Exception e){
        //JOptionPane.showMessageDialog(null, "Error in cbo_idnumberItemsStateChanged :: "+e);
        }
        }
        
    }//GEN-LAST:event_cbo_idnumberItemStateChanged

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        int ans = JOptionPane.showConfirmDialog(null,"The program will exit. Any unsaved data will be lost.", "Confirm",2);
        if(ans == 0){
           System.exit(0);
        }
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_fileAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fileAttachActionPerformed
        //JFileChooser fc = new JFileChooser();
        String fileToAttach;
        if("".equals(txt_controlno.getText())){
            JOptionPane.showMessageDialog(null, "Choose a record by clicking on the table, to attach a file.");
        }else{
            String contno = txt_controlno.getText();
            JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(null);
        
        if (status == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile(); 
           
            File dest = new File("./src/attachments/"+contno+"_"+selectedFile.getName());//"C:\\PLB\\InspectorLogbook\\src\\attachments\\"+contno+"_"+selectedFile.getName()
            int ow = 0;
            if(dest.exists()){
                
                //JOptionPane.showMessageDialog(null, "file already exists!");
                int ans = JOptionPane.showConfirmDialog(null,selectedFile.getName()+" already exist. \n "
                        + "overwrite the file?","Confirm...", JOptionPane.OK_CANCEL_OPTION);
                if(ans == 0){
                     dest.delete();
                     ow=1;
                }else{
                    return;
                }
            }
            try {
                //COPY FILE AND SAVE TO THE ALLOCATED FOLDER NAMED "Attachments"
                //THEN ASSIGN THE PATH AS A STRING FILE NAME TO VARIABLE "dest"
                //ASSIGN "dest" TO "fileToAttach",- A VARIABLE USED FOR PREVIEWING 
                //AND SAVING THE ATTACHED FILE...>
              
                    fileToAttach = dest.toString();
                
                    Files.copy(selectedFile.toPath(), dest.toPath());
                    //JOptionPane.showMessageDialog(null, "File attached successfully.");//+dest.toPath());
                if(ow == 0){
                    new Attachments().addAttachment(contno, selectedFile.getName(), fileToAttach);
                }else{
                    JOptionPane.showMessageDialog(null, "File attached successfully.");
                }
                Attachments att = new Attachments();
                int cnt = att.countAttachments(contno);
                lbl_attachments.setText(cnt+"");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error saving Attachment. "+e);
            }
            
        }
        }
        
    }//GEN-LAST:event_btn_fileAttachActionPerformed

    private void lbl_attachmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_attachmentsMouseClicked
       // frame_attachment frat = new frame_attachment();
       //    frat.showAttachments(taggedControlNo);
       //    frat.setVisible(true);
        int val = Integer.parseInt(lbl_attachments.getText());
        if(val > 0){
        AttachmentDialog ad = new AttachmentDialog(this, true);
        ad.showAttachments(taggedControlNo);
        ad.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "The file has no attachment.");
        }
        lbl_attachments.setText(new Attachments().countAttachments(taggedControlNo)+"");
        //JOptionPane.showMessageDialog(null, lbl_attachments.getText());
    }//GEN-LAST:event_lbl_attachmentsMouseClicked

    private void tbtn_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtn_checkActionPerformed
        int len = tbl_logbook.getRowCount();
        if(tbtn_check.isSelected()){          
           for(int i = 0; i < len; i++){
                tbl_logbook.getModel().setValueAt(true, i, 5);
           }
       }else if(!tbtn_check.isSelected()){
           for(int i = 0; i < len; i++){
               tbl_logbook.getModel().setValueAt(false, i, 5);
           }
       }
    }//GEN-LAST:event_tbtn_checkActionPerformed

    private void btn_deleteRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteRecordActionPerformed
        int tabLen=tbl_logbook.getRowCount();
        int fileCount=0;
        String control="";
        int fileId=0;
        try{            
            for(int i = 0; i < tabLen; i++){            
                
                if((boolean)tbl_logbook.getValueAt(i, 5)==true){
                    fileId=Integer.parseInt(tbl_logbook.getValueAt(i, 1).toString());
                    control=tbl_logbook.getValueAt(i, 7).toString();
                    fileCount++;
                    
                }
        }
        
        if(fileCount > 1){
            JOptionPane.showMessageDialog(null, "For file safety  we only allow to delete one file at a time.");
        }else if(fileCount == 1){
            
            int y = JOptionPane.showConfirmDialog(null,"<html>This record will be deleted permanently.<br> "
                + "Continue to <font color=red>Delete</font>?","Confirm...",0);
            
            if(y==0){
                new Nasiyasat().deleteNasiyasat(fileId);
                new Attachments().deleteAttachment(control);
                
                tbl_logbook.setModel(new TableSetters().LogbookTable(tbl_logbook.getModel()));
                clearFields();
            }
            
        }else if(fileCount < 1){
            JOptionPane.showMessageDialog(null, "Choose a file through the checkbox at the left-side of the table to delete.");
        }
        }catch(HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_deleteRecordActionPerformed

    private void btn_cancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_cancelKeyPressed
        int keyin = evt.getKeyCode();
        if(keyin == 10){
            cancelBtnInvoked();
        }
    }//GEN-LAST:event_btn_cancelKeyPressed

    private void btn_saveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_saveKeyPressed
        int keyin = evt.getKeyCode();
        if(keyin == 10){
            saveBtnInvoked();
        }
    }//GEN-LAST:event_btn_saveKeyPressed

    private void btn_exitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_exitKeyPressed
        int ans = JOptionPane.showConfirmDialog(null,"The program will exit. Any unsaved data will be lost.", "Confirm",2);
        if(ans == 0){
           System.exit(0);
        }
    }//GEN-LAST:event_btn_exitKeyPressed

    private void tbtn_editKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbtn_editKeyPressed
        int keyin = evt.getKeyCode();
        if(keyin == 10){
            editBtnInvoked();
        }
    }//GEN-LAST:event_tbtn_editKeyPressed

    private void btn_newKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_newKeyPressed
        int keyin = evt.getKeyCode();
        if(keyin == 10){
           newBtnInvoked();
        }
    }//GEN-LAST:event_btn_newKeyPressed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        
        DRDataSource dataSource = new DRDataSource( "PETSA","NAGSIYASAT", "GRUPO", "PROYEKTO"); //
        int tabLen = tbl_logbook.getRowCount();
        
        for(int i = 0; i < tabLen; i++){            
           
                if((boolean)tbl_logbook.getValueAt(i, 5)==true){
                
                    dataSource.add(tbl_logbook.getValueAt(i, 6).toString(),tbl_logbook.getValueAt(i, 9).toString(),tbl_logbook.getValueAt(i, 8).toString(),
                                   tbl_logbook.getValueAt(i, 10).toString());
                }
         
        }
       try{
            PrintSelection ps = new PrintSelection();
            ps.setDdts(dataSource);       
            
            if(dataSource.next()){ 
                dataSource.moveFirst();
                JasperReportBuilder report = ps.selectionBuilder();
                     report.show(false);
            }else{
                JOptionPane.showMessageDialog(null, "Choose a file to print through the checkbox at the left-side of the table.");
            }
        }catch(DRException|JRException e){
              JOptionPane.showMessageDialog(null,"SummaryBF::option::"+e);
        }
        
        
        
    }//GEN-LAST:event_btn_printActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tbl_logbook.setModel(new TableSetters().searchTable(tbl_logbook.getModel(), txt_search.getText()));
    }//GEN-LAST:event_jButton1ActionPerformed
    private void clearFields(){
        ((JTextField)jdc_petsa.getDateEditor().getUiComponent()).setText("");
        txt_bilang.setText("");
        txt_controlno.setText("");
        txt_dapatipaabot.setText("");
        txt_fname.setText("");
        txt_kasalukuyan.setText("");
        txt_kulangmateryales.setText("");
        txt_kulangplano.setText("");
        txt_lname.setText("");
        txt_mname.setText("");
        txt_nagugol.setText("");
        txt_porsyento.setText("");
        cbo_group.setSelectedItem("");
        cbo_idnumber.setSelectedItem("");
        cbo_location.setSelectedItem("");
        cbo_nagpatotoo.setSelectedItem("");
        cbo_project.setSelectedItem("");
    }
    
    private void saveTheData(){
        
        int groupID = 0;
        int proyektoID = 0;
        int inspectorID = 0;
        try{
        //instantiate then save Inspector..................................................
            Inspector insp = new Inspector();           
            insp.setIdnum(cbo_idnumber.getSelectedItem().toString());
            insp.setFirstn(txt_fname.getText());
            insp.setMiddlen(txt_mname.getText());
            insp.setLastn(txt_lname.getText()); 
            insp.addInspector();
            inspectorID=insp.getInsid();
        /*    
            JOptionPane.showMessageDialog(null, "idnum = "+insp.getIdnum()+"\n"
                    + "fn => "+insp.getFirstn()+"\n mn => "+insp.getMiddlen()+"\n ln => "+insp.getLastn()+"\n"
                            + "insId => "+inspectorID);
        */    
        //instantiate then save Grupo......................................................   
            Grupo grp = new Grupo();
            grp.setGrpName(cbo_group.getSelectedItem().toString());
            grp.addNewGroup();
            groupID=grp.getGrpId();
        /*    
            JOptionPane.showMessageDialog(null, "grupo => "+grp.getGrpName()+"\n grpId => "+groupID);
        */    
        //instantiate then save Proyekto...................................................
            //JOptionPane.showMessageDialog(null, "pn => "+cbo_project.getSelectedItem().toString()+ "\n pl => "+cbo_location.getSelectedItem().toString());//+ "\n proyID => "+proyektoID);
            Proyekto proy = new Proyekto();
            proy.setPronam(cbo_project.getSelectedItem().toString());
            proy.setProloca(cbo_location.getSelectedItem().toString());
            proy.addProyekto();
            proyektoID = proy.getProid();
        /*    
            JOptionPane.showMessageDialog(null, "pn => "+proy.getPronam()+ "\n pl => "+proy.getProloca()+ "\n proyID => "+proyektoID);
         */   
        //instantiate then save Nasiyasat..................................................
            Nasiyasat siya = new Nasiyasat();
            siya.setContno(txt_controlno.getText());
            siya.setPetsa(((JTextField)jdc_petsa.getDateEditor().getUiComponent()).getText());
            siya.setCurrwork(txt_kasalukuyan.getText());
            siya.setMpr_cnt(txt_bilang.getText());
            siya.setGugol(txt_nagugol.getText());
            siya.setKulangPlano(txt_kulangplano.getText());
            siya.setKulangMateryals(txt_kulangmateryales.getText());
            siya.setPercentComp(txt_porsyento.getText());
            siya.setNapuna(txt_dapatipaabot.getText());
            siya.setPid(proyektoID);
            siya.setInid(inspectorID);
            siya.setNagpatotoo(cbo_nagpatotoo.getSelectedItem().toString());
            siya.setGid(groupID);
            siya.addSiyasat();
        /*   JOptionPane.showMessageDialog(null, "contNo = "+siya.getContno()+"\n petsa = "+siya.getPetsa()+"\n"
                   + "currentWork = "+siya.getCurrwork()+"\n mprCnt = "+siya.getMpr_cnt()+"\n gugol = "+siya.getGugol()+"\n"
                           + " kulangNaPlano = "+siya.getKulangPlano()+"\n kulangNaMateryales = "+siya.getKulangMateryals()+"\n"
                                   + "percentComp = "+siya.getPercentComp()+"\n napuna = "+siya.getNapuna()+"\n pid = "+siya.getPid()+"\n"
                                           + "Inid = "+siya.getInid()+"\n nagpatotoo = "+siya.getNagpatotoo()+"\n gid = "+siya.getGid()+"\n"
                                                   + "siyid = "+siya.getNid());
        */    
           JOptionPane.showMessageDialog(null, "Successfully saved!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error initializing accessors...  "+ e);
        }
    }    
    
    
    private boolean allInputsValid(){
        
        boolean isValid = false;
        if(txt_controlno.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html> <font color = red> Control no.</font> is required.");
            txt_controlno.requestFocus();
        }else if(((JTextField)jdc_petsa.getDateEditor().getUiComponent()).getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html> <font color = red> Petsa </font> is required.");
            jdc_petsa.requestFocus();
        }else if((cbo_project.getSelectedItem().toString()).trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Project </font> is required.");
            cbo_project.requestFocus();
        }else if((cbo_location.getSelectedItem().toString()).trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html> <font color = red> Location </font> is required.");
            cbo_location.requestFocus();
        }else if((cbo_idnumber.getSelectedItem().toString()).trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> ID Number </font> is required.");
            cbo_idnumber.requestFocus();
        }else if(txt_fname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> First name </font> is required.");
            txt_fname.requestFocus();
        }else if(txt_lname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Last name </font> is required.");
            txt_lname.requestFocus();
        }else if(txt_kasalukuyan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Kasalukuyang gawain </font> is required.");
            txt_kasalukuyan.requestFocus();
        }else if(txt_bilang.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"<html><font color = red> Bilang ng tao </font> is required.");
            txt_bilang.requestFocus();
        }else if(txt_nagugol.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html> <font color = red> Nagugol </font> is required.");
            txt_nagugol.requestFocus();
        }else if(txt_kulangplano.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html> <font color = red> Kulang na plano </font is required.");
            txt_kulangplano.requestFocus();
        }else if(txt_kulangmateryales.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Kulang na materyales </font> is required.");
            txt_kulangmateryales.requestFocus();
        }else if(txt_porsyento.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Porsyento ng gawain </font> is required.");
            txt_porsyento.requestFocus();
        }else if(txt_dapatipaabot.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "<html><font color = red> Bagay na dapat ipa-abot sa Tanggapan </font> is required.");
            txt_dapatipaabot.requestFocus();
        
        }else{
            isValid = true;
        }
        
        return isValid;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogbookMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogbookMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogbookMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogbookMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogbookMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_deleteRecord;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_fileAttach;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> cbo_group;
    private javax.swing.JComboBox<String> cbo_idnumber;
    private javax.swing.JComboBox<String> cbo_location;
    private javax.swing.JComboBox<String> cbo_nagpatotoo;
    private javax.swing.JComboBox<String> cbo_project;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser jdc_petsa;
    private javax.swing.JLabel lbl_attachments;
    private javax.swing.JTable tbl_logbook;
    private javax.swing.JToggleButton tbtn_check;
    private javax.swing.JToggleButton tbtn_edit;
    private javax.swing.JTextField txt_bilang;
    private javax.swing.JTextField txt_controlno;
    private javax.swing.JTextArea txt_dapatipaabot;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextArea txt_kasalukuyan;
    private javax.swing.JTextArea txt_kulangmateryales;
    private javax.swing.JTextArea txt_kulangplano;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_mname;
    private javax.swing.JFormattedTextField txt_nagugol;
    private javax.swing.JTextField txt_porsyento;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
