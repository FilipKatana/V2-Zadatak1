package wrappers;

import abstracts.AbstractWrapper;
import components.Method;
import enums.Visibility;

public class Interface extends AbstractWrapper {

	public Interface(Visibility visibility, String name, Method ...methods) {
		super(visibility, name, methods);
	}

}
