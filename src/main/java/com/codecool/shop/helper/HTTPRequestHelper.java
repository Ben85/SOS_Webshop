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

    private HttpURLConnection createHTTPConnection(URL targetURL, String method, HashMap<String, String> requestProperties)
        throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) targetURL.openConnection();
        connection.setRequestMethod(method);

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

    private void sendRequest(OutputStream requestStream, String requestBody)
        throws IOException
    {
        DataOutputStream writer = new DataOutputStream(requestStream);
        writer.writeBytes(requestBody);
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
        String requestBody,
        HashMap<String, String> requestProperties
    )
        throws IOException
    {
        URL url = new URL(targetURL);

        if (requestProperties == null) {
            requestProperties = new HashMap<String, String>() {{
                put("Content-Type", "application/x-www-form-urlencoded");
                put("Content-Length", Integer.toString(requestBody.getBytes().length));
                put("Content-Language", "en-US");
            }};
        }

        HttpURLConnection connection = createHTTPConnection(url, method, requestProperties);

        sendRequest(connection.getOutputStream(), requestBody);

        return getResponse(connection.getInputStream());
    }

    public String executePost(String targetUrl, String requestBody, HashMap<String, String> requestProperties)
        throws IOException
    {
        return executeRequest(
            "POST",
            targetUrl,
            requestBody,
            requestProperties
        );
    }

    public String executeGet(String targetUrl, String requestBody,  HashMap<String, String> requestProperties)
        throws IOException
    {
        return executeRequest(
            "GET",
            targetUrl,
            requestBody,
            requestProperties
        );
    }

    public String executePost(String targetUrl, String requestBody)
        throws IOException
    {
        return executeRequest("POST",
            targetUrl, requestBody,
            null
        );
    }

    public String executeGet(String targetUrl, String requestBody)
        throws IOException
    {
        return executeRequest(
            "GET",
            targetUrl, requestBody,
            null
        );
    }
}
