import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.Timer;



public class MyFrame extends JFrame implements ActionListener
{
	

	
	private JButton quizButton;
	private JButton chartCreatorButton;
	private JButton viewChart;
	private JButton settingsButton;
	String[] input;
	
	MyFrame()
	{
	
		viewChart = new JButton();
		viewChart.setBounds(75, 105, 250, 65);
		viewChart.addActionListener(this);
		viewChart.setText("VIEW CHART");
		
		quizButton = new JButton();
		quizButton.setBounds(75, 25, 250, 65);
		quizButton.addActionListener(this);
		quizButton.setText("QUIZ YOURSELF");
		
		chartCreatorButton = new JButton();
		chartCreatorButton.setBounds(75, 185, 250, 65);
		chartCreatorButton.addActionListener(this);
		chartCreatorButton.setText("CREATE CHART");
		
		
		settingsButton = new JButton();
		settingsButton.setBounds(75, 265, 250, 65);
		settingsButton.addActionListener(this);
		settingsButton.setText("SETTINGS");
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(400, 400);
		this.setVisible(true);
		this.add(quizButton);
		this.add(chartCreatorButton);
		this.add(viewChart);
		this.add(settingsButton);
		
		this.getContentPane().setBackground(new Color(100,100,100));
		this.setTitle("MEMORIZE POKER CHARTS");
		this.setResizable(false);
		
		
		
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == quizButton)
		{
			
		
			try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new QuizSelector();
			
		} else if(e.getSource() == chartCreatorButton)
		{
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			new ChartCreator();
		} else if(e.getSource() == viewChart) {
			chartViewer newWindow = new chartViewer(false);	
			newWindow.addName();
		} else if(e.getSource() == settingsButton) {
			new Settings();
			
		}
	}

}
