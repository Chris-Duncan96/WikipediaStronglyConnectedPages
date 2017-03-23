/**
 * @author Eric Rysavy, Chris Duncan
 */

import java.util.ArrayList;

public class WikiCrawler {
	
	public static final String BASE_URL = "https://en.wikipedia.org";
	
	/*
	 * Constructor with parameters:
	 * 1. Relative address of the seed url (within Wiki domain).
	 * 2. Maximum number of pages to be crawled.
	 * 3. Name of the file the graph will be written to.
	 */
	WikiCrawler(String seedUrl, int max, String fileName) {
		
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
	ArrayList<String> extractLinks(String doc) {
		return null;
	}
	
	/*
	 * This method should construct the web graph over following pages: Consider the first
	 * max many pages that are visited when you do a BFS with seedUrl. Your program should construct
	 * the web graph only over those pages. and writes the graph to the file fileName.
	 */
	void crawl() {
		
	}
}
