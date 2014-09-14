

import java.math.BigDecimal;

import org.hibernate.Session;

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

    
    @org.junit.Test
    public void testCategory(){
        String name = "Penalty";
        category = new Category();
        session = HibernatePersistence.getSessionFactory().openSession();
        category.setName(name);
        session.beginTransaction();
        
        Integer categoryId = (Integer) session.save(category);
        session.getTransaction().commit();
        category = (Category) session.get(Category.class, categoryId);
        
        assertEquals("Penalty", category.getName());
    }
    
    @org.junit.Test
    public void testProduct(){
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
}