# Testausdokumentti

Sovellusta on testattu JUnitilla yksikkö- sekä integraatiotestein. Testit on luotu siten, että ne testaisivat sovelluksen
kannalta olennaista osaa. Käyttöliittymää ei ole otettu huomioon testauksessa.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikan eli pakkauksen caressys.domain luokille on muodostettu omat testiluokkansa. Luokan CaressysService toiminnot on jaettu kahteen eri testiluokkaan, CaressysServiceUserTest, joka testaa sovelluksen käyttäjäpuolen toimintaa, sekä CaressysServiceCaresTest, joka testaa sovelluksen varausten toimintaa.

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia FakeUserDao ja FakeCaresDao luokkia.

Lisäksi luokille Cares ja User on muodostettu omat yksikkötestit, jossa testataan integraatiotesteistä puuttuvia toiminnallisuuksia.

### DAO-luokat

Luokkien CaresDao ja UserDao luokkien päätoiminnallisuuksia testataan käyttäen hyödyksi JUnitin TemporayFolder:ia. CaresDaon testauksessa keskitytään lähtökohtaisesti testaamaan sitä, että halutun varauksen ajankohta ei tuota päällekkäisyyksiä jo tehtyjen varausten kanssa.

### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 72% ja haarautumakattavuus 80%.

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testReport.png">

DAO-luokkien sekä CaressysService testauksessa ei ole otettu huomioon tilannetta, kun varauksia tai käyttäjiä ei ole vielä olemassa.

## Järjestelmätestaus

Järjestelmän testaus on suoritettu manuaalisesti. Asennus ja konfigurointi toimii seuraamalla sovelluksen [käyttöohjetta]("https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md"). Sovelluksen toiminnallisuutta on testattu OSX-ympäristössä niin, että käynnistämishakemistossa on on _config.properties_-tiedosto.

Määrittelydokumentin ja käyttöohjeen toiminnallisuudet on käyty läpi ja toimivat määritteiden mukaisesti, myös silloin kun sovelluksen ei tulisi toimia, kuten sisäänkirjautuminen väärällä käyttäjällä tai varauksen luominen niin, että se luo päällekkäisyyksiä olemassa olevien varausten kanssa.

## Sovelluksen puutteita

Sovellus ei anna virheilmoitusta, mikäli konfiguraatiotiedostoa ei ole olemassa.
