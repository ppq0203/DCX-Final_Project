package project.momento.testjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVReader;

import project.momento.test.MyClassLoader;
import project.momento.test.OpenCsv;
import project.momento.test.InsertLineToFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		String strDirPath = "22/"; 
        File path = new File( strDirPath ); 
        File[] fList = path.listFiles();
    	
        for( int i = 0; i < fList.length; i++ ) { 
             
            if( fList[i].isFile() ) { 
            	String filePath = strDirPath + "Solution.java";
            	InsertLineToFile inLine = new InsertLineToFile();
            	try {
					inLine.insertStringInFile(fList[i], filePath);

            	
	    	        // Create a Java compiler
	    	        JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
	    	        // Create a Java compilation task
	    	        int compilationResult = compiler.run(null, null, null, filePath);
	    	        System.out.println("compile finish");
	//    	        System.out.println(compilationResult);

					// load solution class
	    	        MyClassLoader myClassLoader = new MyClassLoader(ClassLoaderTestController.class.getClassLoader(), strDirPath);
	    	        Class clazz = myClassLoader.loadClass("Solution");
					System.out.println("this err?");
					
					// solution class object create
					Object myClass = clazz.newInstance();
					
					if(compilationResult == 0)
	    	        {
						String no = fList[i].getName().split("-")[0];
//	    	        	System.out.println(no);
	    	        	CSVReader reader;
						try {
							reader = new CSVReader(new FileReader(strDirPath + "csv/input.csv"));
		    	            String [] nextLine;
		    	            while ((nextLine = reader.readNext()) != null) {   // 2
		    	                for (int l = 0; l < nextLine.length; l++) {
//		    	                    System.out.println(l + " " + nextLine[l]);
		    	                    Object out = myClassLoader.testLoadClass(myClass, "twoSum", nextLine[l]);
		    	                    Object out1 = myClassLoader.testLoadClass(myClass, "lengthOfLongestSubstring", nextLine[l]);
		    	                    Object out2 = myClassLoader.testLoadClass(myClass, "findMedianSortedArrays", nextLine[l]);
		    	                    if (out != null)
		    	                    	System.out.println("no: " + no + ", input: " + nextLine[l] + ", out: " + out);
		    	                    else if (out1 != null)
		    	                    	System.out.println("no: " + no + ", input: " + nextLine[l] + ", out1: " + out1);
		    	                    else if (out2 != null)
	    	                    		System.out.println("no: " + no + ", input: " + nextLine[l] + ", out2: " + out2);
		    	                }
		    	            }
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // 1
	    	        }
				} catch (FileNotFoundException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } 
            else if( fList[i].isDirectory() ) { 
                System.out.println( "[폴더] " + fList[i].getPath() );  // 재귀함수 호출 
            } 
        } 
        return "index";
    }
	
	@RequestMapping(value = "/opencsv")
    public String opencsv() {
		try {
			OpenCsv.writeDataToCsv("sol_test/sample.csv");
			OpenCsv.readDataFromCsv("sol_test/sample.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "index";
    }
}
