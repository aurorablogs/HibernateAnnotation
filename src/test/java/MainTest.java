

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.TransientObjectException;

import com.hibernate.persistence.HibernatePersistence;
import com.hibernate.tutorial.Category;
import com.hibernate.tutorial.Product;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MainTest 
    extends TestCase
{
	private static Session session;
    private static Category category;
    private static Product product;
    
    @org.junit.Test(expected = TransientObjectException.class)
    public void testTransientException(){
    	String name = "Penalty";
        category = new Category();
        session = HibernatePersistence.getSessionFactory().openSession();
        category.setName(name);
        //do not save category object this will cause hibernate to
        // throw TransientObjectException
        
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product.setName("COKE");
        product.setCode("C002");
        product.setPrice(new BigDecimal("18.00"));
        product.setCategory(category);
        session.beginTransaction();
        
        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        product = (Product) session.get(Product.class, productId);
        
        assertEquals("COKE",product.getName());
        assertEquals(category.getId(), product.getCategory().getId());
    	
    }

    @org.junit.Test
    public void testSaveTransient(){
    	String name = "Penalty";
        category = new Category();
        session = HibernatePersistence.getSessionFactory().openSession();
        category.setName(name);
        
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product.setName("COKE");
        product.setCode("C002");
        product.setPrice(new BigDecimal("18.00"));
        product.setCategory(category);
        session.beginTransaction();
        
        //save category first and get it from database
        //in order to avoid TransientObjectException
        Integer categoryId = (Integer) session.save(category);
        session.getTransaction().commit();
        category = (Category) session.get(Category.class, categoryId);
        
        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        product = (Product) session.get(Product.class, productId);
        
        assertEquals("COKE",product.getName());
        assertEquals(category.getId(), product.getCategory().getId());
    }
}