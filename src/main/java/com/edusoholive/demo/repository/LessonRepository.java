package com.edusoholive.demo.repository;

import com.edusoholive.demo.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
