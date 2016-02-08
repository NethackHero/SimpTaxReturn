

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiebing
 */
public class Validation {
    public Boolean isBlank(String input)
    {
        Boolean valid = false;
        if(input.length() == 0)
        {
            valid = true;
        }
        return valid;
    }
    
    public Boolean isNumeric(String input)
    {
        try
        {
            double d = Double.parseDouble(input);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    public Boolean isPositive(String input)
    {
        Boolean valid = false;
        double d = Double.parseDouble(input);
        if (d<0)
        {
            valid = false;
        }
        else
        {
            valid = true;
        }
        return valid;
    }
    /**
     * 
     * @param input to be tested whether it is alphabetical (allowing spaces)
     * @return true or false to satisfy conditions
     */
    public Boolean isName(String input) //isName has only alphabets and spaces
    {
        char c;
        int i=0;
        Boolean valid = true;
        while(i<input.length() && valid == true)
        {
            c = input.charAt(i);
            if(!(Character.isLetter(c) || c == ' ' || c == '\'' || c == '-'))
            {
                valid = false;
            }
            i++;
        }
        return valid;
    }
    
    public Boolean isWithinRange(double min, double max, String input)
    {
        double d = Double.parseDouble(input);
        if(d<min || d>max)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public Boolean isWithinTextLength(String str, int min, int max)
    {
        int length = str.length();
        if(length > min && length < max)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
