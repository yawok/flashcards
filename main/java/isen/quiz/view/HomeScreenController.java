package isen.quiz.view;

import javafx.fxml.FXML;
import isen.quiz.App;

public class HomeScreenController {

	@FXML
	private void handleLaunchButton() {
		try {
			App.setRoot("/isen/quiz/view/QuizOverview");
			System.out.println("Clicked");
		} catch (Exception e) {
			System.out.println("Clicked error");
			e.printStackTrace();
		}
	}
	
	
}
