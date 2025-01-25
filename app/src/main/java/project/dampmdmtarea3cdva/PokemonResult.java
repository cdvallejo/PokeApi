package project.dampmdmtarea3cdva;

import java.util.List;

public class PokemonResult {
    private String name;
    private int id;
    private double weight;
    private double height;
    private String type;

    // Constructor vacío (Firebase lo necesita para deserializar)
    public PokemonResult() {
    }
    // Constructor completo
    public PokemonResult(String name, int id, int weight, int height, String type) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.type = type;
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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Generar la URL de la imagen directamente a partir del ID
    public String getImageUrl() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + getId() + ".png";
    }

}