package main;



import java.util.ArrayList;

import abstracts.AbstractWrapper;
import abstracts.Component;
import components.Attribute;
import components.Method;
import enums.Type;
import enums.Visibility;
import generator.Generator;
import generator.PythonGenerator;
import wrappers.Interface;
import wrappers.Class;




public class Main {
	public static void main(String[] args) {
		Attribute arg1 = new Attribute(Visibility.LOCAL, Type.CHAR, "arg1", 's');
		Attribute arg2 = new Attribute(Visibility.LOCAL, Type.INT, "arg2", 8);
		
		
		ArrayList<Method> methods = new ArrayList<Method>();
		methods.add(new Method(Visibility.PUBLIC, Type.INT, "myMethod", arg1, arg2));
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(Visibility.PUBLIC, true, Type.INT, "MAX", 45));
		attributes.add(new Attribute(Visibility.PRIVATE, Type.INT, "theMeaningOfLife", 42));
		
		
		
		Class testClass = new Class(Visibility.PROTECTED, "Classy", new ArrayList<AbstractWrapper>(), new ArrayList<Attribute>(), new ArrayList<Method>());
		
		
		ArrayList<AbstractWrapper> supers = new ArrayList<AbstractWrapper>();
		supers.add(testClass);
		supers.add(new Interface(Visibility.PUBLIC, "Runnable", new ArrayList<Interface>(), new ArrayList<Method>()));
		supers.add(new Interface(Visibility.PUBLIC, "Iterable", new ArrayList<Interface>(), new ArrayList<Method>()));
		
		Class mainClass = new Class(Visibility.PUBLIC, "MainClass", supers, attributes, methods);
		
		//System.out.println(PythonGenerator.generateCode(mainClass));
		System.out.println(Generator.generateCode(mainClass));
	}

}
