/**
 * @author Eric Rysavy, Chris Duncan
 */

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class WikiCrawler {
	
	public static final String BASE_URL = "https://en.wikipedia.org";
	private int pagesLeftToVisitCount;
	private File outputFile;
	private PrintWriter writer;
	private String seedUrl;
	private ArrayList<linkData> visitedUrlData;
	private ArrayList<String> vistedUrls;
	private ArrayList<String> urlsToVist;
	/*
	 * Constructor with parameters:
	 * 1. Relative address of the seed url (within Wiki domain).
	 * 2. Maximum number of pages to be crawled.
	 * 3. Name of the file the graph will be written to.
	 */
	WikiCrawler(String seedUrl, int max, String fileName) {
		pagesLeftToVisitCount = max;
		outputFile = new File(fileName);
		this.seedUrl = seedUrl;
		visitedUrlData = new ArrayList<linkData>();
		urlsToVist = new ArrayList<String>();
		vistedUrls = new ArrayList<String>();
		try {
			writer = new PrintWriter(outputFile);
			writer.println(max);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Gets a string that represents the contents of a .html file as
	 * a parameter. Should return an ArrayList of Strings consisting
	 * of links from the doc. Method must:
	 * 1. Extract only wiki links, I.e. only links that are of form /wiki/XXXXX.
	 * 2. Only extract links that appear after the first occurence of the html tag <p> or <P>.
	 * 3. Should not extract any wiki link that contains the characters "#" or ":".
	 * 4. The order of the links in the returned ArrayList must be the same order they appear in the doc.
	 */
	private static ArrayList<String> extractLinksFromUrlString(String urlString) throws IOException {
		String pageDocument = urlStringToDocString(BASE_URL + urlString);
		ArrayList<String> list = new ArrayList<String>();
		boolean hasNotReachedFirstParagraph = true;
		for(String token : pageDocument.split("\"")){
			token = token.trim();
			if(hasNotReachedFirstParagraph){ // Do not check tokens until after paragraph has been reached
				if(token.contains("<p>") || token.contains("<P>")){
					hasNotReachedFirstParagraph = false;
				}
				continue;
			}
			if(token.length() <= 7)
				continue;
			
			if(token.substring(0,6).equalsIgnoreCase("/wiki/") && !token.contains(":") && !token.contains("#")){
				if(list.isEmpty()){
					list.add(token);
				}
				else if(!list.contains(token)){
					list.add(token);
				}
			}
		}

		return list;
	}
	
	/*
	 * This method should construct the web graph over following pages: Consider the first
	 * max many pages that are visited when you do a BFS with seedUrl. Your program should construct
	 * the web graph only over those pages. and writes the graph to the file fileName.
	 */
	void crawl(){
		amassData();
		parseData();
		printDataToDoc();
	}
	
	private void amassData(){
		try{
			visitUrl(seedUrl);
		}
		catch(IOException e){
			System.out.print("IO Exception on first link");
			return;
		}
		int pagesHitCount = 1;
		while(pagesLeftToVisitCount > 0){
			if(0 == pagesHitCount %100){
				try {
				    Thread.sleep(3000);//MUST WAIT 3 SECONDS PER 100 HITS
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
			pagesHitCount++;
			
			try {
				visitUrl(urlsToVist.get(0));
				urlsToVist.remove(0);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	private void parseData(){
		ArrayList<String> toRemove;
		for(linkData dataBlock: visitedUrlData){
			System.out.println(dataBlock.startLinkString);
			toRemove = new ArrayList<String>();
			for(String link : dataBlock.endLinksArrayList){
				if(!vistedUrls.contains(link) || link.equalsIgnoreCase(dataBlock.startLinkString)){
					toRemove.add(link);
					System.out.println(link);
				}
			}
			dataBlock.endLinksArrayList.removeAll(toRemove);
		}
	}
	
	private void printDataToDoc(){
		for(linkData dataBlock: visitedUrlData){
			for(String link : dataBlock.endLinksArrayList){
				writer.println(dataBlock.startLinkString + " " + link);
				writer.flush();
			}
		}
	}
	
	private void visitUrl(String url) throws IOException{
		vistedUrls.add(url);
		ArrayList<String> links;
		links = extractLinksFromUrlString(url);
		visitedUrlData.add(new linkData(url, links));
		pagesLeftToVisitCount--;
		for(String link : links){
			if(!visitedUrlData.contains(link) && !urlsToVist.contains(link) && !link.equalsIgnoreCase(url)){
				urlsToVist.add(link);
			}
		}
	}
	
	
	
	/* Takes a URL, returns the page as a string.
	 */
	private static String urlStringToDocString(String urlString) throws IOException{
		URL url = new URL(urlString);
		InputStream stream = url.openStream();
		Scanner scanner = new Scanner(stream).useDelimiter("\\A");
		String stringToReturn = scanner.hasNext() ? scanner.next() : "";
		stream.close();
		scanner.close();
		return stringToReturn;
	}
	
	
	/*Main just for testing. Remove when finished.
	 */
	public static void main(String[] args){
		WikiCrawler wc = new WikiCrawler("/wiki/Complexity_theory", 20, "TestOutput.txt");
		wc.crawl();
	}
}

final class linkData{
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
