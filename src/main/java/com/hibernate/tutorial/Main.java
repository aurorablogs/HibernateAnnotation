package com.hibernate.tutorial;

import java.math.BigDecimal;

import org.hibernate.Session;

import com.hibernate.persistence.HibernatePersistence;


public class Main {

    public static void main( String[] args )
    {
        Session session = HibernatePersistence.getSessionFactory().openSession();

        session.beginTransaction();
        Product product = new Product();

        //Make some product for storing in database
       // product.setId(4);
        product.setName("COKE");
        product.setCode("C001");
        product.setPrice(new BigDecimal("18.00"));

        //Save product to database
        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        
        //get data from  database
        product = (Product) session.get(Product.class, productId);
        System.out.println(product);
        
        //close session
        HibernatePersistence.shutdown();
}
}