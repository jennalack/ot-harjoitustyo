# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjillä on mahdollisuus *pitää yhteisesti kirjaa näkemistään lintulajeista*. Sovellusta voi siis käyttää useampi käyttäjä, joilla on yhteinen lista havainnoimistaan lintulajeista.

## Käyttäjät

Sovelluksella on vain *normaaleita käyttäjiä* eli kaikilla käyttäjillä on samanlaiset oikeudet.

## Käyttöliittymäluonnos

Sovellus koostuu kolmesta eri näkymästä. Ensimmäisenä käyttäjälle avautuu kirjautumisikkuna, josta on mahdollista siirtyä uuden käyttäjän luomiseen tai onnistuneen kirjautumisen jälkeen kolmanteen näkymään, jossa voi merkitä litulajea nähdyiksi. 

![Käyttöliittymäkuva](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/lintuapp.jpg)

## Perusversion tarjoama toiminnallisuus

**Ennen kirjautumista**
- Käyttäjä voi luoda käyttäjätunnuksen
  - Käyttäjätunnuksen tulee olla uniikki ja vähintään 5 merkkiä pitkä
- Käyttäjä voi kirjautua järjestelmään
  - Kirjautuminen onnistuu syötettäessä järjestelmään rekisteröity käyttäjätunnus
  - Järjestelmä ilmoittaa, jos käyttäjää ei ole olemassa
  
**Kirjautumisen jälkeen**
- Käyttäjä näkee
  - Listan lintulajeista, joita on yhteisö on kirjannut nähdyiksi järjestelmään
  - Listan lintulajeista, joita yhteisö ei ole vielä havainnoinut
- Käyttäjä voi merkata uuden lintulajin nähdyksi
- Käyttäjä voi poistaa merkinnän nähdystä lintulajista
- Käyttäjä voi kirjautua ulos

## Jatkokehitysideoita

- Sovellukseen voi lisätä listat 
  - Käyttäjäkohtaisista havainnoista
  - Kalenterivuoden aikana Suomessa näkemistäään lintulajeista sekä lukumäärän, monta lajia hän on nähnyt
  - Elämänsä aikana maailmalla näkemistäään lintulajeista sekä lukumäärän, monta lajia hän on nähnyt
  - Lista kaikista maailman lintulajeista
- Sovellukseen voisi lisätä lintulajeja
- Nähtyjen lintulajien jakaminen toisille käyttäjille
- Toisten käyttäjien lisääminen kaveriksi
- Mahdollisuus kisata toisia vastaan, kenellä on eniten niin sanottuja pinnoja (=nähtyjä lajeja)
- Mahdollisuus lisätä aika ja paikka, jossa linnun nähnyt
- Käyttäjän yhteyteen salasana
- Käyttäjätunnuksen ja siihen liittyvien tietojen poistaminen

