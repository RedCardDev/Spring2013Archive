package com.example.kickshot; 

import java.util.Vector;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.Window;
import android.view.WindowManager;

public class GameBoardLayout extends Activity {
        private boolean m_galleryOn;
        private int m_playedCardIndex, m_currentPlayer;
        private BoardView m_board;
    private GameController m_controller;
        private Button m_playCardBtn, m_dontPlayCardBtn, m_discardCardBtn, m_skipturnBtn;
        private ImageView[] m_cardImage;
        private ImageView m_selectedCard;
        private LinearLayout m_gameBoard, m_cardGallery, m_enlargedCard;
        private Canvas m_canvas;
    private Player[] m_players;
        private ScaleAnimation m_expandAnimation;
        private Vector<Card> m_hand;
        private AnimationSet m_selectCardAnimationSet;
        private int prevcard;
        private Deck m_ref;
        private boolean temp;
       
       
        @Override
    public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
           WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_layout);
       
        Display display = getWindowManager().getDefaultDisplay();

        int SCREEN_WIDTH = display.getWidth();
        int SCREEN_HEIGHT = display.getHeight();


        m_canvas = new Canvas();
        m_playCardBtn = (Button) findViewById(R.id.PlayCard);
        m_dontPlayCardBtn = (Button) findViewById(R.id.DontPlayCard);
        m_discardCardBtn = (Button) findViewById(R.id.DiscardCard);
        m_skipturnBtn = (Button) findViewById(R.id.SkipTurn);
        m_discardCardBtn.setVisibility(View.GONE);
        m_skipturnBtn.setVisibility(View.VISIBLE); 
        m_playCardBtn.setVisibility(View.GONE);
        m_dontPlayCardBtn.setVisibility(View.GONE);
        initializePlayers(2, 6);
                initializeCardGallery();
        m_gameBoard = (LinearLayout)findViewById(R.id.LinLay);
        m_board = new BoardView(this, SCREEN_HEIGHT, SCREEN_WIDTH);
        m_controller = new GameController(2, 6);
        prevcard = 1;
        syncGame();
       
        this.onDraw(m_canvas);  //original
   //I think it crashes here
   //Possible idea of putting a loop either here or in mainactivity for it to cycle through players.
       
        }

        public void initializePlayers(int numPlayers, int handSize)
        {
        m_players = new Player[numPlayers];
        m_ref = new Deck(5);	//5 is the number of cards in the ref deck

        for(int i=0; i<numPlayers; i++)
        {
            int team = 1;
            if(i%2 == 0)
                team = -1;
            m_players[i] = new Player(42, handSize, team);

            for(int l = 0; l < 5; l++)	//this loop initializes the ref deck
        	{
                m_ref.addCard(new DirectFreeKickCard());
        	}//end for
//draw cards

/*
            m_players[i].addCard(new GoalShotRightCard(m_players[i].getTeam()));
//            m_players[i].addCard(new PassCard(m_players[i].getTeam()));
//            m_players[i].addCard(new InterceptCard(m_players[i].getTeam()));
            m_players[i].addCard(new GoalShotRightCard(m_players[i].getTeam()));
            m_players[i].addCard(new DirectFreeKickCard());
            m_players[i].addCard(new DirectFreeKickCard());
            m_players[i].addCard(new DirectFreeKickCard());
*/
        	m_players[i].drawrefCard(m_ref);	//each player draws 1 ref card
        	for(int j = 1; j < 6; j++)
            {
            	m_players[i].drawDeckCard(); 
            }
//  */      	
        	
/*            
            m_players[i].addCard(new DribbleCard(m_players[i].getTeam()));
            m_players[i].addCard(new BikeKickCard(m_players[i].getTeam()));
            m_players[i].addCard(new PassCard(m_players[i].getTeam()));
            m_players[i].addCard(new DribbleCard(m_players[i].getTeam()));
            m_players[i].addCard(new HeaderCard(m_players[i].getTeam()));
            m_players[i].addCard(new DribbleCard(m_players[i].getTeam()));
// */
        }
       
        }

        private void setUpAnimation()
        {
                 m_selectCardAnimationSet =  new AnimationSet(true);
             m_expandAnimation = new ScaleAnimation(0.3f, 1.0f, 0.3f, 1.0f,
                                        ScaleAnimation.RELATIVE_TO_SELF, ScaleAnimation.RELATIVE_TO_SELF);
//             m_expandAnimation.setDuration(1000);	//original
             m_expandAnimation.setDuration(100);	//i think this line determines how fast the card enlarges
             m_expandAnimation.setFillAfter(true);
             m_selectCardAnimationSet.addAnimation(m_expandAnimation);
             m_selectCardAnimationSet.setFillAfter(true);
        }
       
        public void initializeCardGallery()
        {
                m_currentPlayer = 0; //m_board.getCurrentTeam();
        m_galleryOn=false;
                m_enlargedCard = (LinearLayout) findViewById(R.id.englargedCardlayout);
        m_cardImage = new ImageView[6];
        m_hand = new Vector<Card>(6);
        
        m_cardGallery = (LinearLayout)findViewById(R.id.card1gallery);
        m_selectedCard = new ImageView(this);
        m_cardImage[0] = new ImageView(this);
        m_cardImage[1] = new ImageView(this);
        m_cardImage[2] = new ImageView(this);
        m_cardImage[3] = new ImageView(this);
        m_cardImage[4] = new ImageView(this);
        m_cardImage[5] = new ImageView(this);

        setUpAnimation();
//UpdateHand call
        updateHand();
     
      m_playCardBtn.setOnClickListener(new Button.OnClickListener() {
//Stuff that happens when the card is "played"
        public void onClick(View v) {
              m_players[m_currentPlayer].setPlayedCardIndex(m_playedCardIndex);

              m_enlargedCard.removeAllViews();
              m_playCardBtn.setVisibility(View.GONE);
              m_dontPlayCardBtn.setVisibility(View.GONE);
              m_discardCardBtn.setVisibility(View.GONE);
              m_skipturnBtn.setVisibility(View.VISIBLE);
              m_cardGallery.removeAllViews();
              m_galleryOn = false;
              for (int i = 0; i < m_players[m_currentPlayer].getHandSize(); i++) 
              {
                  m_cardImage[i].setClickable(true);
              }

 
              //send selected card to controller
              m_controller.evaluateCard(m_players[m_currentPlayer].playCard(), m_players[m_currentPlayer].getTeam(), m_board.getCurrentTeam());


//TODO need check for ref card
              if(m_players[m_currentPlayer].getCard().gameMode() == 10) //if card is a refcard
              {
//            	  if(m_ref.getSize() != 0)	//if ref deck is not empty
            	  if(m_ref.counter != 0)	//if ref deck is not empty
            	  {
                	  m_players[m_currentPlayer].drawrefCard(m_ref);	//draw a ref card            		  
            	  }//end if
                  else
                  {
                      m_players[m_currentPlayer].drawDeckCard();	//draws the next card from the deck and places it in the hand
                  }
              }//end if
              else
              {
                  m_players[m_currentPlayer].drawDeckCard();	//draws the next card from the deck and places it in the hand
              }
// */
//              m_players[m_currentPlayer].drawDeckCard();	//draws the next card from the deck and places it in the hand
              updateHand();
              //another updatehand call
              syncGame();
              //game sync
             
              m_currentPlayer++;
              if(m_currentPlayer > 1)
              {
                  m_currentPlayer = 0;
              }
            //I'm not sure the player change should be taking place here, and should probably be put in it's own function.
//I'm not sure the player change should be taking place here, and should probably be put in it's own function.

//              m_board.turnover();     //DEMO CODE ONLY!!!!!
              //m_board.turnover(); doesn't need to be here, in fact it really shouldn't be here. All it does is change the possession of the ball regardless of card choice.
        }

      });
        m_dontPlayCardBtn.setOnClickListener(new Button.OnClickListener() {
//Stuff that happens when card is "dont played"
            public void onClick(View v) {
                m_enlargedCard.removeAllViews();
                m_playCardBtn.setVisibility(View.GONE);
                m_dontPlayCardBtn.setVisibility(View.GONE);
                m_discardCardBtn.setVisibility(View.GONE);
                m_skipturnBtn.setVisibility(View.VISIBLE);
                for (int i = 0; i < m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(true);
                }
            }

        });
       
        m_discardCardBtn.setOnClickListener(new Button.OnClickListener() {
//Stuff that happens when card is "discarded"
        	//incomplete and not working
            public void onClick(View v) {
                m_enlargedCard.removeAllViews();
                m_playCardBtn.setVisibility(View.GONE);
                m_dontPlayCardBtn.setVisibility(View.GONE);
                m_discardCardBtn.setVisibility(View.GONE);
                m_skipturnBtn.setVisibility(View.VISIBLE);
                for (int i = 0; i < m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(true);
                }
//                m_players[m_currentPlayer].addtoDiscard(m_players[m_currentPlayer].playCard());
                m_players[m_currentPlayer].addCardtoDiscard(null);
                updateHand();
                //another updatehand call
                syncGame();
                //game sync
                
            }

        });

        m_skipturnBtn.setOnClickListener(new Button.OnClickListener() {
//Stuff that happens when turn is skipped
            public void onClick(View v) {
                m_enlargedCard.removeAllViews();
                m_playCardBtn.setVisibility(View.GONE);
                m_dontPlayCardBtn.setVisibility(View.GONE);
                m_discardCardBtn.setVisibility(View.GONE);
                m_skipturnBtn.setVisibility(View.VISIBLE);
                m_cardGallery.removeAllViews();
                m_galleryOn = false;

                m_currentPlayer++;	//increment player counter
                if(m_currentPlayer > 1)
                {
                    m_currentPlayer = 0;
                }

                for (int i = 0; i < m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(true);
                }
            }

        });
       
        
       
        }
   
