# Käyttöohje

Aloita lataamalla tiedosto [Birdapp.jar](https://github.com/jennalack/ot-harjoitustyo/releases/tag/viikko5)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät tallentavan tiedoston nimen. Tiedostoon birds.txt tallennetaan käyttäjänimi sekä tieto siitä, onko checkbox valittuna vai ei. Tämä tallennetaan Boolean tyyppisenä listana, jossa true tarkoittaa, että lintu on havaittu ja false tarkoittaa, ettei lintua ole havaittu. 

Tiedostojen muodot ovat seuraavat

userFile=users.txt
birdFile = birds.txt


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla: java -jar birdapp.jar


## Kirjautuminen

Kun sovellus käynnistetään avautuu ensimmäisenä kirjautumisnäkymä. 

![Kirjautumisnäkymä](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Loginnakyma.PNG)

Jos käyttäjätunnus on jo luotu, niin käyttäjä voi kirjautua sisään syöttämällä tunnuksensa ja painamalla Login-nappia. Käyttäjätunnuksen ollessa oikein, sovellus näyttää listan linnuista. Listan vieressä on checkboxit, joihin voi merkata, jos on nähnyt jonkun linnuista tai ottaa checkin pois, mikäli listaan on vahingossa tullut virheellinen merkkaus. Mikäli käyttäjätunnusta ei ole olemassa, ilmoittaa sovellus "User does not exist".

## Uuden käyttäjän luominen

Mikäli käyttäjä ei ole vielä luonut käyttäjätunnusta itselleen, tulee hänen painaa "Create new user" -nappia, jolloin aukeaa seuraavanlainen näkymä:

![Uudenkäyttäjänluominen](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Createnewuser.PNG)

Tähän käyttäjän tulee syöttää haluamansa käyttäjätunnus, jonka tulee olla uniikki ja yli 4 merkkiä pitkä. Sovellus ilmoittaa, jos valittu käyttäjätunnus on käytössä tai liian lyhyt. Käyttäjän on syötettävä myös nimensä uutta käyttäjätunnusta luodessa. Kun tarvittavat tiedot on syötetty käyttäjä painaa Create-nappia ja sovellus ilmoittaa käyttäjätunnuksen onnistuneesta luomisesta ja näkymä palaa takaisin kirjautumiseen.

## Lintujen merkkaaminen nähdyksi

Sovellus pitää sisällään listan linnuista, jotka voi sovelluksessa ruutua klikkaamalla merkitä nähdyksi. Lintu on nähty, jos ruudussa linnun nimen vieressä on check-merkki. Kaikilla käyttäjillä on yhteinen lista eli kaikki näkevät samat checkit kirjautuessaan sisään. 

![Listalinnuista](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Lintun%C3%A4kym%C3%A4.PNG)

Päänäkymässä oikeassa yläkulmassa näkyy kirjautuneen käyttäjän käyttäjätunnus ja vasemmassa yläkulmassa on logout-nappi, jota painamalla käyttäjä pystyy kirjautumaan ulos. 
