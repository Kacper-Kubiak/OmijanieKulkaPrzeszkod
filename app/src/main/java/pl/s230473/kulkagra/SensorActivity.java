package pl.s230473.kulkagra;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

class SensorActivity implements Runnable, SensorListener, SensorEventListener {
    Kulka kulka;
    Przeszkoda przeszkoda;
    Handler handler;
    Button button;
    boolean started = true;
    boolean gameover = false;
    boolean winner = false;

    public SensorActivity(Button b, Kulka k, Przeszkoda p, Handler h) {
        button = b;
        kulka = k;
        przeszkoda = p;
        handler = h;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int randomX = (int)(Math.random());
        int randomY = (int)(Math.random());
        kulka.setMovePosX((int) Math.ceil(-event.values[0])-randomX);
        kulka.setMovePosY((int) Math.ceil(event.values[1])-randomY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {

    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            przeszkoda.createCollision();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (started)
        {
            if(gameover || winner) {
                continue;
            }
            try {
                Thread.sleep(24);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        kulka.move();
                        kulka.invalidate();
                        if(przeszkoda.isCollision(kulka.getPos()))
                        {
                            gameover = true;
                            button.setVisibility(View.VISIBLE);
                        }
                        if(przeszkoda.isEnd(kulka.getPos())) {
                            winner = true;
                            button.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void restart() {
        Log.i("restart", "true");
        button.setVisibility(View.INVISIBLE);
        gameover = false;
        winner = false;
        kulka.setPosX(10);
        kulka.setPosY(10);
        kulka.invalidate();
    }
}

