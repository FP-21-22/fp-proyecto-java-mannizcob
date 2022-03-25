package fp.clinico;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public record Persona(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) implements Comparable<Persona>{
	
	//Constructor (lo redefinimos para a�adir las restricciones)
	public Persona {
		//- La fecha de nacimiento debe ser anterior a la fecha actual.
		Checkers.check("La fecha de nacimiento debe ser anterior a la fecha actual", fechaNacimiento.isBefore(LocalDate.now()));
		//- El dni debe ser una cadena con ocho d�gitos y seguidos de una letra.
		boolean isNumeric = dni.substring(0,7).chars().allMatch(Character::isDigit);
		boolean isChar = dni.substring(8,9).chars().allMatch(Character::isLetter);
		Checkers.check("El dni debe ser una cadena de 8 d�gitos y seguidos de una letra", dni.length() == 9 && isNumeric && isChar);
	}
	
	//M�todos de factor�a
	//- M�todo static of: Recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
	public static Persona of(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) {
		Persona res = new Persona(nombre, apellidos, dni, fechaNacimiento);
		return res;
	}
	//- M�todo static parse: Recibe una cadena con un formato espec�fico y devuelve una persona. Ejemplo de cadena: �Juan, Garc�a Rodr�guez, 12755078Z, 20/03/1965�.
	public static Persona parse(String cadena) {
		//Restricci�n por si la cadena dada est� vac�a.
		Checkers.checkNoNull("Cadena vac�a", cadena);
		String[] datos = cadena.split(",");
		//Restricci�n para evitar excesivos par�metros o insuficientes par�metros
		Checkers.check("Error en los par�metros dados, solo se aceptan 4 parametros", datos.length == 4);
		String nombre = datos[0].trim();
		String apellidos = datos[1].trim();
		String dni = datos[2].trim();
		LocalDate fechaNacimiento = LocalDate.parse(datos[3], DateTimeFormatter.ofPattern("d/M/y"));
		return Persona.of(nombre, apellidos, dni, fechaNacimiento);
	}
	
	//M�todos de las propiedades
	public Integer edad() {
		return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
	}

	//� Representaci�n como cadena: por defecto asociado al record.

	//� Criterio de igualdad: por defecto asociado al record.

	//� Orden natural: por dni.
	@Override
	public int compareTo(Persona o) {
		return this.dni().compareTo(o.dni());
	}
	
	//- Incluya un m�todo main para comprobar el correcto funcionamiento del m�todo parse. public static void main(String[] args){ � }
	public static void main(String[] args) {
		Persona p = Persona.parse("Juan, Garc�a Rodr�guez, 12755078Z, 20/03/1965");
		System.out.println(p + "\n");
		System.out.println("Nombre: " + p.nombre());
		System.out.println("Apellidos: " + p.apellidos());
		System.out.println("DNI: " + p.dni());
		System.out.println("Fecha de nacimiento: " + p.fechaNacimiento());
		System.out.println("Edad: " + p.edad());
	}
}
