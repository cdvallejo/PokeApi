package project.dampmdmtarea3cdva;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.dampmdmtarea3cdva.databinding.PokemonItemBinding;  // Importar ViewBinding adecuado

public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.ViewHolder> {

    private List<PokemonResult> capturedPokemonList;  // Lista de Pokémon capturados

    public CapturedPokemonAdapter(List<PokemonResult> capturedPokemonList) {
        this.capturedPokemonList = capturedPokemonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Usamos ViewBinding para inflar la vista del item
        PokemonItemBinding binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokemonResult pokemon = capturedPokemonList.get(position);
        holder.binding.pokemonName.setText(pokemon.getName());
        Picasso.get().load(pokemon.getImageUrl()).into(holder.binding.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemBinding binding;

        public ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}