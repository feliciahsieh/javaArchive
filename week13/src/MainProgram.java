import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuKeyListener;

public class MainProgram {
	static int scoops;
	static boolean isSugarCone;
	static String[] flavorStrings = { "Almond Swirl $1.50", "Chocolate $2.00", "Fudge Swirl $2.50", "Pecan $3.00", "Vanilla $3.50" };
	static String[] flavorList = { "Almond Swirl", "Chocolate", "Fudge Swirl", "Pecan", "Vanilla" };
	static double[] flavorListPrice = { 1.50, 2.00, 2.50, 3.00, 3.50 };
	static double flavorPrice=0.0;
	static double totalPrice=0.0;
	
	static final int SCOOP_MIN  = 1;//Slider values
	static final int SCOOP_MAX  = 4;//Slider values
	static final int SCOOP_INIT = 1;//Slider, initial frames per second

	static private JLabel passwordLabel;
	static private JPasswordField userPassword;
	static private JRadioButton sugarConeRButton;
	static private JRadioButton waffleConeRButton;
	static private JComboBox flavorComboBox;
	static private JLabel totalPriceLabel;
	static private JTextField totalPriceBox;
	static private JTextArea statusArea;
	static private JButton orderButton;
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		JPanel panel = new JPanel();

		//Initialize Initial variables
		scoops = 1;
		flavorPrice = 3.5;
		isSugarCone = true;
		
		//PASSWORD
		passwordLabel = new JLabel("Password");
		
		userPassword = new JPasswordField(10);
		
		//Create the radio buttons.
		String sugarConeString = "Sugar Cone";
		sugarConeRButton = new JRadioButton(sugarConeString);
		sugarConeRButton.setMnemonic(KeyEvent.VK_S);
		sugarConeRButton.setActionCommand(sugarConeString);
		sugarConeRButton.setSelected(true);

		String waffleConeString = "Waffle Cone";
		waffleConeRButton = new JRadioButton(waffleConeString);
		waffleConeRButton.setMnemonic(KeyEvent.VK_W);
		waffleConeRButton.setActionCommand(waffleConeString);

		//Group the Radio Buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(sugarConeRButton);
		group.add(waffleConeRButton);

		//Add Radio Button Listener
		sugarConeRButton.addActionListener(new ConeButtonListener());
		//sugarConeRButton.addMenuKeyListener();
		waffleConeRButton.addActionListener(new ConeButtonListener());

		//Total Price label
		totalPriceLabel = new JLabel("Total Price");
		//Total Price TextField
		totalPriceBox = new JTextField(6);
		totalPriceBox.setHorizontalAlignment(JTextField.RIGHT);
		totalPriceBox.setEditable(false);

		//Order Button
		orderButton =new JButton("Order");
		orderButton.addActionListener(new OrderButtonListener() );

		//Combo Box of Flavors
		flavorComboBox = new JComboBox(flavorStrings);
		flavorComboBox.setSelectedIndex(4);
		flavorComboBox.addActionListener(new FlavorComboBoxListener());

		//Slider for # of Scoops
		JSlider numScoopSlider = new JSlider(JSlider.HORIZONTAL, SCOOP_MIN, SCOOP_MAX, SCOOP_INIT);
		numScoopSlider.addChangeListener(new SliderListener());

		Font font = new Font("Serif", Font.ITALIC, 15);
		numScoopSlider.setFont(font);
		
		//Turn on labels at major tick marks.
		numScoopSlider.setMajorTickSpacing(1);
		numScoopSlider.setMinorTickSpacing(1);
		numScoopSlider.setPaintTicks(true);
		numScoopSlider.setSnapToTicks(true);
		numScoopSlider.setPaintLabels(true);
		
		statusArea = new JTextArea("Status",4,20); //Placeholder Text, # rows, # cols
		    
		panel.add(totalPriceLabel);
		panel.add(totalPriceBox);
		panel.add(flavorComboBox);
		panel.add(numScoopSlider);
		panel.add(sugarConeRButton);
		panel.add(waffleConeRButton);
		panel.add(passwordLabel);
		panel.add(userPassword);
		panel.add(orderButton);
		panel.add(statusArea);
		
		window.add(panel);
		window.setSize(280,380);
		window.setTitle("Ice Cream Order");
		window.setVisible(true);
		
		CalcTotalListener();
	}
	
	private static boolean checkPassword()
	{
		char[] input = userPassword.getPassword();
		if (isPasswordCorrect(input))
		{
			System.out.println("Password matches");
			return(true);
		} else
		{
			System.out.println("Password DOES NOT match");
			return(false);
		}
	}//checkPassword

	private static class ConeButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			isSugarCone = sugarConeRButton.isSelected()? true : false;
			    
			CalcTotalListener();
		}
	}//ConeButtonListener

	private static class FlavorComboBoxListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Flavor:" + flavorComboBox.getSelectedIndex());
			flavorPrice = flavorListPrice[flavorComboBox.getSelectedIndex()];
			CalcTotalListener();
		}
	}//FlavorComboBoxListener
	
	//Calculate total when any order item changes
	private static void CalcTotalListener()
	{
		if(isSugarCone)
			totalPrice = scoops * flavorPrice;
		else
			totalPrice = 1.1 * scoops * flavorPrice;

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(totalPrice);
		System.out.println("Total:" + moneyString);
		
		totalPriceBox.setText(moneyString);
		
		statusArea.setText("");
	}
	
	private static class SliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				scoops = (int)source.getValue();
				System.out.println("Scoop: " + scoops);
			}
			CalcTotalListener();
		}
	}//ChangeListener (for Slider)
	
	
	private static class OrderButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Order button pressed");
			
			updateStatusArea();
		}
	}//OrderButtonListener
	
	private static void updateStatusArea()
	{
		String result;
		
		if(checkPassword())
		{
			String cone = (isSugarCone)? "Sugar Cone" : "Waffle Cone";
int flavIndex = flavorComboBox.getSelectedIndex();
double simpleTotal = flavorListPrice[flavIndex]*scoops;
double wafflePremium=0.0;

NumberFormat formatter = NumberFormat.getCurrencyInstance();

result = ("--- Your order is complete ---" + "\n" +
cone + "\n" +
flavorList[flavIndex] + "\n" +
scoops + " scoop(s)" + "\n" +
formatter.format(flavorListPrice[flavIndex]) + " per scoop = " + formatter.format(simpleTotal) + "\n");

if(!isSugarCone)
{
wafflePremium=flavorListPrice[flavIndex]*scoops*0.1;
result = result + "Waffle Cone extra fee = " + formatter.format(wafflePremium) + "\n";
}

result = result + "Total price: " + formatter.format(totalPrice);
} else
{
result = "Sorry, password is invalid.";
}

statusArea.setText(result);
}//updateStatusArea()

private static boolean isPasswordCorrect(char[] input)
{
    boolean isCorrect = true;
    char[] correctPassword = { 'f', 'r', 'o', 'z', 'e', 'n' };

    if (input.length != correctPassword.length) {
        isCorrect = false;
    } else {
        isCorrect = Arrays.equals(input, correctPassword);
    }

    //Zero out the password.
    Arrays.fill(correctPassword,'0');

    return isCorrect;
}//isPasswordCorrect()

}
