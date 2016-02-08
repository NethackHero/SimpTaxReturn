
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiebing
 */
public class clsCustomer {
    private String tfn;
    private clsName name;
    private Date dob;
    private char gender;
    private String phone;
    private clsAddress address;
    int customerId;
    
    TaxDatabase tax;
    
    clsCustomer(String tfn, clsName name, Date dob, char gender, String phone, clsAddress address)
    {
        this.tfn = tfn;
        this.name = name;
//        name = setName(surname, firstName);
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
//        address = setAddress(streetNo, streeName, suburb, postCode);
        this.address = address;
        
    }
    clsCustomer(String tfn, clsName name, Date dob, char gender, String phone, clsAddress address, int customerId)
    {
        this.tfn = tfn;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.customerId = customerId;
    }
    
    public void retrieveId()
    {
        customerId = tax.getLastRowCustomerId();
    }
    
    public void addToDatabase()
    {
        tax = new TaxDatabase();
        tax.addCustomer(tfn, name.getSurname(), name.getFirstName(), formatDate(dob), phone, gender,
                address.getStreetNo(), address.getStreetName(), address.getSuburb(), address.getPostCode());
        retrieveId();
    }
    
    private String formatDate(Date date)
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sqlDate = formatDate.format(date);
        return sqlDate;
    }

    /**
     * @return the tfn
     */
    public String getTfn() {
        return tfn;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    
    public int getCustomerId()
    {
        return customerId;
    }
    
    public clsName getName()
    {
        return name;
    }
    
    public clsAddress getAddress()
    {
        return address;
    }
    
    public void setCustomerId(int id)
    {
        this.customerId = id;
    }
    
//    private clsName setName(String surname, String firstName)
//    {
//        clsName cname = new clsName();
//        cname.setSurname(surname);
//        cname.setFirstName(firstName);
//        return cname;
//    }
//    
//    private clsAddress setAddress(String streetNo, String streetName, String suburb, String postCode)
//    {
//        clsAddress cAddress = new clsAddress();
//        cAddress.setStreetNo(streetNo);
//        cAddress.setStreetName(streetName);
//        cAddress.setSuburb(suburb);
//        cAddress.setPostCode(postCode);
//        return cAddress;
//    }
}
