package pl.admwk.prawo_jazdy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.admwk.prawo_jazdy.PrawoJazdyApplication;
import pl.admwk.prawo_jazdy.model.Question;

import java.util.Random;

@Controller
public class QuestionController {

    @GetMapping("/question")
    public String question(Model model) {
        Random rand = new Random();
        Question randomQuestion = PrawoJazdyApplication.questions.get(rand.nextInt(PrawoJazdyApplication.questions.size()));
        model.addAttribute("questionText", randomQuestion.getQuestionText());
        model.addAttribute("answerA", randomQuestion.getAnswers().get('A'));
        model.addAttribute("answerB", randomQuestion.getAnswers().get('B'));
        model.addAttribute("answerC", randomQuestion.getAnswers().get('C'));
        model.addAttribute("isYesNo", randomQuestion.isYesNo());
        model.addAttribute("correctAnswer", randomQuestion.getCorrectAnswer());
        model.addAttribute("file", randomQuestion.getFileName());
        model.addAttribute("fileType", getFileType(randomQuestion.getFileName()));
        return "question";
    }

    private String getFileType(String fileName) {
        return fileName.contains(".mp4") ? "VID" : "IMG";
    }
}
