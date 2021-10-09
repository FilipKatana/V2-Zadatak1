package wrappers;

import java.util.ArrayList;

import abstracts.AbstractWrapper;
import abstracts.Component;
import components.Attribute;
import components.Method;
import enums.Visibility;

public class Class extends AbstractWrapper {
	
	private ArrayList<Attribute> attributes;

	public Class(Visibility visibility, String name, Component ...components) {
		super(visibility, name);
		this.attributes = new ArrayList<Attribute>();
		
		for (Component com : components) {
			if (com instanceof Method) {
				this.addMethod((Method) com);
			} else if (com instanceof Attribute) {
				this.addAttribute((Attribute) com);
			}
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

}
