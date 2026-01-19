# GameAssets

*GameAssets* bilden den Grundbaustein des Projektes. Ein *GameAsset* besteht aus:
* x-Position
* y-Position
* Bild
* Name

Das gesamte Spiel ist aus erweiterten *GameAssets* aufgebaut.
Jeden Frame werden alle Assets, die in der *visibleGameAssets*-Liste der [*Canvas*-Klasse](./src/main/java/frame/Canvas.java) gespeichert sind, gezeichnet.

Die *GameAsset*-Klasse kann von weiteren Klassen erweitert werden, um eine Differenzierung der Assets zu ermöglichen (z.B. Items, NPCs, ...).
So kann jedes erweiterte *GameAsset* eigene Variablen speichern oder eigene Funktionen besitzen.
