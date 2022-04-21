package fp.clinico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class EstudioClinicoStream implements EstudioClinico {

	private List<PacienteEstudio> ls;
	
	//C1: Construye el tipo EstudioClinicoBucles creando una lista sin elementos.
	public EstudioClinicoStream() {
		this.ls = new ArrayList<>();
	}
	
	//C2: Construye el tipo EstudioClinicoBucles a partir de una lista con elementos.
	public EstudioClinicoStream(List<PacienteEstudio> ls) {
		this.ls = ls;
	}
	
	//Parse: Construye un objeto del tipo PacienteEstudio.
	public static PacienteEstudio parseaLinea(String cadena) {
		return PacienteEstudio.parse(cadena);
	}
	
	@Override
	public Integer numeroPacientes() {
		return this.ls.size();
	}

	@Override
	public void incluyePaciente(PacienteEstudio paciente) {
		this.ls.add(paciente);
	}

	@Override
	public void incluyePacientes(Collection<PacienteEstudio> pacientes) {
		this.ls.addAll(pacientes);
	}

	@Override
	public void eliminaPaciente(PacienteEstudio paciente) {
		Checkers.check("El paciente no se encuentra en la lista", this.ls.contains(paciente));
		this.ls.remove(paciente);
	}

	@Override
	public Boolean estaPaciente(PacienteEstudio paciente) {
		return this.ls.contains(paciente);
	}

	@Override
	public void borraEstudio() {
		this.ls.clear();
	}

	@Override
	public EstudioClinico of(String nombreFichero) {
		return new EstudioClinicoBucles(this.leeFichero(nombreFichero));
	}

	@Override
	public List<PacienteEstudio> leeFichero(String nombreFichero) {
		return Ficheros.leeFichero("No se pudo leer el fichero", nombreFichero).stream()
				.map(x -> EstudioClinicoStream.parseaLinea(x))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean todosPacienteSonDelTipo(TipoResidencia tipo) {
		return this.ls.stream()
				.allMatch(x -> x.tipoResidencia().equals(tipo));
	}

	@Override
	public Boolean existeAlgunPacienteDelTipo(TipoResidencia tipo) {
		return this.ls.stream()
				.anyMatch(x -> x.tipoResidencia().equals(tipo));
	}

	@Override
	public Integer numeroPacientesFactorRiesgo() {
		return this.ls.stream()
				.filter(x -> x.factorRiesgo())
				.toList()
				.size();
	}

	@Override
	public Double edadMediaPacientesConFactorRiesgo() {
		return this.ls.stream()
				.filter(x -> x.factorRiesgo())
				.collect(Collectors.averagingDouble(x -> x.edad()));
	}

	@Override
	public List<PacienteEstudio> filtraPacientesPorEdad(Double edad) {
		return this.ls.stream()
				.filter(x -> x.edad() > edad)
				.toList();
	}

	@Override
	public Map<String, List<PacienteEstudio>> agruparPacientesEdadMayorQuePorGenero(Double edad) {
		return this.ls.stream()
				.filter(x -> x.edad() > edad)
				.collect(Collectors.groupingBy(x -> x.genero()));
	}

	@Override
	public Map<String, Long> numeroPacientesPorGenero() {
		return this.ls.stream()
				.collect(Collectors.groupingBy(x -> x.genero(), Collectors.counting()));
	}

	@Override
	public Map<String, Double> edadMediaPacientesPorPorGenero() {
		return this.ls.stream()
				.collect(Collectors.groupingBy(x -> x.genero(), Collectors.averagingDouble(x -> x.edad())));
	}

}
