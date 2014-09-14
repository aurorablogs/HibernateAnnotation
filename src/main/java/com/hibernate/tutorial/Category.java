package com.hibernate.tutorial;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "category")
public class Category implements java.io.Serializable{

	private Integer id;
    private String name;
    private Set<Product> productDetails = new HashSet<Product>();
    
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

    @OneToMany(mappedBy = "category")
    public Set<Product> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Set<Product> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", productDetails="
				+ productDetails + "]";
	}	
	
	
}