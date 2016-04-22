/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ShoppingCartEjb.ShoppingCartLocal;
import checkout.checkoutProcessLocal;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 *
 * @author Enda
 */
@Named(value = "checkoutBean")
@RequestScoped
public class CheckoutBean {

    @EJB
    private ShoppingCartLocal shoppingCart;
    
    private BigDecimal totalCost;
    private String message = "Please select your required quantity for the items below.";
    

    @EJB
    private checkoutProcessLocal process;
    
    
    
    public CheckoutBean() {  
        totalCost = new BigDecimal(0);
    }
    
    
    public String logCancel()
    {
        process.setCancelLog();
        return "cart";
    }
    
     public String logProcess()
    {
        String result = process.checkQty();
        
        if(!result.equals("confirmation"))
        {
            setMessage("<b>That quantity is no longer available for "+result+"."
                        +"<br/>Please select another quantity.</b><br/>");
            result = "cart";
        }
        
        return result;
    }
     
     public void calculateCost(){
         this.totalCost = process.getTotalCost();
     }
     

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    

   
     
     

    
    
    
    
    
    
    
    
}
