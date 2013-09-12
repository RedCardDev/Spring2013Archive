package com.example.kickshot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class BoardView extends View {

        private Bitmap m_board, m_ballBitmap, m_dice1Bitmap, m_dice2Bitmap;
        public SoccerBall m_ball;
        private int m_ballPos, m_currentTeam, SCREEN_WIDTH, SCREEN_HEIGHT;
        private int[] m_diceImages;
        public int didscore;
        
        public BoardView(Context context, int screenHeight, int screenWidth) {
                super(context);
                SCREEN_WIDTH = screenWidth;
                SCREEN_HEIGHT = screenHeight;
                m_ballPos=SCREEN_HEIGHT/2 - 20;
                m_currentTeam = -1;
                m_ball = new SoccerBall(m_currentTeam);
                
                m_ballBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_ball.resID()), 40, 40, true);
                m_board = BitmapFactory.decodeResource(getResources(), R.drawable.board);
                m_board = Bitmap.createScaledBitmap(m_board, SCREEN_WIDTH , SCREEN_HEIGHT, true);

                //load dice Image array
                m_diceImages = new int[6];
                m_diceImages[0] = R.drawable.dice1;
                m_diceImages[1] = R.drawable.dice2;
                m_diceImages[2] = R.drawable.dice3;
                m_diceImages[3] = R.drawable.dice4;
                m_diceImages[4] = R.drawable.dice5;
                m_diceImages[5] = R.drawable.dice6;

                m_dice1Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_diceImages[0]), 40, 40, true);
                m_dice2Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_diceImages[0]), 40, 40, true);
                
                didscore = 0;	//init didscore
                //moveBall(1);
        }
        
        @Override  
        protected void onDraw(Canvas canvas) {
        	canvas.drawBitmap(m_board, 0, 0, null);
        	canvas.drawBitmap(m_ballBitmap, SCREEN_WIDTH/2 - 20 , m_ballPos, null);
        	canvas.drawBitmap(m_dice1Bitmap, SCREEN_WIDTH/2, 0, null);
        	canvas.drawBitmap(m_dice2Bitmap, SCREEN_WIDTH/2 - 40, 0, null);
        }

        public int getCurrentTeam()
        {
            return m_currentTeam;
        }

        public void resetball()
        {
            m_ballPos = SCREEN_HEIGHT/2 - 20;	//resets the ball to it's original position
        }
        
        public void moveBall(int distance)
        {
        	double dy = 37.0/1044*SCREEN_HEIGHT;     //distance between each zone line
            m_ballPos+=(int)(m_currentTeam*distance*dy);
        	//m_ballPos+=(int)(m_currentTeam*distance*(SCREEN_HEIGHT*0.03396*(SCREEN_HEIGHT/1044)));
        }

       public void turnover()
       {
    	   m_ball.turnover();
    	   m_ballBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_ball.resID()), 40, 40, true);
    	   m_currentTeam=m_ball.getTeam();
       }
       
       public void updateDice(int [] roll)
       {
           m_dice1Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_diceImages[roll[0]-1]), 40, 40, true);
           m_dice2Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_diceImages[roll[1]-1]), 40, 40, true);
       }
       
}