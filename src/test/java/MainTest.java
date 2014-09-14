import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.hibernate.persistence.HibernatePersistence;
import com.hibernate.tutorial.Course;
import com.hibernate.tutorial.Student;

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
    private static Student student;


    @org.junit.Test
    public Course testCourse(String name){
        Course course = new Course();
        //session = HibernatePersistence.getSessionFactory().openSession();
        course.setName(name);
        session.beginTransaction();
        Integer courseId = (Integer) session.save(course);
        session.getTransaction().commit();
        course = (Course) session.get(Course.class, courseId);
        System.out.println("Course Name is : "+course.getName());
        return course;
    }

    @org.junit.Test
    public void testStudent(){
        Set<Student> students = new HashSet<Student>();
        student = new Student();
        session = HibernatePersistence.getSessionFactory().openSession();
        student.setName("ALI");
        students.add(student);
        Course mathCourse = testCourse("Math");
        mathCourse.setStudents(students);
        Course phyCourse = testCourse("PHY");
        phyCourse.setStudents(students);
        Set<Course> courses = new HashSet<Course>();
        courses.add(mathCourse);
        courses.add(phyCourse);
        student.setCourses(courses);
        session.beginTransaction();

        Integer studentId =(Integer) session.save(student);
        session.getTransaction().commit();
        student = (Student) session.get(Student.class, studentId);
        System.out.println("Student name is : "+student.getName());
        assertEquals("ALI",student.getName());
    }
}

