# Day 01 – Dial Rotation Simulation

## 📌 Problema
Se simula la rotación de un dial con una secuencia de rotaciones, cada una con una dirección (L/R) y un número de pasos.

El objetivo es calcular cuántas veces el dial pasa por la posición 0 bajo dos estrategias distintas de rotación.

---

## 🧠 Soluciones
El problema se modela como un estado mutable (`Dial`) que posee un puntero sobre un rango circular de 0 a 99.

Se aplican dos estrategias:

- **Parte 1:** aplicar cada rotación completa
- **Parte 2:** aplicar cada rotación paso a paso

---

## 🏗  Arquitectura

- `Day01` → solver
- `Day01Parser` → transforma el input en una lista de `Rotation`
- `Rotation` → modelo de dato (dirección + número de pasos)
- `Dial` → estado mutable con lógica de manipulación de su puntero

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en una clase específica `Dial`
- Se usan dos métodos de simulación para comparar granularidad:
    - salto completo (`rotateFull`)
    - simulación paso a paso (`rotateClickByClick`)

---

## 🔷 Parte 1

Objetivo: obtener el nº de veces que el dial apunta a 0, tras cada rotación completa.

Se parte de:
- `List<Rotation>`
- `Dial` con puntero inicializado a 50.

Se iteró por la `List<Rotation>` y se aplicó una operación de suma o resta (L/R) módulo 100 
para simular una rotación circular. 

Tras cada rotación completa, se verificó si el dial
apunta a 0, para incrementar un contador, que será el retorno tras acabar el bucle.

---

## 🔷 Parte 2

Objetivo: obtener el nº de veces que el dial apunta a 0, tras cada paso de cada rotación.
 
Se parte de:
- `List<Rotation>`
- `Dial` con puntero inicializado a 50.

Se iteró por la `List<Rotation>`, y para cada `Rotation` se iteró en el número de pasos para
comprobar si, tras un paso (L/R), el dial apunta a 0, para incrementar el contador que será retornado
al acabar el bucle principal.

---