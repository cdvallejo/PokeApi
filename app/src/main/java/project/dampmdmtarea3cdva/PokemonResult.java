package project.dampmdmtarea3cdva;

import java.util.List;

public class PokemonResult {
    private String name;
    private int id; // Índice de la Pokédex
    private double weight; // Peso del Pokémon
    private double height; // Altura del Pokémon
    private List<String> types; // Tipos del Pokémon (por ejemplo, "Fuego", "Agua")

    // Constructor vacío requerido por Firestore
    public PokemonResult() {}

    // Constructor completo
    public PokemonResult(String name, int id, double weight, double height, List<String> types) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.types = types;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    // Generar la URL de la imagen directamente a partir del ID
    public String getImageUrl() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
    }

}