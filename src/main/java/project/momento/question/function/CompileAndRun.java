package project.momento.question.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.tools.JavaCompiler;

import project.momento.question.dto.TestcaseDto;
import project.momento.question.controller.GenerateTestcaseController;

public class CompileAndRun {

	public static int classRun(Object myClass, List<TestcaseDto> testcaseDtos, String funcName, int option)
	{
		int result = 0;
        for (TestcaseDto testDto : testcaseDtos) {   // 
        	Object out = funcRunOut(myClass, funcName, testDto.getInput());
        	// option값이 3인 경우 solDto.output에 input에 대한 결과값 저장
        	if(option == 3) {
        		testDto.setOutput(out.toString());
        		result = 3;
        	}
        }
        return result;
	}
	
	public static Object classLoad(String fixSolPath, String className) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		MyClassLoader myClassLoader = new MyClassLoader(GenerateTestcaseController.class.getClassLoader(), fixSolPath);
        Class clazz = myClassLoader.loadClass(className);
		// solution class object create
		Object myClass = clazz.newInstance();
		return myClass;
	}
	

	// 함수에 imput값을 넣고 수행했을때 output되는 값을 리턴 
	public static Object funcRunOut(Object obj, String methodName, String inputContent)
	{
		// object의 class타입 저장
    	Class<?> objClass = obj.getClass();
    	Object result = null;
    	// 해당 class의 내부에있는 method들 모두가져와서 반복수행
    	for (Method method : objClass.getDeclaredMethods())
    	{
    		// 가져온 method가 실행을 원하는 method명과 일치하면 수행
    		if(method.getName().equals(methodName))
            {
    			// 변수마다 구분을 ;로 하여 저장되어있는 input string을 배열로 변환
        		String[] instr = inputContent.split(";");
        		// method의 매개변수 개수 저장
            	int parameterCount = method.getParameterCount();
            	// method의 매개변수들을 저장할 Object Array생성
            	Object[] inputs = new Object[parameterCount];
            	try {
            		int p = 0;
            		for (Class<?> parameter : method.getParameterTypes())
            		{
            			inputs[p] = MyParser.myParser(instr[p], parameter.getName());
            			p++;
            		}
            	} catch (Exception e) {}
            	
            	try {
					result = method.invoke(obj, inputs);
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
        }
		return result;
	}

	public static int fileCompile(File file, String fixSolPath)
	{
		int result = -1;
		try {
			String filePath = fixSolPath + "/Solution.java";
			// 코드에 import java.util.*; 추가, class 앞에 public 추가
			InsertLineToFile.insertStringInFile(file, filePath);
			// Create a Java compiler
			JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
			// Create a Java compilation task
			result = compiler.run(null, null, null, filePath);
		} catch (Exception e) {}
		return result;
	}
}
