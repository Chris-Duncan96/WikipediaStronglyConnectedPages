/**
 * @author Eric Rysavy, Chris Duncan
 */

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;
public class GraphProcessor {

	private ArrayList<linkData> rawData;
	private ArrayList<node> graph;
	private ArrayList<node> orderedGraph;
	private ArrayList<node> reverseGraph;
	private ArrayList<ArrayList<String>> SCCList;
	private int graphSize;
	
	public static void main(String[] args) throws IOException{
		try{
			GraphProcessor gp = new GraphProcessor("TestOutput.txt");
			//gp.printGraph();
			//gp.printOrderedGraph();
			//gp.printReverseGraph();
			//gp.printSCC();
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
		rawData = new ArrayList<linkData>();
		graph = new ArrayList<node>();
		orderedGraph = new ArrayList<node>();
		reverseGraph = new ArrayList<node>();
		SCCList = new ArrayList<ArrayList<String>>();
		generateRawData(graphData);
		generateGraph();
		orderGraph();
		makeSCC();
		//generateReverseGraph();
		//orderGraph();
		//generateSCCList();
	}
	
	/* Generates graph based on file data
	 * 
	 */
	private void generateRawData(String graphData) throws IOException{
		File inputFile = new File(graphData);
		Scanner fileReader = new Scanner(inputFile);
		String nextLine;
		String[] currentLineTokens;
		linkData currentVertex = null;
		
		nextLine = fileReader.nextLine(); 
		graphSize = Integer.parseInt(nextLine);
		
		while(fileReader.hasNextLine()){
			nextLine = fileReader.nextLine();
			if("" == nextLine.trim()){
				break;
			}
			currentLineTokens = nextLine.split(" ");
			
			if(null != currentVertex && !currentLineTokens[0].equalsIgnoreCase(currentVertex.startLinkString)){
				rawData.add(currentVertex);
				/*for(String string : currentVertex.endLinksArrayList){
					System.out.println(currentVertex.startLinkString+ " "+ string);
				}*/
			}
			
			if(null == currentVertex || !currentLineTokens[0].equalsIgnoreCase(currentVertex.startLinkString)){
				currentVertex = new node(currentLineTokens[0]);
			}
			currentVertex.endLinksArrayList.add(currentLineTokens[1]);	
		}
		
		rawData.add(currentVertex);
		fileReader.close();
	}
	
	private void generateGraph(){
		for(linkData data : rawData){
			graph.add(new node(data.startLinkString, data.endLinksArrayList));
		}
		
		int index = -1;
		ArrayList<node> graphCopy = (ArrayList<node>) graph.clone();
		for(node thisNode : graphCopy){
			for(String link : thisNode.endLinksArrayList){
				index = graph.indexOf(new node(link));
				if(-1 != index){
					node nodePointedTo = graph.get(index);
					thisNode.pointsAt.add(nodePointedTo);
					nodePointedTo.pointedToBy.add(thisNode);
				}
				else{
					node tempNode = new node(link);
					tempNode.pointedToBy.add(thisNode);
					graph.add(new node(link));
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void printGraph(){
		System.out.println(graphSize);
		for(node vertex: graph){
			for(String otherNode: vertex.endLinksArrayList)
				System.out.println(vertex.startLinkString + " " + otherNode);
		}
	}
	
	@SuppressWarnings("unused")
	private void printOrderedGraph(){
		System.out.println(graphSize);
		for(node vertex: orderedGraph){
			System.out.println(vertex.startLinkString);
		}
	}
	
	@SuppressWarnings("unused")
	private void printReverseGraph(){
		System.out.println(graphSize);
		for(linkData vertex: reverseGraph){
			for(String link: vertex.endLinksArrayList){
				System.out.println(vertex.startLinkString + " " + link);
			}
		}
	}
	
	private void printSCC(){
		int count = 1;
		for(ArrayList<String> SCC : SCCList){
			for(String link : SCC){
				System.out.println(link);
			}
			System.out.println("End SCC " + count++);
		}
			
	}
	
	private void orderGraph(){
		for(node vertex : graph){
			orderGraphHelper(vertex);
		}
		Collections.reverse(orderedGraph);
	}
	
	private void orderGraphHelper(node currentVertex){
		if(currentVertex == null || currentVertex.isFlagged()){
			return;
		}
		currentVertex.flag();
		for(node otherNode: currentVertex.pointedToBy){
			if(null == otherNode)
				continue;
			orderGraphHelper(otherNode);
		}
		orderedGraph.add(currentVertex);
	}
	
	private void makeSCC(){
		for(node vertex : orderedGraph){
			vertex.unflag();
		}
		
		for(node vertex : orderedGraph){
			ArrayList<String> SCC = new ArrayList<String>();
			makeSCCHelper(vertex, SCC);
			if(!SCC.isEmpty())
				SCCList.add(SCC);
		}
	}
	
	private void makeSCCHelper(node currentVertex, ArrayList<String> SCC){
		if(currentVertex == null || currentVertex.isFlagged()){
			return;
		}
		
		currentVertex.flag();
		for(node otherNode: currentVertex.pointsAt){
			if(null == otherNode)
				continue;
			makeSCCHelper(otherNode, SCC);
		}
		SCC.add(currentVertex.startLinkString);
		
	}

	
	/*
	 * Returns the out degree of v.
	 */
 	int outDegree(String v) {//TODO check this works
 		int index = orderedGraph.indexOf(new node(v));
 		node nodeToCheck = orderedGraph.get(index);
 		for(node toPrint : nodeToCheck.pointsAt)
 			System.out.println(toPrint.startLinkString);
		return nodeToCheck.pointsAt.size();
	}
	
	/*
	 * Returns true if u and v belong to the same SCC.
	 */
	boolean sameComponent(String u, String v) {//TODO check this works
		for(ArrayList<String> SCC : SCCList){
			if(SCC.contains(v)){
				return SCC.contains(u);
			}
		}
		return false;
	}
	
	/*
	 * Return all the vertices that belong to the same SCC as v, including v.
	 */
	ArrayList<String> componentVertices(String v) {//TODO check this works
		for(ArrayList<String> SCC : SCCList){
			if(SCC.contains(v)){
				return SCC;
			}
		}
		return null;
	}
	
	/*
	 * Returns the size of the largest component.
	 */
	int largestComponent() {//TODO check this works
		int largest = -1;
		for(ArrayList<String> SCC : SCCList){
			if (largest < SCC.size()){
				largest = SCC.size();
			}
		}
		return largest;
	}
	
	/*
	 * Returns the number of strongly connected components.
	 */
	int numComponents() {//TODO what
		//return SCCList.size();
		return 0;
	}
	
	/*
	 * Returns the BFS path from u to v. This method returns an ArrayList of strings that represents 
	 * the BFS path from u to v.  First vertex in the path must be u and the last vertex must be v. If 
	 * there is no path from u to v, then this method returns an empty list.
	 */
	ArrayList<String> bfsPath(String u, String v) {//TODO
		return null;
	}
}
