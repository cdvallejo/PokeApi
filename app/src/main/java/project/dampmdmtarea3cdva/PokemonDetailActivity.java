package project.dampmdmtarea3cdva;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import project.dampmdmtarea3cdva.databinding.PokemonItemCapturedDetailBinding;

public class PokemonDetailActivity extends AppCompatActivity {

    private PokemonItemCapturedDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializamos el ViewBinding
        binding = PokemonItemCapturedDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el botón para volver atrás
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Cierra la actividad y vuelve a la anterior
            }
        });

        // Recuperar el Pokémon desde el Intent
        PokemonResult pokemon = (PokemonResult) getIntent().getSerializableExtra("pokemon");

        if (pokemon != null) {
            // Mostrar los datos del Pokémon
            binding.pokemonName.setText(pokemon.getName());
            binding.pokemonType.setText(pokemon.getType());
            binding.pokemonWeight.setText(String.valueOf(pokemon.getWeight()));
            binding.pokemonHeight.setText(String.valueOf(pokemon.getHeight()));

            // Cargar la imagen con Picasso
            if (pokemon.getImageUrl() != null && !pokemon.getImageUrl().isEmpty()) {
                Picasso.get().load(pokemon.getImageUrl()).into(binding.pokemonImage);
            }
        } else {
            Toast.makeText(this, "No se pudo cargar el Pokémon", Toast.LENGTH_SHORT).show();
            finish(); // Cerrar la actividad si no hay datos
        }
    }
}