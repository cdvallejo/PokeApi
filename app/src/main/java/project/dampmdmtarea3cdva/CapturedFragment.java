package project.dampmdmtarea3cdva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import project.dampmdmtarea3cdva.databinding.FragmentCapturedBinding;

public class CapturedFragment extends Fragment {

    private FragmentCapturedBinding binding;
    private List<PokemonResult> capturedPokemonList = new ArrayList<>(); // Lista de Pokémon capturados
    private CapturedPokemonAdapter adapter; // Adaptador para el RecyclerView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout con ViewBinding
        binding = FragmentCapturedBinding.inflate(inflater, container, false);

        // Configurar RecyclerView
        adapter = new CapturedPokemonAdapter(capturedPokemonList);
        binding.recyclerViewCaptured.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCaptured.setAdapter(adapter);

        // Cargar los Pokémon capturados desde Firebase
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
        db.collection("captured_pokemon_db") // Asegúrate de que el nombre coincida con tu colección en Firestore
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Convertir los documentos de Firestore a objetos PokemonResult
                    capturedPokemonList.clear();
                    capturedPokemonList.addAll(queryDocumentSnapshots.toObjects(PokemonResult.class));

                    // Notificar al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al cargar Pokémon capturados", Toast.LENGTH_SHORT).show();
                });
    }
}