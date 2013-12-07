package org.magen.rookie.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.Id;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.magen.rookie.repository.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

public abstract class BaseDAOImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseDao<M, PK>{
	protected Logger logger = LoggerFactory.getLogger("BaseDAOImpl.class");
	
	private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;
    private final String HQL_OPTIMIZE_PRE_LIST_ALL;
    private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
    private String pkName = null;

    @SuppressWarnings("unchecked")
    public BaseDAOImpl() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }      
        Assert.notNull(pkName);
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
        HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " > ? order by " + pkName + " asc";
        HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " < ? order by " + pkName + " desc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }
        
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
	@SuppressWarnings("unchecked")
	public PK save(M model) {
		return (PK) getSession().save(model);
	}
	
	public void saveOrUpdate(M model) {
		getSession().saveOrUpdate(model);
	}

	public void update(M model) {
		getSession().update(model);	
	}

	public void merge(M model) {
		getSession().merge(model);
	}
	
	public void delete(PK id) {
		 getSession().delete(this.get(id));
	}
	
	public void deleteObject(M model) {		
		getSession().delete(model);
	}

	@SuppressWarnings("unchecked")
	
	public M get(PK id) {	
		return (M) getSession().get(this.entityClass, id);
	}

	public int countAll() {		
		Long total = aggregate(HQL_COUNT_ALL);
	    return total.intValue();
	}
	
	public List<M> listAll() {
		return list(HQL_LIST_ALL);
	}
	
	public List<M> listAll(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<M> pre(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<M> next(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(PK id) {
		return get(id) != null;
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
		
	}
	
	 protected long getIdResult(String hql, Object... paramlist) {
	        long result = -1;
	        List<?> list = list(hql, paramlist);
	        if (list != null && list.size() > 0) {
	            return ((Number) list.get(0)).longValue();
	        }
	        return result;
	    }

	    protected List<M> listSelf(final String hql, final int pn, final int pageSize, final Object... paramlist) {
	        return this.<M> list(hql, pn, pageSize, paramlist);
	    }


	    /**
	     * for in
	     */
	    @SuppressWarnings("unchecked")
	    protected <T> List<T> listWithIn(final String hql,final int start, final int length, final Map<String, Collection<?>> map, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        for (Entry<String, Collection<?>> e : map.entrySet()) {
	            query.setParameterList(e.getKey(), e.getValue());
	        }
	        if (start > -1 && length > -1) {
	            query.setMaxResults(length);
	            if (start != 0) {
	                query.setFirstResult(start);
	            }
	        }
	        List<T> results = query.list();
	        return results;
	    }

	    /**
	     * 根据查询条件返回唯一一条记录
	     */
	    @SuppressWarnings("unchecked")
	    protected <T> T unique(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        return (T) query.setMaxResults(1).uniqueResult();
	    }

	       /**
	        * 
	        * for in
	        */
	    @SuppressWarnings("unchecked")
	    protected <T> T aggregate(final String hql, final Map<String, Collection<?>> map, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        if (paramlist != null) {
	            setParameters(query, paramlist);
	            for (Entry<String, Collection<?>> e : map.entrySet()) {
	                query.setParameterList(e.getKey(), e.getValue());
	            }
	        }

	        return (T) query.uniqueResult();
	    }
	        
	    @SuppressWarnings("unchecked")
	    protected <T> T aggregate(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);

	        return (T) query.uniqueResult();

	    }


	    /**
	     * 执行批处理语句.如 之间insert, update, delete 等.
	     */
	    protected int execteBulk(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        Object result = query.executeUpdate();
	        return result == null ? 0 : ((Integer) result).intValue();
	    }
	    
	    protected int execteNativeBulk(final String natvieSQL, final Object... paramlist) {
	        Query query = getSession().createSQLQuery(natvieSQL);
	        setParameters(query, paramlist);
	        Object result = query.executeUpdate();
	        return result == null ? 0 : ((Integer) result).intValue();
	    }

	    protected <T> List<T> list(final String sql, final Object... paramlist) {
	        return list(sql, -1, -1, paramlist);
	    }	        
	        
	    @SuppressWarnings("unchecked")
	    protected <T> T aggregateByNative(final String natvieSQL, final List<Entry<String, Type>> scalarList, final Object... paramlist) {
	        SQLQuery query = getSession().createSQLQuery(natvieSQL);
	        if (scalarList != null) {
	            for (Entry<String, Type> entity : scalarList) {
	                query.addScalar(entity.getKey(), entity.getValue());
	            }
	        }

	        setParameters(query, paramlist);

	        Object result = query.uniqueResult();
	        return (T) result;
	    }	     

	    @SuppressWarnings("unchecked")
	    public <T> List<T> list(Criteria criteria) {
	        return criteria.list();
	    }

	    @SuppressWarnings("unchecked")
	    public <T> T unique(Criteria criteria) {
	        return (T) criteria.uniqueResult();
	    }

	    public <T> List<T> list(DetachedCriteria criteria) {
	        return list(criteria.getExecutableCriteria(getSession()));
	    }

	    @SuppressWarnings("unchecked")
	    public <T> T unique(DetachedCriteria criteria) {
	        return (T) unique(criteria.getExecutableCriteria(getSession()));
	    }

	    protected void setParameters(Query query, Object[] paramlist) {
	        if (paramlist != null) {
	            for (int i = 0; i < paramlist.length; i++) {
	                if(paramlist[i] instanceof Date) {
	                    query.setTimestamp(i, (Date)paramlist[i]);
	                } else {
	                    query.setParameter(i, paramlist[i]);
	                }
	            }
	        }
	    }

}
