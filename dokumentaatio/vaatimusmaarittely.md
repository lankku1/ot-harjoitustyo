# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ideana sovellukselleni olisi tehdä kalenterivarausjärjestelmä sukuni mökille. Sovelluksen 
avulla käyttäjät pystyvät varaamaan mökin itselleen tietyksi ajankohdaksi niin, että se 
tulee myös muille näkyväksi, eikä muiden ole mahdollista varata päällekkäin toista mökkivuoroa
samalle ajankohdalle.
 
Sovelluksella on siis useampi rekisteröitynyt käyttäjä. Jokaisella käyttäjällä on näkyvillä lista 
omista varauksista sekä näkymä kalenteriin, missä on tiedot muiden varauksista. Alla olevassa kuvassa
vielä hahmotelma käyttöliittymästä.

## Käyttäjät
Sovelluksella on useampi *normaali käyttäjä* sekä yksi *pääkäyttäjä*, jolla on oikeudet.
Haluaisin ainakin tällä hektellä pääkäyttäjälle oikeudet poistaa ja muuttaa muiden varauksia, 
mikäli tämä on mahdollista.

## Käyttöliittymäluonnos
Sovellus koostuu kuudesta eri näkymästä.



Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä luomaan uusi käyttäjä.
Kirjautumisen jälkeen siirrytään käyttäjän etusivulle, josta on mahdollista siirtyä tarkastelemaan
käyttäjän omia varauksia tai kalenterinäkymään. Kalenterinäkymässä voi katsoa kalenterista muiden
käyttäjien tekemiä varauksia, sekä siirtyä uuden varauksen luomisnäkymään, missä tulee kirjata
ajankohta, että koska saapuu ja lähtee pois mökiltä, joka lisätään kalenteriin, mikäli samalla
ajankohdalla ei ole muita varauksia. Voi olla, että muutan kalenterinäkymän vielä yksinkertaisempaan
muotoon. Saatan myös lisätä omat varaukset heti käyttäjän etusivulle, mutta katson tätä myöhempänä.

## Perusversion tarjoama toiminnallisuus
### Ennen kirjautumista
* käyttäjä voi luoda oman käyttäjätunnuksen järjestelmään
	* käyttäjätunnuksen tulee olla uniikki ja pituudeltaan vähintään 3 merkkiä
* käyttäjä voi kirjautua järjestelmään
	* kirjautuminen onnistuu syöttämällä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
	* käyttäjä tulee olla luotuna, muuten järjestelmä ilmoittaa virheestä
### Kirjautumisen jälkeen
* käyttäjä näkee omat varauksensa
* käyttäjä voi nähdä muiden varauksia
* käyttäjä voi luoda uuden varauksen
	* jos samalle ajankohdalle on jo tehty varaus, niin järjestelmä ilmoittaa virheestä
### Ylläpitäjän toiminnallisuuksia
* poistaa muiden varauksia
* muuttaa muiden varauksia
## Jatkokehitysideoita
Seuraavia ideoita pystyisi hyödyntämään sovelluksen kehittämiseen toimivammaksi:
* kalenterinäkymä varauksista (mikäli en siis lisää kalenterinäkymää ensimmäiseen versiooni)
* lisäksi käyttäjille mahdollisuus muuttaa tai poistaa omia varauksiaan
* kirjautumisen yhteydessä salasana
* kun käyttäjä luo uuden varauksen, ylläpitäjän tulee ensin hyväksyä se, että se tulee voimaan
* käyttäjätunnuksen  (ja siihen liittyvien varauksien) poisto