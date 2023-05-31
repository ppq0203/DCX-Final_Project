package project.momento.test;

import java.io.*;

/**
 * 파일의 특정 라인에 문자열을 삽입하는 예제
 * @author 이종철
 *
 */
public class InsertLineToFile {

        /**
        * 파일내의 특정 라인에 아규먼트로 입력하는 문자열을 삽입
        * @param inFile : 입력 파일명
        * @param lineno : 문자열을 삽일할 라인
        * @param lineToBeInserted : 삽입될 문자열
        * @throws Exception
        */
        public void insertStringInFile(File inFile, String outFilePath) throws Exception {
               
                //파일에서 읽은 한라인을 저장하는 임시변수
                String thisLine = "";
               
                // 임시파일을 만듭니다.
                File outFile = new File(outFilePath);

                // 아규먼트로 받은 입력 파일
                FileInputStream fis = new FileInputStream(inFile);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                // output 파일
                FileOutputStream fos = new FileOutputStream(outFile);
                PrintWriter out = new PrintWriter(fos);
       
                int i = 1;
                out.println("import java.util.*;\n");
                //파일 내용을 한라인씩 읽어 삽입될 라인이 오면 문자열을 삽입
                while ((thisLine = in.readLine()) != null) {
                        out.println(thisLine);
                        i++;
                }
                out.flush();
                out.close();
                in.close();

                System.out.println("INSERT OK~~~");
        }

}