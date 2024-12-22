package decorators;

import enums.*;
import users.*;
import entities.*;
import interfaces.*;
import java.util.*;

import common.Comment;
import common.News;
import database.DataBase;

public class ResearcherDecorator implements ManageNews {

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

	public void makeNew(String title, String topic, String content, Languages language) {
		News news = new News(title, topic, content, language, this.getUser());
	}
	public void makeNew(ResearchPaper p) {
		News news = new News(p.getTitle(), p.getTopic(), p.getContent(), Languages.EN, this.getUser());
	}
	public void deleteNew(News n) {
		if (n != null) {
			if (n.getAuthor().equals(this.getUser())) {
				DataBase.getInstance().deleteNew(n);
				System.out.println("Новость удалена.");
				Admin.getInstance().addLog("Пользователь " + this.getUser().getId() + " удалил новость " + n.getTitle() + ".");
			} else {
				System.out.println("Вы можете удалить только Вашу новость.");
			}
		} else {
			System.out.println("Новость не найдена.");
		}
	}
	public void deleteComment(Comment c) {
		if (c != null) {
			News news = c.getNews();
			if (news != null && c.getNews().getAuthor().equals(this.getUser())) {
				news.removeComment(c);
				System.out.println("Комментарий удалён.");
				Admin.getInstance().addLog("Пользователь " + this.getUser().getId() + " удалил комментарий из новости " + news.getTitle() + ".");
			} else {
				System.out.println("Вы можете удалить комментарии только под Вашими новостями.");
			}
		} else {
			System.out.println("Новость не найдена.");
		}
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