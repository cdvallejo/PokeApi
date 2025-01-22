package project.dampmdmtarea3cdva;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.firebase.ui.auth.AuthUI;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Log.d("SettingsFragment", "Cargando preferencias...");
        // Cargar las preferencias desde el archivo XML
        setPreferencesFromResource(R.xml.settings, rootKey);

        // Obtener las preferencias
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());

        // Configurar el Switch de eliminación
        SwitchPreferenceCompat switchAllowDeletion = findPreference("allow_deletion");
        if (switchAllowDeletion != null) {
            switchAllowDeletion.setDefaultValue(sharedPreferences.getBoolean("allow_deletion", false));
        }

        // Configurar la preferencia de idioma
        ListPreference languagePreference = findPreference("language");
        if (languagePreference != null) {
            languagePreference.setDefaultValue(sharedPreferences.getString("language", "es"));
        }

        // Configurar la opción de cerrar sesión
        Preference logoutPreference = findPreference("logout");
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    onLogoutClick();
                    return true;
                }
            });
        }

        // Configurar la opción de Acerca de
        Preference aboutPreference = findPreference("about");
        if (aboutPreference != null) {
            aboutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    onAboutClick();
                    return true;
                }
            });
        }
    }

    // Lógica para cerrar sesión (como redirigir a la pantalla de login)
    private void onLogoutClick() {
        AuthUI.getInstance()
                .signOut(requireContext()) // Usa el contexto adecuado
                .addOnCompleteListener(task -> {
                    goToLogin(); // Llama al método para loguearse
                });
    }

    private void goToLogin() {
        Intent i = new Intent(requireContext(), LoginActivity.class); // Usa el contexto del fragmento
        startActivity(i);
        requireActivity().finish(); // Finaliza la actividad que contiene el fragmento
    }


    private void onAboutClick() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Acerca de")
                .setMessage("Esta aplicación ha sido desarrollada por Carlos Vallejo.")
                .setPositiveButton("Cerrar", (dialog, which) -> {
                    dialog.dismiss(); // Cierra el diálogo
                })
                .show();
    }

}
