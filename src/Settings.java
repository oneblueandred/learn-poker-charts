import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.lang.ref.Reference;


public class Settings extends JFrame implements ActionListener
{
public static Color all_in = Color.magenta;
public static Color raise = Color.red;
public static Color call = Color.green;
public static Color fold = Color.gray;
public static Color plusOne = Color.yellow;
public static boolean askConfirmation = true;



JCheckBox confirmChartButton;
JButton save;

final JComboBox<String> all_inColor;
final JComboBox<String> raiseColor;
final JComboBox<String> callColor;    
final JComboBox<String> foldColor;    	
			
Settings(){
		
		String[] choices = {"Default", "Green", "Blue", "Magenta", "Cyan", "Yellow", "White", "Gray", "Dark Gray", "Orange", "Pink"};
		
		JLabel all_inText = new JLabel("All-in Color:");
		all_inText.setBounds(70, 30, 250, 15);
		
		JLabel raiseText = new JLabel("Raise Color:");
		raiseText.setBounds(70, 100, 250, 15);
		
		JLabel callText = new JLabel("Call Color:");
		callText.setBounds(70, 170, 250, 15);
		
		JLabel foldText = new JLabel("Fold Color:");
		foldText.setBounds(70, 240, 250, 15);
		
		confirmChartButton = new JCheckBox();
		confirmChartButton.setText("Show chart to confirm?");
		confirmChartButton.setFocusable(false);
		confirmChartButton.setFont(new Font("Bold", Font.PLAIN, 14));
		confirmChartButton.setBounds(10, 310, 200, 50);
		confirmChartButton.setSelected(true);
		

		all_inColor = new JComboBox<String>(choices);
		all_inColor.setBounds(75, 30, 250, 65);
		
		
		
		raiseColor = new JComboBox<String>(choices);
		raiseColor.setBounds(75, 100, 250, 65);
		
		
		
		callColor = new JComboBox<String>(choices);
		callColor.setBounds(75, 170, 250, 65);
		
		
		foldColor = new JComboBox<String>(choices);
		foldColor.setBounds(75, 240, 250, 65);
		
		
		
		
		save = new JButton();
		save.setText("Save");
		save.addActionListener(this);
		save.setBounds(220, 320, 160, 40);
		
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(400, 400);
		this.setBackground(Color.gray);
		this.setVisible(true);
		this.setResizable(false);
		this.add(all_inColor);
		this.add(raiseColor);
		this.add(callColor);
		this.add(foldColor);
		this.add(save);
		this.add(confirmChartButton);
		this.add(all_inText);	
		this.add(raiseText);
		this.add(callText);
		this.add(foldText);

		
	}
	
	public Color parseColor(String newColor) 
	{
		switch(newColor) {
			case "Green":
				return Color.green;
			case "Blue":
				return Color.blue;
			case "Magenta":
				return Color.magenta;
			case "Cyan":
				return Color.cyan;
			case "Yellow":
				return Color.yellow;
			case "White":
				return Color.white;
			case "Gray":
				return Color.gray;
			case "Dark Gray":
				return Color.DARK_GRAY;
			case "Orange":
				return Color.orange;
			case "Pink":
				return Color.pink;
			default:
				return Color.black;
		}
	}
	
	public void updateColor(String newColor, String selection) {
		if(selection.equals("all in")) {
			if(newColor.equals("Default")) {
				all_in = Color.magenta;
				return;
			}
			all_in = parseColor(newColor);
		} else if(selection.equals("raise")) {
			if(newColor.equals("Default")) {
				raise = Color.red;
				return;
			}
			raise = parseColor(newColor);
		} else if(selection.equals("call")) {
			if(newColor.equals("Default")) {
				call = Color.green;
				return;
			}
			call = parseColor(newColor);
		} else {
			if(newColor.equals("Default")) {
				fold = Color.gray;
				return;
			}
			fold = parseColor(newColor);
		}
		

		
	}
 

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save) {
			updateColor(all_inColor.getSelectedItem().toString(), "all in");
			updateColor(raiseColor.getSelectedItem().toString(), "raise");
			updateColor(callColor.getSelectedItem().toString(), "call");
			updateColor(foldColor.getSelectedItem().toString(), "fold"); 
			
			askConfirmation = confirmChartButton.isSelected();
			
			JOptionPane.showMessageDialog(null, "Success!", "SAVED", JOptionPane.PLAIN_MESSAGE);
			
			
			
		}
		// TODO Auto-generated method stub
		
	}

}
