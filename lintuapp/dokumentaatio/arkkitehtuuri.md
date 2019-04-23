# Arkkitehtuurikuvaus


## Rakenne

Ohjelmalla on kolmikerroksinen pakkausrakenne, joka näkyy alla olevassa kuvassa:

![Rakennekaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Untitled%20Diagram.png)

birdapp.ui-pakkaus sisältää graafisen käyttöliittymän, joka on toteutettu JavaFX:llä. birdapp.domain-pakkaus taas pitää sisällään sovelluslogiikan ja birdapp.dao-pakkaus vastaa tietojen tallennuksesta tietokantaan. 


## Käyttöliittymä

Avatessaan sovelluksen käyttäjä näkee ensimmäisenä sisäänkirjautumisikkunan, jolla käyttäjä voi joko kirjautua sisään tai luoda uuden käyttäjän. Sisäänkirjautumis- sekä uuden käyttäjän luomisikkuna toimivat jo hyvin, mutta itse sovelluslogiikassa on vielä paljon kehitettävää. Kaikki ikkunat on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa birdapp.ui.BirdappUi.

Olen pyrkinyt pitämään käyttöliittymän mahdollisimman erillisenä sovelluslogiikasta. Käyttöliittymä kutsuu vain sopivin parametrein sovelluslogiikan toteuttavan olion birdappServicen metodeja.

Kun sovelluksen todolistan tilanne muuttuu, eli uusi käyttäjä kirjautuu, todoja merkitään tehdyksi tai niitä luodaan, kutsutaan sovelluksen metodia redrawTodolist joka renderöi todolistanäkymän uudelleen sovelluslogiikalta saamansa näytettävien todojen listan perusteella.


## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Birdapp, jotka kuvaavat käyttäjiä sekä käyttäjien havainnoimia lintuja:

![Sovelluslogiikka](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Sovelluslogiikka.png)

Toiminnallisista kokonaisuuksista vastaa luokan BirdappService ainoa olio. Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodinsa. Tämä on vielä keskeneräinen.

Alla on BirdappServicen ja ohjelman muiden osien suhdetta kuvaava pakkauskaavio:

![Pakkausrakenne](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/Valmispakkaus.png)


## Tietojen pysyväistallennus

Pakkauksen birdapp.dao luokat FileBirdappDao ja FileUserDao pitävät huolta tietojen tallentamisesta tiedostoihin.

Luokat noudattavat Data Access Object -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksesta syntyvän aineiston tallennustapaa päätetään vaihtaa. Luokat on eristetty rajapintojen BirdappDao sekä UserDao taakse eikä sovelluslogiikka käytä luokkia suoraan.

### Tiedostot 

Sovellus tallentaa luodut käyttäjätunnukset omaan tiedostoonsa. Myöhemmin sovellus tallentaa myös käyttäjän lintuhavainnot erilliseen tiedostoon, mutta tämän toteutus on vielä kesken.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto config.properties määrittelee tiedostojen nimet.

Sovellus tallentaa käyttäjät alla havainnoidulla tavalla:

`jennalack;Jenna Lackbergh`

eli ensin käyttäjätunnus, jonka jälkeen tulee käyttäjän nimi puolipisteellä erotettuna.

### Päätoiminnallisuudet

#### Uuden käyttäjän luominen

![Sekvenssikaavio](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Sekvenssikaavio%20(1).png)


## Ohjelman rakenteeseen jääneet heikkoudet

Yritän vielä kehittää sovellusta, sillä tällä hetkellä se ei tallenna lintuhavaintoja käyttäjäkohtaisesti. Sovelluksen kehittämisessä on siis vielä paljon työtä. 

Testaukseen en ole ehtinyt käyttää vielä yhtään aikaa, joten se täytyy aloittaa heti kun saan sovellusta vähän edistettyä.
