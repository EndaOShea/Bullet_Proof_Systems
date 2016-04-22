/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PO;

import ShoppingCartEjb.ShoppingCartLocal;
import entity.Customer;
import entity.PurchaseOrder;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import log.LoggingLocal;


/**
 *
 * @author Enda
 */
@Stateless
public class CreatePO implements CreatePOLocal {

    @EJB
    private LoggingLocal logging;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")    
    @EJB
    private ShoppingCartLocal shoppingCart;
    
    

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    //Customer customerID;
    private String message;
    private Date currentTime;
    private PurchaseOrder po;
    private Customer customer;
    private BigDecimal shipCost;
    private int size,orderNum;
    private Query query;
    //UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
    
    
    
    @Override
    public void createPO()
    {
        //transaction.begin();
        System.out.print("\nTesting");
        size = shoppingCart.getProducts().size();
        currentTime = new Date(); 
        query = em.createNamedQuery("PurchaseOrder.findMax");
        orderNum= (int)query.getSingleResult();

        Long curTwoWeeks = (new Date().getTime()) + (14 * 24 * 3600 * 1000) ;
        
        for(int i=0;i<size;i++)
        {
            po = new PurchaseOrder();
            orderNum++;
        
            po.setOrderNum(orderNum);
                  
            query = em.createNamedQuery("Customer.findByCustomerId")
                    .setParameter("customerId", 863);
            
            customer = (Customer)query.getSingleResult();
            
            po.setCustomerId(customer);
            po.setFreightCompany("DPD Couriers");
            po.setProductId(shoppingCart.getProducts().get(i).getProduct());
            po.setQuantity((short)shoppingCart.getProducts().get(i).getProduct_quantity());
            po.setSalesDate(currentTime);
            po.setShippingDate(currentTime = new Date(curTwoWeeks));
            po.setShippingCost(shipCost = new BigDecimal(20));
            
            logging.sendMessageToQueue("PO:--OrderNo:"+po.getOrderNum()+", CustID:"+po.getCustomerId()
            +", Carrier:"+po.getFreightCompany()+", ProdID:"+po.getProductId()+", Qty:"+po.getQuantity()
            +", DateSold:"+po.getSalesDate()+", ShipDate:"+po.getShippingDate()+", ShipCost:"+po.getShippingCost());
            

            em.persist(po);  
        }
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
    
    
}
