package grafika;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TetrisMenuBar extends JMenuBar {
	JMenuItem file;
	JMenuItem edit;
	JMenuItem help;

	public TetrisMenuBar() {
		file = new JMenuItem("File");
		edit = new JMenuItem("Edit");
		help = new JMenuItem("Help");
		this.add(file);
		this.add(edit);
		this.add(help);
	}
}
