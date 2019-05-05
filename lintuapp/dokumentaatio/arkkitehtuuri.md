# Arkkitehtuurikuvaus


## Rakenne

Ohjelmalla on kolmikerroksinen pakkausrakenne, joka näkyy alla olevassa kuvassa:

![Rakennekaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Untitled%20Diagram.png)

birdapp.ui-pakkaus sisältää graafisen käyttöliittymän, joka on toteutettu JavaFX:llä. birdapp.domain-pakkaus taas pitää sisällään sovelluslogiikan ja birdapp.dao-pakkaus vastaa tietojen tallennuksesta tietokantaan. 


## Käyttöliittymä

Käyttöliittymään kuuluu kolme näkymää ja se on luotu birdapp.ui.BirdappUi -luokassa. 

Avatessaan sovelluksen käyttäjä näkee ensimmäisenä sisäänkirjautumisikkunan, jolla käyttäjä voi joko kirjautua sisään tai luoda uuden käyttäjän. Kaikki ikkunat on luotu omina Scene-olioinaan. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. 

Kun uusi käyttäjä kirjautuu, sovellus hakee birds -tekstitiedostosta Boolean-tyyppisen listan, mitä lintuja on jo havainnoitu ja tämän perusteella asettaa osan checkboxeista checkatuiksi. Käyttäjän kirjautuessa ulos, metodi checkIfChecked käy boksit läpi ja katsoo, missä näistä on check-merkki, ja tallentaa näistä Boolean-tyyppisen listan, joka tallennetaan myös tekstitiedostoon.


## Sovelluslogiikka

Luokka User kuvaa sovelluksen käyttäjiä ja luokka Birdapp yhdistää nähdyt linnut käyttäjiin. 

![Sovelluslogiikka](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Sovelluslogiikka.png)

Toiminnallisuuksista vastuussa on BirdappService-luokka. Se tarjoaa kaikille käyttöliittymän toiminnoille oman metodinsa. Toiminnalisuuksia ovat esimerkiksi:
- boolean login(String username)
- ArrayList<Boolean> getChecked()
- boolean createUser(String username, String name)
- boolean saveChecks(ArrayList<Boolean> list)
  
BirdappService-luokka pystyy hyödyntämään tietojen tallenusta, sillä se on yhteydessä rajapintoihin BirdappDao ja UserDao, niitä toteuttavien luokkien kautta.

Alla on Birdapp-projektin pakkauskaavio:

![Pakkausrakenne](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/Valmispakkaus.png)


## Tietojen pysyväistallennus

Luokat FileBirdappDao ja FileUserDao birdapp.dao-pakkauksessa hoitavat tietojen tallentamisen tekstitiedostoihin.

Luokat on tehty Data Access Object -suunnittelumallia hyödyntäen ja ne on eristetty rajapintojen BirdappDao sekä UserDao taakse, mikä mahdollistaa sen, ettei sovelluslogiikka käytä luokkia suoraan.

### Tiedostot 

Sovellus tallentaa luodut käyttäjätunnukset omaan tiedostoonsa sekä käyttäjien merkitsemät ruudut omaan tiedostoonsa. Eli, jos käyttäjä on nähnyt linnun ja rastittanut kyseisen linnun ruudun, niin teksti-tiedostoon tallennetaan siihen kohtaan true ja muihin eli tyhjien ruutujen kohtaan false.

Sovelluksen juuressa on konfiguraatiotiedosto nimeltään config.properties, jossa määritellään tiedostojen nimet.

Sovellus tallentaa käyttäjät tekstitiedostoon alla havainnoidulla tavalla:

`jennalack;Jenna Lackbergh`

eli ensin merkitään käyttäjätunnus, jonka jälkeen lukee käyttäjän nimi, joka on erotettu käyttäjätunnuksesta puolipisteellä.

Havainnoiduiksi merkityt linnut sovellus tallentaa seuraavalla tavalla:

`jennalack;[true, false, true, false, false, false, false, false]`

eli ensin on taas käyttäjätunnus, jonka jälkeen on lista onko checkboxissa check vai ei. Lyhensin listaa tähän selvyyden vuoksi. Varsinaisessa tekstitiedostossa lista sisältää 40 boolean-totuusarvoa.

### Päätoiminnallisuudet

Alla on kuvattuna muutama sovelluksen päätoiminnallisuuksista sekvenssikaavioina.

#### Uuden käyttäjän luominen

![Sekvenssikaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Sekvenssikaavio%20(1).png)

#### Sisäänkirjautuminen

![Sisaankirjautuminen_kaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/sisaankirjautuminen.png)

## Ohjelman rakenteeseen jääneet heikkoudet

- BirdappUi-luokassa voisi eriyttää toiminnallisuuksia omiksi metodeikseen.
- Muuttujat ja metodit voisivat olla selkeämmin nimettyjä
- Birdapp-luokka on tällä hetkellä melko turha eli sen voisi poistaa tai muuttaa sovelluksen logiikkaa siten, että luokka olisi tarpeellisempi
- Ohjelmassa on vielä jonkin verran toisteisuutta. Joitakin muuttujia olisi voinut jättää luomatta ja käyttää toisen sovelluksen vastaavia muuttujia
- Testauksessa on paljon kehitettävää
- Koodi voisi olla selkeämpää
