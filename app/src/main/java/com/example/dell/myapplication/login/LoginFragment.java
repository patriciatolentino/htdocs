package com.example.dell.myapplication.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.myapplication.MainActivity;
import com.example.dell.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText UserName, UserPassword;
    private Button btnLogin;
    OnLoginFormActivityListener loginFormActivityListener;

    public  interface OnLoginFormActivityListener
    {
        public void performLogin(String name);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        UserName = view.findViewById(R.id.username);
        UserPassword = view.findViewById(R.id.password);
        btnLogin = view.findViewById(R.id.btnLogIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity =  (Activity )context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;

    }

    private void performLogin() {
        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();

        Call<User1> call = MainActivity.apiInterface.performUserLogin(username, password);
        call.enqueue(new Callback<User1>() {
            @Override
            public void onResponse(Call<User1> call, Response<User1> response) {
                if(response.body().getResponse().equals("ok")){

                 // MainActivity.prefConfig.writeLoginStatus(true);
                   String id  =  String.valueOf(response.body().getId());
                  // loginFormActivityListener.performLogin(id);

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("ID", response.body().getId());
                    editor.apply();
                    System.out.println("TOLENTINO" + response.body().getId());

                    //Intent intent = new Intent(getActivity(), OptionalMessage.class);
                    //startActivity(intent);

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    WelcomeFragment llf = new WelcomeFragment();
                    ft.replace(R.id.fragment_container, llf);
                    ft.commit();
                    Toast.makeText(getContext(),"SUCCESS", Toast.LENGTH_SHORT).show();

                }else{
                    MainActivity.prefConfig.displpayToast("Login Failed");
                    Toast.makeText(getContext(),response.body().getResponse(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User1> call, Throwable t) {
                System.out.println("pat123  " + t.getMessage());
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        UserName.setText("");
        UserPassword.setText("");
    }
}
 