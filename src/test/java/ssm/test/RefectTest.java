package ssm.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import org.aspectj.weaver.NameMangler;

public class RefectTest {
	

	public static void main(String[] args){
		String name;
		if(args.length>0){
			name = args[0];
		}else{
			Scanner in =new Scanner(System.in);
			System.out.println("Enter class name ");
			name = in.next(); 
		}
		
		  try {
	            Class c1 = Class.forName(name);
	            Class superclass = c1.getSuperclass();
	            String modifiers = Modifier.toString(c1.getModifiers());
	            if (modifiers.length() > 0 ) {
	                System.out.print(modifiers + " ");
	            }
	            System.out.print("class " + name);
	            if (superclass != null && superclass != Object.class) {
	                System.out.print(" extends " + superclass.getName());
	            }
	            System.out.print("\n{\n");
	            printConstructors(c1);
	            System.out.println();
	            printMethods(c1);
	            System.out.println();
	            printFields(c1);
	            System.out.println("}");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        System.exit(0);

	}
	
	   public static void printConstructors(Class c1) {
	        Constructor[] constructors = c1.getDeclaredConstructors();
	 
	        for (Constructor constructor : constructors) {
	            String name = constructor.getName();
	            System.out.print("   ");
	            String modifiers = Modifier.toString(constructor.getModifiers());
	            if (modifiers.length() > 0) {
	                System.out.print(modifiers + " ");
	            }
	            System.out.print(name + "(");
	            Class[] paramTypes = constructor.getParameterTypes();
	            for (int j = 0; j < paramTypes.length; j ++) {
	                if (j > 0) {
	                    System.out.print(", ");
	                }
	                System.out.println(paramTypes[j].getName());
	            }
	            System.out.println(");");
	        }
	    }
	 
	    public static void printMethods(Class c1) {
	        Method[] methods = c1.getDeclaredMethods();
	 
	        for (Method method : methods) {
	            Class returnType = method.getReturnType();
	            String name = method.getName();
	            System.out.println("   ");
	            String modifiers = Modifier.toString(method.getModifiers());
	            if (modifiers.length() > 0) {
	                System.out.print(modifiers + " ");
	            }
	            System.out.println(returnType.getName() + " " + name + "(");
	            Class[] paramTypes = method.getParameterTypes();
	            for (int j = 0; j < paramTypes.length; j ++) {
	                if (j > 0) {
	                    System.out.print(", ");
	                }
	                System.out.print(paramTypes[j].getName());
	            }
	            System.out.println(");");
	        }
	    }
	 
	    public static void printFields(Class c1) {
	        Field[] fields = c1.getDeclaredFields();
	 
	        for (Field field : fields) {
	            Class type = field.getType();
	            String name = field.getName();
	            System.out.print("    ");
	            String modifiers = Modifier.toString(field.getModifiers());
	            if (modifiers.length() > 0 ) {
	                System.out.print(modifiers + " ");
	            }
	            System.out.println(type.getName() + " " + name + ";");
	        }
	    }

}
