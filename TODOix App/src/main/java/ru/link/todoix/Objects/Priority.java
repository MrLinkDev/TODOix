package ru.link.todoix.Objects;

public enum Priority {
    lowest, low, medium, high, highest;

    public static Priority valueOfString(String priority){
        switch (priority){
            case "lowest": return lowest;
            case "low": return low;
            case "high": return high;
            case "highest": return highest;

            default: return medium;
        }
    }
}
