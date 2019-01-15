
package com.ldnanoline.FLEX;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zgkxzx.modbus4And.requset.ModbusParam;
import com.zgkxzx.modbus4And.requset.ModbusReq;
import com.zgkxzx.modbus4And.requset.OnRequestBack;

import java.net.Socket;
import java.util.Arrays;


public class MazeActivity extends AppCompatActivity {

    private EditText editText_ip, editText_port, editText_mes;
    private String ipAddress;
    private int port = 0;
    private Socket client;
    EditText message;



    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        modbusInit();

        Button mazeReturnBtn = (Button)findViewById(R.id.mazeReturnBtn);
        mazeReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        /*
        Button readCoil = (Button)findViewById(R.id.readCoil);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCoilClickEvent();
            }
        });

        Button readDiscreteInputs = (Button)findViewById(R.id.readDiscreteInput);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ModbusReq.getInstance().readCoil(new OnRequestBack<boolean[]>() {
                                @Override
                                public void onSuccess(boolean[] booleen) {
                                    Log.d(TAG, "readCoil onSuccess " + Arrays.toString(booleen));
                                }

                                @Override
                                public void onFailed(String msg) {
                                    Log.e(TAG, "readCoil onFailed " + msg);
                                }
                            }, 1, 1, 2);
                        }
                    }).start();
                }

        });

        Button readHoldingRegisters = (Button)findViewById(R.id.readHoldingRegisters);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCoilClickEvent();
            }
        });
        Button readInputRegisters = (Button)findViewById(R.id.readInputRegisters);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCoilClickEvent();
            }
        });

        Button writeCoil = (Button)findViewById(R.id.writeCoil);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCoilClickEvent();
            }
        });

        Button writeRegister = (Button)findViewById(R.id.writeRegister);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCoilClickEvent();
            }
        });

        Button writeRegisters = (Button)findViewById(R.id.writeRegisters);
        readCoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCoilClickEvent();
            }
        });

        */
    }

    private void modbusInit() {

        ModbusReq.getInstance().setParam(new ModbusParam()
                .setHost("192.168.1.14")
                .setPort(502)
                .setEncapsulated(false)
                .setKeepAlive(true)
                .setTimeout(20000)
                .setRetries(0))
                .init(new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.d(TAG, "onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG, "onFailed " + msg);
                    }
                });


    }

    public void readCoilClickEvent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.getInstance().readCoil(new OnRequestBack<boolean[]>() {
                    @Override
                    public void onSuccess(boolean[] booleen) {
                        Log.d(TAG, "readCoil onSuccess " + Arrays.toString(booleen));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readCoil onFailed " + msg);
                    }
                }, 1, 1, 2);
            }
        }).start();
    }


    public void readDiscreteInputClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.getInstance().readDiscreteInput(new OnRequestBack<boolean[]>() {
                    @Override
                    public void onSuccess(boolean[] booleen) {
                        Log.d(TAG, "readDiscreteInput onSuccess " + Arrays.toString(booleen));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readDiscreteInput onFailed " + msg);
                    }
                }, 1, 1, 5);
            }
        }).start();
    }

    public void readHoldingRegistersClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //readHoldingRegisters
                ModbusReq.getInstance().readHoldingRegisters(new OnRequestBack<short[]>() {
                    @Override
                    public void onSuccess(short[] data) {
                        Log.d(TAG, "readHoldingRegisters onSuccess " + Arrays.toString(data));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readHoldingRegisters onFailed " + msg);
                    }
                }, 1, 2, 8);
            }
        }).start();
    }

    public void readInputRegistersClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.getInstance().readInputRegisters(new OnRequestBack<short[]>() {
                    @Override
                    public void onSuccess(short[] data) {
                        Log.d(TAG, "readInputRegisters onSuccess " + Arrays.toString(data));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readInputRegisters onFailed " + msg);
                    }
                }, 1, 2, 8);
            }
        }).start();
    }

    public void writeCoilClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.getInstance().writeCoil(new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "writeCoil onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "writeCoil onFailed " + msg);
                    }
                }, 1, 1, true);
            }
        }).start();
    }

    public void writeRegisterClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.getInstance().writeRegister(new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "writeRegister onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "writeRegister onFailed " + msg);
                    }
                }, 1, 1, 234);
            }
        }).start();
    }

    public void writeRegistersClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.getInstance().writeRegisters(new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "writeRegisters onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "writeRegisters onFailed " + msg);
                    }
                }, 1, 2, new short[]{211, 52, 34});
            }
        }).start();
    }
}

