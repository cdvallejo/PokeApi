package project.dampmdmtarea3cdva;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import project.dampmdmtarea3cdva.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializamos el ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el NavHostFragment del FragmentManager
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        // Verificamos si el fragmento es nulo antes de usarlo
        if (navHostFragment != null) {
            // Obtener el NavController del NavHostFragment
            navController = navHostFragment.getNavController();
        }

        // Configurar el BottomNavigationView con el NavController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}