package components;

import java.util.ArrayList;

import abstracts.Component;
import enums.Type;
import enums.Visibility;

public class Method extends Component {
	
	private ArrayList<Attribute> arguments;

	public Method(Visibility visibility, Type type, String name, Attribute ...args) {
		super(visibility, type, name);
		
		if (visibility == Visibility.LOCAL) {
			throw new IllegalArgumentException("Failed method construction! LOCAL visibility is reserved exclusively for attributes!");
		}
		
		
		this.arguments = new ArrayList<Attribute>();
		for (Attribute a : args) {
			if (!(a.getVisibility() == Visibility.LOCAL)) {
				throw new IllegalArgumentException("Failed method construction! Visibility of attribute " + a.getName() + " is "
			+ a.getVisibility() + " expected LOCAL");
			}
			
			this.arguments.add(a);
		}
	}
	
	public Attribute getArgument(int index) {
		return this.arguments.get(index);
	}
	
	public int argumentCount() {
		return this.arguments.size();
	}
	
}
