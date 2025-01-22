package project.dampmdmtarea3cdva.Retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeApiAdapter {

    private static PokeApiService API_SERVICE;

    /**
     * Base URL for the Pokémon API.
     */
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    public static PokeApiService getApiService() {
        if (API_SERVICE == null) {
            // Creamos un interceptor para registrar las solicitudes y respuestas
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Asociamos el interceptor al cliente HTTP
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            // Creamos la instancia de Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Creamos la implementación de la API
            API_SERVICE = retrofit.create(PokeApiService.class);
        }

        return API_SERVICE;
    }
}