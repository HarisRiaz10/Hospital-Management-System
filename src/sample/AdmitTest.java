package sample;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AdmitTest {
Admit obj =new Admit(6,7, "08/5", "04/6", "aaema", 32, 21, 34,"Medical");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(obj.getId(),6);
		assertEquals(obj.getRoomNo(),7);
		assertEquals(obj.getRegDate(),"08/5");
		assertEquals(obj.getEndDate(),"04/6");
		assertEquals(obj.getPatientName(),"aaema");
		assertEquals(obj.getPatientAge(),21);
		assertEquals(obj.getPatientId(),32);
		assertEquals(obj.getWardId(),34);
		assertEquals(obj.getWardName(), "Medical");
	}

}
