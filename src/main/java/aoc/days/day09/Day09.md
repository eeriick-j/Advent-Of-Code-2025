# Day 09 – Max Rectangle Area Calculator

## 📌 Problema
Existe un conjunto de puntos en un plano.

El objetivo es calcular el área máxima del rectángulo que forma cualquier par de puntos.

---

## 🧠 Soluciones
El problema se modela sobre una lista de puntos `List<Point>`.

Se aplican dos estrategias:

- **Parte 1:** cálculo del área máxima para cualquier par de puntos
- **Parte 2:** cálculo del área máxima para cualquier par de puntos contenidos en el polígono que forman 
todos los puntos

---

## 🏗  Arquitectura

- `Point` → modelo de dato (coordenadas X,Y)
- `Segment` → modelo de dato (coordenadas de dos puntos)
- `Polygon` -> modelo de dato (lista de segmentos) 
- `Day08Parser` → transforma el input en un `List<Point>`
- `Day08` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el solver `Day09` para evitar sobreingeniería
- Se usan dos métodos de selección de pares de puntos:
    - cualquiera par válido
    - sólo válido si su rectángulo asociado está contenido en el polígono formado por todos los puntos

---

## 🔷 Parte 1

Objetivo: obtener el área máxima del rectángulo que forma cualquier par de puntos 

Se parte de:
- `List<Point>` con coordenadas (X,Y)

Se define un doble bucle para obtener todas las parejas de puntos posible. 
Para cada par, se calcula el área de su rectángulo asociado y se compara con el máximo obtenido 
hasta ese momento.

Al terminar el bucle, se retorna el área máxima.

---

## 🔷 Parte 2

Objetivo: obtener el área máxima del rectángulo que forma cualquier par de puntos cuyo rectángulo
esté contenido en el polígono que forman todos los puntos.

Se parte de:
- `List<Point>` con coordenadas (X,Y)

Se define un `Polygon` al que se le inyecta la `List<Segment>` que lo compone, sólo verticales y horizontales
porque el ejercicio es sobre geometría Mannhatan.

Se define un doble bucle para obtener todas las parejas de puntos posible.
Para cada par, se calcula el área de su rectángulo asociado si está incluido en el `Polygon`, 
y se compara con el máximo obtenido hasta ese momento.

Al terminar el bucle, se retorna el área máxima.

---