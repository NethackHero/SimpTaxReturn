/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiebing
 */
public class clsAddress {
    private String streetNo;
    private String streetName;
    private String suburb;
    private String postCode;
    
    public clsAddress(String streetNo, String streetName, String suburb, String postcode)
    {
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.suburb = suburb;
        this.postCode = postcode;
    }

    /**
     * @return the streetNo
     */
    public String getStreetNo() {
        return streetNo;
    }

    /**
     * @param streetNo the streetNo to set
     */
    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @param suburb the suburb to set
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
