# Vaatimus Määrittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjillä on mahdollisuus *pitää kirjaa näkemistään lintulajeista*. Sovellusta voi käyttää useampi käyttäjä, joilla jokaisella on oma listansa nähdyistä linnuista.

## Käyttäjät

Sovelluksella on ainakin alkuvaiheessa vain yksi käyttäjä, joka on *normaalikäyttäjä*. Myöhemmin voisi lisätä mahdollisesti *premiumkäyttäjiä*, joille sovellus tarjoaisi enemmän toimintoja.

## Käyttöliittymäluonnos

Sovellus koostuu kolmesta eri näkymästä. Ensimmäisenä käyttäjälle avautuu kirjautumisikkuna, josta on mahdollista siirtyä uuden käyttäjän luomiseen tai onnistuneen kirjautumisen jälkeen kolmanteen näkymään, jossa voi tallentaa nähtyjä lajeja. 

## Perusversion tarjoama toiminnallisuus

**Ennen kirjautumista**
- Käyttäjä voi luoda käyttäjätunnuksen
  - Käyttäjätunnuksen tulee olla uniikki
- Käyttäjä voi kirjautua järjestelmään
  - Kirjautuminen onnistuu syötettäessä järjestelmään rekisteröity käyttäjätunnus
  - Järjestelmä ilmoittaa, jos käyttäjää ei ole olemassa
  
**Kirjautumisen jälkeen**
- Käyttäjä näkee
  - Listan lintulajeista, joita on Suomessa elämänsä aikanaan nähnyt (ja kirjannut järjestelmään) sekä lukumäärän
  - Listan kaikista lintulajeista Suomessa
- Käyttäjä voi merkata uuden lintulajin nähdyksi
  - Tämä näkyy ainoastaan käyttäjälle
- Käyttäjä voi kirjautua ulos

## Jatkokehitysideoita

- Sovellukseen voi lisätä listat 
  - Kalenterivuoden aikana Suomessa näkemistäään lintulajeista sekä lukumäärän, monta lajia hän on nähnyt
  - Elämänsä aikana maailmalla näkemistäään lintulajeista sekä lukumäärän, monta lajia hän on nähnyt
  - Lista kaikista maailman lintulajeista
- Nähtyjen lintulajien jakaminen toisille käyttäjille
- Toisten käyttäjien lisääminen kaveriksi
- Mahdollisuus kisata toisia vastaan, kenellä on eniten niin sanottuja pinnoja (=nähtyjä lajeja)
- Mahdollisuus lisätä aika ja paikka, jossa linnun nähnyt
- Käyttäjän yhteyteen salasana
- Käyttäjätunnuksen ja siihen liittyvien tietojen poistaminen
