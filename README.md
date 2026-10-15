Pokémon Battler

Detta är min andra inlämningsuppgift i Java.

Programmet är en vidareutveckling av min Pokédex från inlämning 1. Det körs i konsolen och låter användaren hantera Pokémon och spela strider mot datorn.

Funktioner

Lägga till, visa, söka, ändra och ta bort Pokémon

Filtrera Pokémon efter typ

Sortera Pokémon efter namn

Visa statistik

Återställa seed-data med sex Pokémon

Spara och ladda Pokédex från fil

Starta Pokémon-strider mot datorn

Välja Pokémon och attack

Turordningsbaserade strider med HP och skada

Träffsäkerhet och typeffektivitet

Visa antal vinster och förluster

Spara och läsa in stridsresultat

Lagring

Programmet använder en ArrayList för att lagra Pokémon under körning.

Pokédex sparas i pokedex.txt.

Stridsresultat sparas i battle-results.txt och läses in när programmet startar.

Om pokedex.txt saknas används sex Pokémon som seed-data.

Teknik

Java

Maven

ArrayList

java.nio.file för filhantering

Starta programmet

Öppna projektet i IntelliJ IDEA.

Kör Main.java.

Välj ett alternativ i konsolmenyn.

Välj alternativ 12 för att starta en strid.

Välj alternativ 13 för att visa stridsresultat.

lumpfaktorn är mellan 0,85 och 1,0.
Typeffektiviteten kan vara 0,5, 1,0 eller 2,0
beroende på Pokémon-typerna.

## OOP och kodstruktur
Pokemon-klassen hanterar Pokémonens namn, typ, HP och attacker.
Attack-klassen innehåller information om en attack.
Battle-klassen ansvarar för stridssystemet och stridsresultat.
Main-klassen ansvarar för huvudmenyn och Pokédex-funktionerna.

Klasserna har olika ansvarsområden för att hålla koden
tydligare och enklare att vidareutveckla.

## VG-krav
Jag siktar på betyget G. Typeffektivitet finns med i
stridssystemet, men övriga VG-krav är inte implementerade.

## Kända begränsningar
Pokémonens aktuella HP sparas inte mellan programstarter.
När programmet startas om får Pokémon full HP igen.