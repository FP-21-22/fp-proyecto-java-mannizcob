package fp.clinico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import fp.utiles.Checkers;

public record Paciente(Persona persona, String codigoImpreso, LocalDateTime fechaHoraIngreso) {

	//Constructor (lo redefinimos para a�adir las restricciones)
	public Paciente {
		//- La fecha y hora de ingreso debe ser anterior o igual a la fecha actual.
		Checkers.check("La fecha y hora de ingreso debe ser anterior o igual a la fecha actual", fechaHoraIngreso.isBefore(LocalDateTime.now()) || fechaHoraIngreso.isEqual(LocalDateTime.now()));
	}
	
	//M�todos de factor�a
	//- M�todo static of: recibe nombre, apellidos, dni, fecha de nacimiento, c�digo y fecha y hora de ingreso y devuelve un paciente.
	public static Paciente of(String nombre, String apellidos, String dni, LocalDate fechaNacimiento, String codigoImpreso, LocalDateTime fechaHoraIngreso) {
		Paciente res = new Paciente(Persona.of(nombre, apellidos, dni, fechaNacimiento), codigoImpreso, fechaHoraIngreso);
		return res;
	}
	//- M�todo static of: recibe un objeto persona, un c�digo y una fecha y hora de ingreso y devuelve un paciente.
	public static Paciente of(Persona p, String codigoImpreso, LocalDateTime fechaHoraIngreso) {
		Paciente res = Paciente.of(p, codigoImpreso, fechaHoraIngreso);
		return res;
	}
	
	//M�todos de las propiedades
	public LocalDate fechaIngreso() {
		return this.fechaHoraIngreso.toLocalDate();
	}
	public String horaIngreso() {
		return this.fechaHoraIngreso.toLocalTime().toString();
	}
	
	//� Representaci�n como cadena: por defecto asociado al record.
	
	//� Criterio de igualdad: por defecto asociado al record.
}
