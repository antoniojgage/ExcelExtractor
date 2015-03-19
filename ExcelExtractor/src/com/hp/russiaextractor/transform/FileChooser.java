package com.hp.russiaextractor.transform;

	import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

	import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
	
	public class FileChooser {
	    
		public File open;
		public File save;
		
		public static void main(String[] args) {
	        Runnable r = new Runnable() {

	        	
	        	
	            @Override
	            public void run() {
	                new FileChooser().createUI();
	            }
	        };

	        EventQueue.invokeLater(r);
	    }

	    private void createUI() {
	        JFrame frame = new JFrame();
	        frame.setLayout(new BorderLayout());

	        JButton openBtn = new JButton("Open");
	        JButton saveBtn = new JButton("Run & Save");
	        

	        saveBtn.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                JFileChooser saveFile = new JFileChooser();
	                saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
	                saveFile.showSaveDialog(null);
	                save = new File( saveFile.getSelectedFile(), open.getName().substring(0,open.getName().indexOf("."))+ "_" + "Transformed.xls" );
	                
	                try {
						SalesLedgerTransformer.run(open,save);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	            }
	        });

	        openBtn.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                JFileChooser openFile = new JFileChooser();
	                openFile.showOpenDialog(null);
	                open = openFile.getSelectedFile();
	            }
	        });

	        frame.add(new JLabel("File Chooser"), BorderLayout.CENTER);
	        frame.add(openBtn, BorderLayout.CENTER);
	        frame.add(saveBtn, BorderLayout.SOUTH);
	        frame.setTitle("Excel Transposer");
	        frame.pack();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        frame.setLocationRelativeTo ( null );
	        frame.setSize ( 300, 300 );
	    }
	}

