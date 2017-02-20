// MonteCarloBinarySearch by Jacob Zimmerman
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MonteCarlo {

	private JFrame frmMontecarlo;
	JTextField txtFile;
	JButton btnFindFile;
	JButton btnReadthefile;
	int numRecs;
	JLabel lblNumLines;
	WordNum[] wn ;
	JTextField txtSearchFor;
	JLabel lblNewLabel;
	JButton btnSearch;
	JTextField txtOut;
	JButton btnMonteCarlo;
	JButton btnNewButton;
	final JTextField txtBinarySearchOut = new JTextField();
	double runs;
	double totalBinaryComps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonteCarlo window = new MonteCarlo();
					window.frmMontecarlo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonteCarlo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		txtBinarySearchOut.setBounds(359, 218, 388, 32);
		txtBinarySearchOut.setColumns(10);
		frmMontecarlo = new JFrame();
		frmMontecarlo.setTitle("MonteCarloSequentialSearch by Jacob Zimmerman");
		frmMontecarlo.setBounds(100, 100, 774, 300);
		frmMontecarlo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMontecarlo.getContentPane().setLayout(null);
		
		txtFile = new JTextField();
		txtFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFile.setBounds(10, 11, 546, 32);
		frmMontecarlo.getContentPane().add(txtFile);
		txtFile.setColumns(10);
		
		btnFindFile = new JButton("...");
		btnFindFile.addActionListener(new BtnFindFileActionListener());
		btnFindFile.setBounds(566, 10, 89, 33);
		frmMontecarlo.getContentPane().add(btnFindFile);
		
		btnReadthefile = new JButton("ReadTheFile");
		btnReadthefile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReadthefile.addActionListener(new BtnReadthefileActionListener());
		btnReadthefile.setBounds(10, 54, 205, 32);
		frmMontecarlo.getContentPane().add(btnReadthefile);
		
		lblNumLines = new JLabel("");
		lblNumLines.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumLines.setBounds(256, 54, 216, 32);
		frmMontecarlo.getContentPane().add(lblNumLines);
		
		txtSearchFor = new JTextField();
		txtSearchFor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearchFor.setBounds(117, 97, 86, 32);
		frmMontecarlo.getContentPane().add(txtSearchFor);
		txtSearchFor.setColumns(10);
		
		lblNewLabel = new JLabel("Search For:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 97, 97, 23);
		frmMontecarlo.getContentPane().add(lblNewLabel);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new BtnSearchActionListener());
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSearch.setBounds(237, 97, 89, 33);
		frmMontecarlo.getContentPane().add(btnSearch);
		
		txtOut = new JTextField();
		txtOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtOut.setBounds(359, 97, 389, 32);
		frmMontecarlo.getContentPane().add(txtOut);
		txtOut.setColumns(10);
		
		btnMonteCarlo = new JButton("Do A bunch of sequential searches");
		btnMonteCarlo.addActionListener(new BtnMonteCarloActionListener());
		btnMonteCarlo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMonteCarlo.setBounds(10, 140, 321, 47);
		frmMontecarlo.getContentPane().add(btnMonteCarlo);
		
		btnNewButton = new JButton("Binary Search");
		btnNewButton.addActionListener(new BtnNewButtonActionListener());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(475, 140, 158, 54);
		frmMontecarlo.getContentPane().add(btnNewButton);
		
		frmMontecarlo.getContentPane().add(txtBinarySearchOut);
		setVis(false);
	}
	private void setVis(boolean v) {
		btnMonteCarlo.setVisible(v);
		lblNewLabel.setVisible(v);
		btnSearch.setVisible(v);
		txtOut.setVisible(v);
		txtSearchFor.setVisible(v);
		btnNewButton.setVisible(v);
		txtBinarySearchOut.setVisible(v);
	}
	private class BtnFindFileActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser();
			int ret = jfc.showOpenDialog(frmMontecarlo);
			if (ret != JFileChooser.APPROVE_OPTION) return;
			File theFile = jfc.getSelectedFile();
			txtFile.setText(theFile.getAbsolutePath());
		}
	}
	private class BtnReadthefileActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(txtFile.getText()));
				numRecs = 0;
				while (br.readLine() != null) numRecs++;
				br.close();
				lblNumLines.setText("n = " + numRecs);
				wn = new WordNum[numRecs];
				String l;
				br = new BufferedReader(new FileReader(txtFile.getText()));
				for  (int i=0; (l=br.readLine()) != null; i++){
					String[] split = l.split(",");
					assert split.length == 2;
					String word = split[1].trim();
					int num = Integer.parseInt(split[0].trim());
					wn[i] = new WordNum(word, num);
				}
				setVis(true);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class BtnSearchActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int numToSearchFor = Integer.parseInt(txtSearchFor.getText());
			boolean foundIt = false;
			int comparisons= 0;
			for (int i=0; i<wn.length  && !foundIt; i++) {
				comparisons ++;
				if (wn[i].num == numToSearchFor) {
					txtOut.setText("Found, #comparisons: " + comparisons + " word is " + wn[i].word);
					foundIt = true;
				}
			}
		}
	}
	private class BtnMonteCarloActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int[] randNumsArray = new int[150];
			for (int i = 0; i < randNumsArray.length; i++) {
				randNumsArray[i] = (int) (Math.random() * numRecs);
			}
			int[] comparisonsArray = new int[150];
			for (int i = 0; i < randNumsArray.length; i++) {
				int comparisons = 0;
				for (int j = 0; j < wn.length; j++) {
					comparisons ++;
					if (wn[j].num == randNumsArray[i]) {
						comparisonsArray[i] = comparisons;
					}
				}
			}
			int totalComparisons = 0;
			for (int i = 0; i < comparisonsArray.length; i++) {
				totalComparisons = comparisonsArray[i] +  totalComparisons;
			}
			int averageComparisons = totalComparisons / comparisonsArray.length;
			txtOut.setText("Avg #seq comparisons for 150 random nums is " + averageComparisons );
		}
	}
	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int numToSearchFor = Integer.parseInt(txtSearchFor.getText());
			boolean foundIt = false;
			int comparisons= 0;
			Arrays.sort(wn);
			int lo = 0;
			int hi =wn.length-1;
			
			while ((hi-lo)>1) {
				int mid = (hi+lo)/2;
				comparisons ++;
				if (wn[mid].num > numToSearchFor) {
					hi = mid;
				} else {
					lo = mid;
				}
			}
			int location;
			if (wn[lo].num == numToSearchFor){ location = lo;
			}else {location = hi;
			comparisons ++;
			} txtBinarySearchOut.setText("word is: "+wn[location].word+" # of comparisons: " + comparisons);
		} 
	}
}
