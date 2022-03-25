package fp.clinico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import fp.utiles.Checkers;

public record Paciente(Persona persona, String codigoImpreso, LocalDateTime fechaHoraIngreso) {

	//Constructor (lo redefinimos para añadir las restricciones)
	public Paciente {
		//- La fecha y hora de ingreso debe ser anterior o igual a la fecha actual.
		Checkers.check("La fecha y hora de ingreso debe ser anterior o igual a la fecha actual", fechaHoraIngreso.isBefore(LocalDateTime.now()) || fechaHoraIngreso.isEqual(LocalDateTime.now()));
	}
	
	//Métodos de factoría
	//- Método static of: recibe nombre, apellidos, dni, fecha de nacimiento, código y fecha y hora de ingreso y devuelve un paciente.
	public static Paciente of(String nombre, String apellidos, String dni, LocalDate fechaNacimiento, String codigoImpreso, LocalDateTime fechaHoraIngreso) {
		Paciente res = new Paciente(Persona.of(nombre, apellidos, dni, fechaNacimiento), codigoImpreso, fechaHoraIngreso);
		return res;
	}
	//- Método static of: recibe un objeto persona, un código y una fecha y hora de ingreso y devuelve un paciente.
	public static Paciente of(Persona p, String codigoImpreso, LocalDateTime fechaHoraIngreso) {
		Paciente res = Paciente.of(p, codigoImpreso, fechaHoraIngreso);
		return res;
	}
	
	//Métodos de las propiedades
	public LocalDate fechaIngreso() {
		return this.fechaHoraIngreso.toLocalDate();
	}
	public String horaIngreso() {
		return this.fechaHoraIngreso.toLocalTime().toString();
	}
	
	//· Representación como cadena: por defecto asociado al record.
	
	//· Criterio de igualdad: por defecto asociado al record.
}
