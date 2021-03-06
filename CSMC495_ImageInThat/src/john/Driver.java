/*
 * Driver phase 1
 * Author John Holl
 * Last Edited 22Nov2014
 * 
 * Has the user determine which memory to load
 * 
 * If new memory, generates new hashmap to build memory
 * 
 * If old memory, reads and builds hashmap for logic center
 */
package john;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Driver {
		
	public Driver() {
		//Open memory load 
		Selecter resume = new Selecter();
		resume.pack();
		resume.setTitle("Memory Select");
		resume.setLocationRelativeTo(null);
		resume.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resume.setVisible(true);
		
		
	}
	//new memory or load memory
	static class Selecter extends JFrame{

		private static final long serialVersionUID = 1L;

		Selecter(){
			final JButton jbNew = new JButton("New Memory");
			final JButton jbOpen = new JButton("Open Memory");
			add(jbNew, BorderLayout.EAST);
			add(jbOpen, BorderLayout.WEST);
			
			jbNew.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					dispose();
					HashMap<Integer, String> temp = new HashMap<Integer, String>();
					new AI(temp, null);
				}
			});
			
			jbOpen.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					dispose();
					new GUIUpload();
				}
			});
		}
	}
	
	static class GUIUpload extends JFrame{
		//load memory from .txt file
		private static final long serialVersionUID = 1L;
		private HashMap<Integer, String> temp = new HashMap<Integer, String>();

		public GUIUpload(){
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				uploadFile(selectedFile);
				dispose();
				new AI(temp, selectedFile);
			}
		}
		
		//upload file method
		public  void uploadFile(File file){
			//upload file
			try {
				Scanner scan = new Scanner(new FileInputStream(file));
				scan.nextLine();
				while (scan.hasNext()) {
					String tempString = scan.nextLine();
					String[] tempVal = tempString.split(";");
					temp.put(Integer.parseInt(tempVal[0]), tempVal[1]);
				}
				scan.close();
				//catch exceptions
			} catch (FileNotFoundException fnfe) {
	            fnfe.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("\nProgram terminated Safely...");
	        }  
		}
	}
}

