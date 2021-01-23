package com.sdk.network;

public class FindQuery {

    private String query;
    private String sort;
    private int limit;
    private int skip;

    private FindQuery(String query, String sort, int limit, int skip) {
        this.query = query;
        this.sort = sort;
        this.limit = limit;
        this.skip = skip;
    }

    public String getQuery() {
        return query;
    }

    public FindQuery setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getLimit() {
        return limit;
    }

    public FindQuery setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getSkip() {
        return skip;
    }

    public FindQuery setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public static class Builder {

        private String query = null;
        private String sort = "mcap";
        private int limit, skip;


        public FindQuery.Builder query(String query) {
            this.query = query;
            return this;
        }



        public FindQuery.Builder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public FindQuery.Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public FindQuery.Builder skip(int skip) {
            this.skip = skip;
            return this;
        }

        public FindQuery build() {
            return new FindQuery(this.query,  this.sort, this.limit, this.skip);
        }
    }
}
