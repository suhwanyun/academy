package com.example.comment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.example.comment.repo", "com.example.comment.beans"})
public class CommentConfig {

}
