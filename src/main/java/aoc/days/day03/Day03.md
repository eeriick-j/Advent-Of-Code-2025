# Day 03 – Max Subsequence Detector

## 📌 Problema
Existen un conjunto de bancos con voltajes, y se quiere detectar las máximas
subsecuencias de voltajes de longitud 'k'. 

El objetivo es obtener la suma de la mejor subsecuencia de todos los bancos.

---

## 🧠 Soluciones
El problema se modela con una lista de bancos con voltajes `Bank`.

Se aplican dos estrategias:

- **Parte 1:** detección de la mejor subsecuencia de 2 voltajes
- **Parte 2:** detección de la mejor subsecuencia de 12 voltajes

---

## 🏗  Arquitectura

- `Bank` → modelo de dato (lista de voltajes de 1-9)
- `Day03Parser` → transforma el input en una `List<Bank>`
- `Day03` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el propio solver `Day03` para evitar sobreingeniería
- Se usa un método de detección de la mejor subsecuencia de voltajes de banco, reutilizable 
para ambos problemas (`maxSubsequence`)

---

## 🔷 Parte 1

Objetivo: obtener la suma de los mejores pares de voltajes de todos los bancos

Se parte de: `List<Bank>`

Se iteró por la `List<Bank>`, y para cada `Bank`, se define una pila vacía.

Para cada voltaje de cada `Bank`, se borra de la pila todos los voltajes menores que el actual, 
que se añade al final de la limpieza.

---

## 🔷 Parte 2

Objetivo: obtener la suma de las mejores subsecuencia de 12 voltajes de todos los bancos

Se parte de: `List<Bank>`

Se iteró por la `List<Bank>`, y para cada `Bank`, se define una pila vacía.

Para cada voltaje de cada `Bank`, se borra de la pila todos los voltajes menores que el actual,
que se añade al final de la limpieza.

---