package project.dampmdmtarea3cdva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

import project.dampmdmtarea3cdva.databinding.PokemonItemBinding;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder> {

    private Context context;
    private List<PokemonResult> pokemonList;
    private OnItemClickListener onItemClickListener;

    public PokedexAdapter(Context context, List<PokemonResult> pokemonList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.pokemonList = pokemonList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Usamos ViewBinding para inflar la vista
        PokemonItemBinding binding = PokemonItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        PokemonResult pokemon = pokemonList.get(position);
        // Construir la URL de la imagen (basado en el ID del Pokémon)
        int pokemonId = position + 1; // La lista es ordenada por ID
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonId + ".png";

        // Cargar la imagen con Picasso con transparencia dependiendo de si está capturado el Pokémon
        // Use the asynchronous isPokemonCaptured method
        isPokemonCaptured(pokemon, isCaptured -> {
            if (isCaptured) {
                // Si está cazado, ponemos transparencia
                holder.binding.pokemonName.setText(pokemon.getName());
                holder.binding.pokemonName.setAlpha(0.5f);  // Establece la transparencia
                Picasso.get().load(imageUrl).into(holder.binding.pokemonImage);
                holder.binding.pokemonImage.setAlpha(0.5f); // Transparent for captured Pokémon
            } else {
                // Si no, se muestra normal
                holder.binding.pokemonName.setText(pokemon.getName());
                holder.binding.pokemonName.setAlpha(1.0f);
                Picasso.get().load(imageUrl).into(holder.binding.pokemonImage);
                holder.binding.pokemonImage.setAlpha(1.0f);
            }
        });

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(pokemon));
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemBinding binding;

        public PokemonViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PokemonResult pokemon);
    }

    private void isPokemonCaptured(PokemonResult pokemon, OnPokemonCapturedListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("captured_pokemon_db")
                .whereEqualTo("name", pokemon.getName())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // If the Pokémon is found in the captured collection
                        listener.onCaptured(true);
                    } else {
                        // If the Pokémon is not found
                        listener.onCaptured(false);
                    }
                });
    }

    public interface OnPokemonCapturedListener {
        void onCaptured(boolean isCaptured);
    }
}
