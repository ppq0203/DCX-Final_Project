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
	    	writer.write("import java.util.*;\n\n");
	    	writer.write(code);
	    	writer.close();
	    	System.out.println(sourceFile.getPath());

	        JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
	        // Create a Java compilation task
	        int compilationResult = compiler.run(null, null, null, sourceFile.getPath());

	        return "fin";
	    } catch (Exception e) {
	        // An error occurred, so return an error message
	        return "An error occurred while running the code.";
	    }
	}
}