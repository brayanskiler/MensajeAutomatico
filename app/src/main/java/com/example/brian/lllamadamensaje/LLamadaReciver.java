package com.example.brian.lllamadamensaje;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class LLamadaReciver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        String n =MainActivity.intent.getExtras().getString("NumeroTelefonico");
        String mensaje = MainActivity.intent.getExtras().getString("MensajeTexto");

        Toast.makeText(context,n +"    numero del intent",Toast.LENGTH_LONG).show();

        try {
            System.out.println("Receiver start");

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);


            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING )){
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);


                if(incomingNumber.equals(n) ){
                    Toast.makeText(context,"Mandando mensaje al numero igual RECIVER",Toast.LENGTH_SHORT).show();



                    //Uri uri = Uri.parse("smsto:"+incomingNumber);
                    //Intent intent1 = new Intent(Intent.ACTION_SEND,uri);
                    //intent1.putExtra("sms_body",MainActivity.intent.getExtras().getString("MensajeTexto"));
                    //context.startActivity(intent1);

                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    sendIntent.putExtra("address", incomingNumber);
                    sendIntent.putExtra("sms_body", mensaje);
                    context.startActivity(sendIntent);


                }


                Toast.makeText(context,"Incoming Call State",Toast.LENGTH_SHORT).show();
                Toast.makeText(context,"Ringing State Number is -"+incomingNumber,Toast.LENGTH_SHORT).show();
                //mensaje m = new mensaje(incomingNumber);
                //m.compara();
            }

            if (TelephonyManager.EXTRA_INCOMING_NUMBER == n){
                Toast.makeText(context,"Los numeros son iguales", Toast.LENGTH_LONG);
            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                Toast.makeText(context,"Call Received State",Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"Call Idle State",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


