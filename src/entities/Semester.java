package entities;

import enums.TypeSemester;

import java.util.HashMap;

public class Semester {

	private HashMap<Course, Double> attestations1;

	private double GPA;

	private int credits;

	private int semesterNum;

	private TypeSemester semesterType;

	private HashMap<Course, Double> attestation2;

	private HashMap<Course, Double> totalAttestation;

	private Student student;

	public TypeSemester getSemesterType() {
		return null;
	}

	public void getFirstAttestation(Course c) {

	}

	public void getSecondAttestation(Course c) {

	}

	public void getTotalAttestation(Course c) {

	}

}