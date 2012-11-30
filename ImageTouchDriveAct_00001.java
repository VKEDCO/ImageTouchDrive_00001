package org.vkedco.mobappdev.touch_image_drag_drop_00001;

/**********************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 **********************************************************
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class ImageTouchDriveAct_00001 extends Activity {

	TouchImageSketcher mTouchImageSketcher;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_image_drag_drop_act_00001);
        mTouchImageSketcher = (TouchImageSketcher) this.findViewById(R.id.sketcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_touch_image_drag_drop_act_00001, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch ( item.getItemId() ) {
		case R.id.restoreInitialPositions:
			mTouchImageSketcher.restoreInitialPositions();
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if ( mTouchImageSketcher != null ) {
			mTouchImageSketcher.disableDrawing();
			mTouchImageSketcher = null;
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if ( mTouchImageSketcher != null ) {
			mTouchImageSketcher.disableDrawing();
			mTouchImageSketcher = null;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if ( mTouchImageSketcher != null ) {
			mTouchImageSketcher.disableDrawing();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		if ( mTouchImageSketcher != null ) {
			mTouchImageSketcher.enableDrawing();
		}
		else {
			mTouchImageSketcher = (TouchImageSketcher) this.findViewById(R.id.sketcher);
			mTouchImageSketcher.enableDrawing();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if ( mTouchImageSketcher != null ) {
			mTouchImageSketcher.disableDrawing();
		}
	}
}
