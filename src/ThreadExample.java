/*
  If this code works fine, it was written by HIRURG
  If no, I don`t know, who wrote it.
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ThreadExample extends JFrame {
	
	private static JProgressBar progressBar;
	private JButton button;
	private JPanel panel;
	private final String START = "Start";
	private final String PAUSE = "Pause";
	protected static boolean isStarted = false;
	
	public ThreadExample() {
		super("Thread test");
	    setBounds(100, 100, 300, 70);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    panel = new JPanel();
	    panel.setLayout(new FlowLayout());
	    
	    button = new JButton();
	    button.setText(START);
	    
	    progressBar = new JProgressBar();
	    progressBar.setStringPainted(true);
	    progressBar.setMinimum(0);
	    progressBar.setMaximum(100);
	    progressBar.setValue(0);
	    
	    panel.add(progressBar);
	    panel.add(button);
	    
	    add(panel);
	    initListeners();
	}

	private void initListeners() {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateBtnLabel();
				isStarted = !isStarted;
			}
		});
	}
	
	private void updateBtnLabel() {
		String newLabel = START.equals(button.getText()) ? PAUSE : START;
		button.setText(newLabel);
	}
	
	public static void main(String[] args) {
		ThreadExample myApp = new ThreadExample();
		myApp.setVisible(true);
		
		Thread countThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int value = 0; value <= 100; ) {
					if (isStarted) {
						progressBar.setValue(value);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {}
						
						if (value == 100) {
							value=0;
						} else {
							value += 1;
						}
						
					} else { //if on pause
						try {
							Thread.sleep(350);
						} catch (InterruptedException e) {}
					}
				}
			}
		});
		
		countThread.start();
	}

}
