package com.example.dennis.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int toppingsPrice = 0;
    int coffeePrice = 0;

    int numberOfCups = 0;
    int pricePerCup = 200;

    String whipcreamTopping;
    String chocolateTopping;

    int chocolatePrice = 50;
    int whipcreamPrice = 70;

    EditText nameEditText;
    String nameOfShopper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.name_edit);
    }

    /**
     * This method checks which topping is selected
     */

    public void isClicked(View view) {


        CheckBox whipcreamCheckBox = findViewById(R.id.whip_cream);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate);
        boolean whipcreamIsChecked = whipcreamCheckBox.isChecked();
        boolean chocolateIsChecked = chocolateCheckBox.isChecked();

        if (whipcreamIsChecked && chocolateIsChecked) {
            whipcreamTopping = getString(R.string.affirmative);
            chocolateTopping = getString(R.string.affirmative);

        } else if (whipcreamIsChecked && !chocolateIsChecked) {
            whipcreamTopping = getString(R.string.affirmative);
            chocolateTopping = getString(R.string.negative);

        } else if (!whipcreamIsChecked && chocolateIsChecked) {
            whipcreamTopping = getString(R.string.negative);
            chocolateTopping = getString(R.string.affirmative);

        } else {
            whipcreamTopping = getString(R.string.negative);
            chocolateTopping = getString(R.string.negative);
        }

    }

    /**
     * This method is called when the add(+) button is clicked.
     */
    public void increment(View view) {
        numberOfCups += 1;
        display(numberOfCups);
    }

    /**
     * This method is called when the add(+) button is clicked.
     */
    public void decrement(View view) {
        if (numberOfCups > 0)
            numberOfCups -= 1;
        display(numberOfCups);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

//        Check whether checkboxes are checked

        CheckBox whipcreamCheckBox = findViewById(R.id.whip_cream);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate);
        boolean whipcreamIsChecked = whipcreamCheckBox.isChecked();
        boolean chocolateIsChecked = chocolateCheckBox.isChecked();

//        Retrieve value of numberOfCups
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        String q = quantityTextView.getText().toString();

        numberOfCups = Integer.parseInt(q);

        coffeePrice = pricePerCup * numberOfCups;

        if (whipcreamIsChecked && chocolateIsChecked) {
            toppingsPrice = numberOfCups * (chocolatePrice + whipcreamPrice);
        } else if (whipcreamIsChecked && !chocolateIsChecked) {
            toppingsPrice = numberOfCups * whipcreamPrice;
        } else if (!whipcreamIsChecked && chocolateIsChecked) {
            toppingsPrice = numberOfCups * chocolatePrice;
        }

        int totalPrice = coffeePrice + toppingsPrice;

        String thankNote = createOrderSummary(totalPrice);
        displayMessage(thankNote);

//      Change the "press 'ORDER' button to confirm order, because order has been placed.
        TextView pressOrder = findViewById(R.id.press_order_text_view);
        pressOrder.setText("Order Confirmed :) ");
    }

    /**
     * Method creates a personalized order summary
     *
     * @return String thankNote
     */
    public String createOrderSummary(int price) {
        nameOfShopper = nameEditText.getText().toString();
        String thankNote = "Name: " + nameOfShopper + "\nWhip Cream Topping: " + whipcreamTopping;
        thankNote += "\nChocolate Topping: " + chocolateTopping + "\nQuantity: " + numberOfCups + " cups";
        thankNote += "\nTotal: Ksh " + price + ".00";
        return thankNote;
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}