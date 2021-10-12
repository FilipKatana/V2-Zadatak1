package wrappers;

import java.util.ArrayList;

import abstracts.AbstractWrapper;
import components.Method;
import enums.Visibility;

public class Interface extends AbstractWrapper {

	public Interface(Visibility visibility, String name, ArrayList<Interface> interfaces, ArrayList<Method> methods) {
		super(visibility, name, null, methods);
		
		for (AbstractWrapper a : interfaces) {
			this.addSuper(a);
		}

	}
	
	public String discriminator() {
		return "interface";
	}

}
