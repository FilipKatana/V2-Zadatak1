package wrappers;

import java.util.ArrayList;

import abstracts.AbstractWrapper;
import components.Attribute;
import components.Method;
import enums.Visibility;

public class Class extends AbstractWrapper {
	
	private ArrayList<Attribute> attributes;
	

	public Class(Visibility visibility, String name, ArrayList<AbstractWrapper> supers, ArrayList<Attribute> attributes, ArrayList<Method> methods) {
		super(visibility, name, supers, methods);
		this.attributes = new ArrayList<Attribute>();
		
		for (Attribute a : attributes) {
			if (a.getVisibility() == Visibility.LOCAL) {
				throw new IllegalArgumentException("Exception while constructing class! Forbidden visibility LOCAL assigned to attribute " + a.getName());
			}
			
			this.attributes.add(a);
			
		}
		

	}
	
	public void addAttribute(Attribute a) {
		if (a.getVisibility() == Visibility.LOCAL) {
			throw new IllegalArgumentException("Exception while adding class attributes! Forbidden visibility LOCAL assigned to attribute " + a.getName());
		}
		this.attributes.add(a);
	}
	
	public Attribute getAttribute(int index) {
		return this.attributes.get(index);
	}
	
	public int attributeCount() {
		return this.attributes.size();
	}
	
	public String discriminator() {
		return "class";
	}

}
