
package project.dampmdmtarea3cdva.Retrofit;
import project.dampmdmtarea3cdva.PokemonListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService {

    // Método para obtener detalles del Pokémon, incluyendo su imagen

    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList(
            @Query("offset") int offset,
            @Query("limit") int limit
    );

}