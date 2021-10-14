package abstracts;

import enums.Visibility;

public abstract class ObjectOrientedConcept {
	private String name;
	private Visibility visibility;
	
	public ObjectOrientedConcept(Visibility visibility, String name) {
		this.visibility = visibility;
		this.name = name;
		if (name.indexOf(' ') != -1) {
			throw new IllegalArgumentException("Naming exeption occured with: " + name + " No spaces can be included!");
		}
	}
	
	public String getName() {
		return name;
	}

	public Visibility getVisibility() {
		return visibility;
	}
	
	public abstract String discriminator();


}
