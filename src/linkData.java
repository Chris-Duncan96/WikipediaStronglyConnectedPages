import java.util.ArrayList;

class linkData{
	public String startLinkString;
	public ArrayList<String> endLinksArrayList;
	
	linkData(String start, ArrayList<String> end){
		startLinkString = start;
		endLinksArrayList = end;
	}
	
	linkData(String start){
		startLinkString = start;
		endLinksArrayList = new ArrayList<String>();
	}
	
	public boolean containsEndLink(String link){
		return endLinksArrayList.contains(link);
	}
	
	@Override
	public int hashCode() {
	    return startLinkString.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
	    if(o instanceof linkData){
	        String toCompare = ((linkData) o).startLinkString;
	        return startLinkString.equals(toCompare);
	    }
	    return false;
	}
}

final class node extends linkData{
	private int finishTime;
	private int outDegree;
	private boolean flagged;
	public ArrayList<node> pointsAt;
	public ArrayList<node> pointedToBy;
	
	node(String start) {
		super(start);
		flagged = false;
		pointsAt = new ArrayList<node>();
		pointedToBy= new ArrayList<node>();
	}
	
	node(String start, ArrayList<String> end){
		super(start,end);
		flagged = false;
		pointsAt = new ArrayList<node>();
		pointedToBy= new ArrayList<node>();
	}

	
	public void flag(){
		flagged = true;
	}
	
	public boolean isFlagged(){
		return flagged;
	}
	
	public boolean linkEquals(String input){
		return (input.equals(this.startLinkString));
	}
	
	public void unflag(){
		flagged = false;
	}
	
	
}