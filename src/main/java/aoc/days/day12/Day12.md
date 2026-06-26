# Day 12 – Piece Placement Validator

## 📌 Problema
Existen regiones donde se quieren encajar varias piezas.

El objetivo es validar que cada región pueda contener a todas las piezas que quieran colocarse 
dentro de ella sin solaparse.

---

## 🧠 Soluciones
El problema se modela con unas regiones `Region`, en la que se intentan contener un conjunto de `Piece`.

Se aplica una única estrategias:
- **Parte 1:** backtracking sobre todas las posibles colocaciones de las piezas
- **Parte 2:** nula

---

## 🏗  Arquitectura

- `Cell` → modelo de dato (coordenadas X,Y relativas a la celda)
- `Piece` → modelo de dato (lista de celdas)
- `Placement` → modelo de dato (pieza + fila y columna X,Y de la región (donde empieza a colocarse) + celdas)
- `Region` → modelo de dato (ancho + alto + requisitos de nº de `Piece`'s a colocar)
- `Puzzle` → modelo de dato (wrapper de lista de piezas + lista de regiones)
- `Search` → contiene el algoritmos de búsqueda (backtracking) + métodos auxiliares para completarlo
- `Day12Parser` → transforma el input en un `Puzzle`
- `Day12` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- La resolución del problema se divide entre el solver y la clase `Search` porque el estado del problema ser es demasiado complejo de
  mantener en el solver durante toda la ejecución.
- Se usa un método de búsqueda:
    - Backtracking

---

## 🔷 Parte 1

Objetivo: contar el número de regiones cuyos requerimientos de piezas a colocar sobre ella puedan puedan hacerlo sin
solaparse.

Se parte de:
- `List<Piece>`
- `List<Region>`

Este ejercicio es muy pesado, así que se hizo necesario realizar comprobaciones previas antes de aplicar el algoritmo
de búsqueda para cada región. 

Primero se filtran las regiones descartando las que no tienen suficiente área para contener todas las piezas.

Para cada región válida, y para cada `Piece`, se generan todas las colocaciones posibles de cada pieza 
y sus variantes (se generan todas sus variantes posibles (rotaciones, reflejos y combinaciones)). 
A cada una de esas variantes se le prueban todas las posiciones dentro de la región, 
recorriendo el tablero completo, y solo se guardan las colocaciones que no se 
salen de los límites.

Cada colocación válida se convierte en un Placement con la pieza, su posición en la región y las celdas que ocupa.

Finalmente, se agrupan todos los placements por pieza en un Map, se ordenan las piezas según cuántas colocaciones 
tienen, y se ejecuta el backtracking probando combinaciones hasta encontrar una que encaje sin colisiones.

Se retorna el número de regiones que permiten colocar todas las piezas definidas en su requerimiento.

---

## 🔷 Parte 2

No hay enunciado.

---