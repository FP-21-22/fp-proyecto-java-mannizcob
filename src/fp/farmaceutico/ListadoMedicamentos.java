package fp.farmaceutico;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoMedicamentos {
	
	private List<Medicamento> ls;
	
	public ListadoMedicamentos(Stream<Medicamento> s) {
		this.ls = s.toList();
	}
	
	public Boolean existeMedicamentoSegunTipoAnteriorA(TipoMedicamento tipo, LocalDate fecha) {
		return this.ls.stream()
				.filter(x -> x.getTipoMedicamento().equals(tipo))
				.anyMatch(x -> x.getFechaCatalogo().isAfter(fecha));
	}
	
	public Set<String> nombreMedicamentosPuntuacionMayorA(Double puntuacion) {
		return this.ls.stream()
				.filter(x -> x.getPuntuacion() > puntuacion)
				.collect(Collectors.mapping(Medicamento::getNombreMedicamento, Collectors.toSet()));
	}
	
	public String nombreMedicamentoMayorIndiceSomaticoSegunTipoMedicamento(TipoMedicamento tipo) {
		return this.ls.stream()
				.filter(x -> x.getTipoMedicamento().equals(tipo))
				.max(Comparator.comparing(Medicamento::getIndiceSomatico))
				.map(Medicamento::getNombreMedicamento)
				.get();
	}
	
	public Map<TipoMedicamento, Double> agrupaTipoMedicamentoSegunPuntuacionMedia() {
		return this.ls.stream()
				.collect(Collectors.groupingBy(Medicamento::getTipoMedicamento, 
						Collectors.averagingDouble(Medicamento::getPuntuacion)));
	}
	
	public LocalDate fechaCatalogoMasFrecuente() {
		Map<LocalDate, Long> map = this.ls.stream()
				.collect(Collectors.groupingBy(Medicamento::getFechaCatalogo,
						Collectors.counting()));
		return map.entrySet().stream()
				.max(Entry.comparingByValue())
				.get()
				.getKey();
	}
}
