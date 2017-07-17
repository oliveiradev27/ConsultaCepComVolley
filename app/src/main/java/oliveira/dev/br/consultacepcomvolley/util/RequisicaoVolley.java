package oliveira.dev.br.consultacepcomvolley.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequisicaoVolley {
    private static RequestQueue queue;

    public RequisicaoVolley(Context context) {
        initVolley(context);
    }

    public void initVolley(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void doPost(JSONObject jsonObject) {
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    "",
                    jsonObject,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something...
                        }
                    });

         queue.add(request);
    }

    public void doGet(String url, final ResponseListener listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.run(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        queue.add(stringRequest);
    }
}
