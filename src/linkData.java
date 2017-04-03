import java.util.ArrayList;

class linkData{
	public String startLinkString;
	public ArrayList<String> endLinksArrayList;
	
	linkData(String start, ArrayList<String> end){
		startLinkString = start;
		endLinksArrayList = end;
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