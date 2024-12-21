//package UniversitySystem;
//
//import java.util.Objects;
//
//public class ResearchPaperComparators {
//
//	public Comparator<ResearchPaper> byDate;
//
//	public Comparator<ResearchPaper> byCitations;
//
//	public Comparator<ResearchPaper> byPages;
//
//	public ResearchPaperComparators(Comparator<ResearchPaper> byDate, Comparator<ResearchPaper> byPages, Comparator<ResearchPaper> byCitations) {
//		this.byDate = byDate;
//		this.byPages = byPages;
//		this.byCitations = byCitations;
//	}
//
//	public Comparator<ResearchPaper> getByDate() {
//		return byDate;
//	}
//
//	public void setByDate(Comparator<ResearchPaper> byDate) {
//		this.byDate = byDate;
//	}
//
//	public Comparator<ResearchPaper> getByPages() {
//		return byPages;
//	}
//
//	public void setByPages(Comparator<ResearchPaper> byPages) {
//		this.byPages = byPages;
//	}
//
//	public Comparator<ResearchPaper> getByCitations() {
//		return byCitations;
//	}
//
//	public void setByCitations(Comparator<ResearchPaper> byCitations) {
//		this.byCitations = byCitations;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (!(o instanceof ResearchPaperComparators)) return false;
//		ResearchPaperComparators that = (ResearchPaperComparators) o;
//		return Objects.equals(getByDate(), that.getByDate()) && Objects.equals(getByCitations(), that.getByCitations()) && Objects.equals(getByPages(), that.getByPages());
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(getByDate(), getByCitations(), getByPages());
//	}
//
//	@Override
//	public String toString() {
//		return "ResearchPaperComparators{" +
//				"byDate=" + byDate +
//				", byCitations=" + byCitations +
//				", byPages=" + byPages +
//				'}';
//	}
//}
