package project.dampmdmtarea3cdva;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.dampmdmtarea3cdva.databinding.PokemonItemCapturedBinding;
public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.CapturedPokemonViewHolder> {

    private List<PokemonResult> capturedPokemonList;

    public CapturedPokemonAdapter(List<PokemonResult> capturedPokemonList) {
        this.capturedPokemonList = capturedPokemonList;
    }

    @NonNull
    @Override
    public CapturedPokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemCapturedBinding binding = PokemonItemCapturedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CapturedPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CapturedPokemonViewHolder holder, int position) {
        PokemonResult pokemon = capturedPokemonList.get(position);

        holder.binding.pokemonName.setText(pokemon.getName());

        // Cargar imagen con Picasso (o cualquier otra librería de imágenes)
        Picasso.get().load(pokemon.getImageUrl()).into(holder.binding.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    public static class CapturedPokemonViewHolder extends RecyclerView.ViewHolder {
        PokemonItemCapturedBinding binding;

        public CapturedPokemonViewHolder(PokemonItemCapturedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}