package testiranje;

import java.util.Random;
import java.util.Scanner;

import logika.Figura;
import logika.PoljanaZaIgru;

public class test1 {

	public static void main(String[] args) throws CloneNotSupportedException {
		PoljanaZaIgru pzi = new PoljanaZaIgru(20, 10, "Classic", 1);
		Scanner sc = new Scanner(System.in);
		String ulaz = "";
		while (true) {
			ulaz = sc.next();
			for (int i = 0; i < ulaz.length(); i++) {
				if (ulaz.charAt(i) == '7') {
					pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_LIJEVO);
				}
				if (ulaz.charAt(i) == '8') {
					pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_ROTIRAJ);
				}
				if (ulaz.charAt(i) == '9') {
					pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DESNO);
				}
				if (ulaz.charAt(i) == '4') {
					pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
				}
				if (ulaz.charAt(i) == '1') {
					pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_NA_DNO);
				}
			}
			pzi.pomjeriFiguru(PoljanaZaIgru.POTEZ_DOLE);
			System.out.println(pzi);
		}
	}

}
