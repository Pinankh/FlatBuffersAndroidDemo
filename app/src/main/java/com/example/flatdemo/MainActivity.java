package com.example.flatdemo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.google.flatbuffers.FlatBufferBuilder;

import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlatBufferBuilder builder = new FlatBufferBuilder(1024);

        int nameOffset = builder.createString("Goblin");
        int colorOffset = builder.createString("Green");

        Monster.startMonster(builder);
        Monster.addName(builder, nameOffset);
        Monster.addHp(builder, 150);
        Monster.addColor(builder, colorOffset);
        int monster = Monster.endMonster(builder);

        builder.finish(monster);

        ByteBuffer bb = builder.dataBuffer();
        Monster goblin = Monster.getRootAsMonster(bb);

        Log.d("FlatBuffer", "Name: " + goblin.name());
        Log.d("FlatBuffer", "HP: " + goblin.hp());
        Log.d("FlatBuffer", "Color: " + goblin.color());
    }
}
