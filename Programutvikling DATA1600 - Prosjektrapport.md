# Programutvikling DATA1600 - Prosjektrapport

## Innledning

I faget Programutvikling DATA1600 - Våren 2019, ble det gitt i semesteroppgave å utvikle et registreringssystem. Arbeidet foregikk over 5 uker og forventet arbeidsmengde var 50-60 timer per person. Det var tre alternativer å velge mellom, henholdsvis et registreringssystem for forsikring, vikarbyrå eller kulturhus.

Gruppen, bestående av Rany Tarek Bouorm (s236210) og Eirik Bøyum (s170002), valgte oppgaven om forsikring. 

Problemstilling

Et registreringssystem for forsikringer skal implementeres. Oppgavens vedlegg lister opp elementer som systemet skal registrere. Elementene består av:

- Kunder
- Forsikringer
  - Båtforsikring
  - Hus og innboforsikring
  - fritidsboligforsikring
  - Reiseforsikring.
- Skademeldinger

##### Programmet skal inneholde følgende funksjonaliteter:

- Elementene skal kunne registreres, redigeres og valideres. Det skal dermed ikke være mulig å legge til ugyldige elementer. Det er ikke gitt noen formelle krav til hva som oppfattes som gyldige og ugyldige elementer.
- Elementene skal kunne visualiseres i et grafisk brukergrensesnitt. Det skal også være mulig å sortere disse etter hver datakolonne, og filtrere dem etter datakolonnene som det ville vært naturlig å filtrere etter.
- Programmets data skal kunne lagres og leses fra fil. Det er gitt krav til at programmet skal støtte to filtyper, serialisert format med filendelse ".JOBJ" og CSV-format. Løsningen for lagring skal implementeres med **strategy** designmønsteret og applikasjonen skal dynamisk kunne velge mellom de to filformatene beskrevet. Løsningen for innlesing fra fil skal kjøre i en egen tråd for å å ikke blokkere programmet under innlesing av store filer.

### Verktøy

##### Følgende verktøy ble brukt i prosjektet:

