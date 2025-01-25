package project.dampmdmtarea3cdva;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import project.dampmdmtarea3cdva.databinding.PokemonItemCapturedBinding;
public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.ViewHolder> {

    private final List<PokemonResult> capturedPokemonList;

    public CapturedPokemonAdapter(List<PokemonResult> capturedPokemonList) {
        this.capturedPokemonList = capturedPokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usa el View Binding para inflar el layout del elemento
        PokemonItemCapturedBinding binding = PokemonItemCapturedBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtén el Pokémon actual
        PokemonResult pokemon = capturedPokemonList.get(position);

        // Asigna los datos al TextView usando binding
        holder.binding.pokemonName.setText(pokemon.getName());
        if (pokemon.getImageUrl() != null && !pokemon.getImageUrl().isEmpty()) {
            Picasso.get()
                    .load(pokemon.getImageUrl())
                    .into(holder.binding.pokemonImage);
        } else {
            holder.binding.pokemonImage.setImageResource(R.drawable.logo_autenticar);
        }
    }

    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemCapturedBinding binding;

        public ViewHolder(@NonNull PokemonItemCapturedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
