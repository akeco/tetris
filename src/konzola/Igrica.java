package konzola;

import java.io.Console;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

import logika.PoljanaZaIgru;

public class Igrica {
	int trenutniLevel;
	int dimX, dimY;
	PoljanaZaIgru pzi;
	private static final int MAX_LEVEL = 9;

	public static void main(String[] args) throws CloneNotSupportedException, IOException {
		Igrica igrica = new Igrica();
		igrica.odaberiDimenzije();
		igrica.pzi = new PoljanaZaIgru(igrica.dimX, igrica.dimY, "Classic", 1);
		prikaziStanjePoljane(igrica.pzi.getPoljana());
		int offset = 0;
		do {
			DataInputStream in = new DataInputStream(System.in);
			byte b = in.readByte();
			char ch=(char)b;

			if (ch == '7') {
				igrica.pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_LIJEVO);
			}
			if (ch == '8') {
				igrica.pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_ROTIRAJ);
			}
			if (ch == '9') {
				igrica.pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DESNO);
			}
			if (ch == '4') {
				igrica.pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
			}
			if (ch == ' ') {
				igrica.pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_NA_DNO);
			}

			offset++;
			if (offset % (MAX_LEVEL - igrica.trenutniLevel) == 0) {
				igrica.pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
				offset = 0;
			}
			prikaziStanjePoljane(igrica.pzi.getPoljana());
		} while(!igrica.pzi.krajIgre());
		System.out.println(igrica.trenutniLevel);
		System.out.println(igrica.dimX);
		System.out.println(igrica.dimY);
	}

	private static void prikaziStanjePoljane(int[][] poljana) {
		for (int i = 0; i < poljana.length; i++) {
			for (int j = 0; j < poljana[i].length; j++) {
				if (poljana[i][j] == 0) {
					System.out.print(". ");
				} else {
					System.out.print(poljana[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private void odaberiDimenzije() {
		System.out.print("Odaberite visinu poljane: ");
		dimX = ucitajInt();
		System.out.print("Odaberite sirinu poljane: ");
		dimY = ucitajInt();
		System.out.print("Odaberite brzinu: ");
		trenutniLevel = ucitajInt();
	}

	private int ucitajInt() {
		Scanner sc = new Scanner(System.in);
		try {
			return sc.nextInt();
		} catch(Exception e) {
			System.out.println("Nije unesen ispravan broj!!!");
			return ucitajInt();
		}
	}

}
