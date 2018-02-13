/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.dennis.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int number = 0;
    int pricePerCup = 2;
    String name = "_dennoT8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the add(+) button is clicked.
     */
    public void increment(View view) {
        number += 1;
        display(number);
    }

    /**
     * This method is called when the add(+) button is clicked.
     */
    public void decrement(View view) {
        if (number > 0)
            number -= 1;
        display(number);
    }

    /**
     * Method calculates and returns price of order.
     *
     * @return int price
     */
    public int calculatePrice() {
        int price = pricePerCup * number;
        return price;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String thankNote = createOrderSummary(price);
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
        String thankNote = "Name: " + name + "\nQuantity: " + number + " cups" + "\nTotal: £" + price;
        return thankNote;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}