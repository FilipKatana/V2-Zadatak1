package abstracts;

import java.util.ArrayList;

import components.Method;
import enums.Visibility;

public abstract class AbstractWrapper extends ObjectOrientedConcept {
	private ArrayList<Method> methods;
	private ArrayList<AbstractWrapper> supers;
	
	public AbstractWrapper(Visibility visibility, String name, ArrayList<AbstractWrapper> supers, ArrayList<Method> methods) {
		super(visibility, name);
		
		if (visibility == Visibility.LOCAL) {
			throw new IllegalArgumentException("Exception occured in AbstractWrapper initialization: assigned illegal visibility LOCAL." +
		" If you're seeing this it means you've proubably tried to initialize an interface or class with local visibility.");
		}
		
		
		this.supers = new ArrayList<AbstractWrapper>();
		this.methods = new ArrayList<Method>();
		
		try {
			this.supers.addAll(supers);
			this.methods.addAll(methods);			
		} catch (NullPointerException e) {}

		
	}
	
	public AbstractWrapper getSuper(int index) {
		return this.supers.get(index);
	}
	
	public void addSuper(AbstractWrapper a) {
		this.supers.add(a);
	}
	
	public int superCount() {
		return this.supers.size();
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
