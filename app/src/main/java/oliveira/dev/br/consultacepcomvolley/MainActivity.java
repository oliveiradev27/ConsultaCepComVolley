package oliveira.dev.br.consultacepcomvolley;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import oliveira.dev.br.consultacepcomvolley.util.EventListener;
import oliveira.dev.br.consultacepcomvolley.util.RequisicaoVolley;
import oliveira.dev.br.consultacepcomvolley.util.ResponseListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getView(View view) {
        EditText cepEditText        = (EditText) findViewById(R.id.cepEditText);
        Button pesquisarCepButton   = (Button) findViewById(R.id.pesquisarCepButton);

        if (view.getId() == pesquisarCepButton.getId()) {
            if (!cepEditText.getText().toString().isEmpty()) {
                ResponseListener listener = new ResponseListener(){
                    @Override
                    public void run(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            AlertDialog.Builder  dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("Retorno");
                            dialog.setMessage(jsonObject.toString());
                            dialog.show();
                        } catch (JSONException e) {
                            Log.e("Erro JSON retorno", e.getMessage());
                        }
                    }
                };
                String url = "https://viacep.com.br/ws/"+cepEditText.getText()+"/json/";
                RequisicaoVolley requisicao = new RequisicaoVolley(MainActivity.this);
                requisicao.doGet(url, listener);

            } else {
                AlertDialog.Builder  dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Erro do sistema");
                dialog.setMessage("Preencha o campo!");
                dialog.show();
            }

        }
    }
}
