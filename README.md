# ecf-arcadia-back

## Mise en place de l'environnement de demonstration du back-end.

Cet environnement de démonstration ne prend pas en charge la mise en place du chiffrement TLS. Seul l'environnement de production dispose de cette fonctionalité.

Le parmétrage d'envoi de mail n'est pas présent. Pour activer les envois de mail, il faut renseigner les paramètres du serveur smtp, comme décrit dans la deuxième étape, ci-dessous.

Il nécessite également un serveur ou poste de travail ayant docker installé.

Après avoir installé la partie front-end de l'application (voir le README du repo **ecf-arcadia-front**) :

- cloner le repo github (back)

```
git clone https://github.com/etupdt/ecf-arcadia-back.git
```

- Sur la branche main, vous pouvez éditer le fichier **env.demo.properties** à la racine du répertoire. Facultativement, vous pouvez y modifier les password des bases de données qui sont initialisés par défaut à la valeur "password" (variables DB_PASSWORD pour la base PostgreSql et MONGO_PASSWORD pour la base MongoDb, ainsi que MONGO_INITDB_ROOT_PASSWORD pour son initialisation). Vous pouvez également renseigner les paramètres du smtp :
```
MAIL_HOST=<adresse du smtp (ex : smtp.orange.fr)>
MAIL_PORT=<port du serveur smtp>
MAIL_USER=<identifiant auprès du serveur smtp>
MAIL_PASSWORD=<password auprès du serveur smtp>
```

- Lancer, à la racine du répertoire **ecf-arcadia-back**, la commande de création du container du back :

```
docker compose -f docker-compose-back-demo.yml --env-file env.demo.properties up -d
```

- allez dans le répertoire du projet

```
cd ecf-arcadia-back
```

L'environnement est près. Le serveur back écoute sur le port 8083. 
L'identifiant de l'administrateur est "admin@test.com" et son mot de passe par défaut est "password"
L'environnement de demo comprend également un jeu de données de test et des images copiées au moment de l'installation. Un employé y est également créé "employee@test.com" et un vétérinaire "veto@test.com". Tout deux ayant le même mot de passe que l'adminitrateur.

Si vous voulez supprimer les containers :
```
docker compose -f docker-compose-back-demo.yml --env-file env.demo.properties down
```
