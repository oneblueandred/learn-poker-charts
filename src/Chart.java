import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.BorderFactory;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class Chart extends JPanel implements MouseListener{
private JPanel[][] panelArray;
private int[][] actionArray;
public int currentAction;
private JPanel fold;
private JPanel call;
private JPanel raise;
private JPanel all_in;
private JPanel plusOne;
private boolean editable;

public boolean mouseClicked;

	public Chart() {
		currentAction = -1;
		actionArray = new int[13][13];
	
	}

	
	public Chart(int x, int y, int width, int height, boolean editable)
	{
		currentAction = -1;
		
		this.editable = editable;		
		panelArray = new JPanel[13][13];
		actionArray = new int[13][13];
		this.setBackground(Color.black);
		this.setBounds(x, y, width, height);
		this.setLayout(new GridLayout(13, 13));
		fillPanel();
		
		
		
	}
	
	
	public void fillPanel() { //fills chart with card combinations (i.e A5o)
		for(int i = 12; i >= 0; i--) 
		{
			for(int j = 12; j >= 0; j--)
			{
				
				panelArray[i][j] = createPanel(getHand(i,j));
				this.add(panelArray[i][j]);
				if(editable) {
				panelArray[i][j].addMouseListener(this);
				
				}
				
			} 
		}
	}
	
	
	public File getChart() {
		JFileChooser fileChooser = new JFileChooser();		
		int response = fileChooser.showOpenDialog(null);
		if(response == JFileChooser.APPROVE_OPTION)
		{
			File chartFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
			return chartFile;
		} else {
			return null;
		}
		
	}
	

	public void updateCurrentAction(int action) {
		currentAction = action;
	}
	
	
	public JPanel createBettingOptions(int x, int y, int xSize, int ySize) 
	{
		JPanel bettingOptions = new JPanel(new GridLayout(5, 1));
		bettingOptions.setBounds(7, 100, 100, 200);
		
		all_in = new JPanel();
		all_in.setBackground(Settings.all_in);
		all_in.add(new JLabel("All-in"));
		all_in.addMouseListener(this);
		bettingOptions.add(all_in);
		
		raise = new JPanel();
		raise.setBackground(Settings.raise);
		raise.add(new JLabel("Raise"));
		raise.addMouseListener(this);
		bettingOptions.add(raise);
		
		call = new JPanel();
		call.setBackground(Settings.call);
		call.add(new JLabel("Call"));
		call.addMouseListener(this);
		bettingOptions.add(call);
		
		fold = new JPanel();
		fold.setBackground(Settings.fold);
		fold.add(new JLabel("Fold"));
		fold.addMouseListener(this);
		bettingOptions.add(fold);
		
		if(editable == true) {
			plusOne = new JPanel();
			plusOne.setBackground(Settings.plusOne);
			plusOne.add(new JLabel("+1"));
			plusOne.addMouseListener(this);
			plusOne.setBorder(BorderFactory.createLineBorder(Color.black, 3));
			bettingOptions.add(plusOne);
		}
		return bettingOptions;
	}
	
	
	public String getCard(int i) {
		String card;
		
		switch(i) {
		
		case 12: card = "A";
		break;
		case 11: card = "K";
		break;
		case 10: card = "Q";
		break;
		case 9: card = "J";
		break;
		case 8: card = "T";
		break;
		case 7: card = "9";
		break;
		case 6: card = "8";
		break;
		case 5: card = "7";
		break;
		case 4: card = "6";
		break;
		case 3: card = "5";
		break;
		case 2: card = "4";
		break;
		case 1: card = "3";
		break;
		case 0: card = "2";
		break;
		default:
		card = "-1";
	}
	return card;
	}
	
	public String getHand(int i, int j)
	{
	String hand = null;
	
	
		if(i > j)
		{
			hand = getCard(i);
			hand += getCard(j);
			hand += "s";
		} else if(j > i)
		{
			hand = getCard(j);
			hand += getCard(i);
			hand += "o";
		} else if(j == i) {
			hand = getCard(j);
			hand += getCard(i);
		}
	
	return hand;
	}
	
	

	
	
	
	
	public int getActionValue(int i, int j)
	{
		return actionArray[i][j];
	}
	
	

	public void updateColor(int i, int j) {
		if(actionArray[i][j] == 4)
		{
			actionArray[i][j] = 0;
		}
		
		switch(actionArray[i][j])
		{

			case 0:
			panelArray[i][j].setBackground(Settings.fold);
			break;
			
			case 1:
			
			panelArray[i][j].setBackground(Settings.call);
			break;
			
			case 2:
			
			panelArray[i][j].setBackground(Settings.raise);
			break;
			
			case 3:
			
			panelArray[i][j].setBackground(Settings.all_in);
			break;

			
		}
		
		
	}
	
	public void updateAllColors()
	{
		for(int i = 12; i >= 0; i--)
		{
			for(int j = 12; j >= 0 ; j--)
			{
				if(actionArray[i][j] == 4)
				{
					actionArray[i][j] = 0;
				}
				
				switch(actionArray[i][j])
				{
					case 0: 
						panelArray[i][j].setBackground(Settings.fold);
						break;
					
					case 1:
						panelArray[i][j].setBackground(Settings.call);
						break;
					
					case 2:
						panelArray[i][j].setBackground(Settings.raise);
						break;
					
					case 3:
						panelArray[i][j].setBackground(Settings.all_in);
						break;
						
					case 5:
						panelArray[i][j].setBackground(Color.green);
						break;
					
					case 6:
						panelArray[i][j].setBackground(Color.red);
						break;
				}
			}
		}
			

	}
	
	
	public void updateActionArray(int i, int j, int value)
	{
		actionArray[i][j] = value;
	}
	
	
	public void importChart(File referenceChart) 
	{
		try {
			Scanner s = new Scanner(referenceChart);

				for(int i = 12; i >= 0; i--)
				{
					for(int j = 12; j >= 0 ; j--)
					{
						updateActionArray(i, j, s.nextInt());
					}
				} 
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
	
	public JPanel createPanel(String text) {
		  JPanel panel = new JPanel();
		  JLabel label = new JLabel(text);
		  panel.add(label);
		  panel.setBackground(Color.gray);
		  panel.setOpaque(true);
		  panel.setBorder(BorderFactory.createLineBorder(Color.white));
		  panel.setFont(null);
		  panel.setFont(new Font("Arial", Font.PLAIN, 15));
		  panel.setFocusable(false);
			
		  return panel;
		}

	
	public void clearChart() {

		for(int i = 12; i >= 0; i--){
			for(int j = 12; j >= 0; j--)
			{
				actionArray[i][j] = 0;
			}	
		}
		updateAllColors();
	}
	
	
	public void manualChartUpdate(int[][] updateChart) {
		for(int i = 12; i >= 0; i--) {
			for(int j = 12; j >= 0; j--) {
				actionArray[i][j] = updateChart[i][j];
			}
		}
		
		updateAllColors();
	}
	
	
	public void saveFile(File fileToSave) throws IOException
	{
		FileWriter fw = new FileWriter(fileToSave);
		
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i = 12; i >= 0; i--)
		{
			for(int j = 12; j >= 0; j--)
			{
				pw.write(actionArray[i][j] + " ");
			}
		}
		
		pw.close();
	}

	public void updateBorder(JPanel clickedPanel) {
		fold.setBorder(null);
		call.setBorder(null);
		raise.setBorder(null);
		all_in.setBorder(null);
		plusOne.setBorder(null);
		
		clickedPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseClicked = true;
		if(e.getSource() == fold) {
			currentAction = 0;
			updateBorder(fold);
		} else if(e.getSource() == call) {
			currentAction = 1;
			updateBorder(call);
		} else if(e.getSource() == raise) {
			currentAction = 2;
			updateBorder(raise);
		} else if(e.getSource() == all_in) {
			currentAction = 3;
			updateBorder(all_in);
		} else if(e.getSource() == plusOne) {
			currentAction = -1;
			updateBorder(plusOne);
		}
		else {
			for(int i = 12; i >= 0; i--)
			{
				for(int j = 12; j >= 0; j--)
				{
					if(e.getSource() == panelArray[i][j]) 
					{
						if(currentAction != -1) {
							actionArray[i][j] = currentAction;
						} else {
							actionArray[i][j] += 1;
						}
						updateColor(i, j);
						
					}
				}
			}
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseClicked = false;
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(mouseClicked)
		{
			for(int i = 12; i >= 0; i--)
			{
				for(int j = 12; j >= 0; j--)
				{
					if(e.getSource() == panelArray[i][j]) 
					{
						if(currentAction != -1) {
							actionArray[i][j] = currentAction;
						} else {
							actionArray[i][j] += 1;
						}
						updateColor(i, j);
					}
				}
			}
		}
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}








}
