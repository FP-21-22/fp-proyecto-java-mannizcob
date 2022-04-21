# Proyecto del Segundo Cuatrimestre Fundamentos de Programaci�n (Curso  21/22)
Autor/a: \<Manuel Jes�s Niza Cobo\>   uvus:\<mannizcob\>

El proyecto que voy a desarrollar tiene como objetivo implementar diferentes tipos de clases requeridos.
En las siguientes entregas se trabajara con alguno de los datasets proporcionados.

## Estructura de las carpetas del proyecto

* **/src**: Contiene los diferentes archivos de Java que forman parte del proyecto.
  * **fp.clinico**: Paquete que contiene las clases e interfaces del tipo Paciente.
   * **\<EstudioClinico.java\>**: Interfaz con los m�todos del tipo EstudioClinico.
   * **\<EstudioClinicoBucles.java\>**: Clase que implementa los m�todos de la interfaz EstudioClinico con bucles.
   * **\<EstudioClinicoStream.java\>**: Clase que implementa los m�todos de la interfaz EstudioClinico con Stream.
   * **\<Paciente.java\>**: Record del tipo Paciente.
   * **\<PacienteEstudio.java\>**: Record del tipo PacienteEstudio.
   * **\<Persona.java\>**: Record del tipo Persona.
   * **\<TestEstudioClinicoBucles.java\>**: Clase con el m�todo Main para comprobar la clase EstudioClinicoBucles.
   * **\<TestEstudioClinicoStream.java\>**: Clase con el m�todo Main para comprobar la clase EstudioClinicoStream.
   * **\<TipoResidencia.java\>**: Enumerado del tipo TipoResidencia.
  * **fp.farmaceutico**: Paquete que contiene las clases e interfaces del tipo Medicamento.
   * **\<FactoriaMedicamentos.java\>**: Clase con los m�todos para leer archivos del fichero "medicamentos.csv".
   * **\<Medicamento.java\>**: Clase del tipo Medicamento.
   * **\<TestMedicamento.java\>**: Clase con el m�todo Main para comprobar la clase Medicamento.
   * **\<TipoMedicamento.java\>**: Enumerado del tipo TipoMedicamento.
  * **fp.farmaceutico.test**: Paquete que contiene la clase de test: TestFactoriaMedicamentos.
   * **\<TestFactoriaMedicamentos.java\>**: Clase con el m�todo Main para comprobar la factor�a de Medicamento.
  * **fp.vacunas**: Paquete que contiene las clases e interfaces del tipo Vacunacion.
   * **\<FactoriaVacunaciones.java\>**: Clase con los m�todos para leer archivos del fichero "cca_vacunas_3.csv".
   * **\<TestVacunacion.java\>**: Clase con el m�todo Main para comprobar la clase Vacunacion.
   * **\<Vacunacion.java\>**: Clase del tipo Vacunacion.
  * **fp.utiles**:  Paquete que contiene las clases de utilidad (Checkers y Ficheros). 
   * **\<Checkers.java\>**: Clase que contiene los m�todos para implementar restricciones.
   * **\<Ficheros.java\>**: Clase que contiene los m�todos para leer y escribir en ficheros.
* **/data**: Contiene el dataset o datasets del proyecto.
   * **\<cca_vacunas_3.csv\>**: Archivo csv que contiene los datos a parsear del tipo Vacunacion.
   * **\<estudio_clinico.csv\>**: Archivo csv que contiene los datos a parsear del tipo PacienteEstudio.
   * **\<medicamentos.csv\>**: Archivo csv que contiene los datos a parsear del tipo Medicamento.
* **/doc**: Contiene la documentaci�n del proyecto.
   * **\<Proyecto de laboratorio de Java_SUBGRUPO_IS2_3.pdf\>**: Archivo con los datos del 2� entregable.
    
## Estructura del *dataset*

Estos son los 3 datasets que se implementar�n en los tipos desarrollados en los paquetes del proyecto.

* **\<cca_vacunas_3.csv\>**: Archivo csv que contiene los datos a parsear del tipo Vacunacion.
	* El dataset est� compuesto por \<7\> columnas.
