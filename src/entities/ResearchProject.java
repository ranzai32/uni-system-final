package entities;

import users.User;
import decorators.*;
import java.util.Objects;
import java.util.Vector;

public class ResearchProject {

	private String topic;

	private Vector<ResearchPaper> publishedPapers;

	private Vector<ResearcherDecorator> projectMembers;

	public ResearchProject(String topic, Vector<ResearcherDecorator> projectMembers, Vector<ResearchPaper> publishedPapers) {
		this.topic = topic;
		this.projectMembers = projectMembers;
		this.publishedPapers = publishedPapers;
	}

	public String getTopic() {
		return topic;
	}

	public void addMember(ResearcherDecorator r) {
		projectMembers.add(r);
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Vector<ResearcherDecorator> getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(Vector<ResearcherDecorator> projectMembers) {
		this.projectMembers = projectMembers;
	}

	public Vector<ResearchPaper> getPublishedPapers() {
		return publishedPapers;
	}

	public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
		this.publishedPapers = publishedPapers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResearchProject)) return false;
		ResearchProject that = (ResearchProject) o;
		return Objects.equals(getTopic(), that.getTopic()) && Objects.equals(getPublishedPapers(), that.getPublishedPapers()) && Objects.equals(getProjectMembers(), that.getProjectMembers());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTopic(), getPublishedPapers(), getProjectMembers());
	}

	@Override
	public String toString() {
		return "ResearchProject{" +
				"topic='" + topic + '\'' +
				", publishedPapers=" + publishedPapers +
				", projectMembers=" + projectMembers +
				'}';
	}
}
