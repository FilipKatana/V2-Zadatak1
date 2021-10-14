package generator;

import abstracts.AbstractWrapper;
import abstracts.Component;
import abstracts.ObjectOrientedConcept;
import components.Attribute;
import components.Method;
import enums.Type;
import enums.Visibility;
import wrappers.Class;
import wrappers.Interface;

public class Generator {
	
	private static String stringVisibility(ObjectOrientedConcept o) {
		Visibility y = o.getVisibility();
		switch (y) {
		case PUBLIC:
		case PRIVATE:
		case PROTECTED:
			return y.toString().toLowerCase();
		case PACKAGE:
		case LOCAL:
		default:
			return "";
		}
	}
	
	
	private static String attributeValue(Attribute a) {
		switch(a.getType()) {
		case STRING:
			return '"' + a.getValue().toString() + '"';
		case CHAR:
			return "'" + a.getValue().toString() + "'";
		case INT:
		case DOUBLE:
		default:
			return a.getValue().toString();
		}
	}
	
	
	
	private static String generateHeader(ObjectOrientedConcept o, String identifier) {
		String result = stringVisibility(o);
		result += (result.equals("")) ? "" : " ";
		
		switch (o.discriminator()) {
		case "attribute":
		case "method":
			result += ((Component) o).isStatic() ? "static " : "";
		}
		
		
		result += identifier + " " + o.getName();
		return result;
	}
	
	private static String componentType(Component c) {
		return (c.getType() == Type.STRING) ? "String" : c.getType().toString().toLowerCase();
	}
	
	//ATTRIBUTE
	public static String generateCode(Attribute a) {
		String result = generateHeader(a, componentType(a));
		result += " = " + attributeValue(a) + ";";
		return result;
	}
	
	
	//METHOD
	public static String generateCode(Method m, boolean hasBody) {
		String result = generateHeader(m, componentType(m)) + "(";
		for (int i = 0; i < m.argumentCount(); ++i) {
			if (i > 0) {
				result += ", ";
			}
			result += componentType(m.getArgument(i)) + " " + m.getArgument(i).getName();
		}
		result += ")";
		
		
		if (hasBody) {
			result += " {\n";
			switch(m.getType()) {
			case INT:
				result += "\treturn 0;\n";
				break;
			case DOUBLE:
				result += "\treturn 0.0;\n";
				break;
			case CHAR:
				result += "\treturn 'c';\n";
				break;
			case STRING:
				result += "\treturn \"String\";\n";
				break;
			}
			result += "}";
		} else {
			result += ";";
		}
		return result;
	}
	
	
	//METHOD 2
	public static String generateCode(Method m) {
		return generateCode(m, true);
	}
	
	//INTERFACE
	public static String generateCode(Interface iFace) {
		String result = generateHeader(iFace, "interface");
		result += " {\n";
		for (int i = 0; i < iFace.methodCount(); ++i) {
			result += "\t" + generateCode(iFace.getMethod(i), false) + "\n";
		}
		result += "}";
		return result;
	}
	
	
	//CLASS
	public static String generateCode(Class c) {
		String result = generateHeader(c, "class");
		
		String interfaces = "";
		boolean hasSuper = false;
		
		//Inheretence
		for (int i = 0; i < c.superCount(); ++i) {
			//Interface or class check
			if (c.getSuper(i).discriminator() == "class") {
				//One super per class rule
				if (hasSuper) {
					throw new IllegalArgumentException("The class " + c.getName() + " provided to the generator has more than one super class. Due to Java "
							+ "restrictions, only one super class is allowed.");
				} else {
					hasSuper = true;
					result += " extends " + c.getSuper(i).getName();
				}
			} else if (c.getSuper(i).discriminator() == "interface") { //Accumulate interface string
				if (interfaces != "") {
					interfaces += ", ";
				}
				
				interfaces += c.getSuper(i).getName();
			}
		}
		
		//Add accumulated string
		if (interfaces != "") {
			result += " implements " + interfaces + " ";
		}
		
		result += " {\n";
		
		for (int i = 0; i < c.attributeCount(); ++i) {
			result += "\t" + generateCode(c.getAttribute(i)) + "\n";
		}
		
		if (c.attributeCount() >= 1) {
			result += "\n\n";
		}
		
		for (int i = 0; i < c.methodCount(); ++i) {
			result += "\t" + generateCode(c.getMethod(i)) +"\n";
		}
		result += "}";
		return result;
	}
	
}
