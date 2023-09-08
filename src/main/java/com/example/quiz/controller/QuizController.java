package com.example.quiz.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService service;
	
	@ModelAttribute
	public QuizForm setUpForm() {
		QuizForm form = new QuizForm();
		form.setAnswer(true);
		return form;
	}
	
	@GetMapping
	public String showList(QuizForm quizForm, Model model) {

		quizForm.setNewQuiz(true);
		
		Iterable<Quiz> list = service.findAll();
		
		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		
		return "crud";
	}
	
	@PostMapping("/insert")
	public String insert(
		@Validated QuizForm quizForm,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return showList(quizForm, model);
		}
		
		Quiz quiz = makeQuiz(quizForm);
		service.insertQuiz(quiz);
		redirectAttributes.addFlashAttribute("complete", "登録が完了しました。");
		return "redirect:/quiz";
	}
	
	@GetMapping("/{id}")
	public String showUpdate(
		QuizForm quizForm,
		@PathVariable Integer id,
		Model model) {
		
		Optional<Quiz> quizOpt = service.selectOneById(id);
		Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
		
		if (quizFormOpt.isPresent()) {
			quizForm = quizFormOpt.get();
		}
		
		makeUpdateModel(quizForm, model);
		return "crud";
	}
	
	@PostMapping("update")
	public String update(
		@Validated QuizForm quizForm,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			makeUpdateModel(quizForm, model);
			return "crud";
		}
		
		Quiz quiz = makeQuiz(quizForm);
		service.updateQuiz(quiz);
		redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
		return "redirect:/quiz/" + quiz.getId();
		
	}
	
	private Quiz makeQuiz(QuizForm quizForm) {
		Quiz quiz = new Quiz();
		quiz.setId(quizForm.getId());
		quiz.setQuestion(quizForm.getQuestion());
		quiz.setAnswer(quizForm.getAnswer());
		quiz.setAuthor(quizForm.getAuthor());
		return quiz;
	}
	
	private void makeUpdateModel(QuizForm quizForm, Model model) {
		model.addAttribute("id", quizForm.getId());
		quizForm.setNewQuiz(false);
		model.addAttribute("quizForm", quizForm);
		model.addAttribute("title", "更新用フォーム");		
	}

	private QuizForm makeQuizForm(Quiz quiz) {
		QuizForm quizForm = new QuizForm();
		quizForm.setId(quiz.getId());
		quizForm.setQuestion(quiz.getQuestion());
		quizForm.setAnswer(quiz.getAnswer());
		quizForm.setAuthor(quiz.getAuthor());
		quizForm.setNewQuiz(false);
		return quizForm;
	}

	@PostMapping("/delete")
	public String delte(
		@RequestParam("id") String id,
		Model model,
		RedirectAttributes redirectAttributes) {
	
		service.deleteQuiz(Integer.parseInt(id));
		
		redirectAttributes.addFlashAttribute("complete", "削除が完了しました");
		return "redirect:/quiz";
	
	}
	
}