//Update Hand
    private void updateHand()
    {
        m_cardImage[0].setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                m_playedCardIndex=0;
                m_selectedCard.setImageBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(0).resID()));
                m_selectedCard.setAnimation(m_expandAnimation);
                m_selectedCard.startAnimation(m_expandAnimation);
                m_enlargedCard.addView(m_selectedCard);
                m_playCardBtn.setVisibility(View.VISIBLE);
                m_dontPlayCardBtn.setVisibility(View.VISIBLE);
//                m_discardCardBtn.setVisibility(View.VISIBLE);
                m_skipturnBtn.setVisibility(View.GONE);

                for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++)
                {
                    m_cardImage[i].setClickable(false);
                }
            }
        });
        m_cardImage[1].setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                m_playedCardIndex=1;
                m_selectedCard.setImageBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(1).resID()));
                m_selectedCard.setAnimation(m_expandAnimation);
                m_selectedCard.startAnimation(m_expandAnimation);
                m_enlargedCard.addView(m_selectedCard);
                m_playCardBtn.setVisibility(View.VISIBLE);
                m_dontPlayCardBtn.setVisibility(View.VISIBLE);
                m_skipturnBtn.setVisibility(View.GONE);
                for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(false);
                }
            }
        });
        m_cardImage[2].setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                m_playedCardIndex=2;
                m_selectedCard.setImageBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(2).resID()));
                m_selectedCard.setAnimation(m_expandAnimation);
                m_selectedCard.startAnimation(m_expandAnimation);
                m_enlargedCard.addView(m_selectedCard);
                m_playCardBtn.setVisibility(View.VISIBLE);
                m_dontPlayCardBtn.setVisibility(View.VISIBLE);
                m_skipturnBtn.setVisibility(View.GONE);
                for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(false);
                }
            }
        });
        m_cardImage[3].setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                m_playedCardIndex=3;
                m_selectedCard.setImageBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(3).resID()));
                m_selectedCard.setAnimation(m_expandAnimation);
                m_selectedCard.startAnimation(m_expandAnimation);
                m_enlargedCard.addView(m_selectedCard);
                m_playCardBtn.setVisibility(View.VISIBLE);
                m_dontPlayCardBtn.setVisibility(View.VISIBLE);
                m_skipturnBtn.setVisibility(View.GONE);
                for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(false);
                }
            }
        });
        m_cardImage[4].setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                m_playedCardIndex=4;
                m_selectedCard.setImageBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(4).resID()));
                m_selectedCard.setAnimation(m_expandAnimation);
                m_selectedCard.startAnimation(m_expandAnimation);
                m_enlargedCard.addView(m_selectedCard);
                m_playCardBtn.setVisibility(View.VISIBLE);
                m_dontPlayCardBtn.setVisibility(View.VISIBLE);
                m_skipturnBtn.setVisibility(View.GONE);
                for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(false);
                }
            }
        });
        m_cardImage[5].setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                m_playedCardIndex=5;
                m_selectedCard.setImageBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(5).resID()));
                m_selectedCard.setAnimation(m_expandAnimation);
                m_selectedCard.startAnimation(m_expandAnimation);
                m_enlargedCard.addView(m_selectedCard);
                m_playCardBtn.setVisibility(View.VISIBLE);
                m_dontPlayCardBtn.setVisibility(View.VISIBLE);
                m_skipturnBtn.setVisibility(View.GONE);
                for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++) //need to go to HandSize
                {
                    m_cardImage[i].setClickable(false);
                }
            }
        });
    }
