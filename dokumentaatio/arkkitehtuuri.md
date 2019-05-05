# Arkkitehtuurikuvaus
## Rakenne
Sovellus koostuu kolmesta eri pakkauksesta _caressys.ui, caressys.domain_ ja _caressys.dao_. _Caressys.ui_ pakkaus sisältää sovelluksen käyttöliittymän toimintaa, joka on toteutettu JavaFX:llä. _Caressys.dao_ pakkaus vastaa tietojen pysyväistallennuksesta ja _caressys.domain_ taas määrittelee sovelluslogiikan sovellukselle. 

Pakkaus _caressys.ui_ hyödyntää pakkauksen _caressys.domain_ tarjoamia ominaisuuksia, joka taas hyödyntää pakkauksen _caressys.dao_ ominaisuuksia, jotka tulevat esiin seuraavissa kohdissa.

## Käyttöliittymä

Käyttöliittymä koostuu neljästä eri näkymästä, jotka on sijoitettuna sovelluksen ikkunaan (primaryStage).
- kirjautumisnäkymä (loginScene)
- uuden käyttäjänluomisen näkymästä (newUserScene)
- käyttäjänäkymästä käyttäjän kirjauduttua sisään (userScene)
- uuden varauksen luonti näkymästä (newReservationScene)

Käyttöliittymä on rakennettu luokassa CaressysUi.java. Käyttöliittymän tavoitteena on pääasiassa tulostaa näkymissä vaadittavat asiat ja näiden tietojen pohjalta hyödyntää sovelluslogiikan service palveluja esim. tiedon tallettamiseen ja sisään- tai uloskirjautumiseen.

Metodia getReservations() käytetään kaikkien tehtyjen varausten tulostamiseen näkyville käyttäjänäkymässä.

## Sovelluslogiikka
Sovelluksen logiikan muodostaa luokat User ja Cares(CalendarReservation), jotka kuvaavat käyttäjiä sekä käyttäjien tekemiä varauksia.

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Untitled%20Diagram.png">

CaressysService-luokka muodostaa sovelluksen toiminnan sovelluslogiikan, jossa hyödynnetään caressys.dao pakkauksen luokkia esim. käyttäjän etsimiseen tai luomiseen. Metodeina toimii muun muassa
- boolean login(String username)
- boolean createUser(String username, String name)
- boolean createReservation(LocalDate arrival, LocalDate departure)
- List<Cares> listAllReservations()

CaressysServicen ja ohjelman muiden osien suhdetta kuvaava pakkauskaavio (kaaviossa CaRes toimii kalenterivarausten luokkana):

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavioSovelluksesta.png">

## Tietojen pysyväistallennus
Pakkauksen caressys.dao luokkien _FileUserDao_ ja _FileCaresDao_ tarkoituksena on tallettaa tiedot luotuihin tiedostoihin. _FileUserDao_ tallettaa tiedot käyttäjistä _users.txt_ tiedostoon ja _FileCaresDao_ tallettaa tiedot varauksista _reservations.txt_ tiedostoon. Luokat noudattavat Data Access Object -suunnittelumallia ja ne on luoto rajapintojen _UserDao_ ja _CaresDao_ taakse.

## Tiedostot
Kuten jo tietojen pysyväistallennuksessa avattiin, niin sovellus tallettaa käyttäjän tiedot, sekä käyttäjien luomat varaukset erillisiin tiedostoihin.

Sovelluksen juureen on sijoitettu konfiguraatiotiedosto config.properties, joka määrittelee tiedostojen nimet.
Sovellus tallettaa käyttäjät seuraavasti:
<pre>
testaaja;Maija
muumi;Maikki
</pre>
, missä on ensin talletettu käyttäjänimi ja tämän jälkeen käyttäjän nimi.
Käyttäjien tekemät varaukset taas talletetaan seuraavasti
<pre>
1;2019-05-07;2019-05-09;testaaja
4;2019-09-10;2019-09-14;muumi
</pre>
, missä näkyy ensimmäisenä varauksen id eli tunniste, tästä seuraa tulo- ja lähtöpäivä sekä lopuksi varauksen tehneen käyttäjän käyttäjänimi.
Molemmissa tiedostoissa kentät on erotettu puolipisteellä.

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikaa käyttäen muutaman päätoiminnallisuuden osaa sekvenssikaavion muodossa.

### Uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötetty käyttäjätunnus, joka ei ole jo käytössä, sekä nimi ja klikataan painiketta create user etenee sovelluksen kontrolli seuraavasti:

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newUser.png">

### Käyttäjän sisäänkirjautuminen

Kun käyttäjä on luotu, voidaan sisäänkirjautua sovellukseen käyttämällä luodun profiilin käyttäjänimeä. Mikäli käyttäjänimi on olemassa, voidaan siirtyä käyttäjänäkymään klikkaamalla painiketta login, jonka näkymää varten tulostetaan vielä olemassa olevat varaukset.

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/userLogin.png">

### Uuden varauksen luonti

Uutta varausta varten sisäänkirjautuneen käyttäjän tulee syöttää näkymälle halutun ajankohdan tulo- ja lähtöpäivä. Klikattuaan painiketta newReservation, sovellus varmistaa, että kyseinen ajankohta ei luo päällekkäisyyksiä muiden varausten kanssa (tässä kaaviossa ei ole tehty vielä muita varauksia). Mikäli ei tule päällekkäisyyksiä, luodaan kyseinen varaus käyttäjälle, oikealla tunnuksella ja tallennetaan tämä tiedostoon. Lopuksi, mikäli varaus luotiin onnistuneesti, sovellus siirtyy takaisin käyttäjän etusivulle, missä uusi varaus on jo näkyvillä.

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/createNewReservation.png">
