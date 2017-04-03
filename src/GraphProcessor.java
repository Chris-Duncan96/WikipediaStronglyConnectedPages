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
public class GraphProcessor {

	private ArrayList<linkData> graph;
	private int graphSize;
	private ArrayList<linkData> reverseGraph;
	private ArrayList<ArrayList<String>> SCCList;
	
	public static void main(String[] args) throws IOException{
		try{
			GraphProcessor gp = new GraphProcessor("TestOutput.txt");
			gp.printReverseGraph();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * Constructor. graphData holds the absolute path of a file that stores a directed graph. 
	 * This file will be of the following format: First line indicates number of vertices.
	 * Each subsequent line lists a directed edge of the graph. The vertices of this graph 
	 * are represented as strings. This class should create efficient data structures so that 
	 * the following public methods run efficiently.
	 */
	GraphProcessor(String graphData) throws IOException {
		generateGraph(graphData);
		generateReverseGraph();
		//generateSCCList();
	}
	
	/* Generates graph based on file data
	 * 
	 */
	private void generateGraph(String graphData) throws IOException{
		graph = new ArrayList<linkData>();
		File inputFile = new File(graphData);
		Scanner fileReader = new Scanner(inputFile);
		String nextLine;
		String[] currentLineTokens;
		linkData currentVertex = null;
		
		nextLine = fileReader.nextLine(); //TODO This  the number of vertexes. How can this be used?
		graphSize = Integer.parseInt(nextLine);
		
		while(fileReader.hasNextLine()){
			nextLine = fileReader.nextLine();
			if("" == nextLine.trim()){
				break;
			}
			currentLineTokens = nextLine.split(" ");
			
			if(null != currentVertex && currentLineTokens[0] != currentVertex.startLinkString){
				graph.add(currentVertex);
			}
			
			if(null == currentVertex || currentLineTokens[0] != currentVertex.startLinkString){
				currentVertex = new linkData(currentLineTokens[0], new ArrayList<String>());
			}
			//System.out.println(currentVertex.startLinkString);
			currentVertex.endLinksArrayList.add(currentLineTokens[1]);	
		}
		
		graph.add(currentVertex);
		fileReader.close();
	}
	
	private void printGraph(){
		System.out.println(graphSize);
		for(linkData vertex: graph){
			for(String link: vertex.endLinksArrayList){
				System.out.println(vertex.startLinkString + " " + link);
			}
		}
	}
	
	private void printReverseGraph(){
		System.out.println(graphSize);
		for(linkData vertex: reverseGraph){
			for(String link: vertex.endLinksArrayList){
				System.out.println(vertex.startLinkString + " " + link);
			}
		}
	}
	
	
	/* Makes the reverse of the other graph.
	 * Must be called after generateGraph
	 */
	private void generateReverseGraph(){
		reverseGraph = new ArrayList<linkData>();
		int count = 0;
		linkData temp;
		for(linkData vertex: graph){
			for(String link: vertex.endLinksArrayList){
				temp = new linkData(link,null);
				if(reverseGraph.contains(temp)){
					//System.out.println("This should print a lot");
					reverseGraph.get(reverseGraph.indexOf(temp)).endLinksArrayList.add(vertex.startLinkString);
				}
				else{
					temp.endLinksArrayList = new ArrayList<String>();
					//System.out.println(temp.startLinkString);
					temp.endLinksArrayList.add(vertex.startLinkString);
					reverseGraph.add(temp);
				}
			}
		}
	}
	
	/* Makes ArrayList of SCCs
	 * Must be called after generateGraph / generateReverseGraph
	 */
	private void generateSCCList(){
		
	}
	
	/*
	 * Returns the out degree of v.
	 */
 	int outDegree(String v) {
		return 0;
	}
	
	/*
	 * Returns true if u and v belong to the same SCC.
	 */
	boolean sameComponent(String u, String v) {
		return false;
	}
	
	/*
	 * Return all the vertices that belong to the same SCC as v, including v.
	 */
	ArrayList<String> componentVertices(String v) {
		return null;
	}
	
	/*
	 * Returns the size of the largest component.
	 */
	int largestComponent() {
		return 0;
	}
	
	/*
	 * Returns the number of strongly connected components.
	 */
	int numComponents() {
		return 0;
	}
	
	/*
	 * Returns the BFS path from u to v. This method returns an ArrayList of strings that represents 
	 * the BFS path from u to v.  First vertex in the path must be u and the last vertex must be v. If 
	 * there is no path from u to v, then this method returns an empty list.
	 */
	ArrayList<String> bfsPath(String u, String v) {
		return null;
	}
}
