package org.magen.cache;

/**
 * 缓存抽象模型
 * @author magen
 *
 */
public abstract class AbstractModel {
	/**
	 * 基础id 可以区分是否同一对象
	 */
	private Object uid;
	
	/**
	 * 0:db 1:cache
	 */
	private int source;   

	public Object getUid() {
		return uid;
	}
	
	protected void setUid(Object obj){
		this.uid = obj;
	}
	/**
	 * 子类实现该方法  设置唯一标识
	 * 调用setUid()
	 * @param obj
	 */
	protected abstract void initUid(Object obj);
	
	/**
	 * 子类实现该方法  从非缓存中获取model
	 * @param uid
	 * @return
	 */
	public abstract AbstractModel loadModel(Object uid);
		
	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}
	
	public boolean equals(Object o){
		if(o == this)
			return true;
		if(!(o instanceof AbstractModel))
			return false;
		AbstractModel model = (AbstractModel)o;
		return uid.equals(model.getUid());
	}

}
