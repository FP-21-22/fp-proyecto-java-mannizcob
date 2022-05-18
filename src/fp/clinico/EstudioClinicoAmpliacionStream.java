package fp.clinico;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstudioClinicoAmpliacionStream extends EstudioClinicoStream implements EstudioClinicoAmpliacion {

	protected List<PacienteEstudio> pacientes;
	
	public EstudioClinicoAmpliacionStream() {
		super();
	}
	
	public EstudioClinicoAmpliacionStream(List<PacienteEstudio> lista) {
		super(lista);
	}
	
	public EstudioClinicoAmpliacionStream(Stream<PacienteEstudio> st) {
		super(st);
	}
	
	@Override
	public Map<TipoResidencia, Integer> agruparNumeroPacientesPorTipoResidencia() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::tipoResidencia, 
						Collectors.collectingAndThen(Collectors.toList(), ls -> ls.size())));
	}

	@Override
	public Map<TipoResidencia, Double> agruparNivelMedioGlucosaMedioPorTipoResidencia() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::tipoResidencia,
						Collectors.averagingDouble(PacienteEstudio::nivelMedioGlucosa)));
	}

	@Override
	public Map<TipoResidencia, PacienteEstudio> agruparNivelMedioGlucosaMaximoPorTipoResidencia() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::tipoResidencia,
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(PacienteEstudio::nivelMedioGlucosa)), o -> o.get())));
	}

	@Override
	public Map<String, List<PacienteEstudio>> agrupaPacientesPorGenero() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::genero));
	}

	@Override
	public Map<String, Set<PacienteEstudio>> agrupaPacientesPorPorGeneroEnConjunto() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::genero, 
						Collectors.toSet()));
	}

	@Override
	public Map<String, SortedSet<PacienteEstudio>> agrupaPacientesPorPorGeneroEnConjuntoOrdenado() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::genero, 
						Collectors.toCollection(TreeSet::new)));
	}

	@Override
	public Map<String, PacienteEstudio> pacienteEdadMaximaPacientesPorGenero() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::genero,
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(PacienteEstudio::edad)), o -> o.get())));
	}

	@Override
	public Map<String, List<Double>> listaEdadesPorGenero() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::genero,
						Collectors.mapping(PacienteEstudio::edad, Collectors.toList())));
	}

	@Override
	public Map<String, Double> edadMaximaPacientesPorGenero() {
		return this.pacientes.stream()
				.collect(Collectors.groupingBy(PacienteEstudio::genero,
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(PacienteEstudio::edad)), o -> o.get().edad())));
	}

	@Override
	public String generoEdadMaximaPacientesPorGenero() {
		return this.edadMaximaPacientesPorGenero().entrySet().stream()
				.max(Entry.comparingByValue())
				.get().getKey();
	}

}
