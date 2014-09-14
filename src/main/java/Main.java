
import org.hibernate.Session;

import com.hibernate.persistence.HibernatePersistence;


public class Main {

    public static void main( String[] args )
    {
        Session session = HibernatePersistence.getSessionFactory().openSession();

        session.beginTransaction();
        //close session
        HibernatePersistence.shutdown();
}
}