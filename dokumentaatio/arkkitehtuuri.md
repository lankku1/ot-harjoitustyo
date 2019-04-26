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

CaressysService-luokka muodostaa sovelluksen toiminnan sovelluslogiikan, jossa hyödynnetään caressys.dao pakkauksen luokkia esim. käyttäjän etsimiseen tai luomiseen. Metodeina toimii muun muassa
- boolean login(String username)
- boolean createUser(String username, String name)
- boolean createReservation(LocalDate arrival, LocalDate departure)
- List<Cares> listAllReservations()

CaressysServicen ja ohjelman muiden osien suhdetta kuvaava pakkauskaavio (kaaviossa CaRes toimii kalenterivarausten luokkana):

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavioSovelluksesta.png">

## Päätoiminnallisuudet

### uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötetty käyttäjätunnus joka ei ole jo käytössä sekä nimi ja klikataan painiketta createUser etenee sovelluksen kontrolli seuraavasti:

<img src= "https://github.com/lankku1/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newUser.png">
