# Käyttöohje

Aloita lataamalla tiedosto [Birdapp.jar](https://github.com/jennalack/ot-harjoitustyo/releases/tag/viikko5)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät tallentavan tiedoston nimen. Myöhemmin lisätään myös tiedosto, joka tallentaa tiedon nähdyistä linnuista. Tiedoston muoto on seuraava

userFile=users.txt

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla: java -jar birdapp.jar

## Kirjautuminen

Käynnistettäessä sovellus, ensimmäisenä avautaa kirjautumisnäkymä. 

![Kirjautumisnäkymä](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Loginnakyma.PNG)

Jos käyttäjätunnus on jo luotu, niin käyttäjä voi kirjautua sisään syöttämällä tunnuksensa ja painamalla Login-nappia. Käyttäjätunnuksen ollessa oikein, sovellus näyttää listan linnuista, jotka voi merkata nähdyiksi. Mikäli käyttäjätunnusta ei ole olemassa, ilmoittaa sovellus "User does not exist".

## Uuden käyttäjän luominen

Mikäli käyttäjä ei ole vielä luonut käyttäjätunnusta itselleen, tulee hänen painaa "Create new user" -nappia, jolloin aukeaa seuraavanlainen näkymä:

![Uudenkäyttäjänluominen](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Createnewuser.PNG)

Tähän käyttäjän tulee syöttää haluamansa käyttäjätunnus, jonka tulee olla uniikki ja yli 2 merkkiä pitkä. Sovellus ilmoittaa, jos valittu käyttäjätunnus on käytössä tai liian lyhyt. Käyttäjän on syötettävä myös nimensä uutta käyttäjätunnusta luodessa. Kun tarvittavat tiedot on syötetty käyttäjä painaa Create-nappia ja sovellus ilmoittaa käyttäjätunnuksen onnistuneesta luomisesta ja näkymä palaa takaisin kirjautumiseen.

## Lintujen merkkaaminen nähdyksi

Sovellus pitää sisällään listan linnuista, jotka voi sovelluksessa ruutua klikkaamalla merkitä nähdyksi. Lintu on nähty, jos ruudussa linnun nimen vieressä on check-merkki. Tällä hetkellä check-merkit tallennu tietokantaan, joten ne eivät säily, jos sovellus sammutetaan eivätkä ne ole vielä käyttäjäkohtaisia. Tämä vaatii vielä kehittämistä. 

![Listalinnuista](https://github.com/jennalack/ot-harjoitustyo/blob/master/lintuapp/dokumentaatio/kuvat/Lintun%C3%A4kym%C3%A4.PNG)

Päänäkymässä oikeassa yläkulmassa näkyy kirjautuneen käyttäjän käyttäjätunnus ja vasemmassa yläkulmassa on logout-nappi, jota painamalla käyttäjä pystyy kirjautumaan ulos. 
