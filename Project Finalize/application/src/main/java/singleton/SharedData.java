package singleton;

public class SharedData {
    private static SharedData instance = new SharedData();
    private String selectedCategory;

    private SharedData() {}

    public static SharedData getInstance() {
        return instance;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}
