# Proyecto del Segundo Cuatrimestre Fundamentos de Programación (Curso  21/22)
Autor/a: \<Manuel Jesús Niza Cobo\>   uvus:\<mannizcob\>

El proyecto que voy a desarrollar tiene como objetivo implementar diferentes tipos de clases requeridos.
En las siguientes entregas se trabajara con alguno de los datasets proporcionados.

## Estructura de las carpetas del proyecto

* **/src**: Contiene los diferentes archivos de Java que forman parte del proyecto.
  * **fp.clinico**: Paquete que contiene los records: Persona, Paciente, PacienteEstudio y el enumerado TipoResidencia.
  * **fp.farmaceutico**: Paquete que contiene las clases: Medicamento, FactoriaMedicamentos y el enumerado TipoMedicamento.
  * **fp.farmaceutico.test**: Paquete que contiene la clase de test: TestFactoriaMedicamentos.
  * **fp.vacunas**: Paquete que contiene el record Vacunacion.
  * **fp.utiles**:  Paquete que contiene las clases de utilidad. 
* **/data**: Contiene el dataset o datasets del proyecto (No para este entregable).
   * **\<dataset1.csv\>**: Añade una descripción genérica del dataset.
   * **\<dataset2.csv\>**: Añade una descripción del resto de datasets que puedas tener.
* **/doc**: Contiene la documentación del proyecto.
    
## Estructura del *dataset*

Aquí debes describir la estructura del dataset explicando qué representan los datos que contiene y la descripción de cada una de las columnas. Incluye también la URL del dataset original.

El dataset está compuesto por \<N\> columnas, con la siguiente descripción:

* **\<columna 1>**: de tipo \<tipo\>, representa....
* **\<columna 2>**: de tipo \<tipo\>, representa....
....

## Tipos implementados

En este apartado resaltamos los diferentes tipos que implementamos en el proyecto:

### Tipo Persona
El tipo Persona es un record que se implementa dentro del paquete `fp.clinico` con sus respectivos atributos y métodos.

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
- R2: El dni debe ser una cadena con ocho dígitos y seguidos de una letra.

**Representación como cadena**: por defecto asociado al record.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenación**: por dni.

**Otras operaciones**:
 
-	_Método static of_: Recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
-	_Método static parse_: Recibe una cadena con un formato específico y devuelve una persona.
-	_Método main_: Comprobar el correcto funcionamiento del método parse.

### Tipo Paciente
El tipo Paciente es un record que se implementa dentro del paquete `fp.clinico` con sus respectivos atributos y métodos.

**Propiedades**:

- _persona_, de tipo \<Persona\>, consultable. 
- _código de ingreso_, de tipo \<String\>, consultable. 
- _fecha y hora de ingreso_, de tipo \<LocalDateTime\>, consultable. 
- _fecha de ingreso_, de tipo \<LocalDate\>, consultable (Derivada a partir de la fecha y hora de ingreso).
- _hora de ingreso_, de tipo \<String\>, consultable (Derivada a partir de la fecha y hora de ingreso).

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `Paciente(Persona persona, String codigoImpreso, LocalDateTime fechaHoraIngreso)`

**Restricciones**:
 
- R1: La fecha y la hora de ingreso debe ser anterior o igual a la fecha actual.

**Representación como cadena**: por defecto asociado al record.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenación**: por defecto asociado al record.

**Otras operaciones**:
 
-	_Método static of_: Recibe nombre, apellidos, dni, fecha de nacimiento, código y fecha y hora de ingreso y devuelve un paciente.
-	_Método static of_: Recibe un objeto persona, un código y una fecha y hora de ingreso y devuelve un paciente.

### Tipo PacienteEstudio
El tipo PacienteEstudio es un record que se implementa dentro del paquete `fp.clinico` con sus respectivos atributos y métodos.

**Propiedades**:

