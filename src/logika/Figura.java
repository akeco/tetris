package logika;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.omg.CORBA.portable.Streamable;

public class Figura implements Cloneable {
	private String naziv;
	private int[][] matrica;
	private int dimenzija;

	public Figura(String tip, String naziv) {
		this.naziv = naziv;
		FileReader fr;
		BufferedReader br;
		try {
			// pogledamo na google-u kako se citaju file-ovi i to primjenimo :)
			fr = new FileReader(tip + "/" + naziv + ".fig");
			br = new BufferedReader(fr);
			dimenzija = Integer.parseInt(br.readLine());
			matrica = new int[dimenzija][dimenzija];
			for (int i = 0; i < matrica.length; i++) {
				Scanner sc = new Scanner(br.readLine());
				for (int j = 0; j < matrica[i].length; j++) {
					matrica[i][j] = sc.nextInt();
				}
			}
			fr.close();
		} catch (Exception e) {
			naziv = "Ne valja";
			dimenzija = 2;
			matrica = new int[][]{{1, 2}, {3, 4}};
		}
	}

	public void rotate() {
		rotate(1);
	}

	public void rotate(int smjer) {
		int[][] tmp = new int[dimenzija][dimenzija];
		for (int i = 0; i < dimenzija; i++) {
			for (int j = 0; j < dimenzija; j++) {
				if (smjer > 0)
					tmp[i][j] = matrica[j][dimenzija - i - 1];
				else
					tmp[i][j] = matrica[dimenzija - j - 1][i];
			}
		}
		matrica = tmp;
	}

	public int getDimenzija() {
		return dimenzija;
	}

	public int getMatrica(int i, int j) {
		if ((i >= 0) && (i < dimenzija) && (j >= 0) && (j < dimenzija))
			return matrica[i][j];
		return -1;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				str += matrica[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
