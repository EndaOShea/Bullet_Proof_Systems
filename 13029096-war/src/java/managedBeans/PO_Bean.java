/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import PO.CreatePOLocal;
import log.LoggingLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Enda
 */
@Named(value = "pO_Bean")
@RequestScoped
public class PO_Bean {

    @EJB
    private CreatePOLocal createPO;

    //Order Num (int)
    //Customer ID (int)
    //Product ID (int)
    //Quantity (small int)
    //Shipping Cost (Big Decimal)
    //Sales Date (Date)
    //Shiiping Date (Date)
    //Freight Company (String)
    
    
    public PO_Bean() {
    }
    
    public void createPO()
    {
    createPO.createPO();
    }
    
    
    
}
