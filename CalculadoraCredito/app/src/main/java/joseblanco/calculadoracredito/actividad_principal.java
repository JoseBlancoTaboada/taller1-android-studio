package joseblanco.calculadoracredito;

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

    private EditText campoCredito;
    private EditText campoCuotas;
    private EditText campoInteres;
    private TextView etiCuota;
    private TextView etiTotal;
    private TextView etiGanancia;

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

        campoCredito = findViewById(R.id.campoCredito);
        campoCuotas = findViewById(R.id.campoCuotas);
        campoInteres = findViewById(R.id.campoInteres);
        etiCuota = findViewById(R.id.etiCuota);
        etiTotal = findViewById(R.id.etiTotal);
        etiGanancia = findViewById(R.id.etiGanancia);
        Button botonCalcular = findViewById(R.id.botonCalcular);

        botonCalcular.setOnClickListener(this::calcular);
    }

    // METODO QUE CALCULA LA CUOTA, EL TOTAL Y LA GANANCIA DEL CREDITO
    public void calcular(View control) {
        String textoCredito = campoCredito.getText().toString();
        String textoCuotas = campoCuotas.getText().toString();
        String textoInteres = campoInteres.getText().toString();

        if (textoCredito.isEmpty() || textoCuotas.isEmpty() || textoInteres.isEmpty()) {
            Toast.makeText(this, "Completa los tres campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double credito;
        int cuotas;
        double interesPorcentaje;
        try {
            credito = Double.parseDouble(textoCredito);
            cuotas = Integer.parseInt(textoCuotas);
            interesPorcentaje = Double.parseDouble(textoInteres);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Revisa que los valores sean numeros validos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (credito <= 0 || cuotas <= 0) {
            Toast.makeText(this, "El credito y las cuotas deben ser mayores a 0", Toast.LENGTH_SHORT).show();
            return;
        }

        double interes = interesPorcentaje / 100;
        double cuota = (credito / cuotas) + (credito * interes);
        double total = cuota * cuotas;
        double ganancia = total - credito;

        etiCuota.setText(getString(R.string.formato_cuota, cuota));
        etiTotal.setText(getString(R.string.formato_total, total));
        etiGanancia.setText(getString(R.string.formato_ganancia, ganancia));
    }
}
