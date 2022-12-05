package com.example.myapplication;

import java.util.List;

public class MainModel {

    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result{
        private int id;
        private String name;
        private String imageURL;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", imageURL='" + imageURL + '\'' +
                    '}';
        }
    }
}
