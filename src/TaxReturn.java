
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony Tan
 */
public class TaxReturn
{
    private String name;
    private double annualIncome;
    private double taxWithheldPercent;
    private double totalDeductibleExpenses;
    private String time;
    
    private double taxWithheld;
    private double taxableIncome;
    private double incomeTax;
    private double medicareLevy;
    private double taxReturn;
    private double actualTaxRate;
    
    private Date date;
    
    public TaxReturn(String name, String time, double annual, double taxWithheldPerc, double totalDeductible, Date date)
    {
        this.name = name;
        this.annualIncome = annual;
        this.taxWithheldPercent = taxWithheldPerc;
        this.totalDeductibleExpenses = totalDeductible;
        this.time = time;
        this.date = date;
        
        calculateTaxWithheld();
        calculateTaxableIncome();
        calculateIncomeTax();
        calculateMedicareLevy();
        calculateTaxReturnOrOwing();
        calculateActualTaxRate();
    }
    
    public TaxReturn()
    {
        this.annualIncome = 0;
        this.taxWithheldPercent = 0;
        this.totalDeductibleExpenses = 0;
    }
    
    public void addToDatabase()
    {
        TaxDatabase tax = new TaxDatabase();
        tax.addTaxReturn(name, time, annualIncome, taxWithheldPercent, totalDeductibleExpenses, formatDate(date));
    }
    
    private String formatDate(Date date)
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sqlDate = formatDate.format(date);
        return sqlDate;
    }

    private void calculateTaxWithheld()
    {
        this.taxWithheld = annualIncome * (taxWithheldPercent/100);
    }

    private void calculateTaxableIncome()
    {
        this.taxableIncome = annualIncome - totalDeductibleExpenses;
    }

    private void calculateTaxReturnOrOwing()
    {
         this.taxReturn = taxWithheld - incomeTax - medicareLevy;
    }

    private void calculateIncomeTax()
    {
        double incTax = 0;
        if (taxableIncome>=1 && taxableIncome <= 18200)
        {
            incTax = 0;
        }
        else if (taxableIncome >= 18201 && taxableIncome <= 37000)
        {
            incTax = (taxableIncome - 18200) * .19;
        }
        else if (taxableIncome >=37001 && taxableIncome <= 80000)
        {
            incTax = (3572 + (taxableIncome - 37000) * .325);
        }
        else if (taxableIncome >=80001 && taxableIncome <= 180000)
        {
            incTax = (17547 + (taxableIncome - 80000) * .37);
        }
        else if (taxableIncome > 180000)
        {
            incTax = (54547 + (taxableIncome - 180000) * .45);
        }
        else{}
        this.incomeTax = incTax;
    }

    private void calculateMedicareLevy()
    {
        double medLevy = 0;
        if(taxableIncome < 19404)
        {
            medLevy = 0;
        }
        else if(taxableIncome >= 19404 && taxableIncome <= 22828)
        {
            medLevy = (taxableIncome -19404) * .1;
        }
        else if(taxableIncome >22828)
        {
            medLevy = taxableIncome * .015;
        }
        else{}
        this.medicareLevy = medLevy;
    }

    private void calculateActualTaxRate()
    {
        this.actualTaxRate = (incomeTax + medicareLevy)/taxableIncome * 100;
    }

    public double getTaxReturn()
    {
        return taxReturn;
    }

    public double getIncomeTax()
    {
        return this.incomeTax;
    }

    public double getMedicareLevy()
    {
        return this.medicareLevy;
    }

    public double getActualTaxRate()
    {
        return this.actualTaxRate;
    }

    public double getTaxableIncome()
    {
        return this.taxableIncome;
    }

    public double getTaxWithheld()
    {
        return this.taxWithheld;
    }

        
        
    public String getTime()
    {
        return time;
    }

    public String getName()
    {
        return name;
    }

    public double getAnnualIncome()
    {
        return annualIncome;
    }
    
    public void setAllTaxValues(double an, double perc, double exp)
    {
        this.annualIncome = an;
        this.taxWithheldPercent = perc;
        this.totalDeductibleExpenses = exp;
        
        calculateTaxWithheld();
        calculateTaxableIncome();
        calculateIncomeTax();
        calculateMedicareLevy();
        calculateTaxReturnOrOwing();
        calculateActualTaxRate();
    }
}
 