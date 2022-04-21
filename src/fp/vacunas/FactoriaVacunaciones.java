package fp.vacunas;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import fp.utiles.Ficheros;

public class FactoriaVacunaciones {
	
	//Parse: Construye un objeto del tipo PacienteEstudio.
	public static Vacunacion parseaLinea(String cadena) {
		return Vacunacion.parse(cadena);
	}
	
	public static List<Vacunacion> leeFichero(String nombreFichero) {
		return Ficheros.leeFichero("El fichero no pudo ser leido", nombreFichero, Charset.forName("UTF-8")).stream()
				.skip(1)
				.map(x -> FactoriaVacunaciones.parseaLinea(x))
				.collect(Collectors.toList());
	}
	
}
