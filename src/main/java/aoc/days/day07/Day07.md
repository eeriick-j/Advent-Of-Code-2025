# Day 07 – Ray Splitting Simulator

## 📌 Problema
Existe un grid de obstáculos y una posición de salida de un rayo.

El objetivo es representar la propagación de estados a través de una estructura discreta con ramificaciones
y calcular las trayectorias resultantes.

---

## 🧠 Soluciones
El problema se modela con una lista de caracteres `char[][]` (obstáculos + posición de salida del rayo)

Se aplican dos estrategias:

- **Parte 1:** conteo de veces que los rayos se dividen
- **Parte 2:** conteo de todos los caminos posibles que un rayo inicial puede seguir 

---

## 🏗  Arquitectura

- `Day07Parser` → transforma el input en un `char[][]`
- `Day07` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el propio solver `Day07` para evitar sobreingeniería
- Se usan dos métodos de conteo, basados en:
    - propagación entera desde la fila entera anterior
    - propagación parcial desde la fila anterior 

---

## 🔷 Parte 1

Objetivo: obtener la cuenta del número de veces que los rayos se dividen.

Se parte de:
- `char[][]` con obstáculos y posición inicial del rayo

Se definió un set de posiciones con rayos, y se iteró por filas la `char[][]`. 

Para cada rayo del set de posiciones actuales, si hay un obstáculo en la fila, se elimina
ese rayo del set y se añaden dos nuevos rayos (las dos posiciones anterior y posterior del rayo eliminado). 
Tras el split, se incrementa el contador de splits, que será retornado al terminar.

---

## 🔷 Parte 2

Objetivo: obtener el número de caminos posibles que un rayo inicial puede tomar.

Se parte de:
- `char[][]` de dígitos (todas las filas menos la última) y operadores (última fila)

Se define un array de opciones de llegar a la posición 'i', 
y se inicializa para la fila 0 con 1 por ser la posición de salida.

Se iteró por la `char[][]` por filas.

Para cada fila, se calculan todas las opciones de llegar a cada índice de la siguiente fila, considerando los obstáculos
de la fila actual, incrementando el valor en ese índice si sucede. 
Tras terminar con la fila actual, se asigna la lista de formas de llegar a la siguiente fila a lista actual,
y se continúa con la siguiente.

En Programación Dinámica es habitual definir matrices, pero en este caso solo existe dependencia entre las 
listas de formas de llegar a los índices de la fila actual y la de las del siguiente, con lo que con asignamiento evitamos
estructuras gigantes.

---