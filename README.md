# FFAGame - FFA Rush Plugin

**FFAGame** est le plugin principal d'un serveur nommé **FFA Rush**, développé pour Minecraft 1.8.9 (Spigot/Paper).  
Il gère **le lobby**, **les knockbacks des TNT**, **le stuff**, **les blocs temporaires** et l’ensemble de la logique du mode de jeu.

---

## ✨ Fonctionnalités
- **Lobby sécurisé** : empêche toute destruction/construction non autorisée.
- **Knockback TNT personnalisé** : réglages fins de la puissance horizontale/verticale.
- **Gestion du stuff** : attribution automatique de l’équipement aux joueurs.
- **Spawns & téléportations** : gestion centralisée via `WorldManager` et `SpawnManager`.
- **Blocs temporaires** : destruction automatique après un délai via `BreakBlockTask`.
- **Vérification du vide** : téléporte ou gère les joueurs qui tombent.
- **Commandes personnalisées** :
  - `/lobby` : retour au lobby.
  - `/world` : gestion et téléportation vers des mondes.
- **Permissions** : //TODO `Perms`.

---

## ⚙️ Installation
1. Compiler le plugin :
   ```bash
   mvn clean package
   ```
   Le `.jar` se trouvera dans le dossier `target/`.
2. Placer le fichier `.jar` dans le dossier `plugins` de ton serveur Minecraft.
3. Redémarrer le serveur.

Le plugin est également accessible sur le release.
---

## 🛠 Configuration
- **config.yml** : permet de définir les réglages comme la puissance des knockbacks.
- **plugin.yml** : contient les commandes, permissions et infos du plugin.

---

## 🚀 Commandes
| Commande | Description | Permission |
|----------|-------------|------------|
| `/lobby` | Téléporte au lobby | `ffa.lobby` |
| `/world` | Gère et téléporte vers des mondes | `ffa.world` |

---

## 🔑 Permissions
- `ffa.lobby` : accès à la commande `/lobby`.
- `ffa.world` : accès à la commande `/world`.

---

## 📜 Licence
Projet privé – réservé à l’usage personnel du développeur.
