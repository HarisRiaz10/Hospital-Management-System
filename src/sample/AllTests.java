package sample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddAdmitControllerTest.class, AdmissionControllerTest.class, AdmitTest.class,
		ClaimInsuranceControllerTest.class, MedicalRecordControllerTest.class,LoginControllerTest.class })
public class AllTests {

}
