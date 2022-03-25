package fp.farmaceutico;

import java.time.LocalDate;
import java.util.Objects;
import fp.utiles.Checkers;

public class Medicamento implements Comparable<Medicamento>{
	private String nombreMedicamento;
	private TipoMedicamento tipoMedicamento;
	private String codigoEnfermedad;
	private String farmaceutica;
	private Double puntuacion;
	private Integer indiceSomatico;
	private LocalDate fechaCatalogo;
	
	//Constructores
	public Medicamento(String nombreMedicamento, TipoMedicamento tipoMedicamento, String codigoEnfermedad, String farmaceutica, Double puntuacion, Integer indiceSomatico, LocalDate fechaCatalogo) {
		//- La puntación tiene que ser mayor estricta que cero.
		Checkers.check("La puntuación debe ser mayor que cero", puntuacion > 0);
		//- El índice somático tiene que ser mayor o igual que 1000.
		Checkers.check("El índice somático tiene que ser mayor o igual que 1000", indiceSomatico >= 1000);
		//- La fecha de catálogo tiene que ser posterior al 01/01/2015.
		Checkers.check("La fecha del catálogo tiene que ser posterior al 01/01/2015", fechaCatalogo.isAfter(LocalDate.of(2015, 01, 01)));
		this.nombreMedicamento = nombreMedicamento;
		this.tipoMedicamento = tipoMedicamento;
		this.codigoEnfermedad = codigoEnfermedad;
		this.farmaceutica = farmaceutica;
		this.puntuacion = puntuacion;
		this.indiceSomatico = indiceSomatico;
		this.fechaCatalogo = fechaCatalogo;
	}

	//Métodos de las propiedades
	public LocalDate getFechaCatalogo() {
		return fechaCatalogo;
	}
	public void setFechaCatalogo(LocalDate fechaCatalogo) {
		//- La fecha de catálogo tiene que ser posterior al 01/01/2015.
		Checkers.check("La fecha del catálogo tiene que ser posterior al 01/01/2015", fechaCatalogo.isAfter(LocalDate.of(2015, 01, 01)));
		this.fechaCatalogo = fechaCatalogo;
	}
	public String getNombreMedicamento() {
		return nombreMedicamento;
	}
	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}
	public String getCodigoEnfermedad() {
		return codigoEnfermedad;
	}
	public String getFarmaceutica() {
		return farmaceutica;
	}
	public Double getPuntuacion() {
		return puntuacion;
	}
	public Integer getIndiceSomatico() {
		return indiceSomatico;
	}
	
	//Métodos derivados
	public Boolean getTratarEnfermedad(String codigo) {
		Boolean res = false;
		if(this.codigoEnfermedad.contentEquals(codigo)) {
			res = true;
		}
		return res;
	}

	//· Representación como cadena: según el nombre del medicamento y de la farmacéutica.
	@Override
	public String toString() {
		return "Medicamento [nombreMedicamento=" + nombreMedicamento + ", farmaceutica=" + farmaceutica + "]";
	}

	//· Criterio de igualdad: por nombre del medicamento y farmacéutica.
	@Override
	public int hashCode() {
		return Objects.hash(farmaceutica, nombreMedicamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		return Objects.equals(farmaceutica, other.farmaceutica) && Objects.equals(nombreMedicamento, other.nombreMedicamento);
	}

	//· Orden natural: por nombre del medicamento y en caso de igualdad por la farmacéutica.
	@Override
	public int compareTo(Medicamento m) {
		Integer res = this.nombreMedicamento.compareTo(m.nombreMedicamento);
		if(res==0) {
			res = this.farmaceutica.compareTo(m.farmaceutica);
		}
		return res;
	}	
}
