package nl.saxion.recipes.model;

import android.graphics.Bitmap;

import java.util.List;

public class Recipe extends Item {
    private List<Requirement> requirements;
    private int time;

    private int difficulty;
    private String instructions;
    private int servings;

    public Recipe() {}

    public Recipe(String name, Bitmap imageBitmap, List<Requirement> requirements, int time, int difficulty, int servings, String instructions) {
        super(name, imageBitmap);
        this.requirements = requirements;
        this.time = time;
        this.difficulty = difficulty;
        this.instructions = instructions;
        this.servings = servings;
    }

    public Recipe(String name, String placeholder, List<Requirement> requirements, int time, int difficulty, String instructions, int servings) {
        this(name, null, requirements, time, difficulty, servings, instructions);
        this.placeholder = placeholder;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

}
