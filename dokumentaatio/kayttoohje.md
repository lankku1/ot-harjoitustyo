# Käyttöohje

Lataa tiedosto caressysapp.jar.

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittelee käyttäjät ja todot tallettavien tiedostojen nimet. Tiedoston muoto on seuraava

```
userFile=users.txt
resFile=reservations.txt
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar caressysapp.jar
```
## Kirjautuminen
Sovellus käynnistyy kirjautumisnäkymään:

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/loginScene.png">

Kirjautuminen onnistuu syöttämällä olemassaoleva käyttäjätunnus syötekenttään ja painamalla _Login_.

## Uuden käyttäjän luominen
Mikäli käyttäjää ei ole vielä luotuna, voi tällaisen luoda painamalla kirjautumisnäkymässä _Create new user_
painiketta, jolloin siirrytään seuraavaan näkymään:

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/createNewUserScene.png">

Uuden käyttäjän luominen onnistuu kirjoittamalla uniikin käyttäjänimen sekä nimen ja tämän jälkeen painamalla _Create_ painiketta.
Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Käyttäjänäkymä sekä uusien varausten luominen
Kun käyttäjä on päässyt kirjautumaan sisään, käyttäjälle on näkyvissä omat sekä muiden tekemät varaukset. 

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/userScene.png">

Käyttäjä voi halutessaan päästä uuden varauksen luomisnäkymään painamalla _New reservation_ painiketta.
Tällöin siirrytään uuden varauksen luomisnäkymään

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/createNewReservationScene.png">

Nyt valitaan kalenterista haluttu ajankohta ja painetaan painiketta _Create new reservation_. Tämän jälkeen
sovellus siirtyy takaisin käyttäjänäkymään ja tehty varaus tulisi näkyä tässä näkymässä. Uloskirjautuminen onnistuu painamalla _sign out_ painiketta.

## Ylläpitäjän näkymä
Ylläpitäjän näkymään pääsee kirjautumisnäkymästä kirjoittamalla salasanakenttään "admin" ja tämän jälkeen painamalla _Log in as admin_ painiketta. Tämän jälkeen ohjelma siirtyy seuraavaan näkymään:

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/adminScene.png">

Ylläpitäjä voi halutessaan poistaa minkä tahansa varauksen painamalla kyseisen varauksen _Delete_ painiketta, jonka jälkeen varaus poistuu näkyviltä kaikilta sovelluksen käyttäjiltä. Halutessa ylläpitäjä voi poistua näkymästä painamalla _Return_ painiketta, jolloin käyttäjä palaa takaisin kirjautumisnäkymään.
