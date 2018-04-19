package com.codecool.shop.helper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Singleton class for managing http requests:
public class HTTPRequestHelper {
    private HTTPRequestHelper() {}

    private static HTTPRequestHelper singletonInstance = new HTTPRequestHelper();

    public static HTTPRequestHelper getInstance() {
        return singletonInstance;
    }

    private HttpURLConnection createHTTPConnection(URL targetURL, HashMap<String, String> requestProperties)
        throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) targetURL.openConnection();;

        for (Map.Entry<String, String> property : requestProperties.entrySet()) {
            connection.setRequestProperty(
                property.getKey(),
                property.getValue()
            );
        }

        connection.setUseCaches(false);
        connection.setDoOutput(true);

        return connection;
    }

    private void sendRequest(OutputStream requestStream, String urlParameters)
        throws IOException
    {
        DataOutputStream writer = new DataOutputStream(requestStream);
        writer.writeBytes(urlParameters);
        writer.close();
    }

    private String getResponse(InputStream responseStream)
        throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        reader.close();
        return response.toString();
    }

    private String executeRequest(
        String method,
        String targetURL,
        String urlParameters,
        HashMap<String, String> requestProperties
    )
        throws IOException
    {
        URL url = new URL(targetURL);

        if (requestProperties == null) {
            requestProperties = new HashMap<String, String>() {{
                put("Content-Type", "application/x-www-form-urlencoded");
                put("Content-Length", Integer.toString(urlParameters.getBytes().length));
                put("Content-Language", "en-US");
            }};
        }

        HttpURLConnection connection = createHTTPConnection(url, requestProperties);

        sendRequest(connection.getOutputStream(), urlParameters);

        return getResponse(connection.getInputStream());
    }

    public String executePost(String targetUrl, String[] urlParameters, HashMap<String, String> requestProperties)
        throws IOException
    {
        return executeRequest(
            "POST",
            targetUrl,
            String.join(",", urlParameters),
            requestProperties
        );
    }

    public String executeGet(String targetUrl, String[] urlParameters,  HashMap<String, String> requestProperties)
        throws IOException
    {
        return executeRequest(
            "GET",
            targetUrl,
            String.join(",", urlParameters), requestProperties
        );
    }

    public String executePost(String targetUrl, String[] urlParameters)
        throws IOException
    {
        return executeRequest("POST",
            targetUrl, String.join(",", urlParameters),
            null
        );
    }

    public String executeGet(String targetUrl, String[] urlParameters)
        throws IOException
    {
        return executeRequest(
            "GET",
            targetUrl, String.join(",", urlParameters),
            null
        );
    }
}
