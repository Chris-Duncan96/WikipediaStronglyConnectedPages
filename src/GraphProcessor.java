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

	private ArrayList<ArrayList<String>> graph;
	private ArrayList<ArrayList<String>> reverseGraph;
	private ArrayList<ArrayList<String>> SCCList;
	/*
	 * Constructor. graphData holds the absolute path of a file that stores a directed graph. 
	 * This file will be of the following format: First line indicates number of vertices.
	 * Each subsequent line lists a directed edge of the graph. The vertices of this graph 
	 * are represented as strings. This class should create efficient data structures so that 
	 * the following public methods run efficiently.
	 */
	GraphProcessor(String graphData) {
		generateGraph(graphData);
		generateReverseGraph();
		generateSCCList();
	}
	
	/* Generates graph based on file data
	 * 
	 */
	private void generateGraph(String graphData){
		
	}
	
	/* Makes the reverse of the other graph.
	 * Must be called after generateGraph
	 */
	private void generateReverseGraph(){
		
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
