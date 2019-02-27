package com.caiw.config;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


public class APIClientManager {

    private static APIClientManager apiClientManager = new APIClientManager();

    public static APIClientManager getInstance() {
        return apiClientManager;
    }

    private static Logger logger = LoggerFactory.getLogger(APIClientManager.class);
    private static ConcurrentMap<String, OkHttpClient> cache = new ConcurrentHashMap<String, OkHttpClient>();

    public static OkHttpClient getAPIClient(APIConfig config) {
        OkHttpClient okHttpClient = cache.get(config.getId());
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(25, TimeUnit.SECONDS)
                    .authenticator(new Authenticator() {
                        @Override public Request authenticate(Route route, Response response) throws IOException {
                            logger.info("Authenticating for response: " + response);
                            logger.debug("Challenges: " + response.challenges());
                            String credential = Credentials.basic(config.getUser(), config.getPassword());

                            logger.debug("credential = " + credential);
                            if (credential.equals(response.request().header("Authorization"))) {
                                logger.error("验证失败，返回null");
                                return null; // If we already failed with these credentials, don't retry.
                            }

                            if (responseCount(response) >= 3) {
                                return null; // If we've failed 3 times, give up.
                            }

                            return response.request().newBuilder()
                                    .header("Authorization", credential)
                                    .build();
                        }
                    })
                    .build();
            cache.put(config.getId(), okHttpClient);
        }
        if (okHttpClient == null) {
            logger.error("found okHttpClient");
        }
        return okHttpClient;
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;

        }
        return result;
    }
}
