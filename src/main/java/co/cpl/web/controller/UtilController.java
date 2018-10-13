/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static co.cpl.utilities.Constants.RAML_URL;

/**
 * this Controller objective is to navigate to the root page of Service
 * documentation implemented with RAML
 * 
 * @author jmunoz
 * @since 28/09/2018
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/")
public class UtilController {

	@GetMapping("/")
	public void getRaml(HttpServletResponse response) throws IOException {
		response.sendRedirect(RAML_URL);
	}
}
