# movies-api

REST API ja frontend filmide andmebaasi haldamiseks ja katsetamiseks.
Projekt on tehtud kooli kohustusliku grupitöö raames.

---

## Projekti eesmärk

Eesmärk on luua Spring Boot + JPA põhine REST API, mis võimaldab:

- hallata filme, žanreid ja näitlejaid
- filtreerida filme žanri, näitleja ja aasta järgi
- kasutada lehekülgede kaupa päringuid (pagination)
- teostada lihtsat otsingut filmi pealkirja alusel
- demonstreerida erinevaid seoseid filmide, žanrite ja näitlejate vahel

Frontend on boonus, mis ei ole kooli poolt kohustuslik, ning on mõeldud API visuaalseks demonstreerimiseks.


---

## Kasutatud tehnoloogiad

Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- SQLite
- Maven

Frontend 

Frontend kood ei ole kooli hindamiseks vajalik.
Boonus-frontendi testimiseks piisab veebilehe külastamisest.

---

## Backend — käivitamine lokaalselt

Eeldused
- Java 21
- Maven

Projekti käivitamine:
mvn clean spring-boot:run

Backend käivitub aadressil:
http://localhost:8081

---

## Andmed (Sample Data)

Rakendus kasutab hardcoded näidisandmeid, mis laetakse käivitamisel automaatselt.

Tagatud on:
- vähemalt 20 filmi
- vähemalt 5 žanri
- vähemalt 15 näitlejat
- igal žanril on vähemalt 2 filmi
- mõnel filmil on mitu žanri
- mõnel näitlejal on mitu filmi
- filmide aastad katavad vähemalt kahte aastakümmet

---

## API kasutamine (lühijuhis)

Filmide päring (filtreerimine) Postmanis

- GET /api/movies — kõik filmid
- GET /api/movies?genreId=... — filter žanri järgi
- GET /api/movies?actorId=... — filter näitleja järgi
- GET /api/movies?year=... — filter aasta järgi
- Filtreid saab kombineerida (AND loogika)

Näited:
GET /api/movies?genreId=1
GET /api/movies?actorId=4
GET /api/movies?year=2010
GET /api/movies?genreId=1&actorId=4&year=2010

---

## Pagination

Näide: esimesed 10 filmi
GET /api/movies?page=0&size=10

---

## Frontend (boonus) — kasutamine

Frontend on avalik demo ja töötab hardcoded konfiguratsiooniga (Render + Vercel).

Ava veebileht:
https://movies-frontend-omega.vercel.app/

Võtab ca 20 sekundit andmete laadimiseks käivitamisel...

Kasutus:
- Home lehel kuvatakse filmide koguarv ja nimekiri.
- Movies lehel saad kasutada filtreid:
    - Žanr
    - Näitleja
    - Aasta
- Reset nupp taastab filtrite algseisu.

Lisainfo:
- Frontend kasutab backend’i Renderis.
- Frontend on hostitud Vercelis.
