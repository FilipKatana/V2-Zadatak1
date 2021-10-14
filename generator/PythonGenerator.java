package generator;

import java.util.ArrayList;

import components.Attribute;
import components.Method;
import wrappers.Class;
import wrappers.Interface;

public class PythonGenerator {
	
	//ATTRIBUTE GEN
	public static String generateCode(Attribute a) {
		return a.getName() + " = " + a.getValue().toString();
	}
	
	//METHOD GEN
	public static String generateCode(Method m, String start) {
		String result = "def " + m.getName() + "(";
		for (int i = 0; i < m.argumentCount(); ++i) {
			if (i >= 1) {
				result += ", ";
			}
			
			result += m.getArgument(i).getName();
		}
		result += "):\n";
		result += start + "\t" + "pass";
		return result;
	}
	
	public static String generateCode(Method m) {
		return generateCode(m, "");
	}
	
	
	//CLASS GEN
	public static String generateCode(Class c) {
		String result = "class " + c.getName();
		
		//Inheretence
		if (c.superCount() >= 1) {
			result += "(";
			for (int i = 0; i < c.superCount(); ++i) {
				if (i > 0) {
					result += ", ";
				}
				result += c.getSuper(i).getName();
			}
			result += ")";
		}
		result += ":\n";
		
		//Static attributes
		ArrayList<Attribute> nonStaticAttributes = new ArrayList<Attribute>();
		for (int i = 0; i < c.attributeCount(); ++i) {
			if (c.getAttribute(i).isStatic()) {
				result += "\t" + generateCode(c.getAttribute(i)) + "\n";
			} else {
				nonStaticAttributes.add(c.getAttribute(i));
			}
		}
		
		//Non-static attributes
		result += "\tdef __init__(self):\n";
		
		if (nonStaticAttributes.size() > 0) {
		for (Attribute a : nonStaticAttributes) {
			result += "\t\t" + "self."  + generateCode(a) + "\n";
		}
		
		} else {
			result += "\t\tpass\n";
		}
		
		//Methods
		result += "\n\n";
		for (int i = 0; i < c.methodCount(); ++i) {
			result += "\t" + generateCode(c.getMethod(i), "\t") + "\n";
		}
		return result;
	}
	
	
	//INTERFACE GEN
	public static String generateCode(Interface in) {
		String result = "class " + in.getName() + "(ABC):\n";
		for (int i = 0; i < in.methodCount(); ++i) {
			result += "\t@abstractmethod\n\t" + generateCode(in.getMethod(i), "\t");
		}
		return result;
	}
}
