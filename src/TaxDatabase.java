
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiebing
 */
public class TaxDatabase {
    public static Connection connection = null;
    public TaxDatabase()
    { 
//        connection = getConnection();
//        if (connection != null)
//        {
//            System.out.println("Connection has been started.\n");
//        }
    }
    public Connection getConnection()
    {
        try
        {
            // if necessary, set the home directory for Derby
            //String dbDirectory = "C:/xampp/mysql/data";
            //System.setProperty("derby.system.home", dbDirectory);
            // set the db url, username, and password
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);
            return connection;
        }
        catch (SQLException e)
        {
            for (Throwable t : e)
                t.printStackTrace();   // for debugging
            return null;
        }
        catch (ClassNotFoundException e)
        {
            e.getStackTrace();
            return null;
        }
    }

    public boolean disconnect()
    {
        try
        {
            // On a successful shutdown, this throws an exception
            String shutdownURL = "jdbc:mysql:;shutdown=true";
            DriverManager.getConnection(shutdownURL);
        }
        catch (SQLException e)
        {
            if (e.getMessage().equals("system shutdown."))
                return true;
        }
        return false;
    }
    
    public void addTaxReturn(String name, String time, double annualIncome, double taxWith, double totaldeductible, String date)
    {
        try(Statement statement = connection.createStatement())
        {
            String insertStatement = "INSERT INTO simple_tax_return.taxreturn (name, time, annualincome, taxwithheld"
                    + ", totaldeductibleexpenses, date) VALUES ('" + name + "', '" + time + "', "
                    + annualIncome + ", " + taxWith + ", " + totaldeductible + ", '" + date +  "');";
            statement.executeUpdate(insertStatement);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void getData()
    {
        try(Statement statement = TaxDatabase.connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM simple_tax_return.taxreturn"))
        {
            while(rs.next())
            {
                String name = rs.getString("name");
                String time = rs.getString("time");
                double annualInc = rs.getDouble("annualincome");
                double taxWith = rs.getDouble("taxwithheld");
                double totalDeduct = rs.getDouble("totaldeductibleexpenses");
                Date date = rs.getDate("date");
                TaxReturn tax = new TaxReturn(name, time, annualInc, taxWith, totalDeduct, date);
                GlobalVar.recordList.add(tax);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void getCustomerData()
    {
        try(Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM simple_tax_return.customer"))
        {
            while(rs.next())
            {
                int custId = rs.getInt("idcustomer");
                String tfn = rs.getString("taxFileNumber");
                String surname = rs.getString("surname");
                String firstName = rs.getString("firstName");
                clsName name = new clsName(surname, firstName);
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String streetNo = rs.getString("streetNo");
                String streetName = rs.getString("streetName");
                String suburb = rs.getString("suburb");
                String postcode = rs.getString("postcode");
                clsAddress address = new clsAddress(streetNo, streetName, suburb, postcode);
                
                clsCustomer customer = new clsCustomer(tfn, name, dob, gender.charAt(0), phone, address, custId);
                GlobalVar.customerList.add(customer);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something wrong in loading in the data of customers");
        }
    }
    
    public int getLastRowCustomerId()
    {
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = statement.executeQuery("SELECT * FROM simple_tax_return.customer"))
        {
            rs.last();
            return rs.getInt("idcustomer");
        }
        catch(SQLException e)
        {
            e.getStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong retrieving Id");
            return 0;   
        }
    }
    
    public void addCustomer(String tfn, String surname, String firstName, String dob, String phone, char gender, String streetNo,
            String streetName, String suburb, String postCode)
    {
        try(Statement statement = connection.createStatement())
        {
            System.out.println("This is at least going into the try block");
            String insertStatement = "INSERT INTO simple_tax_return.customer (taxFileNumber, surname, firstName, dob, phone, gender, streetNo, streetName, suburb, postcode) "
                    + "VALUES ('" + tfn + "', '" + surname + "', '"
                    + firstName + "', '" + dob + "', '" + phone + "', '" + gender + "', '" + streetNo + "', '" + streetName + "', '"
                    + suburb + "', '" + postCode + "');";
            
            System.out.println(insertStatement);
            System.out.println("after the string");
            statement.executeUpdate(insertStatement);
            System.out.println("after executing the sql statement");
            JOptionPane.showMessageDialog(null, "Customer added");
        }
        catch(SQLException e)
        {
            e.getStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong adding to database");
        }
    }
//    public void insertTaxReturn()
//    {
//        try(Statement statement = )
//    }
}
