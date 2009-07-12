package com.thoughtworks.selenium;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;

/*
 * Tests the JS browser prefix formation
 *
 */
public class TestBrowserPrefix extends TestCase {

	private FlashSelenium flashApp;
	private Selenium selenium;

	private static final String FLASH_OBJ_ID = "FLASH_OBJ_ID";
	private static final String FUNCTION = "FUNCTION";
	private static final String RETURN_VALUE = "RETURN_VALUE";
	
	
	public void setUp() {
		selenium = createMock(Selenium.class);
		flashApp = FlashSelenium.createFlashSelenium(selenium, FLASH_OBJ_ID);
	}

	public void tearDown() {
		selenium = null;
		flashApp = null;
	}

	public void testJSPrefixFormation() {
		String expectedFunctionCall = FlashSelenium.createJSPrefix_browserbot(FLASH_OBJ_ID) + FUNCTION + "();"; 
		expect(selenium.getEval(expectedFunctionCall)).andReturn(RETURN_VALUE);
		replay(selenium);
		flashApp = new FlashSelenium(selenium, FLASH_OBJ_ID);
		assertEquals(RETURN_VALUE, flashApp.call(FUNCTION));
		verify(selenium);
	}	
	

}
