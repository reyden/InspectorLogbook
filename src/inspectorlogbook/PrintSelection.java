/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspectorlogbook;

import java.awt.Color;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.MarginBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import static net.sf.dynamicreports.report.builder.style.Styles.pen1Point;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author 19950014
 */
public class PrintSelection {
    DRDataSource ddts = new DRDataSource();

    public DRDataSource getDdts() {
        return ddts;
    }

    public void setDdts(DRDataSource ddts) {
        this.ddts = ddts;
    }
    public JasperReportBuilder selectionBuilder() throws DRException{
           JasperReportBuilder selectionReport = report();
           
           MarginBuilder margin = DynamicReports.margin().setLeft(20)
                   .setTop(7)
                   .setRight(20);
           
           StyleBuilder boldStyle  = stl.style().bold();
           StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
           StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                   .setBorder(stl.pen1Point())
                   .setBackgroundColor(Color.lightGray);
           
           StyleBuilder titleStyle = DynamicReports.stl.style()
                   .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT)
                   .setBold(Boolean.TRUE)
                   .setFontSize(10)                   
                   .setPadding(2)
                   .setForegroundColor(Color.BLACK);
        /*   StyleBuilder centerStyle = DynamicReports.stl.style()
                   .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                   .setBorder(pen1Point())
                   .setFontSize(11)                   
                   .setPadding(2);*/
           StyleBuilder tableFont = DynamicReports.stl.style()
                   .setFontSize(11)
                   .setBorder(pen1Point())
                   .setPadding(2);
        /*   StyleBuilder remarkFont = DynamicReports.stl.style()
                   .setFontSize(11)
                   .setBorder(pen1Point())
                   .setPadding(2);*/
           
           TextColumnBuilder<String> petsaCol = col.column("PETSA", "PETSA",
                   type.stringType()).setStyle((titleStyle));//.setFixedWidth(170)
           TextColumnBuilder<String> nasiCol = col.column("NAGSIYASAT", "NAGSIYASAT",
                   type.stringType()).setStyle(tableFont.setRightBorder(pen1Point()))//(titleStyle)
                   .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT); //.setFixedWidth(140)
           
           TextColumnBuilder<String> gruCol = col.column("GRUPO",
                   "GRUPO", type.stringType())//.setFixedWidth(160)
                   .setStyle(tableFont.setRightBorder(pen1Point()));
           TextColumnBuilder<String> proCol = col.column("PROYEKTO",
                   "PROYEKTO", type.stringType())//.setFixedWidth(120)
                   .setStyle(tableFont.setRightBorder(pen1Point()));
          
          selectionReport
                   
                   .setColumnTitleStyle(columnTitleStyle)
                   .columns( petsaCol, nasiCol, gruCol, proCol)
                   .setHighlightDetailEvenRows(false)
                   .setHighlightDetailOddRows(false)
                   .groupBy(petsaCol,proCol)
                   .title(Components.horizontalFlowList(Components
                   .text("\n\n COMPANY XXX \n ENGINEERING AND CONSTRUCTION DEPARTMENT")).setStyle(boldCenteredStyle))
                   .title(Components.horizontalFlowList(Components
                   .text("\n\nINSPECTOR LOGBOOK")))//.setDimension(8, 24)
                   .setPageFormat(PageType.LEGAL, PageOrientation.PORTRAIT)
                   .setPageMargin(margin)                   
                   .pageFooter(Templates.footerComponent)
                   .setDataSource(getDdts());
           
           
           return selectionReport;
    }
    
}
