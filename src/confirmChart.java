import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class confirmChart extends chartViewer implements ActionListener {
	
int quizType; //0 for recreate quiz and 1 for pop quiz
File referenceChart;
JButton confirmChart;
JButton differentChart;


	


	confirmChart(File referenceChart, int quizType) {
	
		super(referenceChart, false);
		this.quizType = quizType;
	
		
		this.referenceChart = referenceChart;
		
		differentChart = new JButton();
		differentChart.setBounds(650, 845, 200, 50);
		differentChart.setText("Select different chart");
		differentChart.addActionListener(this);
	
		
		confirmChart = new JButton();
		confirmChart.setBounds(355, 820, 250, 100);
		confirmChart.setText("CONFIRM");
		confirmChart.addActionListener(this);
		
		
		JLabel confirmationLabel = new JLabel("CONFIRM CHART");
		confirmationLabel.setFont(new Font("Bold", Font.PLAIN, 25));
		
		JPanel confirmationPanel = new JPanel();
		confirmationPanel.setBounds(155, 30, 650, 200);
		confirmationPanel.add(confirmationLabel);
		
		this.add(confirmationPanel);
		this.add(confirmChart);
		this.add(differentChart);
		
			
	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getSource() == confirmChart) && quizType == 0) {
			new recreateQuiz(referenceChart);
			this.dispose();
			
		} else if((e.getSource() == confirmChart) && quizType == 1) {
			new popQuiz(referenceChart);
			this.dispose();
		} else if(e.getSource() == differentChart) {
			new confirmChart(viewingChart.getChart(), quizType);
			this.dispose();
			
		}
		
	}
		
}
	
