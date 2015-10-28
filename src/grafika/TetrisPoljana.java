/*package grafika;


import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.*;

import logika.PoljanaZaIgru;

public class TetrisPoljana extends JPanel {
	PoljanaZaIgru pzi;
	JTextField[][] jtfMatrix;
	FileReader fr;
	ImageIcon image;
	JLabel [][] labela; 
	String [] nazivi = {"blue.png", "bred.png", "dblue.png", "dgreen.png", "red.png", "gold.png", "purple.png", "pink.png"};
	
	public TetrisPoljana(PoljanaZaIgru pzi) {
		this.pzi = pzi;
		MojListener ml = new MojListener();
		int[][] poljana = this.pzi.getPoljana();
		
		jtfMatrix = new JTextField[poljana.length][poljana[0].length];
		this.setLayout(new GridLayout(poljana.length, poljana[0].length));
		labela =new JLabel[poljana.length][poljana[0].length];
		for (int i = poljana.length - 1; i >= 0 ; i--) {
			for (int j = 0; j < poljana[i].length; j++) {
			/*	
               // image = new ImageIcon (getClass().getResource(nazivi[poljana[i][j]]));
				image = new ImageIcon (getClass().getResource("bred.png"));
				labela[i][j]= new JLabel(image);
				labela[i][j].addKeyListener(ml);
				this.add(labela[i][j]); 
				
				if (poljana[i][j]==0){
					image = new ImageIcon(getClass().getResource(nazivi[0]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				if (poljana[i][j]==1){
					image = new ImageIcon (getClass().getResource(nazivi[1]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				if (poljana[i][j]==2){
					image = new ImageIcon (getClass().getResource(nazivi[2]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				if (poljana[i][j]==3){
					image = new ImageIcon (getClass().getResource(nazivi[3]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
					
				if (poljana[i][j]==4){
					image = new ImageIcon (getClass().getResource(nazivi[4]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				if (poljana[i][j]==5){
					image = new ImageIcon (getClass().getResource(nazivi[5]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				if (poljana[i][j]==6){
					image = new ImageIcon (getClass().getResource(nazivi[6]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				if (poljana[i][j]==7){
					image = new ImageIcon (getClass().getResource(nazivi[7]));
					labela[i][j]= new JLabel(image);
					
					labela[i][j].addKeyListener(ml);
					this.add(labela[i][j], i, j);	
				}
				
				jtfMatrix[i][j] = new JTextField(2);
				if (poljana[i][j] == 0) {
					jtfMatrix[i][j].setText("");;
				} else {
					jtfMatrix[i][j].setText("" + poljana[i][j]);
				}
				jtfMatrix[i][j].setBackground(getBoja(poljana[i][j]));
				jtfMatrix[i][j].setEditable(false);
				jtfMatrix[i][j].addKeyListener(ml);
				this.add(jtfMatrix[i][j], i, j); 
			}
		}
	}

	private void updateTetrisPoljana() {
		int[][] poljana = pzi.getPoljana();
		for (int i = poljana.length - 1; i >= 0 ; i--) {
			for (int j = 0; j < poljana[i].length; j++) {
//				System.out.println("postavljamo za (i, j) = (" + i + ", " + j + ") vrijednost " + poljana[i][j] +
//						"\t stara vrijednost je " + ((JTextField)this.getComponentAt(i, j)).getText());
				if (poljana[i][j] == 0) {
					image = new ImageIcon (getClass().getResource(nazivi[0]));
					labela[i][j]= new JLabel(image);
				} else {
					image = new ImageIcon (getClass().getResource(nazivi[poljana[i][j]]));
					labela[i][j]= new JLabel(image);
				}
				//jtfMatrix[i][j].setBackground(getBoja(poljana[i][j]));
			}
		}
		System.out.println(this);
	}

	private Color getBoja(int b) {
		Color boja = Color.YELLOW;
		switch (b) {
		case 1:
			boja = Color.BLACK;
			break;
		case 2:
			boja = Color.BLUE;
			break;
		case 3:
			boja = Color.CYAN;
			break;
		case 4:
			boja = Color.DARK_GRAY;
			break;
		case 5:
			boja = Color.GRAY;
			break;
		case 6:
			boja = Color.GREEN;
			break;
		case 7:
			boja = Color.PINK;
			break;

		default:
			break;
		}
		return boja;
	}   

class MojListener implements KeyListener {
	int counter = 0;

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			if (e.getKeyChar() == '7') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_LIJEVO);
			}
			if (e.getKeyChar() == '8') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_ROTIRAJ);
			}
			if (e.getKeyChar() == '9') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DESNO);
			}
			if (e.getKeyChar() == '4') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
			}
			if (e.getKeyChar() == ' ') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_NA_DNO);
			}
			counter++;
			if (counter == 10) {
				counter = 0;
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
			}
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		updateTetrisPoljana();
		System.out.println(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
} */



