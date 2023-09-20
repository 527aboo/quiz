package com.example.quiz.service;

import java.util.Optional;

import com.example.quiz.entity.Quiz;

public interface QuizService {
	
	Iterable<Quiz> findAll();
	
	Optional<Quiz> selectOneById(Integer id);
	
	Optional<Quiz> selectOneRandomQuiz();
	
	Boolean checkQuiz(Integer id, Boolean myAnswer);
	
	void insertQuiz(Quiz quiz);
	
	void updateQuiz(Quiz quiz);
	
	void deleteQuiz(Integer id);

}
