package generator;

import java.util.ArrayList;

import components.Attribute;
import components.Method;
import wrappers.Class;
import wrappers.Interface;

public class PythonGenerator {
	public static String generateCode(Attribute a) {
		return a.getName() + " = " + a.getValue().toString();
	}
	
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
	
	public static String generateCode(Class c) {
		String result = "class " + c.getName() + ":\n";
		
		
		ArrayList<Attribute> nonStaticAttributes = new ArrayList<Attribute>();
		for (int i = 0; i < c.attributeCount(); ++i) {
			if (c.getAttribute(i).isStatic()) {
				result += "\t" + generateCode(c.getAttribute(i)) + "\n";
			} else {
				nonStaticAttributes.add(c.getAttribute(i));
			}
		}
		
		result += "\tdef __init__(self";
		for (int i = 0; i < c.attributeCount(); ++i) {
			result += ", " + c.getAttribute(i).getName();
		}
		result += "):\n";
		
		for (int i = 0; i < c.attributeCount(); ++i) {
			result += "\t\t" + "self." + generateCode(c.getAttribute(i)) + "\n";
		}
		result += "\n\n";
		for (int i = 0; i < c.methodCount(); ++i) {
			result += "\t" + generateCode(c.getMethod(i), "\t") + "\n";
		}
		return result;
	}
	
	public static String generateCode(Interface in) {
		String result = "class " + in.getName() + "(ABC):\n";
		for (int i = 0; i < in.methodCount(); ++i) {
			result += "\t@abstractmethod\n\t" + generateCode(in.getMethod(i), "\t");
		}
		return result;
	}
}
