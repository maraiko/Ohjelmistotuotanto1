# Ohjelmistotuotanto1
## Yleistä tietoa
Tekijät: Lassi Uosukainen, Markus Martikainen ja Antti Jumpponen

Tiedostot kattavat Ohjelmistotuotanto 1 -projektin, joka on Mökkikylät-yrityksen sisäinen lomaKylä-järjestelmä. Järjestelmässä on tarkoitus hallinnoida Mökkikylät-yrityksen omistamiin mökkeihin liittyviä varauksia, laskuja ja asiakkaita.

![lomaKylän logo](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/parhainlogoikina.png?raw=true)

## Kuvia järjestelmästä
Kirjautumisikkuna, jossa voi kirjautua sisään joko adminina tai guestina:
![lomaKylän Loginikkuna](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/kuvat/loginikkuna.png)

Alkuikkuna, josta pääsee neljään eri ikkunaan:
![lomaKylän Alkuikkuna](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/kuvat/alkuikkuna.png)

Mökki-ikkuna, jossa voidaan hallinnoida mökin tietoja:
![lomaKylän Mökkiikkuna](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/kuvat/mokkikkuna.png)

Asiakasikkuna, jossa voidaan hallinnoida asiakkaan tietoja:
![lomaKylän Asiakasikkuna](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/kuvat/asiakasikkuna.png)

Varausikkuna, jossa voidaan hallinnoida varauksen tietoja:
![lomaKylän Varausikkuna](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/kuvat/varausikkuna.png)

Laskuikkuna, jossa voidaan hallinnoida laskujen tietoja:
![lomaKylän Laskuikkuna](https://github.com/maraiko/Ohjelmistotuotanto1/blob/main/kuvat/laskuikkuna.png)

## Miten asentaa lomaKylä-ohjelmisto:
Luo projekti (IntelliJ:llä tai muulla sovelluksella) ja lisää projektiin kaikki .java-päätteiset luokat. Projekti toimii ohjelmistotuotanto-nimisellä packagella. lomakyla.sql hyödyntää MySQL:ää, joten lataa lomakyla.sql ja aja se MySQL:ssä. Lataa myös kuvat mokki.jpg ja parhainlogoikina.png, ja siirrä ne projektin kohdalle src -> main -> resources-kansioon. Ohjelman pitäisi tällöin toimia ja päivittää järjestelmässä tehtyjä muutoksia tauluihin myös MySQL:n puolella.
