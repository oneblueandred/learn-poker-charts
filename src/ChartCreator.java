
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ChartCreator extends chartViewer implements ActionListener{
static ActionListener e;
private JTextField chartNameBox;
private JPanel textFieldPanel;
private JButton save;
private JButton clear;
private JButton load;
private boolean quickSave;
File importedChart;
String chartName;
JPanel fold;
JPanel call;
JPanel raise;
JPanel all_in;




	ChartCreator() 
	{
		super(true);
		
		quickSave = false;
		importedChart = null;
		chartName = null;
		save = new JButton();
		save.setText("Save as");
		save.addActionListener(this);
		
		clear = new JButton();
		clear.setText("Clear");
		clear.setBounds(800, 840, 50, 50);
		clear.addActionListener(this);
		
		load = new JButton();
		load.setText("Load");
		load.addActionListener(this);
		
		KeyListener checkTyped = new KeyListener() { public void keyPressed(KeyEvent e) {} public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(importedChart != null && !(importedChart.getName().equals(chartNameBox.getText()))) {
					save.setText("Save as");
					quickSave = false;
				}
				// TODO Auto-generated method stub	
			}
		};
		
		
		chartNameBox = new JTextField();
		chartNameBox.setPreferredSize(new Dimension(400, 50));
		chartNameBox.setText("Chart name");
		chartNameBox.addKeyListener(checkTyped);

	
		textFieldPanel = new JPanel();
		textFieldPanel.setBounds(190, 20, 650, 50);
		textFieldPanel.add(chartNameBox);
		textFieldPanel.add(save);
		textFieldPanel.add(load);
		
		this.setVisible(true);
		this.add(textFieldPanel);
		this.add(clear);
		
		
	
		}






	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == save)
		{
			if(importedChart != null && chartNameBox.getText().equals(importedChart.getName()) && quickSave) {
				try {
					viewingChart.saveFile(importedChart);
					if(JOptionPane.showConfirmDialog(null, "Saved! \nWould you like to clear the chart?", "SUCCESS", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION)
					{
						viewingChart.clearChart();
						chartNameBox.setText("Chart name");
						save.setText("Save as");
						return;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JFileChooser saveLocation = new JFileChooser();
				saveLocation.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(saveLocation.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File dir = saveLocation.getSelectedFile();
					File chartToSave = new File(dir, chartNameBox.getText());
					
					try {
						viewingChart.saveFile(chartToSave);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				
					if(JOptionPane.showConfirmDialog(null, "Saved! \nWould you like to clear the chart?", "SUCCESS", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION)
					{
						viewingChart.clearChart();
						chartNameBox.setText("Chart name");
						return;
					}
				}
			}
		} else if(e.getSource() == clear){
				System.out.println("clear");
				if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to clear the chart without saving?", "Clear", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION)	{
					viewingChart.clearChart();
					chartNameBox.setText("Chart name");
					quickSave = false;
					save.setText("Save as");
				}
		}	else if(e.getSource() == load)
			{
				importedChart = viewingChart.getChart();
				viewingChart.importChart(importedChart);
				viewingChart.updateAllColors();	
				chartNameBox.setText(importedChart.getName());
				System.out.println(importedChart.getAbsoluteFile());
				quickSave = true;
				save.setText("Save");
					
		} 
		
	}


}
