package org.vkedco.mobappdev.touch_image_drag_drop_00001;

/**********************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 **********************************************************
 */

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchImageSketcher extends View {
	final Paint mBackgroundPaint;
	final ChessPiece mBlackKnight;
	final ChessPiece mBlackBishop;
	final ChessPiece mBlackKing;
	final ChessPiece mWhiteKnight;
	final ChessPiece mWhiteBishop;
	final ChessPiece mWhiteKing;
	final ArrayList<ChessPiece> mChessPieces;
	private boolean mDrawingEnabled = true;

	public TouchImageSketcher(Context context, AttributeSet atrs) {
		super(context, atrs);

		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(Color.WHITE);

		mBlackKnight = new ChessPiece(getResources(),
				R.drawable.black_knight_40_40, 50.0f, 30f, 30f);
		mBlackBishop = new ChessPiece(getResources(),
				R.drawable.black_bishop_40_40, 50.0f, 200f, 100f);
		mBlackKing = new ChessPiece(getResources(),
				R.drawable.black_king_40_40, 50.0f, 400f, 30f);

		mWhiteKnight = new ChessPiece(getResources(),
				R.drawable.white_knight_40_40, 50.0f, 30f, 300f);
		mWhiteBishop = new ChessPiece(getResources(),
				R.drawable.white_bishop_40_40, 50.0f, 200f, 200f);
		mWhiteKing = new ChessPiece(getResources(),
				R.drawable.white_king_40_40, 50.0f, 400f, 300f);

		mChessPieces = new ArrayList<ChessPiece>();

		mChessPieces.add(mBlackKnight);
		mChessPieces.add(mBlackBishop);
		mChessPieces.add(mBlackKing);

		mChessPieces.add(mWhiteKnight);
		mChessPieces.add(mWhiteBishop);
		mChessPieces.add(mWhiteKing);

	}

	public void draw(Canvas canvas) {
		if (mDrawingEnabled) {
			int width = canvas.getWidth();
			int height = canvas.getHeight();
			
			canvas.drawRect(0, 0, width, height, mBackgroundPaint);

			for (ChessPiece cp : mChessPieces)
				cp.draw(canvas);

			invalidate();
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		if ( mDrawingEnabled ) {
			for (ChessPiece cp : mChessPieces)
				cp.handleOnTouchEvent(event);
			return true;
		}
		else
			return true;
	}

	public void restoreInitialPositions() {
		for (ChessPiece cp : mChessPieces)
			cp.restoreInitialPosition();
	}
	
	public void enableDrawing() {
		mDrawingEnabled = true;
	}
	
	public void disableDrawing() {
		mDrawingEnabled = false;
	}

}
