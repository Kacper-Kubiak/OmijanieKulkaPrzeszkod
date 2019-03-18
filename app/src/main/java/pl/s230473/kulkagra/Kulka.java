package pl.s230473.kulkagra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class Kulka extends View {
    private Paint p;
    int pos_x = 10;
    int pos_y = 10;
    int move_pos_x = 0;
    int move_pos_y = 0;
    private final static int kulkaSize = 10;

    public Kulka(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.RED);
    }

    public void setMovePosX(int x) {
        move_pos_x = x;
    }

    public void setMovePosY(int y) {
        move_pos_y = y;
    }

    public void setPosX(int x) {
        pos_x = x;
    }

    public void setPosY(int y) {
        pos_y = y;
    }

    public void move () {
        if(pos_x+move_pos_x < getWidth() && pos_x+move_pos_x >= 0)
        {
            pos_x += move_pos_x;
        }
        if(pos_x+move_pos_x >= getWidth()-10)
        {
            pos_x = getWidth()-kulkaSize;
        }
        if(pos_x+move_pos_x < 0+kulkaSize)
        {
            pos_x = 0+kulkaSize;
        }

        if(pos_y+move_pos_y <= getHeight() && pos_y+move_pos_y >= 0)
        {
            pos_y += move_pos_y;
        }
        if(pos_y+move_pos_y > getHeight()-10)
        {
            pos_y = getHeight()-kulkaSize;
        }
        if(pos_y+move_pos_y < 0+kulkaSize)
        {
            pos_y = 0+kulkaSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(pos_x, pos_y, kulkaSize, p);
    }

    public Point getPos() {
        return new Point(pos_x, pos_y);
    }
}


