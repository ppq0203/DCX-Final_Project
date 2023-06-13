package project.momento.question.function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;

import project.momento.answer.dto.AnswerDto;
import project.momento.answer.service.AnswerService;

public class AnswerToDB {
	
	// 문제번호, 유저번호, 답변, 결과
	public static void answerToDB (int pkQuestionSeq, int pkUserSeq, String answerUser, int result, String type, AnswerService answerService)
	{
		AnswerDto answerDto = new AnswerDto();
		answerDto.setPkQuestionSeq(pkQuestionSeq);
		answerDto.setPkUserSeq(pkUserSeq);
		answerDto.setAnswerUser(answerUser);
		String answerOx;
		if (result == 0)
			answerOx = "O";
		else
			answerOx = "X";
		answerDto.setAnswerOx(answerOx);
		answerDto.setType(type);
		LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
		answerDto.setSolveTime(dateTime.toString());

		System.out.println(answerDto);
		answerService.insertAnswer(answerDto);
	}
}
