package project.momento.question.function;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


import java.util.HashMap;
import java.util.ArrayList;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import project.momento.question.dto.TestcaseDto;

public class TestcaseGenerator {
	
	public static void testcaseGenrate()
	{
		// solution 코드들이 저장되어있는 dir경로
//		String solsPath = "question/java_sols/";
		String solsPath = "question/sol1/";
		// 솔루션 코드 수정한 파일 저장할 경로
		String fixSolPath = solsPath + "sol";
    	String className = "Solution";
    	// input csv 파일 경로
    	String inputCsvPath = "question/csv/input.csv";
    	// 함수이름 csv 파일 경로
    	String funcNameCsvPath = "question/csv/classname.csv";
    	// 테스트케이스 생성파일 경로
    	String inOutCsvPath = fixSolPath + "/noInOut.csv";
    	// solution.java파일을 저장할 디렉토리가 존재하지않으면 생성
    	File theDir = new File(fixSolPath);
		if (!theDir.exists()) theDir.mkdirs();
		// csv파일 로받은 input data를 List<SolutionDto>에 저장
		List<TestcaseDto> testcaseDtos = csvToInput(inputCsvPath);
		// csv파일 로받은 function 정보를 key=문제no, value=함수명 형태로 Map에 저장
    	Map<Integer, String> funcMap = csvToGetFuncName(funcNameCsvPath);
		// input Data를 불러오지 못하면 중단
    	if (testcaseDtos.size() == 0 || funcMap.size() == 0)
    	{
    		System.out.println("no datas");
    		return;
    	}
    	// solution 파일들 불러오기
        File path = new File( solsPath );
        File[] fList = path.listFiles();
    	
        for( int i = 0; i < fList.length; i++ ) {
        	// 불러온 파일이 파일형태면
            if( fList[i].isFile() ) {
            	//parseInt에서 에러나는경우 해당파일 스킵
            	try{
                	// solution file의 이름이 문제번호-문제이름 형태이므로 문제번호에 해당하는 함수명을 가져옴
            		int questionNum = Integer.parseInt(fList[i].getName().split("-")[0]);
            		String funcName = funcMap.get(questionNum);
            		// solution file의 필요한 코드추가후 컴파일 컴파일 성공시 0값 return
            		if(CompileAndRun.fileCompile(fList[i], fixSolPath) == 0)
            		{
						System.out.println(fList[i].getName());
            			try {
	                		// class파일 실행
							ExecutorService executor = Executors.newSingleThreadExecutor();
							Callable<Object> tesk  = () -> {
								Object myClass = CompileAndRun.classLoad(fixSolPath, className);
								CompileAndRun.classRun(myClass, testcaseDtos, funcName, -1);
//								TestcaseGenerator tg = new TestcaseGenerator();
								outputToCsv(questionNum, testcaseDtos, inOutCsvPath);
								return null;
							};
							Future<Object> future = executor.submit(tesk);
							try {
								// 최대 30초간 작업을 기다림
								future.get(30, TimeUnit.SECONDS);
							} catch (TimeoutException e) {
								// 시간 초과 예외 처리
								System.out.println("TimeOut");
								// 작업 취소
								future.cancel(true);
								executor.shutdownNow();
							} catch (InterruptedException | ExecutionException e) {
								// 다른 예외 처리
							} catch (OutOfMemoryError e) {
								
							}

	            		} catch (Exception e) {
	            			
	            		} catch (OutOfMemoryError e) {}
            		}
            	} catch (NumberFormatException e) { System.out.println("compile error"); }
            } 
        }
	}
	
	//	csv파일을 읽어와서 List<SolutionDto>에 input을 저장하는 함수
	public static List<TestcaseDto> csvToInput(String csvPath)
	{
		List<TestcaseDto> inputDataList = new ArrayList<TestcaseDto>();
		TestcaseDto solDto = null;
		try {
			// input값으로 넣을 데이터들 csv파일로 load
			CSVReader reader = new CSVReader(new FileReader(csvPath));
            String [] nextLine;
			// 데이터 한행씩 로드
            while ((nextLine = reader.readNext()) != null) {   // 2
            	solDto = new TestcaseDto();
                // input.csv에는 input 하나만 들어있기때문에 nextLine[0]만 수행함
            	solDto.setInput(nextLine[0]);
            	inputDataList.add(solDto);
            }
		} catch (IOException e) {
			// CSVReader에서 오류 발생했을때,
			e.printStackTrace();
		} // 1
		return inputDataList;
	}
	
