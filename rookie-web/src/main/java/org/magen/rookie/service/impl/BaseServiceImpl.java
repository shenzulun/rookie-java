package org.magen.rookie.service.impl;

import java.util.List;

import org.magen.rookie.repository.IBaseDao;
import org.magen.rookie.service.IBaseService;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseService<M, PK>{
	
	protected IBaseDao<M, PK> baseDao;
	
	public abstract void setBaseDao(IBaseDao<M, PK> baseDao);
	
	public M save(M model) {	
		baseDao.save(model);
	    return model;
	}

	public void saveOrUpdate(M model) {
		baseDao.saveOrUpdate(model);
	}
	
	public void update(M model) {
		baseDao.update(model);
	}

	public void merge(M model) {
		baseDao.merge(model);		
	}

	public void delete(PK id) {
		baseDao.delete(id);	
	}

	public void deleteObject(M model) {
		baseDao.deleteObject(model);
	}
	
	public M get(PK id) {
		return baseDao.get(id);
	}

	public int countAll() {
		return baseDao.countAll();
	}

	public List<M> listAll() {
		return baseDao.listAll();
	}

}
