package com.example.android.festin.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.festin.R;
import com.example.android.festin.Utils.TypeWriter;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ActivityIntro extends AppCompatActivity {

    TextView titleView;
    TypeWriter motoView;
    SurfaceView cameraPreview;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1000;
    ImageView scanBut;
    TextView infoView;


    /*Permission request method, for camera*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    /*Try-catch statements for starting the camera*/
                    try {
                        cameraSource.start( cameraPreview.getHolder() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_intro );

        initials();
        typeWriterText();
//        This commented for easing development.
//        scanningProcess();

    }

    /*Method for initializing intro views*/
    private void initials() {
        titleView = (TextView) findViewById( R.id.title_textView );
        titleView.getPaint().setShader( new LinearGradient( 0, 0, 0, titleView.getLineHeight(),
                getResources().getColor( R.color.colorAccent ),
                getResources().getColor( R.color.colorPrimaryDark ), Shader.TileMode.REPEAT ) );

        cameraPreview = (SurfaceView) findViewById( R.id.qr_surface );
        cameraPreview.setVisibility( View.INVISIBLE );

        infoView = (TextView) findViewById( R.id.info_view );
        infoView.setVisibility( View.INVISIBLE );

        scanBut = (ImageView) findViewById( R.id.scan_button );
        scanBut.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraPreview.setVisibility( View.VISIBLE );
                scanBut.setVisibility( View.INVISIBLE );
                infoView.setVisibility( View.VISIBLE );

                /*JUST FOR EASE in development. Delete after finish develepoment*/
                Intent toMenu = new Intent(ActivityIntro.this, ActivityMarket.class);
                Log.e("from receive detections", "before starting activity");
                startActivity(toMenu);
            }
        } );
    }

    /*Scanning QR-code method*/
    private void scanningProcess(){
        barcodeDetector = new BarcodeDetector.Builder( this )
                .setBarcodeFormats( Barcode.QR_CODE )
                .build();

        cameraSource = new CameraSource
                .Builder( this, barcodeDetector )
                .setRequestedPreviewSize( 900, 900 )
                .build();
        Log.e( "From Processor ", "start getHolder" );
        cameraPreview.getHolder().addCallback( new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.e( "From Processor ", "start REQUEST" );
                if (ActivityCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions( ActivityIntro.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);

                    return;

                }
                try {
                    Log.e( "From Processor ", "start camera" );
                    cameraSource.start( holder );
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        } );
        Log.e( "From Processor ", "end REQUEST" );

        barcodeDetector.setProcessor( new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            /*Getting detected QR-code and check its value. If gooo*/
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                /*If condition to be adjusted depending on event*/
                if(qrCodes.size() != 0)
                {
                    infoView.post( new Runnable() {
                        @Override
                        public void run() {
                            /*Create vibrator*/
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService( Context.VIBRATOR_SERVICE );
                            vibrator.vibrate( 1000 );
                            infoView.setText(qrCodes.valueAt( 0 ).displayValue);
                            cameraSource.stop( );

                            Intent toMenu = new Intent(ActivityIntro.this, ActivityMarket.class);
                            Log.e("from receive detections", "befor starting activity");
                            startActivity(toMenu);
                        }
                    } );
                }
            }
        } );

    }

    /*Setting up animated motto. Using TypeWriter class in Utils*/
    private void typeWriterText(){
        motoView = (TypeWriter) findViewById( R.id.moto_textView );
        motoView.setText( "" );
        motoView.setCharacterDelay( 100 );
        motoView.animateText( getResources().getText( R.string.moto ) );

    }
}
