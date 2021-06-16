package data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class URLCreator {

    public static final String address = "getAddress()";
    public static final String answersAddress = address + "/answers";
    public static final String genericTermsAddress = address + "/genericTerms";
    public static final String synonymsAddress = address + "/synonyms";

    public static void sendAnswer(int caseID, String keywords, String answer) throws IOException, InterruptedException {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("caseID", Integer.toString(caseID));
        arguments.put("keywords", keywords);
        arguments.put("answer", answer);
        sendPostRequest(answersAddress, arguments);
    }

    public static void sendGenericTerm(int id, String genericTerm) throws IOException, InterruptedException {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("id", Integer.toString(id));
        arguments.put("generic_term", genericTerm);
        sendPostRequest(genericTermsAddress, arguments);
    }

    public static void sendSynonym(String synonym, int id) throws IOException, InterruptedException {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("synonym", synonym);
        arguments.put("id", Integer.toString(id));
        sendPostRequest(synonymsAddress, arguments);
    }

    private static void sendPostRequest(String address, Map<String, String> arguments) throws IOException, InterruptedException {
        URL url = new URL(address);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String, String> entry: arguments.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                    URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        boolean connect = true;
        while(connect) {
            try {
                http.connect();
                connect = false;
            } catch (BindException e) {
                Thread.sleep(5000);
            }
        }

        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        http.disconnect();
    }

    public static String getAddress() {
        JSONParser parser = new JSONParser();
        String url = null;
        try(Reader reader = new FileReader("src/main/resources/url.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            //System.out.println(jsonObject);

            url = (String) jsonObject.get("url");
            //System.out.println(url);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return url;
    }

}
