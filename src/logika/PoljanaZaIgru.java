package logika;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class PoljanaZaIgru {

	

	public static final char POTEZ_LIJEVO = 'a';
	public static final char POTEZ_DESNO = 'b';
	public static final char POTEZ_DOLE = 'c';
	public static final char POTEZ_ROTIRAJ = 'd';
	public static final char POTEZ_NA_DNO = 'e';
	private int dimX, dimY;
	private int[][] poljana;
	private boolean krajIgre;
	String tip;
	String[] nazivi;
	Figura[] figure;
	Figura figura;
	int tekuciIndexX;
	int tekuciIndexY;
	int smjerRotacije;
	Random rnd;

	public PoljanaZaIgru(int x, int y, String tip, int smjerRotacije) {
		krajIgre = false;
		rnd = new Random();
		dimX = x;
		dimY = y;
		this.tip = tip;
		this.smjerRotacije = smjerRotacije;
		poljana = new int[dimX][dimY];
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				poljana[i][j] = 0;
			}
		}
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(tip + "/setup.set");
			br = new BufferedReader(fr);
			int brojFigura = Integer.parseInt(br.readLine());
			nazivi = new String[brojFigura];
			figure = new Figura[brojFigura];
			for (int i = 0; i < brojFigura; i++) {
				nazivi[i] = br.readLine();
				figure[i] = new Figura(tip, nazivi[i]);
			}
		} catch (Exception e) {
			System.out.println("Nabasali smo na neku gresku");
			e.printStackTrace();
		}
		izaberiFiguru();
	}
	
	public PoljanaZaIgru() {
		this(20, 10, "Classic", 1);
	}
	
	public void izaberiFiguru() {
		figura = figure[rnd.nextInt(figure.length)];
		tekuciIndexX = -1;
		tekuciIndexY = (dimY - figura.getDimenzija()) / 2;
	}

	public void pomjeriFiguru(char potez) throws CloneNotSupportedException {
		if (potez == POTEZ_LIJEVO) {
			if (dozvoljeno(POTEZ_LIJEVO)) {
				tekuciIndexY--;
			}
		}
		if (potez == POTEZ_DESNO) {
			if (dozvoljeno(POTEZ_DESNO)) {
				tekuciIndexY++;
			}
		}
		if (potez == POTEZ_DOLE) {
			if (dozvoljeno(POTEZ_DOLE)) {
				tekuciIndexX++;
			} else {
				snimiFiguru();
			}
		}
		if (potez == POTEZ_ROTIRAJ) {
			if (dozvoljeno(POTEZ_ROTIRAJ)) {
				figura.rotate(smjerRotacije);
			}
		}
		if (potez == POTEZ_NA_DNO) {
			while(dozvoljeno(POTEZ_DOLE)) {
				tekuciIndexX++;
			}
			snimiFiguru();
		}
	}

	public boolean dozvoljeno(char potez) throws CloneNotSupportedException {
		if (potez == POTEZ_LIJEVO) {
			int i = -1, j = -1;
			boolean nadjen = false;
			for (j = 0; (j < figura.getDimenzija()) && !nadjen; j++) {
				for (i = 0; (i < figura.getDimenzija()) && !nadjen; i++) {
					nadjen = (figura.getMatrica(i, j) != 0);
				}
			}
			if (nadjen) j--;
//			System.out.println("razlicit od praznog na i = " + i + "\tj = " + j);
			if (j + tekuciIndexY <= 0)
				return false;
			boolean mozeLijevo = true;
			for (j = 0; (j < figura.getDimenzija()) && mozeLijevo;j++) {
				for (i = 0; (i < figura.getDimenzija()) && mozeLijevo; i++) {
// ako bude gresaka da vidite kako sam ja provjeravao koje su mi vrijednosti
//					System.out.println("i = " + i + "\tj = " + j + "\ttekuciIndexX = " + tekuciIndexX + "\ttekuciIndexY = " + tekuciIndexY);
//					System.out.println("\ttekuciIndexX + i - figura.getDimenzija() + 1 = " + (tekuciIndexX + i - figura.getDimenzija() + 1) + "\n\t\ttekuciIndexY + j - 1 = " + (tekuciIndexY + j - 1));
					if (figura.getMatrica(i, j) != 0) {
						if (tekuciIndexX + i - figura.getDimenzija() + 1 >= 0) {
							mozeLijevo &= (poljana[tekuciIndexX + i - figura.getDimenzija() + 1][tekuciIndexY + j - 1] == 0);
						}
					}
				}
			}
			return mozeLijevo;
		}
		if (potez == POTEZ_DESNO) {
			int i = -1, j = -1;
			boolean nadjen = false;
			for (j = figura.getDimenzija() - 1; (j >= 0) && !nadjen; j--) {
				for (i = 0; (i < figura.getDimenzija()) && !nadjen; i++) {
					nadjen = (figura.getMatrica(i, j) != 0);
				}
			}
			if (nadjen) j++;
//			System.out.println("razlicit od praznog na i = " + i + "\tj = " + j);
			if (j + tekuciIndexY >= dimY - 1)
				return false;
			boolean mozeDesno = true;
			for (j = 0; (j < figura.getDimenzija()) && mozeDesno;j++) {
				for (i = 0; (i < figura.getDimenzija()) && mozeDesno; i++) {
// ako bude gresaka da vidite kako sam ja provjeravao koje su mi vrijednosti
//					System.out.println("i = " + i + "\tj = " + j + "\ttekuciIndexX = " + tekuciIndexX + "\ttekuciIndexY = " + tekuciIndexY);
//					System.out.println("\ttekuciIndexX + i - figura.getDimenzija() + 1 = " + (tekuciIndexX + i - figura.getDimenzija() + 1) + "\n\t\ttekuciIndexY + j - 1 = " + (tekuciIndexY + j - 1));
					if (figura.getMatrica(i, j) != 0) {
//						System.out.println("Indexi: tekuciIndexX = " + tekuciIndexX + "\ttekuciIndexY = " + tekuciIndexY + "\ti = " + i + "\tj = " + j);
						if (tekuciIndexX + i - figura.getDimenzija() + 1 >= 0) {
							mozeDesno &= (poljana[tekuciIndexX + i - figura.getDimenzija() + 1][tekuciIndexY + j + 1] == 0);
						}
					}
				}
			}
			return mozeDesno;
		}
		if (potez == POTEZ_DOLE) {
			int i = -1, j = -1;
			boolean nadjen = false;
			for (i = figura.getDimenzija() - 1; (i >= 0) && !nadjen; i--) {
				for (j = figura.getDimenzija() - 1; (j >= 0) && !nadjen; j--) {
					nadjen = (figura.getMatrica(i, j) != 0);
				}
			}
			if (nadjen) i++;
			if (tekuciIndexX + i - figura.getDimenzija() + 2 >= dimX)
				return false;
			boolean mozeDole = true;
			for (i = 0; (i < figura.getDimenzija()) && mozeDole; i++) {
				for (j = 0; (j < figura.getDimenzija()) && mozeDole; j++) {
					if (figura.getMatrica(i, j) != 0) {
						if (tekuciIndexX + i - figura.getDimenzija() + 2 >= 0) {
							mozeDole &= (poljana[tekuciIndexX + i - figura.getDimenzija() + 2][tekuciIndexY + j] == 0);
						}
					}
				}
			}
			return mozeDole;
		}
		if (potez == POTEZ_ROTIRAJ) {
			Figura fig = (Figura)figura.clone();
			fig.rotate(smjerRotacije);
			boolean mozeRotirati = true;
			for (int i = 0; (i < fig.getDimenzija()) && mozeRotirati; i++) {
				for (int j = 0; (j < fig.getDimenzija()) && mozeRotirati; j++) {
					if (fig.getMatrica(i, j) != 0) {
						if (
								(tekuciIndexX + i - fig.getDimenzija() + 1 >= 0) &&
								(tekuciIndexX + i - fig.getDimenzija() + 1 < dimX) &&
								(tekuciIndexY + j >= 0) &&
								(tekuciIndexY + j < dimY)
								) {
							mozeRotirati &= (poljana[tekuciIndexX + i - fig.getDimenzija() + 1][tekuciIndexY + j] == 0);
						} else {
							mozeRotirati = false;
						}
					}
				}
			}
			return mozeRotirati;
		}
		return true;
	}

	private void snimiFiguru() {
		for (int i = tekuciIndexX - figura.getDimenzija() + 1; i <= tekuciIndexX; i++) {
			for (int j = tekuciIndexY; j < tekuciIndexY + figura.getDimenzija(); j++) {
				if ((figura.getMatrica(i + figura.getDimenzija() - 1 - tekuciIndexX, j - tekuciIndexY) != 0) && (i < 0)) {
					krajIgre = true;
				}
				if ((i >= 0) && (i < dimX) && (j >= 0) && (j < dimY)) {
					poljana[i][j] = poljana[i][j] + figura.getMatrica(i + figura.getDimenzija() - 1 - tekuciIndexX, j - tekuciIndexY);
				}
			}
		}
		ocistiRedove();
		izaberiFiguru();
	}

	public boolean krajIgre() {
		return krajIgre;
	}

	private void ocistiRedove() {
		for (int i = dimX - 1; i > 0; i--) {
			boolean pun = true;
			int j = 0;
			while (pun && (j < dimY)) {
				pun &= (poljana[i][j] != 0);
				j++;
			}
			if (pun) {
				for (int k = i; k > 0; k--) {
					for (int y = 0; y < dimY; y++) {
						poljana[k][y] = poljana[k - 1][y];
					}
				}
				for (int y = 0; y < dimY; y++) {
					poljana[0][y] = 0;
				}
				i++;
			}
		}
	}
	
	public int getDimX() {
		return dimX;
	}
	public int getDimY() {
		return dimY;
	}

	public int[][] getPoljana() {
		int[][] tmpPoljana = new int[dimX][dimY];
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				if ((i <= tekuciIndexX) && (i > tekuciIndexX - figura.getDimenzija()) &&
						(j >= tekuciIndexY) && (j < tekuciIndexY + figura.getDimenzija())) {
					tmpPoljana[i][j] = poljana[i][j] + figura.getMatrica(i + figura.getDimenzija() - 1 - tekuciIndexX, j - tekuciIndexY);
				} else {
					tmpPoljana[i][j] = poljana[i][j];
				}
			}
		}
		System.out.println(this);
		return tmpPoljana;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				if ((i <= tekuciIndexX) && (i > tekuciIndexX - figura.getDimenzija()) &&
						(j >= tekuciIndexY) && (j < tekuciIndexY + figura.getDimenzija())) {
					int tmp = poljana[i][j] + figura.getMatrica(i + figura.getDimenzija() - 1 - tekuciIndexX, j - tekuciIndexY);
					if (tmp != 0) {
						str = str + tmp;
					} else {
						str = str + '.';
					}
				} else {
					//str = str + poljana[i][j];
					if (poljana[i][j] == 0) {
						str += '.';
					} else {
						str = str + poljana[i][j];
					}
				}
			}
			str = str + "\n";
		}
		return str;
	}

}
