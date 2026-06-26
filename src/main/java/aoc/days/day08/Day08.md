# Day 08 – Box Connector

## 📌 Problema
Existen un conjunto de cajas con coordenadas (X,Y,Z).

El objetivo es enlazarlas con la menor distancia entre ellas.

---

## 🧠 Soluciones
El problema se modela con una lista de cajas `List<Box>`y una estructura de parejas de cajas `Pair`.

Se aplican dos estrategias:

- **Parte 1:** conectar 1000 pares de cajas (Kruskal sobre subconjunto)
- **Parte 2:** conectar todos los pares de cajas (Kruskal completo)

---

## 🏗  Arquitectura

- `Box` → modelo de dato (id + coordenadas tridimensionales)
- `Pair` -> modelo de dato (par de cajas + distancia entre ambas)
- `Day08Parser` → transforma el input en un `List<Box>`
- `Day08` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el solver `Day08` con una clase con estado mutable `DSU` (Disjoint Set-Union)
- Se usa un único método de conexión:
    - algoritmo de Kruskal + estructura de verificación auxiliar DSU 

---

## 🔷 Parte 1

Objetivo: obtener el producto del tamaño de los 3 grupos más grandes formados de conectar
1000 cajas más cercanas entre sí

Se parte de:
- `List<Box>` con coordenadas (X,Y,Z)

La idea es aplicar el algoritmo de Kruskal a un subconjunto de 1000 conexiones.
Para ello, se...
1) Se genera todos los pares de cajas en una `List<Pair>`
2) Se ordena por distancia (de menor a mayor)
3) Se define un `DSU` y una lista de ids de las cajas
4) Se itera 1000 veces en `List<Pair>` para conectar dos cajas no conectadas ya

Tras las 1000 iteraciones, habrán varios grupos. 
Se define una lista del tamaño del número de componentes, y se consulta al DSU a qué grupo pertenece 
cada nodo, para acumularlos en la lista.

Se retorna el producto de los 3 grupos de mayor tamaño. 

---

## 🔷 Parte 2

Objetivo: obtener el producto del componete X de entre los últimos dos nodos conectados
tras conectar todas las cajas hasta que quede 1 único componente.

Se parte de:
- `List<Box>` con coordenadas (X,Y,Z)

La idea es aplicar el algoritmo de Kruskal a todo el dominio de cajas.
Para ello, se...
1) Se genera todos los pares de cajas en una `List<Pair>`
2) Se ordena por distancia (de menor a mayor)
3) Se define un `DSU` y una lista de ids de las cajas
4) Se itera en la `List<Pair>` entera para conectar dos cajas no conectadas ya

Se finaliza al quedar un componente.


Se retorna el producto de las X de los últimos dos nodos conectados.

---