	// csv파일을 읽어와서 map에 함수명을 저장하는 함수
	public static Map<Integer, String> csvToGetFuncName(String csvPath)
	{
		Map<Integer, String> funcMap = new HashMap<Integer, String>();
		try {
			// 넣을 데이터들 csv파일로 load
			CSVReader reader = new CSVReader(new FileReader(csvPath));
            String [] nextLine;
			// 데이터 한행씩 로드
            while ((nextLine = reader.readNext()) != null) {   // 2
            	// 문제 번호가 0행, 함수명이 1행에 있으므로 번호를 key 함수명을 value로 갖는 데이터를 map에 추가
            	funcMap.put(Integer.parseInt(nextLine[0]) , nextLine[1]);
            }
		} catch (IOException e) {
			// CSVReader에서 오류 발생했을때,
			e.printStackTrace();
		} // 1
		return funcMap;
	}
	
	public static void outputToCsv(int questionNum, List<TestcaseDto> testcaseDtos, String inOutCsvPath) throws IOException
	{
		// 저장할 배열이 no, input, output 3개이므로 3개의 String 배열 생성
		String[] entries = new String[3];
		CSVWriter writer = new CSVWriter(new FileWriter(inOutCsvPath, true));
		for (TestcaseDto testcaseDto : testcaseDtos)
		{
			entries[2] = testcaseDto.getOutput();
			if (entries[2] != null && entries[2].length() < 40)
			{
				entries[0] = Integer.toString(questionNum);
				entries[1] = testcaseDto.getInput();
		        writer.writeNext(entries);
			}
			testcaseDto.setOutput(null);
		}
        writer.close();
	}
	
	public void outputToDB(int questionNum, List<TestcaseDto> testcaseDtos) throws IOException
	{
		List<TestcaseDto> dbDtos = new ArrayList<TestcaseDto>();
		TestcaseDto dbDto;
		String outstr;
		// 저장할 배열이 no, input, output 3개이므로 3개의 String 배열 생성
		for (TestcaseDto testcaseDto : testcaseDtos)
		{
			outstr = testcaseDto.getOutput();
			if (outstr != null && outstr.length() < 40)
			{
				dbDto = new TestcaseDto();
				dbDto.setPkQuestionSeq(questionNum);
				dbDto.setInput(testcaseDto.getInput());
				dbDto.setOutput(outstr);
				dbDtos.add(dbDto);
			}
			testcaseDto.setOutput(null);
		}
//		testcaseService.testCaseToDbList(dbDtos);
	}
	
	public static List<TestcaseDto> csvToDB(String csvPath)
	{
		List<TestcaseDto> inputDataList = new ArrayList<TestcaseDto>();
		TestcaseDto solDto = null;
		try {
			// input값으로 넣을 데이터들 csv파일로 load
			CSVReader reader = new CSVReader(new FileReader(csvPath));
            String [] nextLine;
			// 데이터 한행씩 로드
            while ((nextLine = reader.readNext()) != null) {   // 2
            	solDto = new TestcaseDto();
//            	System.out.println("no: "+Integer.parseInt(nextLine[0]) + "  in: " + nextLine[1] + "  out: "+nextLine[2]);
            	solDto.setPkQuestionSeq(Integer.parseInt(nextLine[0]));
            	solDto.setInput(nextLine[1]);
            	solDto.setOutput(nextLine[2]);
            	inputDataList.add(solDto);
            }
//            testcaseService.testCaseToDbList(inputDataList);
		} catch (IOException e) {
			// CSVReader에서 오류 발생했을때,
			e.printStackTrace();
		} // 1
		return inputDataList;
	}
}
