package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;

@SpringBootApplication
public class QuizApplication {
	
	@Autowired
	QuizService service;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
	
	
	private void execuite() {
//		setup();
//		showList();
//		showOne();
//		updateQuiz();
//		deleteQuiz();
		doQuiz();
		doQuiz();
		doQuiz();
		doQuiz();
	}

	private void setup() {
		Quiz quiz1 = new Quiz(null, "「Java」はオブジェクト志向言語である", true, "登録太郎");
		
		Quiz quiz2 = new Quiz(null, "「Spring Data」はデータアクセスに対する機能を提供する", true, "登録太郎");

		Quiz quiz3 = new Quiz(null, "プログラムがたくさん配置されいてるサーバーのことを「ライブラリ」という", false, "登録太郎");

		Quiz quiz4 = new Quiz(null, "「@Component」はインスタンス生成アノテーションである", true, "登録太郎");

		Quiz quiz5 = new Quiz(null, "「Spring MVC」が実装している「デザインパターン」ですべてのリクエストを1つのコントローラーで受け取るパターンは「シングルコントローラー・パターン」である", false, "登録太郎");

		List<Quiz> list = new ArrayList<>();
		Collections.addAll(list, quiz1, quiz2, quiz3, quiz4, quiz5);
		for (Quiz quiz : list) {
			service.insertQuiz(quiz);
		}
	}

	private void showList() {
		Iterable<Quiz> quizzes = service.findAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
	}
	
	private void showOne() {
		Optional<Quiz> quizOpt = service.selectOneById(1);
		
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当する問題が存在しません");
		}
	}

	private void updateQuiz() {
		Quiz quiz1 = new Quiz(1, "「Spring」はフレームワークですか？", true, "変更タロウ");
		service.updateQuiz(quiz1);
		System.out.println(quiz1);
	}

	private void deleteQuiz() {
		service.deleteQuiz(2);
	}

	private void doQuiz() {
		
		Optional<Quiz> quiz = service.selectOneRandomQuiz();
		if (quiz.isPresent()) {
			System.out.println(quiz.get());
			
			Boolean myAnswer = false;
			Integer id = quiz.get().getId();
			
			if (service.checkQuiz(id, myAnswer) ) {
				System.out.println("正解です");
			} else {
				System.out.println("不正解です。。。");
			}

		} else {
			System.out.println("該当する問題が存在しません。。。");
		}
		
	}
	
}