* **\<estudio_clinico.csv\>**: Archivo csv que contiene los datos a parsear del tipo PacienteEstudio.
	* El dataset est� compuesto por \<7\> columnas.
* **\<medicamentos.csv\>**: Archivo csv que contiene los datos a parsear del tipo Medicamento.
	* **El dataset est� compuesto por \<7\> columnas.

## Tipos implementados

En este apartado resaltamos los diferentes tipos que implementamos en el proyecto:

### Tipo Persona
El tipo Persona es un record que se implementa dentro del paquete `fp.clinico` con sus respectivos atributos y m�todos.

**Propiedades**:

- _nombre_, de tipo \<String\>, consultable. 
- _apellidos_, de tipo \<String\>, consultable. 
- _dni_, de tipo \<String\>, consultable. 
- _fecha de nacimiento_, de tipo \<LocalDate\>, consultable. 
- _edad_, de tipo \<Integer\>, consultable (Derivada a partir de la fecha de nacimiento).

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `Persona(String nombre, String apellidos, String dni, LocalDate fechaNacimiento)`

**Restricciones**:
 
- R1: La fecha de nacimiento debe ser anterior a la fecha actual.
- R2: El dni debe ser una cadena con ocho d�gitos y seguidos de una letra.

**Representaci�n como cadena**: por defecto asociado al record.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenaci�n**: por dni.

**Otras operaciones**:
 
-	_M�todo static of_: Recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
-	_M�todo static parse_: Recibe una cadena con un formato espec�fico y devuelve una persona.
-	_M�todo main_: Comprobar el correcto funcionamiento del m�todo parse.

### Tipo Paciente
El tipo Paciente es un record que se implementa dentro del paquete `fp.clinico` con sus respectivos atributos y m�todos.

**Propiedades**:

- _persona_, de tipo \<Persona\>, consultable. 
- _c�digo de ingreso_, de tipo \<String\>, consultable. 
- _fecha y hora de ingreso_, de tipo \<LocalDateTime\>, consultable. 
- _fecha de ingreso_, de tipo \<LocalDate\>, consultable (Derivada a partir de la fecha y hora de ingreso).
- _hora de ingreso_, de tipo \<String\>, consultable (Derivada a partir de la fecha y hora de ingreso).

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `Paciente(Persona persona, String codigoImpreso, LocalDateTime fechaHoraIngreso)`

**Restricciones**:
 
- R1: La fecha y la hora de ingreso debe ser anterior o igual a la fecha actual.

**Representaci�n como cadena**: por defecto asociado al record.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenaci�n**: por defecto asociado al record.

**Otras operaciones**:
 
-	_M�todo static of_: Recibe nombre, apellidos, dni, fecha de nacimiento, c�digo y fecha y hora de ingreso y devuelve un paciente.
-	_M�todo static of_: Recibe un objeto persona, un c�digo y una fecha y hora de ingreso y devuelve un paciente.

### Tipo PacienteEstudio
El tipo PacienteEstudio es un record que se implementa dentro del paquete `fp.clinico` con sus respectivos atributos y m�todos.

**Propiedades**:

