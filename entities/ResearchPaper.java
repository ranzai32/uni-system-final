package UniversitySystem.entities;

import UniversitySystem.common.Date;
import UniversitySystem.decorators.ResearcherDecorator;

import java.util.Objects;

public class ResearchPaper extends ResearcherDecorator {

	private String title;

	private int pages;

	private Date publishedDate;

	private int citations;

	private ResearcherDecorator researcher;

	public ResearchPaper(String title, ResearcherDecorator researcher, int citations, Date publishedDate, int pages) {
		this.title = title;
		this.researcher = researcher;
		this.citations = citations;
		this.publishedDate = publishedDate;
		this.pages = pages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResearchPaper)) return false;
		ResearchPaper that = (ResearchPaper) o;
		return getPages() == that.getPages() && getCitations() == that.getCitations() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getPublishedDate(), that.getPublishedDate()) && Objects.equals(researcher, that.researcher);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTitle(), getPages(), getPublishedDate(), getCitations(), researcher);
	}

	@Override
	public String toString() {
		return "ResearchPaper{" +
				"title='" + title + '\'' +
				", pages=" + pages +
				", publishedDate=" + publishedDate +
				", citations=" + citations +
				", researcher=" + researcher +
				'}';
	}

	public String getTitle() {
		return null;
	}

	public void setTitle(int String) {

	}

	public int getPages() {
		return 0;
	}

	public Date getPublishedDate() {
		return null;
	}

	public int getCitations() {
		return 0;
	}

//	public String getCitation(TypeFormat f) {
//		return null;
//	}

}
