package project.dampmdmtarea3cdva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import project.dampmdmtarea3cdva.databinding.FragmentCapturedBinding;

public class CapturedFragment extends Fragment {

    private FragmentCapturedBinding binding;
    private List<PokemonResult> capturedPokemonList = new ArrayList<>();  // Lista de Pokémon capturados

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout con ViewBinding
        binding = FragmentCapturedBinding.inflate(inflater, container, false);

        // Configurar el RecyclerView
        RecyclerView recyclerView = binding.recyclerViewCaptured;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear el adaptador y asignarlo al RecyclerView
        CapturedPokemonAdapter adapter = new CapturedPokemonAdapter(capturedPokemonList);
        recyclerView.setAdapter(adapter);

        // Cargar Pokémon capturados desde Firestore
        loadCapturedPokemon();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Método para cargar Pokémon capturados desde Firestore
    private void loadCapturedPokemon() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("captured_pokemon")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Convertir los documentos de Firestore a objetos PokemonResult
                    List<PokemonResult> capturedPokemonListFromFirestore = queryDocumentSnapshots.toObjects(PokemonResult.class);

                    // Actualizar la lista de Pokémon capturados
                    capturedPokemonList.clear();  // Limpiar la lista antes de agregar los nuevos
                    capturedPokemonList.addAll(capturedPokemonListFromFirestore);

                    // Notificar al adaptador que los datos han cambiado
                    ((CapturedPokemonAdapter) binding.recyclerViewCaptured.getAdapter()).notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al cargar Pokémon capturados", Toast.LENGTH_SHORT).show();
                });
    }

    // Método para agregar un Pokémon a la lista de capturados
    public void capturePokemon(PokemonResult pokemon) {
        capturedPokemonList.add(pokemon);
        if (getView() != null) {
            // Notificar al adaptador que los datos han cambiado
            ((CapturedPokemonAdapter) binding.recyclerViewCaptured.getAdapter()).notifyDataSetChanged();
        }
    }
}