package decorators;
import entities.*;
import java.util.Comparator;
public class ResearchPaperComparators {
	public static final Comparator<ResearchPaper> byDate = (paper1, paper2) -> 
    	paper1.getPublishedDate().compareTo(paper2.getPublishedDate());
    
    public static final Comparator<ResearchPaper> byCitations = (paper1, paper2) -> 
    	Integer.compare(paper1.getCitations(), paper2.getCitations());

    public static final Comparator<ResearchPaper> byPages = (paper1, paper2) -> 
    	Integer.compare(paper1.getPages(), paper2.getPages());
}
