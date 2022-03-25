package fp.farmaceutico;

public class TestFactoriaMedicamentos {

	//- Implemente una clase de nombre TestFactoriaMedicamentos en un paquete de nombre fp.farmaceutico.test y compruebe el correcto funcionamiento del método anterior.
	public static void main(String[] args) {
		Medicamento m = FactoriaMedicamentos.parseaMedicamento("efavirenz,Anatomico,Y212XXA,Actavis Mid Atlantic LLC,90.0,1848,04/12/2019");
		System.out.println(m + "\n");
		System.out.println("Nombre del medicamento: " + m.getNombreMedicamento());
		System.out.println("Tipo de medicamento: " + m.getTipoMedicamento());
		System.out.println("Código de la enfermedad: " + m.getCodigoEnfermedad());
		System.out.println("Farmacéutica: " + m.getFarmaceutica());
		System.out.println("Puntuación: " + m.getPuntuacion());
		System.out.println("Índice somático: " + m.getIndiceSomatico());
		System.out.println("Fecha de catálogo: " + m.getFechaCatalogo());
		System.out.println("¿Se puede tratar? " + m.getTratarEnfermedad("Y212XXA"));

	}

}
