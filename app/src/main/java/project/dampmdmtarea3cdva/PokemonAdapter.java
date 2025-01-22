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

        // Comprobamos si los objetos intermedios son null antes de acceder a ellos
        if (pokemon.getSprites() != null && pokemon.getSprites().getOther() != null
                && pokemon.getSprites().getOther().getOfficialArtwork() != null) {

            String imageUrl = pokemon.getSprites().getOther().getOfficialArtwork().getFrontDefault();
            if (imageUrl != null) {
                Picasso.get().load(imageUrl).into(holder.binding.pokemonImage);
            } else {
                // Cargar una imagen predeterminada si la URL de la imagen es null
                holder.binding.pokemonImage.setImageResource(R.drawable.logo_autenticar);
            }
        } else {
            // Si alguno de los objetos es null, cargar una imagen predeterminada
            holder.binding.pokemonImage.setImageResource(R.drawable.logo_autenticar);
        }

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
