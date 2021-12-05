# Vessel tracker
### UWAGA!
* Na branchu master znajduje się aplikacja skonfigurowana pod heroku i jest dostępna pod adresem: https://hr-vessel.herokuapp.com/. 
(Pierwsze uruchomienie może zając chwilę, ponieważ aplikacja musi się wybudzić)
* Na branchu docker znajduje się aplikacja skonfigurowana pod dockera.
### Uwagi do wesji aplikacji na dockera
* używana jest baza h2, która zapamiętuje swój stan do czasu zamknięcia aplikacji.
* Uruchomienie:
1. Przejść do maven i wykonać kolejno cykle: clean, compile, package,
2. Przejść w gitbash lub powershell do katalogu, gdzie znajduje się plik Dockerfile,
3. Wykonać polecenie "docker build -f Dockerfile -t vesseltracker:v1 ."
4. Sprawdzić utworzony image poleceniem "docker images",
5. Wykonać polecenie "docker run -p 8000:8080 79d", przy czym podstawiając za 79d początek hashu własnego image.
6. Aplikacja dostępna jest pod adresem "http://localhost:8080/".
### Opis działania aplikacji
Jest to aplikacja webowa umożliwiająca śledzenie bieżącego połozenia statków oraz zbierania ich danych. 
Na pierwszej stronie widzimy krótki opis dostępnych funkcji w aplikacji.
Aby przejść do pozostałych stron należy założyć konto. Hasła są szyfrowane, ale odradzam wpisywanie własnych haseł.
#### Mapa
Mapa wyświetla aktualne położenie statków. Zdarza się, że przeglądarka ustawia jako widok zbliżenia, własne ustawienia lokalizacji, 
a badany obszar jest w Norwegii, więc tam należy szukać statków. Klikając na jeden statek, możemy obliczyć jego odległość do pozostałych
statków, poprzez klikanie w ich znaczniki. Resetowanie zaznaczeń wykonuje się poprzez podwójne kliknięcie.
#### Lista statków
Wyświetlane są wszystkie statki, które co najmniej raz znalazły się na badanym obszarze.
Statki można dodawać do ulubionych, aby ułatwić wracanie do nich. 
Każdy statek ma link do wyświetlenia statystyk i danych historycznych oraz do pokazania aktualnej pozycji statku na mapie.
Może zdarzyć się sytuacja, że wybrane statki są juz poza badanym obszarem i nie da się wyświetlić ich położenia.
#### Statystyki i dane historyczne statków
Znajdują się tam dane zebrane podczas przeglądania mapy z aktualnymi pozycjami statków. Wybrane kolumny można wyłączyć poprzez ustawienia w sekcji "Settings" w menu.
W widoku utawień użytkownika wystąpił błąd w stylu, ale nie poprawiam, ponieważ minął czas na złoszenia.
#### Widok administracji użytkownikami 
W wersji heroku wystarczy zalogować się loginem i hasłem: admin admin.
W wersji z dockerem, aby skorzystać z widoku zarządania użytkownikami, należy skorzystać z kontrolera restowego o endpoincie "http://localhost:8080/api/users". 
Należy ustawić Authorization na Basic Auth i podać dane utworzonego kona użytkownika.
W sekcji Body podać w formacie raw JSON, np.

{
    "id": 2,
    "username": "admin",
    "email": "admin@gmail.com",
    "password": "admin",
    "role": "ROLE_ADMIN",
    "enabled": true
}

Najważniejsza jest część "role", która oznacza możliwości administratora.

W tym widoku widzymy utworzonych użytowników, możemy dodawać nowych, w tym również innych administratorów. Możliwe jest usuwanie kont, ale proszę tego nie 
używać w wersji heroku, aby nie psuć pracy innych.

#### Widok About i Contact
W kontakcie jest kontakt, a w About nic nie ma, ponieważ nie zdążyłem przenieść tam opisu, który zamieszczam na githubie.
