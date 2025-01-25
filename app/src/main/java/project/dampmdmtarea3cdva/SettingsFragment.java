package project.dampmdmtarea3cdva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.firebase.ui.auth.AuthUI;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
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
            // Establecer el idioma por defecto
            languagePreference.setDefaultValue(sharedPreferences.getString("language", "es"));

            // Listener para cambiar el idioma
            languagePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                    String languageCode = (String) newValue;
                    // Guardar el nuevo idioma seleccionado
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("language", languageCode);
                    editor.apply();

                    // Cambiar el idioma de la app
                    setLocale(languageCode);
                    // Recargar la actividad para aplicar el cambio de idioma
                    requireActivity().recreate();
                    return true;
                }
            });
        }

        // Configurar la opción de cerrar sesión
        Preference logoutPreference = findPreference("logout");
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
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
                public boolean onPreferenceClick(Preference preference) {
                    onAboutClick();
                    return true;
                }
            });
        }
        // Preferencia de cerrar sesión
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    onLogoutClick();
                    return true;
                }
            });
        }
    }

    // Método para cerrar sesión
    public void onLogoutClick() {
        AuthUI.getInstance()
                .signOut(requireContext())  // Usa el contexto adecuado
                .addOnCompleteListener(task -> {
                    goToLogin();  // Redirige a la pantalla de login
                });
    }
    // Diálogo que da la opción de cerrar sesión
    private void goToLogin() {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.close_login_title))
                .setMessage(getString(R.string.close_login_message))
                .setPositiveButton(getString(R.string.yes_button), (dialog, which) -> {
                    Intent i = new Intent(requireContext(), LoginActivity.class);
                    startActivity(i);
                    requireActivity().finish();
                })
                .setNegativeButton(getString(R.string.no_button), (dialog, which) -> dialog.dismiss())
                .show();
    }
    // Diálogo que muestra la info de la app
    public void onAboutClick() {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.about_title))
                .setMessage(getString(R.string.about_message))
                .setPositiveButton(getString(R.string.close_button), (dialog, which) -> dialog.dismiss())
                .show();
    }

    public void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // Actualizar la configuración de la aplicación
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Aplicar la nueva configuración al contexto
        Context context = requireContext();
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}