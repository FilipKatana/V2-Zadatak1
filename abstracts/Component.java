package abstracts;

import enums.Type;
import enums.Visibility;

public abstract class Component extends ObjectOrientedConcept {
	private Type type;
	
	public Component(Visibility visibility, Type type, String name) {
		super(visibility, name);
		this.type = type;
	}
	public Type getType() {
		return type;
	}
}
