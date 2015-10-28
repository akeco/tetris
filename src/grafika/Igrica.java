package grafika;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import logika.PoljanaZaIgru;

public class Igrica {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout(5, 5));

		PoljanaZaIgru pzi = new PoljanaZaIgru(20, 10, "Classic", 1);

		TetrisPoljana poljana = new TetrisPoljana(pzi);
		TetrisMenuBar tmb = new TetrisMenuBar();
		TetrisNextFigure tnf = new TetrisNextFigure();
		TetrisStatistike ts = new TetrisStatistike();
		jf.getContentPane().add(tmb, BorderLayout.NORTH);
		jf.getContentPane().add(poljana, BorderLayout.CENTER);
		jf.add(tnf, BorderLayout.WEST);
		jf.add(ts, BorderLayout.EAST);
		jf.pack();
		jf.setVisible(true);
	}

}
