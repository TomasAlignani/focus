package com.example.focusflow;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.focusflow.data.*;
import com.example.focusflow.models.*;
import com.example.focusflow.presenter.FocusPresenter;
import com.example.focusflow.services.*;
import com.example.focusflow.views.IFocusView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFocusView {

    private FocusPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ISesionDAO sesionDAO = new SesionDAOLocalImpl(this);
        IUsuarioDAO usuarioDAO = new UsuarioDAOLocalImpl(this);
        IDescansoDAO descansoDAO = new DescansoDAOLocalImpl();
        IGestorUbicacion gps = new GpsManager(this);

        SesionService sesionService = new SesionService(sesionDAO, descansoDAO);
        UsuarioService usuarioService = new UsuarioService(usuarioDAO);

        presenter = new FocusPresenter(this, sesionService, usuarioService, null, gps);
    }

    // --- IFocusView (por ahora vacíos, los iremos completando) ---

    @Override
    public String pedirNombreUsuario() { return "UsuarioPrueba"; }

    @Override
    public String pedirObjetivoEstudio() { return "Estudiar Android"; }

    @Override
    public int pedirNivelEstres() { return 5; }

    @Override
    public boolean confirmarInterrupcion() { return false; }

    @Override
    public void simularFoto(String momento) { }

    @Override
    public void mostrarPantallaDescanso(TipoDescanso descanso) {
        Toast.makeText(this, "Descanso: " + descanso.name(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarResumen(List<SesionEstudio> historial) {
        Toast.makeText(this, "Sesiones guardadas: " + historial.size(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}