package project.momento.question.function;

import java.util.Arrays;

public class MyParser {
	public static Object myParser(String str, String option)
	{
		Object result = null;
		if (option.equals("[I"))	//	int array
		{
			str = str.replaceAll("[\\[ \\]]", "");
			int[] intArray = Arrays.stream(str.split(","))
				    .mapToInt(Integer::parseInt)
				    .toArray();
			result = intArray;
		}
		else if(option.equals("int"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			int number = Integer.parseInt(str);
			result = number;
		}
		else if(option.equals("java.lang.String"))
		{
			str = str.replaceAll("[\\[ \\]\"]", "");
			result = str;
		}
		else if(option.equals("[Ljava.lang.String;"))
		{
			str = str.replaceAll("[\\[\\]\"]", "");
			String[] strArray = str.split(",");
			result = strArray;
		}
		else if(option.equals("char"))
		{
			str = str.replaceAll("[\\[\\]\"]", "");
			char c = str.charAt(0);
			result = c;
		}
		else if(option.equals("[C"))
		{
			str = str.replaceAll("[\\[\\]\",]", "");
			char[] chArray = str.toCharArray();
			result = chArray;
		}
		else if(option.equals("boolean"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			boolean b = Boolean.parseBoolean(str);
			result = b;
		}
		else if(option.equals("[Z"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			String[] parts = str.split(",");
			boolean[] array = new boolean[parts.length];
			for (int i = 0; i < parts.length; i++)
		        array[i] = Boolean.parseBoolean(parts[i]);
			result = array;
		}
		else if(option.equals("double"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			double d = Double.parseDouble(str);
			result = d;
		}
		else if(option.equals("[D"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			String[] parts = str.split(",");
			double[] array = new double[parts.length];
			for (int i = 0; i < parts.length; i++)
		        array[i] = Double.parseDouble(parts[i]);
			result = array;
		}
		else if(option.equals("float"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			float f = Float.parseFloat(str);
			result = f;
		}
		else if(option.equals("[F"))
		{
			str = str.replaceAll("[\\[ \\]]", "");
			String[] parts = str.split(",");
			float[] array = new float[parts.length];
			for (int i = 0; i < parts.length; i++)
		    array[i] = Float.parseFloat(parts[i]);
			result = array;
		}
		return result;
	}
}
