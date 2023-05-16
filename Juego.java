import java.util.Scanner;

public class Juego {

	private int[][] tab;
	private int nroObjetivo;
	private boolean enJuego;

	public Juego() {
		nroObjetivo = 1024;
	}

	private void sigueJugando() {

//		boolean saturado = true;
//
//		loop: for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				try {
//					if ((tab[i][j] == tab[i - 1][j]) || (tab[i][j] == tab[i][j + 1]) || (tab[i][j] == tab[i + 1][j])
//							|| (tab[i][j] == tab[i][j - 1])) {
//						saturado = false;
//						break loop;
//					}
//				} catch (Exception e) {
//					//saturado = true;
//				}
//			}
//		}

		if (/*saturado || */hay1024()) {
			enJuego = false;
		}

	}

	private boolean hay1024() {
		boolean hay = false;

		loop: for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (tab[i][j] == nroObjetivo) {
					hay = true;
					break loop;
				}
			}
		}

		return hay;
	}

	public static void clear() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public String incializar() {
		String reporte;

		tab = new int[4][4];

		ubicar(1);
		ubicar(1);

		enJuego = true;

		reporte = mostrar();

		return reporte;
	}

	private String mostrar() {
		String reporte = "";

		for (int i = 0; i < 4; i++) {
			for (int a = 0; a < 4; a++) {
				reporte += tab[i][a] + "\t";
			}
			reporte += "\n";
		}

		return reporte;
	}

	private void ubicar(int v) {
		int i;
		int j;

		do {
			i = (int) (Math.random() * 4);
			j = (int) (Math.random() * 4);
		} while (tab[i][j] != 0);

		tab[i][j] = v;
	}

	public void moverU() {

		for (int columna = 0; columna < 4; columna++) {
			for (int fila = 1; fila < 4; fila++) {
				if (tab[fila - 1][columna] == 0 && tab[fila][columna] != 0) {
					tab[fila - 1][columna] = tab[fila][columna];
					tab[fila][columna] = 0;
					if (fila != 1) {
						fila -= 2;
					} else {
						fila--;
					}
				} else {
					if (tab[fila - 1][columna] == tab[fila][columna]) {
						tab[fila - 1][columna] = tab[fila - 1][columna] + tab[fila][columna];
						tab[fila][columna] = 0;
					}
				}
			}
		}

	}

	public void moverR() {
		for (int fila = 0; fila < 4; fila++) {
			for (int columna = 2; columna >= 0; columna--) {
				if (tab[fila][columna + 1] == 0 && tab[fila][columna] != 0) {
					tab[fila][columna + 1] = tab[fila][columna];
					tab[fila][columna] = 0;
					if (columna != 2) {
						columna += 2;
					} else {
						columna++;
					}
				} else {
					if (tab[fila][columna + 1] == tab[fila][columna]) {
						tab[fila][columna + 1] = tab[fila][columna + 1] + tab[fila][columna];
						tab[fila][columna] = 0;
					}
				}
			}
		}
	}

	public void moverL() {
		for (int fila = 0; fila < 4; fila++) {
			for (int columna = 1; columna < 4; columna++) {
				if (tab[fila][columna - 1] == 0 && tab[fila][columna] != 0) {
					tab[fila][columna - 1] = tab[fila][columna];
					tab[fila][columna] = 0;
					if (columna != 1) {
						columna -= 2;
					} else {
						columna--;
					}
				} else {
					if (tab[fila][columna - 1] == tab[fila][columna]) {
						tab[fila][columna - 1] = tab[fila][columna - 1] + tab[fila][columna];
						tab[fila][columna] = 0;
					}
				}
			}
		}
	}

	public void moverD() {
		for (int columna = 0; columna < 4; columna++) {
			for (int fila = 2; fila >= 0; fila--) {
				if (tab[fila + 1][columna] == 0 && tab[fila][columna] != 0) {
					tab[fila + 1][columna] = tab[fila][columna];
					tab[fila][columna] = 0;
					if (fila != 2) {
						fila += 2;
					} else {
						fila++;
					}
				} else {
					if (tab[fila + 1][columna] == tab[fila][columna]) {
						tab[fila + 1][columna] = tab[fila + 1][columna] + tab[fila][columna];
						tab[fila][columna] = 0;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Juego juego = new Juego();

		System.out.println(juego.incializar());

		do {

			juego.sigueJugando();

			System.out.print("w = arriba ; s = abajo ; a = izquierda ; d = derecha ; o = salir\n" + "Digite una letra para mover: ");
			String option = sc.next();
			
			if (option.equals("o")) {
				break;
			}

			int n = (int) (Math.random() * 2) + 1;

			switch (option) {
			case "w":
				clear();

				juego.moverU();
				juego.ubicar(n);

				System.out.println(juego.mostrar());

				break;
			case "s":
				clear();

				juego.moverD();
				juego.ubicar(n);

				System.out.println(juego.mostrar());

				break;
			case "a":
				clear();

				juego.moverL();
				juego.ubicar(n);

				System.out.println(juego.mostrar());

				break;
			case "d":
				clear();

				juego.moverR();
				juego.ubicar(n);

				System.out.println(juego.mostrar());

				break;
			default:
				System.out.println("Digite algo valido");

			}

		} while (juego.enJuego);

		System.out.println("FIN DEL JUEGO");

	}

}
