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

## Login Input :

```css
/* TextField -> Login Input */

.login-input {
    -fx-font: 14px 'Inter';
    -fx-background-color: white;
    -fx-border-radius: 4px;
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: #D0D5DD;
    -fx-padding: 8px 16px;
}

.login-input:hover {
    -fx-background-color: #F8FAFC;
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: #EAECF0;
}

.login-input:focused {
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #2970FF 0.2%, #0040C1 99.99%);
}
```

## Password Input :

```css
/* PasswordField -> Password Input */
.password-input {
    -fx-font: 14px 'Inter';
    -fx-background-color: white;
    -fx-border-radius: 4px;
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: #D0D5DD;
    -fx-padding: 8px 16px;
}

.password-input:hover {
    -fx-background-color: #F8FAFC;
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: #EAECF0;
}

.password-input:focused {
    -fx-border-style: solid;
    -fx-border-width: 1px;
    -fx-border-color: linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #2970FF 0.2%, #0040C1 99.99%);
}
```

## Titre :

```css
/* Label -> LoginTitle */
.login-title {
    -fx-font: 24px 'Inter';
    -fx-font-weight: bold;
    -fx-margin-bottom: 100px;
}
```
