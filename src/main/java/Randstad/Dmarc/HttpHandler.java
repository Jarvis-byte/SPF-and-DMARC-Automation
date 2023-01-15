package Randstad.Dmarc;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class HttpHandler {

    public void GetRequest(ArrayList<Data> list) throws IOException {
        System.out.println(list.size());

        for (int a = 1; a < list.size(); a++) {
            System.out.println(list.get(a).getDomainName());
            getSpfPolicy(list, a);
            getDmarcPolicy(list, a);
            // System.out.println(list.get(a).getDMARCPolicy());

        }
        ExportToExcel exportToExcel = new ExportToExcel();
        exportToExcel.export(list);
    }

    private void getDmarcPolicy(ArrayList<Data> list, int a) throws IOException {
        String domain = list.get(a).getDomainName();
        URL url = new URL("https://dns.google/resolve?name=_dmarc." + domain + "&type=TXT");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();
        String inline;
        if (responsecode != 200) {
            list.get(a).setDMARCPolicy("ERROR");
            return;
        } else {

            inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();
        }

        JSONParser parse = new JSONParser();
        try {
            JSONObject data_obj = (JSONObject) parse.parse(inline);
            //Get the required object from the above created object
            if (data_obj.containsKey("Answer")) {
                org.json.simple.JSONArray answer = (org.json.simple.JSONArray) data_obj.get("Answer");

                for (int i = 0; i < answer.size(); i++) {
                    JSONObject ansObj = (JSONObject) answer.get(i);
                    String str = String.valueOf(ansObj);
                    if (str.contains("v=DMARC1")) {
                        list.get(a).setDMARCPolicy(String.valueOf(ansObj.get("data")));
                        break;
                    } else {
                        list.get(a).setDMARCPolicy("No DMARC Record");
                    }

                }
            } else {
                list.get(a).setDMARCPolicy("No DMARC Record");
            }


        } catch (ParseException e) {
            System.out.println(e.toString());
            // throw new RuntimeException(e);
        }

    }

    public void getSpfPolicy(ArrayList<Data> list, int a) throws IOException {
        String domain = list.get(a).getDomainName();
        URL url = new URL("https://dns.google/resolve?name=" + domain + "&type=TXT");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

//Getting the response code
        int responsecode = conn.getResponseCode();
        String inline;
        if (responsecode != 200) {
            list.get(a).setSpfPolicy("ERROR");
            return;
        } else {

            inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();
        }
        //  System.out.println(inline);
        JSONParser parse = new JSONParser();

        try {
            JSONObject data_obj = (JSONObject) parse.parse(inline);
            //Get the required object from the above created object
            if (data_obj.containsKey("Answer")) {
                org.json.simple.JSONArray answer = (org.json.simple.JSONArray) data_obj.get("Answer");

                for (int i = 0; i < answer.size(); i++) {
                    JSONObject ansObj = (JSONObject) answer.get(i);
                    String str = String.valueOf(ansObj);
                    if (str.contains("v=spf1")) {
                        list.get(a).setSpfPolicy(String.valueOf(ansObj.get("data")));
                        break;
                    } else {
                        list.get(a).setSpfPolicy("No SPF Record");
                    }

                }
            } else {
                list.get(a).setSpfPolicy("No SPF Record");
            }


        } catch (ParseException e) {
            System.out.println(e.toString());
            // throw new RuntimeException(e);
        }
    }
}