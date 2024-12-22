package decorators;

import enums.Faculty;
import users.User;
import entities.ResearchPaper;
import entities.ResearchProject;

import java.util.Vector;

public class ResearcherDecorator {

	private int hIndex;

	private Vector<ResearchPaper> researchPapers;

	private Vector<ResearchProject> researchProjects;

	private Faculty school;

	private User researcher;

//	public void printPapers(Comparator<ResearchPaper> c) {
//
//	}

	public void calculateHIndex() {

	}

	public void addResearchPaper(ResearchPaper p) {

	}

	public void addResearchProject(ResearchProject p) {

	}

	public ResearcherDecorator topResearcher(int f) {
		return null;
	}

	public ResearcherDecorator topResearcher() {
		return null;
	}

	public void joinProject(ResearchProject p) {

	}

}