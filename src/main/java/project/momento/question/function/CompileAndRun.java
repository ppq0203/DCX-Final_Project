package project.momento.question.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.tools.JavaCompiler;

import project.momento.question.dto.TestcaseDto2;
import project.momento.question.controller.GenerateTestcaseController;

public class CompileAndRun {

	public static int classRun(Object myClass, List<TestcaseDto2> testcaseDtos, String funcName, int option)
	{
		int result = 0;
		Class<?> objClass = myClass.getClass();
    	// 해당 class의 내부에있는 method들 모두가져와서 반복수행
    	for (Method method : objClass.getDeclaredMethods())
    	{
    		// 가져온 method가 실행을 원하는 method명과 일치하면 수행
    		if(method.getName().equals(funcName))
            {
    			for (TestcaseDto2 testDto : testcaseDtos) {   //
    				try {
    					Object out = funcRunOut(myClass, method, funcName, testDto.getInput());
    					// option값이 -1인 경우 solDto.output에 input에 대한 결과값 저장
    					if(out != null && option == -1) {
    						testDto.setOutput(out.toString());
    						result = option;
    					}
    					// option값이 0인 경우 유저의 결과와 저장되어있는 결과를 비교
    					if(option == 0)
    					{	
    						System.out.println(testDto.getInput());
    						System.out.println(testDto.getOutput());
							System.out.println(out.toString());
    						// 유저결과와 저장되어있는 결과가 다르면 오답이므로 -1을 return
    						if(testDto.getOutput().equals(out.toString()) != true)
    						{
    							return -1;
    						}
    					}
    				} catch(ArrayIndexOutOfBoundsException e) {
//    					e.printStackTrace();
    					result = -1;
    				} catch(NumberFormatException e) {
//    					e.printStackTrace();
    					result = -1;
    				} catch(Exception e) {
//    					e.printStackTrace();
    					result = -1;
    				}
    			}
            }
        }
        return result;
	}
	
	public static Object classLoad(String fixSolPath, String className) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		MyClassLoader myClassLoader = new MyClassLoader(GenerateTestcaseController.class.getClassLoader(), fixSolPath);
        Class<?> clazz = myClassLoader.loadClass(className);
		// solution class object create
		@SuppressWarnings("deprecation")
		Object myClass = clazz.newInstance();
		return myClass;
	}
	

	// 함수에 input값을 넣고 수행했을때 output되는 값을 리턴 
	public static Object funcRunOut(Object obj, Method method, String methodName, String inputContent)
	{
		Object result = null;

		String[] instr = inputContent.split(";");
		// method의 매개변수 개수 저장
		int parameterCount = method.getParameterCount();
		// method의 매개변수들을 저장할 Object Array생성
		Object[] inputs = new Object[parameterCount];
		int p = 0;
		for (Class<?> parameter : method.getParameterTypes())
		{
			inputs[p] = MyParser.myParser(instr[p], parameter.getName());
			p++;
		}
		try {
			result = method.invoke(obj, inputs);
			if (result.getClass().getName() == "[I")
			{
				List<Integer> list =  Arrays.stream((int[])result).boxed().collect(Collectors.toList());
				result = list;
			}
			if (result.toString().length() > 50)
			{
				result = null;
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
//				e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
//				e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
//				e.printStackTrace();
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
