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
		// 폴더 경로
		String strDirPath = "22/"; 
        File path = new File( strDirPath ); 
        File[] fList = path.listFiles();
    	
        for( int i = 0; i < fList.length; i++ ) { 
             
            if( fList[i].isFile() ) {
				// 솔루션 코드 수정한 파일 저장할 경로
            	String filePath = strDirPath + "Solution.java";
            	try {
					InsertLineToFile inLine = new InsertLineToFile();
					// 솔루션코드에 import java.util.*; 추가, class 앞에 public 추가
					inLine.insertStringInFile(fList[i], filePath);
	    	        // Create a Java compiler
	    	        JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
	    	        // Create a Java compilation task
	    	        int compilationResult = compiler.run(null, null, null, filePath);
					// load solution class
	    	        MyClassLoader myClassLoader = new MyClassLoader(ClassLoaderTestController.class.getClassLoader(), strDirPath);
	    	        Class clazz = myClassLoader.loadClass("Solution");
					// solution class object create
					Object myClass = clazz.newInstance();
					// 컴파일 성공시 아래코드 수행
					if(compilationResult == 0)
	    	        {
						// 솔루션 파일명 앞에 문제넘버만 추출
						String no = fList[i].getName().split("-")[0];
						// 넘버에 해당하는 솔루션의 함수명 가져와야함
			//			String funcName = no에 해당하는 함수명
						//
						try {
							// input값으로 넣을 데이터들 csv파일로 load
							CSVReader reader = new CSVReader(new FileReader(strDirPath + "csv/input.csv"));
		    	            String [] nextLine;
							// 데이터 한행씩 로드
		    	            while ((nextLine = reader.readNext()) != null) {   // 2
		    	                // input.csv에는 input만 들어있기때문에(nextLine.length = 1) l=0만 수행함
								for (int l = 0; l < nextLine.length; l++) {
									// 함수에 input data를 넣고 결과를 얻어내는 함수
	//								Object out = myClassLoader.testLoadClass(myClass, funcName, nextLine[l]);
									// 결과를 csv에 저장하는 코드 실행
//									code
									//
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
							// CSVReader에서 오류 발생했을때,
							e.printStackTrace();
						} // 1
	    	        }

				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
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
