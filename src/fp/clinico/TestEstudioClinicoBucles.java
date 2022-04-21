package fp.clinico;

public class TestEstudioClinicoBucles {

	public static void main(String[] args) {
		EstudioClinico a = new EstudioClinicoBucles();
		EstudioClinico b = new EstudioClinicoBucles(a.leeFichero("data/estudio_clinico.csv"));
		System.out.print(b);
	}

}
