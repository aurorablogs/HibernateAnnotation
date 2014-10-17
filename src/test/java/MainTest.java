

import java.math.BigDecimal;
import java.util.ArrayList;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import com.hibernate.persistence.HibernatePersistence;
import com.hibernate.tutorial.Product;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */

@RunWith(value=BlockJUnit4ClassRunner.class)
public class MainTest 
    extends TestCase
{
	private static Session session;
    private static Product product;

    @Test
    public void testGetProductUsingGet(){
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
       product.setName("COKE");
        product.setCode("C002");
        product.setPrice(new BigDecimal("18.00"));
        session.beginTransaction();

        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        product = (Product) session.get(Product.class, 5);
        System.out.println("Query printed or excuted when get() is called");

        assertEquals("COKE",product.getName());
        session.close();
    }

   @Test
    public void testGetProductUsingLoad(){
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product.setName("COKE");
        product.setCode("C002");
        product.setPrice(new BigDecimal("18.00"));
        session.beginTransaction();

        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        product = (Product) session.load(Product.class, productId);
        System.out.println("Query is not printed or excuted when get() is called");
        System.out.println("Query will now be printed or excuted when property of instance is called");

        assertEquals("COKE",product.getName());

        System.out.println("Query printed or excuted when we tries to get property of instance. In this case it is name");
        session.close();
    }

   @Test(expected=NullPointerException.class)
    public void testGetMethodOnException() {
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product = (Product) session.get(Product.class, 100);
        if(product==null){
            System.out.println("System will Through NPE as object is not found in database");
            throw new NullPointerException();
        }
        session.close();
    }

    @Test(expected=ObjectNotFoundException.class)
    public void testLoadMethodOnException() {
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product = (Product) session.load(Product.class, 100);
        if(product==null){
            System.out.println("System will not throw NPE as a Object/Proxy is returned from database");
        }

        System.out.println("System will throw ObjectNotFoundException when I try to get property of the instance");
        product.getName();
        session.close();
    }

}