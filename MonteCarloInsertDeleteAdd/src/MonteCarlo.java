// InsertDeleteAdd by Jacob Zimmerman
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MonteCarlo {

	private JFrame frmInsertDeletAdd;
	JTextField txtFile;
	JButton btnFindFile;
	JButton btnReadthefile;
	int numRecs;
	WordNum[] wn ;
	JButton btnSave;
	JTextField txtNewNum;
	JTextField txtNewWord;
	JTextField txtInsertAt;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JButton btnInsert;
	final JTextField txtDelPoint = new JTextField();
	final JLabel lblDeletionPoint = new JLabel("Deletion Point:");
	final JButton btnDelete = new JButton("Delete");
	final JButton btnAddToEnd = new JButton("Add to End");
	final JLabel lblIsThe = new JLabel("(0 is the start of the list)");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonteCarlo window = new MonteCarlo();
					window.frmInsertDeletAdd.setVisible(true);
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
		frmInsertDeletAdd = new JFrame();
		frmInsertDeletAdd.setTitle("InsertDeleteAdd  by Jacob Zimmerman");
		frmInsertDeletAdd.setBounds(100, 100, 774, 300);
		frmInsertDeletAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInsertDeletAdd.getContentPane().setLayout(null);
		
		txtFile = new JTextField();
		txtFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFile.setBounds(10, 11, 546, 32);
		frmInsertDeletAdd.getContentPane().add(txtFile);
		txtFile.setColumns(10);
		
		btnFindFile = new JButton("...");
		btnFindFile.addActionListener(new BtnFindFileActionListener());
		btnFindFile.setBounds(566, 10, 89, 33);
		frmInsertDeletAdd.getContentPane().add(btnFindFile);
		
		btnReadthefile = new JButton("ReadTheFile");
		btnReadthefile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReadthefile.addActionListener(new BtnReadthefileActionListener());
		btnReadthefile.setBounds(10, 54, 205, 32);
		frmInsertDeletAdd.getContentPane().add(btnReadthefile);
		
		btnSave = new JButton("Save file");
		btnSave.addActionListener(new BtnSaveActionListener());
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(244, 54, 182, 32);
		frmInsertDeletAdd.getContentPane().add(btnSave);
		
		txtNewNum = new JTextField();
		txtNewNum.setBounds(10, 115, 132, 20);
		frmInsertDeletAdd.getContentPane().add(txtNewNum);
		txtNewNum.setColumns(10);
		
		txtNewWord = new JTextField();
		txtNewWord.setBounds(183, 115, 132, 20);
		frmInsertDeletAdd.getContentPane().add(txtNewWord);
		txtNewWord.setColumns(10);
		
		txtInsertAt = new JTextField();
		txtInsertAt.setBounds(354, 115, 115, 20);
		frmInsertDeletAdd.getContentPane().add(txtInsertAt);
		txtInsertAt.setColumns(10);
		
		lblNewLabel = new JLabel("Number:");
		lblNewLabel.setBounds(10, 97, 74, 14);
		frmInsertDeletAdd.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New Word:");
		lblNewLabel_1.setBounds(183, 97, 105, 14);
		frmInsertDeletAdd.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Insertion Point:");
		lblNewLabel_2.setBounds(355, 97, 114, 14);
		frmInsertDeletAdd.getContentPane().add(lblNewLabel_2);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new BtnInsertActionListener());
		btnInsert.setBounds(511, 114, 115, 23);
		frmInsertDeletAdd.getContentPane().add(btnInsert);
		txtDelPoint.setColumns(10);
		txtDelPoint.setBounds(354, 166, 115, 20);
		
		frmInsertDeletAdd.getContentPane().add(txtDelPoint);
		lblDeletionPoint.setBounds(354, 146, 114, 14);
		
		frmInsertDeletAdd.getContentPane().add(lblDeletionPoint);
		btnDelete.addActionListener(new BtnDeleteActionListener());
		btnDelete.setBounds(511, 165, 115, 23);
		
		frmInsertDeletAdd.getContentPane().add(btnDelete);
		btnAddToEnd.addActionListener(new BtnAddToEndActionListener());
		btnAddToEnd.setBounds(633, 114, 115, 23);
		
		frmInsertDeletAdd.getContentPane().add(btnAddToEnd);
		lblIsThe.setBounds(345, 197, 160, 14);
		
		frmInsertDeletAdd.getContentPane().add(lblIsThe);
		setVis(false);
	}
	private void setVis(boolean v) {
		btnSave.setVisible(v);
		btnDelete.setVisible(v);
		btnInsert.setVisible(v);
		btnAddToEnd.setVisible(v);
		lblDeletionPoint.setVisible(v);
		lblIsThe.setVisible(v);
		lblNewLabel.setVisible(v);
		lblNewLabel_1.setVisible(v);
		lblNewLabel_2.setVisible(v);
		txtDelPoint.setVisible(v);
		txtInsertAt.setVisible(v);
		txtNewNum.setVisible(v);
		txtNewWord.setVisible(v);
	}
	private class BtnFindFileActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser();
			int ret = jfc.showOpenDialog(frmInsertDeletAdd);
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
				JOptionPane.showMessageDialog(frmInsertDeletAdd, "Hmm, I'm not sure if that file exists. Please try another location!");
			}
		}
	}
	private class BtnSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String newFileName = txtFile.getText();
			newFileName = newFileName.substring(0, newFileName.length()-3);
			newFileName += "new.csv";
			//System.out.println(newFileName);
			try {
				PrintWriter pr = new PrintWriter(new FileWriter(newFileName));
				for (int i=0; i<wn.length;i++) {
					System.out.println("saving " + i + " " + wn[i]);
					pr.println(wn[i].num +"," + wn[i].word);
				}
				pr.close();
				JOptionPane.showMessageDialog(frmInsertDeletAdd, "Done. Wrote file with " + wn.length + " records.");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class BtnInsertActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
			WordNum[] nwn = new WordNum[wn.length+1];
			int k = Integer.parseInt(txtInsertAt.getText());
			for (int i=0; i<k; i++) nwn[i] = wn[i];
			int theNewNum = Integer.parseInt(txtNewNum.getText());
			WordNum newThing = new WordNum(txtNewWord.getText(),theNewNum);
			nwn[k] = newThing;
			for (int i = k; i<wn.length; i++) nwn[i+1] = wn[i];
			wn = nwn;
			JOptionPane.showMessageDialog(frmInsertDeletAdd, "I inserted it!");
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(frmInsertDeletAdd, "Uh Oh! Please check your input in the required feilds!");
			}
		}
	}
	private class BtnDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
			WordNum[] nwn = new WordNum[wn.length-1];
			int k = Integer.parseInt(txtDelPoint.getText());
			for (int i=0; i<k; i++) nwn[i] = wn[i+1];
			for (int i = k; i<wn.length-1; i++) nwn[i] = wn[i+1];
			wn = nwn;
			JOptionPane.showMessageDialog(frmInsertDeletAdd, "I deleted it!");
			} catch (Exception e4) {
				JOptionPane.showMessageDialog(frmInsertDeletAdd, "Yikes! looks like you entered a value that is not within the range of the file!");
			}
		}
	}
	private class BtnAddToEndActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
			WordNum[] nwn = new WordNum[wn.length+1];
			int k = wn.length;
			for (int i=0; i<wn.length; i++) nwn[i] = wn[i];
			int theNewNum = Integer.parseInt(txtNewNum.getText());
			WordNum newThing = new WordNum(txtNewWord.getText(),theNewNum);
			nwn[k] = newThing;
			wn = nwn;
			JOptionPane.showMessageDialog(frmInsertDeletAdd, "Added it!");
			} catch (Exception e5) {
				JOptionPane.showMessageDialog(frmInsertDeletAdd, "Uh Oh! Please check your input in the required feilds!");
			}
		}
	}
}
