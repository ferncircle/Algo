/**
 * 
 */
package com.chawkalla.algorithms.examples.design;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class DynamicArray<T> {

	private T[] buffer;
	private int length;

	private double lf=1.5;

	@SuppressWarnings("unchecked")
	public DynamicArray(){
		buffer=(T[])new Object[100];
	}

	@SuppressWarnings("unchecked")
	public DynamicArray(int size){
		buffer=(T[])new Object[size];
	}

	public void add(T item){
		if(full())
			grow();
		buffer[length++]=item;
	}
	
	public T get(int index){
		return buffer[index];
	}

	public void remove(T item){
		for (int i = 0; i < buffer.length; i++) {
			if(item.equals(buffer[i])){
				remove(i);
				return;
			}
		}
	}

	public void remove(int index){
		System.arraycopy(buffer, index+1, buffer, index, length-index);
		buffer[length]=null;
		length--;
	}	
	
	public boolean contains(T item){
		for (int i = 0; i < buffer.length; i++) {
			if(item.equals(buffer[i])){
				return true;
			}
		}
		return false;
	}
	
	public int getLength(){
		return length;
	}
	
	private boolean full(){
		return length==buffer.length;
	}
	
	@SuppressWarnings("unchecked")
	private void grow(){
		int newSize=(int)(buffer.length*lf);
		T[] newBuffer=(T[])new Object[newSize];
		System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
		buffer=newBuffer;
	}


	public static void main(String[] args) {
		
		DynamicArray<String> a=new DynamicArray<String>(10);
		String[] sa={"abc","ab2c","a4bc","ab634c","a4bserc","a23bc","a45bc","ab75c","a13bc","a76bc","afgbc","abrhec"};
		for(String s:sa)
			a.add(s);
		assertThat(a.getLength(), is(sa.length));
		a.remove("a4bc");
		assertThat(a.getLength(), is(sa.length-1));
		assertThat(a.get(2), is("ab634c"));
		assertThat(a.get(a.getLength()-1), is("abrhec"));
		System.out.println("all cases passed");
	}

}
