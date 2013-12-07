package org.magen.rookie.service;

import java.util.List;

public interface IBaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {
    public M save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public void merge(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);
    
    public int countAll();
    
    public List<M> listAll();
}
