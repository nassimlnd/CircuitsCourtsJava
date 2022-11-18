# CSS de chaque élements de la maquette graphique

## Bouton :

```css
.button {
    -fx-cursor: hand;
    -fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #2970FF 0.2%, #0040C1 99.99%);
    -fx-border-radius: 4px;
    -fx-text-fill: white;
    -fx-font-size: 64px; // Change it to the real size (maybe 14px)
    -fx-padding: 12px 24px;
}

.button:hover {
    -fx-background-color: #154FEF;
}

.button:pressed {
    -fx-background-color: #5280FF;
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: #5280FF;
}
```