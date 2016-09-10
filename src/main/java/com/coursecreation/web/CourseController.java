package com.coursecreation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coursecreation.domain.Course;
import com.coursecreation.domain.Lesson;
import com.coursecreation.repository.CourseRepository;

@Controller
public class CourseController {

	private CourseRepository courseRepo;

	@RequestMapping("/")
	public String root() {
		return "redirect:/courses";
	}

	@RequestMapping(value="/courses", method=RequestMethod.GET)
	public String courses(ModelMap model) {
		List<Course> courses = courseRepo.findAll();
		model.put("courses", courses);
		return "courses";
	}

	@RequestMapping(value="createCourse", method=RequestMethod.GET)
	public String createCourseGet (ModelMap model) {
		Course course = new Course();
		model.put("course", course);
		return "createCourse";
	}
	
	@RequestMapping(value="createCourse", method=RequestMethod.POST)
	public String createCoursePost (@ModelAttribute Course course, ModelMap model) {
		courseRepo.save(course);
		return "redirect:/";
	}
	
	@RequestMapping(value="editCourse/{courseId}", method=RequestMethod.GET)
	public String editCourseGet (@PathVariable Long courseId, ModelMap model) {
		Course course = courseRepo.findOne(courseId);
		
		model.put("course", course);
		model.put("lessons", course.getLessons());
		return "editCourse";
	}
	
	@RequestMapping(value="editCourse/addLesson/{courseId}", method=RequestMethod.GET)
	public String addLessonGet (@PathVariable Long courseId, ModelMap model) {
		Course course = courseRepo.findOne(courseId);
		model.put("course", course);
		model.put("lesson", new Lesson());
		return "addLesson";
	}
	
	@RequestMapping(value="editCourse/addLesson/{courseId}", method=RequestMethod.POST)
	public String addLessonPost (@ModelAttribute Lesson lesson, @PathVariable Long courseId, ModelMap model) {
		Course course = courseRepo.findOne(courseId);
		lesson.setCourse(course);
		course.getLessons().add(lesson);
		courseRepo.save(course);
		return "redirect:/editCourse/" + courseId;
	}


	@Autowired
	public void setCourseRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
}
