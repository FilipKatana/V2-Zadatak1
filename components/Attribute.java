package components;

import abstracts.Component;
import enums.Type;
import enums.Visibility;

public class Attribute extends Component {
	private Object value;
	
	
	
	private static void failConstructor(String adMsg) {
		throw new IllegalArgumentException("Failed to construct attribute! " + adMsg);
	}
	
	private static void typeException(String type, Object value) {
		failConstructor("The assigned type " + type + " does not match the given value: " + value);
	}
	
	public Attribute(Visibility visibility, Type type, String name, Object value) {
		super(visibility, type, name);
		switch(type) {
		case DOUBLE:
			if (!(value instanceof Double)) {
				typeException(type.toString(), value);
			}
			break;
		case CHAR:
			if (!(value instanceof Character)) {
				typeException(type.toString(), value);
			}			
			break;
		case INT:
			if (!(value instanceof Integer)) {
				typeException(type.toString(), value);
			}
			break;
		case STRING:
			if (!(value instanceof String)) {
				typeException(type.toString(), value);
			}
			break;
		case VOID:
			failConstructor("Given type void is not available for attributes. Create a method or use another type instead");
		default:
			break;
		}
		
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
	
}
