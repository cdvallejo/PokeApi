package project.dampmdmtarea3cdva;

import java.util.List;

public class PokemonListResponse {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResult> results;

    public List<PokemonResult> getResults() {
        return results;
    }
}
