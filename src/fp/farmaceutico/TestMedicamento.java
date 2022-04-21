package fp.farmaceutico;

import java.util.List;

public class TestMedicamento {

	public static void main(String[] args) {
		List<Medicamento> a = FactoriaMedicamentos.leeFichero("data/medicamentos.csv");
		for (Medicamento v: a) {
			System.out.println(v);
		}
	}

}
