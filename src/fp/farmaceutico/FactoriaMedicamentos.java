package fp.farmaceutico;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

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
		TipoMedicamento tipoMedicamento = TipoMedicamento.valueOf(datos[1].toUpperCase());
		String codigoEnfermedad = datos[2];
		String farmaceutica = datos[3];
		Double puntuacion = Double.parseDouble(datos[4]);
		Integer indiceSomatico = Integer.parseInt(datos[5]);
		LocalDate fechaCatalogo = LocalDate.parse(datos[6], DateTimeFormatter.ofPattern("d/M/y"));
		return new Medicamento(nombreMedicamento, tipoMedicamento, codigoEnfermedad, farmaceutica, puntuacion, indiceSomatico, fechaCatalogo);
	}
	
	public static List<Medicamento> leeFichero(String nombreFichero) {
		return Ficheros.leeFichero("El fichero no pudo ser leido", nombreFichero, Charset.forName("UTF-8")).stream()
				.skip(1)
				.map(x -> FactoriaMedicamentos.parseaMedicamento(x))
				.collect(Collectors.toList());
	}

}
