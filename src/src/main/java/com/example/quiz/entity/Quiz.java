package com.example.quiz.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("quiz")
public class Quiz {
	
	@Id
	private Integer id;
	
	private String question;
	
	private Boolean answer;
	
	private String author;

}
