package UniversitySystem;

import java.time.DayOfWeek;
import java.util.Objects;

public class Lesson {
    private TypeLesson type;
    private int duration;
    private int room;
    private int begin;
    private Course course;
    private DayOfWeek day;

    enum TypeLesson {
        LECTURE,
        PRACTICE,
        LABORATORY
    }

    public Lesson(TypeLesson type, int duration, int room, int begin, Course course, DayOfWeek day) {
        this.type = type;
        this.duration = duration;
        this.room = room;
        this.begin = begin;
        this.course = course;
        this.day = day;
    }

    public TypeLesson getType() {
        return type;
    }

    public void setType(TypeLesson type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + type +
                ", duration=" + duration +
                ", room=" + room +
                ", begin=" + begin +
                ", course=" + course +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return duration == lesson.duration && room == lesson.room && begin == lesson.begin && type == lesson.type && Objects.equals(course, lesson.course) && day == lesson.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, duration, room, begin, course, day);
    }
}