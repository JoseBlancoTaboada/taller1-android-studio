package joseblanco.ejemplosimple;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class actividad_principal extends AppCompatActivity {

    private EditText editDato;
    private TextView etiMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.actividad_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editDato = findViewById(R.id.campoDato);
        etiMensaje = findViewById(R.id.etiMensaje);
        Button btnAceptar = findViewById(R.id.botonOk);

        btnAceptar.setOnClickListener(this::mostrarMensaje);
    }

    // METODO QUE MUESTRA EL MENSAJE EN PANTALLA
    public void mostrarMensaje(View control) {
        String dato = editDato.getText().toString();
        if (!dato.isEmpty()) {
            etiMensaje.setText(getString(R.string.formato_mensaje, dato));
            Toast.makeText(this, "Dato recibido", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, ingresa un dato", Toast.LENGTH_SHORT).show();
        }
    }
}