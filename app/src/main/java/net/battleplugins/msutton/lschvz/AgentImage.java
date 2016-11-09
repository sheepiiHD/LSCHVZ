package net.battleplugins.msutton.lschvz;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class AgentImage extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_image);

        // Create an instance of Camera
        if(getCameraInstance() != null) {
            mCamera = getCameraInstance();

            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }

    }
    public static Camera getCameraInstance()
    {
        Camera c = null;

        try
        {
            //attempt to get a Camera instance
            c = Camera.open(0);
        }
        catch(Exception e)
        {
            Log.d("test", "This isn't working.");
        }
        return c;//returns null if camera is unavailable
    }


    public void onPause()
    {
        super.onPause();
        mCamera.stopPreview();
        releaseCamera();
    }


    private void releaseCamera()
    {
        if(mCamera != null)
        {
            mCamera.release();
            mCamera = null;
        }
    }
}
