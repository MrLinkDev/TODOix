package ru.link.todoix.Entities;

/**
 * Приоритет дел
 */
public enum Priority {
    lowest, low, medium, high, highest;

    /**
     * Преобразование priority типа String в enum
     *
     * @param priority - приоритет
     * @return Priority
     */
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