- _id_, de tipo \<String\>, consultable. 
- _genero_, de tipo \<String\>, consultable. 
- _edad_, de tipo \<Double\>, consultable. 
- _hipertension_, de tipo \<Boolean\>, consultable. 
- _enfermedad del coraz�n_, de tipo \<Boolean\>, consultable.
- _tipo de residencia_, de tipo \<TipoResidencia\>, consultable, cuyos valores son rural o urbana.
- _nivel medio de glucosa_, de tipo \<Double\>, consultable.
- _factor de riesgo_, de tipo \<Boolean\>, consultable (Derivada, si tiene hipertensi�n y m�s de 40 a�os se considerar� que tiene factor de 	riesgo.

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `PacienteEstudio(String id, String genero, Double edad, Boolean hipertension, Boolean enfermedadCorazon, TipoResidencia tipoResidencia, Double nivelMedioGlucosa)`

**Restricciones**:
 
- R1: La edad tiene que ser mayor o igual que cero y menor o igual que 130.
- R2: El nivel medio de glucosa tiene que ser mayor o igual que cero.

**Representaci�n como cadena**: informa del id y la edad del paciente.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenaci�n**: seg�n la edad y el id.

**Otras operaciones**:
 
-	_M�todo static of_: Recibe valores para cada propiedad b�sica y devuelve un objeto del tipo.
-	_M�todo static parse_: Recibe una cadena con un formato especificado y y devuelve un objeto del tipo.
-	_M�todo main_: Comprobar el correcto funcionamiento del m�todo parse.

### Tipo Vacunacion
El tipo Vacunacion es un record que se implementa dentro del paquete `fp.vacunas` con sus respectivos atributos y m�todos.

**Propiedades**:

- _fecha_, de tipo \<LocalDate\>, consultable. 
- _comunidad_, de tipo \<String\>, consultable. 
- _pfizer_, de tipo \<Integer\>, consultable. 
- _moderna_, de tipo \<Integer\>, consultable. 
- _astrazeneca_, de tipo \<Integer\>, consultable.
- _janssen_, de tipo \<Integer\>, consultable.
- _n�mero de personas_, de tipo \<Integer\>, consultable.
- _n�mero total_, de tipo \<Integer\>, consultable (Derivada, siendo la suma de dosis de Pfizer, moderna, astrazeneca y janssen.

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `Vacunacion(LocalDate fecha, String comunidad, Integer pfizer, Integer moderna, Integer astrazeneca, Integer janssen, Integer numeroPersonas)`

**Restricciones**:
 
- R1: La fecha debe ser posterior al 01/02/2021.

**Representaci�n como cadena**: por defecto asociado al record.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenaci�n**: por comunidad y en caso de igualdad por fecha.

**Otras operaciones**:
 
-	_M�todo static of_: Recibe valores para cada propiedad b�sica y devuelve un objeto del tipo.
-	_M�todo static parse_: Recibe una cadena con un formato especificado y y devuelve un objeto del tipo.
-	_M�todo main_: Comprobar el correcto funcionamiento del m�todo parse.

### Tipo Medicamento
El tipo Medicamento es una clase que se implementa dentro del paquete `fp.farmaceutico` con sus respectivos atributos y m�todos.

**Propiedades**:

- _nombre del medicamento_, de tipo \<String\>, consultable. 
- _tipo de medicamento_, de tipo \<TipoMedicamento\>, consultable, los valores del enumerado son anat�mico, qu�mico y terap�utico. 
- _c�digo de la enfermedad_, de tipo \<String\>, consultable. 
- _farmac�utica_, de tipo \<String\>, consultable. 
- _puntuaci�n_, de tipo \<Double\>, consultable.
- _�ndice som�tico_, de tipo \<Integer\>, consultable.
- _fecha de cat�logo_, de tipo \<LocalDate\>, consultable y modificable.
- _tratar enfermedad_, de tipo \<Boolean\>, consultable (Derivada, siendo cierta si el c�digo de la enfermedad coincide con un par�metro del 	tipo cadena que recibe como argumento la propiedad.

**Constructores**: 

Dado que es una clase se debe construir un constructor para el tipo:
- C1: `Medicamento(String nombreMedicamento, TipoMedicamento tipoMedicamento, String codigoEnfermedad, String farmaceutica, Double puntuacion, Integer indiceSomatico, LocalDate fechaCatalogo)`

**Restricciones**:
 
- R1: La puntuaci�n tiene que ser mayor estricta que cero.
- R2: El �ndice som�tico tiene que ser mayor o igual que 1000.
- R3: La fecha de cat�logo tiene que ser posterior al 01/01/2015.

**Representaci�n como cadena**: seg�n el nombre del medicamento y de la farmac�utica.

**Criterio de igualdad**: por nombre del medicamento y farmac�utica.

**Criterio de ordenaci�n**: por nombre del medicamento y en caso de igualdad por la farmac�utica.

**Otras operaciones**:
 
-	_M�todo static parse_: Recibe una cadena con un formato especificado y y devuelve un objeto del tipo. Implementar en una clase 	FactoriaMedicamentos.
-	_M�todo main_: Comprobar el correcto funcionamiento del m�todo parse. Implementar en un paquete `fp.farmaceutico.test`.
