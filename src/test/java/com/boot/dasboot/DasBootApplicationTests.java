package com.boot.dasboot;

import com.boot.dasboot.controller.HomeController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DasBootApplicationTests {

	@Test
	public void testHomeController() {
		HomeController controller = new HomeController();
		String result = controller.home();

		assertEquals(result, "Das Boot, at your service. I mean, controller.");
	}

}
