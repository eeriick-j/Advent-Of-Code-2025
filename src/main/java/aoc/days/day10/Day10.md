# Day 10 – Machine State Explorer

## 📌 Problema
Existe un conjunto de máquinas.

El objetivo es calcular el coste mínimo (o acumulado óptimo) para transformar el 
estado inicial de una máquina de en un estado objetivo.

---

## 🧠 Soluciones
El problema se modela sobre una lista máquinas `List<Machine>`, con un estado de luces objetivo, 
y unos botones que los enciende y apaga.

Se aplican dos estrategias:

- **Parte 1:** camino mínimo (BFS + bitmasking)
- **Parte 2:** coste mínimo de voltajes (DFS + memoization)

---

## 🏗  Arquitectura

- `Machine` → modelo de dato (target + botones + voltajes)
- `Search` -> contiene algoritmos de búsqueda (BFS + DFS)
- `Day10Parser` → transforma el input en un `List<Machine>`
- `Day10` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- La resolución del problema se traslada a la clase `Search` porque el estado del problema ser es demasiado complejo de 
mantener en el solver durante toda la ejecución.
- Se usan dos métodos de búsqueda:
    - BFS
    - DFS

---

## 🔷 Parte 1

Objetivo: calcular el número mínimo de botones a pulsar (operaciones XOR) para llegar al objetivo.

Se parte de:
- `List<Machine>`, cada una con un target y botones, ambos con formato de máscara binaris binario

Para cada máquina:
1. Se define el estado inicial a 0 y una cola vacía, donde se añadirán los botones de cada nivel.
2. Se itera por los botones (mientras se usa una cola) haciendo XOR (por ser reversible) entre el estado actual y el botón actual (flip del estado, encendido <==> apagado).
3. Cuando el estado actual sea el target, se retorna el número de toggles necesarios para esa máquina.

Se retorna la suma de los toggles mínimos de cada máquina.

- Cada botón invierte el estado, por lo que recorrer todo el dominio garantiza encontrar la solución (si existe).
- Al explorar por niveles, se conoce el coste de acceso a la próxima capa, por lo que se garantiza la solución óptima.

---

## 🔷 Parte 2

Objetivo: calcular el número mínimo de a pulsar para transformar el vector de voltajes de cada máquina a 0.

Se parte de:
- `List<Machine>`, cada una con un target y botones, ambos con formato de máscara binaris binario

Para cada máquina:
1. Se parte del vector inicial de voltajes.
2. Se itera por todas las máscaras de botones posibles (0, buttons.length()) 
3. Para cada máscara, se descarta si el vector de voltajes actual es menor que 0 o impar (no permitiría dividir por 2), y si no
es descartado, se divide el problema actual en dos llamadas recursivas, para convertirlo en uno equivalente pero más pequeño.

Cada división del problema no acerca siempre a la solución, sólo permite continuar con una combinación actual válida (sigue
siendo un DFS en esencia).

Se retorna la suma de los toggles mínimos de cada máquina que hagan que todos sus vectores de voltaje sean 0.

---