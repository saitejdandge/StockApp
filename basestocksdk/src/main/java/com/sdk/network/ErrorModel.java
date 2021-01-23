package com.sdk.network;

import android.graphics.drawable.Drawable;

public class ErrorModel {
    private String statusCode;
    private String title;
    private String subTitle;
    private String primaryCTA;
    private String secondaryCTA;
    private Drawable image;

    private ErrorModel(ErrorModelBuilder errorModelBuilder) {
        this.statusCode = errorModelBuilder.statusCode;
        this.title = errorModelBuilder.title;
        this.subTitle = errorModelBuilder.subTitle;
        this.primaryCTA = errorModelBuilder.primaryCTA;
        this.secondaryCTA = errorModelBuilder.secondaryCTA;
        this.image = errorModelBuilder.image;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getPrimaryCTA() {
        return primaryCTA;
    }

    public Drawable getImage() {
        return image;
    }

    public String getSecondaryCTA() {
        return secondaryCTA;
    }

    public static class ErrorModelBuilder {

        private String statusCode;
        private String title;
        private String subTitle;
        private String primaryCTA;
        private String secondaryCTA;
        private Drawable image;

        public ErrorModelBuilder statusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ErrorModelBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ErrorModelBuilder subTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public ErrorModelBuilder primaryCTA(String primaryCTA) {
            this.primaryCTA = primaryCTA;
            return this;
        }

        public ErrorModelBuilder secondaryCTA(String secondaryCTA) {
            this.secondaryCTA = secondaryCTA;
            return this;
        }

        public ErrorModelBuilder image(Drawable image) {
            this.image = image;
            return this;
        }

        public ErrorModel build() {
            return new ErrorModel(this);
        }
    }
}
