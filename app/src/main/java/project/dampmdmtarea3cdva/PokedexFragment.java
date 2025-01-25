package project.dampmdmtarea3cdva;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
    private PokedexAdapter adapter;
    private List<PokemonResult> pokemonResultList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Usamos ViewBinding para inflar el layout
        binding = FragmentPokedexBinding.inflate(inflater, container, false);

        // Configuramos RecyclerView con ViewBinding
        binding.pokedexRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokedexAdapter(getContext(), pokemonResultList, this::onPokemonClick);
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
        // Si clickea en un Pokémon se captura
        capturePokemon(pokemon); // Guardar en Firebase
    }

    private void capturePokemon(PokemonResult pokemon) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Comprobar si ya está capturado
        checkIfCaptured(db, pokemon);
    }

    private void checkIfCaptured(FirebaseFirestore db, PokemonResult pokemon) {
        db.collection("captured_pokemon_db")
                .whereEqualTo("name", pokemon.getName())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        showMessage(R.string.pokemon_already_captured_title,
                                R.string.pokemon_already_captured_message, pokemon.getName());
                    } else {
                        captureAndSavePokemon(db, pokemon);
                    }
                });
    }

    private void captureAndSavePokemon(FirebaseFirestore db, PokemonResult pokemon) {
        db.collection("captured_pokemon_db")
                .add(pokemon)
                .addOnSuccessListener(documentReference ->
                        showMessage(R.string.pokemon_captured_title,
                                R.string.pokemon_captured_message, pokemon.getName()))
                .addOnFailureListener(e ->
                        showMessage(R.string.pokemon_no_captured_title,
                                R.string.pokemon_no_captured_message, null));
    }

    private void showMessage(int titleRes, int messageRes, String pokemonName) {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(titleRes))
                .setMessage(pokemonName != null ? getString(messageRes, pokemonName) : getString(messageRes))
                .setPositiveButton(getString(R.string.ok_button), (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitamos fugas de memoria
    }
}