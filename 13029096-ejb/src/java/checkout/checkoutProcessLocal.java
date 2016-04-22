/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkout;

import java.math.BigDecimal;
import javax.ejb.Local;

/**
 *
 * @author Enda
 */
@Local
public interface checkoutProcessLocal {
    
    public void setCancelLog();
     public BigDecimal getTotalCost();
     public String checkQty();
}