- _id_, de tipo \<String\>, consultable. 
- _genero_, de tipo \<String\>, consultable. 
- _edad_, de tipo \<Double\>, consultable. 
- _hipertension_, de tipo \<Boolean\>, consultable. 
- _enfermedad del corazón_, de tipo \<Boolean\>, consultable.
- _tipo de residencia_, de tipo \<TipoResidencia\>, consultable, cuyos valores son rural o urbana.
- _nivel medio de glucosa_, de tipo \<Double\>, consultable.
- _factor de riesgo_, de tipo \<Boolean\>, consultable (Derivada, si tiene hipertensión y más de 40 años se considerará que tiene factor de 	riesgo.

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `PacienteEstudio(String id, String genero, Double edad, Boolean hipertension, Boolean enfermedadCorazon, TipoResidencia tipoResidencia, Double nivelMedioGlucosa)`

**Restricciones**:
 
- R1: La edad tiene que ser mayor o igual que cero y menor o igual que 130.
- R2: El nivel medio de glucosa tiene que ser mayor o igual que cero.

**Representación como cadena**: informa del id y la edad del paciente.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenación**: según la edad y el id.

**Otras operaciones**:
 
-	_Método static of_: Recibe valores para cada propiedad básica y devuelve un objeto del tipo.
-	_Método static parse_: Recibe una cadena con un formato especificado y y devuelve un objeto del tipo.
-	_Método main_: Comprobar el correcto funcionamiento del método parse.

### Tipo Vacunacion
El tipo Vacunacion es un record que se implementa dentro del paquete `fp.vacunas` con sus respectivos atributos y métodos.

**Propiedades**:

- _fecha_, de tipo \<LocalDate\>, consultable. 
- _comunidad_, de tipo \<String\>, consultable. 
- _pfizer_, de tipo \<Integer\>, consultable. 
- _moderna_, de tipo \<Integer\>, consultable. 
- _astrazeneca_, de tipo \<Integer\>, consultable.
- _janssen_, de tipo \<Integer\>, consultable.
- _número de personas_, de tipo \<Integer\>, consultable.
- _número total_, de tipo \<Integer\>, consultable (Derivada, siendo la suma de dosis de Pfizer, moderna, astrazeneca y janssen.

**Constructores**: 

Dado que es un record solamente se define en su estructura los atributos:
- Record: `Vacunacion(LocalDate fecha, String comunidad, Integer pfizer, Integer moderna, Integer astrazeneca, Integer janssen, Integer numeroPersonas)`

**Restricciones**:
 
- R1: La fecha debe ser posterior al 01/02/2021.

**Representación como cadena**: por defecto asociado al record.

**Criterio de igualdad**: por defecto asociado al record.

**Criterio de ordenación**: por comunidad y en caso de igualdad por fecha.

**Otras operaciones**:
 
-	_Método static of_: Recibe valores para cada propiedad básica y devuelve un objeto del tipo.
-	_Método static parse_: Recibe una cadena con un formato especificado y y devuelve un objeto del tipo.
-	_Método main_: Comprobar el correcto funcionamiento del método parse.

### Tipo Medicamento
El tipo Medicamento es una clase que se implementa dentro del paquete `fp.farmaceutico` con sus respectivos atributos y métodos.

**Propiedades**:

- _nombre del medicamento_, de tipo \<String\>, consultable. 
- _tipo de medicamento_, de tipo \<TipoMedicamento\>, consultable, los valores del enumerado son anatómico, químico y terapéutico. 
- _código de la enfermedad_, de tipo \<String\>, consultable. 
- _farmacéutica_, de tipo \<String\>, consultable. 
- _puntuación_, de tipo \<Double\>, consultable.
- _índice somático_, de tipo \<Integer\>, consultable.
- _fecha de catálogo_, de tipo \<LocalDate\>, consultable y modificable.
- _tratar enfermedad_, de tipo \<Boolean\>, consultable (Derivada, siendo cierta si el código de la enfermedad coincide con un parámetro del 	tipo cadena que recibe como argumento la propiedad.

**Constructores**: 

Dado que es una clase se debe construir un constructor para el tipo:
- C1: `Medicamento(String nombreMedicamento, TipoMedicamento tipoMedicamento, String codigoEnfermedad, String farmaceutica, Double puntuacion, Integer indiceSomatico, LocalDate fechaCatalogo)`

**Restricciones**:
 
- R1: La puntuación tiene que ser mayor estricta que cero.
- R2: El índice somático tiene que ser mayor o igual que 1000.
- R3: La fecha de catálogo tiene que ser posterior al 01/01/2015.

**Representación como cadena**: según el nombre del medicamento y de la farmacéutica.

**Criterio de igualdad**: por nombre del medicamento y farmacéutica.

**Criterio de ordenación**: por nombre del medicamento y en caso de igualdad por la farmacéutica.

**Otras operaciones**:
 
-	_Método static parse_: Recibe una cadena con un formato especificado y y devuelve un objeto del tipo. Implementar en una clase 	FactoriaMedicamentos.
-	_Método main_: Comprobar el correcto funcionamiento del método parse. Implementar en un paquete `fp.farmaceutico.test`.