| Verktøy                                                      | Beskrivelse                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [IntelliJ IDEA](<https://www.jetbrains.com/idea/>)           | IDE. Brukt til utvikling av programmet i Java. IntelliJ har også innebygd støtte for Maven. |
| [Scene Builder](<https://gluonhq.com/products/scene-builder/>) | Grafisk grensesnitt for produksjon av FXML filer. Brukt for å lage brukergrensesnittene i programmet. |
| [Maven](https://maven.apache.org/)                           | Prosjekthåndteringsverktøy. Brukt til å integrere JavaFX og JUnit i prosjektet. |
| [Git](https://git-scm.com/)                                  | Versjonskontrollssystem. Brukt til å holde orden på prosjektet med historikk og muligheten for å reversere endringer. |
| [GitHub Dekstop](https://desktop.github.com/)                | Grafisk brukergrensesnitt for bruk av git. Brukt som supplement til git kommandolinje. |
| [Evernote](https://evernote.com/)                            | Notatprogram. Brukt til å skrive sammendrag av gruppemøter og til å dele informasjon mellom gruppemedlemmene. |
| [Typora](https://typora.io/)                                 | Enkelt program for produksjon av Markdown dokumenter. Brukt til denne rapporten. |



<div style="page-break-after: always;"></div>

## Fremgangsmåte

Gruppen kom til enighet om å gjøre oppgaven som omhandler forsikringsregister. Det ble gjort forarbeid for å få kunnskaper i JavaFX, TableView og Maven før arbeidet med oppgaven startet. 

Første gruppemøte ble holdt 2. April 2019. Her ble oppgaven gjennomgått og det ble gjort rede for de forskjellige bruksscenarioene gruppen så for seg. Basert på dette ble det utarbeidet noen skisser over de forskjellige vinduene programmet skulle ha. Medlemmene kom også til enighet om forarbeidet som skulle gjøres på dette møtet.

##### Utført forarbeid

- En oppgave kalt "NotepadFX" som var lagt ut på ressurssiden i emnet, hvor en imitasjon av Notepad skulle implementeres i Java med fillagring og bruk av tråder. 
- Gjennomgåelse av [Oracle sin tutorial](https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm) på bruk av TableView.
- Oppsett av et "hello world", modulbasert Maven-prosjekt i IntelliJ med JavaFX som dependency.
- Lesing av deler av boken [Clean Code: A Handbook of Agile Software Craftmanship](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882) og sammendrag av denne.
- Lesing om populære designmønstre, spesielt "strategy" mønsteret, men også "factory" og "state".

Punktene listet over ble gjort av minst ett gruppemedlem og videreformidlet til den andre. Dette gjorde gruppen bedre rustet til å planlegge oppgaven, da det ga erfaring med noen av de forskjellige funksjonalitetene som skulle implementeres og verktøyene som skulle brukes.

##### Videre arbeid

Etter forarbeidet ble prosjektet satt opp i [git](<https://git-scm.com/>). Gruppen kom til enighet om at det vil være naturlig å starte med å opprette klassen for kunde og en klasse for kunderegister, som skal fungere som en singletonklasse hvor programmets data er lagret. 

Etter at disse ble implementert, begynte arbeidet med å implementere visning (TableView), registrering og redigering av kunder, samt lagring og innlesing av disse til og fra fil. Deretter ble dette utvidet ved å legge til forsikringer og skademeldinger.

Oppgaven med å implementere TableView, registrering og redigering ble tildelt Rany, mens oppgaven i filhåndtering ble tildelt Eirik. Etterhvert ble det klart at Eirik hadde utfordringer med å utføre den oppgaven, da han ikke hadde skrevet klassene som skulle lagres. Her var blant annet bruken av properties forvirrende. Dette ble løst ved å jobbe med denne delen av oppgaven sammen i form av parprogrammering. Filhåndtering med filtypen "CSV" ble forskjøvet til slutt, og Eirik fikk i oppgave å implementere funksjonalitet for skademeldinger og ubetalte erstatninger, slik at han også fikk erfaring med blant annet JavaFX, TableView og bruk av properties.

<div style="page-break-after: always;"></div>

## Resultater

Arbeidet resulterte til slutt i et fungerende registreringssystem for kunder, forsikringer og skademeldinger. Systemet har **Kunde** som hovedobjekt. Med andre ord holder Kunde-objektet referanse til all relevant kundeinfo som forsikringer og skademeldinger. En singletonklasse **Kunderegister**, inneholder en **ObservableList** med alle kundene. Forsikringsklassene arver alltid fra klassen **Forsikring** og implementerer interface for validering og serialisering. Det er laget egendefinerte exception-klasser for Kunde, Forsikring og Skademelding. Disse kastes dersom en ugyldig Kunde blir lagt til via addForsikring() i kunderegisteret, eller dersom en ugyldig forsikring eller skademelding blir lagt inn i kunde via addForsikring() eller addSkademelding().

Relaterte elementer er abstrahert ut i egne klasser: for eksempel har en båtforsikring en båt, og båten har en eier, slik at når en båtforsikring skal opprettes, vil man i dens konstruktør kun ta imot forsikringsdetaljer og den forsikrede båten som parameter. Dette begrenser lengde på konstruktørene og minimerer sjansene for feil.

Det er lagt stor vekt på lesbarheten av kode og bruk av Javadoc, som forenkler utviklingsprosessen og utvidelse av programmet senere. Vi har også forsøkt å holde oss til [SOLID-prinsippene](https://en.wikipedia.org/wiki/SOLID), spesielt "[Single responsibility principle](https://en.wikipedia.org/wiki/Single_responsibility_principle)". Dette har vist seg å være både utfordrende og lærerrikt, da det tvinger en til å tenke gjennom klassene som opprettes og variabelnavnene som brukes. 

Dersom programmet i fremtiden skal utvides med flere forsikringer, er prosessen som følger: 

- Opprett en ny forsikringsklasse som utvider klassen **Forsikring**. Implementer **Validerbar** og **Serializable** interfacet i klassen. **Validerbar** gjør at forsikringen kan valideres og legges til forsikringsregisteret, **Serializable** gjør at forsikringen kan lagres i serialisert format.

- Utvid klassene **CSVWriteHelper** og **CSVReadHelper** under **filemanagement**-pakken slik at forsikringen kan lagres og leses fra CSV.

- Opprett en valideringsklasse for forsikringstypen som innehar forsikringens lovlige verdier. 

- Opprett view og controller for opprettelse og redigering av forsikringen. 

- Lag en ny knapp i **RegistrerNyForsikring**-menyen, som leder til viewet for registrering av forsikringen. 

- Utvid klassen **RedigerForsikringHelper** slik at den åpner riktig scene  når den nye forsikringstypen er valgt.  

  <div style="page-break-after: always;"></div>

## Refleksjon

Videre følger en refleksjon over arbeidet, som spesifisert i rapportbeskrivelsen.

##### Deler vi er fornøyd med

- Lesbarhet i koden. Vi har lagt stor vekt på navngivning av metoder og variabler og det å dele opp metoder i hensiktsmessige deler. Nedenfor følger et eksempel på navngivning og oppdelte metoder.

  **Fra RegistrerReiseforsikringController.java:**

```java
    @FXML
    private void registrerReiseforsikring(ActionEvent event){

        try {
            /*generateReise() er tatt ut i en egen private metode for å gjøre denne 
            metoden mer lesbar. Metodenavnet indikerer at en reiseforsikring blir
            generert når metoden kalles.*/
            Reiseforsikring reiseforsikring = generateReiseforsikring(); 
            
            /*En InvalidForsikringException kastes dersom forsikringen er ugyldig
            og håndteres her i en try/catch blokk*/
            kunde.addForsikring(reiseforsikring);

            //Lukk vindu hvis forsikring blir registrert OK.
            Stage stage = (Stage) btnNeste.getScene().getWindow();
            stage.close();

        } catch (InvalidForsikringException|NumberFormatException e) {
            /*Alerts er brukt flere steder i klassen, og er abstrahert ut i en
            egen metode. Dette minker mengden repetiv kode.*/
            generateAlert("Kunne ikke registrere forsikring:\nFyll inn alle felt eller 				sjekk rød-markerte felt.");
        }
    }
```



- Valideringsløsningen: Det er opprettet egne klasser for validering. Disse inneholder statiske metoder for validering av hvert enkelt felt i en forsikring, og brukes i alle kontrollerne som registrerer og redigerer forsikringer for umiddelbar tilbakemelding på om inntastet informasjon er gyldig eller ikke. Klassene inneholder også en metode som validerer elementet i sin helhet basert på alle feltene. Denne brukes i **Validerbar** interfacet.

  Nedenfor følger et eksempel på validering av enkeltfelt:

  **Fra InnboForsikringValidator.java:**

  ```java
  public static boolean forsikringsbeløpInnboIsValid(String forsikringsbeløpForInnbo) {
  
  	try {
  		/*Forsøker å parse og validere input, returnerer false hvis NumberformatException 			blir fanget.*/
  		int forsikringsbeløpForInnboParsed = Integer.parseInt(forsikringsbeløpForInnbo);
   		return forsikringsbeløpForInnbo >= minForsikringssbeløpForInnbo && 
              	forsikringsbeløpForInnbo <= maxForsikringssbeløpForInnbo;
  	} catch (NumberFormatException e) {
  		return false;
  	}
  }
  ```

  **Fra RegistrerInnboForsikring.java:**

  ```java
   /*Metoden kalles når det blir skrevet noe i feltet "forsikringsbeløp innbo"
   og kaller på en statisk metode som styler feltet basert på gyldighet*/   
  @FXML
  private void forsikringsbeløpInnboChanged(InputEvent event){
  	String forsikringsbeløpInnbo = txtForsikringsbeløpInnbo.getText();
  	if(!InnboForsikringValidator.forsikringsbeløpInnboIsValid(forsikringsbeløpInnbo)){
  		FieldStyler.setInvalidStyle(txtForsikringsbeløpInnbo);
  	} else{
  		FieldStyler.setValidStyle(txtForsikringsbeløpInnbo);
  		updateForsikringsbeløp();
  	}
  }
  ```



- Løsning av ubetalte erstatninger: Oppgaven spesifiserte at ubetalte erstatninger skulle tas med som et element under kunde. Vi valgte å løse dette i View. En skademelding inneholder et felt utbetaltErstatning. Dersom denne er satt til 0, er det ikke utbetalt noen erstatning, og kunden har dermed en ubetalt erstatning. Dermed lagde vi en **FilteredList** basert på skademeldinger med ingen utbetalt erstating, og viser dette i en egen **TableView**.

##### Elementer som kunne blitt gjennomført bedre

- Det er noe brukt noe repetiv kode, spesielt i views og kontrollere for registrering og redigering. Her kunne det vært implementert en superklasse for å unngå duplikatkode. 
- Designet av applikasjonen føles noe utdatert ut. Her kunne vi lagt noe mer vekt på design og UX.
- Automatisert testing: Det er implementert noen få JUnit tester i prosjektet, men det er for det meste testet manuelt. Dette har vist seg å være svært upålitelig, da man ofte vil komme over feil av ren tilfeldighet. Hvis det er en ting vi ville lagt større vekt på, ville det vært mer utstrakt bruk av JUnit testing, potensielt i form av "test-driven developement" prosessen.

##### Læringsutbytte

Prosjektet har vært det første større prosjektet vi har hatt iløpet av studiet. Vi føler at det har vært svært lærerrikt å jobbe med å utvikle et program over lenger tid.

- Lært og erfart viktigheten av godt forarbeid.
- Vi har lært viktigheten av navngivning, både mtp. gruppearbeid, men også det å skulle forstå sin egen kode noen dager/uker senere.
- Vi har fått erfaring med å legge klasser med relatert funksjonalitet i egne klasser, og å abstrahere ut metoder som brukes i flere klasser.
- Vi har lært å bruke git til å samarbeide. Spesielt i form av Feature Branching. Vi har også hatt noen problemer og frustrasjoner med git underveis, som har gjort at vi måtte grave dypere i dokumentasjonen av git's mange funksjonaliteter. Dette har økt forståelsen av git.
- Vi har erfart at det ikke alltid er lett å sette seg inn i andres kode, og at parprogrammering kan hjelpe på dette.

Til neste prosjekt ville vi lagt større vekt på planleggingsfasen, men spesielt på automatisert testing, da vi nå ser nytteverdien av dette.

##### Beskrivelse av arbeidet utført av hvert medlem:

- **Rany Tarek Bouorm:**

  - Design av menyer.
  - Oppsett av prosjektet i git.
  - Oppsett av Maven.
  - Implementasjon av klassene for kunde og forsikringer.
  - Oppsett av TableView for Kundelisten og forsikringslisten.
  - View og kontrollere for registrering, redigering av forsikringer.
  - Kodestrukturen i valideringsklassene.
  - Implementasjon av singleton designmønsteret for kunderegisteret.
  - Implementasjon av Validerbar interfacet og bruken av denne.
  - Implementert tråder i filhåndteringsklassene.
  - Omgjort prosjektets mappestruktur.
  - Skrevet Javadocs.

  **Eirik Bøyum:**

  - Design av menyer.

  - Implementasjon av Serializable interfacet for alle elementer.
  - Implementasjon av filhåndtering.
  - View og kontrollere for registrering, redigering og validering av skademelding og ubetalt erstatning.

<div style="page-break-after: always;"></div>

## Konklusjon

