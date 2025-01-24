package project.dampmdmtarea3cdva;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import project.dampmdmtarea3cdva.databinding.PokemonItemCapturedBinding;

public class PokemonDetailActivity extends AppCompatActivity {

    private PokemonItemCapturedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializamos el ViewBinding
        binding = PokemonItemCapturedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el Pokémon de la Intent
        PokemonResult pokemon = (PokemonResult) getIntent().getSerializableExtra("pokemon");

        if (pokemon != null) {
            binding.pokemonName.setText(pokemon.getName());
            Picasso.get().load(pokemon.getImageUrl());
        }
    }
}