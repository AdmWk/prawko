package pl.admwk.prawko;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.admwk.prawko.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class PrawkoApplication {
	public static final List<Question> questions = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PrawkoApplication.class, args);
		File file = new File("src/main/resources/data/baza_test.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		rowIterator.next();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			boolean hasCorrectCategory = checkIfQuestionMatchesBCategory(row.getCell(18).getStringCellValue());
			if(!row.getCell(2).getStringCellValue().isEmpty() && hasCorrectCategory) {
				questions.add(mapRowToQuestion(row));
			}
		}

	}

	private static boolean checkIfQuestionMatchesBCategory(String categoriesString) {
		List<String> categories = Arrays.asList(categoriesString.split(","));
		return categories.contains("B");
	}

	private static Question mapRowToQuestion(Row row) {
		return Question.QuestionBuilder.aQuestion()
				.withQuestionText(row.getCell(2).getStringCellValue())
				.withAnswers(getAnswersToQuestion(row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue()))
				.withIsYesNo(row.getCell(3).getStringCellValue().isEmpty())
				.withCorrectAnswer(getCorrectAnswer(row.getCell(14).getStringCellValue()))
				.withFileName(row.getCell(15).getStringCellValue().replace(".wmv", ".mp4"))
				.build();
	}

	private static Character getCorrectAnswer(String correctAnswer) {
		if ("T".equals(correctAnswer)) {
			return 'A';
		}
		if("N".equals(correctAnswer)) {
			return 'B';
		}
		return correctAnswer.charAt(0);
	}

	private static Map<Character, String> getAnswersToQuestion(String answerA, String answerB, String answerC) {
		Map<Character, String> answerMap = new HashMap<>();
		if(answerA.isEmpty() && answerB.isEmpty() && answerC.isEmpty()) {
			answerMap.put('A', "Tak");
			answerMap.put('B', "Nie");
		} else {
			answerMap.put('A', answerA);
			answerMap.put('B', answerB);
			answerMap.put('C', answerC);
		}
		return answerMap;
	}

}
