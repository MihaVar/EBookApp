package com.duikt.ebookapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duikt.ebookapp.R;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duikt.ebookapp.adapters.SubjectAdapter;
import com.duikt.ebookapp.models.SubjectModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SubjectModel> list;
    SubjectAdapter adapter;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    View header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recySubject);
        menu = findViewById(R.id.activity_menu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navView);

        list = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        list.add(new SubjectModel("Іван Нечуй-Левицький - Кайдашева сім'я", R.drawable.kaydasheva_simya));
        list.add(new SubjectModel("Іван Багряний - Тигролови", R.drawable.tyhrolovy));
        list.add(new SubjectModel("Василь Стефаник - Камінний хрест", R.drawable.kaminnyy_khrest));
        list.add(new SubjectModel("Пантелеймон Куліш - Чорна рада", R.drawable.chorna_rada));
        list.add(new SubjectModel("Тарас Шевченко - Гайдамаки", R.drawable.haydamaky));
        list.add(new SubjectModel("Іван Котляревський - Енеїда", R.drawable.eneida));
        list.add(new SubjectModel("Всеволод Нестайко - Тореадори з Васюківки", R.drawable.toreadory_z_vasiukivky));
        list.add(new SubjectModel("Микола Хвильовий - Я (Романтика)", R.drawable.ya_romantyka));
        list.add(new SubjectModel("Юрій Яновський - Майстер корабля", R.drawable.mayster_korablia));
        list.add(new SubjectModel("Михайло Коцюбинський - Тіні забутих предків", R.drawable.tini_zabutykh_predkiv));

        adapter = new SubjectAdapter(this,list);
        recyclerView.setAdapter(adapter);

        header = navigationView.getHeaderView(0);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                    case R.id.share:
                        String shareText = "Я використовую електронну бібліотеку українських творів";
                        
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT,shareText);
                        startActivity(intent);

                        drawerLayout.closeDrawer(GravityCompat.START);

                    case R.id.rate:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com")));
                        drawerLayout.closeDrawer(GravityCompat.START);
                }


                return false;
            }
        });

    }

    OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
    };
}