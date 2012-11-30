package org.vkedco.mobappdev.touch_image_drag_drop_00001;

/**********************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 **********************************************************
 */
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class ChessPiece {
	
	final Bitmap mBitmap;	  // image of chess piece
	float mStartX;			  // x of top left corner of bitmap
	float mStartY;			  // y of top left corner of bitmap
	float mCurrentX; 		  // current x coordinate of ChessPiece
	float mCurrentY; 		  // current y coordinate of ChessPiece
	float mActionDownX;   	  // x coordinate of ChessPiece of an action down
	float mActionDownY;   	  // y coordinate of ChessPiece of an action down
	float mActionMoveOffsetX; // x coordinate of a move action
	float mActionMoveOffsetY; // y coordinate of a move action
	float mEuclidDistThresh;  // threshold to decide if motion event should be consumed
	
	static float euclidDist(float x1, float y1, float x2, float y2) {
		return android.util.FloatMath.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	}
	
	public ChessPiece(Resources res, int res_id, float dthresh, float start_x, float start_y) {
		mBitmap = BitmapFactory.decodeResource(res, res_id);
		mStartX = start_x;
		mStartY = start_y;
		mCurrentX = start_x;
		mCurrentY = start_y;
		mEuclidDistThresh = dthresh;
	}
	
	Bitmap getBitmap() { return mBitmap; }
	
	float getCurrentX()       { return mCurrentX; }
	void setCurrentX(float x) { mCurrentX = x; }
	
	float getCurrentY()       { return mCurrentY; }
	void setCurrentY(float y) { mCurrentY = y; }
	
	float getActionDownX()       { return mActionDownX; }
	void setActionDownX(float x) { mActionDownX = x; }
	
	float getActionDownY()       { return mActionDownY; }
	void setActionDownY(float y) { mActionDownY = y; }

	float getActionMoveOffsetX()       { return mActionMoveOffsetX; }
	void setActionMoveOffsetX(float x) { mActionMoveOffsetX = x; }
	
	float getActionMoveOffsetY()       { return mActionMoveOffsetY; }
	void setActionMoveOffsetY(float y) { mActionMoveOffsetX = y; }
	
	void draw(Canvas canvas) {
		if ( mCurrentX < 0f ) mCurrentX = mStartX;
		
		if ( (mCurrentX - mBitmap.getWidth()) >= 390f ) {
			mCurrentX = 350f;
		}
		
		if ( mCurrentY < 0f ) mCurrentY = mStartY;
		
		if ( (mCurrentY - mBitmap.getHeight()) >= 650f ) {
			mCurrentY = 650f;
		}
		
		canvas.drawBitmap(mBitmap, mCurrentX, mCurrentY, null);
	}
	
	void handleOnTouchEvent(MotionEvent me) {
		final float me_x = me.getX();
		final float me_y = me.getY();
		final float left_x = mCurrentX;
		final float top_y  = mCurrentY;
		final float right_x = left_x + (float)mBitmap.getWidth();
		final float bot_y   = mCurrentY + (float)mBitmap.getHeight();
		
		if ( euclidDist(left_x, top_y, me_x, me_y)  > mEuclidDistThresh && 
			 euclidDist(right_x, top_y, me_x, me_y) > mEuclidDistThresh &&
			 euclidDist(left_x, bot_y, me_x, me_y)  > mEuclidDistThresh &&
			 euclidDist(right_x, bot_y, me_x, me_y) > mEuclidDistThresh ) 
		{
			return;
		}
		
		final int action = me.getAction();
		switch ( action ) {
		case MotionEvent.ACTION_DOWN:
			this.setActionDownX(this.getCurrentX());
			this.setActionDownY(this.getCurrentY());
			this.setActionMoveOffsetX(me_x);
			this.setActionMoveOffsetY(me_y);
			break;
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
			this.setCurrentX(this.getActionDownX() + me_x - this.getActionMoveOffsetX());
			this.setCurrentY(this.getActionDownY() + me_y - this.getActionMoveOffsetY());
			break;
		case MotionEvent.ACTION_CANCEL:
			this.restoreInitialPosition();
			break;
		}
	}
	
	void restoreInitialPosition() {
		mCurrentX = mStartX;
		mCurrentY = mStartY;
	}
	
	
	
	

}

