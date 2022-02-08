package com.example.myapplication;

import static io.paysky.util.AllURLsStatus.STAGINIG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import io.paysky.data.model.SuccessfulCardTransaction;
import io.paysky.data.model.SuccessfulWalletTransaction;
import io.paysky.exception.TransactionException;
import io.paysky.ui.PayButton;
import io.paysky.util.AppUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PayButton payButton = new PayButton(this);

        payButton.setMerchantId("10098206085");        // your merchant id
        payButton.setTerminalId("94674732");         // your terminal id
        payButton.setAmount(10.0);        // the payment amount
        payButton.setCurrencyCode(434);        // use 434 for the Libyan currency [Optional]
        payButton.setMerchantSecureHash("63336634373139362D616530392D346532642D383861372D623666333963656563623265");        // Merchant secure hash
        payButton.setTransactionReferenceNumber(AppUtils.generateRandomNumber());
        // unique transaction reference number.
        payButton.setProductionStatus(STAGINIG);        // "Use AllURLsStatus.STAGINIG for test environment,
        // AllURLsStatus.PRODUCTION for production"
        payButton.setLang("ar");


        payButton.createTransaction(
                new PayButton.PaymentTransactionCallback() {
                    @Override
                    public void onCardTransactionSuccess
                            (SuccessfulCardTransaction cardTransaction) {
                        Toast.makeText(MainActivity.this,
                                cardTransaction.toString(),
                                Toast. LENGTH_LONG)
                                .show();
                    }
                    @Override
                    public void onWalletTransactionSuccess
                            (SuccessfulWalletTransaction walletTransaction) {
                        Toast.makeText(MainActivity.this,
                                walletTransaction.toString(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                    @Override
                    public void onError(TransactionException error) {
                        Toast.makeText(MainActivity.this,
                                error.toString(),
                                Toast.LENGTH_LONG)
                                .show();
                    }            });


        }
}