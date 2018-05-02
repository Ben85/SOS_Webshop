package com.codecool.paypal.helper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {
    private HTTPClient() {}

    private static HTTPClient singletonInstance = new HTTPClient();

    public static HTTPClient getInstance() {
        return singletonInstance;
    }

    private HttpsURLConnection createHTTPConnection(URL targetURL, String method, HashMap<String, String> requestProperties)
        throws IOException
    {
        HttpsURLConnection connection = (HttpsURLConnection) targetURL.openConnection();
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
        writer.write(requestBody.getBytes("UTF-8"));
        writer.flush();
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
                put("Content-Language", "en-US");
            }};
        }
        requestProperties.put("Content-Length", Integer.toString(requestBody.getBytes().length));

        HttpsURLConnection connection = createHTTPConnection(url, method, requestProperties);

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
