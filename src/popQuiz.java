import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class popQuiz extends JFrame implements ActionListener{
	
JPanel messagePanel;
JLabel message;
JPanel scorePanel;
JLabel scoreLabel;
int score;
int attempts;
int cardsSeen;
boolean seenCard;
ImageIcon confirmationIcon;
int[] index;
Card card1;
Card card2;
Chart correctChart;
File referenceChart;
BufferedImage cardImage1;
BufferedImage cardImage2;
JButton fold;
JButton call;
JButton raise;
JButton all_in;
JPanel cards;


	popQuiz(File referenceChart) {
		index = new int[2];
		
		
		try {
			confirmationIcon = new ImageIcon(getClass().getResource("correct.png"));
		} catch(Exception e) {
			System.out.println("Confirmation image not found");
		}
		
		cardsSeen = 0;
		score = 0;
		attempts = 0;
		seenCard = false;
		
		fold = new JButton();
		fold.setBounds(70, 280, 100, 50);
		fold.setText("Fold");
		fold.addActionListener(this);
		
		call = new JButton();
		call.setBounds(190, 280, 100, 50);
		call.setText("Call");
		call.addActionListener(this);
		
		raise = new JButton();
		raise.setBounds(310, 280, 100, 50);
		raise.setText("Raise");
		raise.addActionListener(this);
		
		all_in = new JButton();
		all_in.setBounds(430, 280, 100, 50);
		all_in.setText("All In");
		all_in.addActionListener(this);
		
		correctChart = new Chart();
		correctChart.importChart(referenceChart);
		
		cards = cards();
		
		scorePanel = scorePanel();
		
		

		messagePanel = new JPanel();
		message = new JLabel(null, confirmationIcon, JLabel.CENTER); 
		messagePanel.setVisible(false);
		messagePanel.setLayout(new GridLayout(1,1));
		messagePanel.setBounds(20, 75, 130, 150);
		messagePanel.add(message);
		
		
		
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(600, 400);
		this.setResizable(false);
		this.setVisible(true);
		this.setResizable(false);
		this.add(cards);
		this.setTitle(referenceChart.getName());
		
		
		this.add(messagePanel);
		this.add(scorePanel);
		this.add(fold);
		this.add(call);
		this.add(raise);
		this.add(all_in);
		
		
	}

	
	public JPanel scorePanel() {
		scoreLabel = new JLabel("Score: " + score + "/" + cardsSeen);
		scoreLabel.setFont(new Font("Bold", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 20, 100, 100);
		panel.add(scoreLabel);
		
		return panel;
	}
	
	public void updateIndex(Card card1, Card card2) {
		if(card1.getSuit() == card2.getSuit()) { //if the first index is larger, then they're suited--hence this function
			if(card1.valueToIndex() > card2.valueToIndex()) { 
				index[0] = card1.valueToIndex();
				index[1] = card2.valueToIndex();
			} else {
				index[0] = card2.valueToIndex();
				index[1] = card1.valueToIndex();
			} 
		} else if(card1.valueToIndex() != card2.valueToIndex()) { //off-suit, but not pairs
			if(card1.valueToIndex() > card2.valueToIndex()) { 
				index[0] = card2.valueToIndex();
				index[1] = card1.valueToIndex();
			} else {
				index[0] = card1.valueToIndex();
				index[1] = card2.valueToIndex();
			} 
		} else { // pairs
			index[0] = card1.valueToIndex();
			index[1] = card1.valueToIndex();
		}
	} 

	
	
	public JPanel cards() {
		attempts = 0;
		
		card1 = new Card();
		do {
			card2 = new Card();
		} while(card1.getFilePath().equals(card2.getFilePath()));
		
			updateIndex(card1, card2);
		
			try {
			cardImage1 = ImageIO.read(new File(card1.getFilePath()));
	    	JLabel displayField = new JLabel();
	    	Image scaledImage = cardImage1.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
	    	ImageIcon imageIcon = new ImageIcon(scaledImage);
	    	displayField = new JLabel(imageIcon);

	    	
	    	cardImage2 = ImageIO.read(new File(card2.getFilePath()));
	    	JLabel displayField2 = new JLabel();
	    	Image scaledImage2 = cardImage2.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
	    	ImageIcon imageIcon2 = new ImageIcon(scaledImage2);
	    	displayField2 = new JLabel(imageIcon2);
	    	
	    	
	    	JPanel cards = new JPanel();
	    	cards.setBounds(150, 25, 300, 225);
	    	cards.setLayout(new GridLayout(1,2));
	    	cards.add(displayField);
	    	cards.add(displayField2);
	    	return cards;
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Missing image", null, JOptionPane.PLAIN_MESSAGE);
			}
	    	
	   return null;
		
		
	}

	
	public void updateScore() {
		if(attempts == 0) {
			score += 1;
		}
		if(seenCard == false) {
			cardsSeen += 1;
		}
		seenCard = true;
		this.remove(scorePanel);
		scorePanel = scorePanel();
		this.add(scorePanel);
		this.revalidate();
		this.repaint();
	}

	public void panelDisappear() {

	}

	
	public void newCards() {
		updateScore();
		

        this.remove(cards);
		cards = cards();
		this.add(cards);

		this.revalidate();
		this.repaint();
		attempts = 0;
		seenCard = false;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fold) {
			if((correctChart.getActionValue(index[0], index[1]) == 0)) {
				newCards();
					
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect" , null, JOptionPane.PLAIN_MESSAGE);
				attempts += 1;
				updateScore();
			}
		} else if(e.getSource() == call) {
			if((correctChart.getActionValue(index[0], index[1]) == 1)) {
				newCards();
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect" , null, JOptionPane.PLAIN_MESSAGE);
				attempts += 1;
				updateScore();
			}
		} else if(e.getSource() == raise) {
			if((correctChart.getActionValue(index[0], index[1]) == 2)) {
				newCards();
				
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect" , null, JOptionPane.PLAIN_MESSAGE);
				attempts += 1;
				updateScore();
			}
		} else if(e.getSource() == all_in) {
			if((correctChart.getActionValue(index[0], index[1]) == 3)) {
				newCards();
				
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect" , null, JOptionPane.PLAIN_MESSAGE);
				attempts += 1;
				updateScore();
			}
		}
		
	}

}
