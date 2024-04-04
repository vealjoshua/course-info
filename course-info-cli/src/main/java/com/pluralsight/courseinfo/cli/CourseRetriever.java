package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import com.pluralsight.courseinfo.cli.service.CourseStorageService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import com.pluralsight.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {
    private static final Logger Log = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String ...args) {
        Log.info("CourseRetriever starting!");

        if (args.length == 0) {
            Log.warn("Please provide an author name as first argument.");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            Log.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        Log.info("Retrieving courses for author '{}'", authorId);

        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
                .filter(not(PluralsightCourse::isRetired))
                .toList();

        Log.info("Retrieved the following {} courses for {}", coursesToStore.size(), coursesToStore);
        courseStorageService.storePluralsightCourses(coursesToStore);
        Log.info("Courses successfully stored");
    }
}
