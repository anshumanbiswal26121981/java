package improved;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TemperatureControllerGui implements IStateChangeObserverForGui {

	private JFrame frmTemperaturecontroller;
	private JTextField roomTemperatureTextField;
	private Thermostat thStat; // = new Thermostat();
	private static TemperatureController tc;// = new
											// TemperatureController(thStat);
	JButton offButton,fanButton,acButton,heaterButton;
	private JTextArea systemOutPutText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TemperatureControllerGui window = new TemperatureControllerGui();
					window.frmTemperaturecontroller.setVisible(true);
					setListener(window);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	private static void setListener(TemperatureControllerGui window) {
		tc.setListener(window);
	}

	/**
	 * Create the application.
	 */
	public TemperatureControllerGui() {
		initialize();
		thStat = new Thermostat();
		tc = new TemperatureController(thStat);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTemperaturecontroller = new JFrame();
		frmTemperaturecontroller.setTitle("TemperatureController");
		frmTemperaturecontroller.setBounds(100, 100, 612, 405);
		frmTemperaturecontroller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTemperaturecontroller.getContentPane().setLayout(
				new GridLayout(4, 1, 0, 0));

		JPanel panel = new JPanel();
		frmTemperaturecontroller.getContentPane().add(panel);

		JLabel lblSetTemperature = new JLabel("Set Temperature");
		panel.add(lblSetTemperature);

		roomTemperatureTextField = new JTextField();
		panel.add(roomTemperatureTextField);
		roomTemperatureTextField.setColumns(10);

		JButton setRoomTemperatureButton = new JButton("Set");

		setRoomTemperatureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = roomTemperatureTextField.getText();
				float roomTemp = 0;
				if (!temp.isEmpty()) {
					roomTemp = Float.parseFloat(temp);
				}
				thStat.setRoomTemperature(roomTemp);
				stateProcessedUpdateToGui("Room temperature is set to "+temp);
			}
		});
		panel.add(setRoomTemperatureButton);

		JPanel buttonPanel = new JPanel();
		frmTemperaturecontroller.getContentPane().add(buttonPanel);

		offButton = new JButton("OFF");
		//offButton.setEnabled(false);
		offButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.setOffDoNothingMode();offButton.setEnabled(true);
				offButton.setEnabled(false);
				fanButton.setEnabled(true);
				acButton.setEnabled(true);
				heaterButton.setEnabled(true);
			}
		});
		buttonPanel.add(offButton);

		fanButton = new JButton("FAN");
		fanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.switchFanOn();
				offButton.setEnabled(true);
				fanButton.setEnabled(false);
				acButton.setEnabled(true);
				heaterButton.setEnabled(true);
			}
		});
		buttonPanel.add(fanButton);

		acButton = new JButton("AC");
		acButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					tc.setAcModeEnabled();
					offButton.setEnabled(true);
					fanButton.setEnabled(true);
					acButton.setEnabled(false);
					heaterButton.setEnabled(true);
			}

		});
		buttonPanel.add(acButton);

		heaterButton = new JButton("HEATER");
		heaterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.setHeaterModeOn();
				offButton.setEnabled(true);
				fanButton.setEnabled(true);
				acButton.setEnabled(true);
				heaterButton.setEnabled(false);
			}
		});
		buttonPanel.add(heaterButton);

		JPanel currentTemperature = new JPanel();
		currentTemperature.setPreferredSize(new Dimension(300,100));
		frmTemperaturecontroller.getContentPane().add(currentTemperature);
		currentTemperature.setLayout(new BoxLayout(currentTemperature, BoxLayout.X_AXIS));

		JLabel lblCurrentTemperature = new JLabel("Current Temperature");
		currentTemperature.add(lblCurrentTemperature);
		
		JSpinner temperatureSpinner = new JSpinner();
		temperatureSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		temperatureSpinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		
		temperatureSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String  tempVal =  ((JSpinner)(e.getSource())).getValue().toString();
				float temp = Float.parseFloat(tempVal);
				 thStat.setCurrentTemperature(temp);
			}
		});
		currentTemperature.add(temperatureSpinner);
		
		JPanel panel_2 = new JPanel();
		currentTemperature.add(panel_2);
		

		JPanel panel_1 = new JPanel();
		frmTemperaturecontroller.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		systemOutPutText = new JTextArea();
		systemOutPutText.setRows(4);
		systemOutPutText.setLineWrap(true);
		systemOutPutText.setColumns(50);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(systemOutPutText);
		panel_1.add(scrollPane);
	}


	@Override
	public void stateProcessedUpdateToGui(String text) {
		systemOutPutText.append(text);
		systemOutPutText.append("\n");
	}

}
