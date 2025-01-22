package project.dampmdmtarea3cdva;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import project.dampmdmtarea3cdva.Retrofit.PokeApiAdapter;
import project.dampmdmtarea3cdva.Retrofit.PokeApiService;
import project.dampmdmtarea3cdva.databinding.FragmentPokedexBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding; // Usamos ViewBinding
    private PokemonAdapter adapter;
    private List<PokemonResult> pokemonResultList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Usamos ViewBinding para inflar el layout
        binding = FragmentPokedexBinding.inflate(inflater, container, false);

        // Configuramos RecyclerView con ViewBinding
        binding.pokedexRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonAdapter(getContext(), pokemonResultList, this::onPokemonClick);
        binding.pokedexRecyclerView.setAdapter(adapter);

        fetchPokemonList();

        return binding.getRoot(); // Usamos el root de ViewBinding para devolver la vista
    }

    private void fetchPokemonList() {
        PokeApiService apiService = PokeApiAdapter.getApiService();
        apiService.getPokemonList(0, 150).enqueue(new Callback<PokemonListResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonResultList.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error cargando Pokémon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onPokemonClick(PokemonResult pokemon) {
            new AlertDialog.Builder(requireContext())
                    .setTitle("¡Pokémon capturado!")
                    .setMessage("Has capturado a " + pokemon.getName())
                    .setPositiveButton("Ok!", (dialog, which) -> {
                        dialog.dismiss(); // Cierra el diálogo
                    })
                    .show();
        // Aquí manejarás la captura del Pokémon

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitamos fugas de memoria
    }
}