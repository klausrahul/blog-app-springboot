package com.rahul.blog.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "User_Comments", length = 100,nullable = false)
	private String comment;
	
	
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	
	
	
	  @ManyToOne private User user;
	  
	  
	  
	  
	  @Override public String toString() { return "Comment [id=" + id +
	  ", comment=" + comment + ", post=" + post + ", user=" + user + "]"; }
	 
	 
	 
	
	
	
	

}
