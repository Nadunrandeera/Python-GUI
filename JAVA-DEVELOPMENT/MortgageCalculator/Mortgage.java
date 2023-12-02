//package com.youtube;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Mortgage {
    final static int MONTH_IN_YEARS = 12;//field
    final static int PERCENT = 100;

    public static void main(String[] args) {

        double principal = readNumber("Principal (Rs.10k - Rs.1M): ",10000,1000000);
        double annualInterest = readNumber("Annual Interest Rate: ",1,30);
        int years = (int)readNumber("Period (Years): ",1,30);

        printMortgage(principal, annualInterest, years);
        printPaymentSchedule(principal, annualInterest, years);
    }
    private static void printPaymentSchedule(double principal, double annualInterest, int years) {
        System.out.println("\n");
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(int numberOfPaymentMade = 1; numberOfPaymentMade <= years *MONTH_IN_YEARS; numberOfPaymentMade++){
            double balance = generateBalance(principal, annualInterest, years,numberOfPaymentMade);
            String getCurrency = NumberFormat.getCurrencyInstance(new Locale("en", "lk")).format(balance);
            System.out.println(getCurrency);
        }
    }
    private static void printMortgage(double principal, double annualInterest, int years) {
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        double monthlyPayment = calculateMortgage(principal, annualInterest, years);
        String currency = NumberFormat.getCurrencyInstance(new Locale("en", "lk")).format(monthlyPayment);
        System.out.print("Monthly Payment: " + currency);
    }
    public static double readNumber(String prompt,double min,double max){
        Scanner scanner = new Scanner(System.in);

        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            else
                System.out.println("Enter the value between "+ min +" and "+ max);
        }
        return value;
    }
    public static double calculateMortgage(double principal,
                                           double annualInterest,
                                           int years){

        double monthlyInterest = (annualInterest / PERCENT) / MONTH_IN_YEARS;
        int numberOfPayment = (years * MONTH_IN_YEARS);

        double monthlyPayment = principal *
                (monthlyInterest * Math.pow((1 + monthlyInterest), numberOfPayment)) /
                (Math.pow((1 + monthlyInterest), numberOfPayment) - 1);
        return monthlyPayment;
    }
    public static double generateBalance(double principal,
                               double annualInterest,
                               int years, int numberOfPaymentMade){

        double monthlyInterest = (annualInterest / PERCENT) / MONTH_IN_YEARS;
        int numberOfPayment = (years * MONTH_IN_YEARS);

        double payOfBalance = principal*(Math.pow((1 + monthlyInterest),numberOfPayment)-
                (Math.pow((1 + monthlyInterest),numberOfPaymentMade)))/
                (Math.pow((1 + monthlyInterest),numberOfPayment)-1);

        return payOfBalance;

    }
}
