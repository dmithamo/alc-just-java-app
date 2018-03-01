package com.example.dennis.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCups = 0;
    int pricePerCup = 200;

    String whipcreamStatus;
    String chocolateStatus;

    int chocolatePrice = 50;
    int whipcreamPrice = 100;

    EditText nameEditText;
    String nameOfShopper;

    String theOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.name_edit);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));
    }

    /**
     * This method is called when the add(+) button is clicked.
     */
    public void increment(View view) {
        if (numberOfCups < 100) {
            numberOfCups += 1;
        }
        display(numberOfCups);
        summarizeOrder(null);
    }

    /**
     * This method is called when the add(+) button is clicked.
     */
    public void decrement(View view) {
        if (numberOfCups > 0)
            numberOfCups -= 1;
        display(numberOfCups);
        summarizeOrder(null);
    }

    public int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = pricePerCup;

        if (hasWhippedCream) {
            basePrice += whipcreamPrice;
        }

        if (hasChocolate) {
            basePrice += chocolatePrice;
        }

        return numberOfCups * basePrice;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void summarizeOrder(View view) {
        CheckBox whipcreamCheckBox = findViewById(R.id.whip_cream);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate);
        boolean whipcreamIsChecked = whipcreamCheckBox.isChecked();
        boolean chocolateIsChecked = chocolateCheckBox.isChecked();

        if (whipcreamIsChecked && chocolateIsChecked) {
            whipcreamStatus = getString(R.string.affirmative);
            chocolateStatus = getString(R.string.affirmative);

        } else if (whipcreamIsChecked && !chocolateIsChecked) {
            whipcreamStatus = getString(R.string.affirmative);
            chocolateStatus = getString(R.string.negative);

        } else if (!whipcreamIsChecked && chocolateIsChecked) {
            whipcreamStatus = getString(R.string.negative);
            chocolateStatus = getString(R.string.affirmative);

        } else {
            whipcreamStatus = getString(R.string.negative);
            chocolateStatus = getString(R.string.negative);
        }

        int totalPrice = calculatePrice(whipcreamIsChecked, chocolateIsChecked);

        theOrder = createOrderSummary(totalPrice);
        displayMessage(Integer.toString(totalPrice));
//
////      Change the "press 'ORDER' button to confirm order, because order has been placed.
//        TextView pressOrder = findViewById(R.id.press_order_text_view);
//        pressOrder.setText("Order Confirmed :) ");


    }


    /**
     * Method creates a personalized order summary
     *
     * @return String thankNote
     */
    public String createOrderSummary(int price) {
        theOrder = "\nWhip Cream Topping: " + whipcreamStatus;
        theOrder += "\n\nChocolate Topping: " + chocolateStatus + "\n\nQuantity: " + numberOfCups;
        if (numberOfCups == 1){
            theOrder += " cup";
        }else if (numberOfCups > 1){
            theOrder += " cups";
        }

        theOrder += "\n\nTotal: Ksh " + price + ".00";
        return theOrder;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(getString(R.string.currency_units) + message);
    }


    //    Send order to email
    public void sendOrder(View view) {
        nameOfShopper = nameEditText.getText().toString();
        if (nameOfShopper.length() > 0 && numberOfCups > 0){
            Intent sendOrder = new Intent(Intent.ACTION_SENDTO);
            sendOrder.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.enail_subject) + " " + nameOfShopper);
            sendOrder.putExtra(Intent.EXTRA_TEXT, theOrder);
            sendOrder.setData(Uri.parse("mailto:"));
            if (sendOrder.resolveActivity(getPackageManager()) != null) {
                startActivity(sendOrder);
            }
        }else if (nameOfShopper.length() == 0){
            Toast alert = Toast.makeText(this, R.string.toast_no_name, Toast.LENGTH_LONG);
            alert.show();
        }
        if (numberOfCups == 0) {
            Toast.makeText(this, R.string.toast_zero_coffees, Toast.LENGTH_LONG).show();
        }

    }

}