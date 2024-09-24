package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author ramganesh
 *
 */

public class Main {

	public static void main(String[] args) {

		String c = "";

		String b = "";

		System.out.println("Please enter filename to decode:");

		Scanner sc = new Scanner(System.in);

		String fileName = sc.nextLine();

		File file = new File(fileName);

		Scanner scanlength = null;

		Scanner scanCount = null;
		
		int index = 0;

		int counter = 0;
		
		try {

			scanlength = new Scanner(file);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {
			
			scanCount = new Scanner(file);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanCount.hasNext()) {
			
			scanCount.nextLine();
			
			counter++;
		
		}
		
		while (scanlength.hasNext()) {

			String line = scanlength.nextLine();

			boolean search = true;

			for (int i = 0; i < line.length(); i++) {

				if (line.charAt(i) == '^') {

					search = false;

				}
			}

			if (!search) {

				c += line;

				if (index == 0 && counter == 3) {

					c += "\n";

					index++;
				}

			} else {

				b += line;
			
			}

		}

		MsgTree a = new MsgTree(c);

		MsgTree.printCodes(a, "");

		a.decode(a, b);

	}

}
