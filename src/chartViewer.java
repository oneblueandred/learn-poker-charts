import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class chartViewer extends JFrame {
Chart viewingChart;
File referenceChart;

	
	public chartViewer(File referenceChart) {
		viewingChart = new Chart(120, 90, 720, 720, true);
		this.referenceChart = referenceChart;
		setup(referenceChart);
	}
	

	public chartViewer(boolean editable) 
	{
		viewingChart = new Chart(120, 90, 720, 720, editable);
		if(editable == false) {
			referenceChart = viewingChart.getChart();
			if(referenceChart != null) {
				setup(referenceChart);
				
				viewingChart.importChart(referenceChart);
				viewingChart.updateAllColors();
				
				this.setVisible(true); 
			} else { this.dispose(); }
		} else {
			referenceChart = null;
			setup(null);
			this.setVisible(true);
		}
		
		
	}
	
	public chartViewer(File referenceChart, boolean editable) 
	{
		this.referenceChart = referenceChart;
		viewingChart = new Chart(120, 90, 720, 720, editable);
		if(referenceChart != null) {
			setup(referenceChart);
			viewingChart.importChart(referenceChart);
			viewingChart.updateAllColors();
			
			this.setVisible(true); } 
		else {
			this.dispose();
		} 
	}
	
	public void setup(File referenceChart) {
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(960, 960);
		this.add(viewingChart.createBettingOptions(7, 100, 100, 200));
		this.add(viewingChart);
		this.setResizable(false);
		System.out.println("a");
		
	}
	
	public void addName() {
		System.out.println("tried");
		if(referenceChart != null) {
			JLabel chartNameText = new JLabel(referenceChart.getName());
			chartNameText.setFont(new Font("Bold", Font.PLAIN, 20));
			JPanel chartName = new JPanel();
			chartName.setBounds(155, 30, 650, 200);
			chartName.add(chartNameText);
			this.add(chartName);
		}
	}
	
}

