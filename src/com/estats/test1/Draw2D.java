package com.estats.test1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Draw2D extends View{
    public Draw2D(Context context) {
	        super(context);
	}  
	@Override
	protected void onDraw(Canvas c){
	    super.onDraw(c);
	    
	    int Txt_size, c_height, c_width;
	    
	    c_height = c.getHeight();
	    c_width = c.getWidth();
	    Txt_size = (int)(c_width * 0.025);
	    
	    Paint paint = new Paint();
	    paint.setStyle(Paint.Style.FILL);

	    // ����������� ����� ����� ������
	    paint.setColor(Color.WHITE);
	    c.drawPaint(paint);
	    
	    
	 // ������ ������� �������������
	    paint.setColor(Color.GREEN);
	    c.drawRect(20, 200, 460, 230, paint);
	    
	   	//���
	    paint.setColor(Color.BLACK);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setAntiAlias(true);
	    c.drawLine(Txt_size+15, Txt_size+15, Txt_size+15, c_height - 50, paint);
	    
	    
	    //����� ������
	    paint.setColor(Color.BLACK);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setAntiAlias(true);
	    paint.setTextSize(Txt_size);
	    c.drawText(String.valueOf(c.getHeight()), 0, 30, paint);
	   
	  //����� ������
	    paint.setColor(Color.BLUE);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setAntiAlias(true);
	    paint.setTextSize(Txt_size);
	    c.drawText(String.valueOf(c.getWidth()), 0, 60, paint);
	    
	    
	    
	    // ������ ������ ����
	    paint.setAntiAlias(true);
	    paint.setColor(Color.YELLOW);
	    c.drawCircle(450, 30, 25, paint);
	    
	    
	    
	   
	    
	    // ����� ��� �����
	    int x = 310;
	    int y = 190;
	     
	    paint.setColor(Color.GRAY);
	    paint.setTextSize(25);
	    String str2rotate = "����� ������!";

	    // ������� �������������� ������������� ��� ���������� ������
	    Rect rect = new Rect();

	    // ������������ ����� �� ������ ������
	    c.rotate(-45, x + rect.exactCenterX(), y + rect.exactCenterY());

	    // ������ �����
	    paint.setStyle(Paint.Style.FILL);
	    c.drawText(str2rotate, x, y, paint);

	    // ��������������� �����
	    c.restore();
	    
	    // ������� ������ �� ��������
	    Resources res = this.getResources();
	    Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
	    c.drawBitmap(bitmap, 415, 655, paint);
	}
}
