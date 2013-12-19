package org.magen.cache;

public class Book {
	private long id;
	private String ISBN;
	private String name;
	private String author;
	private int source;   // 0:db  1:cache
	
	
	public Book(){
		
	}
	
	public Book(String ISBN){
		loadFromDB(ISBN);
	}
	
	void loadFromDB(String ISBN){
		this.ISBN = ISBN;
		this.name = "test";
		this.author = "szl";
		this.source = 0;
		System.out.println("load book from db...");
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}
	
	public boolean equals(Book book){
		return book.getISBN() == ISBN;
	}
	
}
