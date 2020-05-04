package au.edu.unsw.infs3634.courseoutliner;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    List<Course> getCourses();

    //TODO Implement a Room query that return a course with a specified id


    //TODO Implement a Room query that returns all courses from a specified school

}
