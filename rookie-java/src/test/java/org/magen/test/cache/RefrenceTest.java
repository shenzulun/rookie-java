package org.magen.test.cache;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import org.junit.Test;

public class RefrenceTest {

	@Test
	public void testWeak(){
		Hello hello = new Hello("world");
		WeakReference<Hello> w = new WeakReference<Hello>(hello);
		hello = null;
		int i = 0;
		while(w.get() != null){
			if(i++ == 3){
				System.gc();
				System.out.println("JVM GC");
			}else{
				w.get().show();
			}
		}
		
	}
	
	@Test
	public void testSoft(){
		Hello hello = new Hello("re");
		SoftReference<Hello> s = new SoftReference<Hello>(hello);
		hello = null;
		int i = 0;
		while(s.get() != null && i < 5){
			if(i++ == 3){
				System.gc();
				System.out.println("JVM GC");
			}else{
				s.get().show();
			}
		}	
		
	}
	
	static class Hello{
		private String desc;
		
		Hello(String desc){
			this.desc = desc;
		}
		
		void show(){
			System.out.println("hello " + desc);
		}
	}
}
