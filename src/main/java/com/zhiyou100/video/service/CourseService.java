package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;

public interface CourseService {

	
	Page<Course> findAllCourseByConditions(int currentPage);

	Course findCourseById(int id);

	void editCourse(Course course);

	void addCourse(Course course);

	void deleteCourseById(int id);

	List<Course> findAllCourse();

	List<Course> findCoursesById(Integer subjectId);
}
