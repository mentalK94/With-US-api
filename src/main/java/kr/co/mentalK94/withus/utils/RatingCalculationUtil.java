package kr.co.mentalK94.withus.utils;

public class RatingCalculationUtil {

    public static double calculate(int count, double existingRating, double addRating) {
        double calculatedRating = 0;

        calculatedRating = Double.parseDouble(String.format("%.2f", ((count * existingRating) + addRating) / (count+1)));

        return calculatedRating;
    }
}
