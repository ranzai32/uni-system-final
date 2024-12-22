package decorators;

import enums.*;
import users.User;
import entities.*;
import java.util.*;

public class ResearcherDecorator {

	private int hIndex;
	private Vector<ResearchPaper> researchPapers;
	private Vector<ResearchProject> researchProjects;
	private Faculty school;
	private User researcher;

	public ResearcherDecorator(User user, Faculty f) {
		this.researcher = user;
		this.school = f;
		user.setResearcher(true);
		researchPapers = new Vector<>();
		researchProjects = new Vector<>();
	}
	
	public void printPapers(Comparators c) {
		if(c == Comparators.byCitations) {
			Collections.sort(researchPapers, ResearchPaperComparators.byCitations);
		}
		else if(c == Comparators.byDate) {
			Collections.sort(researchPapers, ResearchPaperComparators.byCitations);
		}
		else if(c == Comparators.byPages) {
			Collections.sort(researchPapers, ResearchPaperComparators.byPages);
		}
		for(int i = 0; i < researchPapers.size(); i++) {
			researchPapers.get(i).toString();
		}
	}

	public void calculateHIndex() {
		if (researchPapers == null || researchPapers.isEmpty()) {
	        hIndex = 0; 
	        return;
	    }
	    List<Integer> citationsList = new ArrayList<>();
	    for (ResearchPaper paper : researchPapers) {
	        citationsList.add(paper.getCitations());
	    }
	    citationsList.sort(Collections.reverseOrder());
	    int h = 0;
	    for (int i = 0; i < citationsList.size(); i++) {
	        if (citationsList.get(i) >= i + 1) {
	            h = i + 1; 
	        } 
	        else {
	            break;
	        }
	    }

	    this.hIndex = h;
	}

	public void addResearchPaper(ResearchPaper p) {
		researchPapers.add(p);
		this.calculateHIndex();
	}

	public void addResearchProject(ResearchProject p) {
		researchProjects.add(p);
		for(int i = 0; i < p.getPublishedPapers().size(); i++) {
			researchPapers.add(p.getPublishedPapers().get(i));
		}
		this.calculateHIndex();
	}
	
	public Faculty getSchool() {
		return this.school;
	}

	public User getUser() {
		return researcher;
	}
	public void joinProject(ResearchProject p) {
		researchProjects.add(p);
		p.addMember(this);
	}
	
	public int getHIndex() {
		return hIndex;
	}

}