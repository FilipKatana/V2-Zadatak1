package abstracts;

import java.util.ArrayList;

import components.Method;
import enums.Visibility;

public abstract class AbstractWrapper extends ObjectOrientedConcept {
	private ArrayList<Method> methods;
	
	public AbstractWrapper(Visibility visibility, String name, Method ...methods) {
		super(visibility, name);
		
		this.methods = new ArrayList<Method>();
		if (visibility == Visibility.LOCAL) {
			throw new IllegalArgumentException("Exception occured in AbstractWrapper initialization: assigned illegal visibility LOCAL." +
		" If you're seeing this it means you've proubably tried to initialize an interface or class with local visibility.");
		}
		
		for (Method m : methods) {
			this.methods.add(m);
		}
	}
	
	
	public Method getMethod(int index) {
		return this.methods.get(index);
	}
	
	public void addMethod(Method m) {
		this.methods.add(m);
	}
	
	public int methodCount() {
		return this.methods.size();
	}

}
