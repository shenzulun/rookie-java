package org.magen.cache.impl;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import org.magen.cache.AbstractModel;

/**
 * <p>软引用保存model信息,当jvm内存快不足时，会回收所引用对象的内存。
 * 同时JVM会将这个软引用加入到与之关联的引用队列{@link ReferenceQueue}}中</p>
 * @author magen
 *
 */
public class ModelRef extends SoftReference<AbstractModel>{
	private Object uid;
	
	public ModelRef(AbstractModel model,
			ReferenceQueue<? super AbstractModel> queue) {
		super(model, queue);
		this.uid = model.getUid();
	}
	
	public Object getUid(){
		return uid;
	}
	
	
}
