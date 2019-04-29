# Programutvikling DATA1600 - Prosjektrapport

## Innledning



### Bakgrunn

I faget Programutvikling DATA1600 - Våren 2019, ble det gitt i semesteroppgave å utvikle et registreringssystem. Arbeidet skal foregå over 5 uker og forventet arbeidsmengde er 50-60 timer per person. Det var tre alternativer å velge mellom, henholdsvis et registreringssystem for forsikring, vikarbyrå eller kulturhus.

Gruppen, bestående av Rany Tarek Bouorm og Eirik Bøyum, valgte oppgaven om forsikring. 

### Problemstilling

Et registreringssystem for forsikringer skal implementeres. Oppgavens vedlegg lister opp elementer som systemet skal registrere. Elementene består av:

* Kunder
* Forsikringer
  * Båtforsikring
  * Hus og innboforsikring
  * fritidsboligforsikring
  * Reiseforsikring.
* Skademeldinger

##### Programmet skal inneholde følgende funksjonaliteter:

* Elementene skal kunne registreres, redigeres og valideres. Det skal dermed ikke være mulig å legge til ugyldige elementer. Det er ikke gitt noen formelle krav til hva som oppfattes som gyldige og ugyldige elementer.
* Elementene skal kunne visualiseres i et grafisk brukergrensesnitt. Det skal også være mulig å sortere disse etter hver datakolonne, og filtrere dem etter datakolonnene som det ville vært naturlig å filtrere etter.
* Programmets data skal kunne lagres og leses fra fil. Det er gitt krav til at programmet skal støtte to filtyper, serialisert format med filendelse ".JOBJ" og CSV-format. Løsningen for lagring skal implementeres med **strategy** designmønsteret, og applikasjonen skal dynamisk kunne velge mellom de to filformatene beskrevet. Løsningen for innlesing fra fil skal kjøre i en egen tråd for å å ikke blokkere programmet under innlesing av store filer.

### Verktøy

##### Følgende verktøy ble brukt i prosjektet:

| Verktøy                                                      | Beskrivelse                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [IntelliJ IDEA](<https://www.jetbrains.com/idea/>)           | IDE. Brukt til utvikling av programmet i Java. IntelliJ har også innebygd støtte for Maven. |
| [Scene Builder](<https://gluonhq.com/products/scene-builder/>) | Grafisk grensesnitt for produksjon av FXML filer. Brukt for å lage brukergrensesnittene i programmet. |
| [Maven](https://maven.apache.org/)                           | Prosjekthåndteringsverktøy. Brukt til å integrere JavaFX og JUnit i prosjektet. |
| [Git](https://git-scm.com/)                                  | Versjonskontrollssystem. Brukt til å holde orden på prosjektet med historikk og muligheten for å reversere endringer. |
| [Evernote](https://evernote.com/)                            | Notatprogram. Brukt til å skrive sammendrag av gruppemøter og til å dele informasjon mellom gruppemedlemmene. |
| [Typora](https://typora.io/)                                 | Enkelt program for produksjon av Markdown dokumenter. Brukt til denne rapporten. |




<div style="page-break-after: always;"></div>

## Fremgangsmåte

Gruppen, bestående av Rany Tarek Bouorm og Eirik Bøyum, kom til enighet om å gjøre oppgaven som omhandler forsikringsregister. Det ble gjort forarbeid for å få kunnskaper i JavaFX, TableView og Maven før arbeidet med oppgaven startet. 

Første gruppemøte ble holdt 2. April 2019. Her ble oppgaven gjennomgått og det ble gjort rede for de forskjellige bruksscenarioene gruppen så for seg. Basert på dette ble det utarbeidet noen skisser over de forskjellige vinduene programmet skulle ha. Medlemmene kom også til enighet om forarbeidet som skulle gjøres på dette møtet.

##### Forarbeid

* En oppgave kalt "NotepadFX" som var lagt ut på ressurssiden i emnet, hvor en imitasjon av Notepad skulle implementeres i Java med fillagring og bruk av tråder. 
* Gjennomgåelse av [Oracle sin tutorial](https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm) på bruk av TableView.
* Oppsett av "hello world" modulbasert Maven-prosjekt i IntelliJ med JavaFX som dependency.
* Lesing av deler av boken [Clean Code: A Handbook of Agile Software Craftmanship](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882) og sammendrag av denne.
* Lesing om designmønstre, spesielt "strategy" mønsteret, men også "factory" og "state".

Punktene listet over ble gjort av minst ett gruppemedlem, og gjorde gruppen bedre rustet til å planlegge oppgaven, da det ga erfaring med mange av de forskjellige funksjonalitetene som skulle implementeres.

##### Videre arbeid

Etter forarbeidet ble prosjektet satt opp i [git](<https://git-scm.com/>). Gruppen kom til enighet om at det vil være naturlig å starte med å opprette klassen for kunde og en klasse for kunderegister, som skal fungere som en Singleton klasse hvor programmets data er lagret. 

Etter at disse ble implementert, begynte arbeidet med å implementere visning (TableView), registrering og redigering av kunder, samt lagring og innlesing av disse til og fra fil. Deretter ble dette utvidet ved å legge til forsikringer og skademeldinger.

Oppgaven med å implementere TableView, registrering og redigering ble tildelt Rany, mens oppgaven i filhåndtering ble tildelt Eirik. Etterhvert ble det klart at Eirik hadde utfordringer med å utføre den tildelte oppgaven, da han ikke hadde skrevet klassene som skulle lagres. Her var blant annet bruken av properties forvirrende. Dette ble løst ved å jobbe med denne delen av oppgaven sammen i form av parprogrammering. Filhåndtering med filtypen "CSV" ble forskjøvet til slutt, og Eirik fikk i oppgave å implementere funksjonalitet for skademeldinger og ubetalte erstatninger, slik at han også fikk erfaring med blant annet JavaFX, TableView og bruk av properties.



* Belyse de delene av oppgaven dere er spesielt fornøyd med.
  * Ryddigheten i kode, navngivning av metoder, bruk av hjelpemetoder for å forbedre lesbarhet hvor dette er mulig.
  * Implementasjonen av TableView og hvordan dette oppdateres i sanntid.
  * Implementasjonen av ubetalte erstatninger istedenfor å ha dette som en egen klasse.
* Diskusjon rundt elementer som dere mener kunne ha blitt gjennomført bedre og/eller
  aspekter i besvarelsen dere ikke er helt fornøyd med.
* Utdyp hva dere har lært i henhold til prosjektarbeid og hva dere vil gjøre annerledes for deres
  neste store gruppeprosjekt.
* Beskrivelse av arbeidet som har blitt uført for hvert medle

