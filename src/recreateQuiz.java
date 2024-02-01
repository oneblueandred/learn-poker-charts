import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class recreateQuiz extends chartViewer implements ActionListener{

Chart visibleChart;
Chart correctChart;
JButton checkCorrect;
JButton retry;
File referenceChart;
	
	
	recreateQuiz(File referenceChart) {
		super(referenceChart);
		this.referenceChart = referenceChart;
		
		

		
		checkCorrect = new JButton();
		checkCorrect.setBounds(700, 840, 150, 50);
		checkCorrect.setText("VIEW SCORE");
		checkCorrect.addActionListener(this);
		
		retry = new JButton();
		retry.setBounds(700, 840, 150, 50);
		retry.setText("RETRY");
		retry.addActionListener(this);
		
		this.addName();
		this.add(checkCorrect);
		this.setResizable(false);
		this.setVisible(true);
		
		correctChart = new Chart();
		correctChart.importChart(referenceChart);
		
	}

	public int tallyScore(Chart correctChart, Chart inputChart) {
		int score = 0;
		
		for(int i = 12; i >= 0; i--) {
			for(int j = 12; j >= 0; j--) {
				if(correctChart.getActionValue(i, j) == inputChart.getActionValue(i, j)) {
					score += 1;
				}
			}
		}		
		
		return score;
	}
	
	public int[][] answerArray(Chart correctChart, Chart inputChart) {
		
		int[][] answerArray = new int[13][13];
		
		for(int i = 12; i >= 0; i--) {
			for(int j = 12; j >= 0; j--) {
				if(correctChart.getActionValue(i, j) == inputChart.getActionValue(i, j)) {
					answerArray[i][j] = 5;
				} else {
					answerArray[i][j] = 6;
				}
			}
		}	

		return answerArray;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retry) {
			this.dispose();
			new recreateQuiz(referenceChart);
			
		} else if(e.getSource() == checkCorrect) {
			if(JOptionPane.showConfirmDialog(null, "SCORE: " + tallyScore(correctChart, viewingChart) + "/169 \n Would you like to see what you got right?", "SCORE", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
				viewingChart.manualChartUpdate(answerArray(correctChart, viewingChart));
				this.remove(checkCorrect);
				this.repaint();
				this.add(retry);
				
		
			} 
		}
		
	}

}
