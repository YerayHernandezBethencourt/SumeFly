package com.example.sumefly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.python.util.PythonInterpreter;

public class Transcription extends AppCompatActivity {
    PythonInterpreter interpreter = new PythonInterpreter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcription);

        interpreter.exec("import os\n" +
                "import openai\n" +
                "openai.api_key = os.getenv(\"OPENAI_API_KEY\")\n" +
                "openai.api_key= \"sk-<your key>\"\n" +
                "completion = openai.ChatCompletation.create(\n" +
                "    model=\"gpt-3.5-turbo\",\n" +
                "    messages=[\n" +
                "        {\"role\": \"user\", \"content\": \"hello\"}\n" +
                "    ],\n" +
                ")\n" +
                "print(completion.choices[0].message)");

    }
}