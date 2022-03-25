package fp.farmaceutico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public class FactoriaMedicamentos {
	
	//Método de factoría
	//- Programe una clase de nombre FactoriaMedicamentos que incluya, de momento, un método static de nombre parseaMedicamente, que recibe una cadena con un formato específico y devuelve un objeto de tipo Medicamento.
	public static Medicamento parseaMedicamento(String cadena) {
		//Restricción por si la cadena dada está vacía.
		Checkers.checkNoNull("Cadena vacía", cadena);
		String[] datos = cadena.split(",");
		//Restricción para evitar excesivos parámetros o insuficientes parámetros
		Checkers.check("Error en los parámetros dados, solo se aceptan 7 parametros", datos.length == 7);
		String nombreMedicamento = datos[0];
		TipoMedicamento tipoMedicamento = isTipoMedicamento(datos[1]);
		String codigoEnfermedad = datos[2];
		String farmaceutica = datos[3];
		Double puntuacion = Double.parseDouble(datos[4]);
		Integer indiceSomatico = Integer.parseInt(datos[5]);
		LocalDate fechaCatalogo = LocalDate.parse(datos[6], DateTimeFormatter.ofPattern("d/M/y"));
		Medicamento res = new Medicamento(nombreMedicamento, tipoMedicamento, codigoEnfermedad, farmaceutica, puntuacion, indiceSomatico, fechaCatalogo);
		return res;
	}
	
	private static TipoMedicamento isTipoMedicamento(String cadena) {
		TipoMedicamento res;
		cadena = cadena.toUpperCase().toString();
		if(cadena.contentEquals("ANATOMICO")) {
			res = TipoMedicamento.ANATÓMICO;
		}
		else if(cadena.contentEquals("QUIMICO")){
			res = TipoMedicamento.QUÍMICO;
		}
		else if(cadena.contentEquals("TERAPEUTICO")) {
			res = TipoMedicamento.TERAPÉUTICO;
		}
		else {
			res = null;
		}
		return res;
	}
}
