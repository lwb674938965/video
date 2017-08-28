package com.zhiyou100.video.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.CourseMapper;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Packing;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {
  
	
	@Autowired
	CourseMapper cm;
	
	
	@Override
	public Page<Course> findAllCourseByConditions(int currentPage) {
		Page<Course> page = new Page<>();
		page.setPage(currentPage);
		page.setSize(5);
		Packing pack = new Packing();
		pack.setCurrentPage((currentPage-1)*5);
		page.setTotal(cm.finAllCourseCount());
		page.setRows(cm.findAllCourse(pack));
		//System.out.println(cm.finAllCourseCount());
		//System.out.println(cm.findAllCourse(pack));
		
		
		return page;
	}

	@Override
	public Course findCourseById(int id) {
		
		return cm.selectByPrimaryKey(id);
	}

	@Override
	public void editCourse(Course course) {
		course.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cm.updateByPrimaryKeySelective(course);
		
	}

	@Override
	public void addCourse(Course course) {
		course.setInsertTime(new Timestamp(System.currentTimeMillis()));
		cm.insertSelective(course);
		
	}

	@Override
	public void deleteCourseById(int id) {
		cm.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Course> findAllCourse() {
		
		return cm.selectByExample(null);
	}
}
