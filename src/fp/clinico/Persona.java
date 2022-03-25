package fp.clinico;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public record Persona(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) implements Comparable<Persona>{
	
	//Constructor (lo redefinimos para añadir las restricciones)
	public Persona {
		//- La fecha de nacimiento debe ser anterior a la fecha actual.
		Checkers.check("La fecha de nacimiento debe ser anterior a la fecha actual", fechaNacimiento.isBefore(LocalDate.now()));
		//- El dni debe ser una cadena con ocho dígitos y seguidos de una letra.
		boolean isNumeric = dni.substring(0,7).chars().allMatch(Character::isDigit);
		boolean isChar = dni.substring(8,9).chars().allMatch(Character::isLetter);
		Checkers.check("El dni debe ser una cadena de 8 dígitos y seguidos de una letra", dni.length() == 9 && isNumeric && isChar);
	}
	
	//Métodos de factoría
	//- Método static of: Recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
	public static Persona of(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) {
		Persona res = new Persona(nombre, apellidos, dni, fechaNacimiento);
		return res;
	}
	//- Método static parse: Recibe una cadena con un formato específico y devuelve una persona. Ejemplo de cadena: “Juan, García Rodríguez, 12755078Z, 20/03/1965”.
	public static Persona parse(String cadena) {
		//Restricción por si la cadena dada está vacía.
		Checkers.checkNoNull("Cadena vacía", cadena);
		String[] datos = cadena.split(",");
		//Restricción para evitar excesivos parámetros o insuficientes parámetros
		Checkers.check("Error en los parámetros dados, solo se aceptan 4 parametros", datos.length == 4);
		String nombre = datos[0].trim();
		String apellidos = datos[1].trim();
		String dni = datos[2].trim();
		LocalDate fechaNacimiento = LocalDate.parse(datos[3], DateTimeFormatter.ofPattern("d/M/y"));
		return Persona.of(nombre, apellidos, dni, fechaNacimiento);
	}
	
	//Métodos de las propiedades
	public Integer edad() {
		return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
	}

	//· Representación como cadena: por defecto asociado al record.

	//· Criterio de igualdad: por defecto asociado al record.

	//· Orden natural: por dni.
	@Override
	public int compareTo(Persona o) {
		return this.dni().compareTo(o.dni());
	}
	
	//- Incluya un método main para comprobar el correcto funcionamiento del método parse. public static void main(String[] args){ … }
	public static void main(String[] args) {
		Persona p = Persona.parse("Juan, García Rodríguez, 12755078Z, 20/03/1965");
		System.out.println(p + "\n");
		System.out.println("Nombre: " + p.nombre());
		System.out.println("Apellidos: " + p.apellidos());
		System.out.println("DNI: " + p.dni());
		System.out.println("Fecha de nacimiento: " + p.fechaNacimiento());
		System.out.println("Edad: " + p.edad());
	}
}
