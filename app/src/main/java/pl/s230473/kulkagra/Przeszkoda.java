package pl.s230473.kulkagra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Przeszkoda extends View {
    private Paint p;
    private Rect[] collision = new Rect[7];
    private Rect meta;

    public Przeszkoda(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLUE);
    }

    public boolean isCollision(Point pos)
    {
        for(Rect id:collision) {
            if (pos.x-5 >= id.left && pos.x+5 <= id.right) {
                if (pos.y+5 >= id.top && pos.y-5 <= id.bottom) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEnd(Point pos)
    {
        if (pos.x-5 >= meta.left && pos.x+5 <= meta.right) {
                if (pos.y+5 >= meta.top && pos.y-5 <= meta.bottom) {
                    return true;
                }
            }
        return false;
    }

    public void createCollision()
    {
        collision[0] = new Rect(0, 100, getWidth()-50, 150);
        collision[1] = new Rect(0, 250, getWidth()-100, 350);
        collision[2] = new Rect(getWidth()-50, 250, getWidth(), 350);
        collision[3] = new Rect(0, 500, 40, 600);
        collision[4] = new Rect(90, 500, getWidth(), 600);
        collision[5] = new Rect(0, 650, getWidth()/2-10, 675);
        collision[6] = new Rect(getWidth()/2+10, 650, getWidth(), 675);
        meta = new Rect(0, getHeight()-50, getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setColor(Color.CYAN);
        canvas.drawRect(0, 100, getWidth()-50, 150, p );
        p.setColor(Color.YELLOW);
        canvas.drawRect(0, 250, getWidth()-100, 350, p );
        canvas.drawRect(getWidth()-50, 250, getWidth(), 350, p );
        p.setColor(Color.RED);
        canvas.drawRect(0, 500, 40, 600, p );
        canvas.drawRect(90, 500, getWidth(), 600, p );
        p.setColor(Color.GREEN);
        canvas.drawRect(0, 650, getWidth()/2-10, 675, p );
        canvas.drawRect(getWidth()/2+10, 650, getWidth(), 675, p );
        p.setColor(Color.GRAY);
        canvas.drawRect(0, getHeight()-50, getWidth(), getHeight(), p );
        p.setColor(Color.BLACK);
        p.setTextSize(32);
        canvas.drawText("Koniec", 100, getHeight()-15, p);
    }
}



