package project.momento.testjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import project.momento.test.MyClassLoader;
import project.momento.test.InsertLineToFile;

import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.tools.JavaCompiler;

import java.io.File;

@Controller
public class ClassLoaderTestController {
	@RequestMapping(value = "/loadtest")
    public String index() throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		MyClassLoader myClassLoader = new MyClassLoader(ClassLoaderTestController.class.getClassLoader(), "11/22");
        Class clazz = myClassLoader.loadClass("Solution");

        Object myClass = clazz.newInstance();
        System.out.println(myClass.toString());
        Class<?> carClass = myClass.getClass();
        Arrays.stream(carClass.getDeclaredMethods())
        .forEach(method -> {
            System.out.println(method.getName());
            System.out.println(method.getParameterCount());
            Arrays.stream(method.getParameterTypes())
            .forEach(parameter -> { System.out.println(parameter.getName()); });
            System.out.println("================================================");
        });
        return "index";
    }
	
	@RequestMapping(value = "/dircheck")
    public String dircheck() {
		String strDirPath = "sol_test/"; 
        File path = new File( strDirPath ); 
        File[] fList = path.listFiles();
    	
        for( int i = 0; i < fList.length; i++ ) { 
             
            if( fList[i].isFile() ) { 
            	String filePath = strDirPath + "/Solution.java";
            	InsertLineToFile inLine = new InsertLineToFile();
            	try {
					inLine.insertStringInFile(fList[i], filePath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
    	        // Create a Java compiler
    	        JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
    	        // Create a Java compilation task
    	        int compilationResult = compiler.run(null, null, null, filePath);
    	        System.out.println(compilationResult);
    	        if(compilationResult == 0)
    	        {
    	        	System.out.println(fList[i].getName().split("-")[0]);
    	        }
            } 
            else if( fList[i].isDirectory() ) { 
                System.out.println( "[폴더] " + fList[i].getPath() );  // 재귀함수 호출 
            } 
        } 
        return "index";
    }
}
