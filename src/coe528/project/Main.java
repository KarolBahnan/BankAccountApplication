package coe528.project;

import javafx.application.Application;
import coe528.project.DefineException;
import coe528.project.View;

/**
 * The Class Main which is used to run the program.
 * 
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments of command line
	 * @throws DefineException which is related to issues with files.
	 */
	public static void main(String[] args) throws DefineException {
		
		Application.launch(View.class, args);		
	}

}
