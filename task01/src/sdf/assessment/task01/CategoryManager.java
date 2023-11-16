package sdf.assessment.task01;

public class CategoryManager {
    private String app;
    private String category;
    private String rating;

    public CategoryManager (String app, String category, String rating) {
        this.app = app;
        this.category = category;        
        this.rating = rating;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    
    
}
