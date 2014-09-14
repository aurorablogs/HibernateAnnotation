package com.hibernate.tutorial;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by bilal on 9/14/14.
 */

@Entity
@Table(name = "course")
public class Course {

    private Integer id;
    private String name;
    private Set<Student> students = new HashSet<Student>(0);

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course_map", joinColumns = {
            @JoinColumn(name = "course_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "student_id",
                    nullable = false, updatable = false) })
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}