package grafika;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import logika.PoljanaZaIgru;

public class TetrisPoljana extends JPanel {
	PoljanaZaIgru pzi;
	JTextField[][] jtfMatrix;
	FileReader fr;
	
	public TetrisPoljana(PoljanaZaIgru pzi) {
		this.pzi = pzi;
		MojListener ml = new MojListener();
		int[][] poljana = this.pzi.getPoljana();
		jtfMatrix = new JTextField[poljana.length][poljana[0].length];
		this.setLayout(new GridLayout(poljana.length, poljana[0].length));
		for (int i = poljana.length - 1; i >= 0 ; i--) {
			for (int j = 0; j < poljana[i].length; j++) {
				jtfMatrix[i][j] = new JTextField(2);
				if (poljana[i][j] == 0) {
					jtfMatrix[i][j].setText("");;
				} else {
					jtfMatrix[i][j].setText("" + poljana[i][j]);
				}
				jtfMatrix[i][j].setBackground(getBoja(poljana[i][j]));
				jtfMatrix[i][j].setEditable(false);
				jtfMatrix[i][j].addKeyListener(ml);
				this.add(jtfMatrix[i][j], i, j);
			}
		}
	}

	private void updateTetrisPoljana() {
		int[][] poljana = pzi.getPoljana();
		for (int i = poljana.length - 1; i >= 0 ; i--) {
			for (int j = 0; j < poljana[i].length; j++) {
//				System.out.println("postavljamo za (i, j) = (" + i + ", " + j + ") vrijednost " + poljana[i][j] +
//						"\t stara vrijednost je " + ((JTextField)this.getComponentAt(i, j)).getText());
				if (poljana[i][j] == 0) {
					jtfMatrix[i][j].setText("");
				} else {
					jtfMatrix[i][j].setText("" + poljana[i][j]);
				}
				jtfMatrix[i][j].setBackground(getBoja(poljana[i][j]));
			}
		}
		System.out.println(this);
	}

	private Color getBoja(int b) {
		Color boja = Color.YELLOW;
		switch (b) {
		case 1:
			boja = Color.BLACK;
			break;
		case 2:
			boja = Color.BLUE;
			break;
		case 3:
			boja = Color.CYAN;
			break;
		case 4:
			boja = Color.DARK_GRAY;
			break;
		case 5:
			boja = Color.GRAY;
			break;
		case 6:
			boja = Color.GREEN;
			break;
		case 7:
			boja = Color.PINK;
			break;

		default:
			break;
		}
		return boja;
	}

class MojListener implements KeyListener {
	int counter = 0;

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			if (e.getKeyChar() == '7') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_LIJEVO);
			}
			if (e.getKeyChar() == '8') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_ROTIRAJ);
			}
			if (e.getKeyChar() == '9') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DESNO);
			}
			if (e.getKeyChar() == '4') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
			}
			if (e.getKeyChar() == ' ') {
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_NA_DNO);
			}
			counter++;
			if (counter == 10) {
				counter = 0;
				pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
			}
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		updateTetrisPoljana();
		System.out.println(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
} 

