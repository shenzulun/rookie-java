package org.magen.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
@SequenceGenerator(name="seq",allocationSize=1,sequenceName="book_sequence")
public class Book {
	private Long id;
	private String name;
	private String author;
	
	public Book(){}
	
	public Book(String name, String author){
		this.name = name;
		this.author = author;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="AUTHOR")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
