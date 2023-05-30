package project.momento.test.controller;

import javax.tools.JavaCompiler;
import java.io.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaOnlineCompilerApplication {

	@GetMapping("/compile")
	public String runJavaCode(CompileDTO cd) {
		String code = cd.getSource();
		System.out.println(code);
	    try {
	    	// Save source in .java file.
			System.out.println("2");
			String roomNum = "11";
			String idNum = "22";
			String directoryPath = roomNum + "/" + idNum;
			File theDir = new File(directoryPath);
			if (!theDir.exists()) theDir.mkdirs();
			String filePath = directoryPath + "/Solution.java";
	    	File sourceFile = new File(filePath);
	    	sourceFile.createNewFile();
			System.out.println("3");
	    	Writer writer = new FileWriter(sourceFile);
			System.out.println("4");
	    	writer.write(code);
	    	writer.close();
	    	System.out.println(sourceFile.getPath());
	    	
//	    	File tempFile = new File("temp");
//	    	Writer tempwriter = new FileWriter(tempFile);
//	    	tempwriter.write("36 24 27");
//	    	tempwriter.close();
	    	
	        // Create a Java compiler
	        JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
	        // Create a Java compilation task
	        int compilationResult = compiler.run(null, null, null, sourceFile.getPath());

	        // Check the compilation result
//	        if (compilationResult == 0) {
//	            // The code compiled successfully, so run it
//	            ProcessBuilder processBuilder = new ProcessBuilder("java", "HelloWorld");
////	            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//	            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
////	            processBuilder.redirectInput(tempFile);
//	            Process process = processBuilder.start();
//	            process.waitFor();
//
//	            // Get the output of the program
////	            String output = process.getInputStream().toString();
//	            String result = new String(process.getInputStream().readAllBytes());
//	            System.out.println(result);
//	            return result;
////	            return "1";
//	        } else {
//	            // The code did not compile, so return an error message
//	            return "The code did not compile.";
//	        }
	        return "fin";
	    } catch (Exception e) {
	        // An error occurred, so return an error message
	        return "An error occurred while running the code.";
	    }
	}
}