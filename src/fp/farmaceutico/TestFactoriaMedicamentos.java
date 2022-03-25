package fp.farmaceutico;

public class TestFactoriaMedicamentos {

	//- Implemente una clase de nombre TestFactoriaMedicamentos en un paquete de nombre fp.farmaceutico.test y compruebe el correcto funcionamiento del m�todo anterior.
	public static void main(String[] args) {
		Medicamento m = FactoriaMedicamentos.parseaMedicamento("efavirenz,Anatomico,Y212XXA,Actavis Mid Atlantic LLC,90.0,1848,04/12/2019");
		System.out.println(m + "\n");
		System.out.println("Nombre del medicamento: " + m.getNombreMedicamento());
		System.out.println("Tipo de medicamento: " + m.getTipoMedicamento());
		System.out.println("C�digo de la enfermedad: " + m.getCodigoEnfermedad());
		System.out.println("Farmac�utica: " + m.getFarmaceutica());
		System.out.println("Puntuaci�n: " + m.getPuntuacion());
		System.out.println("�ndice som�tico: " + m.getIndiceSomatico());
		System.out.println("Fecha de cat�logo: " + m.getFechaCatalogo());
		System.out.println("�Se puede tratar? " + m.getTratarEnfermedad("Y212XXA"));

	}

}
