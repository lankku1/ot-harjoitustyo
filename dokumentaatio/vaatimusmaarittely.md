# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ideana sovellukselleni olisi tehdä kalenterivarausjärjestelmä sukuni mökille. Sovelluksen 
avulla käyttäjät pystyvät varaamaan mökin itselleen tietyksi ajankohdaksi niin, että se 
tulee myös muille näkyväksi. Muiden käyttäjien ole mahdollista varata päällekkäin toista mökkivuoroa
samalle ajankohdalle.
 
Sovelluksella on siis useampi rekisteröitynyt käyttäjä. Jokaisella käyttäjällä on näkyvillä lista 
omista sekä muiden varauksista. Sovelluksen varauksia hallinnoi ylläpitäjä. Alla olevassa kuvassa
vielä hahmotelma käyttöliittymästä.

## Käyttäjät
Sovelluksella on useampi *normaali käyttäjä* sekä yksi *pääkäyttäjä*, jolla on oikeudet.
Pääkäyttäjällä on oikeudet poistaa käyttäjien varauksia.

## Käyttöliittymäluonnos
Sovellus koostuu viidestä eri näkymästä.

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/overview.png>

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä luomaan uusi käyttäjä.
Kirjautumisen jälkeen siirrytään käyttäjän etusivulle, jossa näkyy käyttäjien tekemiä varauksia. Käyttäjän etusivulta mahdollista siirtyä uuden varauksen luontiin tai kirjautua ulos käyttäjältä takaisin kirjautumisnäkymään. 
Uuden varauksen luomisnäkymässä tulee kirjata
ajankohta, koska saapuu ja lähtee pois mökiltä. Varaus onnistuu mikäli samalla
ajankohdalla ei ole muita varauksia.
Kirjautumisnäkymästä on myös mahdollista siirtyä ylläpitäjän näkymään, jossa ylläpitäjä voi tarkastella tehtyjä varauksia ja poistaa näitä.

## Perusversion tarjoama toiminnallisuus
### Ennen kirjautumista
* käyttäjä voi luoda oman käyttäjätunnuksen järjestelmään
	* käyttäjätunnuksen tulee olla uniikki ja pituudeltaan vähintään 3 merkkiä
* käyttäjä voi kirjautua järjestelmään
	* kirjautuminen onnistuu syöttämällä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
	* käyttäjä tulee olla luotuna, muuten järjestelmä ilmoittaa virheestä
### Kirjautumisen jälkeen
* käyttäjä voi näkee omat sekä muiden varaukset
* käyttäjä voi luoda uuden varauksen
	* jos samalle ajankohdalle on jo tehty varaus, niin järjestelmä ilmoittaa virheestä
### Ylläpitäjän toiminnallisuuksia
* poistaa muiden varauksia
## Jatkokehitysideoita
Seuraavia ideoita pystyisi hyödyntämään sovelluksen kehittämiseen toimivammaksi:
* kalenterinäkymä varauksista
* varausta luodessa kalenterivalikossa jo varattuja päiviä ei ole mahdollista edes valita
	* kalenterivalikon näkymässä näkyy jo valmiina tehdyt varaukset ja kuka varauksen on tehnyt
* käyttäjällä mahdollisuus muuttaa tai poistaa omia varauksiaan
* kirjautumisen yhteydessä käyttäjällä salasana
* kun käyttäjä luo uuden varauksen, ylläpitäjän tulee ensin hyväksyä se, että se tulee voimaan
* ylläpitäjällä tai itse käyttäjällä mahdollisuus käyttäjätunnuksen (ja siihen liittyvien varauksien) poistoon
