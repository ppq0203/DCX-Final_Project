package project.momento.question.function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;

import project.momento.answer.dto.AnswerDto;
import project.momento.answer.service.AnswerService;

public class AnswerToDB {
	
	@Autowired
	private AnswerService answerService;

	
	// 문제번호, 유저번호, 답변, 결과
	public static void answerToDB (int pkQuestionSeq, int pkUserSeq, String answerUser, int result, String type)
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
		LocalDate date = LocalDate.of(2015, 03, 18);
        LocalTime time = LocalTime.of(11, 30, 30);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
		answerDto.setSolveTime(dateTime.toString());

		AnswerToDB answerToDB = new AnswerToDB();
		answerToDB.answerService.insertAnswer(answerDto);
	}
}
