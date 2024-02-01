import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuizSelector extends JFrame implements ActionListener{


private JButton popQuiz;
private JButton recreateQuiz;
private Chart chart;


	
	
	QuizSelector(){
		
		

			
			recreateQuiz = new JButton();
			recreateQuiz.setBounds(20, 15, 120, 40);
			recreateQuiz.add(new JLabel("Recreate Chart"));
			recreateQuiz.addActionListener(this);

			popQuiz = new JButton();
			popQuiz.setBounds(160, 15, 120, 40);
			popQuiz.add(new JLabel("Pop Quiz"));
			popQuiz.addActionListener(this);
			
		
			this.setSize(300, 100);
			this.add(popQuiz);
			this.add(recreateQuiz);
			this.setLayout(null);
			this.setVisible(true);
			this.setTitle("CHOOSE QUIZ TYPE");
			this.setResizable(false);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == recreateQuiz) {
			Chart chart = new Chart();
			File referenceChart = chart.getChart();
			this.dispose();
			if(Settings.askConfirmation) {
				new confirmChart(referenceChart, 0); //0 quiz type is for recreate quiz
			} else {
				new recreateQuiz(referenceChart);
			}
			
			
			
			
		} else if(e.getSource() == popQuiz) {
			Chart chart = new Chart();
			File referenceChart = chart.getChart();
			this.dispose();
			if(Settings.askConfirmation) {
				new confirmChart(referenceChart, 1); //1 quiz type is for pop quiz
			} else {
				new popQuiz(referenceChart);
			}
			
		}
	}

}
