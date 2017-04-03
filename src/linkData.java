import java.util.ArrayList;

class linkData{
	public String startLinkString;
	public ArrayList<String> endLinksArrayList;
	
	linkData(String start, ArrayList<String> end){
		startLinkString = start;
		endLinksArrayList = end;
	}
	
	@Override
	public boolean equals(Object o){
		linkData otherTuple = (linkData)o;
		return (otherTuple.startLinkString.equalsIgnoreCase(this.startLinkString));
	}
	
	public boolean containsEndLink(String link){
		return endLinksArrayList.contains(link);
	}
}