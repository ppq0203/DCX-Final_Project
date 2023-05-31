package project.momento.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MyClassLoader extends ClassLoader {

    private final String rootDir;

    public MyClassLoader(ClassLoader parent, String rootDir) throws FileNotFoundException {

        super(parent);

        File f = new File(rootDir);
        if (f.isDirectory())
            this.rootDir = rootDir;
        else
            throw new FileNotFoundException("'" + rootDir + "' isn't a directory");
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        String classFilePath = rootDir + File.separator + name.replace(".", File.separator) + ".class";

        try {
            FileInputStream fis = new FileInputStream(classFilePath);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            return defineClass(name, buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
    
    public Object testLoadClass(Object obj, String methodName, String inputContent)
	{
    	Class<?> objClass = obj.getClass();
    	Object[] result = new Object[1];
    	result[0] = null;
		Arrays.stream(objClass.getDeclaredMethods())
        .forEach(method -> {
            if(method.getName().equals(methodName))
            {
            	String str = inputContent;
        		String[] instr = str.split(";");
            	int parameterCount = method.getParameterCount();
//            	System.out.println(parameterCount);
            	Object[] inputs = new Object[parameterCount];
            	int[] p = new int[1];
            	p[0] = 0;
            	try {
            	Arrays.stream(method.getParameterTypes())
                .forEach(parameter -> {
            		inputs[p[0]] = MyParser.myParser(instr[p[0]], parameter.getName());
                	p[0]++;
                	});
            	} catch (Exception e) {}
            	
            	try {
					result[0] = method.invoke(obj, inputs);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
            }
        });
		return result[0];
	}
}




