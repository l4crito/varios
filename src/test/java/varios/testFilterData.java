package varios;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class testFilterData {

	@Test
	public void testReadFile() {
		FilterData fd = new FilterData();
		int ass=fd.readFile("test.txt", "\"", "test");
		assertEquals(1, ass);
		
	}

	@Test
	public void testVerifyPatron() {
		FilterData fd = new FilterData();
		keyDocument key=new keyDocument();
		key.setLlave("0108201801170033017600110010020000000994157561316");
		key.setRuc("1801795475001");
		key.setSecuencial("0000000");
		key.setCodigo("24235235");
		String actual=fd.verifyPatron(key);
		assertEquals("sin patron", actual);
	}

}
