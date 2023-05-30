package project.momento.testjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import project.momento.test.MyClassLoader;

import java.io.FileNotFoundException;
import java.util.Arrays;

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
            System.out.println(method);
            System.out.println("================================================");
        });
        return "index";
    }
}
