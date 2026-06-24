# Day 06 – Matrix Calculator

## 📌 Problema
Existe un grid de dígitos y operadores.

El objetivo es dividir una estructura en bloques y evaluar expresiones 
dentro de cada bloque con distintas estrategias de lectura.

---

## 🧠 Soluciones
El problema se modela con una lista de caracteres `char[][]` (dígitos y operadores)

Se aplican dos estrategias:

- **Parte 1:** operaciones de columna completa
- **Parte 2:** operaciones de subcolumnas para cada columna

---

## 🏗  Arquitectura

- `Day06Parser` → transforma el input en un `char[][]`
- `Day06` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el propio solver `Day06` para evitar sobreingeniería
- Se usan dos métodos de operación:
    - selección en bloque de los dígitos de cada columna
    - selección individual de los dígitos de cada columnas

---

## 🔷 Parte 1

Objetivo: obtener la suma de las operaciones de cada columna con selección de dígitos 
en bloque (todos los dígitos de la columna).

Se parte de:
- `char[][]` de dígitos (todas las filas menos la última) y operadores (última fila)

Se iteró por la `char[][]`, y para columna, se itera por filas. 
Para cada fila, se calculan los índices de inicio y fin de cada número, para aplicarles los operadores 
definidos en la última fila.

El resultado de cada columna se suma a un contador, que es retornado al terminar.

---

## 🔷 Parte 2

Objetivo: obtener la suma de las operaciones de cada columna con selección de dígitos
individual bloque (solo aplicar el operador a los dígitos de la subcolumna).

Se parte de:
- `char[][]` de dígitos (todas las filas menos la última) y operadores (última fila)

Se iteró por la `char[][]`, y para columna, se itera por filas.
Para cada fila, se calculan los índices de inicio y el de fin (el máximo de todas las filas). 
Para cada subcolumna, se concatenan sus dígitos y se le aplica la operación correspondiente.

Tras terminar con la columna actual, la operación se suma a un contador que es retornado al terminar.

---