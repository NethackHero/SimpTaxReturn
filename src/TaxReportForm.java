
//import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiebing
 */
public class TaxReportForm extends JFrame {
    //private javax.swing.JTable tblTable;
    String[][] arrayTaxTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTable;
    
    public TaxReportForm()
    {
        getList();
        formatTaxValues(arrayTaxTable);
        initComponents();
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        JPanel p1 = new JPanel();
//        
//        class TaxTableCellRenderer extends DefaultTableCellRenderer
//        {
//            //http://stackoverflow.com/questions/14546968/swing-is-it-possible-to-set-the-font-color-of-specific-text-within-a-jtable/14547140#14547140
//            //this link is on how to make JTable cell text foreground RED
//        }
//        
////        tblTable = new javax.swing.JTable();
////        tblTable.setModel(new javax.swing.table.DefaultTableModel(
////            new Object [][] {
////                {null, null, null, null, null, null, null}
////            },
////            new String [] {
////                "Name", "Time", "Taxable Income", "Tax Withheld", "Income Tax", "Medicare Levy", "Tax Return"
////            }
////        ));
//        
//        String[] columnNames = {
//                "Name", "Time", "Taxable Income", "Tax Withheld", "Income Tax", "Medicare Levy", "Tax Return"
//            };
//        getList();
//        
//        
////= new String[2][7];
////        tableTest[0][0] = "Tony";
////        tableTest[0][1] = "Tony2";
////        tableTest[0][2] = "Tony3";
////        tableTest[0][3] = "Tony4";
////        tableTest[0][4] = "Tony5";
////        tableTest[0][5] = "Tony6";
////        tableTest[0][6] = "Tony7";
////        tableTest[1][0] = "Tony";
////        tableTest[1][1] = "Tony2";
////        tableTest[1][2] = "Tony3";
////        tableTest[1][3] = "Tony4";
////        tableTest[1][4] = "Tony5";
////        tableTest[1][5] = "Tony6";
////        tableTest[1][6] = "Tony7";
//        
//        JTable table = new JTable(tableTest, columnNames);
//        table.getColumnModel().getColumn(2).setPreferredWidth(110);
//        table.getColumnModel().getColumn(3).setPreferredWidth(80);
//        JScrollPane pane = new JScrollPane(table);
//        p1.add(pane);
//        setContentPane(p1);
    }
    
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            arrayTaxTable,
            new String []
            {
                "Name", "Time", "Taxable Income", "Tax Withheld", "Income Tax", "Medicare Levy", "Tax Return"
            }
        ));
        jScrollPane1.setViewportView(tblTable);
        tblTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        pack();
    }
    
    private void getList()
    {
        arrayTaxTable = new String[GlobalVar.recordList.size()][7];
        for(int i = 0; i < GlobalVar.recordList.size(); i++)
        {
            TaxReturn tax = (TaxReturn) GlobalVar.recordList.get(i);
            arrayTaxTable[i][0] = tax.getName();
            arrayTaxTable[i][1] = tax.getTime();
            arrayTaxTable[i][2] = Double.toString(tax.getTaxableIncome());
            arrayTaxTable[i][3] = Double.toString(tax.getTaxWithheld());
            arrayTaxTable[i][4] = Double.toString(tax.getIncomeTax());
            arrayTaxTable[i][5] = Double.toString(tax.getMedicareLevy());
            arrayTaxTable[i][6] = Double.toString(tax.getTaxReturn());
        }
    }
    
    
    
    private String formatStringCurrency(String str)
    {
        double curr = Double.parseDouble(str);
        return str.format("%,.2f", curr);
    }
    
    private String formatStringPercentage(String str)
    {
        double perc = Double.parseDouble(str);
        return str.format("%.2f%%", perc);
    }
    
    private void formatTaxValues(String[][] arr)
    {
        for(int i = 0; i<arr.length; i++)
        {
//            arr[i][2] = formatStringCurrency(arr[i][2]);
//            arr[i][3] = formatStringPercentage(arr[i][3]);
//            arr[i][4] = formatStringCurrency(arr[i][4]);
//            arr[i][5] = formatStringCurrency(arr[i][5]);
//            arr[i][6] = formatStringCurrency(arr[i][6]);
            int j = 2;
            while(j < 7)
            {
                arr[i][j] = formatStringCurrency(arr[i][j]);
                j++;
            }
            
            if(arr[i][6].startsWith("-"))
            {
                arr[i][6] = arr[i][6].replace("-", "Tax Owing ");
            }
        }
    }
    
    public static void main(String args[])
    {
        TaxReportForm form = new TaxReportForm();
        form.setBounds(200, 200, 800, 400);
        form.setVisible(true);
    }
}