//End Update Hand

    public void onDraw(Canvas canvas)
        {      
        m_board.onDraw(canvas); //original
                m_gameBoard.addView(m_board);
        }

    public void showHand()
    {
    	m_galleryOn=true;
    	m_cardGallery.removeAllViews();

    	m_hand = m_players[m_currentPlayer].getHand();

        for(int i=0; i<m_players[m_currentPlayer].getHandSize(); i++)  //i should go to PLAYER_HANDSIZE
        {
        	m_cardImage[i].setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), m_hand.get(i).resID()), 200, 300, true));
        		//if crashing here you probably have the wrong emulator settings
        		//if that's not hte case need better bitmap memory management
        	m_cardGallery.addView(m_cardImage[i]);
        }
    }
 
    //synchronize the GameController with the BoardView
    private void syncGame()
    {
        m_board.moveBall(m_controller.getM_moveAmount() * m_controller.possession());	//moves based on color of ball
        m_board.updateDice(m_controller.getRoll());

        
        if(m_controller.possession() != m_board.getCurrentTeam())	//changes the possession of the ball
        {
            m_board.turnover();
        }//end if

        //Is a m_controller.reset method needed here?
    }


    public void checkgoalcondition(Player p)	//incomplete function 
    {
    	if(p.getTeam() == 1)					//if team is team 1
    	{
    		if(m_board.m_ball.getPos() == -12)	//if ball at team 1 goal
    		{
    			m_controller.m_score[0]++;		//team2 score ++
    		}//end if
    	}//end if
    	else
    	{
    		if(m_board.m_ball.getPos() == 12)
    		{
    			m_controller.m_score[1]++;
    		}//end if
    	}//end if
    	if(m_controller.m_score[0] == 5 || m_controller.m_score[1] == 5)	//if halftime is reached
    	{
    		m_board.m_ball.resetball();	//reset the ball
    	}//end if
    	if(m_controller.m_score[0] == 10 || m_controller.m_score[1] == 10)	//if halftime is reached
    	{
    		//TODO
    		//player X wins the game
  			//game win
    		//exit program
		}//end if
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                        int height) {
                // TODO Auto-generated method stub
                
        }


        public void surfaceCreated(SurfaceHolder holder) {
                // TODO Auto-generated method stub
               
        }


        public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub
               
        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if(m_galleryOn==false)
                {
                        showHand();
//                        return true;
                }
        }
        return false;
    }
 
}

