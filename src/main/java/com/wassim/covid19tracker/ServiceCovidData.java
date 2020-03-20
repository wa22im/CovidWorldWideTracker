package com.wassim.covid19tracker;

import com.wassim.covid19tracker.model.Cordinations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Getter
@Setter

@Service
public class ServiceCovidData {

    private static String   virusDataUrl =
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

     private ArrayList<Cordinations> cordinationsList = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {

        ArrayList<Cordinations> newCordinations = new ArrayList<>( ) ;





        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create(virusDataUrl))
                .build() ;
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(httpResponse.body());

        StringReader csvBuddyReady = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBuddyReady);
        for (CSVRecord record : records) {
            String state = record.get("Province/State");
            String country =record.get("Country/Region");
            Cordinations cordinations = new Cordinations() ;

            int numberofCases = Integer.parseInt(record.get(record.size()-1));

            cordinations.setCountry(country);
            cordinations.setState(state);
            cordinations.setNumberOfCases(numberofCases);
            newCordinations.add(cordinations);

           /* String customerNo = record.get("CustomerNo");
            String name = record.get("Name");*/
        }
        cordinationsList=newCordinations ;

    }
}
