package com.ldnanoline.FLEX;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ShapeActivity extends AppCompatActivity {

    private ServerSocket serverSocket;

    Handler UIHandler;

    Thread Thread1 = null;

    private EditText EDITTEXT;

    public static final int SERVERPORT = 502;

    private Socket client;
    private EditText editText_ip;
    private String ipAddress = "192.168.1.14";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);

        editText_ip = (EditText) findViewById(R.id.ipaddress);

        EDITTEXT = (EditText) findViewById(R.id.edittext);

        UIHandler = new Handler();

        Thread Thread1 = new Thread();
        Thread1.start();

        //this.Thread1 = new Thread(new Thread1());
        //this.Thread1.start();

        Button shapeReturnBtn = (Button) findViewById(R.id.shapeReturnBtn);
        shapeReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });
        class Thread1 implements Runnable {

            @Override
            public void run() {
                Socket socket = null;
                try{
                    ipAddress = editText_ip.getText().toString();
                    serverSocket = new ServerSocket(SERVERPORT);
                    client = new Socket(ipAddress, SERVERPORT);
                    PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                    printWriter.write("Test Message");
                    printWriter.flush();
                    printWriter.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Thread2 implements Runnable {

        private Socket clientSocket;

        private BufferedReader input;

        public Thread2(Socket clientSocket) {

            this.clientSocket = clientSocket;

            try {
                this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {

                try {
                    String read = input.readLine();
                    if(read!= null) {
                        UIHandler.post(new updateUIThread(read));
                    }
                    else {
                        Thread Thread1 = new Thread();
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class updateUIThread implements Runnable {

        private String msg;

        public updateUIThread(String str) {this.msg = str;}

        @Override
        public void run() {
            EDITTEXT.setText(EDITTEXT.getText().toString() + "Server: " + msg + "\n");
        }
    }

}