package abstracts;


import enums.Type;
import enums.Visibility;

public abstract class Component extends ObjectOrientedConcept {
	private Type type;
	private boolean IsStatic = false;
	
	public Component(Visibility visibility, boolean Static, Type type, String name) {
		super(visibility, name);
		this.type = type;
		this.IsStatic = Static;
	}
	public Type getType() {
		return type;
	}
	
	public boolean isStatic() {
		return this.IsStatic;
	}
}
