package com.jepsolucoes.fansmarvel.view.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jepsolucoes.fansmarvel.R;
import com.squareup.picasso.Picasso;

public class CharacterActivity extends AppCompatActivity {

    private ImageView imageChar2;
    private TextView textName2;
    private TextView textDescription2;
    private String name;
    private String description;
    private String path;
    private String extension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            name = bundle.getString("name");
            description = bundle.getString("description");
            path = bundle.getString("path");
            extension = bundle.getString("extension");
        }
        textName2 = findViewById(R.id.textName2);
        textDescription2 = findViewById(R.id.textDescription2);
        imageChar2 = findViewById(R.id.imageChar2);

        textName2.setText(name);
        if(description.isEmpty()){
            textDescription2.setText("Sorry! We do not have description of this character");
        }else{
            textDescription2.setText(description);
        }
        Picasso.get().load(path + "/portrait_uncanny." + extension).into(imageChar2);

    }
}
