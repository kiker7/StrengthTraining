package com.example.rafal.strengthtraining.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */

public class Generator {

    public static String strSeparator = ",";
    DatabaseHelper databaseHelper = null;

    public Generator() {
    }

    public static void generateIntensive() {
        int id = 0;
        String str = "";
        Random rand = new Random();
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 1; i <= 8; i++)
            for (int j = 1; j <= 3; j++)
                for (int k = 1; k <= 7; k++)
                    for (int l = 1; l <= 3; l++) {
                        id = i * 1000 + j * 100 + k * 10
                                + (rand.nextInt(6) + 1);
                        array.add(String.valueOf(id));
                    }

        System.out.println("Intensive:");
        str = convertArrayToString(array);
        System.out.println(str);
        System.out.println("Dlugosc: "+ str.length());

        // z powrotem na tablice stringow
        String[] sepstr = convertStringToArray(str);
        for (String string : sepstr) {
            System.out.print(string + " ");
        }

    }

    public static void generateHard() {
        int id = 0;
        int max = 0;
        String str = "";
        Random rand = new Random();
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 1; i <= 8; i++)
            for (int j = 1; j <= 3; j++)
                for (int k = 1; k <= 7; k++) {
                    if (i > 4) {
                        max = 4;
                    } else
                        max = 3;
                    for (int l = 1; l <= max; l++) {
                        id = i * 1000 + j * 100 + k * 10
                                + (rand.nextInt(6) + 1);
                        array.add(String.valueOf(id));
                    }
                }
        System.out.println("Hard:");
        str = convertArrayToString(array);
        System.out.println(str);
        System.out.println("Dlugosc: "+ str.length());

        // z powrotem na tablice stingow
        String[] sepstr = convertStringToArray(str);
        for (String string : sepstr) {
            System.out.print(string + " ");
        }

    }


    public static String convertArrayToString(ArrayList<String> array){
        String str = "";
        for (int i = 0;i<array.size(); i++) {
            str = str+array.get(i);
            if(i<array.size()-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

    /*
    Do odczytu z bazy
     */
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }

    /*
    Wypelnienie bazy
     *
     * do wrzucenia w aktywnosc

    private int generateExercises() {

        // generowanie wszystkich cwiczen
        //  zwraca liczbe rekordow w tabeli 'exercises'

        List<Exercise> list = new ArrayList<>();

        Exercise e1 = new Exercise("Naprzemianstronne zginanie przedramion ze sztangielkami z obrotem nadgarstka i uniesieniem łokci", "W pozycji siedzącej sztangielki trzymane w obu rękach nachwytem, przedramiona w ustawieniu pośrednim: wziąć wdech i zgiąć przedramię, wykonując półobrót nadgarstka na zewnątrz, zanim przedramię znajdzie się w położeniu poziomym;  - zakończyć ruch podniesieniem lokci i wykonać wydech. Ćwiczenie to angażuje mięsień ramienno-promieniowy, mięsień ramienny, mięsień dwugłowy ramienia, mięsień naramienny część przednią a w mniejszym zakresie mięsień kruczo-ramienny i część obojczykową mięśnia piersiowego większego.", 1, 1);
        list.add(e1);
        Exercise e2 = new Exercise("Naprzemianstronne zginanie przedramion ze sztangielkami (łokieć wsparty na udzie)", "W pozycji siedzącej, łokieć wsparty na wewnętrznej stronie uda: - wziąć wdech i zgiąć przedramię:  -w końcowej fazie ruchu wykonać wydech. To izolowane ćwiczenie pozwala na kontrolę zakresu, szybkości i dokładności ruchu. Angażuje głownie mięsień dwugłowy ramienia i mięsień ramienny.", 1, 2);
        list.add(e2);
        Exercise e3 = new Exercise("Naprzemianstronne zginanie przedramion ze sztangielkami trzymanymi neutralnie", "W pozycji stojącej lub siedzącej, sztangielki trzymane w obu rękach nachwytem, przedramię w pozycji pośredniej - wziąć wdech i zgiąć oba przedramiona równocześnie lub naprzemiennie - wykonać wydech w końcowej fazie ruchu. To ćwiczenie najlepiej rozwija mięsień ramienno-promieniowy. Wpływa ono róznież na mięsień dwugłowy ramienia i mięsień ramienny oraz w mniejszym stopniu na mięsień prostownik promieniowy krótki nadgarstka i mięsień prostownik promieniowy długi nadgarstka.", 1, 3);
        list.add(e3);
        Exercise e4 = new Exercise("Naprzemianstronne zginanie przedramion z wykorzystaniem wyciągu dolnego", "W pozycji stojącej, twarzą do przodu, rączka wyciągu trzymana podchwytem: - wziąc wdech i zgiąć przedramię; - wykonać wydech w końcowej fazie ruchu. Ćwiczenie to pozawala na koncentrację wysiłku w obrębie mięśnia dwugłowego ramienia i sprzyja intensywnemu ukrwieniu mięśnia.", 1, 4);
        list.add(e4);
        Exercise e5 = new Exercise("Ćwiczenie na mięsień dwugłowy ramienia z wykorzystaniem wyciągu górnego", "W pozycji stojącej centralnie w stosunku do wyciągu, ramiona wyciągnięte, rączki wyciągu trzymane podchwytem: - wziąć wdech i zgiąć przedramiona; - wykonać wydeh w końcowej fazie ruchu. Ćwiczenie to, które najczęściej kończy trening mięśni ramion, pozwala na rozwijanie mięśnia dwugłowego ramienia, głównie jego głowy krótkiej uprzednio rozciągniętej i napinanej w pozycji rozłożenia ramion; rozwija również mięsień ramienny będący jednostawowym zginaczem łokcia. Ćwiczenia tego nie wykonuje się nigdy przy dużym obciążeniu. Istotne, aby skoncentrować się na odczuciu kurczenia się wewnętrznej części mięśnia dwugłowego ramienia. Najlepsze wyniki przynoszą długie serie ćwiczeń.", 1, 5);
        list.add(e5);
        Exercise e6 = new Exercise("Zginanie przedramion ze sztangą trzymaną podchwytem", "W pozycji stojącej, grzbiet wyprostowany, sztanga trzymana podchwytem (ręce rozstawione na szerokość nieco większą niż rozstaw barków); - wziąć wdech a następnie zgiąć przedramiona, starając się nie poruszać klatką piersiową przez wykonanie izometrycznego skurczu mięśni pośladkowych, brzusznych i przykręgosłupowych;  wykonać wdech w końcowej fazie ruchu. Ćwiczenie to angażuje głównie mięsień dwugłowy ramienia i mięsień ramienny, a w mniejszym stopniu mięsień ramienny-promienowy, mięsień nawrotny obły i wszystkie mięśnie zginacze nadgarstka i palców.", 1, 6);
        list.add(e6);
        Exercise e7 = new Exercise("Ćwiczenie na mięsień trójgłowy ramienia z wykorzystaniem wyciągu górnego (rączka wyciągu trzymana nachwytem)", "W pozycji stojącej, ręce na rączce przyrządu, łokcie wzdłuż tułowia: - wziąc wdech i wyprostować przedramiona, starając się nie odrywać łokci od tułowia; - wykonac wydech w końcowej fazie ćwiczenia", 2, 1);
        list.add(e7);
        Exercise e8 = new Exercise("Ćwiczenie na mięsień trójgłowy ramienia z wykorzystaniem wyciągu górnego (rączka wyciągu trzymana podchwytem)", "W pozycji stojącej przodem do przyrządu, ramiona wzdłuż tułowia, zgięte w łokciach, ręce na rączkach przyrządu: - wziać wdech i wyprostować przedramiona, starając się nie odrtwać łokci od tułowia; - wykonać wydech w końcowej fazie ruchu. Pozycja nachwytu nie pozwala na trening z dużymi obciążeniami, dlatego ćwiczenia to wykonuje się z niewielkim obciążeniem w celu rozwijania mięsnia trójgłowego ramienia i skoncentrowania wysiłku w głowie przyśrodkowej mięśnia trójgłowego ramienia. Ruch prostowania przedramion angażuje również mięsień łokciowy i mięśnie prostowniki nadgarstków. Te ostatnie mięśnie (prostownik łokciowy nadgarstka, prostownik palców, prostownik palca małego, prostownik promieniowy długi nadgarstka i prostownik promieniowy krótki nadgarstka) usztywniają nadgarstek dzięki skurczowi izometrycznemu trwającemu przez cały czas wykonywania ruchu prostowania przedramion.", 2, 2);
        list.add(e8);
        Exercise e9 = new Exercise("Naprzemienne prostowanie przedramion z wykorzystaniem wyciągu górnego (rączka wyciągu trzymana podchwytem)", "W pozycji stojącej twarzą do przyrządu, rączka wyciągu trzymana podchwytem: - wziąć wdech i wyprostować przedramię; - wykońać wydech. To ćwiczenie angażuje mięsień trójgłowy ramienia, głównie jego głowę boczną.", 2, 3);
        list.add(e9);
        Exercise e10 = new Exercise("Prostowanie przedramion ze sztangą w pozycji leżącej na ławeczce", "W pozycji leżącej na plecach na ławeczce, sztanga trzymana nachwytem, ramiona ustawione pionowo w stosunku do ławeczki: - wziąć wdech i zgiąć przedramiona, starając się nie odwodzić zanadto w bok łokci przy opuszczaniu sztangi na wysokość czoła - wykonać wydech w końcowej fazie ruchu.", 2, 4);
        list.add(e10);
        Exercise e11 = new Exercise("Prostowanie przedramion ze sztangielkami w pozycji leżącej", "W pozycji leżącej, sztangielki trzymane w obu rękach, ramiona ustawione pionowo do podłoża: - wziąć wdech i zgiąć przedramiona, kontrolując ruch; - powrócić do pozycji wyjściowej i wykonać wydech w końcowej fazie ruchu. Ćwiczenie to równomiernie angażuje wszystkie trzy głowy mięśnia trójgłowego ramienia.", 2, 5);
        list.add(e11);
        Exercise e12 = new Exercise("Zginanie przedramion w podporze tyłem na ławeczkach", "Dłonie ułożone na brzegu jednej ławeczki, stopy oparte na drugiej ławeczce, ciało zawieszone w powietrzu: - wziąć wdech i zgiąć przedramiona, po czym wyprostować je (odepchnąć się na nich); - wykonać wydech w końcowej fazie ćwiczenia. Ćwiczenie to angażuje mięsień trójgłwy ramienia, mięśnie piersiowe i przednioą część mięsni naramiennych. Na udach można położyć ciężarek, dzięki czemu odpychanie będzie trudniejsze, a wysiłek intensywniejszy", 2, 6);
        list.add(e12);
        Exercise e13 = new Exercise("Wyciskanie sztangi zza karku", "W pozycji siedzącej, sztanga za karkiem trzymana nachwytem: - wziąć wdech i wycisnąć sztangę pionowo, nie wyginając nadmiernie grzbietu;  wykonać wykonać wydech w końcowej fazie ruchu. Ćwiczenie to angażuje mięsień naramienny, głównie jego część środkową i tylną, jak również mięsnień czworoboczny, mięsień trójgłowy ramienia i mięsień zębaty przedni. W wyciskaniu uczestniczą również, ale w mniejszym stopniu, mięśnie równoległoboczne, mięsień podgrzebieniowy, mięśień obły mniejszy, a w warstwie głębokiej mięsień nadgrzbietowy.", 3, 1);
        list.add(e13);
        Exercise e14 = new Exercise("Wyciskanie sztangielek w pozycji siedzącej", "W pozycji siedzącej na ławeczce, sztangielki trzymane nachwytem (kciuki do wewnątrz) na wysokości barków: wziąć wdech i wyciskać sztangielki aż do wyprostawania ramion w pionie; - wykonać wydech w końcowej fazie ruchu. Ćwiczenie to angażuje mięsień naramienny, głównie jego część środkową, jak również mięsień czworoboczny, mięsień zębaty przedni i mięsień trójgłowy ramienia. Ćwiczenie to można też wykonać w pozycji stojącej lub prostując ramiona na przemian", 3, 2);
        list.add(e14);
        Exercise e15 = new Exercise("Unoszenie ramion w bok ze sztangielkami", "W pozycji stojącej, kończyny dolne w lekkim rozkroku, grzbiet wyprostowany, ramiona ułożone wzdłuż tułowia, sztangielki w rękach: - podnieść ramiona do poziomu (łokcie lekko zgięte); - powrócić do pozycji wyjśiowej. Ćwiczenie to angażuje mięśnie naramienne, głownie ich część środkową. Ćwiczenia tego nie wykonuje się nigdy z dużymi obciążeniami. Najlepsze rezultaty dają serie 10-25 powtórzeń wykonywane pod różnymi kątami, bez zbyt długich odpoczynków między seriami i kontynuowane do chwili, gdy odczuje się pieczenie. W celu zwiększenia intensywności wysiłku można między poszczególnymi seriami przez kilka sekund trzymać uniesione poziomo ramiona w skurczu izometrycznym.", 3, 3);
        list.add(e15);
        Exercise e16 = new Exercise("Naprzemianstronne unoszenie ramion w przód ze sztangielkami", "W pozycji stojącej, kończyny dolne w lekim rozkroku, sztangielki trzymane nachwytem spoczywają na udzue lub nieco z boku: - wziąć wdech i unosić naprzemiennie ramiona do przodu na wysokości oczu; - wykonać wydech w końcowej fazie ruchu. Ćwiczenie to angażuje głownie przednią część mięśni naramiennych, część obojczykową mięśnia piersiowego większego, a w mniejszym stopiniu resztę mięsnia naramiennego. We wszystkich ruchach podnozenia ramion biorą udzuał mięśnie przytwierdzające łopatkę do klatki piersiowej, takie jak mięsień zębaty przedni i mięsień równoległoboczny większy, pozwalając kości ramiennej balansować na stabilnym wsporniku", 3, 4);
        list.add(e16);
        Exercise e17 = new Exercise("Unoszenie ramion ze sztangielkami w pozycji leżącej na boku", "W pozycji leżącej na boku na podłodze lub ławeczce, sztangielka trzymana nachwytem: - wziąc wdech i podnieść ramię do poziomu;  wykonać wydech w końcowej fazie ruchu. W odróżnieniu do podnoszenia ramion w pozycji stojącej, które angażują stopniowo mięsień naramienny aż do osiągnięcia maksymalnej intensywności wysiłku w końcowej fazie (kiedy ramiona dochodzą do poziomu), w tym ćwiczeniu wysiłek koncentruje się w początkowej fazie podnoszenia. Najlepsze wyniki uzyskuje się, wykonując 10-20 powtórzeń.", 3, 5);
        list.add(e17);
        Exercise e18 = new Exercise("Naprzemianstronne unoszenie ramion w bok z wykorzystaniem wyciągu dolnego", "Rączka wyciągu w dłoni, ramię wzdłuż tułowia: - wziąć wdech i unieść ramię do poziomu; - wykonać wydech w końcowej fazie ruchu. Ćwiczenie to rozwija mięsień naramienny, głównie jego część środkową wielopierzastą, tzn. zbudowaną z kilku pasm w kształcie pióra, dlatego należy zmieniać kąty, pod jakimi unosi się ramiona w celu zaangażowania wszystkich pasm mięśnia.", 3, 6);
        list.add(e18);
        Exercise e19 = new Exercise("Wyciskanie sztangi na skośnej ławeczce (głową do góry)", "W pozycji siedzącej na skośnej ławeczce (nachylenie ławeczki w granicach 45 i 60 stopni), sztanga trzymana nachwytem na szerokości większej niż rozstaw barków: - wziąć wdech i przenieść sztangę w okolice wcięcia szyjnego mostka; - wyciskać, wykonując wydech w końcowej fazie ruchu. Ćwiczenie to angażuje mięsień piersiowy większy, głównie jego część obojczykową i przednią część mieśnia naramiennego, mięsień trójgłowy ramienia, mięsnień zębaty przedni i mięsień piersiowy mniejszy. Można je wykonywać przy wykorzystaniu stojaków.", 4, 1);
        list.add(e19);
        Exercise e20 = new Exercise("Pompki (zginanie ramion w podporze przodem)", "Ręce wyprostowanie oparte o podłoże, rozstawione na szerokość barków (lub szerzej), stopy przy sobie lub w lekkim rozwarciu: - wziąć wdech i zgiąć ramiona tak, aby klatka piersiowa znalazła się przy podłodze, nie wyginając nadmiernie grzbietu w okolicach lędzwiowych; - wykonać wydech w końcowej fazie ruchu. Jest to doskonałe ćwiczenie na mięsień piersiowy większy i mięsień trójgłwy ramienia. Można he wykonywać w dowolnym miejscu.", 4, 2);
        list.add(e20);
        Exercise e21 = new Exercise("Zginanie ramion na poręczach (DIPSY / SZWEDZKIE POMPKI)", "Ramiona wyprostowane, oparte na poręczach, kończyny dolne zwisają nie dotykając podłoża:  - wziąć wdech i zgiąć całkowicie przedramiona tak, aby piersi znalazły się na wysokości poręczy;  - wykonać wydech w końcowej fazie pracy.  Im głębsze pochylenie tułowia do przodu tym pełniejsze zaangażowanie mięsni piersiowych (ich części wewnętrznej), i na odwrót, im bardziej wyprostowany tułów  tym pełniejsza praca mięsni trójgłowych ramienia.", 4, 3);
        list.add(e21);
        Exercise e22 = new Exercise("Wyciskanie sztangielek w pozycji leżącej", "W pozycji leżącej na poziomej ławeczce, stopy wsparte na podłożu, kończyny górne ugięte w łokciach, sztangielki trzymane nachwytem na wysokości piersi:  - wziąć wdech i wyprostować kończyny górne w pionie, obracając jednocześnie przedramiona tak, aby dłonie znalazły się naprzeciwko siebie; - gdy dłonie znajdą się naprzeciwko siebie, wykonać skurcz izometryczny w celu skoncentrowania wysiłku w części mostkowo-żebrowej mieśi piersiowych większych - wykonac wydech w końcowej fazie ruchu. Ćwiczenie to przypomina wyciskanie sztangi, ale ze względu na większą amplitudę ruchu sprzyja ono rozciąganiu mięśni piersiowych większych. Podczas wykonywania tego ćwiczenia pracuje również mięsień trójgłowy i przednie częsci mięśnia naramiennego.", 4, 4);
        list.add(e22);
        Exercise e23 = new Exercise("Rozpiętki ze sztangielkami w pozycji leżącej", "W pozycji leżącej na wąskiej ławeczce umożliwiającej wykonywanie swobodnych ruchów ramionami, sztangielki trzymane w rękach wyciągniętych w górę lub lekko zgiętych w łokciach w celu odciążenia stawów:  - wykonać wdech a następnie rozłożyć ramiona do poziomu;  - powrócić do pionu, wykonując wydech;  - wykonać niewielki skurcz izometryczny w końcowej fazie ruchu w celu wzmocnienia pracy mostkowo-żebrowej części mięśni piersiowych większych. Tego ćwiczenia nigdy nie wykonuje się z dużymi obciążeniami. Koncentruje ono wysiłek głównie w mięśniach piersiowych większych. Jest to podstawowe ćwiczenie rozwijające mięśnie klatki piersiowej które zwiększa także objętość płuc i świetnie uelastycznia mięśnie.", 4, 5);
        list.add(e23);
        Exercise e24 = new Exercise("Pull-over ze sztangą w pozycji leżącej na poziomej ławeczce", "Ramiona wyciągnięte do góry, sztanga trzymana nachwytem (uchwyt gryfu na szerokości rozstawu barków): - wziąc wdech, nabierając do płuc maksymalnie dużo powietrza i opuścić sztangę za głowę, lekko zginając kończyny górne w łokciach; - wykonać wydecg, powracając do pozycji wyjściowej.  Ćwiczenie to rozwija mięsień piersiowy większy najszerszy grzbietu, jak również mięśnie zębate przednie, mięsień równoległoboczny i mięsień piersiowy mniejszy. Wykonuje się je z niewielkim obciążeniem.", 4, 6);
        list.add(e24);
        Exercise e25 = new Exercise("Podciąganie na drążku trzymanym podchwytem", "W zwisie na drążku, ręce w odwróceniu, rozstawione na szerokość barków:  - wziąć wdech, wypiąć klatkę piersiową i podciągnąc się tak aby broda znalazła się na wysokości drążka; - wykonac wydech w końcowej fazie ruchu. Ćwiczenie to rozwija mięsień najszerszy grzbietu i mięsień obły większy raz umożliwia intensywny trening mięśnia dwugłowego ramienia i mięsnia ramiennego. Z tego względu może zostać włączone do planu treningu ramion. W ćwiczeniu bierze równiez udział mięsien czworoboczny (jego częśc poprzeczna i wstępująca), mięsień równoległoboczny i mięśnie piersiowe.", 5, 1);
        list.add(e25);
        Exercise e26 = new Exercise("Podciąganie na drążku umocowanym na stałe", "W zwisie na drążku, ręce w nawróceniu, rozstawione szeroko: - wziąć wdech i podciągnąć się tak aby klatka piersiowa znalazła się prawie na wysokości drążka;  - wykonać wydech w końcowej fazie ruchu  -powrócić do pozycji wyjściowej kontrolując ruch podczas opadania a następnie powtórzyć całe ćwiczenie. To doskonałe, choć wymagające dość dużej siły, ćwiczenie rozwija mięsień najszerszy grzebitu i mięsień obły większy, a w czsie ściągania łopatek w końcowej fazie podciągania także mięśnie równoległoboczne oraz poprzeczne i wstępujące części mięśnia czworobocznego. Angażuje również mięsień dwugłowy ramienai, mięsień ramienny i mięsień ramienno-promieniowy.", 5, 2);
        list.add(e26);
        Exercise e27 = new Exercise("Przyciąganie drążka wyciągu górnego do karku", "W pozycji siedzącej, twarzą do przyrządu, uda przytrzymywane przez wałki, drążek trzymany nachwytem szeroko rozstawionymi rękami:  - wziąć wdech i przyciągnąć drązek za kark, odwodząc łokcie wzdłuż tułowia; - wykonać wydechw  końcowej fazie ruchu. Jest to doskonałe ćwiczenie na zwiększenie szerokości mięsni grzbietu. Angażuje mięśnie najszersze grzbietu (głównie ich włókna zewnętrzne i dolne) i mięśnie obłe większe. Angażowane są również mięśnie zginacze przedramion (dwugłowy ramienia, ramienny i ramienno-promieniowy), jak również mięśnie równoległoboczne i część wstępująca mięsni czworobocznych  przy czym te dwa ostatnie włączją się w czasie ściągania łopatek. Jest to dobre ćwiczenie dla początkujących.", 5, 3);
        list.add(e27);
        Exercise e28 = new Exercise("Podciąganie sztangielki w klęku podpartym na ławeczce", "Sztangielka trzymana w jednej ręce w półnawróceniu, druga ręka i zgięta w kolanie kończyna dolna oparte na ławeczce; - zablokować grzbiet, wziąć wdech i podciągnąć sztangielkę najwyżej, jak to możliwe, trzymając ramię przy tułowiu i odwodząc mocno łokieć do tyłu; - wyonać wydech w końcowej fazie ruchu. W celu uzyskania maksymalnego napięcia mięśni można lekko skręcic klatkę pirsiową w końcowej fazie podciągania. Ćwiczenie to angażuje głównie mięsień najszerszy grzbietu, mięsień obły większy, tylne części mięśnia naramiennego oraz mięsień czworoboczny i mięsień równoległoboczny ( w końcowej fazie napięcia) a ponadto mięśnie zginacze ramion (dwugłlwy ramienia, ramienny i ramienno-promienowy).", 5, 4);
        list.add(e28);
        Exercise e29 = new Exercise("Podciąganie pionowe sztangi do brody (wąski uchwyt)", "W pozycji stojącej, kończyny dolne w lekkim rozkroku, grzbiet mocno wyprostowany, sztanga trzymana nachwytem (ręce rozstawione na szerokość dłoni lub trochę większą): - wziąć wdech, podciągnąc sztangę wzdłuż tułowia do brody, podnosząc łokcie możliwie najwyżej; - wykonać wydech i kontrolować opuszczanie sztangi tak, aby było ono płynne. Ćwiczenie to angażuje mięśnie czworoboczne, głównie ich część zstępującą, jak również mięśnie naramianne, mięśnie dźwigacze łopatki, mięśnie dwugłowe ramienia, mięśnie ramienne, mięśnie przedramion, brzucha i pośladków oraz mięśnie krzyżowo-lędźwiowe.", 5, 5);
        list.add(e29);
        Exercise e30 = new Exercise("Wniosy barków ze sztangą (SZRUGSY)", "W pozycji stojącej, przodem do położonej na podłoży lub podstawce sztangi, kończyny dolne w lekkim rozkroku: - chwycić sztangę rękami w nawróceniu lub uchwytem odwróconym, jeśli obciążenia są duże (uchwyt nie czerszy niż rozstaw barków)  - mocno wyprostować grzbiet, napiąć mięśnie brzucha i wykonać wznos ramion. Ćwiczenie to roziwja partię zstępującą mięśnia czworobocznego, głównie jego część potyliczno-obojczykową, jak różnież okolice kąta łopatki.", 5, 6);
        list.add(e30);
        Exercise e31 = new Exercise("Półprzysiad ze sztangielkami", "W pozycji stojącej, kończyny dolne w lekkim rozkroku, sztangielki trzymane w rękach, ramiona opadają swobodnie: - patrzeć prosto przed siebie, wziąć wdech, lekko wygiąć grzbiet i zgiąć kolana;  kiedy uda znajdą się w poziomie do podłoża, wyprostować kończyny dolne i powrócić do pozycji wyjściowej; - wykonać wydech. Ćwiczenie to angażuje głównie mięśnie czworogłowe uda i mięśnie pośladkowe.", 6, 1);
        list.add(e31);
        Exercise e32 = new Exercise("Wypychanie ciężaru na suwnicy (uda w pochyleniu)", "W ułożeniu na przyrządzie, grzbiet przylega do oparcia, stopy rozstawione niezbyt szeroko: - wziąc wdech, odblokowac zabezpieczenie przyrządu i najmocniej jak to możliwe zgiąć kolana tak, aby kończyny dolne znalazły się przy klatce piersiowej; - powrócić do pozycji wyjściowej wykonując wydech w końcowej fazie ruchu. Jeśli stopy ułożone są w dolnej części platformy,pracują przede wszystkim mięsnie czworogłoweud,gdy natomiast stopy znajdują się w górnej części platformy wysiłek koncentruje się bardziej na mięśniach pośladkowych i kulszowo-goleniowych. Jeśli stopy są szeroko rozstawione, wysiłek kierowany jest na mięśnie przywodziciele", 6, 2);
        list.add(e32);
        Exercise e33 = new Exercise("Prostowanie kończyn dolnych z wykorzystaniem przyrządu", "W pozycji siedzącej na przyrządzie, ręce zaciśnięte na drążkach lub siedzeniu, aby utrzymać tułów nieruchomo, kończyny dolne zgięte w kolanach, a w okolicach kostek ułożone są wałki;  - wziąć wdech i wyprostować kończyny tak aby ustawić je do poziomu; - wykonać wydech w końcowej fazie ruchu. Jesto do doskonałe ćwiczenie izolowane na mięśnie czworogłowe. Należy zauważyć że im bardziej pochyli się oparcie tym większe będzie tyłopochylenie miednicy. Rozciąga się mocniej mięśnień prosty uda, który jest dwustawową częścią środkową mięśnia czworogłowego, dzięki czemu będzie on intensywiej pracował podczas prostowania kończyn dolnych.", 6, 3);
        list.add(e33);
        Exercise e34 = new Exercise("Ćwiczenie na mięśnie kulszowo-goleniowe w pozycji stojącej z wykorzystaniem przyrządu", "W pozycji stojącej, klatka piersiowa oparta o podstawkę, kolana zablokowane, kończyny dolne wyprostowane, pod kostkami wałki: - wziąc wdech i zgiąć kolano; - wykonać wydech w końcowej fazie ruchu. Ćwiczenie to angażuje wszystkie mięśnie kulszowo-goleniowe (mięśnie półścięgniste i półbłoniaste, głowę krótką i długą mięśnia dwugłowego uda), a w mniejszym stopniu mięśnie brzuchate łydki. Aby mocniej zaangażować te ostatnie mięśnie w wykonywanie ruchu, wystarczy podczas zginania kolana zgiąć stopę, aby zaangażoważ słabiej (do czego zwykle się dązy) należy stopę wyprostować", 6, 4);
        list.add(e34);
        Exercise e35 = new Exercise("Wspięcie na palcach jednej kończyny dolnej ze sztangielką", "W pozycji stojącej, palce stopy wsparte o podstawkę, sztangielka w jednej ręce, druga ręka dla lepszej równowagi oparta na wsporniku: - wspiąć się na palcach (zgięcie podeszwowe), utrzymując staw kolanowy w wyproście lub w lekkim zgięciu; - powrócić do pozycji wyjściowej. Ćwiczenie to angażuje mięsień trójgłowy łydki (zbudowany z mięśnia płaszczkowatego, głowy przyśrodkowej i głowy bocznej mięśnia brzuchatego łydki. Przy każdym powtórzeniu należy wykonać pełne zgięcie, aby dobrze rozciągnąć mięsień trójgłowy łydki. Tylko wykonywanie długich serii ćwiczeń daje najlepsze rezultaty", 6, 5);
        list.add(e35);
        Exercise e36 = new Exercise("Wspięcie na palcach w pozycji siedzącej na przyrządzie (ćwiczenie na mięśnie płaszczkowate)", "W pozycji siedzącej, dolna część ud pod wyścirłanym klockiem, palce stóp na podstawce, pięty lekko opuszczone: - wspiąc się na palcach (zgięcie podeszwowe) Ćwiczenie to angażuje głównie mięsień płaszczkowaty. Mięsień ten ma przyczep u góry, pod stawem kolanowym, na kości piszczelowej i strzałkowej i jest przymocowany do kości piętowej przez ścięgno Achillesa, jego podstawowa funkcja to prostowanie w stawie skokowym.", 6, 6);
        list.add(e36);
        Exercise e37 = new Exercise("Skłony tułowia w przód w pozycji leżącej (CRUNCH)", "W pozycji leżącej, ręce za głową, uda ustawione pionowo do podłoża, kolana ugięte: - wziąć wdech i oderwać ramiona od podłoża, przyciągając kolana do głowy i zginając kręgosłup; - wykonać wdech w końcowej fazie ruchu. Ćwiczenie to angażuje głównie mięsień prosty brzucha. Aby bardziej intensywnie pracowały mięśnie skośne brzucha, wystarczy zgiąć kręgosłup i przyciągnąc naprzemiennie prawy łokieć do lewego kolana i lewy łokieć do prawego kolana.", 7, 1);
        list.add(e37);
        Exercise e38 = new Exercise("Unoszenie tułowia z podłoża", "W pozycji leżącej, kończyny dolne ugięte, stopy na podłodze, ręce za głową: - wziąć wdech i unieść tułów, wyginając kręgosłup w łuk, wykonać wydech w końcowej fazie ćwiczenia; - powrócić do pozycji wyjściowej ale tym razem nie kładąc się całkiem na podłodze; - ponownie unieść tułów ( w okolicy brzucha powinno pojawić się pieczenie) Ćwiczenie to angazuje mięśnie zginacze biodra oraz mięśnie skośne brzucha, ale przede wszystkim mięsnień prosty brzucha.", 7, 2);
        list.add(e38);
        Exercise e39 = new Exercise("Skłony tułowia w przód z wykorzystaniem wyciągu górnego", "W siadzie klęcznym, drążek za karkiem:  wziąć wdech i zgiąć kręgosłup, zbliżając mostek do kości łoniowej; wykonać wydech w końcowej fazie ruchu. Ćwiczenie tego nigdy nie wykonuje się z dużym obciążeniami. Należy skuić się na pracy mięśni, aby lepiej zlokalizować wysiłek w tłoczni brzusznej a głównie w mięśniu prostym brzucha.", 7, 3);
        list.add(e39);
        Exercise e40 = new Exercise("Unoszenie kończyn dolnych na skośnej ławeczce ze zgięciem kręgosłupa i uniesieniem miednicy", "W pozycji leżącej na skośnej ławeczce, ręce na barierce lub drążku: - unieśc kończyny dolne do pionu, następniepodnieść miednicę, zgiąć kręgosłup i próbując dotknąć kolanami głowy. Ćwiczenie to angażuje przy podnoszeniu kończyn mięsień biodrowo-lędźwiowy, mięsień napinacz powięzi szerokiej i mięsień prosty uda ( część mięsnia czworogłowego uda) Podczas zginania kręgosłupa angażowana jest tłocznia brzuszna, głównie część podpępkowa mięśni prostych brzucha.", 7, 4);
        list.add(e40);
        Exercise e41 = new Exercise("Unoszenie kolan na przyrządzie do ćwiczeń na mięśnie brzucha", "W oparciu na łokciach, grzbiet zablokowany: - wziąć wdech i podciągnąc kolana do piersi, wygiąć grzbiet w łuk, aby napiąć mięśnie tłoczni brzusznej;  - wykonać wydech w końcowej fazie ruchu. Ćwiczenie to angażuje mięśnie zginacze biodra, głównie mięśnie biodrowo-lędźwiowe, jak również mięśnie skośne i mięsień prosty brzucha, przy czym ten ostatni jest angażowany intensywnie w swej dolnej części", 7, 5);
        list.add(e41);
        Exercise e42 = new Exercise("Unoszenie kończyn dolnych na zwisie na drążku", "W zwisie na drążku: - wziąć wdech i unieść kolana możliwie najwyżej, starając się zbliżyć kość łoniową do mostka przez zgięcie kręgosłupa ; - wykonać wydech w końcowej fazie ćwiczenia. Ćwiczenie to oddziałuje na mięśnie biodrowo-lędźwiowe, mięsień prosty uda i misień napinacz powięzi szerokiej (podczas unoszenia kończyn dolnych). W celu zlokalizowania pracy w tłoczni brzusznej zaleca się wykonywanie niewielkich poziomych ruchów udami. Kolana nie mogą jednak nigdy opadać poniżej poziomu.", 7, 6);
        list.add(e42);

        int size = 0;

        try{
            final Dao<Exercise,Integer> exercisesDao = getHelper().getExerciseDao();
            for (Exercise e:list
                    ) {
                exercisesDao.create(e);
            }
            list.clear();
            list = exercisesDao.queryForAll();
            size = list.size();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return size;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
    */

}
