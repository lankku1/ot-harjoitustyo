# KalenteriVarausjärjestelmä - CaressysApp

Sovellus toimii kalenterivarausjärjestelmänä sukuni mökille. Sovelluksen avulla käyttäjät pystyvät varaamaan mökin itselleen halutulle ajankohdalle niin, että se tulee myös muille näkyväksi, eikä muiden ole mahdollista varata päällekkäin toista mökkivuoroa. Nimetään kalenterivarausjärjestelmä CaressysApp:iksi.

Sovelluksella on siis useampi rekisteröitynyt käyttäjä. Jokaisella käyttäjällä on näkyvillä lista omista varauksista sekä näkymä kalenteriin, missä on tiedot muiden varauksista. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/lankku1/ot-harjoitustyo/releases/tag/Viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _CaressysApp-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

