# Day 11 – Path Counter

## 📌 Problema
Existen nodos con conexiones a otros nodos (grafo).

El objetivo es calcular el número de caminos posibles desde un nodo concreto a otro según ciertas condiciones.

---

## 🧠 Soluciones
El problema se modela sobre una grafo `Graph`, representado como lista de adyacencia (mapa de pares: nodo - lista de conexiones).

Se aplican dos estrategias:

- **Parte 1:** cuenta de caminos entre dos nodos
- **Parte 2:** cuenta de caminos entre dos nodos que pasan por otros dos nodos concretos

---

## 🏗  Arquitectura

- `Graph` → modelo de dato
- `Search` -> contiene algoritmos de búsqueda (dos versiones de DFS)
- `Day11Parser` → transforma el input en un `Graph`
- `Day11` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- La resolución del problema se traslada a la clase `Search` porque el estado del problema ser es demasiado complejo de
  mantener en el solver durante toda la ejecución.
- Se usa un métodos de búsqueda principal, con dos implementaciones concretas:
    - DFS

---

## 🔷 Parte 1

Objetivo: calcular el número de caminos posibles desde el nodo 'you' hasta 'out'.

Se parte de:
- `Graph`

El DFS consistirá en probar todas las combinaciones posibles del dominio de forma recursiva, contabilizando cada camino
cuando que llegue al nodo 'out', para regresar a la llamada anterior y probar con otro.

Se retorna la cuenta de caminos desde 'you' hasta 'out'.

---

## 🔷 Parte 2

Objetivo: calcular el número de caminos posibles desde el nodo 'svr' hasta 'out' que pasen por
los nodos 'dac' y 'fft'.

Se parte de:
- `Graph`

El DFS consistirá en probar todas las combinaciones posibles del dominio de forma recursiva, contabilizando cada camino
cuando que llegue al nodo 'out' si antes han pasado por 'dac' y 'fft' (cuando llegue a alguno de los dos, se indica con un flag
booleano), para regresar a la llamada anterior y probar con otro.

Se retorna la cuenta de caminos desde 'svr' hasta 'out' que pasan por 'dac' y 'fft'.

---