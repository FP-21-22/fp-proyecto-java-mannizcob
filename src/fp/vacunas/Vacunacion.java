package fp.vacunas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public record Vacunacion(LocalDate fecha, String comunidad, Integer pfizer, Integer moderna, Integer astrazeneca, Integer janssen, Integer numeroPersonas) implements Comparable<Vacunacion>{
	
	//Constructor (lo redefinimos para añadir las restricciones)
	public Vacunacion {
		//- La fecha de debe ser posterior al 01/02/2021.
		Checkers.check("La fecha " + fecha + " debe ser posterior a " + LocalDate.of(2021, 1, 2), fecha.isAfter(LocalDate.of(2021, 1, 2)));
	}
	
	//Métodos de factoría
	//- Método static of: recibe valores para cada propiedad básica y devuelve un objeto del tipo.
	public static Vacunacion of(LocalDate fecha, String comunidad, Integer pfizer, Integer moderna, Integer astrazeneca, Integer janssen, Integer numeroPersonas) {
		return new Vacunacion(fecha, comunidad, pfizer, moderna, astrazeneca, janssen, numeroPersonas);
	}
	//- Método static parse: recibe una cadena con un formato específico y devuelve un objeto del tipo. Ejemplo de cadena: “04/01/2021;Andalucía;140295;0;0;0;0”.
	public static Vacunacion parse(String cadena) {
		//Restricción por si la cadena dada está vacía.
		Checkers.checkNoNull("Cadena vacía", cadena);
		String[] datos = cadena.split(";");
		//Restricción para evitar excesivos parámetros o insuficientes parámetros
		Checkers.check("Error en los parámetros dados, solo se aceptan 7 parametros", datos.length == 7);
		LocalDate fecha = LocalDate.parse(datos[0], DateTimeFormatter.ofPattern("d/M/y"));
		String comunidad = datos[1];
		Integer pfizer = Integer.parseInt(datos[2]);
		Integer moderna = Integer.parseInt(datos[3]);
		Integer astrazeneca = Integer.parseInt(datos[4]);
		Integer janssen = Integer.parseInt(datos[5]);
		Integer numeroPersonas = Integer.parseInt(datos[6]);
		return Vacunacion.of(fecha, comunidad, pfizer, moderna, astrazeneca, janssen, numeroPersonas);
	}
	
	//Métodos de las propiedades
	public Integer numeroTotal() {
		return (this.pfizer + this.moderna + this.astrazeneca + this.janssen);
	}
	
	//· Representación como cadena: por defecto asociado al record.

	//· Criterio de igualdad: por defecto asociado al record.

	//· Orden natural: por comunidad y en caso de igualdad por fecha.
	@Override
	public int compareTo(Vacunacion v) {
		Integer res = this.comunidad().compareTo(v.comunidad());
		if(res==0) {
			res = this.fecha().compareTo(v.fecha());
		}
		return res;
	}
	
	//- Incluya un método main para comprobar el correcto funcionamiento del método parse. public static void main(String[] args){ … }
	public static void main(String[] args) {
		Vacunacion v = Vacunacion.parse("04/02/2021;Andalucía;140295;0;0;0;0");
		System.out.println(v + "\n");
		System.out.println("Fecha: " + v.fecha());
		System.out.println("Comunidad: " + v.comunidad());
		System.out.println("Pfizer: " + v.pfizer());
		System.out.println("Moderna: " + v.moderna());
		System.out.println("Astrazeneca: " + v.astrazeneca());
		System.out.println("Janssen: " + v.janssen());
		System.out.println("Número de personas: " + v.numeroPersonas());
		System.out.println("Número total de vacunas: " + v.numeroTotal());
	}
}
