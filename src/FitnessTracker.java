/*
Project : Fitness Tracker
Group :
Abdullah Irfan    SE 231061
Adil Sajjad       SE 231076
Muhammad Husnain  SE 231089
Aman Khan         SE 232003
 */

import java.util.Scanner;

public class FitnessTracker {

    public static void main(String[] args) {
        FitnessTracker tracker = new FitnessTracker();
        tracker.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);

        User user = collectUserDetails(scanner);
        user.setActivityLevel(selectActivityLevel(scanner));
        user.setFitnessGoal(selectFitnessGoal(scanner));

        // Display BMI
        double bmi = user.calculateBMI();
        System.out.printf("Your BMI is: %.2f\n", bmi);

        // Display estimated daily caloric needs
        double dailyCalories = user.calculateDailyCalories();
        System.out.printf("Your estimated daily caloric needs: %.2f kcal\n", dailyCalories);

        // Provide nutrition advice based on fitness goal
        provideNutritionAdvice(user);

        // Suggest exercises based on fitness goal
        ExerciseSuggestion.suggestExercises(user);

        scanner.close();
    }

    private User collectUserDetails(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter your height (cm): ");
        double height = scanner.nextDouble();

        return new User(name, age, weight, height);
    }

    private int selectActivityLevel(Scanner scanner) {
        System.out.println("Select your daily activity level:");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Lightly active (light exercise/sports 1-3 days/week)");
        System.out.println("3. Moderately active (moderate exercise/sports 3-5 days/week)");
        System.out.println("4. Very active (hard exercise/sports 6-7 days a week)");
        System.out.println("5. Super active (very hard exercise/sports & physical job)");
        return scanner.nextInt();
    }

    private int selectFitnessGoal(Scanner scanner) {
        System.out.println("Select your fitness goal:");
        System.out.println("1. Lose weight");
        System.out.println("2. Maintain weight");
        System.out.println("3. Gain weight");
        return scanner.nextInt();
    }

    private void provideNutritionAdvice(User user) {
        switch (user.getFitnessGoal()) {
            case 1:
                System.out.println("To lose weight, focus on a balanced diet with reduced calorie intake.");
                System.out.println("Ensure adequate protein intake to maintain muscle mass.");
                break;
            case 2:
                System.out.println("To maintain weight, consume a balanced diet with moderate calorie intake.");
                System.out.println("Include a variety of nutrients from different food groups.");
                break;
            case 3:
                System.out.println("To gain weight, prioritize a diet rich in proteins and healthy fats.");
                System.out.println("Eat in a caloric surplus and focus on nutrient-dense foods.");
                break;
            default:
                System.out.println("Invalid fitness goal selected.");
                break;
        }
    }
}

class User {
    private String name;
    private int age;
    private double weight;
    private double height;
    private int activityLevel;
    private int fitnessGoal;

    // Constructor
    public User(String name, int age, double weight, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public int getFitnessGoal() {
        return fitnessGoal;
    }

    // Setters
    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setFitnessGoal(int fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    // BMI Calculation
    public double calculateBMI() {
        // BMI formula: weight / (height * height) in meters
        double heightInMeters = height / 100; // convert height from cm to meters
        return weight / (heightInMeters * heightInMeters);
    }

    // Daily Caloric Needs Calculation
    public double calculateDailyCalories() {
        // Harris-Benedict equation for BMR (Basal Metabolic Rate)
        double bmr;
        if (activityLevel == 1) {
            bmr = 1.2 * (66 + (13.75 * weight) + (5 * height) - (6.76 * age));
        } else if (activityLevel == 2) {
            bmr = 1.375 * (66 + (13.75 * weight) + (5 * height) - (6.76 * age));
        } else if (activityLevel == 3) {
            bmr = 1.55 * (66 + (13.75 * weight) + (5 * height) - (6.76 * age));
        } else if (activityLevel == 4) {
            bmr = 1.725 * (66 + (13.75 * weight) + (5 * height) - (6.76 * age));
        } else {
            bmr = 1.9 * (66 + (13.75 * weight) + (5 * height) - (6.76 * age));
        }

        // Adjust for fitness goal
        switch (fitnessGoal) {
            case 1: // Lose weight
                return bmr * 0.8; // Deficit for weight loss
            case 2: // Maintain weight
                return bmr;
            case 3: // Gain weight
                return bmr * 1.2; // Surplus for weight gain
            default:
                return bmr;
        }
    }
}

class ExerciseSuggestion {
    public static void suggestExercises(User user) {
        System.out.println("Hello " + user.getName() + ", based on your profile, here are some exercise suggestions:");

        switch (user.getFitnessGoal()) {
            case 1:
                System.out.println("Goal: Lose weight");
                System.out.println("1. Cardio exercises like running, cycling, swimming");
                System.out.println("2. High-intensity interval training (HIIT)");
                System.out.println("3. Strength training to build muscle and increase metabolism");
                break;
            case 2:
                System.out.println("Goal: Maintain weight");
                System.out.println("1. A balanced mix of cardio and strength training");
                System.out.println("2. Activities like yoga and pilates to improve flexibility and core strength");
                System.out.println("3. Regular moderate-intensity workouts to maintain fitness level");
                break;
            case 3:
                System.out.println("Goal: Gain weight");
                System.out.println("1. Strength training with a focus on heavy lifting");
                System.out.println("2. Compound exercises like squats, deadlifts, and bench press");
                System.out.println("3. Caloric surplus with protein-rich diet to build muscle");
                break;
            default:
                System.out.println("Invalid fitness goal selected.");
                break;
        }
    }
}
