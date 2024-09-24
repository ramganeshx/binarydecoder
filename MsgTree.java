package edu.iastate.cs228.hw4;

import java.util.Stack;

/**
 * 
 * @author ramganesh
 *
 */

public class MsgTree {

	public char payloadChar;

	public MsgTree left;

	public MsgTree right;

	/*
	 * Can use a static char idx to the tree string for recursive solution, but it
	 * is not strictly necessary
	 */
	private static int staticCharIdx = 0;

	// Constructor building the tree from a string
	public MsgTree(String encodingString) {

		// checking if tree length atleast 2 or if it's null
		if (encodingString == null || encodingString.length() < 2) {

			return;
		}

		Stack<MsgTree> tree = new Stack<>();

		this.payloadChar = encodingString.charAt(staticCharIdx++);

		tree.push(this);

		MsgTree msg = this;

		boolean check = true;

		while (staticCharIdx < encodingString.length()) {

			MsgTree stored = new MsgTree(encodingString.charAt(staticCharIdx++));

			if (check) {

				msg.left = stored;

				if (stored.payloadChar == '^') {

					msg = tree.push(stored);

					check = true;

				} else {

					if (!tree.empty())

						msg = tree.pop();

					check = false;

				}

			} else {

				msg.right = stored;

				if (stored.payloadChar == '^') {

					msg = tree.push(stored);

					check = true;

				} else {

					if (!tree.empty())

						msg = tree.pop();

					check = false;
				}
			}
		}

	}

	// Constructor for a single node with null children
	public MsgTree(char payloadChar) {

		this.payloadChar = payloadChar;
		this.left = null;
		this.right = null;

	}

	// method to print characters and their binary codes
	public static void printCodes(MsgTree root, String code) {

		if (root.payloadChar == '^') {

			printCodes(root.left, code + '0');

			printCodes(root.right, code + '1');

		} else {

			System.out.println("    " + root.payloadChar + "    " + code);

		}

	}

	public void decode(MsgTree codes, String msg) {

		System.out.println("MESSAGE:");

		MsgTree tree = codes;

		String s = "";

		for (int i = 0; i < msg.length(); i++) {

			char c = msg.charAt(i);

			if (c == '0') {

				codes = codes.left;

			} else {

				codes = codes.right;

			}

			if (codes.payloadChar != '^') {

				s += codes.payloadChar;

				codes = tree;

			}

		}

		System.out.println(s.toString());

		statistc(msg, s.toString());

	}

	/**
	 * extra credit stats
	 * 
	 * @param compress
	 * @param uncompress
	 */
	private void statistc(String compress, String uncompress) {
		// TODO Auto-generated method stub

		// this is printing stats header
		System.out.println("STATISTICS:");

		// this is calculating and displaying average bits per character
		double avgBitsPerChar = compress.length() / uncompress.length();
		System.out.println("Avg bits/char:\t" + String.format("%.1f", avgBitsPerChar));

		// this is displaying the total number of characters in the uncompressed data
		System.out.println("Total Characters:\t" + uncompress.length());

		// this is calculating and displaying space saving percentage
		double spaceSaving = (1d - uncompress.length() / compress.length()) * 100;
		System.out.println("Space Savings:\t" + String.format("%.1f%%", spaceSaving));

	}

}
