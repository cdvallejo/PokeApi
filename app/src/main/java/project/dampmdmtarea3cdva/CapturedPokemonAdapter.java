package project.dampmdmtarea3cdva;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

package project.dampmdmtarea3cdva;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.dampmdmtarea3cdva.databinding.ItemCapturedPokemonBinding;

public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.CapturedPokemonViewHolder> {

    private List<PokemonResult> capturedPokemonList;

    public CapturedPokemonAdapter(List<PokemonResult> capturedPokemonList) {
        this.capturedPokemonList = capturedPokemonList;
    }

    @NonNull
    @Override
    public CapturedPokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout item_captured_pokemon.xml que contiene el CardView
        ItemCapturedPokemonBinding binding = ItemCapturedPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CapturedPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CapturedPokemonViewHolder holder, int position) {
        PokemonResult pokemon = capturedPokemonList.get(position);

        holder.binding.pokemonName.setText(pokemon.getName());
        holder.binding.pokemonTypes.setText(pokemon.getFormattedTypes());
        holder.binding.pokemonWeight.setText("Peso: " + pokemon.getWeight() + " kg");
        holder.binding.pokemonHeight.setText("Altura: " + pokemon.getHeight() + " m");

        // Cargar imagen con Picasso
        Picasso.get().load(pokemon.getImageUrl()).into(holder.binding.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    public static class CapturedPokemonViewHolder extends RecyclerView.ViewHolder {
        ItemCapturedPokemonBinding binding;

        public CapturedPokemonViewHolder(ItemCapturedPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
