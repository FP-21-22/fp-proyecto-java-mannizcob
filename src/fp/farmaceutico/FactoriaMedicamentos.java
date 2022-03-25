package fp.farmaceutico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public class FactoriaMedicamentos {
	
	//M�todo de factor�a
	//- Programe una clase de nombre FactoriaMedicamentos que incluya, de momento, un m�todo static de nombre parseaMedicamente, que recibe una cadena con un formato espec�fico y devuelve un objeto de tipo Medicamento.
	public static Medicamento parseaMedicamento(String cadena) {
		//Restricci�n por si la cadena dada est� vac�a.
		Checkers.checkNoNull("Cadena vac�a", cadena);
		String[] datos = cadena.split(",");
		//Restricci�n para evitar excesivos par�metros o insuficientes par�metros
		Checkers.check("Error en los par�metros dados, solo se aceptan 7 parametros", datos.length == 7);
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
			res = TipoMedicamento.ANAT�MICO;
		}
		else if(cadena.contentEquals("QUIMICO")){
			res = TipoMedicamento.QU�MICO;
		}
		else if(cadena.contentEquals("TERAPEUTICO")) {
			res = TipoMedicamento.TERAP�UTICO;
		}
		else {
			res = null;
		}
		return res;
	}
}
