package Experiments;

import java.io.Serializable;

public class ClassB implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ClassA a;
	private int n;
	
	public ClassB (int a, int b, int n)
	{
		this.setA(new ClassA(a,b)); // Implements Composition
		this.setN(n) ;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public ClassA getA() {
		return a;
	}

	public void setA(ClassA a) {
		this.a = a;
	}
}
