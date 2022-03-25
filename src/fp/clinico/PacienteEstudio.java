package fp.clinico;

import fp.utiles.Checkers;

public record PacienteEstudio(String id, String genero, Double edad, Boolean hipertension, Boolean enfermedadCorazon, TipoResidencia tipoResidencia, Double nivelMedioGlucosa) implements Comparable<PacienteEstudio> {
	
	//Constructor (lo redefinimos para añadir las restricciones)
	public PacienteEstudio {
		//- La edad tiene que ser mayor o igual que cero y menor o igual que 130.
		Checkers.check("La edad tiene que ser mayor o igual que cero y menor o igual que 130", edad >= 0 && edad <= 130);
		//- El nivel medio de glucosa tiene que ser mayor o igual que cero.
		Checkers.check("El nivel medio de glucosa tiene que ser mayor o igual que cero", nivelMedioGlucosa >= 0);
	}
	
	//Métodos de factoría
	//- Método static of: recibe valores para cada propiedad básica y devuelve un objeto del tipo.
	public static PacienteEstudio of(String id, String genero, Double edad, Boolean hipertension, Boolean enfermedadCorazon, TipoResidencia tipoResidencia, Double nivelMedioGlucosa) {
		PacienteEstudio res = new PacienteEstudio(id, genero, edad, hipertension, enfermedadCorazon, tipoResidencia, nivelMedioGlucosa);
		return res;
	}
	//- Método static parse: recibe una cadena con un formato especificado y y devuelve un objeto del tipo. Ejemplo de cadena: “6306;Male;80;false;false;URBANA;83.84”
	public static PacienteEstudio parse(String cadena) {
		//Restricción por si la cadena dada está vacía.
		Checkers.checkNoNull("Cadena vacía", cadena);
		String[] datos = cadena.split(";");
		//Restricción para evitar excesivos parámetros o insuficientes parámetros
		Checkers.check("Error en los parámetros dados, solo se aceptan 7 parametros", datos.length == 7);
		String id = datos[0];
		String genero = datos[1];
		Double edad = Double.parseDouble(datos[2]);
		Boolean hipertension = Boolean.parseBoolean(datos[3]);
		Boolean enfermedadCorazon = Boolean.parseBoolean(datos[4]);
		TipoResidencia tipoResidencia = isTipoResidencia(datos[5]);
		Double nivelMedioGlucosa = Double.parseDouble(datos[6]);
		PacienteEstudio res = PacienteEstudio.of(id, genero, edad, hipertension, enfermedadCorazon, tipoResidencia, nivelMedioGlucosa);
		return res;
	}
	
	//Métodos de las propiedades
	public boolean factorRiesgo() {
		Boolean res = false;
		if(this.edad > 40 && this.hipertension == true) {
			res = true;
		}
		return res;
	}
	
	private static TipoResidencia isTipoResidencia(String cadena) {
		TipoResidencia res;
		cadena = cadena.toUpperCase().toString();
		if(cadena.contentEquals("RURAL")) {
			res = TipoResidencia.RURAL;
		}
		else if(cadena.contentEquals("URBANA")){
			res = TipoResidencia.URBANA;
		}
		else {
			res = null;
		}
		return res;
	}

	//· Representación como cadena: informa del id y la edad del paciente.
	@Override
	public String toString() {
		return "PacienteEstudio [id=" + id + ", edad=" + edad + "]";
	}

	//· Criterio de igualdad: por defecto asociado al record.

	//· Criterio de orden: según la edad y el id.
	public int compareTo(PacienteEstudio p) {
		int res = this.edad().compareTo(p.edad());
		if(res==0) {
			res = this.id().compareTo(p.id());
		}
		return res;
	}
	
	//- Incluya un método main para comprobar el correcto funcionamiento del método parse. public static void main(String[] args){ … }
	public static void main(String[] args) {
		PacienteEstudio p = PacienteEstudio.parse("6306;Male;80;false;false;URBANA;83.84");
		System.out.println(p + "\n");
		System.out.println("ID: " + p.id());
		System.out.println("Género: " + p.genero());
		System.out.println("Edad: " + p.edad());
		System.out.println("¿Tiene hipertensión? " + p.hipertension());
		System.out.println("¿Tiene enfermedad del corazón? " + p.enfermedadCorazon());
		System.out.println("Tipo de residencia: " + p.tipoResidencia());
		System.out.println("Nivel medio de glucosa: " + p.nivelMedioGlucosa());
		System.out.println("¿Tiene factor de riesgo? " + p.factorRiesgo());
	}
}
