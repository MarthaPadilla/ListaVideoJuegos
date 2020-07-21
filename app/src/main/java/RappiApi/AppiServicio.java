package RappiApi;

import Modelo.RespuestaGame;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AppiServicio {
    @GET("games")
    Call <RespuestaGame> obtenerListaGame();

}
