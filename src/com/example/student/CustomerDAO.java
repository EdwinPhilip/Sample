package com.example.student;

import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class CustomerDAO {

    @PersistenceContext
    private EntityManager em;
    
    public Customer getCustomer(int id) {
        return em.find(Customer.class, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            em.persist(customer);
        }
    }
    
    public Collection<Customer> getAllCustomers() {
    	TypedQuery<Customer> query =em.createNamedQuery("Customer.findAll", Customer.class);
    	return query.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCustomer(int Id, String Fname, String Lname) {
    	
    	Customer cust = em.find(Customer.class, Id);
    	
    	cust.setId(Id);
    	cust.setName(Fname);
    	cust.setSurname(Lname);
    	em.merge(cust);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCustomer(int id) {
    	Customer cust = em.find(Customer.class, id);
    	em.remove(cust);
    	
    }
    
    
    
}
