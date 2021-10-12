package main;



import java.util.ArrayList;

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
		Method m = new Method(Visibility.PUBLIC, Type.INT, "myMethod", arg1, arg2);
		
		/*
		Class c = new Class(Visibility.PUBLIC, "Class", m, new Attribute(Visibility.PUBLIC, Type.STRING, "word", "cat"));
		
		Interface in = new Interface(Visibility.PUBLIC, "Relatable", m);
		
		System.out.println(Generator.generateCode(m));
		System.out.println(PythonGenerator.generateCode(c));
		System.out.println(".............................");
		System.out.println(Generator.generateCode(c));
		System.out.println("-------------------------------");
		System.out.println(PythonGenerator.generateCode(in));
		System.out.println(Generator.generateCode(in));
		*/
		
		ArrayList<Method> mm = new ArrayList<Method>();
		mm.add(m);
		
		ArrayList<Interface> ii = new ArrayList<Interface>();
		ii.add(new Interface(Visibility.PRIVATE, "Face", new ArrayList<Interface>(), new ArrayList<Method>()));
		
		Interface i = new Interface(Visibility.PUBLIC, "Serializable", new ArrayList<Interface>(), new ArrayList<Method>());
	}

}
