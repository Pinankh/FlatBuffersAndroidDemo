# FlatBuffers Android Demo

This sample demonstrates how to use Google's [FlatBuffers](https://google.github.io/flatbuffers/) library in an Android app for fast and memory-efficient data serialization.

## 📂 Project Structure
```
FlatBuffersAndroidDemo/
├── app/
│   ├── src/main/java/com/example/flatdemo/
│   │   ├── MainActivity.java
│   │   └── Monster.java (generated)
│   ├── src/main/res/layout/activity_main.xml
│   └── build.gradle
├── monster.fbs
└── README.md
```

---

## 📜 Schema File (`monster.fbs`)
```fbs
namespace com.example.flatdemo;

table Monster {
  name:string;
  hp:int;
  color:string;
}
root_type Monster;
```

### Compile the schema:
Use the [FlatBuffers compiler](https://google.github.io/flatbuffers/flatbuffers_guide_using_schema_compiler.html):
```bash
flatc --java monster.fbs
```
Place the generated Java files (`Monster.java`) in your `com.example.flatdemo` package.

---

## 🧠 MainActivity.java
```java
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
```

---

## ⚙️ Gradle Dependency
In your module-level `build.gradle`:
```gradle
dependencies {
    implementation 'com.google.flatbuffers:flatbuffers-java:2.0.0'
}
```

---

## 📚 Resources
- 🔗 [FlatBuffers GitHub Repo](https://github.com/google/flatbuffers)
- 🔗 [FlatBuffers Java Documentation](https://google.github.io/flatbuffers/flatbuffers_guide_use_java.html)
- 🔗 [ARCore using FlatBuffers](https://developers.google.com/ar)

---

## 🤝 Contribution
Feel free to fork this repo and submit pull requests. For any issues, open a GitHub issue.

---

## 📜 License
This project is licensed under the MIT License.

