
package com.qualcomm.vuforia.samples.SampleApplication.utils;

import com.qualcomm.vuforia.samples.VuforiaSamples.app.ImageTargets.ImageTargetRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen.
 * This view can also be used to capture touch events, such as a user
 * interacting with drawn objects.
 */
public class SampleApplicationGLView extends GLSurfaceView {

    private final ImageTargetRenderer mRenderer;
    
    public SampleApplicationGLView(Context context, ImageTargetRenderer renderer) {
        super(context);
        mRenderer= renderer;
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 500;
    private float mPreviousX;

    public void init(boolean i, int j, int b) {
    	return;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;

                mRenderer.setAngle(
                        mRenderer.getAngle() +
                        ((dx) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                requestRender();
        }

        mPreviousX = x;
        return true;
    }

    public void toggleRotationMode(boolean isAutoRotation){
    	if(isAutoRotation){
    		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    	}
    	else{
    		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    	}
    	mRenderer.toggleRotation(isAutoRotation);
    }
}
