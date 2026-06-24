# Day 02 – Invalid ID Detector

## 📌 Problema
Existen un conjunto de rangos de IDs, y se quiere detectar los IDs incluidos en cada rango
que no son válidos.

El objetivo es obtener la suma de todos los IDs inválidos.

---

## 🧠 Soluciones
El problema se modela con una lista de rangos de IDs `IDRange`. 

Se aplican dos estrategias:

- **Parte 1:** validar según si el ID tiene las dos mitades de dígitos iguales  
- **Parte 2:** validar según si el ID tiene dos o más secuencias de dígitos iguales

---

## 🏗  Arquitectura

- `IDRange` → modelo de dato (límite inferior, límite superior)
- `Day02Parser` → transforma el input en una `List<IDRange>`
- `Day02` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el propio solver `Day02` para evitar sobreingeniería
- Se usan dos métodos de validación de ID:
    - mitades de digitos iguales (`equalHalves`)
    - dos o más secuenias iguales (`hasTwoOrMoreEqualSequence`)

---

## 🔷 Parte 1

Objetivo: obtener la suma de los IDs inválidos (aquellos cuya dos mitades sean iguales)

Se parte de: `List<IDRange>`

Se iteró por la `List<IDRange>`, y para cada `IDRange`, se iteró en el rango de IDs definido por su límite inferior
y superior (incluidos). 
Para cada ID, se verifica si las dos mitades son iguales y, si es el caso,
se incrementa un contador, que será el retorno tras acabar el bucle.

---

## 🔷 Parte 2

Objetivo: obtener la suma de los IDs inválidos (aquellos que tengan dos o más subsecuencias iguales)

Se parte de: `List<IDRange>`

Se aprovechó una propiedad de las cadenas.
Si hay alguna subsecuencia repetida, aparecerá desplazado en la string duplicada del ID original
antes del final.
Sucede porque duplicar una cadena crea todas las posibles rotaciones.

También podría haberse hecho la verificación sobre la propia string, pero requerería de precaución con
el desbordamiento de índices al fin y transformarlo al de inicio, y se obtendría mayor complejidad computacional
por la necesidad de un doble bucle.

---