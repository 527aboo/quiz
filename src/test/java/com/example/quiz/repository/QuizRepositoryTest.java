package com.example.quiz.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.quiz.entity.Quiz;

@DataJpaTest
class QuizRepositoryTest {
	
	@Autowired
	private QuizRepository repository;

	@Test
	void 登録テスト() {
		Quiz quiz1 = new Quiz(null, "「Spring」はフレームワークですか", true, "登録太郎");
		quiz1 = repository.save(quiz1);
		
		Quiz quiz2 = new Quiz(null, "「Spring MVC」はバッチ処理機能を提供しますか", false, "登録太郎");
		quiz2 = repository.save(quiz2);
	}
	
	@Test 
	void 全件取得テスト() {
		Iterable<Quiz> quizzes = repository.findAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
	}
	

}
