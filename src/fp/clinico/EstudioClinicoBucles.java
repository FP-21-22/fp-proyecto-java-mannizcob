package fp.clinico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class EstudioClinicoBucles implements EstudioClinico {

	private List<PacienteEstudio> ls;
	
	//C1: Construye el tipo EstudioClinicoBucles creando una lista sin elementos.
	public EstudioClinicoBucles() {
		this.ls = new ArrayList<>();
	}
	
	//C2: Construye el tipo EstudioClinicoBucles a partir de una lista con elementos.
	public EstudioClinicoBucles(List<PacienteEstudio> ls) {
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
		List<String> lista = Ficheros.leeFichero("El fichero no pudo ser leido", nombreFichero);
		List<PacienteEstudio> ls = new ArrayList<>();
		for (String s: lista) {
			ls.add(EstudioClinicoBucles.parseaLinea(s));
		}
		return ls;
	}

	@Override
	public Boolean todosPacienteSonDelTipo(TipoResidencia tipo) {
		Boolean res = true;
		for (PacienteEstudio p: this.ls) {
			if (!p.tipoResidencia().equals(tipo)) {
				res = false;
				return res;
			}
		}
		return res;
	}

	@Override
	public Boolean existeAlgunPacienteDelTipo(TipoResidencia tipo) {
		Boolean res = false;
		for (PacienteEstudio p: this.ls) {
			if (p.tipoResidencia().equals(tipo)) {
				res = true;
				return res;
			}
		}
		return res;
	}

	@Override
	public Integer numeroPacientesFactorRiesgo() {
		Integer res = 0;
		for (PacienteEstudio p: this.ls) {
			if (p.factorRiesgo()) {
				res += 1;
			}
		}
		return res;
	}

	@Override
	public Double edadMediaPacientesConFactorRiesgo() {
		Double res = 0.0;
		for (PacienteEstudio p: this.ls) {
			if (p.factorRiesgo()) {
				res += p.edad();
			}
		}
		return res / this.numeroPacientesFactorRiesgo();
	}

	@Override
	public List<PacienteEstudio> filtraPacientesPorEdad(Double edad) {
		List<PacienteEstudio> lista = new ArrayList<>();
		for (PacienteEstudio p: this.ls) {
			if (p.edad().equals(edad)) {
				lista.add(p);
			}
		}
		return lista;
	}

	@Override
	public Map<String, List<PacienteEstudio>> agruparPacientesEdadMayorQuePorGenero(Double edad) {
		Map<String, List<PacienteEstudio>> map = new HashMap<>();
		for (PacienteEstudio p: this.ls) {
			if (p.edad() > edad) {
				if (map.containsKey(p.genero())) {
					List<PacienteEstudio> ls = map.get((p.genero()));
					ls.add(p);
					map.replace(p.genero(), ls);
				} else {
					List<PacienteEstudio> ls = new ArrayList<>();
					ls.add(p);
					map.put(p.genero(), ls);
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Long> numeroPacientesPorGenero() {
		Map<String, Long> map = new HashMap<>();
		for (PacienteEstudio p : this.ls) {
			if (map.containsKey(p.genero())) {
				Long numero = map.get((p.genero()));
				numero += 1;
				map.replace(p.genero(), numero);
			} else {
				Long numero = (long) 1;
				map.put(p.genero(), numero);
			}
		}
		return map;
	}

	@Override
	public Map<String, Double> edadMediaPacientesPorPorGenero() {
		Map<String, Double> map = new HashMap<>();
		for (PacienteEstudio p : this.ls) {
			if (map.containsKey(p.genero())) {
				Double edad = map.get((p.genero()));
				edad += p.edad();
				map.replace(p.genero(), edad);
			} else {
				Double edad = p.edad();
				map.put(p.genero(), edad);
			}
		}
		for (Map.Entry<String, Double> e: map.entrySet()) {
			map.replace(e.getKey(), e.getValue() / this.numeroPacientesPorGenero().get(e.getKey()));
		}
		return map;
	}

	@Override
	public String toString() {
		return ls.stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
	}
	
	

}
