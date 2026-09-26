package joseblanco.convertidordemoneda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class actividad_principal extends AppCompatActivity {

    // Unidades de cada moneda por 1 USD (tasas de referencia fijas)
    private final Map<String, Double> tasasPorUsd = new HashMap<>();

    private EditText campoMonto;
    private Spinner listaOrigen;
    private Spinner listaDestino;
    private TextView etiResultado;

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

        tasasPorUsd.put("USD", 1.0);
        tasasPorUsd.put("COP", 4000.0);
        tasasPorUsd.put("EUR", 0.92);
        tasasPorUsd.put("MXN", 17.5);
        tasasPorUsd.put("GBP", 0.79);

        campoMonto = findViewById(R.id.campoMonto);
        listaOrigen = findViewById(R.id.listaOrigen);
        listaDestino = findViewById(R.id.listaDestino);
        etiResultado = findViewById(R.id.etiResultado);
        Button botonConvertir = findViewById(R.id.botonConvertir);

        botonConvertir.setOnClickListener(this::convertir);
    }

    // METODO QUE CONVIERTE EL MONTO Y MUESTRA EL RESULTADO
    public void convertir(View control) {
        String textoMonto = campoMonto.getText().toString();
        if (textoMonto.isEmpty()) {
            Toast.makeText(this, "Ingresa un monto", Toast.LENGTH_SHORT).show();
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(textoMonto);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Monto invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        String origen = listaOrigen.getSelectedItem().toString();
        String destino = listaDestino.getSelectedItem().toString();

        if (!tasasPorUsd.containsKey(origen) || !tasasPorUsd.containsKey(destino)) {
            Toast.makeText(this, "Moneda sin tasa registrada", Toast.LENGTH_SHORT).show();
            return;
        }

        double montoEnUsd = monto / tasasPorUsd.get(origen);
        double resultado = montoEnUsd * tasasPorUsd.get(destino);

        etiResultado.setText(getString(R.string.formato_resultado, monto, origen, resultado, destino));
    }
}
