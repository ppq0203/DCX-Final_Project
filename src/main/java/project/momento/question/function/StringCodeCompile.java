package project.momento.question.function;

import java.io.File;
import java.io.Writer;
import java.util.List;

import javax.tools.JavaCompiler;

import project.momento.question.dto.TestcaseDto2;

import java.io.FileWriter;
import java.io.IOException;

public class StringCodeCompile {
	public static int stringCodeCompile(int roomNo, int UserNo, String functionName, List<TestcaseDto2> testcaseDtos, String code)
	{
		int result = 0;
		String directoryPath = roomNo + "/" + UserNo;
		File theDir = new File(directoryPath);
		if (!theDir.exists()) theDir.mkdirs();
		String filePath = directoryPath + "/Solution.java";
		String className = "Solution";
    	File sourceFile = new File(filePath);
    	try {
			sourceFile.createNewFile();
			Writer writer = new FileWriter(sourceFile);
			writer.write("import java.util.*;\n\n");
			writer.write(code);
			writer.close();
			JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
			// Create a Java compilation task
			result = compiler.run(null, null, null, filePath);
			// 컴파일실패시 -1리턴
			if (result != 0)
				return -1;
			Object myClass = CompileAndRun.classLoad(directoryPath, className);
			// option = 0 유저가 문제푸는경우
			result = CompileAndRun.classRun(myClass, testcaseDtos, functionName, 0);
			// 제출한 코드가 정답이면 0을 return받음
			if (result != 0)
				return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;
		} catch (ClassNotFoundException e) {
			return -1;
		} catch (InstantiationException e) {
			return -1;
		} catch (IllegalAccessException e) {
			return -1;
		}
    	
		return result;
	}
}
