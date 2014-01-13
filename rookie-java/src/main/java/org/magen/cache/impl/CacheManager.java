package org.magen.cache.impl;

import java.lang.ref.ReferenceQueue;
import org.magen.cache.AbstractModel;
import org.magen.cache.Cache;

/**
 * 缓存管理
 * @author magen
 *
 */
public class CacheManager {
	private static CacheManager cacheManager;
	private Cache<Object,ModelRef> cache;
	private ReferenceQueue<AbstractModel> queue;
	private AbstractModel model;
	
	private CacheManager(Cache<Object,ModelRef> cache,AbstractModel model){
		this.cache = cache;
		this.queue = new ReferenceQueue<AbstractModel>();
		this.model = model;
	}
	
	public static CacheManager getInstance(Cache<Object,ModelRef> cache, AbstractModel model){
		synchronized (CacheManager.class) {
			if(cacheManager == null){
				cacheManager = new CacheManager(cache,model);
			}
			return cacheManager;
		}
	}
	
	/**
	 * 默认使用LinkedHashMap实现的LRUCache
	 * @return
	 */
	public static CacheManager getInstance(AbstractModel model){
		return getInstance(new LRUCache<Object, ModelRef>(), model);
	}
	
	public AbstractModel get(Object key){
		AbstractModel model = null;
		if(cache.containsKey(key)){
			model = cache.get(key).get();
			model.setSource(1);
		}
		if(model == null){
			//load from db
			model = this.model.loadModel(key);
			addCache(model);
		}
		return model;
	}
	
	private void addCache(AbstractModel model){
		cleanCache();
		ModelRef ref = new ModelRef(model, queue);
		cache.put(model.getUid(), ref);
		
	}
	
	private void cleanCache(){
		ModelRef ref = null;
		while((ref=(ModelRef)queue.poll())!=null){
			cache.remove(ref.getUid());
		}
	}
	
	public void clear(){
		cleanCache();
		cache.clear();
		System.gc();
		System.runFinalization();
	}

}
