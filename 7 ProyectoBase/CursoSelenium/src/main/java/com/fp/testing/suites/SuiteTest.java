package com.fp.testing.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fp.testing.core.DriverFactory;
import com.fp.testing.test.TestRegistro;
import com.fp.testing.test.TestReglasRegistro;

@RunWith(Suite.class)
@SuiteClasses({
	TestRegistro.class,
	TestReglasRegistro.class
})
public class SuiteTest {
	
	@AfterClass
	public static void finalizaTudo(){
		DriverFactory.killDriver();
	}

}
