
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class TaxHelpForm extends JFrame
{
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTable;
    
    String[][] taxArray;
    int arrayLength = 1000000/1000;
    int numberOfColumns = 4;
    
    public TaxHelpForm()
    {
        generateTaxArray();
        initComponents();
    }
    
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            taxArray,
            new String []
            {
                "Income", "Income Tax", "Medicare Levy", "Actual %"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    
    private void generateTaxArray()
    {
        double income = 0;
        TaxReturn calc = new TaxReturn();
        
        taxArray = new String[arrayLength][numberOfColumns];
        for(int i = 0; i < arrayLength; i++)
        {
            income += 1000;
            calc.setAllTaxValues(income, 20, 0);
            taxArray[i][0] = String.format("$%,.2f",income);
            taxArray[i][1] = String.format("$%,.2f",calc.getIncomeTax());
            taxArray[i][2] = String.format("$%,.2f",calc.getMedicareLevy());
            taxArray[i][3] = String.format("%.2f%%",calc.getActualTaxRate());
        }
    }
    
    public static void main(String args[])
    {
        TaxHelpForm form = new TaxHelpForm();
        form.setBounds(200, 200, 800, 400);
        form.setVisible(true);
    }
}
