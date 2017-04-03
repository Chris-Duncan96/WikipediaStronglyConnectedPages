import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GraphProcessorTest {
	
	GraphProcessor gp;
	
	@Before
	public void init(){
		try {
			gp = new GraphProcessor("TestOutput.txt");
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testOutDegree() throws IOException {
		assertEquals(0, gp.outDegree("/wiki/Main_Page"));
		assertEquals(13, gp.outDegree("/wiki/Complexity_theory"));
		assertEquals(2, gp.outDegree("/wiki/Climate"));
	}

	@Test
	public void testSameComponent() {
		assertFalse(gp.sameComponent("/wiki/Main_Page", "/wiki/Complexity_theory"));
		assertFalse(gp.sameComponent("/wiki/Main_Page", "/wiki/Complexity_theory"));
		assertTrue(gp.sameComponent("/wiki/Systems_theory", "/wiki/Complex_system"));
	}

	@Test
	public void testComponentVertices() {
		ArrayList<String> mainPageList = new ArrayList<String>();
		mainPageList.add("/wiki/Main_Page");
		assertEquals(mainPageList, gp.componentVertices("/wiki/Main_Page"));
		assertEquals(16, gp.componentVertices("/wiki/Systems_theory").size());
	}

	@Test
	public void testLargestComponent() {
		assertEquals(16, gp.largestComponent());
	}

	@Test
	public void testNumComponents() {
		assertEquals(5, gp.numComponents());
	}

	@Test
	public void testBfsPath() {
		ArrayList<String> mainPageList = new ArrayList<String>();
		mainPageList.add("/wiki/Main_Page");
		mainPageList.add("/wiki/Main_Page");
		
		assertEquals(mainPageList, gp.bfsPath("/wiki/Main_Page", "/wiki/Main_Page"));
		
		ArrayList<String> Complexity_theory_To_Cell= new ArrayList<String>();
		Complexity_theory_To_Cell.add("/wiki/Complexity_theory");
		Complexity_theory_To_Cell.add("/wiki/Complex_system");
		Complexity_theory_To_Cell.add("/wiki/Cell_(biology)");
		assertEquals(Complexity_theory_To_Cell, gp.bfsPath("/wiki/Complexity_theory","/wiki/Cell_(biology)"));
		
	}

}
