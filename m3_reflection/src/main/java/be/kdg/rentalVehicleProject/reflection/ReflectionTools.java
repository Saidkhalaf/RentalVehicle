package be.kdg.rentalVehicleProject.reflection;

import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.undo.CannotRedoException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.CompletionService;
import java.util.jar.Attributes;

public class ReflectionTools {

    public static void classAnalysis(Class<?>... aClass){
        for (Class<?> myClass : aClass){
            System.out.printf("Analyse van de klasse: " + myClass.getSimpleName() + "\n");
            System.out.println("==============================================");
            System.out.println("Fully qualified name   : " + myClass.getName());
            System.out.println("Naam van de superKlasse: " + myClass.getSuperclass().getSimpleName());
            System.out.println("Naam van de package    : " + myClass.getPackageName());

            Class<?>[] interfaces = myClass.getInterfaces();
            System.out.print("Interfaces             : ");
            for (Class<?> interface1 : interfaces){
                System.out.print(interface1.getSimpleName());
            }

            Constructor[] constructors = myClass.getDeclaredConstructors();
            System.out.print("\nConstructors           : ");
            for (Constructor constructor : constructors){
                System.out.println(constructor);
            }

            Field[] fields = myClass.getDeclaredFields();
            System.out.print("attributen             : ");
            for (Field field : fields){
                System.out.print(field.getName() + "(" + field.getType().getSimpleName() + ") ");
            }

            Method[] methods =myClass.getDeclaredMethods();
            System.out.print("\nGetters                :");
            for (Method method : methods){
                if (method.getName().startsWith("get") && method.getParameterCount() == 0 && !void.class.equals(method.getReturnType())){
                    System.out.print(" "+ method.getName() +" ");
                }
            }

            System.out. print("\nSetters                :");
            for (Method method: methods){
                if (method.getName().startsWith("set") && method.getParameterCount() == 1){
                    System.out.print(" "+ method.getName() + " ");
                }
            }

            System.out.print("\nandere methoden        :");
            for (Method method : methods){
                if (!method.getName().startsWith("set") && !method.getName().startsWith("get")
                        &&method.getParameterCount() == 0 && !void.class.equals(method.getReturnType())){
                    System.out.print(" " + method.getName() + " ");
                }
            }
            System.out.println("\n");

        }
    }

    public static Object runAnnotated(Class<?> aClass) throws Exception{
        Constructor<?> constructor =aClass.getDeclaredConstructor();
        Object obj =constructor.newInstance();

        Method[] methods =aClass.getDeclaredMethods();
        for (Method method : methods){
            CanRun canRunAnnotation = method.getAnnotation(CanRun.class);

            if (canRunAnnotation != null && method.getParameterCount() == 1 && method.getGenericParameterTypes()[0] == String.class){
                String value = canRunAnnotation.value();
                method.invoke(obj, value);
            }
        }
        return obj;
    }

}
