package pl.admwk.prawko.model;

import java.util.Map;

public class Question {
    private String questionText;
    private Map<Character, String> answers;
    private Character correctAnswer;
    private String fileName;
    private boolean isYesNo;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Map<Character, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Character, String> answers) {
        this.answers = answers;
    }

    public Character getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Character correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isYesNo() {
        return isYesNo;
    }

    public void setYesNo(boolean yesNo) {
        isYesNo = yesNo;
    }

    public static final class QuestionBuilder {
        private String questionText;
        private Map<Character, String> answers;
        private Character correctAnswer;
        private String fileName;
        private boolean isYesNo;

        private QuestionBuilder() {
        }

        public static QuestionBuilder aQuestion() {
            return new QuestionBuilder();
        }

        public QuestionBuilder withQuestionText(String questionText) {
            this.questionText = questionText;
            return this;
        }

        public QuestionBuilder withAnswers(Map<Character, String> answers) {
            this.answers = answers;
            return this;
        }

        public QuestionBuilder withCorrectAnswer(Character correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public QuestionBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public QuestionBuilder withIsYesNo(boolean isYesNo) {
            this.isYesNo = isYesNo;
            return this;
        }

        public Question build() {
            Question question = new Question();
            question.setQuestionText(questionText);
            question.setAnswers(answers);
            question.setCorrectAnswer(correctAnswer);
            question.setFileName(fileName);
            question.isYesNo = this.isYesNo;
            return question;
        }
    }
}
