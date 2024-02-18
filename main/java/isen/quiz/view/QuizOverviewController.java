package isen.quiz.view;

import java.util.List;

import isen.quiz.model.Answer;
import isen.quiz.model.Question;
import isen.quiz.service.QuestionService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class QuizOverviewController {
	
	QuestionService questionFactory = QuestionService.getInstance();
	
	@FXML
	private Label questionLabel;
	@FXML
	private Button answerButton1;
	@FXML
	private Button answerButton2;
	@FXML
	private Button answerButton3;
	@FXML
	private Label scoreLabel;
	
	private List<Question> questions; 
	private int currentQuestionIndex;
	private List<Answer> answers;
	private int score;
	
	@FXML
	private void showCurrentQuestion() {
		Question currentQuestion = questions.get(currentQuestionIndex);
		answers = currentQuestion.getAnswers();
		
		questionLabel.setText(currentQuestion.getQuestion());
		answerButton1.setText(answers.get(0).getText());
		answerButton2.setText(answers.get(1).getText());
		answerButton3.setText(answers.get(2).getText());
		
		scoreLabel.setText(getScoreText());
		
	}
	
	@FXML
	private void initialize() {
		currentQuestionIndex = 0;
		score = 0;
		questions = questionFactory.getQuestions();
		
		showCurrentQuestion();
	}
	
	@FXML
	private void handleAnswerButton1() {
		handleAnswer(0);
	}
	
	@FXML
	private void handleAnswerButton2() {
		handleAnswer(1);
	}
	
	@FXML
	private void handleAnswerButton3() {
		handleAnswer(2);
	}
	
	private void goToNextQuestion() {
		try {
			currentQuestionIndex++;
			showCurrentQuestion();
		} catch (IndexOutOfBoundsException e) {
			gameOver();
		}
	}
	
	private void gameOver() {
		questionLabel.setText("");
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText("Game Over\nYour score: " + getScoreText());
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> Platform.exit());
	}
	
	private void handleAnswer(int answerIndex) {
		if (answers.get(answerIndex).isGoodAnswer()) {
			increaseScore();
		}
		goToNextQuestion();
	}
	
	private void increaseScore() {
		score++;
	}
	
	private String getScoreText() {
		String scoreText = score + " out of " + (currentQuestionIndex);
		return scoreText;
	}
	
}
