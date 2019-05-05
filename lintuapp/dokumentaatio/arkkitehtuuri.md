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

Uuden käyttäjän luominen alkaa kun käyttäjä klikkaa "Create new user" -painiketta. Tämän jälkeen käyttöliittymästä kutsutaan birdService-luokan metodia createUser, jolle annetaan parametreina käyttäjätunnus ja nimi. UserDaon kautta tarkistetaan onko samalle käyttäjänimelle jo käyttäjätunnus olemassa. Jos ei ole, niin metodi palauttaa "null". Sitten tälle henkilölle luodaan käyttäjätunnus luomalla uusi User-olio, joka tallennetaan UserDao-luokan create-metodin avulla ja palataan loginScene-näkymään. Alla tapahtumaa havainnollistava kuva.

![Sekvenssikaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Sekvenssikaavio%20(1).png)


#### Sisäänkirjautuminen

Sisäänkirjautumisessa käyttäjä syöttää käyttäjänimensä sille tarkoitettuun tekstikenttään ja painaa login-painiketta. Tällöin kutsutaan birdServicen metodia "login", jolle annetaan parametriksi käyttäjätunnus. UserDao:n avulla selvitetään, onko kyseinen käyttäjätunnus olemassa. Jos on, niin näkymäksi vaihtuu näkymäksi birdScene eli näkymä, jossa on lista lintulajeista sekä vieressä checkboxit. Alla tapahtuma on havainnollistettu kuvassa. 

![Sisaankirjautuminen_kaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/sisaankirjautuminen2.png)


#### Lintujen tallentaminen listaan

Lintujen tallentaminen listaan alkaa kun käyttäjä painaa logout-näppäintä. Tällöin käyttöliittymä käy läpi kaikki checkboxit ja luo boolean-tyyppisen listan, johon se tallentaa arvon true, jos lintu on havaittu ja muuten false. Lista viedään luokkaan birdService metodin saveChecks avulla, jolle siis parametrina on annettu kyseinen lista. Sovellus luo uuden Birdapp-olion ja luokan birdDao metodia "create" kutsutaan, jotta se tallentaa tiedot tekstitiedostoon ja sille annetaan parametriksi kyseinen Birdapp-olio. Lopuksi palataan takaisin kirjautumisnäkymään. Alla on kuva tapahtumasta.

![lintujentallentaminen_kaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/lintujen_tallentaminen2.png)


#### Lintujen palauttaminen listasta

Lintujen palauttaminen listasta alkaa käyttäjän painaessa login-näppäintä. Ensimmäisenä tarkistetaan, onko käyttöliittymässä oleva lista tyhjä eli onko sovellus sammutettu ennen uutta sisäänkirjautumista. Mikäli sovellusta ei sammuteta sisäänkirjautumisten välissä, palautetaan käyttöliittymän lista. Muuten kutsutaan birdService-luokan parametritonta getChecked-metodia. Tämä hakee BirdDao:n avulla tekstitiedosta viimeisimmän listan, joka viedään käyttöliittymään asti ja sen listan perusteella asetetaan checkboxit checkatuiksi, jos listassa on true. Alla havainnollistava kuva.

![lintujenpalauttaminen_kaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/lintujen_palauttaminen.png)


## Ohjelman rakenteeseen jääneet heikkoudet

- BirdappUi-luokassa voisi eriyttää toiminnallisuuksia omiksi metodeikseen.
- Muuttujat ja metodit voisivat olla selkeämmin nimettyjä
- Birdapp-luokka on tällä hetkellä melko turha eli sen voisi poistaa tai muuttaa sovelluksen logiikkaa siten, että luokka olisi tarpeellisempi
- Ohjelmassa on vielä jonkin verran toisteisuutta. Joitakin muuttujia olisi voinut jättää luomatta ja käyttää toisen sovelluksen vastaavia muuttujia
- Testauksessa on paljon kehitettävää
- Koodi voisi olla selkeämpää
