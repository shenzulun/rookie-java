package org.magen.cache.impl;

import org.magen.cache.AbstractModel;

public class BookModel extends AbstractModel{
	private long id;
	private String ISBN;
	private String name;
	private String author;
	
	public BookModel(){
		super();
	}
	
	protected void initUid(Object obj) {
		setUid(obj);
	}
	
	public AbstractModel loadModel(Object uid) {
		BookModel model = new BookModel();
		model.setISBN(uid.toString());
		model.setName("test");
		model.setAuthor("szl");
		model.setSource(0);
		return model;
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
	public void setISBN(String iSBN) {
		initUid(iSBN);
		ISBN = iSBN;
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
	
}
