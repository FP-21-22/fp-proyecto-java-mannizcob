package fp.vacunas;

import java.util.List;

public class TestVacunacion {
	public static void main(String[] args) {
		List<Vacunacion> a = FactoriaVacunaciones.leeFichero("data/ccaa_vacunas_3.csv");
		for (Vacunacion v: a) {
			System.out.println(v);
		}
	}
}
