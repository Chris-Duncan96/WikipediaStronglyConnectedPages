/**
 * @author Eric Rysavy, Chris Duncan
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;

public class WikiCrawler {
	
	public static final String BASE_URL = "https://en.wikipedia.org";
	private int maxPages;
	private File outputFile;
	private URL basePage;
	/*
	 * Constructor with parameters:
	 * 1. Relative address of the seed url (within Wiki domain).
	 * 2. Maximum number of pages to be crawled.
	 * 3. Name of the file the graph will be written to.
	 */
	WikiCrawler(String seedUrl, int max, String fileName) {
		maxPages = max;
		//outputFile = new File(fileName);//TODO is this right?
		try {
			basePage = new URL(BASE_URL + seedUrl);
		} catch (MalformedURLException e) {
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
	static ArrayList<String> extractLinks(String doc) {
		ArrayList<String> list = new ArrayList<String>();
		boolean hasNotReachedFirstParagraph = true;
		for(String token : doc.split("\"")){
			if(hasNotReachedFirstParagraph){ // Do not check tokens until after paragraph has been reached
				if(token.contains("<p>") || token.contains("<P>")){
					hasNotReachedFirstParagraph = false;
				}
				continue;
			}
			
			if(token.contains("/wiki/") && !token.contains(":") && !token.contains("#")){
				System.out.println(token.trim());
			}
		}

		return list;
	}
	
	/*
	 * This method should construct the web graph over following pages: Consider the first
	 * max many pages that are visited when you do a BFS with seedUrl. Your program should construct
	 * the web graph only over those pages. and writes the graph to the file fileName.
	 */
	void crawl() throws IOException {//TODO
		extractLinks(extractPageString(this.basePage));
	}
	
	/* Takes a URL, returns the page as a string.
	 */
	private static String extractPageString(URL url) throws IOException{
		InputStream stream = url.openStream();
		Scanner scanner = new Scanner(stream).useDelimiter("\\A");
		String stringToReturn = scanner.hasNext() ? scanner.next() : "";
		stream.close();
		scanner.close();
		return stringToReturn;
	}
	
	
	/*Search a given token for urls
	 */
	private static ArrayList<String> searchTokenForURLS(String token){//TODO
		
		return null;
	}
	
	
	/*Main just for testing. Remove when finished.
	 */
	public static void main(String[] args){
		WikiCrawler wc = new WikiCrawler("/wiki/Computer_Science", 500, null);
		try {
			wc.crawl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
