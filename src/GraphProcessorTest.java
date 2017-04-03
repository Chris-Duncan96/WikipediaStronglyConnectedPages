import static org.junit.Assert.*;

import java.io.IOException;

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
		//assertEquals(0, gp.outDegree("/wiki/Main_Page"));
		assertEquals(13, gp.outDegree("/wiki/Complexity_theory"));
	}

	@Test
	public void testSameComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testComponentVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testLargestComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testNumComponents() {
		fail("Not yet implemented");
	}

	@Test
	public void testBfsPath() {
		fail("Not yet implemented");
	}

}
