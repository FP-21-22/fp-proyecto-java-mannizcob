package fp.clinico;

public class TestEstudioClinicoStream {

	public static void main(String[] args) {
		EstudioClinico a = new EstudioClinicoStream();
		EstudioClinico b = new EstudioClinicoStream(a.leeFichero("data/estudio_clinico.csv"));
		System.out.print(b);
	}

}
