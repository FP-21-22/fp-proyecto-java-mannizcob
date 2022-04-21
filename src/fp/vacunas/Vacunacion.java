package fp.vacunas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public record Vacunacion(LocalDate fecha, String comunidad, Integer pfizer, Integer moderna, Integer astrazeneca, Integer janssen, Integer numeroPersonas) implements Comparable<Vacunacion>{
	
	//Constructor (lo redefinimos para a�adir las restricciones)
	public Vacunacion {
		//- La fecha de debe ser posterior al 01/02/2021.
		Checkers.check("La fecha " + fecha + " debe ser posterior a " + LocalDate.of(2021, 1, 2), fecha.isAfter(LocalDate.of(2021, 1, 2)));
	}
	
	//M�todos de factor�a
	//- M�todo static of: recibe valores para cada propiedad b�sica y devuelve un objeto del tipo.
	public static Vacunacion of(LocalDate fecha, String comunidad, Integer pfizer, Integer moderna, Integer astrazeneca, Integer janssen, Integer numeroPersonas) {
		return new Vacunacion(fecha, comunidad, pfizer, moderna, astrazeneca, janssen, numeroPersonas);
	}
	//- M�todo static parse: recibe una cadena con un formato espec�fico y devuelve un objeto del tipo. Ejemplo de cadena: �04/01/2021;Andaluc�a;140295;0;0;0;0�.
	public static Vacunacion parse(String cadena) {
		//Restricci�n por si la cadena dada est� vac�a.
		Checkers.checkNoNull("Cadena vac�a", cadena);
		String[] datos = cadena.split(";");
		//Restricci�n para evitar excesivos par�metros o insuficientes par�metros
		Checkers.check("Error en los par�metros dados, solo se aceptan 7 parametros", datos.length == 7);
		LocalDate fecha = LocalDate.parse(datos[0], DateTimeFormatter.ofPattern("d/M/y"));
		String comunidad = datos[1];
		Integer pfizer = Integer.parseInt(datos[2]);
		Integer moderna = Integer.parseInt(datos[3]);
		Integer astrazeneca = Integer.parseInt(datos[4]);
		Integer janssen = Integer.parseInt(datos[5]);
		Integer numeroPersonas = Integer.parseInt(datos[6]);
		return Vacunacion.of(fecha, comunidad, pfizer, moderna, astrazeneca, janssen, numeroPersonas);
	}
	
	//M�todos de las propiedades
	public Integer numeroTotal() {
		return (this.pfizer + this.moderna + this.astrazeneca + this.janssen);
	}
	
	//� Representaci�n como cadena: por defecto asociado al record.

	//� Criterio de igualdad: por defecto asociado al record.

	//� Orden natural: por comunidad y en caso de igualdad por fecha.
	@Override
	public int compareTo(Vacunacion v) {
		Integer res = this.comunidad().compareTo(v.comunidad());
		if(res==0) {
			res = this.fecha().compareTo(v.fecha());
		}
		return res;
	}
	
	//- Incluya un m�todo main para comprobar el correcto funcionamiento del m�todo parse. public static void main(String[] args){ � }
	public static void main(String[] args) {
		Vacunacion v = Vacunacion.parse("04/02/2021;Andaluc�a;140295;0;0;0;0");
		System.out.println(v + "\n");
		System.out.println("Fecha: " + v.fecha());
		System.out.println("Comunidad: " + v.comunidad());
		System.out.println("Pfizer: " + v.pfizer());
		System.out.println("Moderna: " + v.moderna());
		System.out.println("Astrazeneca: " + v.astrazeneca());
		System.out.println("Janssen: " + v.janssen());
		System.out.println("N�mero de personas: " + v.numeroPersonas());
		System.out.println("N�mero total de vacunas: " + v.numeroTotal());
	}
}
