package entities;

import java.util.*;
import enums.*;
import decorators.ResearcherDecorator;

import java.util.Objects;

public class ResearchPaper {

	private String title;

	private int pages;

	private Date publishedDate;

	private int citations;

	private Vector <ResearcherDecorator> researchers;

	public ResearchPaper(String title, int citations, Date publishedDate, int pages) {
		this.title = title;
		this.researchers = new Vector<>();
		this.citations = citations;
		this.publishedDate = publishedDate;
		this.pages = pages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResearchPaper)) return false;
		ResearchPaper that = (ResearchPaper) o;
		return getPages() == that.getPages() && getCitations() == that.getCitations() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getPublishedDate(), that.getPublishedDate()) && Objects.equals(researchers, that.researchers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTitle(), getPages(), getPublishedDate(), getCitations(), researchers);
	}

	@Override
	public String toString() {
		String s = "authors={";
		for(int i = 0; i < researchers.size(); i++) {
			s += researchers.get(i).getUser().getFullName();
			if(i != researchers.size() - 1) s += ", ";
		}
		s += "}\ntitle={" + getTitle() + "}\n";
		s += "date={" + publishedDate +"}\npages={" + pages + "}\ncitations={" + citations + "}\n";
		return s;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public int getCitations() {
		return citations;
	}

	public void addMember(ResearcherDecorator r) {
		researchers.add(r);
	}
	public String getCitation(TypeFormat f) {
		if(f == TypeFormat.BibTex) {
			String s = "authors={";
			for(int i = 0; i < researchers.size(); i++) {
				s += researchers.get(i).getUser().getFullName();
				if(i != researchers.size() - 1) s += ", ";
			}
			s += "}\ntitle={" + getTitle() + "}\n";
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(publishedDate);
			int year = calendar.get(Calendar.YEAR);
			s += "year={" + year +"}\npages={" + pages + "}\n";
			return s;
		}
		else {
			String s = "authors={";
			for(int i = 0; i < researchers.size(); i++) {
				s += researchers.get(i).getUser().getFullName();
				if(i != researchers.size() - 1) s += ", ";
			}
			s += "} title={" + getTitle() + "} ";
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(publishedDate);
			int year = calendar.get(Calendar.YEAR);
			s += "year={" + year +"} pages={" + pages + "}";
			return s;
		}
	}

}
