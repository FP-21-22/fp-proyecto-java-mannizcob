package fp.vacunas;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vacunaciones {
	
	private List<Vacunacion> ls;
	
	public Vacunaciones(Stream<Vacunacion> s) {
		this.ls = s.toList();
	}
	
	public void anyadeVacunacion(Vacunacion v) {
		this.ls.add(v);
	}
	
	public List<Vacunacion> vacunacionesEntreFechas(LocalDate fecha1, LocalDate fecha2) {
		return this.ls.stream()
				.filter(x -> x.fecha().isAfter(fecha1) && x.fecha().isBefore(fecha2))
				.collect(Collectors.toList());
	}
	
	public Boolean existeNumPersonasPautaCompletaPorEncimaDe(String comunidad, Integer n) {
		return this.ls.stream()
				.filter(x -> x.comunidad().equals(comunidad))
				.anyMatch(x -> x.numeroTotal() > n);
	}
	
	public Integer diaMasVacunacionesEn(String comunidad) {
		return this.ls.stream()
				.filter(x -> x.comunidad().equals(comunidad))
				.max(Comparator.comparing(Vacunacion::numeroTotal))
				.get()
				.fecha()
				.getDayOfMonth();
	}
	
	public Map<LocalDate, List<Vacunacion>> vacunacionesPorFecha() {
		return this.ls.stream()
				.collect(Collectors.groupingBy(Vacunacion::fecha));
	}
	
	public Map<String, Integer> maximoNumTotalVacunasporComunidad() {
		return this.ls.stream()
				.collect(Collectors.groupingBy(Vacunacion::comunidad, 
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(Vacunacion::numeroTotal)), 
								ls -> ls.get().numeroTotal())));
	}
}
