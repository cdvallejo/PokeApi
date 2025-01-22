package project.dampmdmtarea3cdva;

import com.google.gson.annotations.SerializedName;

public class PokemonResult {
    private String name;

    @SerializedName("sprites")  // El campo que contiene las imágenes en la respuesta JSON
    private Sprites sprites;

    // Constructor
    public PokemonResult(String name, Sprites sprites) {
        this.name = name;
        this.sprites = sprites;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String getImageUrl() {
        // Asumimos que la imagen está dentro de sprites -> other -> official_artwork -> front_default
        return sprites.getOther().getOfficialArtwork().getFrontDefault();
    }

    // Clase interna para manejar las imágenes
    public static class Sprites {
        @SerializedName("other")
        private OtherSprites other;

        public OtherSprites getOther() {
            return other;
        }

        public void setOther(OtherSprites other) {
            this.other = other;
        }

        public static class OtherSprites {
            @SerializedName("official-artwork")
            private OfficialArtwork officialArtwork;

            public OfficialArtwork getOfficialArtwork() {
                return officialArtwork;
            }

            public void setOfficialArtwork(OfficialArtwork officialArtwork) {
                this.officialArtwork = officialArtwork;
            }

            public static class OfficialArtwork {
                @SerializedName("front_default")
                private String frontDefault;

                public String getFrontDefault() {
                    return frontDefault;
                }

                public void setFrontDefault(String frontDefault) {
                    this.frontDefault = frontDefault;
                }
            }
        }
    }
}