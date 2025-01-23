package project.dampmdmtarea3cdva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.dampmdmtarea3cdva.databinding.PokemonItemBinding;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private Context context;
    private List<PokemonResult> pokemonList;
    private OnItemClickListener onItemClickListener;

    public PokemonAdapter(Context context, List<PokemonResult> pokemonList, OnItemClickListener onItemClickListener) {
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
        holder.binding.pokemonName.setText(pokemon.getName());

        // Construir la URL de la imagen manualmente (basado en el ID del Pokémon)
        int pokemonId = position + 1; // La lista es ordenada por ID
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonId + ".png";

        // Cargar la imagen con Picasso
        Picasso.get().load(imageUrl).into(holder.binding.pokemonImage);

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
}
